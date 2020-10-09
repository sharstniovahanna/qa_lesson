package ru.yandex.yandex_disk;

import help_services.AbstractPage;
import help_services.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.Set;

public class YandexDiskPage extends AbstractPage {
    public final By OPEN_DISK_LINK_LOCATOR = By.xpath("//span[text()='Диск']");
    public final By DOWNLOADS_FOLDER_BUTTON_LOCATOR = By.cssSelector("a.navigation__link_downloads");
    private final By MOVE_BUTTON_LOCATOR = By.cssSelector(".groupable-buttons__visible-button_name_move");
    private final By CONFIRMATION_MOVE_BUTTON_LOCATOR = By.xpath("//div[@class='confirmation-dialog__footer']//span[@class='button2__text'and contains(text(), 'Переместить')]");

    public YandexDiskPage(WebDriver driver) {
        super.driver = driver;
    }

    public WebElement getDownloadedFileButton(File file) {
        String xpathOfDownloadedFile = "//span[contains(@title, '" + file.getName() + "')]";
        By DOWNLOADED_FILE_LOCATOR_BUTTON = By.xpath(xpathOfDownloadedFile);
        new WaitManager(driver).waitUntilpresenceOfElementLocated(DOWNLOADED_FILE_LOCATOR_BUTTON);
        return driver.findElement(DOWNLOADED_FILE_LOCATOR_BUTTON);
    }

    public void clickDownloadedFile(File file) {
        getDownloadedFileButton(file).click();
    }

    public WebElement getOpenDiskLink() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(OPEN_DISK_LINK_LOCATOR));
        return driver.findElement(OPEN_DISK_LINK_LOCATOR);
    }

    public WebElement getDownloadsFolderButton() {
        new WaitManager(driver).waitUntilpresenceOfElementLocated(DOWNLOADS_FOLDER_BUTTON_LOCATOR);
        return driver.findElement(DOWNLOADS_FOLDER_BUTTON_LOCATOR);
    }

    public WebElement getMoveButton(){
        new WaitManager(driver).waitUntilpresenceOfElementLocated(MOVE_BUTTON_LOCATOR);
        return driver.findElement(MOVE_BUTTON_LOCATOR);
    }

    public WebElement getConfirmationMoveButton(){
        new WaitManager(driver).waitUntilpresenceOfElementLocated(CONFIRMATION_MOVE_BUTTON_LOCATOR);
        return driver.findElement(CONFIRMATION_MOVE_BUTTON_LOCATOR);
    }


    public void goToYandexDisk() {

        Set<String> handle1 = driver.getWindowHandles();
        getOpenDiskLink().click();
        Set<String> handle2 = driver.getWindowHandles();
        handle2.removeAll(handle1);
        Object[] array = handle2.toArray();
        driver.switchTo().window((String) array[0]);
    }

    public void moveFileToGeneralFolder(){
        getMoveButton().click();
        getConfirmationMoveButton().click();
        new WaitManager(driver).waitUntilpresenceOfElementLocated(By.cssSelector(".notifications__item_moved"));
    }

    public void openDownloadsFolder() {
        getDownloadsFolderButton().click();
    }

}
