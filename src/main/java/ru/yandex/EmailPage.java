package ru.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmailPage {
    public final By EMAIL_LOCATOR_LINK = By.xpath("//a[@data-count='{\"name\":\"logo-service\"}']");
    public final By WRITE_LOCATOR_BUTTON = By.cssSelector("span.mail-ComposeButton-Text");
    public final By RECIPIENT_LOCATOR_TEXTFIELD = By.cssSelector(".tst-field-to  .composeYabbles");
    public final By ATTACHFILE_LOCATOR = By.cssSelector(".ComposeAttachFileButton-FileInput");
    public final By SEND_LOCATOR_BUTTON = By.cssSelector(".ComposeControlPanelButton-Button.ComposeControlPanelButton-Button_action");
    public final By SAVE_TO_DISK_LOCATOR_BUTTON = By.xpath("//a[@title='Сохранить на Диск «random.txt» (20 байт)']");
    private WebDriver driver;

    public EmailPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getEmaillink() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-count='{\"name\":\"logo-service\"}']")));
        return driver.findElement(EMAIL_LOCATOR_LINK);
    }

    public WebElement getWriteButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.mail-ComposeButton-Text")));
        return driver.findElement(WRITE_LOCATOR_BUTTON);
    }

    public WebElement getRecipientTextField() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".tst-field-to  .composeYabbles")));
        return driver.findElement(RECIPIENT_LOCATOR_TEXTFIELD);
    }

    public WebElement getAttachFileInput(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(ATTACHFILE_LOCATOR));
        return driver.findElement(ATTACHFILE_LOCATOR);
    }

    public WebElement getSendButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(SEND_LOCATOR_BUTTON));
        return driver.findElement(SEND_LOCATOR_BUTTON);
    }

    public void openMail() {
        getWriteButton().click();
    }

    public void setRecipient(String emailAddress) {
        getRecipientTextField().sendKeys(emailAddress);
    }

    public void attachFile(){
        FileCreator.create("random.txt");
        getAttachFileInput().sendKeys("/Users/hsharstniova/IdeaProjects/qa_lesson/random.txt");
        getSendButton().click();
    }

    public WebElement getSaveToDiskButton(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(SAVE_TO_DISK_LOCATOR_BUTTON));
        return driver.findElement(SAVE_TO_DISK_LOCATOR_BUTTON);
    }

    public void saveToDisk(){
        getSaveToDiskButton().click();

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
