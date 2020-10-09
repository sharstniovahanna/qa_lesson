package ru.yandex.email_page;

import help_services.AbstractPage;
import help_services.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends AbstractPage {
    private static final By SUBMIT_BUTTON_LOCATOR = By.xpath(".//*[@type='submit']");
    private static final By PASSWORD_FIELD_LOCATOR = By.name("passwd");
    private static final By LOGIN_FIELD_LOCATOR = By.name("login");


    public LoginPage(WebDriver driver) {
        super.driver = driver;
    }

    public WebElement getLoginField() {
        new WaitManager(driver).waitUntilpresenceOfElementLocated(LOGIN_FIELD_LOCATOR);
        return driver.findElement(LOGIN_FIELD_LOCATOR);
    }

    public WebElement getSubmitButton() {
        new WaitManager(driver).waitUntilpresenceOfElementLocated(SUBMIT_BUTTON_LOCATOR);
        return driver.findElement(SUBMIT_BUTTON_LOCATOR);
    }

    public WebElement getPasswordField() {
        new WaitManager(driver).waitUntilpresenceOfElementLocated(PASSWORD_FIELD_LOCATOR);
        return driver.findElement(PASSWORD_FIELD_LOCATOR);
    }

    public void loginWithCreds(String username, String password) {
        getLoginField().sendKeys(username);
        getSubmitButton().click();
        getPasswordField().sendKeys(password);
        getSubmitButton().click();
    }

}
