package epiris.tower.defense.cstockton.org.config;

import epiris.tower.defense.cstockton.org.damage.Damage;
import epiris.tower.defense.cstockton.org.damage.Damages;
import epiris.tower.defense.cstockton.org.spell.affect.AoeSpellAffect;
import epiris.tower.defense.cstockton.org.spell.affect.ISpellAffects;
import epiris.tower.defense.cstockton.org.spell.affect.SpellAffect;

public enum AoeSpellAffectTypes implements ISpellAffects {
    MAGE_FIREBALL_EXPLOSION(125, 256, new Damages(new Damage(DamageTypes.MAGICAL, 60))),
    WIZARD_FIREBALL_EXPLOSION(150, 256, new Damages(new Damage(DamageTypes.MAGICAL, 120))),
    MASTER_WIZARD_FIREBALL_EXPLOSION(175, 256, new Damages(new Damage(DamageTypes.MAGICAL, 250))),
    ARCH_MAGE_WIZARD_FIREBALL_EXPLOSION(225, 256, new Damages(new Damage(DamageTypes.MAGICAL, 400))),

    ;

    private final int mRange;
    private final int mMaxTargets;
    private final Damages mDamages;

    AoeSpellAffectTypes(final int pRange, final int pMaxTargets, final Damages pDamages) {
        mRange = pRange;
        mMaxTargets = pMaxTargets;
        mDamages = pDamages;
    }

    @Override
    public SpellAffect getAffect() {
        final AoeSpellAffect aoe = AoeSpellAffect.obtain();

        aoe.setType(this);
        aoe.setStackId(this.name());
        aoe.mRange = mRange;
        aoe.mMaxTargets = mMaxTargets;
        aoe.mDamages = mDamages;

        return aoe;
    }

    @Override
    public String getStackId() {
        return this.name();
    }
}