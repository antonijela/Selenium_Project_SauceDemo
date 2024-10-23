package Tests;

import Base.BaseTest;
import Base.TestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BurgerMenuTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @Test(priority = 1, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void userCanOpenAndCloseBurgerMenu(String username, String password) throws InterruptedException {
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        burgerMenuPage.clickToOpenBurgerMenu();
        Assert.assertTrue(burgerMenuPage.burgerMenu.isDisplayed());
        Thread.sleep(1000);
        burgerMenuPage.clickToCloseBurgerMenu();
        Assert.assertTrue(burgerMenuPage.burgerMenu.isDisplayed());
    }

    @Test(priority = 2, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void allItemsOptionNavigatesUserToProductsPage(String username, String password){
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.productsNames.get(0).click();
        burgerMenuPage.clickToOpenBurgerMenu();
        burgerMenuPage.selectAllItems();
        productsPage.assertPageHeader();
    }

    @Test(priority = 3, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void aboutOptionNavigatesUserToSauceLabsPage(String username, String password){
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        burgerMenuPage.clickToOpenBurgerMenu();
        burgerMenuPage.selectAbout();
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/");
    }

    @Test(priority = 4, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void resetAppStateEmptiesTheCart(String username, String password){
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.addToCartButton.get(0).click();
        Assert.assertTrue(shoppingCartPage.cartItemCount.isDisplayed());
        burgerMenuPage.clickToOpenBurgerMenu();
        burgerMenuPage.selectResetAppState();
        shoppingCartPage.isCartItemCountDisplayed();
    }


}
