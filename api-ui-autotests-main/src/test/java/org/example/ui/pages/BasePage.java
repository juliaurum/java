package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class BasePage {
    public final static String TITLE_TEXT = "PRODUCT STORE";

    public static SelenideElement pageTitle = $x("//*[@id='nava']"),
            authModal = $x("//*[@class='modal-dialog' and @role='document'][.//div[@class='modal-header']/h5[text()='Log in']]"),
            loginButton = $x("//li[.//a[text()='Log in']]"),
            logoutButton = $x("//li[.//a[text()='Log out']]"),
            homeLink = $x("//a[@class='nav-link' and contains(text(),'Home')]"),
            cartLink = $x("//a[@id='cartur']");

    @Step("Открыть диалоговое окно аутентификации")
    public BasePage openAuthModal() {
        loginButton.shouldHave(Condition.visible)
                .click();
        authModal.shouldHave(Condition.visible);
        return this;
    }

    @Step("Открыть страницу корзины")
    public BasePage openCart() {
        cartLink.shouldHave(Condition.enabled)
                .scrollTo()
                .click();
        return this;
    }

    @Step("Разлогиниться")
    public BasePage logOut() {
        logoutButton.shouldHave(Condition.visible)
                .click();
        return this;
    }

    @Step("Перейти на главную страницу")
    public BasePage openHomeLink() {
        homeLink.shouldHave(Condition.enabled)
                .click();
        pageTitle.shouldHave(Condition.visible)
                .shouldHave(Condition.text(TITLE_TEXT));
        return this;
    }
}
