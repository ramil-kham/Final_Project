package BookStorePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class Login {
    WebDriver webDriver;

    public Login(WebDriver webDriver) {
        PageFactory.initElements(webDriver,this);
        this.webDriver = webDriver;
    }

    @FindBy(id = "userName")
    private WebElement userName;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login")
    private WebElement login;

    public void inputUserName(String login) {
        userName.sendKeys(login);
    }

    public void inputPassword(String pwd) {
        password.sendKeys(pwd);
    }

    public void clickLoginBtn() {
        login.click();
    }
}
