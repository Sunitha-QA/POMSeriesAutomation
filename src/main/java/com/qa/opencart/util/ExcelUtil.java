package com.qa.opencart.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
public class ExcelUtil {

	private static final String TEST_DATA_PATH = "./src/test/resources/testdata/producttestdata.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	private static Object data[][];

	public static Object[][] getExcelTestData(String sheetName) {
		
		
		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_PATH);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);
			data =new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int i=0;i<sheet.getLastRowNum();i++) {
				for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
					data[i][j]=sheet.getRow(i+1).getCell(j).toString();
					
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
}
