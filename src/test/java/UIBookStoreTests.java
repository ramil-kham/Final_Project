import BookStorePages.BookStore;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class UIBookStoreTests {
    WebDriver webDriver;
    BookStore bookStore;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Airis\\IdeaProjects\\Innopolis_Final_Project\\src\\main\\resources\\chromedriver.exe");
        webDriver = new ChromeDriver();
        bookStore = new BookStore(webDriver);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        webDriver.get("https://www.demoqa.com/books");
    }

    @Test
    void searchByValidTitleTest() {
        List<String> titles = bookStore.getBookTitles();
        String title = titles.get(RandomUtils.nextInt(0, titles.size()));
        bookStore.findBooks(title);
        System.out.println(title);
        Assertions.assertEquals(title, bookStore.getBookTitles().get(0));
        webDriver.quit();
    }

    @Test
    void searchByInvalidTitleTest() {
        bookStore.findBooks(RandomStringUtils.randomAlphabetic(5));
        Assertions.assertEquals(0, bookStore.getBookTitles().size());
        webDriver.quit();
    }

    @Test
    void check5RowsSelectorTest() {
        bookStore.selectRow("5");
        Assertions.assertEquals(5, bookStore.getBookTitles().size());
        Assertions.assertFalse(bookStore.checkEnabledPreviousButton());
        Assertions.assertTrue(bookStore.checkEnabledNextButton());
        webDriver.quit();
    }

    @Test
    void checkChangeNumberOfPage() {
        bookStore.selectRow("5");
        bookStore.clickNextButton();
        Assertions.assertEquals("2", bookStore.getPageFieldValue());
        Assertions.assertTrue(bookStore.checkEnabledPreviousButton());
        Assertions.assertFalse(bookStore.checkEnabledNextButton());
        webDriver.quit();
    }
}
