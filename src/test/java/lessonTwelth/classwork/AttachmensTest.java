package lessonTwelth.classwork;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class AttachmensTest {
    @Test
    public void selenideAttachments() {
        step("Открываем главную страницу", () -> {
            open("https://github.com/");
            attachment("Source", webdriver().driver().source());
        });

    }

    @Test
    public void testAnnotatedAttachments() {
        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.takeScreenshot();
    }
}
