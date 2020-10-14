package ru.yandex.yandex_disk.left_menu.sections;

import help_services.AbstractPage;
import help_services.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

public class DownloadFolder extends AbstractPage {
    private static final String DOWNLOADED_FILE_LOCATOR = "//span[contains(@title, '%s')]/../../parent::div[contains(@class, \"js-prevent-deselect\")]";
    private final By MOVE_BUTTON_LOCATOR = By.cssSelector(".groupable-buttons__visible-button_name_move");
    private final By CONFIRMATION_MOVE_BUTTON_LOCATOR = By.cssSelector(".modal_visible_yes .confirmation-dialog__button_submit");
    private final By FOLDER_TREE_ELEMENT_LOCATOR = By.cssSelector(".modal_visible_yes .select-folder-dialog__tree");


    public DownloadFolder(WebDriver driver) {
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


    public WebElement getMoveButton() {
        new WaitManager(driver).waitUntilPresenceOfElementLocated(MOVE_BUTTON_LOCATOR);
        return driver.findElement(MOVE_BUTTON_LOCATOR);
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


}
