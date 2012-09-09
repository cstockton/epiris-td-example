package epiris.tower.defense.cstockton.org.player.rewards;

import epiris.tower.defense.cstockton.org.config.PlayerRewardTypes;

public class PlayerReward {
    final private String mName;
    private int mLevel;

    final PlayerRewardTypes mType;

    public PlayerReward(final String pName, final int pLevel) {
        mName = pName;
        mLevel = pLevel;

        mType = PlayerRewardTypes.getByName(pName);
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mType.mDescription;
    }

    public String getDescriptionUpgrade() {
        return mType.mDescriptionUpgrade;
    }

    public int getLevel() {
        return mLevel;
    }

    public int getMaxLevel() {
        if(mType.hasCost()) {
            return mType.mPlayerRewardCost.getMaxLevel();
        }

        return 1;
    }

    public void setLevel(final int pLevel) {
        mLevel = pLevel;
    }

    public PlayerRewardTypes getType() {
        return mType;
    }

    public int getCost() {
        if(null != mType.mPlayerRewardCost) {
            return mType.mPlayerRewardCost.getCost(mLevel);
        } else {
            return 0;
        }
    }

    public int getCostForNextLevel() {
        if(null != mType.mPlayerRewardCost) {
            return mType.mPlayerRewardCost.getCost(mLevel + 1);
        } else {
            return 0;
        }
    }

    public int getEffects() {
        if(null != mType.mPlayerRewardEffects) {
            return 1; //mType.mPlayerRewardEffects.getValue(mLevel);
        } else {
            return mLevel;
        }
    }

    public int getEffectsForNextLevel() {
        if(null != mType.mPlayerRewardEffects) {
            return 1; //mType.mPlayerRewardEffects.getValue(mLevel + 1);
        } else {
            return mLevel;
        }
    }

    public boolean hasCost() {
        return null != mType.mPlayerRewardCost;
    }

    public boolean hasEffects() {
        return null != mType.mPlayerRewardEffects;
    }

    public boolean hasPrerequisites() {
        return null != mType.mPlayerRewardPrerequisites;
    }

    public IPlayerRewardPrerequisite[] getPrerequisites() {
        return mType.mPlayerRewardPrerequisites;
    }

    public boolean hasSatisfiedAllPrerequisites() {
        if(!hasPrerequisites()) {
            return true;
        }

        boolean satisfied = true;

        for(IPlayerRewardPrerequisite p : mType.mPlayerRewardPrerequisites) {
            if(!satisfied || !p.isSatisfied()) {
                satisfied = false;
                break;
            }
        }

        return satisfied;
    }
}