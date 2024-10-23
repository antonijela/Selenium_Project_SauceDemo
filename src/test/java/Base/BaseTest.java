package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    public static WebDriver driver;
    public LoginPage loginPage;
    public ProductsPage productsPage;
    public ProductDetailsPage productDetailsPage;
    public ShoppingCartPage shoppingCartPage;
    public BurgerMenuPage burgerMenuPage;
    public FooterPage footerPage;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        loginPage = new LoginPage();
        productsPage = new ProductsPage();
        productDetailsPage = new ProductDetailsPage();
        shoppingCartPage = new ShoppingCartPage();
        burgerMenuPage = new BurgerMenuPage();
        footerPage = new FooterPage();

    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
