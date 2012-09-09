package epiris.tower.defense.cstockton.org.config;

import epiris.tower.defense.cstockton.org.spell.affect.ISpellAffects;
import epiris.tower.defense.cstockton.org.spell.affect.MovementSpellAffect;
import epiris.tower.defense.cstockton.org.spell.affect.SpellAffect;

import epiris.tower.defense.cstockton.org.util.ease.EaseLinear;
import epiris.tower.defense.cstockton.org.util.ease.IEaseFunction;

import android.util.FloatMath;

public enum MovementSpellAffectTypes implements ISpellAffects {

    ACCUMULATING_BOOST_1("ACCUMULATING_BOOST", .10f, 3f, .25f, EaseLinear.getInstance(), .25f, EaseLinear.getInstance()),
    ACCUMULATING_BOOST_2("ACCUMULATING_BOOST", .15f, 4f, .25f, EaseLinear.getInstance(), .25f, EaseLinear.getInstance()),
    ACCUMULATING_BOOST_3("ACCUMULATING_BOOST", .20f, 5f, .25f, EaseLinear.getInstance(), .25f, EaseLinear.getInstance()),
    ACCUMULATING_BOOST_4("ACCUMULATING_BOOST", .25f, 6f, .25f, EaseLinear.getInstance(), .25f, EaseLinear.getInstance()),
    ACCUMULATING_BOOST_5("ACCUMULATING_BOOST", .30f, 7f, .25f, EaseLinear.getInstance(), .25f, EaseLinear.getInstance()),

    DIMINISHING_SNARE_1("DIMINISHING_SNARE", .10f, 3f, .25f, EaseLinear.getInstance(), .25f, EaseLinear.getInstance()),
    DIMINISHING_SNARE_2("DIMINISHING_SNARE", .15f, 4f, .25f, EaseLinear.getInstance(), .25f, EaseLinear.getInstance()),
    DIMINISHING_SNARE_3("DIMINISHING_SNARE", .20f, 5f, .25f, EaseLinear.getInstance(), .25f, EaseLinear.getInstance()),
    DIMINISHING_SNARE_4("DIMINISHING_SNARE", .25f, 6f, .25f, EaseLinear.getInstance(), .25f, EaseLinear.getInstance()),
    DIMINISHING_SNARE_5("DIMINISHING_SNARE", .30f, 7f, .25f, EaseLinear.getInstance(), .25f, EaseLinear.getInstance()),

    SLOW_15P_2S_25F("SLOW_15P", -.55f, 2f, .25f, EaseLinear.getInstance(), .25f, EaseLinear.getInstance()),
    SLOW_15P_4S_25F("SLOW_15P", -.15f, 4f, .25f, EaseLinear.getInstance(), .25f, EaseLinear.getInstance()),
    SLOW_15P_6S_25F("SLOW_15P", -.15f, 6f, .25f, EaseLinear.getInstance(), .25f, EaseLinear.getInstance()),
    SLOW_20P_8S_25F("SLOW_15P", -.20f, 8f, .25f, EaseLinear.getInstance(), .25f, EaseLinear.getInstance()),

    SLOW_30P_12S_25F("SLOW_30P", -.15f, 4f, .25f, EaseLinear.getInstance(), .25f, EaseLinear.getInstance()),
    SLOW_30P_16S_25F("SLOW_30P", -.15f, 6f, .25f, EaseLinear.getInstance(), .25f, EaseLinear.getInstance()),
    SLOW_30P_24S_25F("SLOW_30P", -.15f, 8f, .25f, EaseLinear.getInstance(), .25f, EaseLinear.getInstance()),

    MEGA_SLOW("MEGA_SLOW", -.8f, 8f, 0f, EaseLinear.getInstance(), 2f, EaseLinear.getInstance()),

    MAGE_SLOW("MAGE_SLOW", -.25f, 8f, 2f, EaseLinear.getInstance(), 2f, EaseLinear.getInstance()),
    POISON_SLOW("POISON_SLOW", -.25f, 8f, 2f, EaseLinear.getInstance(), 2f, EaseLinear.getInstance()),

    ;

    public final String mStackId;

    public final float mSpeedModifier;
    public final float mDuration;

    public final float mEaseInDuration;
    public final IEaseFunction mEaseInFunction;

    public final float mEaseOutDuration;
    public final IEaseFunction mEaseOutFunction;

    MovementSpellAffectTypes(final String pStackId, final float pSpeedModifier, final float pDuration, final float pEaseInDuration, final IEaseFunction pEaseInFunction, final float pEaseOutDuration, final IEaseFunction pEaseOutFunction) {
        mStackId = pStackId;
        mSpeedModifier = pSpeedModifier;
        mDuration = pDuration;

        mEaseInDuration = pEaseInDuration;
        mEaseInFunction = pEaseInFunction;
        mEaseOutDuration = pEaseOutDuration;
        mEaseOutFunction = pEaseOutFunction;
    }

    @Override
    public SpellAffect getAffect() {
        final MovementSpellAffect affect = MovementSpellAffect.obtain();

        affect.setType(this);
        affect.setStackId(mStackId);
        affect.setStackCount(1);
        affect.setTickInterval(.15f);
        affect.setTickCount((int) FloatMath.ceil(mDuration / .15f));

        return affect;
    }

    @Override
    public String getStackId() {
        return mStackId;
    }
}