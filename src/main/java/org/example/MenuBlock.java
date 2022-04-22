package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MenuBlock extends BaseView {
    public MenuBlock(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@class='nav-element__burger j-menu-burger-btn j-wba-header-item']")
    public WebElement menuButton;

    @Step("Клик на кнопку Меню")
    public MenuBlock menuClick() {
        menuButton.click();
        return new MenuBlock(driver);
    }

    @FindBy(xpath = "//a[contains(@href,'https://www.wildberries.ru/catalog/zhenshchinam')]")
    public WebElement productsMenu;

    @Step("Клик на кнопку Женщинам")
    public MenuBlock productsMenuClick(){
        productsMenu.click();
        return new MenuBlock(driver);
    }

    @FindBy(xpath = "//a[@class='j-menu-item']")
    public WebElement products;

    @Step("Клик на кнопку Джинсы")
    public MenuBlock clickProducts() {
        products.click();
        return new MenuBlock(driver);
    }

    @FindBy(xpath = "//img[@class='j-thumbnail thumbnail']")
    public WebElement productsList;

    @Step("Выбрать товар")
    public MenuBlock clickProductsList() {
        productsList.click();
        return new MenuBlock(driver);
    }

    @FindBy(xpath = "//li[@class='sizes-list__item']/label[contains(@class,'j-size')]")
    public WebElement sizesList;

    @Step("Выбрать размер")
    public MenuBlock selectSize() {
        sizesList.click();
        return new MenuBlock(driver);
    }

    @FindBy(xpath = "//button[@class='btn-main']")
    public WebElement addToCartButton;

    @Step("Клик на кнопку Добавить в корзину")
    public MenuBlock clickAddToCartButton() {
        addToCartButton.click();
        return new MenuBlock(driver);
    }
}


