package epiris.tower.defense.cstockton.org.spell.affect;

import epiris.tower.defense.cstockton.org.spell.ISpellCaster;

public interface ISpellAffect {

    public void setStackId(final String pStackId);
    public String getStackId();

    public void setStackCount(final int pStackCount);
    public int getStackCount();

    public void setTickCount(final int pTickCount);
    public int getTickCount();

    public void setTickInterval(final float pTickInterval);
    public float getTickInterval();

    public int getTotalTickCount();
    public int getCurrentStackCount();

    public void finished();
    public void refresh(final ISpellAffects pSpellAffectType);

    public void setType(final ISpellAffects pSpellAffectType);
    public ISpellAffects getType();

    public void setRefreshable(final boolean pRefreshable);
    public boolean isRefreshable();

    public void setActive(final boolean pActive);
    public boolean isActive();

    public void setAffected(final ISpellAffectable pAffected);
    public ISpellAffectable getAffected();

    public void setCaster(final ISpellCaster pCaster);
    public ISpellCaster getCaster();

    public void activate(final ISpellAffectable pSpellAffectable);
    public void deactivate();
    public void reset();

    public void onApplication();
    public void onRefresh(final ISpellAffects pSpellAffectType);
    public void onRemoval();

    public void onTick(final float pSecondsElapsed);
    public void onManagedTick();

}