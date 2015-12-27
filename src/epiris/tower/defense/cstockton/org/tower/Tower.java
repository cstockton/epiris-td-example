package epiris.tower.defense.cstockton.org.tower;

import java.util.ArrayList;

import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.sprite.TiledSprite;

import epiris.tower.defense.cstockton.org.config.DamageTypes;
import epiris.tower.defense.cstockton.org.config.TowerTypes;
import epiris.tower.defense.cstockton.org.creep.Creep;
import epiris.tower.defense.cstockton.org.damage.Damage;
import epiris.tower.defense.cstockton.org.damage.Damages;
import epiris.tower.defense.cstockton.org.effect.IEffectable;
import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.spell.ISpellCaster;
import epiris.tower.defense.cstockton.org.spell.ISpellListener;
import epiris.tower.defense.cstockton.org.spell.ISpellTargetable;
import epiris.tower.defense.cstockton.org.spell.Spell;
import epiris.tower.defense.cstockton.org.util.Util;

import android.util.FloatMath;

public class Tower extends TiledSprite implements ITower, ISpellCaster, ISpellListener, IEffectable  {

    public final ArrayList<ISpellTargetable> mSpellTargetables = new ArrayList<ISpellTargetable>();
    public final TowerTypes mTowerType;
    public final TiledSprite mTowerBase;

    public int mTowerCol;
    public int mTowerRow;

    protected float mLastAttack = 1000f;

    public Damages mCastDamages;
    public Damages mFlightDamages;
    public Damages mImpactDamages;

    public int mRange;
    public float mSpeed;
    public float mAffect;

    public int mPurityBonusLevel = 0;
    public int mUltimateBonusLevel = 0;
    public int mRangeBonusLevel = 0;
    public int mDamageBonusLevel = 0;
    public int mSpeedBonusLevel = 0;
    public int mAffectBonusLevel = 0;

    @Override
    public void onSpellCast(final Spell pSpell, final ISpellTargetable pTarget) {
        ((Creep)pTarget).damage(mCastDamages);
    }

    @Override
    public void onSpellTravel(final Spell pSpell, final ISpellTargetable pTarget) {
        ((Creep)pTarget).damage(mFlightDamages);
    }

    @Override
    public void onSpellImpact(final Spell pSpell, final ISpellTargetable pTarget) {
        ((Creep)pTarget).damage(mImpactDamages);
    }

    @Override
    public void onSpellFinished(final Spell pSpell, final ISpellTargetable pTarget) { }

    @Override
    public IEntity getEntity() {
        return this;
    }

    @Override
    public float getCenterX() {
        return getX() + 32;
    }

    @Override
    public float getCenterY() {
        return getY() + 32;
    }

    @Override
    public float getEffectX() {
        return 32f;
    }

    @Override
    public float getEffectY() {
        return 32f;
    }

    public void onTowerInit() {
        if(null != mTowerType.getCastDamages()) {
            mCastDamages = (Damages) mTowerType.getCastDamages().clone();
        }
        if(null != mTowerType.getFlightDamages()) {
            mFlightDamages = (Damages) mTowerType.getFlightDamages().clone();
        }
        if(null != mTowerType.getImpactDamages()) {
            mImpactDamages = (Damages) mTowerType.getImpactDamages().clone();
        }

        onTowerBonusChange();
    }

    public void onTowerSell() {
    }

    public void onTowerPlace() {
    }

    public void onTowerDestroy() {
    }

