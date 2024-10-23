package Pages;

import Base.BaseTest;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class ShoppingCartPage extends BaseTest {

    public ShoppingCartPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "shopping_cart_link")
    public WebElement shoppingCart;

    @FindBy(className = "shopping_cart_badge")
    public WebElement cartItemCount;

    @FindBy(className = "inventory_item_name")
    public WebElement productName;

    @FindBy(className = "inventory_item_price")
    public List<WebElement> productPrice;

    @FindBy(css = ".cart_button")
    public List<WebElement> removeButton;

    @FindBy(id = "continue-shopping")
    public WebElement continueShoppingButton;

    @FindBy(id = "checkout")
    public WebElement checkoutButton;

    @FindBy(id = "cancel")
    public WebElement cancelButton;

    @FindBy(className = "checkout_info")
    public WebElement checkoutInfo;

    @FindBy(id = "first-name")
    public WebElement firstNameField;

    @FindBy(id = "last-name")
    public WebElement lastNameField;

    @FindBy(id = "postal-code")
    public WebElement zipCodeField;

    @FindBy(id = "continue")
    public WebElement continueButton;

    @FindBy(className = "title")
    public WebElement checkoutTitle;

    @FindBy(className = "summary_subtotal_label")
    public WebElement totalItemPrice;

    @FindBy(className = "summary_tax_label")
    public WebElement taxPrice;

    @FindBy(className = "summary_total_label")
    public WebElement totalPrice;

    @FindBy(id = "finish")
    public WebElement finishButton;

    @FindBy(className = "complete-header")
    public WebElement notification;

    public void clickOnShoppingCart(){
        shoppingCart.click();
    }
    public void isCartItemCountDisplayed(){
        boolean isItemCountDisplayed = false;
        try {
            isItemCountDisplayed = cartItemCount.isDisplayed();
        } catch (NoSuchElementException e){

        }
        Assert.assertFalse(isItemCountDisplayed);
    }

    public void removeEachProductFromTheCart(){
        for (int i = 0; i < removeButton.size(); i++){
            removeButton.get(i).click();
        }
    }
    public void clickOnContinueShoppingButton(){
        continueShoppingButton.click();
    }
    public void clickOnCheckoutButton(){
        checkoutButton.click();
    }
    public void clickOnCancelButton(){
        cancelButton.click();
    }

    public void typeFirstName(String firstName){
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }
    public void typeLastName(String lastName){
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }
    public void typeZipCode(String zipCode){
        zipCodeField.clear();
        zipCodeField.sendKeys(zipCode);
    }
    public void clickOnContinueButton(){
        continueButton.click();
    }
    public void checkoutInformationForm(String firstName, String lastName, String zipCode){
        typeFirstName(firstName);
        typeLastName(lastName);
        typeZipCode(zipCode);
        continueButton.click();
    }

    public void clickOnFinishButton(){
        finishButton.click();
    }

    public void verifyCartTotal() {

        double totalCalculatedItemPrice = 0.0;
        for (WebElement price : productPrice) {
            String priceText = price.getText().replace("$", "");
            double priceNumber = Double.parseDouble(priceText);
            totalCalculatedItemPrice += priceNumber;
        }

        String taxPriceText = taxPrice.getText().replace("Tax: $", "");
        String totalPriceText = totalPrice.getText().replace("Total: $", "");
        double totalPrice = Double.parseDouble(totalPriceText);
        double totalDisplayedPrice = totalCalculatedItemPrice + Double.parseDouble(taxPriceText);

        Assert.assertEquals(totalPrice, totalDisplayedPrice);
    }
}

