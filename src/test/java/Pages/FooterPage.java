package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FooterPage extends BaseTest {

    public FooterPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "footer")
    public WebElement footer;

    @FindBy(className = "social")
    public WebElement socialLinksSection;

    @FindBy(className = "footer_copy")
    public WebElement copyrightSection;

    @FindBy(className = "social_twitter")
    public WebElement twitterIcon;

    @FindBy(className = "social_facebook")
    public WebElement facebookIcon;

    @FindBy(className = "social_linkedin")
    public WebElement linkedinIcon;

    public void clickOnTwitter(){
        twitterIcon.click();
    }
    public void clickOnFacebook(){
        facebookIcon.click();
    }
    public void clickOnLinkedin(){
        linkedinIcon.click();
    }
}
