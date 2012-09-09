package epiris.tower.defense.cstockton.org.creep;

import epiris.tower.defense.cstockton.org.config.CreepSpellTypes;
import epiris.tower.defense.cstockton.org.config.CreepSprites;
import epiris.tower.defense.cstockton.org.damage.DamageResists;

public class CreepConfiguration {

    public final float mLifeBonus;
    public final float mSpeed;

    public final CreepSprites mSprite;
    public final float mScale;
    public final float mSpriteRed;
    public final float mSpriteGreen;
    public final float mSpriteBlue;
    public final float mSpriteAlpha;

    public final DamageResists mDamageResists;

    public final CreepSpellTypes[] mCreepSpellTypes;

    public CreepConfiguration(
            final float pLifeBonus,
            final float pSpeed,

            final CreepSprites pSprite,
            final float pScale,
            final float pSpriteRed,
            final float pSpriteGreen,
            final float pSpriteBlue,
            final float pSpriteAlpha,

            final DamageResists pDamageResists,
            final CreepSpellTypes... pCreepSpellTypes) {
        mLifeBonus = pLifeBonus;
        mSpeed = pSpeed;

        mSprite = pSprite;
        mScale = pScale;
        mSpriteRed = pSpriteRed;
        mSpriteGreen = pSpriteGreen;
        mSpriteBlue = pSpriteBlue;
        mSpriteAlpha = pSpriteAlpha;

        mDamageResists = pDamageResists;
        mCreepSpellTypes = pCreepSpellTypes;

    }

    public CreepConfiguration(
            final float pLifeBonus,
            final float pSpeed,
            final CreepSprites pSprite,
            final float pScale,
            final DamageResists pDamageResists) {
        this(pLifeBonus, pSpeed, pSprite, pScale, 1f, 1f, 1f, 1f, pDamageResists);
    }
}