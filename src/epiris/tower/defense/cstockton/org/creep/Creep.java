package epiris.tower.defense.cstockton.org.creep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.UUID;

import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import epiris.tower.defense.cstockton.org.config.CreepSpellTypes;
import epiris.tower.defense.cstockton.org.config.CreepSprites;
import epiris.tower.defense.cstockton.org.config.PlayerStatisticTypes;
import epiris.tower.defense.cstockton.org.damage.DamageCalculator;
import epiris.tower.defense.cstockton.org.damage.DamageResists;
import epiris.tower.defense.cstockton.org.damage.Damages;
import epiris.tower.defense.cstockton.org.effect.IEffectable;
import epiris.tower.defense.cstockton.org.map.MapDirection;
import epiris.tower.defense.cstockton.org.map.MapPath;
import epiris.tower.defense.cstockton.org.map.MapSpawn;
import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.spell.ISpellCaster;
import epiris.tower.defense.cstockton.org.spell.ISpellListener;
import epiris.tower.defense.cstockton.org.spell.ISpellTargetable;
import epiris.tower.defense.cstockton.org.spell.Spell;
import epiris.tower.defense.cstockton.org.spell.affect.ISpellAffect;
import epiris.tower.defense.cstockton.org.spell.affect.ISpellAffectable;
import epiris.tower.defense.cstockton.org.spell.affect.ISpellAffects;
import epiris.tower.defense.cstockton.org.spell.affect.SpellAffect;
import epiris.tower.defense.cstockton.org.util.Util;
import epiris.tower.defense.cstockton.org.wave.Wave;

public class Creep implements ISpellCaster, ISpellListener, ISpellTargetable, ISpellAffectable, IEffectable {

    final public static class CreepSortByLowestCurrentHealth implements Comparator<ISpellTargetable> {

        @Override
        public int compare(final ISpellTargetable o1, final ISpellTargetable o2) {
            return (int) (((Creep) o1).mCreepLifeBar.getCurrentLife() - ((Creep) o2).mCreepLifeBar.getCurrentLife());
        }
    }

    final public static CreepSortByLowestCurrentHealth sCustomComparator = new CreepSortByLowestCurrentHealth();

    final public static float DR_1 = 1f;
    final public static float DR_2 = .75f;
    final public static float DR_3 = .5f;
    final public static float DR_4 = .25f;
    final public static float DR_5 = .1f;
    final public static float DR_6 = 0f;
    final public static float DR_COOLDOWN = 12f;
    final public static float[] DR_LOOKUP = new float[] { DR_1, DR_2, DR_3, DR_4, DR_5, DR_6 };
    final public static float MAX_ROOTED_DAMAGE = .05f;
    final public static float GLOBAL_COOLDOWN = 1.5f;

    final public static int CREEP_AI_TARGET_LOWEST = 1;
    final public static int CREEP_AI_TARGET_HIGHEST = 2;

    final public static int CREEP_WIDTH = 64;
    final public static int CREEP_HEIGHT = 64;
    final static public float AFFECT_TICK_MIN_TIME = .10f;
    final static public float CREEP_MIN_SPEED = 0.25f;

    final private ArrayList<SpellAffect> mActiveCreepAffects = new ArrayList<SpellAffect>();
    private float mSpellAffectAccumulator = 0;
    private final ArrayList<ISpellTargetable> mSpellTargetables = new ArrayList<ISpellTargetable>();

    // main creep components
    final private CreepLifeBar mCreepLifeBar;
    final private CreepBuffBar mCreepBuffBar;
    final private CreepBody mCreepBody;

    // map path
    final public MapPath mMapPath = new MapPath();
    public MapSpawn mMapSpawn;

    public boolean mAlive = false;
    public boolean mBlocked = false;
    private long mSpawnId = 0;

    public boolean mStunned = false;
    public boolean mSilenced = false;
    public boolean mRooted = false;

    public int mStunnedDr = 0;
    public int mSilencedDr = 0;
    public int mRootedDr = 0;

    public float mStunnedDuration = 0f;
    public float mSilencedDuration = 0f;
    public float mRootedDuration = 0f;
    public float mRootedInitialLife = 0f;

