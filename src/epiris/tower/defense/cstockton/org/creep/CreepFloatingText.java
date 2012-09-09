package epiris.tower.defense.cstockton.org.creep;

import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.opengl.font.Font;

import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.util.ease.EaseQuintOut;
import epiris.tower.defense.cstockton.org.util.ease.IEaseFunction;

public class CreepFloatingText extends ChangeableText {
    final static private IEaseFunction mEaseFunction = EaseQuintOut.getInstance();

    public Creep mCreep;

    public long mSpawnId = 0L;
    public boolean mActive = false;
    public float mDuration = 0f;

    public float mCreepCenterX = 0f;
    public float mCreepCenterY = 0f;

    public float mOffsetX = 0f;
    public float mOffsetY = 0f;

    public float mTotalElapsedTime;

    public CreepFloatingText(final Font pFont) {
        super(0f, 0f, pFont, "0", 8);

        setVisible(false);
        setIgnoreUpdate(true);

        setWidth(Creep.CREEP_WIDTH);
        setHeight(Creep.CREEP_HEIGHT);
    }

    public boolean isActive() {
        return mActive;
    }


    public void activate(final Creep pCreep, final String pText, final float mDuration) {
        activate(pCreep, pText, mDuration, 0f, 0f);
    }

    public void activate(final Creep pCreep, final String pText, final float pDuration, final float pOffsetX, final float pOffsetY) {

        // duration for the text
        mDuration = pDuration;

        // the offsets for the text target
        mOffsetX = pOffsetX;
        mOffsetY = pOffsetY;

        // set text
        setText(pText);

        // set the creep
        mCreep = pCreep;
        mSpawnId = pCreep.getSpawnId();

        // set the time
        mTotalElapsedTime = 0f;

        // set the current creep coords
        mCreepCenterX = mCreep.getCenterX();
        mCreepCenterY = mCreep.getCenterY();

        // update pos
        onTextUpdate(0f);

        // enable the text
        setVisible(true);
        setIgnoreUpdate(false);

        // set it active
        mActive = true;

        // attach this to the layer
        Registry.sSceneLayerCreeps.attachChild(this);

    }

    public void deactivate() {

        // disable updates and visibility
        setVisible(false);
        setIgnoreUpdate(true);

        // set active to false
        mActive = false;

        // detach this from the layer
        Registry.sSceneLayerCreeps.detachChild(this);

    }

    public void onTextUpdate(final float pSecondsElapsed) {
        mTotalElapsedTime += pSecondsElapsed;

        if(mCreep.isAlive() && mSpawnId == mCreep.getSpawnId()) {

            // set the current creep coords
            mCreepCenterX = mCreep.getCenterX();
            mCreepCenterY = mCreep.getCenterY();

        }

        final float positionX = (mCreepCenterX + mEaseFunction.getPercentageDone(mTotalElapsedTime, mDuration, 0f, mOffsetX)) - (getWidth() / 2f);
        final float positionY = (mCreepCenterY + mEaseFunction.getPercentageDone(mTotalElapsedTime, mDuration, 0f, mOffsetY));

        setPosition(positionX, positionY);
        setZIndex((int) positionY);
        setScale(1f + mEaseFunction.getPercentageDone(mTotalElapsedTime, mDuration, 0f, 1f));

        if(mTotalElapsedTime > mDuration) {
            deactivate();
        }
    }
}