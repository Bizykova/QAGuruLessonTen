package annotations;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import java.util.stream.Stream;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HomeWork {
    SelenideElement email = $("#email");
    SelenideElement password = $("#password");

    @BeforeEach
    void BeforeEach() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
    }


    @Test
    @DisplayName("amazon ввести harry potter получить image на странице")
    void amazonImageTest() {
        open("https://www.amazon.com/");
        $("#twotabsearchtextbox").setValue("harry potter").pressEnter();
        $$(".a-carousel-card.s-visual-card-navigation-carousel-card")
                .shouldBe(sizeGreaterThan(0));
    }


    @ValueSource(strings = {
            "Стиральная машина", "Кофемашина", "Телефон"})
    @ParameterizedTest
    @DisplayName("в строке поиска найти:Стиральная машина,Кофемашина, Телефон")
    public void findTheValuesInTheSearchBar(String value) {
        open("https://www.citilink.ru/");
        $("[name=text]").setValue(value).pressEnter();
        $("h1").shouldHave(visible);

    }


    @CsvSource(value = {
            "Стиральная машина, Стиральная машина",
            "Кофемашина , Кофемашина",
            "Телефон ,Телефон",
    })
    @ParameterizedTest
    @DisplayName("двойной аргумент параметризированный тест")
    void doubleArgument_findTheValuesInTheSearchBar(String value, String expected) {
        open("https://www.citilink.ru/");
        $("[name=text]").setValue(value).pressEnter();
        $("h1").shouldHave(text(expected));

    }


    @CsvFileSource(resources = "/test_date/param_test.csv")
    @ParameterizedTest(name = "первый аогумент(0) второй аргумент(1)")
    @DisplayName("двойной аргумент параметризированный тест")
    @Tag("citilink")
    void doubleArgument_findTheValuesInTheSearchBar_Filed(String value, String expected) {
        open("https://www.citilink.ru/");
        $("[name=text]").setValue(value).pressEnter();
        $("h1").shouldHave(text(expected));

    }

    public static Stream<Arguments> checkingPhoneNameParameterEntry() {
        return Stream.of(
                Arguments.of("masha@test.ru", "Masha123"),
                Arguments.of("ivan@test.ru", "Ivan123"),
                Arguments.of("dima@test.ru", "Dima123")
        );
    }

    @MethodSource("checkingPhoneNameParameterEntry")
    @ParameterizedTest
    @DisplayName("Проверка ввода параметра имени и телефона")
    public void сheckingPhoneNameParameterEntryTest(String email, String password) {
        open("https://lm.skillbox.cc/qa_tester/module06/auth/index.html");
        $("#email").setValue(email);
        $("#password").setValue(password);
        $(".form-submit").click();
        $(".form-error").shouldHave(text("Такой email не зарегистрирован"));


    }
}

