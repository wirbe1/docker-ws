package de.telekom.supersite;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit.SoftAsserts;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.AssertionMode.SOFT;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SuperTest {

    @Rule
    public SoftAsserts softAsserts = new SoftAsserts();

    @BeforeClass
    public static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--proxy-server='direct://'");
        options.addArguments("--proxy-bypass-list=*");
        Configuration.browserCapabilities = new DesiredCapabilities(options);
        Configuration.assertionMode = SOFT;
        Configuration.remote = "http://chrome:4444/wd/hub";
        //Configuration.remote = "http://localhost:4444/wd/hub";


    }

    @Test
    public void userCanSearchAnyKeyword() {
        String targetHost = System.getProperty("targetHost");;
        //String targetHost = "172.17.0.2";
        System.out.println("Target Host: " + targetHost);
        open("http://" + targetHost);
        sleep(10000);

        for (int i = 0; i < $$(By.tagName("a")).size(); i++) {
            checkTag(i);
            back();
        }

    }

    @Step
    public void checkTag(int i){
        $$(By.tagName("a")).get(i).click();
        $(By.id("test_me")).shouldHave(text("I`m gonna be tested"));
        System.out.println("#" + i + " tested");
    }




}
