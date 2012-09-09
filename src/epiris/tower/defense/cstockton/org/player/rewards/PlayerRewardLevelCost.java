package epiris.tower.defense.cstockton.org.player.rewards;

public class PlayerRewardLevelCost implements IPlayerRewardCost {
    final public int mLevelMax;
    final public int mCost;
    final public int mCostConstant;
    final public float mCostMultiplier;

    public PlayerRewardLevelCost(final int pLevelMax, final int pCost, final int pCostConstant, final float pCostMultiplier) {
        mLevelMax = pLevelMax;

        mCost = pCost;
        mCostConstant = pCostConstant;
        mCostMultiplier = pCostMultiplier;
    }

    @Override
    public int getMaxLevel() {
        return mLevelMax;
    }

    @Override
    public int getCost(final int pCurrentLevel) {
        if(1 > pCurrentLevel) {
            return (int) (1 * ((mCost + mCostConstant) * mCostMultiplier));
        }

        return (int) (pCurrentLevel * ((mCost + mCostConstant) * mCostMultiplier));
    }
}