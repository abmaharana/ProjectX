package com.example.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class DriverFactory {
    private static WebDriver driver;
//    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger LOGGER = Logger.getLogger(DriverFactory.class.getName());

    public static WebDriver getDriver() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        if (driver == null) {
            String executionEnv = Config.getExecutionEnv();//System.getProperty("execution", "local");
            String hubHost = System.getProperty("HUB_HOST", "selenium-hub");
            String remoteUrl = hubHost.contains(".") ? "http://" + hubHost + ":4444/wd/hub" : "http://selenium-hub:4444/wd/hub";

            if (executionEnv.equalsIgnoreCase("docker")) {
                try {
                    LOGGER.info("Attempting to connect to Selenium Hub at: " + remoteUrl);
                    options.addArguments("--headless");
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
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                driver = new ChromeDriver(options);

            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String dirPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "screenshots" + File.separator;
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs(); // create folder if not exists
        }
        String filePath = dirPath + screenshotName.replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".png";
        try {
            Thread.sleep(2000);
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(filePath));
            System.out.println("Screenshot saved at: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

    public static void quitDriver() {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
    }
}
