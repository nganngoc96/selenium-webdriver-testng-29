package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_16_Frame_IFrame_Java {
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
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();
        sleepInSecond(5);

        WebElement formIFrame = driver.findElement(By.cssSelector("div#formTemplateContainer>iframe"));
        Assert.assertTrue(formIFrame.isDisplayed());

        //switch vào frame/ iframe trước khi thao tác với element bên trong
        // có 3 cách

//        driver.switchTo().frame(0);
//
//        driver.switchTo().frame("");
//
//        driver.switchTo().frame("");
        driver.switchTo().frame(formIFrame);

        //không tìm thấy element (element nằm trong iframe)

        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        sleepInSecond(3);

        //Switch ra lại trang A
        driver.switchTo().defaultContent();
        // thao tác vs 1 element bên ngoài iframe Trang A
        driver.findElement(By.cssSelector("nav.header--desktop-floater a.menu-item-login")).click();

        driver.findElement(By.cssSelector("button#login")).click();
        sleepInSecond(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(),"Username and password are both required.");









    }
    @Test
    public void TC_02_() {

    }
    @Test
    public void TC_03_() {

        driver.get("https://netbanking.hdfcbank.com/netbanking/");

        driver.switchTo().frame("login_page");

        driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("luss_phuong");
        sleepInSecond(3);

        driver.findElement(By.cssSelector("a.login-btn")).click();
        sleepInSecond(5);

        //driver.switchTo().defaultContent();

        Assert.assertTrue(driver.findElement(By.cssSelector("input#keyboard")).isDisplayed());



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
