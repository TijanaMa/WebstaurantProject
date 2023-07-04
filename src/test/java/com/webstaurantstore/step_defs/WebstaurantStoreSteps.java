package com.webstaurantstore.step_defs;

import com.webstaurantstore.pages.WebstaurantStore_page;
import com.webstaurantstore.utilities.ConfigurationReader;
import com.webstaurantstore.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
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

            for (WebElement eachTitle : webstaurantStore.productTitleContains) {

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
                    // select next page
                    webstaurantStore.nextPageButtonMethod(pageNum);
                    wait.until(ExpectedConditions.visibilityOfAllElements(webstaurantStore.productTitleContains));

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


            wait.until(ExpectedConditions.visibilityOfAllElements(webstaurantStore.productTitleContains));
        }


        //assert all titles contain keyword
        assertTrue("not all titles contain the keyword '" +keyword+ "' ", wordFound);



    }


    @When("user add the last item to the cart")
    public void user_add_the_last_item_to_the_cart() {
        wait = new WebDriverWait(Driver.getDriver(), 20);


        //adding last available product
        WebElement addLastAvailableItemToCart = webstaurantStore.addToCartButtons.get(webstaurantStore.addToCartButtons.size()-1);
        wait.until(ExpectedConditions.elementToBeClickable(addLastAvailableItemToCart));
        addLastAvailableItemToCart.click();



        //verify notification showed - item is added to cart
        WebElement notificationAddedToCart = Driver.getDriver().findElement(By.xpath("//h2[@class='notification__heading']"));
        wait.until(ExpectedConditions.visibilityOf(notificationAddedToCart));
        Assert.assertTrue("notification not present, item not added to cart",notificationAddedToCart.isDisplayed());

        //verify  -  there is only 1 item in cart
        WebElement viewCart = Driver.getDriver().findElement(By.xpath("//a[.='View Cart']"));
        viewCart.click();

        List<WebElement> itemsInCart = Driver.getDriver().findElements(By.xpath("//li//div[@class='cartItem ag-item gtm-product-auto ']"));
        wait.until(ExpectedConditions.visibilityOfAllElements(itemsInCart));

        int itemsCount = itemsInCart.size();
        System.out.println("count of items present in cart: "+itemsCount);

        Assert.assertEquals("there is more then 1 item in cart",1,itemsCount);



    }


    @Then("user empty the cart and the cart should be empty")
    public void userEmptyTheCartAndTheCartShouldBeEmpty() {

        WebElement emptyCartButton = Driver.getDriver().findElement(By.xpath("//button[normalize-space()='Empty Cart']"));
        emptyCartButton.click();
        WebElement emptyCartPopUp = Driver.getDriver().findElement(By.xpath("//div//button[text()='Empty']"));
        emptyCartPopUp.click();

        //verify Cart is empty
        WebElement emptyCartMessage = Driver.getDriver().findElement(By.xpath("//p[.='Your cart is empty.']"));

        Assert.assertTrue(emptyCartMessage.isDisplayed());



    }
}
