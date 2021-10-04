import BookStorePages.Login;
import BookStorePages.StaticLocators.Locators;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import static BookStorePages.StaticLocators.Locators.*;


@Epic("UITest")
@Feature("BookStoreUITest")
@Tag("ui")
public class UILoginTests {

    static WebDriver webDriver;
    Login login = new Login(webDriver);

    @BeforeAll
//    @Step("SetUp")
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Airis\\IdeaProjects\\Innopolis_Final_Project\\src\\main\\resources\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
//        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get("https://www.demoqa.com/login");
    }

    @Test
    public void loginAuthorizationTests1() throws InterruptedException {
        login.inputUserName("Username10!");
        login.inputPassword("Username10!");
        login.clickLoginBtn();
        Thread.sleep(5000);
        Assertions.assertEquals("https://www.demoqa.com/profile", webDriver.getCurrentUrl());
    }

    @Test
    void loginPageStaticTests() {
        Assertions.assertEquals("Login", webDriver.findElement(loginHeader).getText());
        Assertions.assertTrue(webDriver.findElement(loginHeader).isDisplayed());
        Assertions.assertEquals("Welcome,", webDriver.findElement(titleWelcome).getText());
        Assertions.assertTrue(webDriver.findElement(titleWelcome).isDisplayed());
        Assertions.assertEquals("Login in Book Store", webDriver.findElement(titleLoginInBookStore).getText());
        Assertions.assertTrue(webDriver.findElement(titleLoginInBookStore).isDisplayed());
        Assertions.assertEquals("ToolsQA", webDriver.getTitle());
        webDriver.quit();
    }
    @Test
    public void loginNewUserTests() throws InterruptedException {
        webDriver.findElement(newUser).click();
        Thread.sleep(5000);
        Assertions.assertEquals("https://www.demoqa.com/register", webDriver.getCurrentUrl());
        webDriver.quit();
    }
}
