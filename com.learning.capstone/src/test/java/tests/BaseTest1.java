package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest1 {
	
	 protected WebDriver driver;

	    @BeforeClass
	    public void setUp() {
	    	
	    	ChromeOptions options = new ChromeOptions();
	        options.addArguments("force-device-scale-factor=1.2");
	        
	        driver = new ChromeDriver(options);
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.manage().window().maximize();
	        driver.get("https://opensource-demo.orangehrmlive.com/");

	        System.out.println("Browser launched and ready.");
	    }

	    @AfterClass
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	            System.out.println("Browser closed.");
	        }
	    }

}
