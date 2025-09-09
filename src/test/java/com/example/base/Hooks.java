package com.example.base;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.net.MalformedURLException;

public class Hooks {
    private WebDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        driver = DriverFactory.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) throws MalformedURLException {
        if (scenario.isFailed()) {
            // Capture screenshot in bytes
            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            // Attach to Cucumber report
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        DriverFactory.quitDriver();
    }
}
