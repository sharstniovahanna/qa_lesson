import uttils.PropertiesManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.email_page.EmailPage;
import ru.yandex.email_page.LoginPage;
import ru.yandex.email_page.SearchPage;
import ru.yandex.yandex_disk.YandexDiskPage;

public class DocumentSentFunctionTest {
    private WebDriver driver;

    @BeforeMethod
    public void preSetUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(PropertiesManager.getProperty("base_url"));

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
        emailPage.attachFile();
        emailPage.sendEmail();
        emailPage.returnToIncomeMsg();
        emailPage.refreshPage();
        emailPage.saveToDisk();
        YandexDiskPage yandexDisk = new YandexDiskPage(driver);
        yandexDisk.goToYandexDisk();
        yandexDisk.openDownloadsFolder();
//        yandexDisk.clickDownloadedFile();

    }

    @AfterMethod
    public void teardown() {
        driver.close();
        driver.quit();
    }
}



