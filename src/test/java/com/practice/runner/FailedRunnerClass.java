package com.practice.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "com/practice/step_defs",
        features = "@target/rerun.txt"
)
public class FailedRunnerClass {
}
