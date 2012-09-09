package epiris.tower.defense.cstockton.org.util;

import java.math.BigDecimal;

import org.anddev.andengine.util.MathUtils;

import android.util.FloatMath;

public class Util {

    public static double round(double unrounded, int precision, int roundingMode)
    {
        BigDecimal bd = new BigDecimal(unrounded);
        BigDecimal rounded = bd.setScale(precision, roundingMode);
        return rounded.doubleValue();
    }

    public static String getHumanStringFromInt(final float amount) {
        if(amount > 1000000000) {
            return Float.toString((float) round((double) amount / 1000000000, 2, BigDecimal.ROUND_HALF_UP)) + "B";

        } else if(amount > 1000000) {
            return Float.toString((float) round((double) amount / 1000000, 2, BigDecimal.ROUND_HALF_UP)) + "M";

        } else if(amount > 1000) {
            return Float.toString((float) round((double) amount / 1000, 2, BigDecimal.ROUND_HALF_UP)) + "K";

        } else {
            return Integer.toString((int) FloatMath.ceil(amount));

        }
    }

    public static String getHumanStringFromInt(final int amount) {
        return getHumanStringFromInt((float) amount);
    }

    public static int getIntFromString(final String pString, final int pInt) {
        try {
            return Integer.parseInt(pString);
        } catch(final NumberFormatException e) {
            return pInt;
        }
    }

    public static float distance(final float pX1, final float pY1, final float pX2, final float pY2) {
        final float dX = pX2 - pX1;
        final float dY = pY2 - pY1;
        return FloatMath.sqrt((dX * dX) + (dY * dY));
    }

    /**
     * Return the angle in radians from the fixed X axis and the vector x1,y1
     * x2,y2 The angle is normalized between -PI and PI. 0 is parallel to the
     * fixed Y axis. Positive angles from 0 to PI/2 are in the top right
     * quadrant Positive angles from PI/2 to PI are in the bottom right quadrant
     * Negative angles from 0 to -PI/2 are in the top left quadrant Negative
     * angles from -PI/2 to -PI are in the bottom left quadrant
     *
     * @param pX1
     *            The X coordinate of the first point
     * @param pY1
     *            The Y coordinate of the first point
     * @param pX2
     *            The X coordinate of the second point
     * @param pY2
     *            The Y coordinate of the second point
     * @return Angle between the X axis and the vector specified by pX1,pY1
     *         pX2,pY2
     */
    public static float angleAxisToVector(final float pX1, final float pY1, final float pX2, final float pY2) {
        final float dX = pX2 - pX1;
        final float dY = pY2 - pY1;
        return (float) Math.atan2(dX, dY);
    }

    /**
     * Return the angle in degrees from the fixed X axis and the vector x1,y1
     * x2,y2 The angle is normalized between 0 and 360. 360.00 is not included
     * (360.00 == 0 degrees)
     *
     * @param pX1
     *            The X coordinate of the first point
     * @param pY1
     *            The Y coordinate of the first point
     * @param pX2
     *            The X coordinate of the second point
     * @param pY2
     *            The Y coordinate of the second point
     * @return Angle between the X axis and the vector specified by pX1,pY1
     *         pX2,pY2
     */
    public static float angleAxisToVectorDegrees(final float pX1, final float pY1, final float pX2, final float pY2) {
        final float angleRad = angleAxisToVector(pX1, pY1, pX2, pY2);

        return normalAngleDegrees(MathUtils.radToDeg(angleRad));
    }

    /**
     * Normalize an angle in degrees between 0 and 360 The value 360.00 itself
     * is not included in the output range.
     *
     * @param pDeg
     *            Angle to normalize
     * @return Normalized angle between 0 and 360
     */
    public static float normalAngleDegrees(final float pDeg) {
        final float pMod = pDeg % 360;
        if (pMod >= 0)
            return pMod;
        return pMod + 360;
    }

