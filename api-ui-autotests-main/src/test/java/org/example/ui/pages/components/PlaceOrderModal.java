package org.example.ui.pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static org.example.ui.pages.CartPage.placeOrderModal;

public class PlaceOrderModal {
    public static SelenideElement nameInput = $x("//input[@id='name']"),
            countryInput = $x("//input[@id='country']"),
            cityInput = $x("//input[@id='city']"),
            creditCardInput = $x("//input[@id='card']"),
            monthInput = $x("//input[@id='month']"),
            yearInput = $x("//input[@id='year']"),
            purchaseButton = $x("//button[@onclick='purchaseOrder()']"),
            closeButton = $x("//div[@id='orderModal']//button[text()='Close']");

    @Step("Ввести имя")
    public PlaceOrderModal setName(String name) {
        placeOrderModal.shouldHave(Condition.visible);
        nameInput.scrollTo()
                .shouldHave(Condition.visible)
                .setValue(name);
        return this;
    }

    @Step("Ввести страну")
    public PlaceOrderModal setCountry(String country) {
        placeOrderModal.shouldHave(Condition.visible);
        countryInput.scrollTo()
                .shouldHave(Condition.visible)
                .setValue(country);
        return this;
    }

    @Step("Ввести город")
    public PlaceOrderModal setCity(String city) {
        placeOrderModal.shouldHave(Condition.visible);
        cityInput.scrollTo()
                .shouldHave(Condition.visible)
                .setValue(city);
        return this;
    }

    @Step("Ввести номер кредитной карты")
    public PlaceOrderModal setCreditCard(String creditCard) {
        placeOrderModal.shouldHave(Condition.visible);
        creditCardInput.scrollTo()
                .shouldHave(Condition.visible)
                .setValue(creditCard);
        return this;
    }

    @Step("Ввести месяц")
    public PlaceOrderModal setMonth(String month) {
        placeOrderModal.shouldHave(Condition.visible);
        monthInput.scrollTo()
                .shouldHave(Condition.visible)
                .setValue(month);
        return this;
    }

    @Step("Ввести год")
    public PlaceOrderModal setYear(String year) {
        placeOrderModal.shouldHave(Condition.visible);
        yearInput.scrollTo()
                .shouldHave(Condition.visible)
                .setValue(year);
        return this;
    }

    @Step("Нажать кнопку 'Купить'")
    public PlaceOrderModal clickPurchaseButton() {
        placeOrderModal.shouldHave(Condition.visible);
        purchaseButton.shouldHave(Condition.visible)
                .scrollTo()
                .click();
        return this;
    }
}
