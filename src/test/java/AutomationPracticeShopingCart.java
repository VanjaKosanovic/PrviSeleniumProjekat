import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AutomationPracticeShopingCart {

    String url = "http://automationpractice.com/index.php";
    ChromeDriver driver;

    @Test
    public void sortOfItems (){
        driver = new ChromeDriver();
        driver.get(url);
        WebElement womanTab = driver.findElement(By.xpath("//a[@title = 'Women']"));
        womanTab.click();

        List<WebElement> listaProzivoda = driver.findElements(By.xpath("//li[contains(@class, 'ajax_block_product')]"));
        String priceUnsorted = listaProzivoda.get(0).findElement(By.xpath("//span[@class = 'price product-price']")).getText();

//        WebElement dropdown = driver.findElement(By.xpath("//select[@id='selectProductSort']"));
//        Select sortSelect = new Select(dropdown);
//        sortSelect.selectByValue("price:asc");

        WebElement topsMenu = driver.findElement(By.xpath("//div[@class= 'block_content']//ul[@class='tree dynamized']/li[1]"));
        WebElement xButtonTops = driver.findElement(By.xpath("//span[@class = 'grower CLOSE']"));
        xButtonTops.click();
        WebElement tShirts = topsMenu.findElement(By.xpath("//a[text()='T-shirts']/parent::li"));

        WebElement checkbox = driver.findElement(By.xpath("//ul[@id='ul_layered_category_0']/li//input[@type = 'checkbox'][1]"));
        checkbox.click();

        WebElement parentCheckbox = checkbox.findElement(By.xpath("/parent::span"));

        if(parentCheckbox.getAttribute("class").contains("checked")){
            System.out.println("Checkbox is checked");
        }


    }
}
