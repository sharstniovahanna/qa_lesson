import help_services.FileCreator;
import listners.TestListner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.email_page.EmailPage;
import ru.yandex.email_page.NewEmailPopUp;
import ru.yandex.login_page.LoginPage;
import ru.yandex.search_page.SearchPage;
import ru.yandex.yandex_disk.YandexDiskPage;
import ru.yandex.yandex_disk.left_menu.DownloadFolder;
import ru.yandex.yandex_disk.left_menu.GeneralFolder;
import uttils.PropertiesManager;

import java.io.File;

@Listeners({io.qameta.allure.testng.AllureTestNg.class, TestListner.class})

public class DocumentSentFunctionTest extends AbstractTest {
    private File attachedFile;

    @BeforeMethod

    public void preSetUp2() {
        attachedFile = FileCreator.create();
    }

    @AfterMethod

    public void teardown2() {
        if (attachedFile != null) {
            attachedFile.delete();
        }
    }

    @Test
    public void positiveLoginTest() {
        SearchPage searchPage = new SearchPage(driver);
        LoginPage loginPage = searchPage.goToLoginPage();
        EmailPage emailPage = loginPage.loginWithCreds(
                PropertiesManager.getProperty("correct_username"),
                PropertiesManager.getProperty("correct_password")
        );
        Assert.assertTrue(emailPage.isOpened());

        NewEmailPopUp emailPopUp = emailPage.openNewMailPopUp();
        emailPopUp.setRecipient(
                PropertiesManager.getProperty("correct_recipient")
        );
        emailPopUp.attachFile(attachedFile);
        emailPopUp.sendEmail();
        emailPage.returnToIncomeMsg();
        emailPage.refreshPage();
        emailPage.saveToDisk(attachedFile);
        YandexDiskPage yandexDisk = emailPage.goToYandexDisk();
        DownloadFolder downloadFolder = yandexDisk.openDownloadsFolder();
        downloadFolder.clickDownloadedFile(attachedFile);
        downloadFolder.moveFileToGeneralFolder();
        GeneralFolder generalFolder = yandexDisk.goToFileSectionOnDisk();
        generalFolder.dragAndDrop(attachedFile);
        generalFolder.isFileDeleted();
    }
}



