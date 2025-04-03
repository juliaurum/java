package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class MainPage extends BasePage {
    private final static String PATH = "https://www.demoblaze.com";
    public static SelenideElement samsungGalaxyS6Card = $x("//a[contains(text(),'Samsung galaxy s6')]");

    @Step("Открыть главную страницу")
    public MainPage openPage() {
        open(PATH);
        pageTitle.shouldHave(Condition.visible)
                .shouldHave(Condition.text(TITLE_TEXT));
        return this;
    }

    @Step("Перейти в карточку товара Samsung Galaxy S6")
    public MainPage comeToSamsungGalaxyS6() {
        samsungGalaxyS6Card.shouldHave(Condition.visible)
                .click();
        return this;
    }
}
