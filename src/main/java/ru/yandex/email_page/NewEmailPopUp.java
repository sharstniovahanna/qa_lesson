package ru.yandex.email_page;

import help_services.AbstractPage;
import help_services.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

public class NewEmailPopUp extends AbstractPage {
    private static final By RECIPIENT_TEXTFIELD_LOCATOR = By.cssSelector(".tst-field-to  .composeYabbles");
    private static final By ATTACHFILE_LOCATOR = By.cssSelector(".ComposeAttachmentSourcesMenu-FileInput");
    private static final By PROGRESS_BAR_OF_ATTACHING_FILE_LOCATOR = By.cssSelector(".progress_timing_linear");
    private static final By SEND_BUTTON_LOCATOR = By.cssSelector(".ComposeControlPanelButton-Button.ComposeControlPanelButton-Button_action");

    public NewEmailPopUp(WebDriver driver) {
        super.driver = driver;
    }

    public WebElement getSendButton() {
        new WaitManager(driver).waitUntilPresenceOfElementLocated(SEND_BUTTON_LOCATOR);
        return driver.findElement(SEND_BUTTON_LOCATOR);
    }


    public WebElement getRecipientTextField() {
        new WaitManager(driver).waitUntilPresenceOfElementLocated(RECIPIENT_TEXTFIELD_LOCATOR);
        return driver.findElement(RECIPIENT_TEXTFIELD_LOCATOR);
    }

    public WebElement getAttachFileInput() {
        new WaitManager(driver).waitUntilPresenceOfElementLocated(ATTACHFILE_LOCATOR);
        return driver.findElement(ATTACHFILE_LOCATOR);
    }


    public void setRecipient(String... emailAddress) {
        getRecipientTextField().sendKeys(emailAddress);
    }


    public void attachFile(File file) {
        String absolutePath = file.getAbsolutePath();
        getAttachFileInput().sendKeys(absolutePath);
        new WaitManager(driver).waitUntilInvisibilityOfElementLocated(PROGRESS_BAR_OF_ATTACHING_FILE_LOCATOR);
    }

    public void sendEmail() {
        getSendButton().click();
    }

}