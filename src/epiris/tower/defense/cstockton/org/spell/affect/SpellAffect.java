package epiris.tower.defense.cstockton.org.spell.affect;

import epiris.tower.defense.cstockton.org.spell.ISpellCaster;

abstract public class SpellAffect implements ISpellAffect {

    // the affected
    protected ISpellAffectable mAffected;

    // the affected
    protected ISpellCaster mCaster;

    // affect type
    protected ISpellAffects mSpellAffectType;

    // initial affect settings
    protected String mStackId = "";
    protected int mStackCount = 1;
    protected int mTickCount = 1;
    protected float mTickInterval = 1f;

    // current affect state
    protected float mTickAccumulator = 0f;
    protected int mCurrentStackCount = 0;
    protected int mElapsedTickCount = 0;
    protected int mTotalTickCount = 0;

    // affect is active?
    private boolean mActive = false;

    // is this affect refreshable
    private boolean mRefreshable = true;

    // states that must be constantly respected by affectables
    public boolean mCurrentSilenceFreedomModifier = false;
    public boolean mCurrentRootFreedomModifier = false;
    public boolean mCurrentStunFreedomModifier = false;
    public boolean mCurrentMovementFreedomModifier = false;

    public float mCurrentSilenceModifier = 0f;
    public float mCurrentStunModifier = 0f;
    public float mCurrentRootModifier = 0f;
    public float mCurrentMovementSpeedModifier = 0f;
    public float mCurrentResistPhysicalModifier = 0f;
    public float mCurrentResistMagicalModifier = 0f;
    public float mCurrentResistElementalModifier = 0f;

    @Override
    public void setAffected(final ISpellAffectable pAffected) {
        mAffected = pAffected;
    }

    @Override
    public ISpellAffectable getAffected() {
        return mAffected;
    }

    @Override
    public void setCaster(final ISpellCaster pCaster) {
        mCaster = pCaster;
    }

    @Override
    public ISpellCaster getCaster() {
        return mCaster;
    }

    @Override
    public void setActive(final boolean pActive) {
        mActive = pActive;
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void setRefreshable(final boolean pRefreshable) {
        mRefreshable = pRefreshable;
    }

    @Override
    public boolean isRefreshable() {
        return mRefreshable;
    }

    @Override
    public void setStackCount(final int pStackCount) {
        mStackCount = pStackCount;
    }

    @Override
    public int getStackCount() {
        return mStackCount;
    }

    @Override
    public void setStackId(final String pStackId) {
        mStackId = pStackId;
    }

    @Override
    public String getStackId() {
        return mStackId;
    }

    @Override
    public void setTickCount(final int pTickCount) {
        mTickCount = pTickCount;
    }

    @Override
    public int getTickCount() {
        return mTickCount;
    }

    @Override
    public void setTickInterval(final float pTickInterval) {
        mTickInterval = pTickInterval;
    }

    @Override
    public float getTickInterval() {
        return mTickInterval;
    }

    @Override
    public int getCurrentStackCount() {
        return mCurrentStackCount;
    }

    @Override
    public int getTotalTickCount() {
        return mTotalTickCount;
    }

    @Override
    public void activate(final ISpellAffectable pAffected) {

        // affect is now active
        mActive = true;

        // affect state tracking
        mTickAccumulator = 0f;
        mCurrentStackCount = 0;
        mElapsedTickCount = 0;
        mTotalTickCount = 0;

        // modifiers
        mCurrentSilenceFreedomModifier = false;
        mCurrentRootFreedomModifier = false;
        mCurrentStunFreedomModifier = false;
        mCurrentMovementFreedomModifier = false;

        mCurrentSilenceModifier = 0f;
        mCurrentStunModifier = 0f;
        mCurrentRootModifier = 0f;
        mCurrentMovementSpeedModifier = 0f;
        mCurrentResistPhysicalModifier = 0f;
        mCurrentResistMagicalModifier = 0f;
        mCurrentResistElementalModifier = 0f;

        // set the current stack count to 1
        mCurrentStackCount = 1;

        // set the affected target
        mAffected = pAffected;

        // call the on application event
        onApplication();

        if(mTickCount <= 0) {
            deactivate();
        }

    }

    @Override
    public void deactivate() {

        // call the on application event
        onRemoval();

        // set active = false
        mActive = false;

    }

    @Override
    public void reset() {

        // initial affect settings
        mStackId = "";
        mStackCount = 1;
        mTickCount = 1;
        mTickInterval = 1f;

    }

    @Override
    public void setType(final ISpellAffects pSpellAffectType) {
        mSpellAffectType = pSpellAffectType;
    }

    @Override
    public ISpellAffects getType() {
        return mSpellAffectType;
    }

    @Override
    public void refresh(final ISpellAffects pSpellAffectType) {
        mElapsedTickCount = 0;

        if(mCurrentStackCount < mStackCount || 0 == mStackCount) {
            mCurrentStackCount++;
        }

        // this is for debuffs that dont tick, just sit idle till the end.. without this
        // you would end up hitting the tick accumulator and the debuffs wouldnt stick
        if(mTickCount <= 1) {
            mTickAccumulator = 0f;
        }

        onRefresh(pSpellAffectType);
    }

    @Override
    public void onApplication() { }

    @Override
    public void onRefresh(final ISpellAffects pSpellAffectType) { }

    @Override
    public void onRemoval() { }

    @Override
    public void onManagedTick() {}

    @Override
    public void onTick(final float pSecondsElapsed) {
        mTickAccumulator += pSecondsElapsed;

        while(mTickAccumulator >= mTickInterval) {
            mTotalTickCount++;

            onManagedTick();
            if(++mElapsedTickCount >= mTickCount) {
                deactivate();
            }

            mTickAccumulator -= mTickInterval;
        }
    }
}