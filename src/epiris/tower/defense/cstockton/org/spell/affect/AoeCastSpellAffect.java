package epiris.tower.defense.cstockton.org.spell.affect;

import java.util.ArrayList;

import org.anddev.andengine.util.pool.GenericPool;

import epiris.tower.defense.cstockton.org.config.SpellTypes;
import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.spell.ISpellCaster;
import epiris.tower.defense.cstockton.org.spell.ISpellTargetable;
import epiris.tower.defense.cstockton.org.spell.Spell;

public class AoeCastSpellAffect extends SpellAffect {
    private final ArrayList<ISpellTargetable> mSpellTargetables = new ArrayList<ISpellTargetable>();

    public int mRange;
    public int mMaxTargets;
    public float mCastDelay;
    public SpellTypes[] mSpellTypes;

    static final private GenericPool<AoeCastSpellAffect> mAoeCastSpellAffect = new GenericPool<AoeCastSpellAffect>() {

        @Override
        protected AoeCastSpellAffect onAllocatePoolItem() {
            return new AoeCastSpellAffect();
        }
    };

    static final public AoeCastSpellAffect obtain() {
        return mAoeCastSpellAffect.obtainPoolItem();
    }

    static final public void recycle(final AoeCastSpellAffect pAoeCastSpellAffect) {
        mAoeCastSpellAffect.recyclePoolItem(pAoeCastSpellAffect);
        pAoeCastSpellAffect.reset();
    }

    @Override
    public void finished() {
        AoeCastSpellAffect.recycle(this);
    }

    @Override
    public void onApplication() {
        mSpellTargetables.clear();

        Registry.sCreepManager.getSpellTargetablesInRange(mAffected.getX(), mAffected.getY(), mRange, mSpellTargetables);

        int targetsCastUpon = 0;
        //Debug.i("mSpellTypesl=" + mSpellTypes.length + " mSpellTypes[0]=" + mSpellTypes[0] + " mSpellTypes=" + mSpellTypes);

        for(final ISpellTargetable target : mSpellTargetables) {
            if(++targetsCastUpon > mMaxTargets) {
                break;
            }
            if(target.equals(mAffected)) {
                continue;
            }
            for(final SpellTypes spell : mSpellTypes) {
                Spell.cast((ISpellCaster) mAffected, target, spell, (mCastDelay * targetsCastUpon));
            }
        }

        deactivate();
    }
}