    public static float lerp(float start, float target, float duration, float timeSinceStart) {
        float value = start;
        if (timeSinceStart > 0.0f && timeSinceStart < duration) {
            final float range = target - start;
            final float percent = timeSinceStart / duration;
            value = start + (range * percent);
        } else if (timeSinceStart >= duration) {
            value = target;
        }
        return value;
    }

    public static float ease(float start, float target, float duration, float timeSinceStart) {
        float value = start;
        if (timeSinceStart > 0.0f && timeSinceStart < duration) {
            final float range = target - start;
            final float percent = timeSinceStart / (duration / 2.0f);
            if (percent < 1.0f) {
                value = start + ((range / 2.0f) * percent * percent * percent);
            } else {
                final float shiftedPercent = percent - 2.0f;
                value = start + ((range / 2.0f) * ((shiftedPercent * shiftedPercent * shiftedPercent) + 2.0f));
            }
        } else if (timeSinceStart >= duration) {
            value = target;
        }
        return value;
    }

    public class Interpolator {

        private float mCurrent;
        private float mTarget;
        private float mAcceleration;

        // Rather than simply interpolating acceleration and velocity for each
        // time step
        // (as in, position += (velocity * time); velocity += (acceleration *
        // time);),
        // we actually perform the work needed to calculate the integral of
        // velocity with respect to
        // time.
        //
        // The integral of velocity is:
        //
        // integral[(v + aT)dT]
        //
        // Simplified to:
        //
        // vT + 1/2 * aT^2
        //
        // Thus:
        // change in position = velocity * time + (0.5 * acceleration *
        // (time^2))
        // change in velocity = acceleration * time
        public void set(float current, float target, float acceleration) {
            mCurrent = current;
            mTarget = target;
            mAcceleration = acceleration;
        }

        // While this function writes directly to velocity, it doesn't affect
        // position. Instead, the position offset is returned so that it can be
        // blended.
        public float interpolate(float secondsDelta) {
            float oldVelocity = mCurrent;

            // point the acceleration at the target, or zero it if we are
            // already
            // there
            float directionalAcceleration = calculateAcceleration(oldVelocity, mAcceleration, mTarget);

            // calculate scaled acceleration (0.5 * acceleration * (time^2))
            float scaledAcceleration;
            scaledAcceleration = scaleAcceleration(directionalAcceleration, secondsDelta);

            // calculate the change in position
            float positionOffset = (oldVelocity * secondsDelta) + scaledAcceleration;

            // change in velocity = v + aT
            float newVelocity = oldVelocity + (directionalAcceleration * secondsDelta);

            // check to see if we've passed our target velocity since the last
            // time
            // step. If so, clamp to the target
            if (passedTarget(oldVelocity, newVelocity, mTarget)) {
                newVelocity = mTarget;
            }

            mCurrent = newVelocity;

            return positionOffset;
        }

        public float getCurrent() {
            return mCurrent;
        }

        private boolean passedTarget(float oldVelocity, float newVelocity, float targetVelocity) {
            boolean result = false;

            if (oldVelocity < targetVelocity && newVelocity > targetVelocity) {
                result = true;
            } else if (oldVelocity > targetVelocity && newVelocity < targetVelocity) {
                result = true;
            }

            return result;
        }

        // find the magnitude and direction of acceleration.
        // in this system, acceleration always points toward target velocity
        private float calculateAcceleration(float velocity, float acceleration, float target) {
            if (Math.abs(velocity - target) < 0.0001f) {
                // no accel needed
                acceleration = 0.0f;
            } else if (velocity > target) {
                // accel must be negative
                acceleration *= -1.0f;
            }

            return acceleration;
        }

        // calculates 1/2 aT^2
        private float scaleAcceleration(float acceleration, float secondsDelta) {
            float timeSquared = (secondsDelta * secondsDelta);
            float scaledAccel = acceleration * timeSquared;
            scaledAccel *= 0.5f;

            return scaledAccel;
        }
    }
}
