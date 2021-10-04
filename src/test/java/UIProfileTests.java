import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UIProfileTests {
    static WebDriver webDriver;

    private By header = By.className("main-header");
    private By notLogin = By.id("notLoggin-label");
    private By loginButton = By.xpath("//*[@id=\"notLoggin-label\"]/a[1]");
    private By registerButton = By.xpath("//*[@id=\"notLoggin-label\"]/a[2]");

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Airis\\IdeaProjects\\Innopolis_Final_Project\\src\\main\\resources\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.demoqa.com/profile");
    }

    @Test
    void ProfilePageTests() {
        Assertions.assertEquals("Profile", webDriver.findElement(header).getText());
        Assertions.assertTrue(webDriver.findElement(header).isDisplayed());
        Assertions.assertTrue(webDriver.findElement(notLogin).isDisplayed());
        webDriver.quit();
    }

    @Test
    void clickLogin() throws InterruptedException {
        webDriver.findElement(loginButton).click();
        Thread.sleep(5000);
        Assertions.assertEquals("https://www.demoqa.com/login", webDriver.getCurrentUrl());
        webDriver.quit();
    }

    @Test
    void clickRegister() throws InterruptedException {
        webDriver.findElement(registerButton).click();
        Thread.sleep(5000);
        Assertions.assertEquals("https://www.demoqa.com/register", webDriver.getCurrentUrl());
        webDriver.quit();
    }
}
