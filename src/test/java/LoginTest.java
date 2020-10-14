import listners.TestListner;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.email_page.EmailPage;
import ru.yandex.login_page.LoginPage;
import ru.yandex.search_page.SearchPage;
import uttils.PropertiesManager;

@Listeners({io.qameta.allure.testng.AllureTestNg.class, TestListner.class})
public class LoginTest extends AbstractTest {

    @Test
    public void positiveLoginTest() {
        SearchPage searchPage = new SearchPage(driver);
        LoginPage loginPage = searchPage.goToLoginPage();
        EmailPage emailPage = loginPage.loginWithCreds(
                PropertiesManager.getProperty("correct_username"),
                PropertiesManager.getProperty("correct_password")
        );
        Assert.assertTrue(emailPage.isOpened());
    }


}
