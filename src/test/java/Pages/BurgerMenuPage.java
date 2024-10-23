package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BurgerMenuPage extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(BurgerMenuPage.class);

    public BurgerMenuPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "bm-menu-wrap")
    public WebElement burgerMenu;

    @FindBy(id = "react-burger-menu-btn")
    public WebElement burgerMenuButton;

    @FindBy(id = "inventory_sidebar_link")
    public WebElement allItems;

    @FindBy(id = "about_sidebar_link")
    public WebElement about;

    @FindBy(id = "logout_sidebar_link")
    public WebElement logout;

    @FindBy(id = "reset_sidebar_link")
    public WebElement resetAppState;

    @FindBy(id = "react-burger-cross-btn")
    public WebElement closeBurgerMenu;

    public void clickToOpenBurgerMenu(){
        burgerMenuButton.click();
    }
    public void selectAllItems(){
        allItems.click();
    }
    public void selectAbout(){
        about.click();
    }
    public void selectLogout(){
        logout.click();
    }
    public void selectResetAppState(){
        resetAppState.click();
    }
    public void clickToCloseBurgerMenu(){
        closeBurgerMenu.click();
    }
}
