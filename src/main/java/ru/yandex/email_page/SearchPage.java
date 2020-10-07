package ru.yandex.email_page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class SearchPage {
    private static final By ENTER_EMAIL_BUTTON_LOCATOR = By.cssSelector(".desk-notif-card__card > a.button");
    private final WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToLoginPage() {

        Set<String> handle1 = driver.getWindowHandles();
        getEnterEmailButton().click();
        Set<String> handle2 = driver.getWindowHandles();
        handle2.removeAll(handle1);
        Object[] array = handle2.toArray();
        driver.switchTo().window((String) array[0]);
    }

    public WebElement getEnterEmailButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(ENTER_EMAIL_BUTTON_LOCATOR));
        return driver.findElement(ENTER_EMAIL_BUTTON_LOCATOR);
    }

}
