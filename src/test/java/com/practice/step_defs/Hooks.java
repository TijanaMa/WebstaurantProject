package com.practice.step_defs;

import com.practice.utilities.ConfigurationReader;
import com.practice.utilities.DB_Util;
import com.practice.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {


    @Before("@db")
    public void dbHook() {
        System.out.println("creating database connection");
        DB_Util.createConnection();
    }

    @After("@db")
    public void afterDbHook() {
        System.out.println("closing database connection");
        DB_Util.destroy();

    }

    @Before("@ui")
    public void setUp() {

        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().get(ConfigurationReader.getProperty("demo_url"));

    }

    @After("@ui")
    public void tearDown(Scenario scenario) {
        // only takes a screenshot if the scenario fails
        if (scenario.isFailed()) {
            // taking a screenshot
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png","screenshot");
        }
        Driver.closeDriver();
    }



}
