package lesson_twelve.classwork;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {


    @Step("Открываем главную страницу")
    public void openMainPage(){
        SelenideLogger.addListener("allure",new AllureSelenide());
        Configuration.holdBrowserOpen = true;
        open("https://github.com/");
    }
    @Step("Ищем репозиторий {value}")
    public void searchForRepository(String repo){
        $(".search-input-container span.flex-1").click();
        $(".search-input-container input[type=text]").setValue(repo).pressEnter();
    }
    @Step("кликнуть по ссылке репозитория")
    public void clickOnRepositoryLink(){
        $(".Box-sc-g0xbh4-0.search-title a").click();
    }
    @Step ("Открываем tab Issues")
    public void openIssuesTab(){
        $("ul.UnderlineNav-body a#issues-tab").click();
    }
    @Step("Проверяем наличие Issues с номером { ISSUES}")
    public void shouldSeeIssueWithNumber(int issues){
        $(withText("#"+issues)).shouldHave(text("80"));
    }

    @Attachment(value = "Screenshot",type = "image/png",fileExtension = "png")
    public byte[] takeScreenshot(){
        return ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
