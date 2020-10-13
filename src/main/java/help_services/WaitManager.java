package help_services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitManager extends AbstractPage {

    public WaitManager(WebDriver driver) {
        super.driver = driver;
    }

    public void waitUntilPresenceOfElementLocated(By locator) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitUntilInvisibilityOfElementLocated(By locator) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitUntilElementToBeClickable(By locator) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(locator));
    }

}
