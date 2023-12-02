package lesson_eleven.classwerk;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideFilesTest {
    @BeforeEach
    void setUp(){
        SelenideLogger.addListener("allure",new AllureSelenide());
        Configuration.pageLoadStrategy = "eager";
    }
    @Test
    void downloadFileTest() throws IOException {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File download = $("[href = 'https://github.com/junit-team/junit5/raw/main/README.md']").download();
       // String dataAsString = FileUtils.readFileToString(download, StandardCharsets.UTF_8);

        try (InputStream is = new FileInputStream(download)) {
            byte[] data = is.readAllBytes();
            String dataAsString = new String(data, StandardCharsets.UTF_8);
            Assertions.assertTrue(dataAsString.contains("Official CI build server for JUnit 5. Used to perform quick checks"));
        }
    }

    @Test
    void uploadFileTest(){
        open("https://fineuploader.com/demos.html");
        $("input[type='file']").uploadFromClasspath("folder/rous.png");
        $(".qq-uploader-selector").shouldHave(text("rous.png"));

    }
}
