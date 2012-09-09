package epiris.tower.defense.cstockton.org.spell.affect;

import org.anddev.andengine.util.pool.GenericPool;

import epiris.tower.defense.cstockton.org.config.DisableSpellAffectTypes;

public class DisableSpellAffect extends SpellAffect {
    static final private GenericPool<DisableSpellAffect> mSpeedSpellAffect = new GenericPool<DisableSpellAffect>() {

        @Override
        protected DisableSpellAffect onAllocatePoolItem() {
            return new DisableSpellAffect();
        }
    };

    static final public DisableSpellAffect obtain() {
        return mSpeedSpellAffect.obtainPoolItem();
    }

    static final public void recycle(final DisableSpellAffect pSpeedSpellAffect) {
        mSpeedSpellAffect.recyclePoolItem(pSpeedSpellAffect);
        pSpeedSpellAffect.reset();
    }

    @Override
    public void finished() {
        DisableSpellAffect.recycle(this);
    }

    @Override
    public void onApplication() {
        final DisableSpellAffectTypes s = (DisableSpellAffectTypes) mSpellAffectType;

        if(s.mSilenceDuration > 0f) {
            mCurrentSilenceModifier = s.mSilenceDuration;
        }
        if(s.mStunDuration > 0f) {
            mCurrentStunModifier = s.mStunDuration;
        }
        if(s.mRootDuration > 0f) {
            mCurrentRootModifier = s.mRootDuration;
        }
    }
}