package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrangeHRMLoginPage {
	
	WebDriver driver;

    By usernameField = By.name("username");
    By passwordField = By.name("password");
    By loginButton = By.cssSelector("button[type='submit']");

    public OrangeHRMLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) throws Exception {
    	Thread.sleep(2000);
        driver.findElement(usernameField).sendKeys(username);
        Thread.sleep(2000);
        driver.findElement(passwordField).sendKeys(password);
        Thread.sleep(2000);
        driver.findElement(loginButton).click();
        Thread.sleep(2000);
    }

}
