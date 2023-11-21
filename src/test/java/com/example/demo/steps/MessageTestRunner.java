package com.example.demo.steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/feature", glue={"LoginStepDefinitions", "com.example.demo.steps"})
public class MessageTestRunner {
}
