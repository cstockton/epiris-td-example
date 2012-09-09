package epiris.tower.defense.cstockton.org.spell.affect;

import java.util.ArrayList;

import org.anddev.andengine.util.pool.GenericPool;

import epiris.tower.defense.cstockton.org.config.SpellTypes;
import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.spell.ISpellCaster;
import epiris.tower.defense.cstockton.org.spell.ISpellListener;
import epiris.tower.defense.cstockton.org.spell.ISpellTargetable;
import epiris.tower.defense.cstockton.org.spell.Spell;

public class ChainCastSpellAffect extends SpellAffect implements ISpellListener {
    private final ArrayList<ISpellTargetable> mSpellTargetables = new ArrayList<ISpellTargetable>();
    private final ArrayList<ISpellTargetable> mSpellTargetablesSpread = new ArrayList<ISpellTargetable>();

    public int mRange;
    public int mMaxTargets;
    public int mMaxChains;
    public float mCastTargetDelay;
    public float mCastChainDelay;
    public SpellTypes[] mSpellTypes;

    private int mChains = 0;

    static final private GenericPool<ChainCastSpellAffect> mChainCastSpellAffect = new GenericPool<ChainCastSpellAffect>() {

        @Override
        protected ChainCastSpellAffect onAllocatePoolItem() {
            return new ChainCastSpellAffect();
        }
    };

    static final public ChainCastSpellAffect obtain() {
        return mChainCastSpellAffect.obtainPoolItem();
    }

    static final public void recycle(final ChainCastSpellAffect pChainCastSpellAffect) {
        mChainCastSpellAffect.recyclePoolItem(pChainCastSpellAffect);
        pChainCastSpellAffect.reset();
    }

    @Override
    public void finished() { }

    @Override
    public void onApplication() {
        mSpellTargetables.clear();

        Registry.sCreepManager.getSpellTargetablesInRange(mAffected.getX(), mAffected.getY(), mRange, mSpellTargetables);

        int targetsCastUpon = 0;

        for(final ISpellTargetable target : mSpellTargetables) {
            if(++targetsCastUpon > mMaxTargets) {
                break;
            }
            if(target.equals(mAffected)) {
                continue;
            }

            for(final SpellTypes spell : mSpellTypes) {
                Spell.cast((ISpellCaster) mAffected, target, spell, (mCastTargetDelay * targetsCastUpon), (ISpellListener) this);
            }
        }
    }

    @Override
    public void onSpellCast(final Spell pSpell, final ISpellTargetable pTarget) { }

    @Override
    public void onSpellTravel(final Spell pSpell, final ISpellTargetable pTarget) { }

    @Override
    public void onSpellImpact(final Spell pSpell, final ISpellTargetable pTarget) {
        if(++mChains > mMaxChains) {
            mSpellTargetablesSpread.clear();

            Registry.sCreepManager.getSpellTargetablesInRange(mAffected.getX(), mAffected.getY(), mRange, mSpellTargetablesSpread);

            if(mSpellTargetablesSpread.size() <= 0) {
                ChainCastSpellAffect.recycle(this);

            } else {
                int targetsCastUpon = 0;

                for(final ISpellTargetable target : mSpellTargetablesSpread) {
                    if(++targetsCastUpon > mMaxTargets) {
                        break;
                    }
                    if(target.equals(mAffected)) {
                        continue;
                    }

                    for(final SpellTypes spell : mSpellTypes) {
                        Spell.cast((ISpellCaster) mAffected, target, spell, (mCastTargetDelay * targetsCastUpon), (ISpellListener) this);
                    }
                }
            }
        } else {
            ChainCastSpellAffect.recycle(this);

        }
    }

    @Override
    public void onSpellFinished(final Spell pSpell, final ISpellTargetable pTarget) { }
}