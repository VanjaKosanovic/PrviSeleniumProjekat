import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemoTest {

    @Test
    public void DemoTest() {

        String loginURL = "https://www.saucedemo.com/";
        String userName = "standard_user";
        String password = "secret_sauce";

        ChromeDriver driver = new ChromeDriver();

        driver.get(loginURL);
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys(userName);
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(password);
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
//        driver.quit();
    }
}
