package com.example.tests.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.example.tests.steps"},
        plugin= {"pretty", "json:target/cucumber.json",
                "json:target/cucumber-reports/cucumber.runtime.formatter.JSONFormatter"},
        tags = "@smoke",
        monochrome = true
)
public class TestNGCucumberRunnerTest extends AbstractTestNGCucumberTests {
}
