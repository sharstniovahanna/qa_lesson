package help_services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionClass extends AbstractPage {

    public ActionClass(WebDriver driver) {
        super.driver = driver;
    }

    public void dragAndDropBy(WebElement sourcelocator, WebElement destinationlocator) {
        new Actions(driver).dragAndDrop(sourcelocator, destinationlocator).build().perform();
    }

}
