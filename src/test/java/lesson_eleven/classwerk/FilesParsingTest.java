package lesson_eleven.classwerk;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.xlstest.XLS;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FilesParsingTest {

    @BeforeEach
    void setUp(){
        SelenideLogger.addListener("allure",new AllureSelenide());
        Configuration.pageLoadStrategy = "eager";
    }
    @Test
    void  pdfFileParsingTest () throws Exception {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File downloaded = $("[href='junit-user-guide-5.10.1.pdf']").download();
            PDF pdf = new PDF(downloaded);
        Assertions.assertEquals("Stefan Bechtold, Sam Brannen, Johannes Link, Matthias Merdes, Marc Philipp, Juliette de Rancourt, Christian Stein",pdf.author);

        }

//        @Test
//    void xlsFileParsingTest() throws Exception{
//        open("https://excelvba.ru/programmes/Teachers&ysclid=ifcu77j9j9951587711");
//        File downloaded = $("[href='https://ExcelVBA.ru/sites/default/files/teachers.xls']").download();
//            XLS xls = new XLS(downloaded);
//            String actualresult = xls.excel.getSheetAt(0).getRow(3).getCell(2).getStringCellValue();
//            Assertions.assertTrue(actualresult.contains("Cумарное количество часов"));
//
//        }
    }

