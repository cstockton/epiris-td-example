package epiris.tower.defense.cstockton.org.wave;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.res.XmlResourceParser;

import epiris.tower.defense.cstockton.org.config.CreepSpellTypes;
import epiris.tower.defense.cstockton.org.config.CreepSprites;
import epiris.tower.defense.cstockton.org.config.CreepTypes;
import epiris.tower.defense.cstockton.org.config.WaveStrategyTypes;
import epiris.tower.defense.cstockton.org.creep.CreepConfiguration;
import epiris.tower.defense.cstockton.org.creep.CreepConstants;
import epiris.tower.defense.cstockton.org.damage.DamageResists;
import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.util.Debug;

public class WaveManager {

    private final static int STATE_INIT = 0;
    private final static int STATE_PAUSED = 1;
    private final static int STATE_TIMER = 2;
    private final static int STATE_WORKING = 3;
    private final static int STATE_FINISHED = 4;

    private final WaveStrategyTypes mWaveStrategyType;
    private int mCurrentState;
    private int mCurrentWave;
    private int mCurrentWaveInternal;
    private boolean mIsRepeating = false;

    private final ArrayList<Wave> mWaves = new ArrayList<Wave>();
    private final ArrayList<Wave> mWavesWorking = new ArrayList<Wave>();

    private float mTimerStartMillis;

    public WaveManager(final WaveStrategyTypes pWaveStrategyType) {
        super();

        mWaveStrategyType = pWaveStrategyType;
        mCurrentState = STATE_INIT;
        mCurrentWave = 0;
        mCurrentWaveInternal = 0;
    }

    public WaveManager initialize() {
        final XmlResourceParser parser = Registry.sContext.getResources().getXml(mWaveStrategyType.getXml());

        try {
            int eventType = parser.getEventType();
            Wave currentWave = null;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String name = null;

                switch (eventType) {

                    case XmlPullParser.START_TAG: {
                        name = parser.getName().toLowerCase();

                        if (name.equals("wave")) {
                            currentWave = new Wave(this);
                            currentWave.setRepeatable(parser.getAttributeBooleanValue(null, "repeat", parser.getAttributeBooleanValue(null, "repeat", false)));
                        }

                        if (name.equals("creep")) {

                            final float lifeBonus;
                            final float speed;
                            final CreepSprites sprite;
                            final float scale;
                            final float spriteRed;
                            final float spriteGreen;
                            final float spriteBlue;
                            final float spriteAlpha;
                            final DamageResists damageResists;
                            final CreepSpellTypes[] creepSpellTypes;

                            // first see if a creep type was specified
                            if(null != parser.getAttributeValue(null, "type") && parser.getAttributeValue(null, "type").length() > 0) {
                                final CreepTypes creepType = CreepTypes.getFromString(parser.getAttributeValue(null, "type"));
                                lifeBonus = 0f;
                                speed = creepType.getSpeed();
                                scale = creepType.getScale();
                                sprite = creepType.getSprite();
                                spriteRed = creepType.getSpriteRed();
                                spriteGreen = creepType.getSpriteGreen();
                                spriteBlue = creepType.getSpriteBlue();
                                spriteAlpha = creepType.getSpriteAlpha();
                                damageResists = creepType.getDamageResists();
                                creepSpellTypes = creepType.getCreepSpellTypes();

                            } else {
                                lifeBonus = 0f;
                                speed = CreepConstants.CREEP_SPEED_TIER_1;
                                scale = 1f;
                                sprite = null;
                                spriteRed = 1f;
                                spriteGreen = 1f;
                                spriteBlue = 1f;
                                spriteAlpha = 1f;
                                damageResists = CreepConstants.CREEP_RESIST_NONE;
                                creepSpellTypes = null;

                            }

                            final CreepConfiguration cc = new CreepConfiguration(
                                parser.getAttributeFloatValue(null, "lifeBonus", lifeBonus),
                                parser.getAttributeFloatValue(null, "speed", speed),
                                null != parser.getAttributeValue(null, "sprite") && parser.getAttributeValue(null, "sprite").length() > 0 ? CreepSprites.getFromString(parser.getAttributeValue(null, "sprite")) : sprite,
                                parser.getAttributeFloatValue(null, "scale", scale),
                                parser.getAttributeFloatValue(null, "spriteRed", spriteRed),
                                parser.getAttributeFloatValue(null, "spriteGreen", spriteGreen),
                                parser.getAttributeFloatValue(null, "spriteBlue", spriteBlue),
                                parser.getAttributeFloatValue(null, "spriteAlpha", spriteAlpha),
                                null != parser.getAttributeValue(null, "resists") && parser.getAttributeValue(null, "resists").length() > 0 ? CreepConstants.getDamageResistsByName(parser.getAttributeValue(null, "resists")) : damageResists,
                                creepSpellTypes
                            );

                            currentWave.addCreeps(
                                cc,
                                parser.getAttributeFloatValue(null, "delay", 1f),
                                parser.getAttributeIntValue(null, "count", 1)
                            );
                        }

                    } break;

                    case XmlPullParser.END_TAG: {
                        name = parser.getName();

                        if (name.equals("wave")) {
                            addWave(currentWave);
                        }

                    } break;
                }

                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Cannot parse XML");
        } catch (IOException e) {
            throw new RuntimeException("Cannot parse XML");
        } finally {
            parser.close();
        }

        return this;
    }

