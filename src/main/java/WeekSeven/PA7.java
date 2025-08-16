package WeekSeven;

import static org.junit.Assert.*;
import org.junit.Test;

// PA7 uses the downloaded files from Canvas to create a test to verify calculations are bring done correctly
// for the two methods.
// Created by Aman Sarosh

public class PA7 {
    @Test
    public void testToCelsius() {
        Temperature temp = new Temperature();

        // 32°F = 0°C
        assertEquals(0, temp.toCelsius(32.0), 0.0001);
    }

    @Test
    public void testToFahrenheit() {
        Temperature temp = new Temperature();

        // 0°C = 32°F
        assertEquals(32, temp.toFahrenheit(0.0), 0.0001);
    }
}

// JUnit installed in pom.xml by IntelliJ