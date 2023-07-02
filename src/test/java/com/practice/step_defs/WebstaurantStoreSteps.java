package com.practice.step_defs;

import com.practice.pages.WebstaurantStore_homePage;
import com.practice.utilities.ConfigurationReader;
import com.practice.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WebstaurantStoreSteps {


    WebstaurantStore_homePage webstaurantStore = new WebstaurantStore_homePage();



    @Given("user is on the Webstaurant Store website")
    public void user_is_on_the_webstaurant_store_website() {
         Driver.getDriver().get(ConfigurationReader.getProperty("webstaurantStore_url"));
    }
    @When("user search for {string}")
    public void user_search_for_word(String string) {
       // webstaurantStore.searchBox.click();
        webstaurantStore.searchBox.sendKeys(string);
        webstaurantStore.searchButton.click();
    }
    @Then("user should see search results has word {string} in the title")
    public void user_should_see_search_results_has_word_in_the_title(String string) {



    }
    @When("user add the last item to the cart")
    public void user_add_the_last_item_to_the_cart() {

    }
    @When("user empty the cart")
    public void user_empty_the_cart() {

    }
    @Then("the cart should be empty")
    public void the_cart_should_be_empty() {

    }


}
