package ru.yandex.email_page;


import help_services.AbstractPage;
import help_services.WaitManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

public class EmailPage extends AbstractPage {
    private static final By EMAIL_LINK_LOGO_LOCATOR = By.xpath("//a[@data-count='{\"name\":\"logo-service\"}']");
    private static final By BACK_TO_INCOME_MESSG_LINK_LOCATOR = By.cssSelector(".ComposeDoneScreen-Link");
    private static final By REFRESH_BUTTON_LOCATOR = By.cssSelector(".svgicon-mail--ComposeButton-Refresh");
    private static final By SAVED_TO_DISK_IFRAME_LOCATOR = By.cssSelector("iframe.disk-widget-save");
    private static final By GREEN_CIRCLE_PICTURE_LOCATOR = By.xpath("//*[name()='circle' and @fill='#6C6']");
    private static final String SAVE_TO_DISK_BUTTON_LOCATOR_TEMPLATE = "//*[name()='svg' and contains(@class,'Attach-Download_Disk')]/parent::a[contains(@title,'%s')]";
    private static final By WRITE_BUTTON_LOCATOR = By.cssSelector("span.mail-ComposeButton-Text");
    private static final Logger logger = LogManager.getLogger(EmailPage.class);


    public EmailPage(WebDriver driver) {
        super.driver = driver;
    }







    public WebElement getBackToIncomeMsg() {
        new WaitManager(driver).waitUntilPresenceOfElementLocated(BACK_TO_INCOME_MESSG_LINK_LOCATOR);
        return driver.findElement(BACK_TO_INCOME_MESSG_LINK_LOCATOR);
    }

    public WebElement getRefreshButton() {
        new WaitManager(driver).waitUntilPresenceOfElementLocated(REFRESH_BUTTON_LOCATOR);
        return driver.findElement(REFRESH_BUTTON_LOCATOR);
    }
    public WebElement getWriteButton() {
        new WaitManager(driver).waitUntilPresenceOfElementLocated(WRITE_BUTTON_LOCATOR);
        return driver.findElement(WRITE_BUTTON_LOCATOR);
    }

    public NewEmailPopUp openNewMailPopUp() {
        getWriteButton().click();
        return new NewEmailPopUp(driver);
    }


    public void returnToIncomeMsg() {
        getBackToIncomeMsg().click();
    }

    public WebElement getSaveToDiskButton(File file) {
        String xpathOfAttachedFile = String.format(SAVE_TO_DISK_BUTTON_LOCATOR_TEMPLATE, file.getName());
        By selectorOfAttachedFile = By.xpath(xpathOfAttachedFile);
        new WaitManager(driver).waitUntilPresenceOfElementLocated(selectorOfAttachedFile);
        return driver.findElement(selectorOfAttachedFile);
    }

    public void refreshPage() {
        getRefreshButton().click();
    }

    public void saveToDisk(File file) {
        logger.info("Start saving to Disk " + file.getName());
        getSaveToDiskButton(file).click();
        new WaitManager(driver).waitUntilPresenceOfElementLocated(SAVED_TO_DISK_IFRAME_LOCATOR);
        driver.switchTo().frame(driver.findElement(SAVED_TO_DISK_IFRAME_LOCATOR));
        new WaitManager(driver).waitUntilPresenceOfElementLocated(GREEN_CIRCLE_PICTURE_LOCATOR);
        driver.switchTo().parentFrame();
    }

    public boolean isOpened() {
        try {
            new WaitManager(driver).waitUntilPresenceOfElementLocated(EMAIL_LINK_LOGO_LOCATOR);
            return true;
        } catch (TimeoutException e) {
            logger.error("Page was not opened");
            return false;
        }

    }
}
