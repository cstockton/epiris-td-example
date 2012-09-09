package epiris.tower.defense.cstockton.org.creep;

import java.util.ArrayList;
import java.util.Iterator;

import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.util.Util;

public class CreepFloatingTextManager {
    final private ArrayList<CreepFloatingText> mActiveCreepFloatingText = new ArrayList<CreepFloatingText>();
    final private CreepFloatingTextPool mCreepFloatingTextPool;

    public CreepFloatingTextManager() {
        mCreepFloatingTextPool = new CreepFloatingTextPool(Registry.sFontWhite8);
    }

    public void damageText(final Creep pCreep, final float pDamage) {
        final CreepFloatingText cft = mCreepFloatingTextPool.obtainPoolItem();

        cft.activate(pCreep, "-" + Util.getHumanStringFromInt(pDamage), 1.25f, 0f, -64f);
        cft.setColor(1f, 0f, 0f);

        mActiveCreepFloatingText.add(cft);
    }

    public void healText(final Creep pCreep, final float pHeal) {
        final CreepFloatingText cft = mCreepFloatingTextPool.obtainPoolItem();

        cft.activate(pCreep, "+" + Util.getHumanStringFromInt(pHeal), 1.25f, 0f, 64f);
        cft.setColor(0f, 1f, 0f);

        mActiveCreepFloatingText.add(cft);
    }

    public void goldText(final Creep pCreep, final float pGold) {
        final CreepFloatingText cft = mCreepFloatingTextPool.obtainPoolItem();

        cft.activate(pCreep, "+" + Util.getHumanStringFromInt(pGold), 2f, 48f, -48f);
        cft.setColor(1f, .84f, 0f);

        mActiveCreepFloatingText.add(cft);
    }

    public void scoreText(final Creep pCreep, final float pScore) {
        final CreepFloatingText cft = mCreepFloatingTextPool.obtainPoolItem();

        cft.activate(pCreep, "+" + Util.getHumanStringFromInt(pScore), 2f, -48f, -48f);
        cft.setColor(1f, 1f, 1f);

        mActiveCreepFloatingText.add(cft);
    }

    public void onUpdate(final float pSecondsElapsed) {

        // iterate all our text
        for(final Iterator<CreepFloatingText> iterator = mActiveCreepFloatingText.iterator(); iterator.hasNext();) {
            final CreepFloatingText cft = iterator.next();

            // if this creep is dead, we need to remove it
            if(!cft.isActive()) {

                // remove the laser
                iterator.remove();

                // recycle
                mCreepFloatingTextPool.recyclePoolItem(cft);

            } else {

                // call the text update method
                cft.onTextUpdate(pSecondsElapsed);

            }
        }
    }
}