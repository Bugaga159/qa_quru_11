package ru.example.hw9;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.assertj.core.api.Assertions.assertThat;

public class ZipFilesTest {

    @Test
    void zipFile() throws Exception {
        ZipFile zip = new ZipFile("src/test/resources/files/archive.zip");

        ZipEntry csvEntry = zip.getEntry("example.csv");
        try (InputStream csvStream = zip.getInputStream(csvEntry)) {
            CSVReader reader = new CSVReader(new InputStreamReader(csvStream));
            List<String[]> list = reader.readAll();
            assertThat(list).hasSize(3).contains(
                    new String[] {"Author", "Book"},
                    new String[] {"Block", "Apteka"},
                    new String[] {"Esenin", "Cherniy Chelovek"});
        }

        ZipEntry XlsEntry = zip.getEntry("test.xlsx");
        try(InputStream xlsStream = zip.getInputStream(XlsEntry)) {
            XLS parsed = new XLS(xlsStream);
            assertThat(parsed.excel.getSheetAt(0)
                    .getRow(1).getCell(1).getStringCellValue())
                    .isEqualTo("test");
        }

        ZipEntry PdfEntry = zip.getEntry("junit.pdf");
        try(InputStream pdfStream = zip.getInputStream(PdfEntry)) {
            PDF parsed = new PDF(pdfStream);
            assertThat(parsed.text).contains("JUnit 5 User");
        }
    }
}
