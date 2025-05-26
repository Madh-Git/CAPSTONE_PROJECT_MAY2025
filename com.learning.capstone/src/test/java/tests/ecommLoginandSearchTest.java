package tests;

import java.time.Duration;


import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ecommerceLoginPage;
import pages.ecommerceSearchPage;

public class ecommLoginandSearchTest extends BaseTest{
	
	 private static final Logger logger = Logger.getLogger(ecommLoginandSearchTest.class);
	
	@Test(priority = 1)
    public void testLogin() throws Exception {
		logger.info("Starting Login test");
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
        ecommerceLoginPage loginPage = new ecommerceLoginPage(driver);
        loginPage.login("jeeva@gmail.com", "Jeeva@123"); // Use a valid test account if needed
        
        boolean loggedIn = driver.getTitle().contains("My Account") || driver.getCurrentUrl().contains("account/account");
        Assert.assertTrue(loggedIn, "Login failed or not redirected to My Account");
        logger.info("Login successful, verified account page");
    }

    @Test(priority = 2)
    public void testSearchProduct() throws Exception {
    	logger.info("Starting Product Search test");
        driver.get("https://ecommerce-playground.lambdatest.io/");
        ecommerceSearchPage searchPage = new ecommerceSearchPage(driver);
        searchPage.search("MacBook");
        searchPage.addFirstProductToCart();

        boolean searchResult = searchPage.isSearchResultPresent("MacBook");
        Assert.assertTrue(searchResult, "Search result for 'MacBook' not found!");
        logger.info("Search for 'MacBook' successful and results verified");
        
     // Add first product to cart
        logger.info("MacBook added to cart successfully");
        
        // Now perform logout
        logger.info("Starting logout process");
        searchPage.logout();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.or(
		    ExpectedConditions.titleContains("Account Logout"),
		    ExpectedConditions.urlContains("logout")
		));
		logger.info("Logout successful, verified logout page");
    }
}

