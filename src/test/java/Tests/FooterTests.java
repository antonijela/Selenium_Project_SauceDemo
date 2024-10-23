package Tests;

import Base.BaseTest;
import Base.TestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class FooterTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @Test(priority = 1, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void twitterIconRedirectsToCorrectTwitterPage(String username, String password) {
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        Assert.assertTrue(footerPage.footer.isDisplayed());
        footerPage.clickOnTwitter();
        List<String> windowsTab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowsTab.get(1));
        Assert.assertTrue(driver.getCurrentUrl().contains("https://x.com/saucelabs"));
        driver.close();
        driver.switchTo().window(windowsTab.get(0));
    }

    @Test(priority = 2, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void facebookIconRedirectsToCorrectFacebookPage(String username, String password) {
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        Assert.assertTrue(footerPage.footer.isDisplayed());
        footerPage.clickOnFacebook();
        List<String> windowsTab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowsTab.get(1));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/saucelabs");
        Assert.assertEquals(driver.getTitle(), "Sauce Labs | Facebook");
        driver.close();
        driver.switchTo().window(windowsTab.get(0));
    }

    @Test(priority = 3, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void linkedinIconRedirectsToCorrectLinkedinPage(String username, String password) {
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        Assert.assertTrue(footerPage.footer.isDisplayed());
        footerPage.clickOnLinkedin();
        List<String> windowsTab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowsTab.get(1));
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.linkedin.com/company/sauce-labs"));
        Assert.assertEquals(driver.getTitle(), "Sauce Labs | LinkedIn");
        driver.close();
        driver.switchTo().window(windowsTab.get(0));
    }

    @Test(priority = 4, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void copyrightInformationShouldBeCorrect(String username, String password) {
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        Assert.assertTrue(footerPage.footer.isDisplayed());
        Assert.assertTrue(footerPage.copyrightSection.getText().contains("Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy"));
    }

}
