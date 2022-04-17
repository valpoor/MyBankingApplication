package FunctionLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;

//import FunctionLibrary.MyAccount;


//import com.jayway.restassured.builder.RequestSpecBuilder;
//import com.jayway.restassured.specification.RequestSpecification;

public class ReusableFunctions{
	private static Logger objLgr = LogManager.getLogger(ReusableFunctions.class.getName());
	private static FileInputStream fis ;
	private static Properties prop;

	String contactGlopid = null;
	String sapAccID1= null;
	String userName;
	static WebDriver webdriver;
	//public boolean flag_Validation;
	private static String propFilePath = "src/main/java/driver/AutomationParameters.properties";


	public Properties readConfig(String propertyFileLocation) throws IOException {
		objLgr.info("Test Execution begins");
		Properties configFileProp =new Properties();
		FileInputStream configFile = new FileInputStream(propertyFileLocation);
		if (configFile == null) {
			objLgr.error("Failed to read Property file" + propertyFileLocation);
		} else {
			//objLgr.info("Property file" + propertyFileLocation + " read");
		}

		configFileProp.load(configFile);	
		return configFileProp;
	}


	public WebDriver initialize(Properties localProp) throws IOException {
		WebDriver webdriver;
		if(localProp.getProperty("browser").contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", localProp.getProperty("chromeDriverLocation") );

		}
		if(localProp.getProperty("browser").contains("Internet Explorer")) {
			//Create Internet Explorer webdriver object
		}
		if(localProp.getProperty("browser").contains("Safari")) {
			//Create Safari webdriver object
		}
		webdriver = new ChromeDriver();

		if(webdriver == null) {
			objLgr.error("Webdriver object creation for " + localProp.getProperty("browser") + " failed.");
		} else {
			objLgr.info("Webdriver object created for " + localProp.getProperty("browser") + " browser.");
		}
		return webdriver;		
	}	


	//	public WebDriver localWebDriver;
	public void closeApp(WebDriver localWebDriver){
		//		localWebDriver.close();
		localWebDriver.quit();
	}

	public Object[][] universalDataProvider(String FilePath, String SheetName) throws IOException {
		String[][] tabArray = null;
		DataFormatter objDataFormatter = new DataFormatter();
		try {
			FileInputStream fis = new FileInputStream(FilePath);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			FormulaEvaluator objFormulaEvaluator = new XSSFFormulaEvaluator(wb);
			XSSFSheet ws = wb.getSheet(SheetName);
			XSSFRow rw = ws.getRow(1);		
			XSSFCell cell = null;
			int ci,cj,totalRows,totalCols;
			int firstRow = 0;
			int firstCol = 0;			
			totalRows = ws.getLastRowNum() + 1;
			totalCols = rw.getLastCellNum();
			objLgr.info("Reading " + FilePath + " sheet " + SheetName + " # of rows: " + totalRows + " ; # of columns: " + totalCols); 
			tabArray=new String[totalRows][totalCols];

			ci=0;
			for (int i=firstRow;i<totalRows;i++, ci++) {
				cj=0;
				for (int j=firstCol;j<totalCols;j++, cj++){
					cell = wb.getSheet(SheetName).getRow(i).getCell(j);
					objFormulaEvaluator.evaluate(cell);
					tabArray[ci][cj]= objDataFormatter.formatCellValue(cell, objFormulaEvaluator);
					objLgr.info(tabArray[ci][cj] + "\n");
				}
			}
		}

		catch (FileNotFoundException e){
			objLgr.error("Could not read the Excel sheet" + FilePath + ": " + SheetName);
			e.printStackTrace();
		}
		catch (IOException e){
			objLgr.error("Could not read the Excel sheet" + FilePath + ": " + SheetName);
			e.printStackTrace();
		}
		return(tabArray);		
	}



	@DataProvider(name="dataset")
	public static Object[][] universalDataProvider_v2(String FilePath,String SheetName) throws IOException {
		//Initializing object to read data from properties file
		prop = new Properties();
		try{
			fis = new FileInputStream(propFilePath);
			//Loading properties file
			prop.load(fis);
		}catch(FileNotFoundException fe) {
			objLgr.error("Failed to read Property file");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			objLgr.error("Error loading properties file.");
		}

		String[][] tabArray = null;
		Object[][] data = null;
		DataFormatter objDataFormatter = new DataFormatter();

		try {
			FileInputStream fis = new FileInputStream(FilePath);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			FormulaEvaluator objFormulaEvaluator = new XSSFFormulaEvaluator(wb);
			XSSFSheet ws = wb.getSheet(SheetName);
			XSSFRow rw = ws.getRow(1);		
			XSSFCell cell = null;
			XSSFCell cellhrd = null;
			XSSFCell cellval = null;
			int ci,cj,totalRows,totalCols;
			int firstRow = 1;
			int firstCol = 0;			
			totalRows = ws.getLastRowNum() + 1;
			totalCols = rw.getLastCellNum();
			data=new Object[totalRows-1][1];
			Hashtable<String,String> table=null;
			//objLgr.info("Reading " + FilePath + " sheet " + SheetName + " # of rows: " + totalRows + " ; # of columns: " + totalCols); 
			tabArray=new String[totalRows][totalCols];

			ci=0;
			for (int i=firstRow;i<totalRows;i++, ci++) {
				//objLgr.info("debug 1");
				table = new Hashtable<String,String>();
				cj=0;
				//objLgr.info("debug 2");
				for (int j=firstCol;j<totalCols;j++, cj++){
					//objLgr.info("debug 3");
					cell = wb.getSheet(SheetName).getRow(i).getCell(j);
					objFormulaEvaluator.evaluate(cell);
					tabArray[ci][cj]= objDataFormatter.formatCellValue(cell, objFormulaEvaluator);




					cellhrd = wb.getSheet(SheetName).getRow(0).getCell(j);
					objFormulaEvaluator.evaluate(cellhrd);
					cellval = wb.getSheet(SheetName).getRow(i).getCell(j);
					objFormulaEvaluator.evaluate(cellval);					
					table.put(objDataFormatter.formatCellValue(cellhrd, objFormulaEvaluator), objDataFormatter.formatCellValue(cellval, objFormulaEvaluator));
					//objLgr.info("row=" + i + " column=" + j + " ci=" + ci + " cj=" + cj + " " + " totalCols=" + totalCols + " " + tabArray[ci][cj] + "\n");
				}
				data[i-1][0] = table;
			}
		}

		catch (FileNotFoundException e){
			objLgr.error("Could not read the Excel sheet" + FilePath + ": " + SheetName);
			e.printStackTrace();
		}
		catch (IOException e){
			objLgr.error("Could not read the Excel sheet" + FilePath + ": " + SheetName);
			e.printStackTrace();
		}

		return data;		
	}
	
	public static String takeScreenshot(WebDriver webdriver, String screenshotName)
	{
			//Steps to Take Screenshot:
		try {
		TakesScreenshot ts = (TakesScreenshot)webdriver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String dest = "./Screenshots/"+screenshotName+".png" ;
		File destination = new File(dest);
		FileUtils.copyFile(src, destination);
		System.out.println("Screenshot Captured: " +screenshotName);	
		return dest;
		}
		catch(Exception e)
		{
			System.out.println("Getting Error while capturing Screenshots: "+e.getMessage());
			return e.getMessage();
		}
	}

}
