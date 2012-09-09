package epiris.tower.defense.cstockton.org.spell;

import epiris.tower.defense.cstockton.org.config.SpellTypes;
import epiris.tower.defense.cstockton.org.damage.Damages;
import epiris.tower.defense.cstockton.org.effect.Effect;
import epiris.tower.defense.cstockton.org.effect.IEffectable;
import epiris.tower.defense.cstockton.org.effect.IEffects;
import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.spell.ISpellTargetable;
import epiris.tower.defense.cstockton.org.spell.affect.ISpellAffectable;
import epiris.tower.defense.cstockton.org.spell.affect.ISpellAffects;
import epiris.tower.defense.cstockton.org.util.Util;

public class Spell implements ISpells {

    public static Spell fetch(final SpellTypes pSpell) {
        final Spell spell = Registry.sSpellManager.obtain();

        spell.mQueueTime = pSpell.getQueueTime();
        spell.mCastTime = pSpell.getCastTime();
        spell.mVelocity = pSpell.getVelocity();

        spell.mCastDamages = pSpell.mCastDamages;
        spell.mFlightDamages = pSpell.mFlightDamages;
        spell.mImpactDamages = pSpell.mImpactDamages;

        spell.setCastEffect(pSpell.getCastEffect());
        spell.setCastSpellAffects(pSpell.getCastSpellAffects());

        spell.setFlightEffect(pSpell.getFlightEffect());
        spell.setFlightSpellAffects(pSpell.getFlightSpellAffects());

        spell.setImpactEffect(pSpell.getImpactEffect());
        spell.setImpactSpellAffects(pSpell.getImpactSpellAffects());

        return spell;
    }

    public static void cast(final ISpellCaster pCaster, final ISpellTargetable pTarget, final SpellTypes pSpell) {
        fetch(pSpell).cast(pCaster, pTarget);
    }

    public static void cast(final ISpellCaster pCaster, final ISpellTargetable pTarget, final SpellTypes pSpell, final ISpellListener... pSpellListeners) {
        fetch(pSpell).cast(pCaster, pTarget, pSpellListeners);
    }

    public static void cast(final ISpellCaster pCaster, final ISpellTargetable pTarget, final SpellTypes pSpell, final float pQueueTime) {
        fetch(pSpell).setQueueTime(pQueueTime).cast(pCaster, pTarget);
    }

    public static void cast(final ISpellCaster pCaster, final ISpellTargetable pTarget, final SpellTypes pSpell, final float pQueueTime, final ISpellListener... pSpellListeners) {
        fetch(pSpell).setQueueTime(pQueueTime).cast(pCaster, pTarget, pSpellListeners);
    }

    public enum SpellStates {
        NONE,
        QUEUED,
        CASTING,
        TRAVELING,
        IMPACTING,
        FINISHED
    }

    protected SpellStates mState = SpellStates.NONE;

    protected boolean mUpdating = false;
    protected boolean mPersistent = false;

    protected long mAffectableId = 0;

    protected float mElapsedTime = 0f;

    public float mVelocity = 0f;
    public float mQueueTime = 0f;
    public float mCastTime = 0f;
    protected float mImpactTime = 0f;

    protected float mLerpTime = 0f;

    public ISpellCaster mCaster;
    public ISpellTargetable mTarget;
    public ISpellListener[] mSpellListeners;

    public Effect mCastEffect;
    public ISpellAffects[] mCastSpellAffects;

    public Effect mFlightEffect;
    public ISpellAffects[] mFlightSpellAffects;

    public Effect mImpactEffect;
    public ISpellAffects[] mImpactSpellAffects;

    public Damages mCastDamages;
    public Damages mFlightDamages;
    public Damages mImpactDamages;

