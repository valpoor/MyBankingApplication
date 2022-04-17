package driver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import FunctionLibrary.ReusableFunctions;

public class excelFile_POC {
/*	public static void main(String args[]) throws IOException {
		FileInputStream fis = new FileInputStream("/Users/skutt/gitLocalRepo/eCommerce/testInputFile.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet ws = wb.getSheet("Sheet1");
		XSSFRow rw = ws.getRow(1);		
		XSSFCell cl = rw.getCell(1);
		System.out.println("test test " + cl.getStringCellValue());
		System.out.println("getLastRowNum " + ws.getLastRowNum());
		System.out.println("getLastCellNum " + rw.getLastCellNum());
	}*/
	@Test
	public void testDataProvider() throws IOException{
		ReusableFunctions rf = new ReusableFunctions();
		Object[][] temp = rf.universalDataProvider("/Users/skutt/gitLocalRepo/eCommerce/testInputFile.xlsx","Sheet1");
		System.out.print(temp);
	}
	public static Object[][] universalDataProvider_old(String FilePath, String SheetName) throws IOException {
		String[][] tabArray = null;
		DataFormatter objDataFormatter = new DataFormatter();
		try {
			FileInputStream fis = new FileInputStream(FilePath);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			FormulaEvaluator objFormulaEvaluator = new XSSFFormulaEvaluator(wb);
			XSSFSheet ws = wb.getSheet(SheetName);
			XSSFRow rw = ws.getRow(1);		
//			XSSFCell cl = rw.getCell(1);
			
			XSSFCell cell = null;
//			System.out.println("test test " + cl.getStringCellValue());
//			System.out.println("getLastRowNum " + ws.getLastRowNum());
//			System.out.println("getLastCellNum " + rw.getLastCellNum());
			int ci,cj,totalRows,totalCols;
			int firstRow = 0;
			int firstCol = 0;			
			totalRows = ws.getLastRowNum() + 1;
			totalCols = rw.getLastCellNum();
			System.out.println("Reading " + FilePath + " sheet " + SheetName + " # of rows: " + totalRows + " ; # of columns: " + totalCols); 
			tabArray=new String[totalRows][totalCols];

			ci=0;
			for (int i=firstRow;i<totalRows;i++, ci++) {
				cj=0;
				for (int j=firstCol;j<totalCols;j++, cj++){
//					tabArray[ci][cj]=getCellData(i,j);
					cell = wb.getSheet(SheetName).getRow(i).getCell(j);
					
//					CellType dataType = wb.getSheet(SheetName).getRow(i).getCell(j).getCellTypeEnum();
					objFormulaEvaluator.evaluate(cell);
					tabArray[ci][cj]= objDataFormatter.formatCellValue(cell, objFormulaEvaluator);
/*					switch (dataType) {
		                case STRING:
		                    System.out.println(cell.getRichStringCellValue().getString());
		                    tabArray[ci][cj]= cell.getRichStringCellValue().getString();
		                    break;
		                case NUMERIC:
		                    if (DateUtil.isCellDateFormatted(cell)) {
		                        System.out.println(cell.getDateCellValue());
		                        tabArray[ci][cj]= cell.getDateCellValue();
		                    } else {
		                        System.out.println(cell.getNumericCellValue());
		                        tabArray[ci][cj]= NumberToTextConverter.toText(cell.getNumericCellValue());
		                    }
		                    break;
		                case BOOLEAN:
		                    System.out.println(cell.getBooleanCellValue());
		                    tabArray[ci][cj]= cell.getBooleanCellValue();
		                    break;
		                case FORMULA:
		                    System.out.println(cell.getCellFormula());

		                    tabArray[ci][cj]= cell.getCellFormula();
		                    break;
		                default:
		                    System.out.println();
					}
//					tabArray[ci][cj]=

 */
					System.out.println(tabArray[ci][cj] + "\n");
				}
			}
		}

		catch (FileNotFoundException e){
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		catch (IOException e){
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return(tabArray);		
	}
/*	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try{
			XSSFCell Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			int dataType = Cell.getCellType();
			if  (dataType == 3) {
				return "";
			}else{
				String CellData = Cell.getStringCellValue();
				return CellData;
			}catch (Exception e){
			System.out.println(e.getMessage());
			throw (e);
			}
		}*/	
}



