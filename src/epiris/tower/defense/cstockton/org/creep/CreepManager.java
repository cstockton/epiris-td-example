package epiris.tower.defense.cstockton.org.creep;

import java.util.ArrayList;
import java.util.Iterator;
import org.anddev.andengine.util.MathUtils;

import epiris.tower.defense.cstockton.org.config.CreepSpriteSheets;
import epiris.tower.defense.cstockton.org.map.MapSpawn;
import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.spell.ISpellTargetable;
import epiris.tower.defense.cstockton.org.spell.affect.ISpellAffectable;
import epiris.tower.defense.cstockton.org.wave.Wave;

public class CreepManager {

    final private CreepPool mCreepPool[];
    final private ArrayList<Creep> mActiveCreeps = new ArrayList<Creep>();

    private boolean mPathUpdateWaiting = false;

    static public boolean mHasBlockedCreeps = false;
    static public float mLastBlockedCreepsNotification = 1000f;

    public CreepManager() {
        mCreepPool = new CreepPool[CreepSpriteSheets.values().length];

        for(final CreepSpriteSheets creepSheet : CreepSpriteSheets.values()) {
            mCreepPool[creepSheet.ordinal()] = new CreepPool(Registry.sCreepsTextureRegions[creepSheet.ordinal()]);
        }
    }

    public boolean hasActiveCreeps() {
        return mActiveCreeps.size() > 0;
    }

    public void onTileChange(final int pTileCol, final int pTileRow, final boolean isBlocked) {
        mPathUpdateWaiting = true;
    }

    public CreepManager recycleCreep(final Creep pCreep) {
        mCreepPool[pCreep.mSprite.getSpriteSheet().ordinal()].recyclePoolItem(pCreep);
        return this;
    }

    public Creep spawn(final Wave pWave, final MapSpawn pMapSpawn, final CreepConfiguration pCreepConfiguration) {

        // debug output
        //Debug.v("CreepManager:: spawn :: sprite=" + pCreepConfiguration.mSprite.name() + " scale=" + pCreepConfiguration.mScale + " speed=" + pCreepConfiguration.mSpeed + " lifeBonus=" + pCreepConfiguration.mLifeBonus);

        // first get a creep from the pool
        final int ordinal = pCreepConfiguration.mSprite.getSpriteSheet().ordinal();
        final Creep creep = mCreepPool[ordinal].obtainPoolItem();

        // now we activate this creep
        creep.activate(pWave, pMapSpawn, pCreepConfiguration);

        // add this creep to our active creeps list
        mActiveCreeps.add(creep);

        return creep;
    }

    public ArrayList<ISpellTargetable> getSpellTargetablesInRange(final float pX, final float pY, final float pRange) {
        return getSpellTargetablesInRange(pX, pY, pRange, new ArrayList<ISpellTargetable>());
    }

    public ArrayList<ISpellTargetable> getSpellTargetablesInRange(final float pX, final float pY, final float pRange, final ArrayList<ISpellTargetable> pCreeps) {

        // get the creep base list
        final Iterator<Creep> creepItartor = mActiveCreeps.iterator();

        // iterate all creeps
        while (creepItartor.hasNext()) {
            final Creep creep = creepItartor.next();

            if (!creep.isAlive()) {
                continue;
            }

            // get the distance between the creep and coords
            float distance = MathUtils.distance(creep.getX(), creep.getY(), pX, pY);

            // check if the range is satisfactory
            if (pRange > distance) {
                pCreeps.add(creep);
            }
        }

        return pCreeps;

    }

    public ArrayList<ISpellAffectable> getSpellAffectablesInRange(final float pX, final float pY, final float pRange) {
        return getSpellAffectablesInRange(pX, pY, pRange, new ArrayList<ISpellAffectable>());
    }

    public ArrayList<ISpellAffectable> getSpellAffectablesInRange(final float pX, final float pY, final float pRange, final ArrayList<ISpellAffectable> pCreeps) {

        // get the creep base list
        final Iterator<Creep> creepItartor = mActiveCreeps.iterator();

        // iterate all creeps
        while (creepItartor.hasNext()) {
            final Creep creep = creepItartor.next();

            if (!creep.isAlive()) {
                continue;
            }

            // get the distance between the creep and coords
            float distance = MathUtils.distance(creep.getX(), creep.getY(), pX, pY);

            // check if the range is satisfactory
            if (pRange > distance) {
                pCreeps.add(creep);
            }
        }

        return pCreeps;

    }

    public Iterator<Creep> getCreepIterator() {
        return mActiveCreeps.iterator();
    }

    public void onUpdate(final float pSecondsElapsed) {

        // a static flag we use to check if the user has blocked creeps to notify them
        mHasBlockedCreeps = false;

        // flag for handling path updates
        final boolean pathUpdateWaiting = mPathUpdateWaiting;

        // iterate all our active creeps, checking for dead ones that need removal
        for(final Iterator<Creep> iterator = mActiveCreeps.iterator(); iterator.hasNext();) {
            final Creep creep = iterator.next();

            // if this creep is dead, we need to remove it
            if(!creep.isAlive()) {

                // deactivate
                creep.deactivate();

                // remove the laser
                iterator.remove();

                // recycle
                recycleCreep(creep);

            } else {

                // see if we need to update the path of all creeps
                if(pathUpdateWaiting) {
                    creep.updatePath();
                }

                // check to see if the creep is blocked
                if(creep.isBlocked()) {
                    mHasBlockedCreeps = true;
                }

                // call the creep update func
                creep.onCreepUpdate(pSecondsElapsed);

            }
        }

        // see if we need to update the path of all creeps
        if(pathUpdateWaiting) {
            mPathUpdateWaiting = false;
        }

        // check our blocked creeps flag
        if(mHasBlockedCreeps) {

            // has it been 3 seconds since the last notification?
            if(mLastBlockedCreepsNotification > 3f) {
                mLastBlockedCreepsNotification = 0;

                // notify the user they are blocking
                Registry.sGameActivity.userNotifyShort("You are blocking creeps!!! Selling a tower every 3 seconds!!!");

                // sell a random tower
                Registry.sTowerManager.sellTower();

            } else {

                // incrememnt the elapsed time since last tower sell
                mLastBlockedCreepsNotification += pSecondsElapsed;

            }
        }
    }
}