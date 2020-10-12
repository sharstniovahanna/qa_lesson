import junit.framework.TestListener;
import listners.TestListner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.email_page.EmailPage;
import ru.yandex.email_page.FileCreator;
import ru.yandex.email_page.LoginPage;
import ru.yandex.email_page.SearchPage;
import ru.yandex.yandex_disk.YandexDiskPage;
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
        searchPage.goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithCreds(
                PropertiesManager.getProperty("correct_username"),
                PropertiesManager.getProperty("correct_password")
        );
        EmailPage emailPage = new EmailPage(driver);
        Assert.assertTrue(emailPage.isOpened());
        emailPage.openMail();
        emailPage.setRecipient(
                PropertiesManager.getProperty("correct_recipient")

        );

        emailPage.attachFile(attachedFile);
        emailPage.sendEmail();
        emailPage.returnToIncomeMsg();
        emailPage.refreshPage();
        emailPage.saveToDisk(attachedFile);
        YandexDiskPage yandexDisk = new YandexDiskPage(driver);
        yandexDisk.goToYandexDisk();
        yandexDisk.openDownloadsFolder();
        yandexDisk.clickDownloadedFile(attachedFile);
        yandexDisk.moveFileToGeneralFolder();
        yandexDisk.goToFileSectionOnDisk();
        yandexDisk.dragAndDrop(attachedFile);
        yandexDisk.isFileDeleted();
    }
}



