package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage extends BaseTest {

    public ProductDetailsPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[data-test=\"inventory-item-name\"]")
    public WebElement productName;

    @FindBy (className = "inventory_details_img")
    public WebElement productImage;

    @FindBy(className = "inventory_details_price")
    public WebElement productPrice;

    @FindBy(css = "[data-test=\"inventory-item-desc\"]")
    public WebElement itemDescription;

    @FindBy(id = "add-to-cart")
    public WebElement addToCartButton;

    @FindBy(id = "remove")
    public WebElement removeButton;

    @FindBy(id = "back-to-products")
    public WebElement backToProductsButton;

    public void clickOnAddToCartButton(){
        addToCartButton.click();
    }
    public void clickOnRemoveButton(){
        removeButton.click();
    }
    public void clickOnBackToProductsButton(){
        backToProductsButton.click();
    }

}