    public void onUpdate(final float pSecondsElapsed) {
        switch (mCurrentState) {

            default:
            case STATE_FINISHED: {
            } break;

            case STATE_PAUSED: {
            } break;

            case STATE_TIMER: {
                if (System.currentTimeMillis() > mTimerStartMillis) {
                    mCurrentState = STATE_WORKING;
                }
            } break;

            case STATE_WORKING: {

                // if the wave working size is empty we released them all
                if (mWavesWorking.size() == 0) {

                    // make sure all active creeps are killed before spawning more
                    if (Registry.sCreepManager.hasActiveCreeps()) {
                        break;
                    }
                    start(5);
                }

                // call the onUpdate of each wave
                synchronized (mWavesWorking) {
                    for (final Iterator<Wave> iterator = mWavesWorking.iterator(); iterator.hasNext();) {
                        final Wave wave = iterator.next();

                        // update the wave, if it returns false remove it
                        if (!wave.onUpdate(pSecondsElapsed)) {
                            iterator.remove();
                        }
                    }
                }
            }
            break;
        }
    }

    public WaveManager addWave(final Wave pWave) {
        mWaves.add(pWave);
        return this;
    }

    public boolean hasNextWave() {
        return mWaves.size() > mCurrentWaveInternal;
    }

    public int getWorkingWavesCount() {
        return mWavesWorking.size();
    }

    public WaveManager start(final int pSecondsDelay) {

        mTimerStartMillis = System.currentTimeMillis() + (pSecondsDelay * 1000f);
        mCurrentState = STATE_TIMER;
        startNextWave();

        return this;
    }

    public boolean isFinished() {
        return mCurrentState == STATE_FINISHED;
    }

    public WaveManager start() {
        mCurrentState = STATE_WORKING;
        return this;
    }

    public WaveManager stop() {
        mCurrentState = STATE_PAUSED;
        return this;
    }

    public boolean startNextWave() {
        mCurrentWave++;
        mCurrentState = STATE_WORKING;

        if (!hasNextWave()) {
            mIsRepeating = true;
            mCurrentWaveInternal = 0;
        }

        if (mIsRepeating) {
            while (mWaves.size() > mCurrentWaveInternal) {

                // fetch the current wave
                final Wave wave = mWaves.get(mCurrentWaveInternal);

                // increment current wave
                mCurrentWaveInternal++;

                if (wave.isRepeatable()) {
                    synchronized (mWavesWorking) {
                        wave.setWaveNum(mCurrentWave);
                        mWavesWorking.add(wave.release(mCurrentWaveInternal));
                    }

                    return true;
                }
            }

        } else {
            synchronized (mWavesWorking) {
                mWaves.get(mCurrentWaveInternal).setWaveNum(mCurrentWave);
                mWavesWorking.add(mWaves.get(mCurrentWaveInternal).release(mCurrentWaveInternal));
                mCurrentWaveInternal++;
            }

            return true;
        }

        mCurrentState = STATE_FINISHED;

        return false;
    }

    public int getCurrentWave() {
        return mCurrentWave;
    }
}