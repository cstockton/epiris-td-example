package epiris.tower.defense.cstockton.org.spell.affect;

import java.util.ArrayList;

import org.anddev.andengine.util.pool.GenericPool;

import epiris.tower.defense.cstockton.org.config.SpellTypes;
import epiris.tower.defense.cstockton.org.damage.Damages;
import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.spell.ISpellCaster;
import epiris.tower.defense.cstockton.org.spell.ISpellTargetable;
import epiris.tower.defense.cstockton.org.spell.Spell;

public class DotSpellAffect extends SpellAffect {
    private final ArrayList<ISpellAffectable> mSpellAffectables = new ArrayList<ISpellAffectable>();
    private final ArrayList<ISpellTargetable> mSpellTargetables = new ArrayList<ISpellTargetable>();

    public Damages mTickDamages;
    public int mAoeRange;
    public int mAoeMaxTargets;
    public float mAoeDamageModifier;

    public int mTickCastRange;
    public int mTickCastMaxTargets;
    public float mTickCastCastDelay;
    public SpellTypes[] mTickCastSpellTypes;

    static final private GenericPool<DotSpellAffect> mDotSpellAffectPool = new GenericPool<DotSpellAffect>() {

        @Override
        protected DotSpellAffect onAllocatePoolItem() {
            return new DotSpellAffect();
        }
    };

    static final public DotSpellAffect obtain() {
        return mDotSpellAffectPool.obtainPoolItem();
    }

    static final public void recycle(final DotSpellAffect pDotSpellAffect) {
        pDotSpellAffect.mTickDamages = null;
        pDotSpellAffect.mAoeRange = 0;
        pDotSpellAffect.mAoeMaxTargets = 0;
        pDotSpellAffect.mAoeDamageModifier = 0;
        pDotSpellAffect.mTickCastRange = 0;
        pDotSpellAffect.mTickCastMaxTargets = 0;
        pDotSpellAffect.mTickCastCastDelay = 0f;
        pDotSpellAffect.mTickCastSpellTypes = null;

        mDotSpellAffectPool.recyclePoolItem(pDotSpellAffect);
        pDotSpellAffect.reset();
    }

    @Override
    public void finished() {
        DotSpellAffect.recycle(this);
    }

    @Override
    public void onManagedTick() {
        if(null != mTickDamages) {
            mTickDamages.mMultiplier = getCurrentStackCount() - 1;
            mAffected.damage(mTickDamages);

            if(mAoeRange > 0) {
                mSpellAffectables.clear();
                Registry.sCreepManager.getSpellAffectablesInRange(mAffected.getX(), mAffected.getY(), mAoeRange, mSpellAffectables);

                mTickDamages.mMultiplier = mTickDamages.mMultiplier * mAoeDamageModifier;
                int targetsAoeDamaged = 0;

                for(final ISpellAffectable target : mSpellAffectables) {
                    if(target.equals(mAffected)) {
                        continue;
                    }
                    if(++targetsAoeDamaged > mAoeMaxTargets) {
                        break;
                    }

                    target.damage(mTickDamages);
                }
            }
        }

        if(mTickCastRange > 0) {
            mSpellTargetables.clear();
            Registry.sCreepManager.getSpellTargetablesInRange(mAffected.getX(), mAffected.getY(), mTickCastRange, mSpellTargetables);

            int targetsCastUpon = 0;
            for(final ISpellTargetable target : mSpellTargetables) {
                if(target.equals(mAffected)) {
                    continue;
                }
                if(++targetsCastUpon > mTickCastMaxTargets) {
                    break;
                }

                for(final SpellTypes spell : mTickCastSpellTypes) {
                    Spell.cast((ISpellCaster) mAffected, target, spell, (mTickCastCastDelay * targetsCastUpon));
                }
            }
        }
    }
}