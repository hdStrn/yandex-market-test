package com.hdstrn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MarketMainPage {

    private WebDriver driver;
    private WebElement catalogBtn;
    private WebElement computersCat;
    private WebElement laptops;

    public MarketMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement getCatalogBtn() {
        return catalogBtn;
    }

    public WebElement getComputersCat() {
        return computersCat;
    }

    public WebElement getLaptops() {
        return laptops;
    }

    public void setCatalogBtn(WebElement catalogBtn) {
        this.catalogBtn = catalogBtn;
    }

    public void setComputersCat(WebElement computersCat) {
        this.computersCat = computersCat;
    }

    public void setLaptops(WebElement laptops) {
        this.laptops = laptops;
    }
}
