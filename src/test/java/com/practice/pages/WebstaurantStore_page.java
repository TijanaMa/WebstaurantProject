package com.practice.pages;

import com.practice.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WebstaurantStore_page {

    public WebstaurantStore_page(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "searchval")
    public WebElement searchBox;

    @FindBy(xpath = "(//button[@value='Search'])[1]")
    public WebElement searchButton;

    @FindBy(xpath = "//span[contains(@class, 'block') and contains(text(), 'Table')]")
    public List<WebElement> productTitle;

    //WebElement for checking the next page
//    @FindBy(xpath = "//a[@aria-label='page 2']")
//    public WebElement nextPageButton;

    public void nextPageButtonMethod(int pageNum){

        Driver.getDriver().findElement(By.xpath("//a[@aria-label='page "+pageNum+"']")).click();

    }


}