    public void onTowerBonusChange() {
        final int damageBonusValue = getDamageBonusValue();
        final int ultimateBonusValue = getUltimateBonusValue();

        // add damage bonus to casting
        if(mCastDamages != null && mDamageBonusLevel > 0) {
            mCastDamages.mMultiplier = (float) (mUltimateBonusLevel > 0 ? ((double) damageBonusValue / 100) * ultimateBonusValue : ((double) damageBonusValue / 100));
        }

        // add damage bonus to flight
        if(mFlightDamages != null && mDamageBonusLevel > 0) {
            mFlightDamages.mMultiplier = (float) (mUltimateBonusLevel > 0 ? ((double) damageBonusValue / 100) * ultimateBonusValue : (damageBonusValue / 100));
        }

        // add bonuses to impact
        if(mImpactDamages != null) {

            // first add bonus damage to impact
            if(mDamageBonusLevel > 0) {
                mImpactDamages.mMultiplier = (float) (mUltimateBonusLevel > 0 ? ((double) damageBonusValue / 100) * ultimateBonusValue : ((double) damageBonusValue / 100));
            }

            // now add purity bonus
            if(mPurityBonusLevel > 0) {
                final Damage[] damages = new Damage[mImpactDamages.mDamages.length + 1];
                int i = 0;

                for(final Damage damage : mImpactDamages.mDamages) {
                    damages[i++] = damage;
                }
                damages[i] = new Damage(DamageTypes.PURE, ultimateBonusValue > 0 ? getPurityBonusValue() * ultimateBonusValue : getPurityBonusValue());

                mImpactDamages.mDamages = damages;
            }
        }

        // if we have ultimate we add range/speed/affect with ulti bonus
        if(mUltimateBonusLevel > 0) {
            if(mUltimateBonusLevel > 10) {
                mSpeed = (float) (mTowerType.getSpeed() * (1f - ((double)getSpeedBonusValue() / 100) - ((10 * 2) / 100)));
                mRange = mTowerType.getRange() + (getRangeBonusValue() * 10);

            } else {
                mSpeed = (float) (mTowerType.getSpeed() * (1f - ((double)getSpeedBonusValue() / 100) - ((double)(ultimateBonusValue * 2) / 100)));
                mRange = mTowerType.getRange() + (getRangeBonusValue() * ultimateBonusValue);
            }

            mAffect = 1f + (getAffectBonusValue() * ultimateBonusValue);

        // else we add it with no bonuses
        } else {
            mRange = mTowerType.getRange() + getRangeBonusValue();
            mSpeed = (float) (mTowerType.getSpeed() * (1f - ((double)getSpeedBonusValue() / 100)));
            mAffect = 1f + getAffectBonusValue();

        }

        /*
        Debug.i("onTowerBonusLevelChange mPurityBonusLevel=" + mPurityBonusLevel);
        Debug.i("onTowerBonusLevelChange mUltimateBonusLevel=" + mUltimateBonusLevel);
        Debug.i("onTowerBonusLevelChange mDamageBonusLevel=" + mDamageBonusLevel);
        Debug.i("onTowerBonusLevelChange mSpeedBonusLevel=" + mSpeedBonusLevel);
        Debug.i("onTowerBonusLevelChange mRangeBonusLevel=" + mRangeBonusLevel);
        Debug.i("onTowerBonusLevelChange mAffectBonusLevel=" + mAffectBonusLevel);

        Debug.i("onTowerBonusLevelChange mPurityBonusValue=" + getPurityBonusValue());
        Debug.i("onTowerBonusLevelChange mUltimateBonusValue=" + getUltimateBonusValue());
        Debug.i("onTowerBonusLevelChange mDamageBonusValue=" + getDamageBonusValue());
        Debug.i("onTowerBonusLevelChange mSpeedBonusValue=" + getSpeedBonusValue());
        Debug.i("onTowerBonusLevelChange mRangeBonusValue=" + getRangeBonusValue());
        Debug.i("onTowerBonusLevelChange mAffectBonusValue=" + getAffectBonusValue());

        Debug.i("onTowerBonusLevelChange mRange=" + mRange);
        Debug.i("onTowerBonusLevelChange mSpeed=" + mSpeed);
        Debug.i("onTowerBonusLevelChange mAffect=" + mAffect);
        */

    }

