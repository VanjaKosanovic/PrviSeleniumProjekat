import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ToolsQADemo {

    String framesURL = "https://demoqa.com/frames";
    String tabsURL = "https://demoqa.com/browser-windows";
    String formURL = "https://demoqa.com/automation-practice-form";
    String alertURL = "https://demoqa.com/alerts";
    ChromeDriver driver;

    @Test
    public void testIFrame(){
        openPageInSpecificChromeDriver(framesURL);
        WebElement iFrame = driver.findElement(By.id("frame1"));
        driver.switchTo().frame(iFrame);
        WebElement header = driver.findElement(By.id("sampleHeading"));
        System.out.println(header.getText());
        driver.quit();
    }

    @Test
    public void testNewTab () throws InterruptedException {
        openPageInSpecificChromeDriver(tabsURL);
        WebElement newTabButton = driver.findElement(By.id("tabButton"));
        waitUntilClickable(2, newTabButton, driver);
        newTabButton.click();
        ArrayList <String> tabs = new ArrayList (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(10000);
        WebElement headerNewTab = driver.findElement(By.id("sampleHeading"));
        System.out.println(headerNewTab.getText());
        driver.switchTo().window(tabs.get(0));
        Thread.sleep(10000);
        driver.quit();
    }

    @Test
    public void testNewWindow() throws InterruptedException {
        openPageInSpecificChromeDriver(tabsURL);
        WebElement newWindowButton = driver.findElement(By.id("windowButton"));
        waitUntilClickable(2, newWindowButton, driver);
        newWindowButton.click();
        String parentWindow= driver.getWindowHandle();
        List <String> allWindows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(allWindows.get(1));
        WebElement headerNewTab = driver.findElement(By.id("sampleHeading"));
        System.out.println(headerNewTab.getText());

        for(String x : allWindows) {
            driver.switchTo().window(x);
        }
        driver.switchTo().window(parentWindow);
        driver.quit();
    }

    @Test
    public void testForm(){
        openPageInSpecificChromeDriver(formURL);
        WebElement firstNameInput = driver.findElement(By.id("firstName"));
        firstNameInput.sendKeys("Pera");
        WebElement lastNameInput = driver.findElementById("lastName");
        lastNameInput.sendKeys("Peric");
        WebElement emailInput = driver.findElement(By.id("userEmail"));
        emailInput.sendKeys("pera.peric@gmail.com");
        WebElement genderRadioButton1 = driver.findElement(By.xpath("//input[@value='Male']/parent::div"));
        waitUntilClickable(5, genderRadioButton1, driver);
        genderRadioButton1.click();
        WebElement mobileInput = driver.findElement(By.id("userNumber"));
        mobileInput.sendKeys("123456789");
        WebElement musicCheckBox = driver.findElement(By.xpath("//input[@id = 'hobbies-checkbox-3']/parent::div"));
        WebElement sportCheckBox = driver.findElement(By.xpath("//input[@id = 'hobbies-checkbox-1']/parent::div"));
        musicCheckBox.click();

        WebElement uploadPictureButton = driver.findElement(By.xpath("//input[@id= 'uploadPicture']"));
        waitUntilClickable(3, uploadPictureButton, driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement submitButton = driver.findElement(By.id("submit"));
        js.executeScript("arguments[0].scrollIntoView(true);", submitButton);

        Actions builder = new Actions(driver);
        builder.moveToElement(uploadPictureButton).click().build().perform();
        String directoryPath = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        String path1 = directoryPath + separator + "src" + separator + "qa.jpg";
        String path = directoryPath + "\\src\\qa.jpg";
        uploadPictureButton.sendKeys(path);
        submitButton.click();
        driver.quit();
    }

    @Test
    public void testAlert () throws InterruptedException {
        openPageInSpecificChromeDriver(alertURL);
        WebElement alertButton = driver.findElement(By.id("alertButton"));
        alertButton.click();
        driver.switchTo().alert().accept();
        Thread.sleep(5000);
        WebElement alertConfirm = driver.findElement(By.id("confirmButton"));
        alertConfirm.click();
        driver.switchTo().alert().dismiss();
        WebElement alertPrompt = driver.findElement(By.id("promtButton"));
        alertPrompt.click();
        driver.switchTo().alert().sendKeys("QA Test");
        driver.quit();
    }

    public void waitUntilClickable (int timeOutInSeconds, WebElement webElement, WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public ChromeDriver openPageInSpecificChromeDriver (String url){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-popup-blocking");
        options.addArguments("incognito");
        driver = new ChromeDriver(options);
        driver.get(url);
        return driver;
    }

}
