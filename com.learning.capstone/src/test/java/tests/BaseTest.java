package tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class BaseTest {
	
	 public WebDriver driver;

	    @BeforeClass
	    public void setUp() {
	        // Update path to chromedriver if needed
	        driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.manage().window().maximize();
	        System.out.println("Browser launched and maximized.");
	    }

	    @AfterMethod
	    public void takeScreenshotOnFailure(ITestResult result) {
	        if (ITestResult.FAILURE == result.getStatus()) {
	            TakesScreenshot ts = (TakesScreenshot) driver;
	            File src = ts.getScreenshotAs(OutputType.FILE);
	            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	            String fileName = "screenshots/" + result.getName() + "_" + timestamp + ".png";

	            try {
	                FileUtils.copyFile(src, new File(fileName));
	                System.out.println("Screenshot captured: " + fileName);
	            } catch (IOException e) {
	                System.out.println("Failed to save screenshot: " + e.getMessage());
	            }
	        }
	    }

	    @AfterClass
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	            System.out.println("Browser closed.");
	        }
	    }

}
