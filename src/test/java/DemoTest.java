import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemoTest {

    String loginURL = "https://www.saucedemo.com/";
    String homePageURL = "https://www.saucedemo.com/inventory.html";
    String userName = "standard_user";
    String userNameWrong = "standard_user12";
    String password = "secret_sauce";
//    ChromeDriver driver;

//    @After
//    public void endSelenium (){
//        driver.quit();
//    }

    @Test
    public void positiveLoginTest() {
        ChromeDriver driver = new ChromeDriver();
        String url = loginToHomePage(driver);
        assert url.equals(homePageURL) : "User is not logged in. Expected URL is " + homePageURL + " but actual URL is: " + url;
        driver.quit();
    }
    @Test
    public void negativeLoginTest (){
        ChromeDriver driver = new ChromeDriver();
        String url = loginToHomePage (driver);
        assert ! url.equals(homePageURL) : "User is logged in, but it's expected to not. Expected URL is " + loginURL + " but actual URL is: " + url;
        driver.quit();
    }

    @Test
    public void addItemToCart (){
        ChromeDriver driver = new ChromeDriver();
        String url = loginToHomePage(driver);
        assert url.equals(homePageURL) : "User is not logged in. Expected URL is " + homePageURL + " but actual URL is: " + url;
        WebElement addToCartBackpack = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartBackpack.click();
        WebElement removeButton = driver.findElement(By.id("remove-sauce-labs-backpack"));
        String text = removeButton.getText();
        assert text.equals("REMOVE") : "Text is wrong. Expected text is 'REMOVE', but actual is " + text;
//        Assert.assertTrue("Text is wrong. Expected text is 'Remove', but actual is " + text, text.equals("Remove"));
    }

    public String loginToHomePage (ChromeDriver driver){
        driver.get(loginURL);
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys(userName);
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(password);
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        String currentUrl = driver.getCurrentUrl();
        return currentUrl;
    }
}
