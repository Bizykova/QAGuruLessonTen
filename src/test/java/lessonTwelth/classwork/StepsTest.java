package lessonTwelth.classwork;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class StepsTest {
    @BeforeEach
    public void setUp(){
        SelenideLogger.addListener("allure",new AllureSelenide());

    }

    private final String REPOSITORY = "eroshenkoam/allure-example";
    private final int ISSUES = 80;

    @Test
    public void selenideTest() {
        step("Открываем главную страницу", () -> {
            open("https://github.com/");
        });
        step("Ищем репозиторий" + REPOSITORY, () -> {
            $(".search-input-container span.flex-1").click();
            $(".search-input-container input[type=text]").setValue(REPOSITORY).pressEnter();
        });
        step("кликнуть по ссылке репозитория" + REPOSITORY, () -> {
            $(".Box-sc-g0xbh4-0.search-title a").click();
        });
        step("Открываем tab Issues"+ ISSUES, () -> {
            $("ul.UnderlineNav-body a#issues-tab").click();
        });
        step("Проверяем наличие Issues с номером " + ISSUES, () -> {
            $(withText("#"+ISSUES)).shouldHave(text("80"));
        });
    }

    @Test
    public void testAnnotatedStep() {
        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink();
        steps.openIssuesTab();
        steps.shuuldSeeIsssueWithNumber(ISSUES);
    }
}
