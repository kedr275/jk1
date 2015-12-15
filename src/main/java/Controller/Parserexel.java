package Controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Created by jeka on 01.12.2015.
 */
public class Parserexel {

    public static String parser_name(int count, File FilePatch) {

        InputStream inputStream = null;
        XSSFWorkbook workBook = null;

        try {
            String filepatch = FilePatch.getAbsolutePath();
            inputStream = new FileInputStream(filepatch);
            workBook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //int qsheets = workBook.getNumberOfSheets();
        //for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
        //System.out.println("Sheet name: " + workBook.getSheetName(i));
        String SheetName = workBook.getSheetName(count);
        //}

        return SheetName;
    }
    public static int count_sheet(File FilePatch){

        //String fileName = GlobalVars.FileNameIP;
        //инициализируем потоки
        //String result = "";
        InputStream inputStream = null;
        XSSFWorkbook workBook = null;

        try {
            String filepatch = FilePatch.getAbsolutePath();
            inputStream = new FileInputStream(filepatch);
            workBook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }


        int count = workBook.getNumberOfSheets();

        return count;
    }



    public static String parse(File fileName, int RowNum) {

        String result = "";
        InputStream inputStream = null;
        XSSFWorkbook workBook = null;
        try {
            inputStream = new FileInputStream(fileName);
            workBook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  for (int i = 1; i<(workBook.getNumberOfSheets()); i++) {
        Sheet sheet = workBook.getSheetAt(0);
        int qsheets = workBook.getNumberOfSheets();
        Iterator<Row> it = sheet.iterator();

        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            while (cells.hasNext()) {
                Cell cell = cells.next();
                int cellType = cell.getCellType();

                if (row.getRowNum() == RowNum) {
                    if (cell.getColumnIndex() == 0) {
                        switch (cellType) {
                            case Cell.CELL_TYPE_STRING:
                                result += cell.getStringCellValue() + " ";
                                break;
//                            case Cell.CELL_TYPE_NUMERIC:
//                                result += "[" + cell.getNumericCellValue() + "]";
//                                break;

//                            case Cell.CELL_TYPE_FORMULA:
//                               result += "[" + cell.getNumericCellValue() + "]";
//                               break;
                            default:
//                                result += "|";
                                break;
                        }
                    }
                }
            }
//            result += "\n";
        }
        //  }

        return result;
    }

    public static String parse(File fileName, int i, int RowNum, int ColumnIndex) {

        String result = "";
        InputStream inputStream = null;
        XSSFWorkbook workBook = null;
        try {
            inputStream = new FileInputStream(fileName);
            workBook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  for (int i = 1; i<(workBook.getNumberOfSheets()); i++) {
        Sheet sheet = workBook.getSheetAt(i);
        int qsheets = workBook.getNumberOfSheets();
        Iterator<Row> it = sheet.iterator();

        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            while (cells.hasNext()) {
                Cell cell = cells.next();
                int cellType = cell.getCellType();

                if (row.getRowNum() == RowNum) {
                    if (cell.getColumnIndex() == ColumnIndex) {
                        switch (cellType) {
                            case Cell.CELL_TYPE_STRING:
                                result += cell.getStringCellValue() + " ";
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                result += cell.getNumericCellValue();
                                break;

                            //  case Cell.CELL_TYPE_FORMULA:
                            //   result += "[" + cell.getNumericCellValue() + "]";
                            //   break;
                            default:
                                result += "|";
                                break;
                        }
                    }
                }
            }
//            result += "\n";
        }
        //  }

        return result;
    }

    public static int count_row(int i, File FilePatch) {


        InputStream inputStream = null;
        XSSFWorkbook workBook = null;

        try {
            String filepatch = FilePatch.getAbsolutePath();
            System.out.println(GlobalVars.FileNameIP);
            inputStream = new FileInputStream(filepatch);
            workBook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            System.out.print("Parserexel line 177");
            e.printStackTrace();
        }
        Sheet sheet= workBook.getSheetAt(0);
        int count  = sheet.getLastRowNum();


        return count;
    }

    public static String string_substring(File FileName,int NumSheet, int NumRow, int NumCollumn, int Numreturn ) {

        String s = parse(FileName,NumSheet, NumRow,NumCollumn);
        String a[] = s.split("\n");
        System.out.println("**"+a[Numreturn]+"**");

        return a[Numreturn];
    }

}
