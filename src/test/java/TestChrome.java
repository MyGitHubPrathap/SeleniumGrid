import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestChrome extends BaseTest{
    //https://www.demoblaze.com/



    @Test(priority = 1)
    public void checkLoginChrome() throws InterruptedException {
        doLogin();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getTitle(),"STORE");
    }

    @Test(priority = 2)
    public void checkContact() throws InterruptedException {
        doLogin();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[normalize-space()='Cart']")).click();
        WebElement ele = driver.findElement(By.xpath("//button[normalize-space()='Place Order']"));
        Assert.assertEquals(ele.getText(),"Place Order");
    }

}
