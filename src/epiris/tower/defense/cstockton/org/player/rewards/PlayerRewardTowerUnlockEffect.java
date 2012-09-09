package epiris.tower.defense.cstockton.org.player.rewards;

import epiris.tower.defense.cstockton.org.config.TowerTypes;

public class PlayerRewardTowerUnlockEffect implements IPlayerRewardEffect {
    final public TowerTypes mTowerType;

    public PlayerRewardTowerUnlockEffect(final TowerTypes pTowerType) {
        mTowerType = pTowerType;
    }

    @Override
    public Object[] toInfo() {
        return new String[] { mTowerType.mName };
    }
}