package lesson_eleven.homework11;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;


public class CheckFiles {
    String testFolderPath = "src/test/resources/";
    @Test
    public void  pdfTest() throws Exception{
        InputStream is = new FileInputStream(testFolderPath+"newtestFile.pdf");
        PDF pdfActual = new PDF(is);
        InputStream is2 = new FileInputStream(testFolderPath+"testFile.pdf");
        PDF pdfExpected = new PDF(is2);
        Assertions.assertEquals(pdfExpected.text,pdfActual.text);

    }
    @Test
    public void  csvTest() throws Exception{
        InputStream is = new FileInputStream(testFolderPath+"newtestFile.csv");
        CSVReader csvActual = new CSVReader(new InputStreamReader(is));
        Assertions.assertTrue(csvActual.readNext()[0].contains("1"), String.valueOf(true));
    }

    @Test
    public void  exlTest() throws Exception{
        FileInputStream is = new FileInputStream(testFolderPath+"newtestFile.xlsx");
        XLS xlsActual = new XLS (is);
        Cell cell = xlsActual.excel.getSheetAt(0).getRow(0).getCell(0);
        Assertions.assertEquals(
                cell.getNumericCellValue(), 1.0
        );
    }

}
