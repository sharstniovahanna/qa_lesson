package ru.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {
    private WebDriver driver;
    private  static final By ENTER_EMAIL_BUTTON_LOCATOR = By.cssSelector(".desk-notif-card__card > a.button");


    public SearchPage(WebDriver driver){
        this.driver = driver;

    }
    public WebElement getEnterEmailButton(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".desk-notif-card__card > a.button")));
        return driver.findElement(ENTER_EMAIL_BUTTON_LOCATOR);
    }


}