    public void deactivate() {
        //Debug.i("Spell :: deactivate :: mPersistent=" + mPersistent);

        mState = SpellStates.NONE;
        mAffectableId = 0;

        mUpdating = false;

        mVelocity = 0f;
        mCastTime = 0f;
        mQueueTime = 0f;
        mImpactTime = 0f;

        mLerpTime = 0f;

        mElapsedTime = 0f;

        if(null != mCastEffect) {
            mCastEffect.reset();
            if(mCastEffect.isActive()) {
                mCastEffect.deactivate();
            }
        }
        if(null != mFlightEffect) {
            mFlightEffect.reset();
            if(mFlightEffect.isActive()) {
                mFlightEffect.deactivate();
            }
        }
        if(null != mImpactEffect) {
            mImpactEffect.reset();
            if(mImpactEffect.isActive()) {
                mImpactEffect.deactivate();
            }
        }

        if(false == mPersistent) {
            Registry.sSpellManager.recycle(this);
        }
    }

    public void reset() {
        //Debug.i("Spell :: reset");

        mState = SpellStates.NONE;
        mAffectableId = 0;

        mUpdating = false;
        mPersistent = false;

        mVelocity = 0f;
        mCastTime = 0f;
        mQueueTime = 0f;
        mImpactTime = 0f;

        mLerpTime = 0f;

        mElapsedTime = 0f;

        mCaster = null;
        mTarget = null;
        mSpellListeners = null;

        mCastSpellAffects = null;
        mFlightSpellAffects = null;
        mImpactSpellAffects = null;

        mCastDamages = null;
        mFlightDamages = null;
        mImpactDamages = null;

        if(null != mCastEffect) {
            if(mCastEffect.isActive()) {
                mCastEffect.deactivate();
            }
            mCastEffect.recycle();
            mCastEffect = null;
        }
        if(null != mFlightEffect) {
            if(mFlightEffect.isActive()) {
                mFlightEffect.deactivate();
            }
            mFlightEffect.recycle();
            mFlightEffect = null;
        }
        if(null != mImpactEffect) {
            if(mImpactEffect.isActive()) {
                mImpactEffect.deactivate();
            }
            mImpactEffect.recycle();
            mImpactEffect = null;
        }
    }

    public void initializeQueue(final float pSecondsElapsed) {
        onQueueUpdate(pSecondsElapsed);
    }

    public void onQueueUpdate(final float pSecondsElapsed) {
        //Debug.i("mElapsedTime=" + mElapsedTime + " mQueueTime=" + mQueueTime);
        if(mElapsedTime > mQueueTime) {
            mState = SpellStates.CASTING;

            initializeCasting(mElapsedTime - mQueueTime);
        }
    }

    public void initializeCasting(final float pSecondsElapsed) {
        mElapsedTime = pSecondsElapsed;

        if(null != mCastEffect ) {

            // set our target
            final float targetX = mTarget.getCenterX();
            final float targetY = mTarget.getCenterY();

            // set our target
            final float casterX = mCaster.getCenterX();
            final float casterY = mCaster.getCenterY();

            mCastEffect.setRotation((((float)Math.toDegrees(Math.atan2(casterY - targetY, casterX - targetX))) + 180) % 360);
            mCastEffect.setTargetEntity((IEffectable) mCaster);
            mCastEffect.activate();

        }

        if(null != mCastDamages) {
            ((ISpellAffectable) mTarget).damage(mCastDamages);
        }

        if(null != mCastSpellAffects) {
            for(final ISpellAffects sa : mCastSpellAffects) {
                if(mTarget instanceof ISpellAffectable) {
                    ((ISpellAffectable)mTarget).applyAffect(mCaster, sa);
                }
            }
        }

        for(final ISpellListener isl : mSpellListeners) {
            isl.onSpellCast(this, mTarget);
        }

        onCastingUpdate(pSecondsElapsed);
    }

    public void onCastingUpdate(final float pSecondsElapsed) {
        if(mElapsedTime > mCastTime) {
            if(null != mCastEffect) {
                mCastEffect.deactivate();
            }

            mState = SpellStates.TRAVELING;

            initializeTraveling(mElapsedTime - mCastTime);
        }
    }

