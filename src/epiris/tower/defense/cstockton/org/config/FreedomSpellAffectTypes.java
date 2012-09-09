package epiris.tower.defense.cstockton.org.config;

import android.util.FloatMath;
import epiris.tower.defense.cstockton.org.spell.affect.FreedomSpellAffect;
import epiris.tower.defense.cstockton.org.spell.affect.ISpellAffects;
import epiris.tower.defense.cstockton.org.spell.affect.SpellAffect;

public enum FreedomSpellAffectTypes implements ISpellAffects {

    FREE_ALL_6S(6f, true, true, true, true),

    ;

    public final float mDuration;
    public final boolean mSilenceFreedom;
    public final boolean mRootFreedom;
    public final boolean mStunFreedom;
    public final boolean mMovementFreedom;

    FreedomSpellAffectTypes(final float pDuration, final boolean pSilenceFreedom, final boolean pRootFreedom, final boolean pStunFreedom, final boolean pMovementFreedom) {
        mDuration = pDuration;

        mSilenceFreedom = pSilenceFreedom;
        mRootFreedom = pRootFreedom;
        mStunFreedom = pStunFreedom;
        mMovementFreedom = pMovementFreedom;
    }

    @Override
    public SpellAffect getAffect() {
        final FreedomSpellAffect affect = FreedomSpellAffect.obtain();

        affect.setType(this);
        affect.setStackId(this.name());
        affect.setStackCount(1);
        affect.setTickInterval(1f);
        affect.setTickCount((int) FloatMath.ceil(mDuration / 1f));

        return affect;
    }

    @Override
    public String getStackId() {
        return this.name();
    }
}