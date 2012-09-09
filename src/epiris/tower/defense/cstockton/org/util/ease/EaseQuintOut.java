package epiris.tower.defense.cstockton.org.util.ease;

public class EaseQuintOut implements IEaseFunction {

    private static EaseQuintOut INSTANCE;

    private EaseQuintOut() {
    }

    public static EaseQuintOut getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new EaseQuintOut();
        }
        return INSTANCE;
    }

    @Override
    public float getPercentageDone(float pSecondsElapsed, final float pDuration, final float pMinValue, final float pMaxValue) {
        return pMaxValue * ((pSecondsElapsed = pSecondsElapsed / pDuration - 1) * pSecondsElapsed * pSecondsElapsed * pSecondsElapsed * pSecondsElapsed + 1) + pMinValue;
    }
}