    public float mStunnedElapsed = 0f;
    public float mSilencedElapsed = 0f;
    public float mRootedElapsed = 0f;

    private float mCurrentMovementSpeedModifier = 1f;
    public boolean mCurrentSilenceFreedomModifier = false;
    public boolean mCurrentRootFreedomModifier = false;
    public boolean mCurrentStunFreedomModifier = false;
    public boolean mCurrentMovementFreedomModifier = false;

    private int mCurrentWaypointIndex = 1;

    private int mCurrentPathIndex = -1;
    private float mCurrentTimeElapsed = 0f;

    private float mCurrentX = 0f;
    private float mCurrentY = 0f;

    private float mSourceX = 0f;
    private float mSourceY = 0f;

    private float mTargetX = 0f;
    private float mTargetY = 0f;

    private float mLerpTime = 0f;

    public float mSpeed;
    public CreepSprites mSprite;
    public float mScale;
    public float mSpriteRed;
    public float mSpriteGreen;
    public float mSpriteBlue;
    public float mSpriteAlpha;
    public DamageResists mDamageResists;
    public CreepSpellTypes[] mCreepSpellTypes;
    public float[] mCreepSpellCooldowns;

    public Creep(final TiledTextureRegion pCreepTiledTextureRegion) {

        // setup the creep body
        mCreepBody = new CreepBody(pCreepTiledTextureRegion);

        // setup the creep life bar
        mCreepLifeBar = new CreepLifeBar(this);

        // setup the creep buff bar
        mCreepBuffBar = new CreepBuffBar(this, mCreepBody);

    }

    public Creep activate(final Wave pWave, final MapSpawn pMapSpawn, final CreepConfiguration pCreepConfiguration) {

        // set the map spawn ref
        mMapSpawn = pMapSpawn;

        // generate creep id
        mSpawnId = UUID.randomUUID().getMostSignificantBits();

        // configure the creep
        mSpeed = pCreepConfiguration.mSpeed;
        mSprite = pCreepConfiguration.mSprite;
        mScale = pCreepConfiguration.mScale;
        mSpriteRed = pCreepConfiguration.mSpriteRed;
        mSpriteGreen = pCreepConfiguration.mSpriteGreen;
        mSpriteBlue = pCreepConfiguration.mSpriteBlue;
        mSpriteAlpha = pCreepConfiguration.mSpriteAlpha;
        mDamageResists = pCreepConfiguration.mDamageResists;
        mCreepSpellTypes = pCreepConfiguration.mCreepSpellTypes;

        // handle cooldowns
        if(null != mCreepSpellTypes) {

            // this is length + 1 because 0 is the GCD (can't cast more then 1 spell per GLOBAL_COOLDOWN secs)
            mCreepSpellCooldowns = new float[mCreepSpellTypes.length + 1];
        }

        // reset the path
        mMapPath.reset();

        // set the attributes
        mCurrentMovementSpeedModifier = 1f;
        mCurrentSilenceFreedomModifier = false;
        mCurrentRootFreedomModifier = false;
        mCurrentStunFreedomModifier = false;
        mCurrentMovementFreedomModifier = false;
        mStunned = false;
        mSilenced = false;
        mRooted = false;

        mStunnedDr = 0;
        mSilencedDr = 0;
        mRootedDr = 0;

        mStunnedDuration = 0f;
        mSilencedDuration = 0f;
        mRootedDuration = 0f;

        mStunnedElapsed = 0f;
        mSilencedElapsed = 0f;
        mRootedElapsed = 0f;

        mRootedInitialLife = 0f;

        // set waypoint/path info
        mCurrentWaypointIndex = 1;
        mCurrentPathIndex = -1;

        // time info
        mCurrentTimeElapsed = 0f;
        mSpellAffectAccumulator = 0f;

        // coords
        mCurrentX = 0f;
        mCurrentY = 0f;
        mSourceX = 0f;
        mSourceY = 0f;
        mTargetX = 0f;
        mTargetY = 0f;

        // lerp time
        mLerpTime = 0f;

        // determine creep life
        final int creepLife = Registry.sGame.getCreepLife(pWave, this, pCreepConfiguration);

        // activate life/buff/body
        mCreepBody.activate(this);
        mCreepLifeBar.activate(creepLife);
        mCreepBuffBar.activate();

        // this creep is now alive, and unblocked
        mAlive = true;
        mBlocked = false;

        // remove modifiers
        mActiveCreepAffects.clear();

        // call the game event
        Registry.sGame.onCreepActivate(this);

        // place the creep
        place();

        // finally update the creep path
        updatePath();

        return this;
    }

