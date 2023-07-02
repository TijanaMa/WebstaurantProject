package com.practice.pages;

import com.practice.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebstaurantStore_homePage {

    public WebstaurantStore_homePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "searchval")
    public WebElement searchBox;

    @FindBy(xpath = "(//button[@value='Search'])[1]")
    public WebElement searchButton;

}
