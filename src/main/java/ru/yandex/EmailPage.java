package ru.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmailPage {
    public final By EMAIL_LOCATOR_LINK = By.xpath("//a[@data-count='{\"name\":\"logo-service\"}']");
    private WebDriver driver;

    public EmailPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getEmaillink() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-count='{\"name\":\"logo-service\"}']")));
        return driver.findElement(EMAIL_LOCATOR_LINK);
    }

    public boolean isOpened() {
        boolean result = false;
        try {
            result = getEmaillink() != null;
        } catch (TimeoutException e) {
            result = false;
        }
        return result;
    }

}
