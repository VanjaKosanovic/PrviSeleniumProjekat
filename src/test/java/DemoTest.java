import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

public class DemoTest {

    String loginURL = "https://www.saucedemo.com/";
    String homePageURL = "https://www.saucedemo.com/inventory.html";
    String userName = "standard_user";
    String userNameWrong = "standard_user12";
    String performanceUser = "performance_glitch_user";
    String password = "secret_sauce";
    ChromeDriver driver;

    @After
    public void endSelenium (){
        driver.quit();
    }

    @Test
    public void positiveLoginTest() {
        driver = new ChromeDriver();
        String url = loginToHomePage(driver, userName);
        assert url.equals(homePageURL) : "User is not logged in. Expected URL is " + homePageURL + " but actual URL is: " + url;
        driver.quit();
    }

    @Test
    public void negativeLoginTest() {
        driver = new ChromeDriver();
        String url = loginToHomePage(driver, userName);
        assert !url.equals(homePageURL) : "User is logged in, but it's expected to not. Expected URL is " + loginURL + " but actual URL is: " + url;
        driver.quit();
    }

    @Test
    public void addItemToCart() {
        driver = new ChromeDriver();
        String url = loginToHomePage(driver, userName);
        assert url.equals(homePageURL) : "User is not logged in. Expected URL is " + homePageURL + " but actual URL is: " + url;
        WebElement addToCartBackpack = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartBackpack.click();
        WebElement removeButton = driver.findElement(By.id("remove-sauce-labs-backpack"));
        String text = removeButton.getText();
        assert text.equals("REMOVE") : "Text is wrong. Expected text is 'REMOVE', but actual is " + text;
//        Assert.assertTrue("Text is wrong. Expected text is 'Remove', but actual is " + text, text.equals("Remove"));
    }

    @Test
    public void addtoCartAndRemoveFromCart() {
        driver = new ChromeDriver();
        String currentUrl = loginToHomePage(driver, userName);

        WebElement addToCartButton = driver.findElementById("add-to-cart-sauce-labs-backpack");
        addToCartButton.click();
        addToCartButton = driver.findElementById("add-to-cart-sauce-labs-bike-light");
        addToCartButton.click();

        WebElement removeFromCartButton = driver.findElementById("remove-sauce-labs-backpack");
        Assert.assertTrue("The actual text is " + removeFromCartButton.getText() + " The expected result is REMOVE", (removeFromCartButton.getText().equals("REMOVE")));

        WebElement cartIcon = driver.findElementById("shopping_cart_container");
        String cartIconBadge = cartIcon.getText();
        Assert.assertTrue("The Cart Icon Badge isn't right.", cartIconBadge.equals("2"));
        cartIcon.click();

        WebElement itemName1 = driver.findElementById("item_4_title_link");
        WebElement itemName2 = driver.findElementById("item_0_title_link");
        Assert.assertTrue("Expected result is to have a product 'Sauce Labs Backpack' and 'Sauce Labs Bike Light' in a Cart. " + "Actual result is there is a product: " + itemName1.getText() + " and a product: " + itemName2.getText(), itemName1.getText().equals("Sauce Labs Backpack") && itemName2.getText().equals("Sauce Labs Bike Light"));

        WebElement removeFromCart = driver.findElementById("remove-sauce-labs-bike-light");
        removeFromCart.click();
        Assert.assertFalse("Artical is not deleted from a Cart.", isElementPresent(itemName2));

        driver.close();
    }

    @Test
    public void sortListOfElements() throws InterruptedException {
        driver = new ChromeDriver();
        String currentUrl = loginToHomePage(driver, userName);
        assert currentUrl.equals(homePageURL) : "User is not logged in. Expected URL is " + homePageURL + " but actual URL is: " + currentUrl;

        Thread.sleep(10000);
        List<WebElement> listaProizvoda = driver.findElements(By.xpath("//div[@class = 'inventory_item']"));
        String priceUnsorted = listaProizvoda.get(0).findElement(By.xpath("//div[@class = 'inventory_item_price']")).getText();

        WebElement dropdown = driver.findElement(By.xpath("//select[@data-test='product_sort_container']"));
        Select sort = new Select(dropdown);
        sort.selectByValue("hilo");


        Thread.sleep(10000);
        String priceSorted = listaProizvoda.get(0).findElement(By.xpath("//div[@class = 'inventory_item_price']")).getText();

        assert !priceSorted.equals(priceUnsorted) : "Sorted and unsorted prices should be different. New sorted price is: " +priceSorted;

        WebElement addToChartButton = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));

    }

    public String loginToHomePage(ChromeDriver driver, String userName) {
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

    public boolean isElementPresent(WebElement locator) {
        try {
            return locator.isDisplayed();
        } catch (final Exception e) {
            return false;
    }
}

    public void waitUntilClickable (int timeOutInSeconds, WebElement webElement, WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void waitUntilVisible (int timeOutInSeconds, WebElement webElement, WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
}
