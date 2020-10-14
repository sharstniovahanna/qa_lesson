package ru.yandex.yandex_disk.left_menu;

import help_services.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.yandex_disk.YandexDiskPage;
import ru.yandex.yandex_disk.left_menu.sections.DownloadFolder;
import ru.yandex.yandex_disk.left_menu.sections.GeneralFolder;

public class LeftMenu extends YandexDiskPage {
    public final By DOWNLOADS_FOLDER_BUTTON_LOCATOR = By.cssSelector("a.navigation__link_downloads");
    private final By FILE_BUTTON_LOCATOR = By.cssSelector(".navigation__link_current");

    public LeftMenu(WebDriver driver) {
        super(driver);
    }

    public WebElement getDownloadsFolderButton() {
        new WaitManager(driver).waitUntilPresenceOfElementLocated(DOWNLOADS_FOLDER_BUTTON_LOCATOR);
        return driver.findElement(DOWNLOADS_FOLDER_BUTTON_LOCATOR);
    }

    public WebElement getFileButton() {
        return driver.findElement(FILE_BUTTON_LOCATOR);
    }


    public DownloadFolder openDownloadsFolder() {
        getDownloadsFolderButton().click();
        return new DownloadFolder(driver);
    }

    public GeneralFolder goToFileSectionOnDisk() {
        getFileButton().click();
        return new GeneralFolder(driver);
    }

}

