package Tests;

import Base.BaseTest;
import Base.TestData;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ShoppingCartTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        driver.navigate().to("https://www.saucedemo.com/");
        driver.manage().deleteAllCookies();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.sessionStorage.clear();");
        js.executeScript("window.localStorage.clear();");
    }

    @Test(priority = 1, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void userCanAddMultipleItemsToCart(String username, String password){
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.productsSectionIsDisplayed();
        shoppingCartPage.isCartItemCountDisplayed();
        productsPage.addMultipleItemsToTheCart(3);
        Assert.assertEquals(shoppingCartPage.cartItemCount.getText(), "3");
    }
    @Test(priority = 2, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void removeButtonInTheCartRemovesItemFromCart(String username, String password){
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.productsSectionIsDisplayed();
        shoppingCartPage.isCartItemCountDisplayed();
        productsPage.clickOnAddToCartButton(0);
        shoppingCartPage.clickOnShoppingCart();
        shoppingCartPage.removeButton.get(0).click();
        shoppingCartPage.isCartItemCountDisplayed();
    }

    @Test(priority = 3, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void removeButtonOnTheProductCardRemovesItemFromCart(String username, String password){
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.productsSectionIsDisplayed();
        shoppingCartPage.isCartItemCountDisplayed();
        productsPage.clickOnAddToCartButton(0);
        productsPage.clickOnRemoveButton(0);
        shoppingCartPage.isCartItemCountDisplayed();
    }

    @Test(priority = 4, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void itemCountUpdatesCorrectlyWhenAddingAndRemovingItems(String username, String password){
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.productsSectionIsDisplayed();
        shoppingCartPage.isCartItemCountDisplayed();
        productsPage.clickOnAddToCartButton(0);
        Assert.assertEquals(shoppingCartPage.cartItemCount.getText(), "1");
        productsPage.clickOnAddToCartButton(1);
        Assert.assertEquals(shoppingCartPage.cartItemCount.getText(), "2");
        productsPage.clickOnAddToCartButton(2);
        Assert.assertEquals(shoppingCartPage.cartItemCount.getText(), "3");
        productsPage.clickOnRemoveButton(0);
        Assert.assertEquals(shoppingCartPage.cartItemCount.getText(), "2");
        productsPage.clickOnRemoveButton(1);
        Assert.assertEquals(shoppingCartPage.cartItemCount.getText(), "1");
        productsPage.clickOnRemoveButton(2);
        shoppingCartPage.isCartItemCountDisplayed();
    }

    @Test(priority = 5, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void itemsRetainsInCartAfterRefreshingThePage(String username, String password) {
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.productsSectionIsDisplayed();
        shoppingCartPage.isCartItemCountDisplayed();
        productsPage.addMultipleItemsToTheCart(3);
        Assert.assertEquals(shoppingCartPage.cartItemCount.getText(), "3");
        driver.navigate().refresh();
        Assert.assertEquals(shoppingCartPage.cartItemCount.getText(), "3");
    }

    @Test(priority = 6, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void continueShoppingButtonInTheCartNavigatesToProductsPage(String username, String password) {
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.productsSectionIsDisplayed();
        shoppingCartPage.clickOnShoppingCart();
        shoppingCartPage.clickOnContinueShoppingButton();
        productsPage.assertPageHeader();
        productsPage.productsSectionIsDisplayed();
    }

    @Test(priority = 7, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void productDetailsInTheCartCorrespondsToDetailsOnProductCard(String username, String password) {
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.productsSectionIsDisplayed();
        shoppingCartPage.isCartItemCountDisplayed();
        String productName = productsPage.productsNames.get(0).getText();
        String productPrice = productsPage.productsPrice.get(0).getText();
        productsPage.clickOnAddToCartButton(0);
        shoppingCartPage.clickOnShoppingCart();
        Assert.assertEquals(shoppingCartPage.productName.getText(), productName);
        Assert.assertEquals(shoppingCartPage.productPrice.get(0).getText(), productPrice);
    }

    @Test(priority = 8, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void userCanContinueToCheckoutPageWithValidInformationFields(String username, String password) {
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.productsSectionIsDisplayed();
        productsPage.addMultipleItemsToTheCart(2);
        shoppingCartPage.clickOnShoppingCart();
        shoppingCartPage.clickOnCheckoutButton();
        shoppingCartPage.checkoutInformationForm("Ana", "Gasic", "23311");
        Assert.assertEquals(shoppingCartPage.checkoutTitle.getText(), "Checkout: Overview");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
    }

    @Test(priority = 9, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void theTotalPriceCalculatesCorrectly(String username, String password) {
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.productsSectionIsDisplayed();
        productsPage.addMultipleItemsToTheCart(2);
        shoppingCartPage.clickOnShoppingCart();
        shoppingCartPage.clickOnCheckoutButton();
        shoppingCartPage.checkoutInformationForm("Ana", "Gasic", "23311");
        Assert.assertEquals(shoppingCartPage.checkoutTitle.getText(), "Checkout: Overview");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
        shoppingCartPage.verifyCartTotal();
    }

    @Test(priority = 10, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void userCanFinishOrderSuccessfully(String username, String password) {
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.productsSectionIsDisplayed();
        productsPage.addMultipleItemsToTheCart(2);
        shoppingCartPage.clickOnShoppingCart();
        shoppingCartPage.clickOnCheckoutButton();
        shoppingCartPage.checkoutInformationForm("Ana", "Gasic", "12345");
        Assert.assertEquals(shoppingCartPage.checkoutTitle.getText(), "Checkout: Overview");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
        shoppingCartPage.verifyCartTotal();
        shoppingCartPage.clickOnFinishButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");
        Assert.assertEquals(shoppingCartPage.notification.getText(), "Thank you for your order!");
        Assert.assertEquals(shoppingCartPage.checkoutTitle.getText(), "Checkout: Complete!");
    }

    @Test(priority = 11, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void userCanCancelOrderOnCheckoutInformationPage(String username, String password) {
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.productsSectionIsDisplayed();
        productsPage.addMultipleItemsToTheCart(2);
        shoppingCartPage.clickOnShoppingCart();
        shoppingCartPage.clickOnCheckoutButton();
        shoppingCartPage.clickOnCancelButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        Assert.assertTrue(shoppingCartPage.checkoutButton.isDisplayed());
    }

    @Test(priority = 12, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void userCanCancelOrderOnCheckoutPage(String username, String password) {
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.productsSectionIsDisplayed();
        productsPage.addMultipleItemsToTheCart(2);
        shoppingCartPage.clickOnShoppingCart();
        shoppingCartPage.clickOnCheckoutButton();
        shoppingCartPage.checkoutInformationForm("Ana", "Gasic", "12345");
        shoppingCartPage.clickOnCancelButton();
        productsPage.assertPageHeader();
    }

    @Test(priority = 13, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void userCannotContinueToCheckoutWithEmptyInformationFields(String username, String password) {
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.productsSectionIsDisplayed();
        productsPage.addMultipleItemsToTheCart(2);
        shoppingCartPage.clickOnShoppingCart();
        shoppingCartPage.clickOnCheckoutButton();
        shoppingCartPage.checkoutInformationForm("", "", "");
        shoppingCartPage.clickOnContinueButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertTrue(shoppingCartPage.checkoutInfo.isDisplayed());
    }

    @Test(priority = 14, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void itemsAndCartCountRemainsCorrectAfterUserLogsOutAndLogsInAgain(String username, String password) {
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.productsSectionIsDisplayed();
        productsPage.addMultipleItemsToTheCart(2);
        Assert.assertEquals(shoppingCartPage.cartItemCount.getText(), "2");
        burgerMenuPage.clickToOpenBurgerMenu();
        burgerMenuPage.selectLogout();
        loginPage.login(username, password);
        Assert.assertEquals(shoppingCartPage.cartItemCount.getText(), "2");
    }

    @Test(priority = 15, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void userCannotProceedToCheckoutWithEmptyCart(String username, String password) {
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.productsSectionIsDisplayed();
        shoppingCartPage.clickOnShoppingCart();
        shoppingCartPage.isCartItemCountDisplayed();
        shoppingCartPage.clickOnCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        Assert.assertTrue(shoppingCartPage.checkoutButton.isDisplayed());
    }
}
