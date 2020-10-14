package ru.yandex.search_page;

import help_services.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.login_page.LoginPage;

import java.util.Set;

public class SearchPage extends AbstractPage {
    private static final By ENTER_EMAIL_BUTTON_LOCATOR = By.cssSelector(".desk-notif-card__card > a.button");

    public SearchPage(WebDriver driver) {
        super.driver = driver;
    }

    public LoginPage goToLoginPage() {
        Set<String> handle1 = driver.getWindowHandles();
        getEnterEmailButton().click();
        Set<String> handle2 = driver.getWindowHandles();
        handle2.removeAll(handle1);
        Object[] array = handle2.toArray();
        driver.switchTo().window((String) array[0]);
        return new LoginPage(driver);
    }

    public WebElement getEnterEmailButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(ENTER_EMAIL_BUTTON_LOCATOR));
        return driver.findElement(ENTER_EMAIL_BUTTON_LOCATOR);
    }

}
