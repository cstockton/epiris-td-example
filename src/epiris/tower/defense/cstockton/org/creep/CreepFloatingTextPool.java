package epiris.tower.defense.cstockton.org.creep;

import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.util.pool.GenericPool;

public class CreepFloatingTextPool  extends GenericPool<CreepFloatingText> {
    final Font mFont;

    public CreepFloatingTextPool(final Font pFont) {
        super();

        mFont = pFont;
    }

    @Override
    protected CreepFloatingText onAllocatePoolItem() {
        return new CreepFloatingText(mFont);
    }

    @Override
    protected void onHandleRecycleItem(final CreepFloatingText pCreepFloatingText) {
        pCreepFloatingText.reset();
    }
}