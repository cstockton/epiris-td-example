package epiris.tower.defense.cstockton.org.spell;

public interface ISpells {
    //public Spell getEffect();

    //public void initialize();
    //public void uninitialize();

    public boolean isUpdating();
    //public boolean isUpdating();

    //public void onUpdate(final float pSecondsElapsed, final float pSecondsElapsedTotal);
    public void onUpdate(final float pSecondsElapsed);
    //public void onCast(final ISpellCaster pCaster, final ISpellAffectable pTarget);
    //public void onCancel();

    //public void onSpellHit();
    //public void onAffectComplete()

}