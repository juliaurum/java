package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;

public class ProductPage extends BasePage {
    public static SelenideElement productImage = $x("//div[@class='item active']/img"),
            addToCartButton = $x("//a[contains(text(),'Add to cart')]"),
            productPrice = $x("//h3[@class='price-container']");

    @Step("Нажать кнопку 'Добавить товар в корзину'")
    public ProductPage addProductToCart() {
        productImage.shouldHave(Condition.visible);
        addToCartButton.shouldHave(Condition.visible)
                .click();
        return this;
    }

    @Step("Закрыть уведомление о добавлении товара в корзину")
    public ProductPage closeAddProductToCartNotification() {
        if (switchTo().alert().getText().contains("Product added")) {
            switchTo().alert().accept();
        }
        return this;
    }

    @Step("Проверить цену товара")
    public ProductPage checkPrice(String price) {
        productImage.shouldHave(Condition.visible);
        productPrice.shouldHave(Condition.visible)
                .shouldHave(Condition.text(price));
        return this;
    }

    @Step("Запомнить цену товара")
    public String getPrice() {
        productImage.shouldHave(Condition.visible);
        productPrice.shouldHave(Condition.visible);
        return productPrice.getText();
    }

}
