package com.practice.step_defs;

import com.practice.pages.WebstaurantStore_page;
import com.practice.utilities.BrowserUtil;
import com.practice.utilities.ConfigurationReader;
import com.practice.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class WebstaurantStoreSteps {


    WebstaurantStore_page webstaurantStore = new WebstaurantStore_page();
    WebDriverWait wait;
    Actions actions;



    @Given("user is on the Webstaurant Store website")
    public void user_is_on_the_webstaurant_store_website() {
         Driver.getDriver().navigate().to(ConfigurationReader.getProperty("webstaurantStore_url"));
    }
    @When("user search for {string}")
    public void user_search_for_word(String string) {
       // webstaurantStore.searchBox.click();
        webstaurantStore.searchBox.sendKeys(string);
        webstaurantStore.searchButton.click();
    }
    @Then("user should see search results has word {string} in the title")
    public void user_should_see_search_results_has_word_in_the_title(String keyword) {

        actions = new Actions(Driver.getDriver());
        wait = new WebDriverWait(Driver.getDriver(), 10);

        boolean wordFound = true;
        int pageNum = 2;

        while (true) {
            wordFound = true;
            for (WebElement eachTitle : webstaurantStore.productTitle) {
                actions.moveToElement(eachTitle).perform();
                wait.until(ExpectedConditions.visibilityOf(eachTitle));
                String title = eachTitle.getText();
                if(!(title.contains(keyword))){
                    wordFound=false;
                    break; //exit if in each is not found
                }
            }
            if (!wordFound) { // exiting if word is not found in any title
                break;
            }



            try {
                webstaurantStore.nextPageButtonMethod(pageNum);
                pageNum++;
                wait.until(ExpectedConditions.stalenessOf(webstaurantStore.productTitle.get(0)));
                //wait until previous page WebElement becomes Stale
            } catch (NoSuchElementException e) {
                e.printStackTrace();
                System.out.println("No more next pages");
                break; //exit while loop if no next pages
            }

           // wordFound=false; // reset for next page

            BrowserUtil.waitFor(1);
        }


        assertTrue("all titles contains keyword '" +keyword+ "' ", wordFound);


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
