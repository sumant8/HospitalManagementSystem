package practie;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Exceldata {

	public static void main(String[] args) throws IOException {
		DataFormatter DataFormat = new DataFormatter();
		FileInputStream fisExcel = new FileInputStream("./src/test/resources/excel.xlsx");
		Workbook workbook=WorkbookFactory.create(fisExcel);
		Sheet sheet = workbook.getSheet("demo");
		for(int i=0;i<=sheet.getLastRowNum();i++) {
			String data=DataFormat.formatCellValue(sheet.getRow(i).getCell(1));
			if(data.equals("Test Data")) {
				System.out.println(sheet.getRow(i+1).getCell(1));
				break;
			}
		}

		workbook.close();
	}

}
