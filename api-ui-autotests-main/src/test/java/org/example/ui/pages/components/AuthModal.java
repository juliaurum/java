package org.example.ui.pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static org.example.ui.pages.BasePage.authModal;

public class AuthModal {
    public static SelenideElement userInput = $x("//input[@id='loginusername']"),
            passInput = $x("//input[@id='loginpassword']"),
            submitLoginButton = $x("//button[@onclick='logIn()']");
    @Step("Ввести логин")
    public AuthModal setLogin(String login) {
        authModal.shouldHave(Condition.visible);
        userInput.shouldHave(Condition.visible)
                .setValue(login);
        return this;
    }

    @Step("Ввести пароль")
    public AuthModal setPassword(String password) {
        authModal.shouldHave(Condition.visible);
        passInput.shouldHave(Condition.visible)
                .setValue(password);
        return this;
    }

    @Step("Нажать кнопку логина")
    public AuthModal clickSubmitLoginButton() {
        authModal.shouldHave(Condition.visible);
        submitLoginButton.shouldHave(Condition.visible)
                .click();
        authModal.shouldHave(Condition.disappear);
        return this;
    }
}
