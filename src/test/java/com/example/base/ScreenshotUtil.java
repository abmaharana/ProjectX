package com.example.base;

import org.openqa.selenium.WebDriver;
import org.testng.*;

public class ScreenshotUtil implements ITestListener {


    @Override
    public void onTestFailure(ITestResult result) {
        try {
            WebDriver driver = DriverFactory.getDriver();
            if (result.getStatus() == ITestResult.FAILURE) {
                // Take screenshot if the test fails
                DriverFactory.captureScreenshot(driver, result.getName());
            }
        }catch(Exception e){
                e.printStackTrace();
        }
        //DriverFactory.quitDriver();
    }
}
