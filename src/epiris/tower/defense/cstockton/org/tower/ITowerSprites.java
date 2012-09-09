package epiris.tower.defense.cstockton.org.tower;

import epiris.tower.defense.cstockton.org.effect.IEffects;

public interface ITowerSprites {

    public int getId();
    public int getWidth();
    public int getHeight();
    public int getSpriteWidth();
    public int getSpriteHeight();
    public int getSpriteCols();
    public int getSpriteRows();

    public int getInitialTileIndex();

    public int getRotationTileIndexStart();
    public int getRotationTileIndexEnd();
    public int getRotationTileIndexLength();
    public boolean getRotationTileMirror();

    public float getPlacementOffsetX();
    public float getPlacementOffsetY();

    public IEffects getOnIdleSpellEffect();
    public IEffects getOnAttackSpellEffect();
    public IEffects getOnAttackCompleteSpellEffect();

}