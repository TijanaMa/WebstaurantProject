package com.practice.pages;

import com.practice.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public abstract class DemoBasePage {

    public DemoBasePage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }



    public void navigateModule(String moduleName){

        Driver.getDriver().findElement(By.xpath("//h5[.='"+moduleName+"']/../../..")).click();

    }

}
