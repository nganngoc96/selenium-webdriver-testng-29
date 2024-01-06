package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_22_Wait_02_Find_Element {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        //Implicitlywait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.facebook.com/");

    }
    @Test
    public void TC_01_FindElement() {
        //Case 1- Element được tìm thấy chỉ có 1
        //sẽ không cần chờ hết timeout
        //Tìm thấy sẽ trả về 1 WebElement _ Qua tiếp step tiếp theo
        System.out.println("Start step: " + getDateTimeNow());
        driver.findElement(By.cssSelector("input#email"));
        System.out.println("Start step: " + getDateTimeNow());

        //Case 2- Element được tiền thấy nhiều hơn 1
        //sẽ không cần chờ hết timeout
        // Lấy cái Element đầu tiên dù có cả n node_Qua tiếp step tiếp theo
        driver.findElement(By.cssSelector("input[type='text'],[type='password']")).sendKeys("ngan@gmail.com");

        //Case3 Element không được tìm thấy
        //chờ hết timeout
        //Trong time 10s cứ mỏi nua s tìm lại 1 lần
        // tìm thấy sẽ trả về Element _ Qua tiếp step tiếp theo
        // Nếu tìm lại mà ko thấy thì đánh fail tcs: NoSuchElementException
        // Các step còn lại ko chay nua
    }
    @Test
    public void TC_02_FindElements() {
        List <WebElement> elementList;

        //Case1 _ Element được tìm thấy trong khoản thời gian được set
        //sẽ không cần chờ hết timeout
        //Tìm thấy sẽ trả về 1 list element chua đúng 1 element _ Qua tiếp step tiếp theo
        System.out.println("Start step: " + getDateTimeNow());
        elementList = driver.findElements(By.cssSelector("input#email"));
        System.out.println("List have:" + elementList.size());
        System.out.println("End step: " + getDateTimeNow());

        //Case 2- Element được tiền thấy nhiều hơn 1
        //sẽ không cần chờ hết timeout
        //Trả về 1 list chứa nhiều element
        System.out.println("Start step: " + getDateTimeNow());
        elementList = driver.findElements(By.cssSelector("input[type='text'],[type='password']"));
        System.out.println("List have:" + elementList.size());
        System.out.println("End step: " + getDateTimeNow());

        //Case3 Element không được tìm thấy
        // cần chờ hết timeout
        //Trong time 10s cứ mỏi nua s tìm lại 1 lần (polling)
        // Nếu trong time tìm lại vẫn thấy element thù củng trả về 1 list chứa nhiều element
        // Nếu trong time tìm lại vẫn ko thấy thì trả về 1 list rỗng (empty) và ko đánh fail tcs
        // Qua step tiếp theo
        System.out.println("Start step: " + getDateTimeNow());
        elementList = driver.findElements(By.cssSelector("input[name='reg_email__']"));
        System.out.println("List have:" + elementList.size());
        System.out.println("End step: " + getDateTimeNow());

    }
    @Test
    public void TC_03_() {

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
  public String getDateTimeNow (){
      Date date = new Date();
      return date.toString();
  }
}
