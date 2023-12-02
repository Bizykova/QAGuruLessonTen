package lesson_eleven.homework11;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipFile {
    @BeforeEach
    void setUp(){
        SelenideLogger.addListener("allure",new AllureSelenide());
        Configuration.pageLoadStrategy = "eager";
    }
    @Test
    void unZipFile(){
        String testFolderPath = "src/test/resources/";
        String filenameZIP = testFolderPath + "output.zip";
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(filenameZIP))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {

                name = entry.getName(); // получим название файла
                System.out.printf("File name: %s \n", name);

                // распаковка
                FileOutputStream fout = new FileOutputStream(testFolderPath + "new" + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}
