package com.hdstrn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LaptopsPage {

    private WebDriver driver;
    private WebElement showAllManufacturers;
    private WebElement manufacturersScroll;
    private WebElement lenovoCheckbox;
    private WebElement priceFromField;
    private WebElement priceToField;
    private WebElement showBtn;
    private List<WebElement> laptopEntries;

    public LaptopsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement getShowAllManufacturers() {
        return showAllManufacturers;
    }

    public void setShowAllManufacturers(WebElement showAllManufacturers) {
        this.showAllManufacturers = showAllManufacturers;
    }

    public WebElement getManufacturersScroll() {
        return manufacturersScroll;
    }

    public void setManufacturersScroll(WebElement manufacturersScroll) {
        this.manufacturersScroll = manufacturersScroll;
    }

    public WebElement getLenovoCheckbox() {
        return lenovoCheckbox;
    }

    public void setLenovoCheckbox(WebElement lenovoCheckbox) {
        this.lenovoCheckbox = lenovoCheckbox;
    }

    public WebElement getPriceFromField() {
        return priceFromField;
    }

    public void setPriceFromField(WebElement priceFromField) {
        this.priceFromField = priceFromField;
    }

    public WebElement getPriceToField() {
        return priceToField;
    }

    public void setPriceToField(WebElement priceToField) {
        this.priceToField = priceToField;
    }

    public WebElement getShowBtn() {
        return showBtn;
    }

    public void setShowBtn(WebElement showBtn) {
        this.showBtn = showBtn;
    }

    public List<WebElement> getLaptopEntries() {
        return laptopEntries;
    }

    public void setLaptopEntries(List<WebElement> laptopEntries) {
        this.laptopEntries = laptopEntries;
    }
}
