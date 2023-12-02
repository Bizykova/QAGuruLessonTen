package lessonTwelth.classwork;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideTest {
    @Test
    public void selenideTest(){
        SelenideLogger.addListener("allure",new AllureSelenide());
        open("https://github.com/");
        $(".search-input-container span.flex-1").click();
        $(".search-input-container input[type=text]").setValue("eroshenkoam/allure-example").pressEnter();
        $(".Box-sc-g0xbh4-0.search-title a").click();
        $("ul.UnderlineNav-body a#issues-tab").click();
        $("#issue_80").shouldHave(text("80"));

    }
}
