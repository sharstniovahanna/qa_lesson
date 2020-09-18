import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.EmailPage;
import ru.yandex.LoginPage;
import ru.yandex.SearchPage;

public class Runner {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver.get("https://yandex.by/");

        SearchPage searchPage = new SearchPage(driver);
        searchPage.goToMail();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithCreds("sharstniovaH", "123456!");

        EmailPage emailPage = new EmailPage(driver);
        if (emailPage.isOpened()) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failled");
        }


    }
}
