package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseTest {

    public LoginPage(){
        PageFactory.initElements(driver, this);
        productsPage = new ProductsPage();

    }

    @FindBy(id = "login_button_container")
    public WebElement loginForm;

    @FindBy(id = "user-name")
    public WebElement usernameField;

    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(id = "login-button")
    public WebElement loginButton;

    @FindBy(css = "[data-test=\"error\"]")
    public WebElement invalidDataNotification;

    public void typeUsername(String username){
        usernameField.clear();
        usernameField.sendKeys(username);
    }
    public void typePassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void login(String username, String password){
        typeUsername(username);
        typePassword(password);
        loginButton.click();
    }
}
