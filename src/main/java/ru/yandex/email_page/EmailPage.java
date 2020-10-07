package ru.yandex.email_page;


import help_services.WaitManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

public class EmailPage {
    private static final By EMAIL_LINK_LOCATOR = By.xpath("//a[@data-count='{\"name\":\"logo-service\"}']");
    private static final By RECIPIENT_TEXTFIELD_LOCATOR = By.cssSelector(".tst-field-to  .composeYabbles");
    private static final By ATTACHFILE_LOCATOR = By.cssSelector(".ComposeAttachFileButton-FileInput");
    private static final By SEND_BUTTON_LOCATOR = By.cssSelector(".ComposeControlPanelButton-Button.ComposeControlPanelButton-Button_action");
    private static final By BACK_TO_INCOME_MESSG_LINK_LOCATOR = By.cssSelector(".ComposeDoneScreen-Link");
    private static final By REFRESH_BUTTON_LOCATOR = By.cssSelector(".svgicon-mail--ComposeButton-Refresh");
    private static final By WRITE_BUTTON_LOCATOR = By.cssSelector("span.mail-ComposeButton-Text");
    private static final By SAVED_TO_DISK_IFRAME_LOCATOR = By.cssSelector("iframe.disk-widget-save");
    private static final By GREEN_CIRCLE_PICTURE_LOCATOR = By.xpath("//*[name()='circle' and @fill='#6C6']");
    private static final String SAVE_TO_DISK_BUTTON_LOCATOR_TEMPLATE = "//*[name()='svg' and contains(@class,'Attach-Download_Disk')]/parent::a[contains(@title,'%s')]";
    private final WebDriver driver;
    private String attachedFileName;
    private static final Logger logger = LogManager.getLogger(EmailPage.class);

    public EmailPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getAttachedFileName() {
        return attachedFileName;
    }

    public WebElement getEmaillink() {
        new WaitManager(driver).waitUntilpresenceOfElementLocated(EMAIL_LINK_LOCATOR);
        return driver.findElement(EMAIL_LINK_LOCATOR);
    }

    public WebElement getWriteButton() {
        new WaitManager(driver).waitUntilpresenceOfElementLocated(WRITE_BUTTON_LOCATOR);
        return driver.findElement(WRITE_BUTTON_LOCATOR);
    }

    public WebElement getRecipientTextField() {
        new WaitManager(driver).waitUntilpresenceOfElementLocated(RECIPIENT_TEXTFIELD_LOCATOR);
        return driver.findElement(RECIPIENT_TEXTFIELD_LOCATOR);
    }

    public WebElement getAttachFileInput() {
        new WaitManager(driver).waitUntilpresenceOfElementLocated(ATTACHFILE_LOCATOR);
        return driver.findElement(ATTACHFILE_LOCATOR);
    }

    public WebElement getSendButton() {
        new WaitManager(driver).waitUntilpresenceOfElementLocated(SEND_BUTTON_LOCATOR);
        return driver.findElement(SEND_BUTTON_LOCATOR);
    }

    public WebElement getBackToIncomeMsg() {
        new WaitManager(driver).waitUntilpresenceOfElementLocated(BACK_TO_INCOME_MESSG_LINK_LOCATOR);
        return driver.findElement(BACK_TO_INCOME_MESSG_LINK_LOCATOR);
    }

    public WebElement getRefreshButton() {
        new WaitManager(driver).waitUntilpresenceOfElementLocated(REFRESH_BUTTON_LOCATOR);
        return driver.findElement(REFRESH_BUTTON_LOCATOR);
    }


    public void openMail() {
        getWriteButton().click();
    }

    public void setRecipient(String emailAddress) {
        getRecipientTextField().sendKeys(emailAddress);
    }

    public void attachFile() {
        File file = FileCreator.create();
        String absolutePath = file.getAbsolutePath();
        getAttachFileInput().sendKeys(absolutePath);
        new WaitManager(driver).waitUntilInvisibilityOfElementLocated(By.cssSelector(".progress_timing_linear"));
        attachedFileName = file.getName();
        file.delete();
    }

    public void sendEmail() {
        getSendButton().click();
    }

    public void returnToIncomeMsg() {
        getBackToIncomeMsg().click();
    }

    public WebElement getSaveToDiskButton() {
        String xpathOfAttachedFile = String.format(SAVE_TO_DISK_BUTTON_LOCATOR_TEMPLATE,attachedFileName);
        By selectorOfAttachedFile = By.xpath(xpathOfAttachedFile);
        new WaitManager(driver).waitUntilpresenceOfElementLocated(selectorOfAttachedFile);
        return driver.findElement(selectorOfAttachedFile);
    }

    public void refreshPage() {
        getRefreshButton().click();
    }

    public void saveToDisk() {
        logger.info("Start saving to Disk " + attachedFileName);
        getSaveToDiskButton().click();
        new WaitManager(driver).waitUntilpresenceOfElementLocated(SAVED_TO_DISK_IFRAME_LOCATOR);
        driver.switchTo().frame(driver.findElement(SAVED_TO_DISK_IFRAME_LOCATOR));
        new WaitManager(driver).waitUntilpresenceOfElementLocated(GREEN_CIRCLE_PICTURE_LOCATOR);
        driver.switchTo().parentFrame();
    }

    public boolean isOpened() {

        boolean result = false;
        try {
            result = getEmaillink() != null;
        } catch (TimeoutException e) {
            logger.error("Page was not opened");
            result = false;
        }
        logger.info("Page was opened");
        return result;
    }
}
