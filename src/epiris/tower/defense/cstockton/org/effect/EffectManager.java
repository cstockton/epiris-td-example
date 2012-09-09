package epiris.tower.defense.cstockton.org.effect;

import epiris.tower.defense.cstockton.org.config.EffectSpriteSheets;
import epiris.tower.defense.cstockton.org.config.EffectTypes;
import epiris.tower.defense.cstockton.org.object.Registry;

public class EffectManager
{
    final private EffectPool mEffectPool[];

    public EffectManager()
    {
        mEffectPool = new EffectPool[EffectSpriteSheets.values().length];

        for(final EffectSpriteSheets effectSheet : EffectSpriteSheets.values()) {
            mEffectPool[effectSheet.ordinal()] = new EffectPool(Registry.sEffectsTextureRegions[effectSheet.ordinal()]);
        }
    }

    public Effect obtain(final EffectTypes pEffectType)
    {
        final Effect effect = mEffectPool[pEffectType.getEffectSpriteSheet().ordinal()].obtainPoolItem();
        effect.setEffectType(pEffectType);
        return effect;
    }

    public void recycle(final Effect pEffect)
    {
        mEffectPool[pEffect.getEffectType().getEffectSpriteSheet().ordinal()].recyclePoolItem(pEffect);
    }
}