    public Creep deactivate() {

        // this creep is now dead, so he cant be blocked
        mAlive = false;

        // deactivate life/buff/body
        mCreepBuffBar.deactivate();
        mCreepLifeBar.deactivate();
        mCreepBody.deactivate();

        // free spell affects
        for(final Iterator<SpellAffect> iterator = mActiveCreepAffects.iterator(); iterator.hasNext();) {
            final SpellAffect creepAffect = iterator.next();
            creepAffect.deactivate();
            creepAffect.finished();
        }

        // remove modifiers
        mActiveCreepAffects.clear();

        return this;
    }

    public void reset() {
        mCreepLifeBar.reset();
        mCreepBuffBar.reset();
        mCreepBody.reset();
    }

    public Creep leak() {

        // call the game event
        Registry.sGame.onCreepLeak(this);

        // now deactivate the creep
        deactivate();

        return this;
    }

    public Creep kill() {

        // call the game event
        Registry.sGame.onCreepKill(this);

        // now deactivate the creep
        deactivate();

        return this;
    }

    @Override
    public void heal(final float pAmount, final boolean pIsPercentage) {
        final int heal;

        if(pIsPercentage) {
            heal = (int) (getLifeBar().getMaxLife() * pAmount);

        } else {
            heal = (int) pAmount;
        }

        Registry.sCreepFloatingTextManager.healText(this, heal);
        getLifeBar().addCurrentLife(heal);
    }

    @Override
    public int damage(final Damages pDamages) {

        if(mAlive) {

            // add the adjusted damage
            final float totalDamage = DamageCalculator.getTotalDamage(pDamages, mDamageResists);

            // do the damage
            if(totalDamage > 0f) {
                getLifeBar().subtractCurrentLife(totalDamage);
                Registry.sCreepFloatingTextManager.damageText(this, totalDamage);

                // add total damage
                Registry.sPlayerStatistics.addStatistic(PlayerStatisticTypes.TOTAL_DAMAGE_DONE.mName, (int) totalDamage);

                return (int) totalDamage;
            }
        }

        return 0;
    }

    public long getSpawnId() {
        return mSpawnId;
    }

    public boolean isAlive() {
        return mAlive;
    }

    public boolean isBlocked() {
        return mBlocked;
    }

    public float getMovementSpeed() {
        final float adjustedSpeed = getCreepBaseSpeed();
        return adjustedSpeed > CREEP_MIN_SPEED ? adjustedSpeed : CREEP_MIN_SPEED;
    }

    public float getMovementSpeedModifier() {
        return mCurrentMovementSpeedModifier > CREEP_MIN_SPEED ? mCurrentMovementSpeedModifier : CREEP_MIN_SPEED;
    }

    public float getCreepBaseSpeed() {
        return mSpeed;
    }

    public DamageResists getCurrentDamageResist() {
        return mDamageResists;
    }

    public DamageResists getDamageResist() {
        return mDamageResists;
    }

    public Creep setBlocked(final boolean pBlocked) {
        mBlocked = pBlocked;
        return this;
    }

    public void place() {

        // get the spawn point
        final int[] startWayPointTiles = mMapSpawn.mWayPoints[0];

        // set the current tile index to be relative to this creep type
        mCreepBody.setCurrentTileIndex(
            mSprite.getCol(),
            mSprite.getRow()
        );

        // get the coordinates based off tiles
        final int[] coords = Registry.sMap.getCoordsFromTiles(startWayPointTiles[0], startWayPointTiles[1]);

        // set the current creep position
        mCurrentX = coords[0];
        mCurrentY = coords[1];

    }

