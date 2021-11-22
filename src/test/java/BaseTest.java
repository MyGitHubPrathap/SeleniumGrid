import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {
    //WebDriver driver;
    RemoteWebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(String browserName, Method name) throws MalformedURLException {
        System.out.println("browser name is: "+ browserName );
        String methodName = name.getName();
        String remoteUrl = "http://localhost:4444/wd/hub/";
        System.out.println("Selenium hub url is: "+remoteUrl);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("name",methodName);
        cap.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
        cap.setCapability("ideleTimeout",200);
        cap.setCapability("recordVideo",true);

        if(browserName.endsWith("chrome")){
            WebDriverManager.chromedriver().setup();
           cap.setCapability(CapabilityType.BROWSER_NAME, Browser.CHROME);
        }else if(browserName.equals("ff")){
            WebDriverManager.firefoxdriver().setup();
            cap.setCapability(CapabilityType.BROWSER_NAME,Browser.FIREFOX);
        }
        driver = new RemoteWebDriver(new URL(remoteUrl),cap);
        //driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(30));
    }



    public void doLogin(){
        driver.get("https://www.demoblaze.com/");
        driver.findElement(By.xpath("//a[@id='login2']")).click();
        driver.findElement(By.xpath("//input[@id='loginusername']")).sendKeys("test");
        driver.findElement(By.xpath("//input[@id='loginpassword']")).sendKeys("test");
        driver.findElement(By.xpath("//button[normalize-space()='Log in']")).click();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
