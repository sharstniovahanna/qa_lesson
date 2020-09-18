import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.EmailPage;
import ru.yandex.LoginPage;
import ru.yandex.SearchPage;

import java.util.Set;

public class Runner {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver.get("https://yandex.by/");
        Set<String> handle1 = driver.getWindowHandles();

        SearchPage searchPage = new SearchPage(driver);
        searchPage.goToMail();

        Set<String> handle2 = driver.getWindowHandles();
        handle2.removeAll(handle1);
        Object[] array = handle2.toArray();
        driver.switchTo().window((String) array[0]);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithCreds("sharstniovaH","123456!");

        EmailPage emailPage = new EmailPage(driver);
        if (emailPage.isOpened()){
            System.out.println("Test passed");
        }
        else {
            System.out.println("Test failled");
        }






    }
}
