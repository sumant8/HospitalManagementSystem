package practie;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExelData1 {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
DataFormatter dataFormate = new DataFormatter();
FileInputStream fisExcel = new FileInputStream("./src/test/resources/excel.xlsx");
Workbook workbook = WorkbookFactory.create(fisExcel);
Sheet sheet = workbook.getSheet("demo");
int rowNum = sheet.getLastRowNum();
int cellNum=sheet.getRow(0).getLastCellNum();
String[][] str=new String[rowNum][cellNum];
for(int i=1;i<=rowNum;i++) {
	for(int j=0;j<cellNum;j++) {
		str[i-1][j]=dataFormate.formatCellValue(sheet.getRow(i).getCell(j));
	}
}
System.out.println(str[3][1]);
workbook.close();
	}

}
