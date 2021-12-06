import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class KontikiTest {

    String url = "https://https://www.kontiki.rs/";
    ChromeDriver driver;

    @After
    public void endSelenium (){
        driver.quit();
    }

    public void rezervisiNovuGodinu(){
        driver = new ChromeDriver();
        driver.get(url);
        WebElement novaGodinaMenuItem = driver.findElement(By.xpath("//ul[@id='jetmenu']//a[@id = 'ctl00_cphHeader_ctl03_rptMenu_ctl02_hylMenu']"));
        Actions hover = new Actions(driver);
        hover.moveToElement(novaGodinaMenuItem);


    }
}
