package com.practice.step_defs;

import com.practice.pages.DemoBasePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Demo_steps extends DemoBasePage {

    @Given("user go to {string}")
    public void user_go_to(String moduleName) {

        navigateModule(moduleName);

    }
    @Given("user goes to {string} within the module")
    public void user_goes_to_within_the_module(String iframeTab) {

    }
    @Then("user is successfully locating different WebElements within the module")
    public void user_is_successfully_locating_different_web_elements_within_the_module() {

    }


}
