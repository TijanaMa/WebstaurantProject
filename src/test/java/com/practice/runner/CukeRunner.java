package com.practice.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(


        plugin = {
                "html:target/cucumber-report.html",
                "rerun:target/rerun.txt" ,
                "json:target/cucumber.json"
        },
        glue = "com/practice/step_defs",
        features = "src/test/resources/features",
        tags ="@smoke",
        dryRun = false

)

public class CukeRunner { }
