package annotations;

import annotations.data.Language;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ClassWork {

    @BeforeEach
    void BeforeEach() {
        Configuration.pageLoadStrategy = "eager";
    }


    @ValueSource(strings = {
            "google", "allure", "selenide"})
    @ParameterizedTest
    @DisplayName("простой параметризированный тест")
    void successfulSearchTest(String value) {
        open("https://www.google.com/");
        $("[name=q]").setValue(value).pressEnter();
        $("#rso").shouldHave(visible);
    }


    @CsvSource(value = {
            "wildberries, https://www.wildberries.ru",
            "junit, https://junit.org",
            "selenide, https://ru.selenide.org"
    })
    @ParameterizedTest(name = "двойной аргумент параметризированный тест")
    void successTest(String value, String expected) {
        open("https://www.google.com/");
        $("[name=q]").setValue(value).pressEnter();
        $("#rso").shouldHave(text(expected));
    }


    @CsvFileSource(resources = "/test_date/param_test.csv")
    @ParameterizedTest
    @DisplayName("двойной аргумент параметризированный тест")
    @Tag("Smock")
    void doubleArgument_findTheValuesInTheSearchBarTest(String value, String expected) {
        open("https://www.citilink.ru/");
        $("[name=text]").setValue(value).pressEnter();
        $("h1").shouldHave(text(expected));

    }

    static Stream<Arguments>selenideSiteShouldDisplayCorrectTestButtonsTest() {
        return Stream.of(
                Arguments.of(Language.EN,List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes")
                ),
                Arguments.of(Language.RU,List.of("С чего начать", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы")
                ));
    }

    @MethodSource
    @ParameterizedTest
    void selenideSiteShouldDisplayCorrectTestButtonsTest(Language language, List<String> expected) {
        open("https://ru.selenide.org/");
        $$("#languages a").find(text(language.name())).click();
        $$(".main-menu-pages a").filter(visible).shouldHave(texts(expected));
    }

}
