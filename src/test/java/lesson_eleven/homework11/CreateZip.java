package lesson_eleven.homework11;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateZip {
    @BeforeEach
    void setUp(){
        SelenideLogger.addListener("allure",new AllureSelenide());
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    public void checkFiles() {
        String filenamePDF = "testFile.pdf";
        String filenameXLSX = "testFile.xlsx";
        String filenameCSV = "testFile.csv";
        String testFolderPath = "src/test/resources/";
        String filepathPDF = testFolderPath + filenamePDF;
        String filepathXLSX = testFolderPath + filenameXLSX;
        String filepathCSV = testFolderPath + filenameCSV;
        String filenameZIP = testFolderPath + "output.zip";
        try (
                ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(filenameZIP));
                FileInputStream fisPDF = new FileInputStream(filepathPDF);
                FileInputStream fisXLSX = new FileInputStream(filepathXLSX);
                FileInputStream fisCSV = new FileInputStream(filepathCSV);
        ) {
            ZipEntry entry1 = new ZipEntry(filenamePDF);
            zout.putNextEntry(entry1);
            // считываем содержимое файла в массив byte
            byte[] buffer = new byte[fisPDF.available()];
            fisPDF.read(buffer);
            // добавляем содержимое к архиву
            zout.write(buffer);

            ZipEntry entry2 = new ZipEntry(filenameXLSX);
            zout.putNextEntry(entry2);
            byte[] buffer2 = new byte[fisXLSX.available()];
            fisXLSX.read(buffer2);
            zout.write(buffer2);

            ZipEntry entry3 = new ZipEntry(filenameCSV);
            zout.putNextEntry(entry3);
            byte[] buffer3 = new byte[fisCSV.available()];
            fisCSV.read(buffer3);
            zout.write(buffer3);

            // закрываем текущую запись для новой записи
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}
