package lessonTwelth.classwork;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



public class LabelsTest {
    @Test
    @Feature("Issue в репозитории")
    @Story("Создание Issue")
    @Owner("bizyukova")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://testing.github.com")
    @DisplayName("Создание issueдля автоматизированного тестирования")
    public void testStaticLabels() {


    }

    @Test
    public void testDynamicLabels() {
        Allure.getLifecycle().updateTestCase(
                t -> t.setName("Создание issueдля автоматизированного тестирования");
                Allure.feature("Issue в репозитории");
                Allure.story("Создание Issue");
                Allure.label("owner","bizyukova");
                Allure.label("severity",SeverityLevel.CRITICAL.value());
                Allure.link( "Testing","https://testing.github.com");
    }
}