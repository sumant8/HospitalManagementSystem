package org.tyss.genericUtility;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
//read data from excel
public final class ExcelUtility {
	private Workbook workbook;
	//Initialize excel file
	/**
	 * method is use to get data from excel sheet 
	 * @param excelPath
	 */
	public void intiallizeExcellFile(String excelPath) {
		FileInputStream fisExcel=null;
		try {
			fisExcel=new FileInputStream(excelPath);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		try{
			workbook=WorkbookFactory.create(fisExcel);
		}	catch(EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
	}
	public String getDataFromExcel(int rowNumber, int cellNumber, String sheetName) {
		Sheet sheet=workbook.getSheet(sheetName);
		return new DataFormatter().formatCellValue(sheet.getRow(rowNumber).getCell(cellNumber));
	}
	/**
	 * method is used for two dimensional array
	 * @param SheetName
	 * @return
	 */
	public String[][] getMultipleDataFromExcel(String SheetName) {
		
		Sheet sheet = workbook.getSheet(SheetName);
		int rowcount=sheet.getLastRowNum();
		int cellcount=sheet.getRow(0).getLastCellNum();
		String[][] str= new String[rowcount+1][cellcount];
		for(int i=0;i<=rowcount;i++){
			for(int j=0;j<cellcount;j++) {
		str[i][j]= new DataFormatter().formatCellValue(sheet.getRow(i).getCell(j));
			}
		}
		return str;
	}
	/**
	 * method is used save data into excel
	 * @param excelsaveFilePath
	 */
	public void setDataIntoExcel1(int rowNumber, int cellNumber, String sheetName ,String value) {
		Sheet sheet=workbook.getSheet(sheetName);
		sheet.getRow(rowNumber).createCell(cellNumber).setCellValue(value);
	}
	public void saveWrittenDataToExcel(String excelsaveFilePath) {
		FileOutputStream fosExcel=null;
		try {
			fosExcel=new FileOutputStream(excelsaveFilePath);
		}
		 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook.write(fosExcel);
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * this method is used to close the excelworkbook
	 */
	public void closeWorkbook() {
		try {
			workbook.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}