    public void nextPath() {
        if(mCurrentPathIndex < (mMapPath.getLength() - 1)) {
            mCurrentPathIndex++;
            mCurrentTimeElapsed = 0f;

            // fetch our destination
            final int[] pathCoords = mMapPath.getCoords(mCurrentPathIndex);

            // set our source
            mSourceX = getX();
            mSourceY = getY();

            // set our target
            mTargetX = pathCoords[0];
            mTargetY = pathCoords[1];

            // measure the "distance" to gain the speed
            final float diffX = (mTargetX - mSourceX);
            final float diffY = (mTargetY - mSourceY);

            // time which the linear interpolation should occur
            mLerpTime = (float) (Math.sqrt(diffX * diffX + diffY * diffY) / getMovementSpeed());

            // update the walking animation
            animate();

        } else {
            mCurrentWaypointIndex++;
            updatePath();
        }
    }

    public void onCreepUpdate(float pSecondsElapsed) {

        // the elapsed spell affect seconds
        mSpellAffectAccumulator += pSecondsElapsed;

        // if it is more then the tick time, we begin integration
        while(mSpellAffectAccumulator >= AFFECT_TICK_MIN_TIME) {

            // reset the modifiers
            mCurrentMovementSpeedModifier = 1f;
            mCurrentSilenceFreedomModifier = false;
            mCurrentRootFreedomModifier = false;
            mCurrentStunFreedomModifier = false;
            mCurrentMovementFreedomModifier = false;

            // update spell affects
            for(final Iterator<SpellAffect> iterator = mActiveCreepAffects.iterator(); iterator.hasNext();) {
                if(!mAlive) {
                    break;
                }

                final SpellAffect creepAffect = iterator.next();
                if(creepAffect.isActive()) {

                    // tick
                    creepAffect.onTick(AFFECT_TICK_MIN_TIME);

                    // handle any affects
                    handleAffect(creepAffect);


                } else {
                    iterator.remove();
                    creepAffect.finished();

                }
            }

            mSpellAffectAccumulator -= AFFECT_TICK_MIN_TIME;
        }

        // handle stuns
        if(mStunned || mStunnedDr == 6) {
            mStunnedElapsed += pSecondsElapsed;

            if(mStunnedElapsed > mStunnedDuration) {
                mStunned = false;
                mStunnedDuration = 0f;

                // animate if we aren't rooted
                if(!mRooted) {
                    animate();
                }

                if(5 == mStunnedDr) {
                    mStunnedDuration = DR_COOLDOWN;
                    mStunnedDr++;

                } else if(6 == mStunnedDr) {
                    mStunnedElapsed = 0f;
                    mStunnedDr = 0;

                } else {
                    mStunnedElapsed = 0f;

                }
            }
        }

        // handle roots
        if(mRooted || mRootedDr == 6) {
            mRootedElapsed += pSecondsElapsed;

            if(mRootedElapsed > mRootedDuration || (mRootedInitialLife - mCreepLifeBar.getCurrentLife()) > (mCreepLifeBar.getMaxLife() * MAX_ROOTED_DAMAGE)) {
                mRooted = false;
                mRootedDuration = 0f;

                // animate if we arent stunned
                if(!mStunned) {
                    animate();
                }

                if(5 == mRootedDr) {
                    mRootedDuration = DR_COOLDOWN;
                    mRootedDr++;

                } else if(6 == mRootedDr) {
                    mRootedElapsed = 0f;
                    mRootedDr = 0;

                } else {
                    mRootedElapsed = 0f;

                }
            }
        }

        // handle roots
        if(mSilenced || mSilencedDr == 6) {
            mSilencedElapsed += pSecondsElapsed;

            if(mSilencedElapsed > mSilencedDuration) {
                mSilenced = false;
                mSilencedDuration = 0f;

                if(5 == mSilencedDr) {
                    mSilencedDuration = DR_COOLDOWN;
                    mSilencedDr++;

                } else if(6 == mSilencedDr) {
                    mSilencedElapsed = 0f;
                    mSilencedDr = 0;

                } else {
                    mSilencedElapsed = 0f;

                }
            }
        }

        // now allow casting
        if(mCreepSpellTypes != null) {

            // inc. our gcd
            mCreepSpellCooldowns[0] += pSecondsElapsed;

            // iterate our current creep spell types
            for(final CreepSpellTypes cst : mCreepSpellTypes) {

                // pull the cst ordinal local
                final int spellTypeIndex = cst.ordinal() + 1;

                // check the cooldown for this specific spell
                mCreepSpellCooldowns[spellTypeIndex] += pSecondsElapsed;

                // check our GCD
                if(mCreepSpellCooldowns[0] < GLOBAL_COOLDOWN || mSilenced || mStunned) {
                    continue;
                }

                // if we have elapsed more cooldown time then the spell requires
                // we will cast
                if(mCreepSpellCooldowns[spellTypeIndex] > cst.getCooldown()) {

                    // clear current targetables
                    mSpellTargetables.clear();

                    // grab all the targets in range, this can include self
                    Registry.sCreepManager.getSpellTargetablesInRange(mCurrentX, mCurrentY, cst.mRange, mSpellTargetables);

                    switch(cst.mCreepAi) {
                        case CREEP_AI_TARGET_LOWEST: {

                            // sort based on ai
                            Collections.sort(mSpellTargetables, sCustomComparator);

                        } break;
                    }

                    // contains count of how many were casted upon
                    int targetsCastUpon = 0;

                    // iterate our targetables
                    for(final ISpellTargetable target : mSpellTargetables) {
                        if(++targetsCastUpon > cst.mMaxTargets) {
                            break;
                        }

                        Spell.cast(this, target, cst.getSpellType(), cst.mCastDelay + (cst.mCastDelay * targetsCastUpon));

                    }

                    // reset cooldown timers
                    mCreepSpellCooldowns[0] = 0f;
                    mCreepSpellCooldowns[spellTypeIndex] = 0f;

                }
            }
        }

        // allow walking?
        if(false == mStunned && false == mRooted) {

            // increment current time step
            mCurrentTimeElapsed += pSecondsElapsed;

            // make sure we have a path
            if(-1 == mCurrentPathIndex) {
                nextPath();
            }

            // adjusted lerp time
            final float adjustedLerpTime = mLerpTime * (1f + (1f - getMovementSpeedModifier()));

            // get the new position
            mCurrentX = Util.lerp(mSourceX, mTargetX, adjustedLerpTime, mCurrentTimeElapsed);
            mCurrentY = Util.lerp(mSourceY, mTargetY, adjustedLerpTime, mCurrentTimeElapsed);

            // set the position
            mCreepBody.setPosition(mCurrentX, mCurrentY);
            mCreepLifeBar.setPosition(mCurrentX, mCurrentY);

            // set the z index position
            mCreepBody.setZIndex((int) mCurrentY);
            mCreepLifeBar.setZIndex((int) mCurrentY);

            // if the target and current position are identical move on to the next path
            if(mCurrentX == mTargetX && mCurrentY == mTargetY) {
                nextPath();
            }
        }
    }

