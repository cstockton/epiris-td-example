package epiris.tower.defense.cstockton.org.util.ease;

public class EaseLinear implements IEaseFunction {

    private static EaseLinear INSTANCE;

    private EaseLinear() {
    }

    public static EaseLinear getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new EaseLinear();
        }
        return INSTANCE;
    }

    @Override
    public float getPercentageDone(final float pSecondsElapsed, final float pDuration, final float pMinValue, final float pMaxValue) {
        return pMaxValue * pSecondsElapsed / pDuration + pMinValue;
    }
}