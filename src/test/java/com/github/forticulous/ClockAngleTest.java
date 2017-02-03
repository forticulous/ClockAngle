package com.github.forticulous;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Test class for ClockAngle
 */
public class ClockAngleTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void hoursLowerBoundTest() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Hours must be between 1 and 12");
        ClockAngle.findAngle(0, 1);
    }

    @Test
    public void hoursUpperBoundTest() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Hours must be between 1 and 12");
        ClockAngle.findAngle(13, 1);
    }

    @Test
    public void minutesLowerBoundTest() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Minutes must be between 0 and 59");
        ClockAngle.findAngle(1, -1);
    }

    @Test
    public void minutesUpperBoundTest() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Minutes must be between 0 and 59");
        ClockAngle.findAngle(1, 60);
    }

    /**
     * Test that the angle produced is the same as the hour hand's
     * angle when the minute hand is pointing at 12
     */
    @Test
    public void hourHandTest() throws Exception {
        assertEquals(30, ClockAngle.findAngle(1, 0));
    }

    /**
     * Test that the angle produced is the same as the minute hand's
     * angle when the hour hand is pointing at 12
     */
    @Test
    public void minuteHandTest() throws Exception {
        // minute hand angle = 60 deg
        // hour hand procession = 5 deg
        // 60 - 5 = 55 deg
        assertEquals(55, ClockAngle.findAngle(12, 10));
    }

    /**
     * Test that the algorithm always gives the smaller angle between the clock hands
     */
    @Test
    public void smallerAngleTest() throws Exception {
        // minute hand angle = 240 deg
        // hour hand angle = 0 deg
        // hour hand procession = 20 deg
        // clockwise angle would be 220, but the complementary angle 140 is smaller
        assertEquals(140, ClockAngle.findAngle(12, 40));
    }

    /**
     * Test that the algorithm does not produce negative angles
     */
    @Test
    public void negativeAngleTest() throws Exception {
        // minute hand angle = 180 deg
        // hour hand angle = 30 deg
        // hour hand procession = 15 deg
        // 30 + 15 - 180 would be negative, but we always want positive angles
        assertEquals(135, ClockAngle.findAngle(1, 30));
    }
}