    public Tower(final TowerManager pTowerManager, final TowerTypes pTowerType) {
        super(-250, -250, pTowerManager.getTowerTiledTextureRegionClone(pTowerType.getTowerTopSprite()));

        setVisible(false);

        if(null != pTowerType.getTowerBaseSprite()) {
            mTowerBase = new TiledSprite(-250, -250, getTextureRegion().deepCopy());
            mTowerBase.setVisible(false);
        } else {
            mTowerBase = null;
        }

        mTowerType = pTowerType;

        onTowerInit();
    }

    /** Get tower properties */
    public TowerTypes getTowerType() {
        return mTowerType;
    }

    public int getTowerCost() {
        return mTowerType.getCost();
    }

    public int getTowerRefund() {
        return mTowerType.getRefund();
    }

    public float getTowerSpeed() {
        return mSpeed;
    }

    public int getTowerRange() {
        return mRange;
    }

    public float getTowerAffect() {
        return mAffect;
    }

    /** BONUSES */
    public int getDamageBonusValue() {
        return mDamageBonusLevel * 10;
    }

    public int getSpeedBonusValue() {
        return mSpeedBonusLevel * 3;
    }

    public int getRangeBonusValue() {
        return mRangeBonusLevel * 16;
    }

    public int getAffectBonusValue() {
        return mAffectBonusLevel * 10;
    }

    public int getPurityBonusValue() {
        return mPurityBonusLevel * 20;
    }

    public int getUltimateBonusValue() {
        return mUltimateBonusLevel;
    }

    public int getDamageBonusLevel() {
        return mDamageBonusLevel;
    }

    public int getSpeedBonusLevel() {
        return mSpeedBonusLevel;
    }

    public int getRangeBonusLevel() {
        return mRangeBonusLevel;
    }

    public int getAffectBonusLevel() {
        return mAffectBonusLevel;
    }

    public int getPurityBonusLevel() {
        return mPurityBonusLevel;
    }

    public int getUltimateBonusLevel() {
        return mUltimateBonusLevel;
    }

    public void increaseDamageBonusLevel() {
        mDamageBonusLevel++;
    }

    public void increaseSpeedBonusLevel() {
        mSpeedBonusLevel++;
    }

    public void increaseRangeBonusLevel() {
        mRangeBonusLevel++;
    }

    public void increaseAffectBonusLevel() {
        mAffectBonusLevel++;
    }

    public void increasePurityBonusLevel() {
        mPurityBonusLevel++;
    }

    public void increaseUltimateBonusLevel() {
        mUltimateBonusLevel++;
    }

    /** COL/ROWS */
    public int getTowerCol() {
        return mTowerCol;
    }

    public int getTowerRow() {
        return mTowerRow;
    }

    public boolean isTowerReady(final float pLastAttack) {
        mLastAttack += pLastAttack;

        return mLastAttack > getTowerSpeed();
    }

    public TowerTypes[] getTowerUpgradeTypes() {
        return mTowerType.getUpgrades();
    }

    public void attack() {
        if (!mSpellTargetables.isEmpty()) {
            mTowerType.getTowerAi().onAttack(this);
            mLastAttack = 0f;
        }
    }

    // @DEPRECATED
    public Tower setTowerTopRotation(final float pRotation) {
        return setTowerRotation(pRotation);
    }

    public Tower setTowerRotation(final Creep pTarget) {
        float degrees = Util.angleAxisToVectorDegrees(pTarget.getX() + 16, pTarget.getY() + 16, getX() + 32, getY() + 32);
        setTowerRotation(degrees);
        return this;
    }

    public Tower setTowerRotation(final float pRotation) {
        if(mTowerType.getTowerTopSprite().getRotationTileIndexStart() > -1) {
            final int steps = mTowerType.getTowerTopSprite().getRotationTileIndexLength();

            if(mTowerType.getTowerTopSprite().getRotationTileMirror()) {
                final float tileIndex = (float)Math.ceil(pRotation / (360 / (steps * 2)));

                if(tileIndex > steps) {
                    final int ti = (int) (steps - (tileIndex - steps));
                    setCurrentTileIndex(ti);
                    getTextureRegion().setFlippedHorizontal(false);
                } else {
                    setCurrentTileIndex((int) tileIndex);
                    getTextureRegion().setFlippedHorizontal(true);
                }
            } else {
                final int pos = (int) Math.floor(pRotation / (360 / steps));

                if(pos == 0) {
                    setCurrentTileIndex(mTowerType.getTowerTopSprite().getRotationTileIndexStart());
                } else {
                    setCurrentTileIndex(mTowerType.getTowerTopSprite().getRotationTileIndexStart() + (steps - pos));
                }
            }
        }

        return this;
    }

