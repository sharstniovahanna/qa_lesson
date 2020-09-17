import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        searchPage.getEnterEmailButton().click();

        Set<String> handle2 = driver.getWindowHandles();
        handle2.removeAll(handle1);
        Object[] array = handle2.toArray();
        driver.switchTo().window((String) array[0]);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.getLoginField().sendKeys("sharstniovaH");
        loginPage.getSubmitButton().click();
        loginPage.getPasswordField().sendKeys("123456!");
        loginPage.getSubmitButton().click();



        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"nb-1\"]/body/div[2]/div[5]/div/div[2]/div/div/header/div/div[1]/a[2]")));
        if(driver.findElement(By.xpath("//*[@id=\"nb-1\"]/body/div[2]/div[5]/div/div[2]/div/div/header/div/div[1]/a[2]"))!= null){
            System.out.println("Test passed");
        }else{
            System.out.println("Test failed");
        }




    }
}
