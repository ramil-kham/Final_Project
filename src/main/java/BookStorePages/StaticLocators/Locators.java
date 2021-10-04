package BookStorePages.StaticLocators;

import org.openqa.selenium.By;

public class Locators {
    //LoginPage locators
    public static By loginHeader = By.className("main-header");
    public static By titleWelcome = By.xpath("//*[@id=\"userForm\"]/div[1]/h2");
    public static By titleLoginInBookStore = By.xpath("//*[@id=\"userForm\"]/div[1]/h5");
    public static By newUser = By.id("newUser");
}
