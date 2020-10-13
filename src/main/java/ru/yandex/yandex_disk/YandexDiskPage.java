package ru.yandex.yandex_disk;

import help_services.AbstractPage;
import help_services.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.Set;

public class YandexDiskPage extends AbstractPage {
    public final By DOWNLOADS_FOLDER_BUTTON_LOCATOR = By.cssSelector("a.navigation__link_downloads");
    private final By MOVE_BUTTON_LOCATOR = By.cssSelector(".groupable-buttons__visible-button_name_move");
    private final By CONFIRMATION_MOVE_BUTTON_LOCATOR = By.cssSelector(".modal_visible_yes .confirmation-dialog__button_submit");
    private final By FILE_BUTTON_LOCATOR = By.cssSelector(".navigation__link_current");
    private final By TRASH_DRAG_AND_DROP = By.xpath("//span[contains(@class, \"file-icon_dir_trash\") or contains(@class, \"file-icon_dir_trash-full\")]/../../parent::div[contains(@class, \"js-prevent-drag\")]");
    private final By FILE_REMOVED_POP_UP_LOCATOR = By.cssSelector(".notifications__item_moved");
    private final By FOLDER_TREE_ELEMENT_LOCATOR = By.cssSelector(".modal_visible_yes .select-folder-dialog__tree");
    private static final String DOWNLOADED_FILE_LOCATOR = "//span[contains(@title, '%s')]/../../parent::div[contains(@class, \"js-prevent-deselect\")]";

    public YandexDiskPage(WebDriver driver) {
        super.driver = driver;
    }

    public WebElement getDownloadedFileButton(File file) {

        String xpathOfDownloadedFile = String.format(DOWNLOADED_FILE_LOCATOR, file.getName());
        By DOWNLOADED_FILE_LOCATOR_BUTTON = By.xpath(xpathOfDownloadedFile);
        new WaitManager(driver).waitUntilPresenceOfElementLocated(DOWNLOADED_FILE_LOCATOR_BUTTON);
        return driver.findElement(DOWNLOADED_FILE_LOCATOR_BUTTON);
    }

    public void clickDownloadedFile(File file) {
        getDownloadedFileButton(file).click();
    }



    public WebElement getDownloadsFolderButton() {
        new WaitManager(driver).waitUntilPresenceOfElementLocated(DOWNLOADS_FOLDER_BUTTON_LOCATOR);
        return driver.findElement(DOWNLOADS_FOLDER_BUTTON_LOCATOR);
    }

    public WebElement getMoveButton() {
        new WaitManager(driver).waitUntilPresenceOfElementLocated(MOVE_BUTTON_LOCATOR);
        return driver.findElement(MOVE_BUTTON_LOCATOR);
    }


    public WebElement getFileButton() {
        return driver.findElement(FILE_BUTTON_LOCATOR);
    }

    public WebElement getConfirmationMoveButton() {
        new WaitManager(driver).waitUntilPresenceOfElementLocated(CONFIRMATION_MOVE_BUTTON_LOCATOR);
        return driver.findElement(CONFIRMATION_MOVE_BUTTON_LOCATOR);
    }



    public void moveFileToGeneralFolder() {
        getMoveButton().click();
        new WaitManager(driver).waitUntilPresenceOfElementLocated(FOLDER_TREE_ELEMENT_LOCATOR);
        new WaitManager(driver).waitUntilElementToBeClickable(CONFIRMATION_MOVE_BUTTON_LOCATOR);
        getConfirmationMoveButton().click();
    }

    public WebElement getTrash() {
        new WaitManager(driver).waitUntilPresenceOfElementLocated(TRASH_DRAG_AND_DROP);
        return driver.findElement(TRASH_DRAG_AND_DROP);
    }

    public void dragAndDrop(File file) {
        WebElement source = getDownloadedFileButton(file);
        WebElement destination = getTrash();
        new Actions(driver).dragAndDrop(source, destination).build().perform();
    }

    public void openDownloadsFolder() {
        getDownloadsFolderButton().click();
    }

    public void goToFileSectionOnDisk() {
        getFileButton().click();
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


