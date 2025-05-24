package tests;

import org.apache.log4j.Logger;
import org.testng.annotations.*;

import pages.OrangeHRMLoginPage;
import pages.OrangeHRMRecruitmentPage;

public class OrangeHRMTest extends BaseTest1 {

    OrangeHRMLoginPage loginPage;
    OrangeHRMRecruitmentPage recruitmentPage;

    private static final Logger logger = Logger.getLogger(OrangeHRMTest.class);

    @Test(priority = 1)
    public void loginTest() throws Exception {
        logger.info("Starting login test");
        loginPage = new OrangeHRMLoginPage(driver);
        loginPage.login("Admin", "admin123");
        logger.info("Login test completed");
    }

    @Test(priority = 2)
    public void addCandidateTest() throws Exception {
        logger.info("Starting add candidate test");
        recruitmentPage = new OrangeHRMRecruitmentPage(driver);
        recruitmentPage.navigateToRecruitment();
        recruitmentPage.addCandidate("Rekha", "Bhat", "rekha.bhat@gmail.com");
        logger.info("Add candidate test completed");
    }

    @Test(priority = 3)
    public void logoutTest() throws Exception {
        logger.info("Starting logout test");
        recruitmentPage.logout();
        logger.info("Logout test completed");
    }
}
