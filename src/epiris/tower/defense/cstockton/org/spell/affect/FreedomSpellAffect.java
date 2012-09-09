package epiris.tower.defense.cstockton.org.spell.affect;

import org.anddev.andengine.util.pool.GenericPool;

import epiris.tower.defense.cstockton.org.config.FreedomSpellAffectTypes;

public class FreedomSpellAffect extends SpellAffect {
    static final private GenericPool<FreedomSpellAffect> mFreedomSpellAffect = new GenericPool<FreedomSpellAffect>() {

        @Override
        protected FreedomSpellAffect onAllocatePoolItem() {
            return new FreedomSpellAffect();
        }
    };

    static final public FreedomSpellAffect obtain() {
        return mFreedomSpellAffect.obtainPoolItem();
    }

    static final public void recycle(final FreedomSpellAffect pFreedomSpellAffect) {
        mFreedomSpellAffect.recyclePoolItem(pFreedomSpellAffect);
        pFreedomSpellAffect.reset();
    }

    @Override
    public void finished() {
        FreedomSpellAffect.recycle(this);
    }

    @Override
    public void onApplication() {
        onManagedTick();
    }

    @Override
    public void onRemoval() {
        mCurrentSilenceFreedomModifier = false;
        mCurrentRootFreedomModifier = false;
        mCurrentStunFreedomModifier = false;
        mCurrentMovementFreedomModifier = false;
    }

    @Override
    public void onManagedTick() {
        final FreedomSpellAffectTypes s = (FreedomSpellAffectTypes) mSpellAffectType;

        mCurrentSilenceFreedomModifier = s.mSilenceFreedom;
        mCurrentRootFreedomModifier = s.mRootFreedom;
        mCurrentStunFreedomModifier = s.mStunFreedom;
        mCurrentMovementFreedomModifier = s.mMovementFreedom;
    }
}