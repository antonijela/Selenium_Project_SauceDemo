package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductsPage extends BaseTest {

    public ProductDetailsPage productDetailsPage;

    public ProductsPage(){
        PageFactory.initElements(driver, this);
        productDetailsPage = new ProductDetailsPage();
    }

    @FindBy(className = "app_logo")
    public WebElement logo;

    @FindBy(css = "[data-test=\"title\"]")
    public WebElement title;

    @FindBy(className = "inventory_item_label")
    public List<WebElement> products;

    @FindBy(css = ".inventory_item_name")
    public List<WebElement>  productsNames;

    @FindBy(css = ".inventory_item_img img")
    public List<WebElement> productsImage;

    @FindBy(className = "inventory_item_price")
    public List<WebElement> productsPrice;

    @FindBy(css = ".btn_inventory")
    public List<WebElement> addToCartButton;

    @FindBy(css = ".btn_inventory")
    public List<WebElement> removeButton;

    @FindBy(className = "product_sort_container")
    public WebElement sortingDropdown;

    public void productsSectionIsDisplayed(){
        for (WebElement product : products) {
            Assert.assertTrue(product.isDisplayed());
        }
    }
    public void assertPageHeader(){
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertTrue(logo.isDisplayed());
        Assert.assertEquals(logo.getText(), "Swag Labs");
        Assert.assertTrue(title.isDisplayed());
        Assert.assertEquals(title.getText(), "Products");
    }
    public void productsNameIsDisplayed(){
        for (WebElement name : productsNames){
            Assert.assertTrue(name.isDisplayed());
        }
    }
    public void productsImageIsDisplayed(){
        for (WebElement image : productsImage){
            Assert.assertTrue(image.isDisplayed());
        }
    }
    public void productsPriceIsDisplayed(){
        for (WebElement price : productsPrice){
            Assert.assertTrue(price.isDisplayed());
        }
    }
    public void productsAddToCartButtonIsDisplayed(){
        for (WebElement addToCart : addToCartButton){
            Assert.assertTrue(addToCart.isDisplayed());
        }
    }
    public void checkProductsName(){
        for (int i = 0; i < productsNames.size(); i++){
            if (i % 2 == 0) {
                String productName = productsNames.get(i).getText();
                productsNames.get(i).click();
                Assert.assertEquals(productDetailsPage.productName.getText(), productName);
                driver.navigate().back();
            }
        }
    }

    public void clickOnAddToCartButton(int index){
        addToCartButton.get(index).click();
    }
    public void clickOnRemoveButton(int index){
        removeButton.get(index).click();
    }

    public void oneProductIsDisplayed() {
        int actualNumberOfProducts = products.size();
        Assert.assertTrue(actualNumberOfProducts > 0);
    }

    public void productsSize(int expectedNumberOfProducts){
        int actualNumberOfProducts = products.size();
        Assert.assertEquals(actualNumberOfProducts, expectedNumberOfProducts);
    }

    public void selectSortingDropdown(String sortOptionValue) {
        Select select = new Select(sortingDropdown);
        select.selectByValue(sortOptionValue);
    }
    public void productsAreSortedFromAtoZ(){
        List<String> productNames = new ArrayList<>();
        for (WebElement name : productsNames){
            productNames.add(name.getText());
        }
        List<String> sortedProductNames = new ArrayList<>(productNames);
        Collections.sort(sortedProductNames);
        Assert.assertEquals(productNames, sortedProductNames);
    }
    public void productsAreSortedFromZtoA(){
        List<String> productNames = new ArrayList<>();
        for (WebElement name : productsNames){
            productNames.add(name.getText());
        }
        List<String> sortedProductNames = new ArrayList<>(productNames);
        Collections.sort(sortedProductNames,Collections.reverseOrder());
        Assert.assertEquals(productNames, sortedProductNames);
    }
    public void productsAreSortedFromLowToHighPrice(){
        List<Double> prices = new ArrayList<>();
        for (WebElement price : productsPrice){
            String priceText = price.getText().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);
        Assert.assertEquals(prices, sortedPrices);
    }
    public void productsAreSortedFromHighToLowPrice(){
        List<Double> prices = new ArrayList<>();
        for (WebElement price : productsPrice){
            String priceText = price.getText().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices, Collections.reverseOrder());
        Assert.assertEquals(prices, sortedPrices);
    }

    public void addMultipleItemsToTheCart(int itemQuantity){
        for (int i = 0; i < itemQuantity; i++){
            addToCartButton.get(i).click();
        }
    }
}
