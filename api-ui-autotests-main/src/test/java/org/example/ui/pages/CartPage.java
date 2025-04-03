package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class CartPage {
    public static SelenideElement priceCell = $x("//tbody/tr[1]/td[3]"),
            placeOrderModal = $x("//div[@id='orderModal']//div[@class='modal-dialog']"),
            placeOrderButton = $x("//button[@data-target='#orderModal']"),
            purchaseNotification = $x("//div[contains(@class, 'showSweetAlert')]"),
            okInPurchaseNotification = $x("//div[contains(@class, 'showSweetAlert')]//button[text()='OK']"),
            detailsInPurchaseNotification = $x("//div[contains(@class, 'showSweetAlert')]//p");


    @Step("Проверить цену товара в корзине")
    public CartPage checkProductPrice(String price) {
        priceCell.shouldHave(Condition.visible)
                .shouldHave(Condition.text(price));
        return this;
    }

    @Step("Нажать на кнопку 'Оформить заказ'")
    public CartPage clickPlaceOrderButton() {
        placeOrderButton.shouldHave(Condition.visible)
                .click();
        return this;
    }

    @Step("Нажать на кнопку 'OK' в форме заказе")
    public CartPage clickOkInNotification() {
        purchaseNotification.shouldHave(Condition.appear);
        okInPurchaseNotification.shouldHave(Condition.visible);
        sleep(2000);
        okInPurchaseNotification.click();
        purchaseNotification.shouldHave(Condition.disappear);
        placeOrderModal.shouldHave(Condition.disappear);
        return this;
    }

    @Step("Проверить цену товара в форме заказа")
    public CartPage checkProductPriceInNotification(String price) {
        purchaseNotification.shouldHave(Condition.appear);
        detailsInPurchaseNotification.shouldHave(Condition.visible)
                .shouldHave(Condition.text("Amount: " + price));
        return this;
    }


}
