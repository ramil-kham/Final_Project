package BookStorePages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class BookStore {
    WebDriver webDriver;

    public BookStore(WebDriver webDriver) {
        PageFactory.initElements(webDriver,this);
        this.webDriver = webDriver;
    }

    @FindBy(id = "searchBox")
    private WebElement searchBox;

    @FindBy(css = ".action-buttons > span > a")
    private List<WebElement> booksTitles;

    @FindBy(xpath = "//div[@class=\"rt-tr-group\"]//div[3][contains(text(), ' ')]")
    private List<WebElement> booksAuthors;

    @FindBy(xpath = "//div[@class=\"rt-tr-group\"]//div[4][contains(text(), ' ')]")
    private List<WebElement> booksPublishers;

    @FindBy(css = ".-previous > button")
    private WebElement previousButton;

    @FindBy(css = ".-next > button")
    private WebElement nextButton;

    @FindBy(css = ".-pageSizeOptions > select")
    private WebElement rowsSelector;

    @FindBy(css = ".-pageJump > input")
    private WebElement pageNumber;

    public void findBooks(String keys) {
        searchBox.sendKeys(keys);
    }

    public List<String> getBookTitles() {
        List<String> titles = new ArrayList<>();
        for (WebElement title : booksTitles) {
            titles.add(title.getText());
        }
        return titles;
    }

    public List<String> getBookAuthors() {
        List<String> authors = new ArrayList<>();
        for (WebElement author : booksAuthors) {
            authors.add(author.getText());
        }
        return authors;
    }

    public List<String> getBookPublishers() {
        List<String> publishers = new ArrayList<>();
        for (WebElement publisher : booksPublishers) {
            publishers.add(publisher.getText());
        }
        return publishers;
    }

    public void clickPreviousButton() {
        previousButton.click();
    }

    public void clickNextButton() {
        nextButton.click();
    }

    public Boolean checkEnabledPreviousButton() {
        return previousButton.isEnabled();
    }

    public Boolean checkEnabledNextButton() {
        return nextButton.isEnabled();
    }
    public void selectRow(String value){
        scrollToElement(rowsSelector);
        Select rows = new Select(rowsSelector);
        rowsSelector.click();
        rows.selectByValue(value);
    }

    public String getPageFieldValue() {
        return pageNumber.getAttribute("value");
    }

    public void scrollToElement(WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView();", webElement);
    }
}
