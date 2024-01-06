package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_17_Window_Tab {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @Test
    public void TC_01_Basic_Form() {

        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSecond(3);

        //Switch qua trang google
        switchToWindowByTitle("Google");

        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("selenium");
         sleepInSecond(3);

        //   Switch qua trang basic form
        switchToWindowByTitle("Selenium WebDriver");

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSecond(3);

        switchToWindowByTitle("Selenium WebDriver");

        }




    @Test
    public void TC_02_() {

    }

    @Test
    public void TC_03_() {

    }
    @Test
    public void TC_04_() {

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

    public void switchToWindowByID(String expectedID) {
        //Lấy ra hết tất cả tab/window ID

        Set<String> allIDs = driver.getWindowHandles();

        //Dùng vòng lặp duyệt qua từng ID trong SET ở trên
        for (String id : allIDs) {
            if (!id.equals(expectedID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public void switchToWindowByTitle(String expectedTitle) {
        //Lấy hết tất cả ID của các window/tab
        Set<String> allIDs = driver.getWindowHandles();

        //Dùng vòng lặp duyệt qua set ID ở trên
        for (String id : allIDs) {
            //switch vào từng ID trước
            driver.switchTo().window(id);

            //Lấy ra title của tab/window hiện tại
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(expectedTitle)) {
                break;
            }
        }
    }
}
