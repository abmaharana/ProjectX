package com.example.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public class DriverFactory {
    private static WebDriver driver;
    private static final Logger LOGGER = Logger.getLogger(DriverFactory.class.getName());

    public static WebDriver getDriver() throws MalformedURLException {
        if (driver == null) {
            String executionEnv = Config.getExecutionEnv();//System.getProperty("execution", "local");
            String hubHost = System.getProperty("HUB_HOST", "selenium-hub");
            String remoteUrl = hubHost.contains(".") ? "http://" + hubHost + ":4444/wd/hub" : "http://selenium-hub:4444/wd/hub";

            LOGGER.info("Attempting to connect to Selenium Hub at: " + remoteUrl);

            ChromeOptions options = new ChromeOptions();

            if (executionEnv.equalsIgnoreCase("docker")) {
                try {
                    options.addArguments("--headless-new");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--remote-allow-origins=*");
                    options.addArguments("--ignore-certificate-errors");
                    options.addArguments("--allow-insecure-localhost"); // Allow insecure localhost connections
                    options.addArguments("--disable-web-security"); // Disable same-origin policy (use with caution)
                    driver = new RemoteWebDriver(new URL(remoteUrl), options);
                    LOGGER.info("Successfully connected to Selenium Hub");
                } catch (Exception e) {
                    LOGGER.severe("Failed to connect to Selenium Hub: " + e.getMessage());
                    throw e;
                }
            } else {
                driver = new ChromeDriver(options);
            }

            driver.manage().window().maximize();
        }
        return driver;
    }


    @AfterTest
    public static void quitDriver() {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
    }
}
