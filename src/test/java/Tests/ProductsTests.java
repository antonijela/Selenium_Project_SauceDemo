package Tests;

import Base.BaseTest;
import Base.TestData;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductsTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        driver.navigate().to("https://www.saucedemo.com/");
        driver.manage().deleteAllCookies();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.sessionStorage.clear();");
        js.executeScript("window.localStorage.clear();");
    }


    @Test(priority = 1, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void productsSectionIsDisplayedWhenNavigatingToProductsPage(String username, String password){
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.productsSectionIsDisplayed();
    }

    @Test(priority = 2, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void atLeastOneProductIsDisplayed(String username, String password){
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.oneProductIsDisplayed();
    }

    @Test(priority = 3, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void allProductsAreDisplayed(String username, String password){
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.productsSize(6);
    }

    @Test(priority = 4, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void productDetailsAreDisplayed(String username, String password){
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.productsNameIsDisplayed();
        productsPage.productsImageIsDisplayed();
        productsPage.productsPriceIsDisplayed();
        productsPage.productsAddToCartButtonIsDisplayed();
    }

    @Test(priority = 5, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void whenClickingOnTheProductUserIsNavigatedToCorrectProductDetailPage(String username, String password) {
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.checkProductsName();
    }

    @Test(priority = 6, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void userCanAddProductToCartByButtonOnProductCard(String username, String password){
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        String productName = productsPage.productsNames.get(0).getText();
        productsPage.clickOnAddToCartButton(0);
        shoppingCartPage.clickOnShoppingCart();
        Assert.assertEquals(shoppingCartPage.productName.getText(), productName);
    }

    @Test(priority = 7, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void userCanSortProductsCorrectly(String username, String password){
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.selectSortingDropdown("az");
        productsPage.productsAreSortedFromAtoZ();
        productsPage.selectSortingDropdown("za");
        productsPage.productsAreSortedFromZtoA();
        productsPage.selectSortingDropdown("lohi");
        productsPage.productsAreSortedFromLowToHighPrice();
        productsPage.selectSortingDropdown("hilo");
        productsPage.productsAreSortedFromHighToLowPrice();
    }

    @Test(priority = 8, dataProvider = "validLoginData", dataProviderClass = TestData.class)
    public void sortingOrderIsPreservedAfterPageRefresh(String username, String password){
        loginPage.login(username, password);
        productsPage.assertPageHeader();
        productsPage.selectSortingDropdown("za");
        productsPage.productsAreSortedFromZtoA();
        driver.navigate().refresh();
        productsPage.productsAreSortedFromZtoA();
    }
}