    public void initializeTraveling(final float pSecondsElapsed) {
        mElapsedTime = pSecondsElapsed;

        if(null != mFlightEffect) {

            // set the position
            mFlightEffect.setPosition(mCaster.getCenterX(), mCaster.getCenterY());

            // attach to the scene now
            mFlightEffect.activate();

            // do effect
            mFlightEffect.doEffect(true);

            // measure the "distance" to gain the speed
            final float diffX = (mTarget.getCenterX() - mCaster.getCenterX());
            final float diffY = (mTarget.getCenterY() - mCaster.getCenterY());

            // time which the linear interpolation should occur
            mLerpTime = (float) (Math.sqrt(diffX * diffX + diffY * diffY) / mVelocity);

        }

        if(null != mFlightDamages) {
            ((ISpellAffectable) mTarget).damage(mFlightDamages);
        }

        if(null != mFlightSpellAffects) {
            for(final ISpellAffects sa : mFlightSpellAffects) {
                if(mTarget instanceof ISpellAffectable) {
                    ((ISpellAffectable)mTarget).applyAffect(mCaster, sa);
                }
            }
        }

        for(final ISpellListener isl : mSpellListeners) {
            isl.onSpellTravel(this, mTarget);
        }

        onTravelingUpdate(pSecondsElapsed);
    }

    public void onTravelingUpdate(final float pSecondsElapsed) {

        // set our target
        final float targetX = mTarget.getCenterX();
        final float targetY = mTarget.getCenterY();

        // set our target
        final float casterX = mCaster.getCenterX();
        final float casterY = mCaster.getCenterY();

        // get the new position
        final float setPosX = Util.lerp(casterX, targetX, mLerpTime, mElapsedTime);
        final float setPosY = Util.lerp(casterY, targetY, mLerpTime, mElapsedTime);

        if(null != mFlightEffect) {

            // set the position
            mFlightEffect.setPosition(setPosX - (mFlightEffect.getWidth() / 2), setPosY - (mFlightEffect.getHeight() / 2));

            // set the rotation
            mFlightEffect.setRotation((((float)Math.toDegrees(Math.atan2(casterY - targetY, casterX - targetX))) + 180) % 360);

        }

        // if the target and current position are identical move on to the next path
        if(setPosX == targetX && setPosY == targetY) {
            if(null != mFlightEffect) {
                mFlightEffect.deactivate();
            }

            mState = SpellStates.IMPACTING;

            initializeImpact(0f);
        }
    }

    public void initializeImpact(final float pSecondsElapsed) {
        mElapsedTime = pSecondsElapsed;

        if(null != mImpactEffect) {

            // set our target
            final float targetX = mTarget.getCenterX();
            final float targetY = mTarget.getCenterY();

            // set our target
            final float casterX = mCaster.getCenterX();
            final float casterY = mCaster.getCenterY();

            mImpactEffect.setRotation((((float)Math.toDegrees(Math.atan2(casterY - targetY, casterX - targetX))) + 180) % 360);
            mImpactEffect.setTargetEntity((IEffectable) mTarget);
            mImpactEffect.activate();
        }

        if(null != mImpactDamages) {
            ((ISpellAffectable) mTarget).damage(mImpactDamages);
        }

        if(null != mImpactSpellAffects) {
            for(final ISpellAffects sa : mImpactSpellAffects) {
                if(mTarget instanceof ISpellAffectable) {
                    ((ISpellAffectable)mTarget).applyAffect(mCaster, sa);
                }
            }
        }

        for(final ISpellListener isl : mSpellListeners) {
            isl.onSpellImpact(this, mTarget);
        }

        onImpactUpdate(pSecondsElapsed);
    }

    public void onImpactUpdate(final float pSecondsElapsed) {
        /*
        if(mElapsedTime > mImpactTime) {
            if(null != mImpactEffect) {
                mImpactEffect.deactivate();
            }

            mState = SpellStates.FINISHED;
        }
        */

        // do we use the impact time override? or the default effect
        if(null != mImpactEffect) {
            if(mImpactTime > 0f && mElapsedTime > mImpactTime) {
                mImpactEffect.deactivate();
            } else if(mImpactTime == 0f && !mImpactEffect.isAnimationRunning()) {
                mState = SpellStates.FINISHED;
            }
        } else {
            mState = SpellStates.FINISHED;

        }
    }

