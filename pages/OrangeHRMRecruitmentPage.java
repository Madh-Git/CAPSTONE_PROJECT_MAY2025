package pages;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class  OrangeHRMRecruitmentPage {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    By recruitmentTab = By.xpath("//span[text()='Recruitment']");
    By addButton = By.cssSelector("[class=\"oxd-button oxd-button--medium oxd-button--secondary\"]");
    By firstName = By.name("firstName");
    By lastName = By.name("lastName");
    By email = By.xpath("//label[text()='Email']/following::input[1]");
    By saveButton = By.xpath("//button[@type='submit']");
    By userDropdown = By.cssSelector("[class=\"oxd-userdropdown-name\"]");
    By logoutButton = By.linkText("Logout");

    public OrangeHRMRecruitmentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToRecruitment() throws Exception {
        driver.findElement(recruitmentTab).click();
        Thread.sleep(2000);
    }

    public void addCandidate(String fName, String lName, String emailId) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(addButton));

        addBtn.click();
        Thread.sleep(2000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        driver.findElement(firstName).sendKeys(fName);
        Thread.sleep(2000);
        driver.findElement(lastName).sendKeys(lName);
        Thread.sleep(2000);
        driver.findElement(email).sendKeys(emailId);
        Thread.sleep(2000);
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", saveBtn);
        Thread.sleep(500);
        js.executeScript("arguments[0].click();", saveBtn);
        Thread.sleep(2000);
    }

    public void logout() throws Exception {
        driver.findElement(userDropdown).click();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutBtn.click();
        Thread.sleep(2000);
    }

}
