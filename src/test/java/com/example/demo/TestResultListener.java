package com.example.demo;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class TestResultListener implements ITestListener, ISuiteListener {
    
    private int passedTests = 0;
    private int failedTests = 0;
    private int skippedTests = 0;
    
    @Override
    public void onStart(ISuite suite) {
        System.out.println("========== Starting Test Suite: " + suite.getName() + " ==========");
        System.out.println();
    }
    
    @Override
    public void onFinish(ISuite suite) {
        System.out.println();
        System.out.println("========== Test Suite Finished: " + suite.getName() + " ==========");
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + failedTests);
        System.out.println("Skipped: " + skippedTests);
        System.out.println();
    }
    
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Starting test: " + result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        passedTests++;
        System.out.println("✓ PASSED: " + result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        failedTests++;
        System.out.println("✗ FAILED: " + result.getMethod().getMethodName());
        if (result.getThrowable() != null) {
            System.out.println("  Reason: " + result.getThrowable().getMessage());
        }
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        skippedTests++;
        System.out.println("⊘ SKIPPED: " + result.getMethod().getMethodName());
        if (result.getThrowable() != null) {
            System.out.println("  Reason: " + result.getThrowable().getMessage());
        }
    }
}