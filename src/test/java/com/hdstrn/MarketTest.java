package com.hdstrn;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

import static org.openqa.selenium.Keys.PAGE_DOWN;

public class MarketTest {

    public static MarketMainPage marketMainPage;
    public static LaptopsPage laptopsPage;
    public static WebDriver driver;
    public static Actions action;
    public static String category;
    public static String subcategory;
    public static String manufacturer;
    public static String priceFrom;
    public static String priceTo;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        category = "Компьютеры";
        subcategory = "Ноутбуки";
        manufacturer = ConfProperties.getProperty("laptopmanufacturer");
        priceFrom = ConfProperties.getProperty("pricefrom");
        priceTo = ConfProperties.getProperty("priceto");
        driver = new ChromeDriver();
        action = new Actions(driver);
        marketMainPage = new MarketMainPage(driver);
        laptopsPage = new LaptopsPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get(ConfProperties.getProperty("marketpage"));
    }

    @Test
    public void marketTest() {
        marketMainPage.setCatalogBtn(driver.findElement(By.id("catalogPopupButton")));
        marketMainPage.getCatalogBtn().click();

        marketMainPage.setComputersCat(
                driver.findElement(By.xpath("//ul[@class = '_2OFlF']"))
                        .findElement(By.xpath("//*[text() = '" + category + "']")));
        action.moveToElement(marketMainPage.getComputersCat()).perform();

        marketMainPage.setLaptops(
                driver.findElement(By.xpath("//*[text() = '" + subcategory + "']")));
        marketMainPage.getLaptops().click();

        laptopsPage.setShowAllManufacturers(
                driver.findElement(By.xpath("//*[text() = 'Показать всё']")));
        laptopsPage.getShowAllManufacturers().click();

        laptopsPage.setManufacturersScroll(
                driver.findElement(By.xpath("//div[@data-test-id = 'virtuoso-scroller']")));
        scrollManufacturers();
        laptopsPage.getLenovoCheckbox().click();

        laptopsPage.setPriceFromField(
                driver.findElements(By.xpath("//input[@class = '_3qxDp']")).get(0));
        laptopsPage.getPriceFromField().sendKeys(priceFrom);

        laptopsPage.setPriceToField(
                driver.findElements(By.xpath("//input[@class = '_3qxDp']")).get(1));
        laptopsPage.getPriceToField().sendKeys(priceTo);

        laptopsPage.setShowBtn(
                driver.findElement(By.xpath("//*[text()[contains(.,'Найден')]]")));
        laptopsPage.getShowBtn().click();

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        laptopsPage.setLaptopEntries(
                driver.findElements(By.xpath("//article[contains(@class, '_2vCnw')]")));

        checkSearchResults();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    public static void scrollManufacturers() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(300));
        while (true) {
            laptopsPage.getManufacturersScroll().sendKeys(PAGE_DOWN);
            try {
                laptopsPage.setLenovoCheckbox(driver.findElement(
                        By.xpath("//*[text() = '" + manufacturer + "']")));
                break;
            } catch (NoSuchElementException e) {
//                System.out.println(manufacturer + " not found");
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public void checkSearchResults() {
        boolean isLenovo = true;
        String entry = "";
        boolean isPriceCorrect = true;
        int price;
        for (WebElement laptopEntry : laptopsPage.getLaptopEntries()) {
            entry = laptopEntry.getText();
            if (!entry.toLowerCase().contains(manufacturer.toLowerCase())) {
                isLenovo = false;
                break;
            }
            price = Integer.parseInt(
                    laptopEntry.findElement(By.xpath("//div[@class = '_2Ycrt']"))
                            .findElement(By.xpath("//div[contains(@class, '_3NaXx')]"))
                            .getText().replaceAll("[ ₽]", ""));

            if (price < Integer.parseInt(priceFrom) || price > Integer.parseInt(priceTo)) {
                isPriceCorrect = false;
                break;
            }
        }
        assert isLenovo : "not Lenovo is in search result: " + entry;
        assert isPriceCorrect : "price is incorrect in search result";
    }
}
