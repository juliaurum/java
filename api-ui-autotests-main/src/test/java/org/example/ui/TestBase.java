package org.example.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.example.ui.helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.awt.event.KeyEvent;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class TestBase {
    @BeforeAll
    public static void config() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/org/example/ui/driver/chromedriver.exe");
        Configuration.baseUrl = "https://www.demoblaze.com";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        WebDriver driver = new ChromeDriver();
        setWebDriver(driver);
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_F11);
            robot.keyRelease(KeyEvent.VK_F11);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        closeWebDriver();
    }
}
