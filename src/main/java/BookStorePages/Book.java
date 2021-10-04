package BookStorePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Book {
    WebDriver webDriver;

    public Book(WebDriver webDriver) {
        PageFactory.initElements(webDriver,this);
        this.webDriver = webDriver;
    }

    @FindBy(css = ".text-right.fullButton > button")
    private WebElement addToYourCollectionBtn;

    public WebElement getAddToYourCollectionBtn() {
        return addToYourCollectionBtn;
    }

    @FindBy(css = ".text-left.fullButton > button")
    private WebElement backToBookStoreBtn;

    public void clickToAddToYourCollectionBtn() {
        addToYourCollectionBtn.click();
    }

    public void clickToBackToBookStoreBtn() {
        backToBookStoreBtn.click();
    }
}
