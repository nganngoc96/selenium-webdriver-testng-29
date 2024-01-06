package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_21_Wait_01_Element_Status {
    WebDriver driver;
    WebDriverWait explicitWait;
    By reconfirmEmailTextbox = By.cssSelector("input[name='reg_email_confirmation__']");
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.facebook.com/");

    }
    @Test
    public void TC_01_Visible() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSecond(2);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("ngan@gmail.com");
        sleepInSecond(3);

        //tại thời điểm này confirm email đang visible
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reconfirmEmailTextbox));

        // Kiểm tra 1 element đang được hiển thị
        Assert.assertTrue(driver.findElement(reconfirmEmailTextbox).isDisplayed());



    }
    @Test
    public void TC_02_Invisible_In_DOM() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSecond(2);

        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSecond(2);
        //Điều kiện 2 - Element ko xuất hiện trên UI và vẫn có trong cây HTML
        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepInSecond(3);

        //tại thời điểm này confirm email đang invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTextbox));

        // Kiểm tra 1 element không được hiển thị
        Assert.assertFalse(driver.findElement(reconfirmEmailTextbox).isDisplayed());


    }


    @Test
    public void TC_02_Invisible_NOT_In_DOM() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSecond(2);

        //click vào icon close để đóng popup lại
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSecond(3);

        //Điều kiện 3 - Element ko xuất hiện trên UI và cũng ko có trong cây HTML

        //tại thời điểm này/ step này thì confirm email đang invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTextbox));

        // Kiểm tra 1 element không được hiển thị
        //Assert.assertFalse(driver.findElement(reconfirmEmailTextbox).isDisplayed());

    }
    @Test
    public void TC_03_Presence() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSecond(2);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("ngan@gmail.com");
        sleepInSecond(3);

        //Điều kiện 1
        //tại thời điểm này confirm email đang present (có trong HTML)
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmailTextbox));

        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepInSecond(3);
        //Điều kiện 2
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmailTextbox));


    }
    @Test
    public void TC_04_Stale() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSecond(2);

        //tại thời điểm này element có xuất hiện và mình sẽ finelenment
        WebElement reconfirmEmail = driver.findElement(reconfirmEmailTextbox);

        //click vào icon close để đóng popup lại
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSecond(3);

        //Điều kiện 3 - Element ko xuất hiện trên UI và cũng ko có trong cây HTML
        explicitWait.until(ExpectedConditions.stalenessOf(reconfirmEmail));


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