    @Override
    public void onUpdate(final float pSecondsElapsed) {
        //Debug.i("Spell :: onUpdate mState=" + mState);

        // set the elapsed time
        mElapsedTime += pSecondsElapsed;

        // set state to finished
        if(!mTarget.isSpellTargetableId(mAffectableId)) {
            mState = SpellStates.FINISHED;
        }

        // run the relevant updater
        switch(mState) {

            // none/casting states call casting updaters or move state to traveling/impacting
            case NONE: {

                // we decide our initial state at cast time
                if(mQueueTime > 0f) {
                    mState = SpellStates.QUEUED;
                    initializeQueue(pSecondsElapsed);

                // we decide our initial state at cast time
                } else if(mCastTime > 0f) {
                    mState = SpellStates.CASTING;
                    initializeCasting(pSecondsElapsed);

                } else if(mVelocity > 0f) {
                    mState = SpellStates.TRAVELING;
                    initializeTraveling(pSecondsElapsed);

                } else if(mImpactTime > 0f) {
                    mState = SpellStates.IMPACTING;
                    initializeImpact(pSecondsElapsed);

                // finish here no point in waiting a frame
                } else {
                    mState = SpellStates.FINISHED;

                    for(final ISpellListener isl : mSpellListeners) {
                        isl.onSpellFinished(this, mTarget);
                    }

                    deactivate();

                }
            } break;

            case QUEUED: {
                onQueueUpdate(pSecondsElapsed);
            } break;

            case CASTING: {
                onCastingUpdate(pSecondsElapsed);
            } break;

            case TRAVELING: {
                onTravelingUpdate(pSecondsElapsed);
            } break;

            case IMPACTING: {
                onImpactUpdate(pSecondsElapsed);
            } break;

            case FINISHED: {
                for(final ISpellListener isl : mSpellListeners) {
                    isl.onSpellFinished(this, mTarget);
                }
                deactivate();
            } break;

        }
    }

    @Override
    public boolean isUpdating() {
        return mUpdating;
    }

    public Spell cast(final ISpellCaster pCaster, final ISpellTargetable pTarget) {
        cast(pCaster, pTarget, (ISpellListener) pCaster);

        return this;
    }

    public Spell cast(final ISpellCaster pCaster, final ISpellTargetable pTarget, final ISpellListener... pSpellListeners) {
        mAffectableId = pTarget.getSpellTargetableId(this);
        mCaster = pCaster;
        mSpellListeners = pSpellListeners;
        mTarget = pTarget;

        mUpdating = true;

        return this;
    }

    public Spell setPersistent(final boolean pPersistent) {
        mPersistent = pPersistent;
        return this;
    }

    public Spell setQueueTime(final float pQueueTime) {
        mQueueTime = pQueueTime;
        return this;
    }

    public Spell setCastTime(final float pCastTime) {
        mCastTime = pCastTime;
        return this;
    }

    public Spell setImpactTime(final float pImpactTime) {
        mImpactTime = pImpactTime;
        return this;
    }

    public Spell setVelocity(final float pVelocity) {
        mVelocity = pVelocity;
        return this;
    }

    public Spell setCastEffect(final Effect pEffect) {
        mCastEffect = pEffect;
        return this;
    }

    public Spell setCastEffect(final IEffects pEffect) {
        if(null != pEffect) {
            mCastEffect = pEffect.getEffect();
            mCastEffect.setLoop(false);
        }
        return this;
    }

    public Spell setCastSpellAffects(final ISpellAffects[] pAffects) {
        mCastSpellAffects = pAffects;
        return this;
    }

    public Spell setFlightEffect(final Effect pEffect) {
        mFlightEffect = pEffect;
        return this;
    }

    public Spell setFlightEffect(final IEffects pEffect) {
        if(null != pEffect) {
            mFlightEffect = pEffect.getEffect();
            mFlightEffect.setLoop(true);
        }
        return this;
    }

    public Spell setFlightSpellAffects(final ISpellAffects[] pAffects) {
        mFlightSpellAffects = pAffects;
        return this;
    }

    public Spell setImpactEffect(final Effect pEffect) {
        mImpactEffect = pEffect;
        return this;
    }

    public Spell setImpactEffect(final IEffects pEffect) {
        if(null != pEffect) {
            mImpactEffect = pEffect.getEffect();
            mImpactEffect.setLoop(false);
        }
        return this;
    }

    public Spell setImpactSpellAffects(final ISpellAffects[] pAffects) {
        mImpactSpellAffects = pAffects;
        return this;
    }
}