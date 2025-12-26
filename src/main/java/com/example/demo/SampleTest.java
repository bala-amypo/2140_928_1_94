package com.example.demo;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class SampleTest {
    @Test
    public void testAddition() {
        assertEquals(2 + 3, 5);
    }
}
