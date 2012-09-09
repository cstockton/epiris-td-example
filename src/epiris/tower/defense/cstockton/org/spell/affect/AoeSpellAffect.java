package epiris.tower.defense.cstockton.org.spell.affect;

import java.util.ArrayList;

import org.anddev.andengine.util.pool.GenericPool;

import epiris.tower.defense.cstockton.org.damage.Damages;
import epiris.tower.defense.cstockton.org.object.Registry;

public class AoeSpellAffect extends SpellAffect {
    private final ArrayList<ISpellAffectable> mSpellAffectables = new ArrayList<ISpellAffectable>();

    public Damages mDamages;
    public int mRange;
    public int mMaxTargets;

    static final private GenericPool<AoeSpellAffect> mAoeSpellAffect = new GenericPool<AoeSpellAffect>() {

        @Override
        protected AoeSpellAffect onAllocatePoolItem() {
            return new AoeSpellAffect();
        }
    };

    static final public AoeSpellAffect obtain() {
        return mAoeSpellAffect.obtainPoolItem();
    }

    static final public void recycle(final AoeSpellAffect pAoeSpellAffect) {
        mAoeSpellAffect.recyclePoolItem(pAoeSpellAffect);
        pAoeSpellAffect.reset();
    }

    @Override
    public void finished() {
        AoeSpellAffect.recycle(this);
    }

    @Override
    public void onApplication() {
        mSpellAffectables.clear();

        Registry.sCreepManager.getSpellAffectablesInRange(mAffected.getX(), mAffected.getY(), mRange, mSpellAffectables);

        int targetsCastUpon = 0;

        for(final ISpellAffectable target : mSpellAffectables) {
            if(++targetsCastUpon > mMaxTargets) {
                break;
            }
            if(target.equals(mAffected)) {
                continue;
            }

            target.damage(mDamages);
        }

        deactivate();
    }
}