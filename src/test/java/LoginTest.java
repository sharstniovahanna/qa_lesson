import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.email_page.EmailPage;
import ru.yandex.email_page.LoginPage;
import ru.yandex.email_page.SearchPage;

@Listeners(io.qameta.allure.testng.AllureTestNg.class)
public class LoginTest {
    private WebDriver driver;

    @BeforeMethod
    public void preSetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver.get("https://yandex.by/");
    }

    @Test
    public void positiveLoginTest() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithCreds("sharstniovaH", "123456!");
        EmailPage emailPage = new EmailPage(driver);

        Assert.assertTrue(emailPage.isOpened());

    }

    @AfterMethod
    public void teardown() {
        driver.close();
        driver.quit();
    }

}
