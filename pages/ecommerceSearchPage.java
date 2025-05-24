package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class ecommerceSearchPage {
	
	WebDriver driver;

    By searchInput = By.name("search");
    By searchButton = By.xpath("//button[@class=\"type-text\"]");
    By firstProductAddToCartButton = By.cssSelector(".product-thumb .btn-cart");
    By successAlert = By.cssSelector(".alert-success");


    public ecommerceSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    // Search method
    public void search(String query) throws Exception {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         WebElement input = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
         input.clear();
         input.sendKeys(query);
         Thread.sleep(2000);
         driver.findElement(searchButton).click();
    }
    
    public void addFirstProductToCart() throws Exception {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	Actions actions = new Actions(driver);

        // Get the first visible product container
        List<WebElement> productList = wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".product-thumb")));
        Thread.sleep(2000);
        
        if (productList.isEmpty()) {
            throw new RuntimeException("No products found on search result page.");
        }

        WebElement firstProduct = productList.get(0);
        
     // Scroll the first product element into view using JavaScript
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", firstProduct);

        // Optional: scroll a bit more down or up if needed
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollBy(0, -100);"); 
        
        actions.moveToElement(firstProduct).perform();
        
        Thread.sleep(2000);

        WebElement addToCartBtn = firstProduct.findElement(By.cssSelector(".btn-cart"));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();

    }
    
    public boolean isProductAddedToCart() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'alert') and contains(@class,'alert-success')]/p")
            ));
            return successMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isSearchResultPresent(String keyword) {
        return driver.getPageSource().toLowerCase().contains(keyword.toLowerCase());
    }
    
    public void logout() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        try {
            // Scroll to top
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(2000);

            // Locate My Account dropdown by class (hoverable dropdown)
            By accountDropdown = By.cssSelector("[class=\"icon fas fa-user\"]");
            WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(accountDropdown));

            // Hover on the dropdown to reveal Logout
            actions.moveToElement(dropdownElement).perform();
            Thread.sleep(3000);  // Wait for the dropdown menu to appear

            // Now find the Logout link inside the revealed dropdown
            By logoutLink = By.linkText("Logout");
            Thread.sleep(2000);
            WebElement logoutElement = wait.until(ExpectedConditions.elementToBeClickable(logoutLink));

            try {
                logoutElement.click();
            } catch (Exception e) {
                System.out.println("Standard click failed, trying JS click for logout.");
                js.executeScript("arguments[0].click();", logoutElement);
            }

        } catch (Exception e) {
            System.out.println("Failed to logout. Possibly due to UI overlay or locator issue.");
            e.printStackTrace();
        }
    }

}
