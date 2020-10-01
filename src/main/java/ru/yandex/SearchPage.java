package ru.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class SearchPage {
    private static final By ENTER_EMAIL_BUTTON_LOCATOR = By.cssSelector(".desk-notif-card__card > a.button");
    private WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToMail() {

        Set<String> handle1 = driver.getWindowHandles();
        getEnterEmailButton().click();
        Set<String> handle2 = driver.getWindowHandles();
        handle2.removeAll(handle1);
        Object[] array = handle2.toArray();
        driver.switchTo().window((String) array[0]);
    }

    private WebElement getEnterEmailButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".desk-notif-card__card > a.button")));
        return driver.findElement(ENTER_EMAIL_BUTTON_LOCATOR);
    }

}
