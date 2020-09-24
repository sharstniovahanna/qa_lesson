package ru.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private static final By SUBMIT_BUTTON_LOCATOR = By.xpath(".//*[@type='submit']");
    private static final By PASSWORD_FIELD_LOCATOR = By.name("passwd");
    private final By LOGIN_FIELD_LOCATOR = By.name("login");
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getLoginField() {
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.name("login")));
        return driver.findElement(LOGIN_FIELD_LOCATOR);
    }

    public WebElement getSubmitButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@type='submit']")));
        return driver.findElement(SUBMIT_BUTTON_LOCATOR);
    }

    public WebElement getPasswordField() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.name("passwd")));
        return driver.findElement(PASSWORD_FIELD_LOCATOR);
    }

    public void loginWithCreds(String username,String password){
        getLoginField().sendKeys(username);
        getSubmitButton().click();
        getPasswordField().sendKeys(password);
        getSubmitButton().click();

    }

}
