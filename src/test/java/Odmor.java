import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Odmor {

    String url = "https://www.odmoruprirodi.rs";
    String uspomeneUrl = "https://odmoruprirodi.rs/uspomene/";
    String commentsUrl = "https://odmoruprirodi.rs/sarene-taljige/#comments";

    @Test
    public void odmorUPrirodi(){
        ChromeDriver driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        WebElement uspomeneTab = driver.findElement(By.id("menu-item-1580"));
        uspomeneTab.click();
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.equals(uspomeneUrl) : "Wrong URL. Expected url is " + uspomeneUrl + " but actual url is " + currentUrl;

        WebElement comentButtonLocator = driver.findElement(By.xpath("//article[@id = '1264']/div/a"));
        comentButtonLocator.click();
        currentUrl = driver.getCurrentUrl();
        assert currentUrl.equals(commentsUrl) : "Wrong URL. Expected url is " + commentsUrl + " but actual url is " + currentUrl;



    }
}
