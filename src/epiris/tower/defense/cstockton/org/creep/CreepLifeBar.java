package epiris.tower.defense.cstockton.org.creep;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.text.ChangeableText;

import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.util.Util;
import android.util.FloatMath;

public class CreepLifeBar extends Rectangle {

    private final static int LIFEBAR_WIDTH = (Creep.CREEP_WIDTH - 2);

    private final Creep mCreep;
    private final ChangeableText mLifeNumber;

    private float mLifeRemaining = 1;
    private float mLifeTotal = 1;

    public void onCurrentLifeChanged() {

        // this creep is ready to be KILLED
        if(mLifeRemaining <= 0) {
            setWidth(0);
            mCreep.kill();

        } else {
            final float percentage = (float) ((double) mLifeRemaining / (double) mLifeTotal);
            final float i = LIFEBAR_WIDTH * percentage;

            setWidth(i);
            setColor(1f - percentage, percentage, 0f);

            if(!mLifeNumber.isVisible() || !isVisible()) {
                setVisible(true);
                mLifeNumber.setVisible(true);
            }

            mLifeNumber.setText(Util.getHumanStringFromInt(mLifeRemaining));
        }
    }

    public CreepLifeBar(final Creep pCreep) {
        super(0, -8, LIFEBAR_WIDTH, 14);

        // set the lifebar color
        setColor(0, 1f, 0);
        setVisible(false);

        // attack the life bar number
        mLifeNumber = new ChangeableText(1, 1, Registry.sFontWhite12, Integer.toString((int) FloatMath.ceil(mLifeRemaining)), 9);
        mLifeNumber.setVisible(false);
        mLifeNumber.setColor(0f, 0f, 0f);

        // assign the creep reference
        mCreep = pCreep;

        // attach the life number to this lifebar
        attachChild(mLifeNumber);

    }

    public CreepLifeBar activate(final float pLife) {

        // set the life
        setLife(pLife);

        // reset set the width
        setWidth(Creep.CREEP_WIDTH);
        setColor(0, 1f, 0);
        setVisible(false);
        setIgnoreUpdate(false);

        // modify life bar
        mLifeNumber.setColor(0f, 0f, 0f);
        mLifeNumber.setVisible(false);
        mLifeNumber.setIgnoreUpdate(false);

        // attach to the scene now
        Registry.sSceneLayerCreeps.attachChild(this);

        return this;
    }

    public CreepLifeBar deactivate() {

        // reset set the width
        setVisible(false);
        setIgnoreUpdate(true);

        // set position
        setPosition(-500, -500);

        // modify life bar
        mLifeNumber.setVisible(false);
        mLifeNumber.setIgnoreUpdate(true);

        // attach to the scene now
        Registry.sSceneLayerCreeps.detachChild(this);

        reset();

        return this;
    }

    public CreepLifeBar setLife(final float pLife) {
        mLifeTotal = mLifeRemaining = pLife;
        onCurrentLifeChanged();
        return this;
    }

    public float getCurrentLife() {
        return mLifeRemaining;
    }

    public CreepLifeBar setCurrentLife(final float pLife) {
        mLifeRemaining = pLife;
        onCurrentLifeChanged();
        return this;
    }

    public CreepLifeBar addCurrentLife(final float pLife) {
        if((mLifeRemaining + pLife) > mLifeTotal) {
            mLifeRemaining = mLifeTotal;
        } else {
            mLifeRemaining += pLife;
        }
        onCurrentLifeChanged();
        return this;
    }

    public CreepLifeBar subtractCurrentLife(final float pLife) {
        mLifeRemaining -= pLife;
        onCurrentLifeChanged();
        return this;
    }

    public float getMaxLife() {
        return mLifeTotal;
    }

    public CreepLifeBar setMaxLife(final float pLife) {
        mLifeTotal = pLife;
        onCurrentLifeChanged();
        return this;
    }
}