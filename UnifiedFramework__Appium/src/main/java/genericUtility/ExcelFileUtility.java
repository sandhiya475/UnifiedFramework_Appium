package genericUtility;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {
	 public String readDataFromExcel(String sheetName,int rowNum,int cellNum) throws Throwable {
		 FileInputStream fis=new FileInputStream("./testData/AppiumProject.xlsx");
		 Workbook wb = WorkbookFactory.create(fis);
		 String data = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		 return data;
		  }
}

