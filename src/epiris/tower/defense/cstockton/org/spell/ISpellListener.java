package epiris.tower.defense.cstockton.org.spell;

public interface ISpellListener {
    public void onSpellCast(final Spell pSpell, final ISpellTargetable pTarget);
    public void onSpellTravel(final Spell pSpell, final ISpellTargetable pTarget);
    public void onSpellImpact(final Spell pSpell, final ISpellTargetable pTarget);
    public void onSpellFinished(final Spell pSpell, final ISpellTargetable pTarget);
}