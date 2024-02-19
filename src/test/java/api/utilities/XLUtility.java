package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workBook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;

	public XLUtility(String path) {
		this.path = path;
	}

	public int getRowCount(String sheetName) throws IOException {
		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		sheet = workBook.getSheet(sheetName);
		int rowcount = sheet.getLastRowNum();
		workBook.close();
		fi.close();
		return rowcount;
	}

	public int getCellCount(String sheetName, int rownum) throws IOException {
		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		workBook.close();
		fi.close();
		return cellcount;
	}

	public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);

		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}

		workBook.close();
		fi.close();
		return data;
	}

	public String setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {

		File xlfile = new File(path);
		if (!xlfile.exists()) {
			workBook = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			workBook.write(fo);
		}

		fi = new FileInputStream(path);
		workBook = new XSSFWorkbook(fi);

		if (workBook.getSheetIndex(sheetName) == -1) {
			workBook.createSheet(sheetName);
			sheet = workBook.getSheet(sheetName);
		}
		if (sheet.getRow(rownum) == null) {
			sheet.createRow(rownum);
			row = sheet.getRow(rownum);
		}

		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		workBook.write(fo);
		workBook.close();
		fi.close();
		fo.close();
		return data;
	}
	
	public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
	    fi = new FileInputStream(path);
	     workBook = new XSSFWorkbook(fi);
         sheet = workBook.getSheet(sheetName);
         
         
	    row = sheet.getRow(rownum);
	    cell = row.getCell(colnum);

	    style = workBook.createCellStyle();
	    style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
	    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	    cell.setCellStyle(style);

	    workBook.write(fo);
	    workBook.close();
	    fi.close();
	    fo.close();
	}

	public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
	    fi = new FileInputStream(path);
	    workBook = new XSSFWorkbook(fi);
        sheet = workBook.getSheet(sheetName);
        
	     row = sheet.getRow(rownum);
	     cell = row.getCell(colnum);

	    style = workBook.createCellStyle();
	    style.setFillForegroundColor(IndexedColors.RED.getIndex());
	    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	    cell.setCellStyle(style);

	    workBook.write(fo);
	    workBook.close();
	    fi.close();
	}

}
