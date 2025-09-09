package com.example.tests.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.example.tests.steps","com.example.base"},
        plugin= {"pretty",
                "json:target/cucumber-reports/cucumber.json",
                "html:target/cucumber-reports/cucumber-html-report"},
        tags = "@smoke",
        monochrome = true
)
public class TestNGCucumberRunnerTest extends AbstractTestNGCucumberTests {
}
