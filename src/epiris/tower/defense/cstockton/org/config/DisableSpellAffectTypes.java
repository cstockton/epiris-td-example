package epiris.tower.defense.cstockton.org.config;

import epiris.tower.defense.cstockton.org.spell.affect.DisableSpellAffect;
import epiris.tower.defense.cstockton.org.spell.affect.ISpellAffects;
import epiris.tower.defense.cstockton.org.spell.affect.SpellAffect;

public enum DisableSpellAffectTypes implements ISpellAffects {

    SILENCE_3S(3f, 0f, 0f),
    SILENCE_4S(4f, 0f, 0f),
    SILENCE_6S(6f, 0f, 0f),
    SILENCE_8S(8f, 0f, 0f),
    SILENCE_12S(12f, 0f, 0f),

    STUN_1S(0f, 1f, 0f),
    STUN_2S(0f, 2f, 0f),
    STUN_4S(0f, 4f, 0f),
    STUN_6S(0f, 6f, 0f),
    STUN_8S(0f, 8f, 0f),
    STUN_12S(0f, 12f, 0f),

    ROOT_4S(0f, 0f, 4f),
    ROOT_6S(0f, 0f, 6f),
    ROOT_8S(0f, 0f, 8f),
    ROOT_12S(0f, 0f, 12f),
    ROOT_16S(0f, 0f, 16f),
    ROOT_24S(0f, 0f, 24f),

    ;

    public final float mSilenceDuration;
    public final float mStunDuration;
    public final float mRootDuration;

    DisableSpellAffectTypes(final float pSilenceDuration, final float pStunDuration, final float pRootDuration) {
        mSilenceDuration = pSilenceDuration;
        mStunDuration = pStunDuration;
        mRootDuration = pRootDuration;
    }

    @Override
    public SpellAffect getAffect() {
        final DisableSpellAffect affect = DisableSpellAffect.obtain();

        affect.setType(this);
        affect.setStackId(this.name());
        affect.setStackCount(1);
        affect.setTickCount(0);

        return affect;
    }

    @Override
    public String getStackId() {
        return this.name();
    }
}