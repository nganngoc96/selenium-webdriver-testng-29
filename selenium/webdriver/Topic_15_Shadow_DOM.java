package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.print.DocFlavor;
import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_15_Shadow_DOM {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }
    @Test
    public void TC_01_() {
        driver.get("https://automationfc.github.io/shadow-dom/");
        //Bắt shadow hot trước
        WebElement shasdowHostElement = driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext shadowRootContent = shasdowHostElement.getShadowRoot();

        String someText = shadowRootContent.findElement(By.cssSelector("span#shadow_content>span")).getText();
        System.out.println(someText);
        Assert.assertEquals(someText, "some text");

        WebElement checkboxShadow = shadowRootContent.findElement(By.cssSelector("input[type='checkbox']"));
        Assert.assertFalse(checkboxShadow.isSelected());

        List <WebElement> allInput = shadowRootContent.findElements(By.cssSelector("input"));
        System.out.println(allInput.size());


    }
    @Test
    public void TC_02_Shoppe() {
        driver.get("https://shopee.vn/");
        WebElement shadowHostElement = driver.findElement(By.cssSelector("shopee-banner-popup-stateful"));
        SearchContext shadowRootContext = shadowHostElement.getShadowRoot();
        //Verify popup hiển thị

        if (shadowRootContext.findElement(By.cssSelector("div.home-popup__content")).isDisplayed()); {
        // Click để close button
        shadowRootContext.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
        sleepInSecond(2); }

        //Không hiển thị/ đã bị đóng rồi thì qua step search dữ liệu
        driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("iPhone 15");
        sleepInSecond(3);

        driver.findElement(By.cssSelector("button.shopee-searchbar__search-button")).click();
        sleepInSecond(3);

    }
    @Test
    public void TC_03_() {

    }
    @Test
    public void TC_04_() {

    }
    @Test
    public void TC_05_() {

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
