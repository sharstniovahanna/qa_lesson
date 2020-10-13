import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import uttils.PropertiesManager;

public class AbstractTest {
    protected WebDriver driver;

    @BeforeMethod
    public void preSetUp(ITestContext context) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize(); //need for MAC
        driver.get(PropertiesManager.getProperty("base_url"));
        context.setAttribute("driver", driver);

    }

    @AfterMethod
    public void teardown() {
        driver.close();
        driver.quit();


    }
}
