package ec.edu.uce.algorithms;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Utility class to save results in CSV files (results/tables/).
 * The CSV files can be opened in Excel to build the tables and charts of the report.
 */
public class CsvWriter {

    public static final String TABLES_FOLDER = "results/tables/";

    public static void write(String fileName, String header, List<String> rows) {
        File file = new File(TABLES_FOLDER + fileName);
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            pw.println(header);
            for (String row : rows) {
                pw.println(row);
            }
            System.out.println("CSV generated at: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error writing " + fileName + ": " + e.getMessage());
        }
    }
}