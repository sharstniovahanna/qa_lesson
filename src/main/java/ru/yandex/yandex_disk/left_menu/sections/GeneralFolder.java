package ru.yandex.yandex_disk.left_menu.sections;

import help_services.AbstractPage;
import help_services.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;

public class GeneralFolder extends AbstractPage {
    public GeneralFolder(WebDriver driver) {
        super.driver = driver;
    }

    private static final String DOWNLOADED_FILE_LOCATOR = "//span[contains(@title, '%s')]/../../parent::div[contains(@class, \"js-prevent-deselect\")]";
    private final By TRASH_DRAG_AND_DROP = By.xpath("//span[contains(@class, \"file-icon_dir_trash\") or contains(@class, \"file-icon_dir_trash-full\")]/../../parent::div[contains(@class, \"js-prevent-drag\")]");
    private final By FILE_REMOVED_POP_UP_LOCATOR = By.cssSelector(".notifications__item_moved");


    public WebElement getTrash() {
        new WaitManager(driver).waitUntilPresenceOfElementLocated(TRASH_DRAG_AND_DROP);
        return driver.findElement(TRASH_DRAG_AND_DROP);
    }

    public WebElement getDownloadedFileButton(File file) {
        String xpathOfDownloadedFile = String.format(DOWNLOADED_FILE_LOCATOR, file.getName());
        By DOWNLOADED_FILE_LOCATOR_BUTTON = By.xpath(xpathOfDownloadedFile);
        new WaitManager(driver).waitUntilPresenceOfElementLocated(DOWNLOADED_FILE_LOCATOR_BUTTON);
        return driver.findElement(DOWNLOADED_FILE_LOCATOR_BUTTON);
    }

    public void dragAndDrop(File file) {
        WebElement source = getDownloadedFileButton(file);
        WebElement destination = getTrash();
        new Actions(driver).dragAndDrop(source, destination).build().perform();
    }

    public boolean isFileDeleted() {
        try {
            new WaitManager(driver).waitUntilPresenceOfElementLocated(FILE_REMOVED_POP_UP_LOCATOR);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

}
