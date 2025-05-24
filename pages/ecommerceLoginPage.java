package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ecommerceLoginPage {
	
	 WebDriver driver;

	    By emailField = By.id("input-email");
	    By passwordField = By.id("input-password");
	    By loginButton = By.xpath("//input[@value='Login']");

	    public ecommerceLoginPage(WebDriver driver) {
	        this.driver = driver;
	    }

	    public void login(String email, String password) throws Exception {
	        driver.findElement(emailField).sendKeys(email);
	        Thread.sleep(2000);
	        driver.findElement(passwordField).sendKeys(password);
	        Thread.sleep(2000);
	        driver.findElement(loginButton).click();
	    }

}
