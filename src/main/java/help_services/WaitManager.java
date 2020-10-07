package help_services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitManager {
    private final WebDriver driver;

    public WaitManager(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilpresenceOfElementLocated(By locator){
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
