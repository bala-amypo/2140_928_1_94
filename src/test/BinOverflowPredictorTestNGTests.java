package com.example.demo;

import org.testng.annotations.Test;
import org.testng.Assert;

public class BinOverflowPredictorTestNGTests {
    
    @Test
    public void test1() {
        System.out.println("Test 1 running...");
        Assert.assertTrue(true);
    }
    
    @Test  
    public void test2() {
        System.out.println("Test 2 running...");
        Assert.assertEquals(1 + 1, 2);
    }
    
    @Test
    public void test3() {
        System.out.println("Test 3 running...");
        Assert.assertNotNull("Hello");
    }
}