    public void updatePath() {
        if(mMapSpawn.mWayPoints.length <= mCurrentWaypointIndex) {
            leak();

        } else {

            // get the waypoint
            final int[] waypoint = mMapSpawn.mWayPoints[mCurrentWaypointIndex];

            // get the path from the current coords to the to/from tiles
            Registry.sMap.getPathFromCoords(mMapPath, (int) getX(), (int) getY(), waypoint[0], waypoint[1]);

            // check the length
            if(mMapPath.getLength() == 0) {
                mBlocked = true;
            } else if(mBlocked) {
                mBlocked = false;
            }

            // reset the update index
            mCurrentPathIndex = -1;

        }
    }

    public void animate() {

        // animate the facing
        switch(MapDirection.fromCoords(mSourceX, mSourceY, mTargetX, mTargetY)) {
            case DOWN:
                mCreepBody.animate(
                    CreepSprites.getAnimationFrameDurations(),
                    mSprite.getDownAnimationTileIndex(),
                    mSprite.getDownAnimationEndTileIndex(),
                    true
                );
                break;
            case RIGHT:
                mCreepBody.animate(
                    CreepSprites.getAnimationFrameDurations(),
                    mSprite.getRightAnimationTileIndex(),
                    mSprite.getRightAnimationEndTileIndex(),
                    true
                );
                break;
            case UP:
                mCreepBody.animate(
                    CreepSprites.getAnimationFrameDurations(),
                    mSprite.getUpAnimationTileIndex(),
                    mSprite.getUpAnimationEndTileIndex(),
                    true
                );
                break;
            case LEFT:
                mCreepBody.animate(
                    CreepSprites.getAnimationFrameDurations(),
                    mSprite.getLeftAnimationTileIndex(),
                    mSprite.getLeftAnimationEndTileIndex(),
                    true
                );
                break;
            default:
        }
    }

