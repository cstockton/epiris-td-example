package epiris.tower.defense.cstockton.org.player.rewards;

public interface IPlayerRewardCost {
    public int getCost(final int pCurrentLevel);
    public int getMaxLevel();
}