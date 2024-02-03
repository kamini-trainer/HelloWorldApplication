import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjectmodel.LoginPage;

public class LoginTest {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/kaminikamal/Downloads/chromedriver_mac64/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.get("https://example.com/login"); // put your localhost url

        LoginPage loginPage = new LoginPage(driver);

        // Perform login actions
        loginPage.enterUsername("yourUsername");
        loginPage.enterPassword("yourPassword");
        loginPage.clickLoginButton();

        // Other test steps can be added here

        driver.quit();
    }
}
