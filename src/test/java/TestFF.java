import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestFF extends BaseTest {


    public void doLogin(){
        driver.get("https://www.demoblaze.com/");
        driver.findElement(By.xpath("//a[@id='login2']")).click();
        driver.findElement(By.xpath("//input[@id='loginusername']")).sendKeys("test");
        driver.findElement(By.xpath("//input[@id='loginpassword']")).sendKeys("test");
        driver.findElement(By.xpath("//button[normalize-space()='Log in']")).click();
    }

    @Test
    public void checkLoginFF(){
        doLogin();
        Assert.assertEquals(driver.getTitle(),"STORE");
    }
}
