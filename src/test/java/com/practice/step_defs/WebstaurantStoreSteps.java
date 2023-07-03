package com.practice.step_defs;

import com.practice.pages.WebstaurantStore_page;
import com.practice.utilities.BrowserUtil;
import com.practice.utilities.ConfigurationReader;
import com.practice.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.*;
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

        int pageNum = 2;
        boolean wordFound = true;

        while (true) {

            for (WebElement eachTitle : webstaurantStore.productTitle) {

                wait.until(ExpectedConditions.visibilityOf(eachTitle));
                actions.moveToElement(eachTitle).perform();
                String title = eachTitle.getText();
                if(!(title.contains(keyword))){
                    wordFound=false;
                    break; //exit if keyword in each is not found
                }
            }
            if (!wordFound) { // exiting if word is not found
                break;
            }



            try {
                    //it will select next page
                    webstaurantStore.nextPageButtonMethod(pageNum);

                    wait.until(ExpectedConditions.visibilityOfAllElements(webstaurantStore.productTitle));

                    //increment page number in every iteration
                    pageNum++;


            } catch (NoSuchElementException e) {
                //when it throws exceptions means there is no more pages, we came to last page
                System.out.println("last page");
                try{
                    //locate and go to last page
                    webstaurantStore.lastPageButtonMethod(pageNum);
                }catch (NoSuchElementException last){
                    last.printStackTrace();
                    System.out.println("no more pages");
                    break;  //exit while loop , no more pages
                }

            }


            wait.until(ExpectedConditions.visibilityOfAllElements(webstaurantStore.productTitle));
            //BrowserUtil.waitFor(1);
        }


        //assert all titles contain keyword
        assertTrue("not all titles contain the keyword '" +keyword+ "' ", wordFound);



    }


    @When("user add the last item to the cart")
    public void user_add_the_last_item_to_the_cart() {


        WebElement addLastToCart = webstaurantStore.addToCartButtons.get(webstaurantStore.addToCartButtons.size()-1);
      //  addLastToCart.click();

        WebElement proba = webstaurantStore.addCelokupno.get(webstaurantStore.addCelokupno.size()-1);
        try {
            proba.click(); //try to add last to cart
        }catch (ElementNotInteractableException e){
            System.out.println("Product out of stock:");
            // if it's out of stock it will give exception and print which item is out of stock
            webstaurantStore.productTitle.get(webstaurantStore.productTitle.size()-1).getText();
            try {
               addLastToCart.click(); //add last item in cart
            }catch (NoSuchElementException addToCart){
                addToCart.printStackTrace();
            }
        }

        //div[@class='add-to-cart']//p[.='Out of Stock']

        //verify message -  there is 1 item in cart

    }
    @When("user empty the cart")
    public void user_empty_the_cart() {
        //locate cart, empty cart
    }
    @Then("the cart should be empty")
    public void the_cart_should_be_empty() {
        //assertion
    }


}
