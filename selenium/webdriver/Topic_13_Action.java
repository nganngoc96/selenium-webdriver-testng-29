package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Topic_13_Action {
    WebDriver driver;
    Actions actions;
    String fullName;


    public String getFullName(String fullName) {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @Test
    public void TC_01_Hover_Tooltip() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        actions.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();

        WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));
        sleepInSecond(3);
        //div.ui-tooltip-content
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),
                "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Hover_Element() {
        driver.get("http://www.myntra.com/");
        actions.moveToElement(driver.findElement(By.xpath("//a[text()='Kids' and @class='desktop-main']"))).perform();
        sleepInSecond(2);
        actions.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();

        Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='Kids Home Bath']")).getText(), "Kids Home Bath");

    }

    @Test
    public void TC_03_Click_Hold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
        Assert.assertEquals(allNumbers.size(), 20);

        // Chọn theo Block - Ngang/dọc
        //1-15
        actions.clickAndHold(allNumbers.get(0)) //click lên số 1 và giũ chuột
                .pause(2000)//dừng lại 2s
                .moveToElement(allNumbers.get(14)) //di chuyển chuột
                .pause(2000)
                .release() //nhả chuột trái ra
                .perform(); //
        sleepInSecond(3);

        List<String> allNumberTextExpected = new ArrayList<String>();
        allNumberTextExpected.add("1");
        allNumberTextExpected.add("2");
        allNumberTextExpected.add("3");
        allNumberTextExpected.add("5");
        allNumberTextExpected.add("6");
        allNumberTextExpected.add("7");
        allNumberTextExpected.add("9");
        allNumberTextExpected.add("10");
        allNumberTextExpected.add("11");
        allNumberTextExpected.add("13");
        allNumberTextExpected.add("14");
        allNumberTextExpected.add("15");

        //Tổng số đã chọn
        List<WebElement> allNumbersSelected = driver.findElements(By.cssSelector("li.ui-state-default.ui-selected"));
        Assert.assertEquals(allNumbersSelected.size(), 12);


        List<String> allNumberTextActual = new ArrayList<String>();

        for (WebElement element : allNumbersSelected) {
            allNumberTextActual.add(element.getText());

        }
        Assert.assertEquals(allNumberTextExpected, allNumberTextActual);
    }

    @Test
    public void TC_04_ClickAndHold() {


    }

    @Test
    public void TC_05_Fahasha() {
        driver.get("https://www.fahasa.com/");

        actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        sleepInSecond(2);

        actions.moveToElement((driver.findElement
                (By.xpath("//a[@title='Bách Hóa Online - Lưu Niệm']")))).perform();
        sleepInSecond(2);

        String subMenuText = "Thiết Bị Số - Phụ Kiện Số";

        driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='" + subMenuText + "']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("ol.breadcrumb strong")).getText(), "THIẾT BỊ SỐ - PHỤ KIỆN SỐ");
        Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Thiết Bị Số - Phụ Kiện Số']")).isDisplayed());

    }


    @Test
    public void TC_06_Double_Click() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement doubleClickElement = driver.findElement(By.xpath("//button[text()='Double click me']"));

        if (driver.toString().contains("firefox"))
            Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");


    }

    @Test
    public void TC_07_Right_Click() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());

        actions.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());


        actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-hover.context-menu-visible")).isDisplayed());

        actions.click(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
        sleepInSecond(3);

        driver.switchTo().alert().accept();
        sleepInSecond(3);

        //Kiểm tra Paste ko con hiển thị
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());
       // Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-hover.context-menu-visible")).isDisplayed());

    }
    @Test
    public void TC_08_DragDropHTML4() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));

        actions.dragAndDrop(smallCircle, bigCircle).perform();
        sleepInSecond(2);

        Assert.assertEquals(bigCircle.getText(),"You did great!");
        Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex().toLowerCase(),"#03a9f4");

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