    public CreepLifeBar getLifeBar() {
        return mCreepLifeBar;
    }

    @Override
    public float getX() {
        return mCurrentX;
    }

    @Override
    public float getY() {
        return mCurrentY;
    }

    @Override
    public float getCenterX() {
        return mCurrentX + (CREEP_WIDTH / 2);
    }

    @Override
    public float getCenterY() {
        return mCurrentY + (CREEP_HEIGHT / 2);
    }

    @Override
    public IEntity getEntity() {
        return mCreepBody;
    }

    @Override
    public long getSpellTargetableId(final Spell pSpell) {
        return mSpawnId;
    }

    @Override
    public boolean isSpellTargetableId(final long pSpellTargetableId) {
        return mSpawnId == pSpellTargetableId;
    }

    /******************************** AFFECTS ********************************/
    public void handleAffect(final SpellAffect creepAffect) {

        // movement speed handling
        mCurrentSilenceFreedomModifier = creepAffect.mCurrentSilenceFreedomModifier;
        mCurrentRootFreedomModifier = creepAffect.mCurrentRootFreedomModifier;
        mCurrentStunFreedomModifier = creepAffect.mCurrentStunFreedomModifier;
        mCurrentMovementFreedomModifier = creepAffect.mCurrentMovementFreedomModifier;

        // if we have moment freedom we don't allow speed modifiers
        if(mCurrentMovementFreedomModifier) {
            if(creepAffect.mCurrentMovementSpeedModifier > 0f) {
                mCurrentMovementSpeedModifier += creepAffect.mCurrentMovementSpeedModifier;
            }
        } else {
            mCurrentMovementSpeedModifier += creepAffect.mCurrentMovementSpeedModifier;

        }

        // stunned handling, if the affect has a stun modifier (the current requested
        // duration for the stun) then do the handling for it here
        if(mStunnedDr != 6 && creepAffect.mCurrentStunModifier > 0f) {
            final float durationAfterDr = creepAffect.mCurrentStunModifier * DR_LOOKUP[mStunnedDr];

            // the way stuns work, is if a 1sec stun is applied, then another
            // is applied of 6 seconds, the bigger stun takes affect, but
            // the current spent duration does not
            if(durationAfterDr > mStunnedDuration) {

                // the dr handling, each time a creep is stunned they become
                // more resilient to the affect till they gain temporary immunity
                // this is to prevent some ultra lame perma-stun tower build
                mStunnedDuration = durationAfterDr;

                // if we have not yet set a stunned flag, create the stunned and inc
                // our DR (diminishing returns) tracker
                if(!mStunned) {
                    mStunned = true;
                    mStunnedDr++;

                    // stop animations.. your stunned bitch!
                    mCreepBody.stopAnimation();

                }
            }
        }

        // root handling, if the affect has a root modifier (the current requested
        // duration for the root) then do the handling for it here
        if(mRootedDr != 6 && creepAffect.mCurrentRootModifier > 0f) {
            final float durationAfterDr = creepAffect.mCurrentRootModifier * DR_LOOKUP[mRootedDr];

            // the way roots work, is if a 1sec root is applied, then another
            // is applied of 6 seconds, the bigger root takes affect, but
            // the current spent duration does not
            if(durationAfterDr > mRootedDuration) {

                // the dr handling, each time a creep is rooted they become
                // more resilient to the affect till they gain temporary immunity
                // this is to prevent some ultra lame perma-root tower build
                mRootedDuration = durationAfterDr;

                // if we have not yet set a rooted flag, create the rooted and inc
                // our DR (diminishing returns) tracker
                if(!mRooted) {
                    mRooted = true;
                    mRootedInitialLife = mCreepLifeBar.getCurrentLife();
                    mRootedDr++;

                    // stop animations.. your rooted bitch!
                    mCreepBody.stopAnimation();

                }
            }
        }

        // sileneced handling, if the affect has a stun modifier (the current requested
        // duration for the stun) then do the handling for it here
        if(mSilencedDr != 6 && creepAffect.mCurrentSilenceModifier > 0f) {
            final float durationAfterDr = creepAffect.mCurrentSilenceModifier * DR_LOOKUP[mSilencedDr];

            // the way stuns work, is if a 1sec stun is applied, then another
            // is applied of 6 seconds, the bigger stun takes affect, but
            // the current spent duration does not
            if(durationAfterDr > mSilencedDuration) {

                // the dr handling, each time a creep is sileneced they become
                // more resilient to the affect till they gain temporary immunity
                // this is to prevent some ultra lame perma-stun tower build
                mSilencedDuration = durationAfterDr;

                // if we have not yet set a sileneced flag, create the sileneced and inc
                // our DR (diminishing returns) tracker
                if(!mSilenced) {
                    mSilenced = true;
                    mSilencedDr++;

                    // stop animations.. your sileneced bitch!
                    mCreepBody.stopAnimation();

                }
            }
        }
    }

