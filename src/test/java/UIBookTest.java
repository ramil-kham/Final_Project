import BookStorePages.Book;
import BookStorePages.BookStore;
import BookStorePages.Login;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pojo.CollectionOfIsbn;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UIBookTest {
    static WebDriver webDriver;
    BookStore bookStore = new BookStore(webDriver);
    Book book = new Book(webDriver);
    Login login = new Login(webDriver);

    @BeforeAll
//    @Step("SetUp")
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Airis\\IdeaProjects\\Innopolis_Final_Project\\src\\main\\resources\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
//        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String isbnRandomBook = CollectionOfIsbn.getRandomIsbn();
        webDriver.get("https://www.demoqa.com/books?book=" + isbnRandomBook);
    }

    @Test
    void addBookToCollection() throws InterruptedException {
        login.clickLoginBtn();
        login.inputUserName("Username10!");
        login.inputPassword("Username10!");
        login.clickLoginBtn();
        Thread.sleep(5000);
        bookStore.scrollToElement(book.getAddToYourCollectionBtn());
        book.clickToAddToYourCollectionBtn();
        Thread.sleep(5000);
        Alert alert = webDriver.switchTo().alert();
        alert.accept();
        webDriver.get("https://www.demoqa.com/profile");
        List<String> titles = bookStore.getBookTitles();
        String title = titles.get(RandomUtils.nextInt(0, titles.size()));
        bookStore.findBooks(title);
        System.out.println(title);
        Assertions.assertEquals(title, bookStore.getBookTitles().get(0));
    }
    /*
    @Test
    void backToBookStore() throws InterruptedException {
        login.clickLoginBtn();
        login.inputUserName("Username10!");
        login.inputPassword("Username10!");
        login.clickLoginBtn();
        Thread.sleep(3000);
        bookStore.scrollToElement(book.getAddToYourCollectionBtn());
        Thread.sleep(3000);
        webDriver.findElement(By.cssSelector(".text-left.fullButton > button")).click();
//        book.clickToBackToBookStoreBtn();
        Assertions.assertEquals("https://www.demoqa.com/books", webDriver.getCurrentUrl());
    }*/
}
