import Uttils.PropertiesManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.EmailPage;
import ru.yandex.LoginPage;
import ru.yandex.SearchPage;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DocumentSentFunctionTest {
    private WebDriver driver;
    @BeforeMethod
    public void preSetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver.get(PropertiesManager.getProperty("base_url"));
    }

    @Test
    public void positiveLoginTest() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.goToMail();
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
        emailPage.attachFile();





    }

    @AfterMethod
    public void teardown() {
        driver.close();
        driver.quit();
    }
}



