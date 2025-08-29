package com.example.base;

import io.cucumber.java.*;
import java.net.MalformedURLException;

public class Hooks {
    @Before
    public void setUp() throws MalformedURLException {
        DriverFactory.getDriver();
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
