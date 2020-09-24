import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.yandex.EmailPage;
import ru.yandex.LoginPage;
import ru.yandex.SearchPage;
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
        searchPage.goToMail();
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
