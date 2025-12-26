package com.example.demo;

import org.testng.annotations.Test;
import org.testng.Assert;

public class SimpleTest {
    
    @Test
    public void testAddition() {
        System.out.println("TEST IS RUNNING!");
        Assert.assertEquals(2 + 2, 4);
    }
    
    @Test
    public void testString() {
        System.out.println("ANOTHER TEST!");
        Assert.assertEquals("Hello", "Hello");
    }
}