package com.github.forticulous;

/**
 * Utility class used to find the angle between the hour and minute hands of a clock
 */
public final class ClockAngle {
    private ClockAngle() {}

    public static int findAngle(int hrs, int mins) {
        if (hrs < 1 || hrs > 12) {
            throw new IllegalArgumentException("Hours must be between 1 and 12");
        }
        if (mins < 0 || mins > 59) {
            throw new IllegalArgumentException("Minutes must be between 0 and 59");
        }

        // 30 degrees per hour
        int nearestHourAngle = 30 * hrs;

        // 30 degrees per 60 mins or .5 degrees per min
        int hrsProcession = Math.round(.5f * ((float) mins));

        // 30 degrees per 5 minutes or 6 degrees per minute
        int minsAngle = 6 * mins;

        // hrs angle + hrs procession - mins angle
        int clockwiseAngle = nearestHourAngle + hrsProcession - minsAngle;

        // absolute value
        int positiveAngle = Math.abs(clockwiseAngle);

        // smaller of the two angles
        return Math.min(positiveAngle, 360 - positiveAngle);
    }
}
