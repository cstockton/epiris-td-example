package epiris.tower.defense.cstockton.org.player.rewards;

public class PlayerRewardLevelValue implements IPlayerRewardValue {
    final public int mValue;
    final public int mValueConstant;
    final public float mValueMultiplier;

    public PlayerRewardLevelValue(final int pValue, final int pValueConstant, final float pValueMultiplier) {
        mValue = pValue;
        mValueConstant = pValueConstant;
        mValueMultiplier = pValueMultiplier;
    }

    @Override
    public int getValue(final int pCurrentLevel) {
        if(1 > pCurrentLevel) {
            return 0;
        }

        return (int) (pCurrentLevel * ((mValue + mValueConstant) * mValueMultiplier));
    }
}