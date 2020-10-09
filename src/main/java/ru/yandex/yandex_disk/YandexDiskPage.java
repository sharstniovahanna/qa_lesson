package ru.yandex.yandex_disk;

import help_services.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.email_page.EmailPage;

import java.util.Set;

public class YandexDiskPage extends AbstractPage {
    public final By OPEN_DISK_LOCATOR_LINK = By.xpath("//span[text()='Диск']");
    public final By DOWNLOADS_FOLDER_BUTTON = By.cssSelector("a.navigation__link_downloads");
    EmailPage name;
//    private final String downloadedFileName = name.getAttachedFileName();

    public YandexDiskPage(WebDriver driver) {
        super.driver = driver;
    }

 /*   public WebElement getDownloadedFileButton() {
        String xpathOfDownloadedFile = "//span[contains(@title, '" + downloadedFileName + "')]";
        By DOWNLOADED_FILE_LOCATOR_BUTTON = By.xpath(xpathOfDownloadedFile);
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(DOWNLOADED_FILE_LOCATOR_BUTTON));
        return driver.findElement(DOWNLOADED_FILE_LOCATOR_BUTTON);
    }
*/  /*  public void clickDownloadedFile() {
        getDownloadedFileButton().click();
    }*/


    public WebElement getOpenDiskLink() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(OPEN_DISK_LOCATOR_LINK));
        return driver.findElement(OPEN_DISK_LOCATOR_LINK);
    }

    public WebElement getDownloadsFolderButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(DOWNLOADS_FOLDER_BUTTON));
        return driver.findElement(DOWNLOADS_FOLDER_BUTTON);
    }

    public void goToYandexDisk() {

        Set<String> handle1 = driver.getWindowHandles();
        getOpenDiskLink().click();
        Set<String> handle2 = driver.getWindowHandles();
        handle2.removeAll(handle1);
        Object[] array = handle2.toArray();
        driver.switchTo().window((String) array[0]);
    }

    public void openDownloadsFolder() {
        getDownloadsFolderButton().click();
    }

}
