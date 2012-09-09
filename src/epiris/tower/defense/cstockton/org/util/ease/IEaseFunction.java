package epiris.tower.defense.cstockton.org.util.ease;

public interface IEaseFunction {
    public static final IEaseFunction DEFAULT = EaseLinear.getInstance();
    public abstract float getPercentageDone(final float pSecondsElapsed, final float pDuration, final float pMinValue, final float pMaxValue);
}