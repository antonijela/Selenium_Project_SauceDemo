package Tests;

import Base.BaseTest;
import Base.TestData;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductDetailsTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        driver.navigate().to("https://www.saucedemo.com/");
        driver.manage().deleteAllCookies();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.sessionStorage.clear();");
        js.executeScript("window.localStorage.clear();");
    }

    @Test(priority = 1, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void productDetailsPageDisplaysAllDetails(String username, String password){
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.productsSectionIsDisplayed();
        productsPage.productsNames.get(0).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.saucedemo.com/inventory-item"));
        Assert.assertTrue(productDetailsPage.productName.isDisplayed());
        Assert.assertTrue(productDetailsPage.productImage.isDisplayed());
        Assert.assertTrue(productDetailsPage.productPrice.isDisplayed());
        Assert.assertTrue(productDetailsPage.itemDescription.isDisplayed());
        Assert.assertTrue(productDetailsPage.addToCartButton.isDisplayed());
    }

    @Test(priority = 2, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void userCanNavigateBackToProductsPageByBackToProductsButton(String username, String password) {
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.productsSectionIsDisplayed();
        productsPage.productsNames.get(0).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.saucedemo.com/inventory-item"));
        productDetailsPage.clickOnBackToProductsButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test(priority = 3, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void userCanAddProductToCartByButtonOnProductDetailsPage(String username, String password){
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.productsNames.get(0).click();
        productDetailsPage.clickOnAddToCartButton();
        shoppingCartPage.clickOnShoppingCart();
        Assert.assertEquals(shoppingCartPage.productName.getText(), productsPage.productsNames.get(0).getText());
    }

    @Test(priority = 4, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void userCanRemoveProductFromTheCartByRemoveButton(String username, String password){
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.productsSectionIsDisplayed();
        shoppingCartPage.isCartItemCountDisplayed();
        productsPage.productsNames.get(0).click();
        String productName = productDetailsPage.productName.getText();
        productDetailsPage.clickOnAddToCartButton();
        shoppingCartPage.clickOnShoppingCart();
        Assert.assertTrue(shoppingCartPage.productName.getText().contains(productName));
        Assert.assertEquals(shoppingCartPage.cartItemCount.getText(), "1");
        shoppingCartPage.productName.click();
        productDetailsPage.clickOnRemoveButton();
        shoppingCartPage.isCartItemCountDisplayed();
    }
}