    @Override
    public void applyAffect(final ISpellCaster pSpellCaster, final ISpellAffects pSpellAffect) {
        final ISpellAffect exists = getAffect(pSpellAffect);

        if(null != exists && exists.isRefreshable()) {
            exists.refresh(pSpellAffect);

        } else {

            // when a affect is activated, it has a chance to be handled immediately
            // this is for affects that do something upon application then deactivate
            final SpellAffect affect = pSpellAffect.getAffect();

            applyAffect(pSpellCaster, affect);
        }
    }

    @Override
    public void applyAffect(final ISpellCaster pSpellCaster, final SpellAffect pSpellAffect) {

        // when a affect is activated, it has a chance to be handled immediately
        // this is for affects that do something upon application then deactivate
        final SpellAffect affect = pSpellAffect;
        affect.setCaster(pSpellCaster);
        affect.activate(this);

        // handle the affect, apply it's modifications etc
        handleAffect(affect);

        // only bother applying it if its active at this point
        if(affect.isActive()) {
            mActiveCreepAffects.add(affect);

        } else {

            // call its finished method
            affect.finished();
        }
    }

    @Override
    public boolean hasAffect(final ISpellAffects pSpellAffect) {
        return null == getAffect(pSpellAffect);
    }

    @Override
    public ISpellAffect getAffect(final ISpellAffects pSpellAffect) {
        for(final Iterator<SpellAffect> iterator = mActiveCreepAffects.iterator(); iterator.hasNext();) {
            final ISpellAffect creepAffect = iterator.next();

            if(creepAffect.isActive() && creepAffect.getStackId().equals(pSpellAffect.getStackId())) {
                return creepAffect;
            }
        }

        return null;
    }

    @Override
    public boolean isEffectable() {
        return mAlive;
    }

    @Override
    public float getEffectX() {
        return 32f;
    }

    @Override
    public float getEffectY() {
        return 32f;
    }

    @Override
    public void onSpellCast(Spell pSpell, ISpellTargetable pTarget) {

    }

    @Override
    public void onSpellTravel(Spell pSpell, ISpellTargetable pTarget) {

    }

    @Override
    public void onSpellImpact(Spell pSpell, ISpellTargetable pTarget) {

    }

    @Override
    public void onSpellFinished(Spell pSpell, ISpellTargetable pTarget) {

    }
}