    public void place(final int pX, final int pY) {
        final int[] coords = Registry.sMap.getTilesFromCoords(pX, pY);
        place(pX, pY, coords[0], coords[1]);
    }

    public void place(final int pX, final int pY, final int pCol, final int pRow) {

        // assign the tower sprites
        final ITowerSprites spriteTowerTop = mTowerType.getTowerTopSprite();
        final ITowerSprites spriteTowerBase = mTowerType.getTowerBaseSprite();

        // grab the placement coords
        final float placeX = 32 + (pX - mTowerType.getTowerTileCenterX());
        final float placeY = 32 + (pY - mTowerType.getTowerTileCenterY());

        // add the tower top
        setVisible(true);
        setPosition(placeX, placeY);
        setZIndex((int) placeY);
        setCurrentTileIndex(spriteTowerTop.getInitialTileIndex());

        // set extra options
        setAlpha(mTowerType.getTowerTopSpriteAlpha());
        setColor(mTowerType.getTowerTopSpriteRed(), mTowerType.getTowerTopSpriteGreen(), mTowerType.getTowerTopSpriteBlue());
        setScale(mTowerType.getTowerTopSpriteScale());
        setScaleCenter(mTowerType.getTowerTileCenterX(), mTowerType.getTowerTileCenterY());

        // set the tile location
        mTowerCol = pCol;
        mTowerRow = pRow;

        // block the tile
        Registry.sMap.setTileBlocked(mTowerCol, mTowerRow);

        // attach
        if(null != mTowerBase) {

            // setup the tower base
            mTowerBase.setVisible(true);
            mTowerBase.setPosition(placeX, placeY);
            setZIndex((int) placeY - 1);
            mTowerBase.setCurrentTileIndex(spriteTowerBase.getInitialTileIndex());

            // set extra options
            mTowerBase.setAlpha(mTowerType.getTowerBaseSpriteAlpha());
            mTowerBase.setColor(mTowerType.getTowerBaseSpriteRed(), mTowerType.getTowerBaseSpriteGreen(), mTowerType.getTowerBaseSpriteBlue());
            mTowerBase.setScale(mTowerType.getTowerBaseSpriteScale());
            mTowerBase.setScaleCenter(mTowerType.getTowerTileCenterX(), mTowerType.getTowerTileCenterY());

            // attach the base
            Registry.sSceneLayerTowers.attachChild(mTowerBase);

        }

        // sort
        Registry.sSceneLayerTowers.sortChildren();

        // attach our tower to the scene
        Registry.sSceneLayerTowers.attachChild(this);

        // call our event for the tower implementation
        onTowerPlace();

    }

    public void destroy() {
        destroy(false);
    }

    public void destroy(final boolean isUpgrade) {

        // remove the top
        if(null != mTowerBase) {
            detachChild(mTowerBase);
            mTowerBase.reset();
        }

        // remove the base
        Registry.sSceneLayerTowers.detachChild(this);

        // check if this is a upgrade
        if(!isUpgrade) {

            // free the tile
            Registry.sMap.setTileFree(mTowerCol, mTowerRow);

        }

        // call the destroy event
        onTowerDestroy();

        // destroy it
        reset();

    }

    protected boolean isActionOutside(final float pX, final float pY){
        if(pX < 0 || pX > this.getWidth()) return true;
        if(pY < 0 || pY > this.getHeight()) return true;
        return false;
    }

    @Override
    public boolean isEffectable() {
        return true;
    }
}