package Tests;

import Base.BaseTest;
import Base.TestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @Test(priority = 1, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void userCanLoginWithValidData(String username, String password){
        loginPage.login(username, password);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertTrue(productsPage.logo.isDisplayed());
        Assert.assertEquals(productsPage.logo.getText(), "Swag Labs");
        Assert.assertTrue(productsPage.title.isDisplayed());
        Assert.assertEquals(productsPage.title.getText(), "Products");
    }

    @Test(priority = 2, dataProvider = "invalidLoginData", dataProviderClass = TestData.class)
    public void userCannotLoginWithInvalidData(String username, String password){
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(loginPage.invalidDataNotification.isDisplayed());
        Assert.assertEquals(loginPage.invalidDataNotification.getText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(priority = 3, dataProvider = "emptyFieldsData", dataProviderClass = TestData.class)
    public void userCannotLoginWithEmptyFields(String username, String password){
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(loginPage.invalidDataNotification.isDisplayed());
    }

    @Test(priority = 4, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void userCanLogoutSuccessfully(String username, String password){
        loginPage.login(username, password);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        burgerMenuPage.clickToOpenBurgerMenu();
        burgerMenuPage.selectLogout();
        Assert.assertTrue(loginPage.loginForm.isDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");

    }
}
