package ru.yandex;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class EmailPage {
    private static final Logger logger = LogManager.getLogger(EmailPage.class);

    public final By EMAIL_LOCATOR_LINK = By.xpath("//a[@data-count='{\"name\":\"logo-service\"}']");
    public final By WRITE_LOCATOR_BUTTON = By.cssSelector("span.mail-ComposeButton-Text");
    public final By RECIPIENT_LOCATOR_TEXTFIELD = By.cssSelector(".tst-field-to  .composeYabbles");
    public final By ATTACHFILE_LOCATOR = By.cssSelector(".ComposeAttachFileButton-FileInput");
    public final By SEND_LOCATOR_BUTTON = By.cssSelector(".ComposeControlPanelButton-Button.ComposeControlPanelButton-Button_action");
    public final By SAVE_TO_DISK_LOCATOR_BUTTON = By.xpath("//a[@title='Сохранить на Диск «random.txt» (20 байт)']");
    public final By BACK_TO_INCOME_MESSG_LINK = By.cssSelector(".ComposeDoneScreen-Link");
    public final By REFRESH_BUTTON_LOCATOR = By.cssSelector(".svgicon-mail--ComposeButton-Refresh");
    private final WebDriver driver;
    private String attachedFileName;

    public EmailPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getEmaillink() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(EMAIL_LOCATOR_LINK));
        return driver.findElement(EMAIL_LOCATOR_LINK);
    }

    public WebElement getWriteButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(WRITE_LOCATOR_BUTTON));
        return driver.findElement(WRITE_LOCATOR_BUTTON);
    }

    public WebElement getRecipientTextField() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".tst-field-to  .composeYabbles")));
        return driver.findElement(RECIPIENT_LOCATOR_TEXTFIELD);
    }

    public WebElement getAttachFileInput() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(ATTACHFILE_LOCATOR));
        return driver.findElement(ATTACHFILE_LOCATOR);
    }

    public WebElement getSendButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(SEND_LOCATOR_BUTTON));
        return driver.findElement(SEND_LOCATOR_BUTTON);
    }

    public WebElement getBackToIncomeMsg() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(BACK_TO_INCOME_MESSG_LINK));
        return driver.findElement(BACK_TO_INCOME_MESSG_LINK);
    }

    public WebElement getRefreshButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(REFRESH_BUTTON_LOCATOR));
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
        new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".progress_timing_linear")));
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
        String xpathOfAttachedFile = "//*[name()='svg' and contains(@class,'Attach-Download_Disk')]/parent::a[contains(@title,'" + attachedFileName + "')]";
        By SELECTOR_OF_ATTACHED_FILE = By.xpath(xpathOfAttachedFile);
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(SELECTOR_OF_ATTACHED_FILE));
        return driver.findElement(SELECTOR_OF_ATTACHED_FILE);
    }

    public void refreshPage() {
        getRefreshButton().click();
    }

    public void saveToDisk() {
        logger.info("Start saving to Disk " + attachedFileName);
        getSaveToDiskButton().click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("iframe.disk-widget-save")));
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe.disk-widget-save")));
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[name()='circle' and @fill='#6C6'] ")));
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
