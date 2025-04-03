package org.example.ui;

import com.github.javafaker.Faker;
import org.example.ui.pages.CartPage;
import org.example.ui.pages.MainPage;
import org.example.ui.pages.ProductPage;
import org.example.ui.pages.components.AuthModal;
import org.example.ui.pages.components.PlaceOrderModal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class UITests extends TestBase {
    MainPage mainPage = new MainPage();
    AuthModal authModal = new AuthModal();
    ProductPage productPage = new ProductPage();

    CartPage cartPage = new CartPage();
    PlaceOrderModal placeOrderModal = new PlaceOrderModal();

    @DisplayName("Проверить, что цена продукта совпадает в карточке продукта и корзине, в карточке продукта и в форме заказа")
    @Tag("Main_test")
    @Test
    public void fillFormTest() {
        mainPage.openPage()
                .openAuthModal();
        authModal.setLogin("julaurum")
                .setPassword("LDU2024!a")
                .clickSubmitLoginButton();
        mainPage.comeToSamsungGalaxyS6();
        String priceInProductCard = productPage.getPrice().replace("$", "")
                .replace("*includes tax", "");
        productPage.addProductToCart()
                .closeAddProductToCartNotification()
                .openCart();
        cartPage.checkProductPrice(priceInProductCard)
                .clickPlaceOrderButton();
        placeOrderModal.setName("Julia Aurum")
                .setCountry(new Faker().address().country())
                .setCity(new Faker().address().city())
                .setCreditCard(new Faker().finance().creditCard())
                .setMonth(String.valueOf(new Faker().number().numberBetween(new Date().getMonth() + 1, 12)))
                .setYear(String.valueOf(new Faker().number().numberBetween(1900 + new Date().getYear(), 1900 + new Date().getYear() + 1)))
                .clickPurchaseButton();
        cartPage.checkProductPriceInNotification(priceInProductCard)
                .clickOkInNotification();
        mainPage.logOut();
    }
}
