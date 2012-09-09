package epiris.tower.defense.cstockton.org.spell.affect;

import org.anddev.andengine.util.pool.GenericPool;

import epiris.tower.defense.cstockton.org.config.MovementSpellAffectTypes;

public class MovementSpellAffect extends SpellAffect {
    static final private GenericPool<MovementSpellAffect> mSpeedSpellAffect = new GenericPool<MovementSpellAffect>() {

        @Override
        protected MovementSpellAffect onAllocatePoolItem() {
            return new MovementSpellAffect();
        }
    };

    static final public MovementSpellAffect obtain() {
        return mSpeedSpellAffect.obtainPoolItem();
    }

    static final public void recycle(final MovementSpellAffect pSpeedSpellAffect) {
        mSpeedSpellAffect.recyclePoolItem(pSpeedSpellAffect);
        pSpeedSpellAffect.reset();
    }

    @Override
    public void finished() {
        MovementSpellAffect.recycle(this);
    }

    @Override
    public void onApplication() {
        onManagedTick();
    }

    @Override
    public void onRemoval() {
        mCurrentMovementSpeedModifier = 0f;
    }

    @Override
    public void onRefresh(final ISpellAffects pSpellAffectType) {
        if(((MovementSpellAffectTypes) mSpellAffectType).ordinal() < ((MovementSpellAffectTypes) pSpellAffectType).ordinal()) {
            setType(pSpellAffectType);
        }
    }

    @Override
    public void onManagedTick() {
        final MovementSpellAffectTypes s = (MovementSpellAffectTypes) mSpellAffectType;
        final float elapsed = (1 + mElapsedTickCount) * mTickInterval;
        final float realElapsed = (1 + mTotalTickCount) * mTickInterval;

        if(s.mEaseInDuration > 0 && realElapsed < s.mEaseInDuration) {
            mCurrentMovementSpeedModifier = s.mEaseInFunction.getPercentageDone(
                realElapsed,
                s.mEaseInDuration,
                0f,
                s.mSpeedModifier
            );

        } else if(s.mEaseOutDuration > 0 && (s.mDuration - s.mEaseOutDuration) < elapsed) {
            mCurrentMovementSpeedModifier = s.mEaseOutFunction.getPercentageDone(
                s.mDuration - elapsed,
                s.mEaseOutDuration,
                0f,
                s.mSpeedModifier
            );

        } else {
            mCurrentMovementSpeedModifier = s.mSpeedModifier;

        }
    }
}