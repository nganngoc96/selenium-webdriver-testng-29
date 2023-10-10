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

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_08_Default_Dropdown {
    WebDriver driver;

    String fistName = "Kevin", lastName = "Lamping", emailAddress = getEmailAddress();
    String companyName = "Selenium WebDriver", password = "123456";
    String day = "15", month = "November", year = "1950";

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");

    }

    @Test
    public void TC_01_Register() {
        driver.findElement(By.cssSelector("a.ico-register")).click();

        driver.findElement(By.cssSelector("input#FirstName")).sendKeys(fistName);
        driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);

        //Day Dropdown
        Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
        //Chon ngay
        day.selectByVisibleText(this.day);
        //Verify dropdown này là single (ko phai multiple)
        Assert.assertFalse(day.isMultiple());
        //Verify so luong item
        List<WebElement> dayOption = day.getOptions();
        Assert.assertEquals(dayOption.size(), 32);

        new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
        new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);

        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#Company")).sendKeys(companyName);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);

        driver.findElement(By.cssSelector("button#register-button")).click();
        sleepInSecond(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");


    }

    @Test
    public void TC_02_Login() {
        driver.get("https://demo.nopcommerce.com/");
        //Login
        driver.findElement(By.cssSelector("a.ico-login")).click();

        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);

        driver.findElement(By.cssSelector("button.login-button")).click();
        sleepInSecond(3);


        //Verify
        driver.findElement(By.cssSelector("a.ico-account")).click();
        sleepInSecond(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"),fistName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"),lastName);


        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(),day);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(),month);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(),year);


        Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"),emailAddress);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"),companyName);



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

    public String getEmailAddress() {
        Random rand = new Random();
        return "kevinlamp" + rand.nextInt(9999) + "@gmail.com";

    }
}

