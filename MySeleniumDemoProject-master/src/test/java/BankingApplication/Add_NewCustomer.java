package BankingApplication;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjects.demoGuru.Add_NewCustomerPage;
import driver.Base;

public class Add_NewCustomer extends Base {
	private static Logger objLgr=LogManager.getLogger(Add_NewCustomer.class.getName());
	public Add_NewCustomerPage anc;
	public WebDriver webdriver;
	public static HashMap<String, String> data = new HashMap<String, String>();
	String customerName, dateOfBirth, customerAddress, customerCity, customerState;
	
	public Add_NewCustomer(Hashtable<String, String> input) {
		// TODO Auto-generated constructor stub
		this.customerName = input.get("Customer Name");
		this.dateOfBirth = input.get("Date of Birth");
		this.customerAddress = input.get("Customer address");
		this.customerCity = input.get("City");
		this.customerState = input.get("State");
	}

	@BeforeTest (description = "Launching the Chrome Browser")
	public void claimsLaunch() throws IOException, InterruptedException {
		objLgr.info("Browser Launch");
		webdriver=initialize();
		webdriver.manage().window().maximize();
		webdriver.manage().deleteAllCookies();
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webdriver.get(prop.getProperty("ApplicationURL"));
		anc = new Add_NewCustomerPage(webdriver);
		/*if(cor.get_lnkClaims2018().isDisplayed())
		{
			objLgr.info("Navigated to Claims Homepage");
		}
		else
		{
			objLgr.error("Not Navigated to Claims Homepage");
		}*/
	}
	
	@Test(description = "Clicking on 2017 Link from Homepage", alwaysRun=true)
	public void addNewCustomerPage() {
		
		waitForElementLoad(anc.get_addNewCUstomer(), "Claims Entry");
			if ((anc.get_addNewCUstomer().isDisplayed()))
		{
			objLgr.info("User Navigated to Add New Customer Screen Successfully");
			
		}
			
	else {
			objLgr.error("User not Navigated to Add New Customer Screen Search Screen");			
		}
	}
	
	@Test(dependsOnMethods="addNewCustomerPage", description = "Enter Customer Name", enabled=true)
	public void CustomerName() 
	{
		anc.get_customerName().sendKeys(customerName);
		objLgr.info("Entered Customer Name");
	}

	@Test(dependsOnMethods="CustomerName", description = "Select Gender", enabled=true)
	public void SelectGender() 
	{
		anc.get_gender().click();
		objLgr.info("Gender selected");
		
	}
	
	@Test(dependsOnMethods="SelectGender", description = "Enter DOB", enabled=true)
	public void DateofBirth() 
	{
		anc.get_dateOfBirth().sendKeys(dateOfBirth);
		objLgr.info("DOB entered");
		
	}
	
	@Test(dependsOnMethods="DateofBirth", description = "Customeraddress", enabled=true)
	public void Custaddress() 
	{
		anc.get_customerAddress().sendKeys(customerAddress);
		objLgr.info("Custaddress entered");
	}
	
	@Test(dependsOnMethods="Custaddress", description = "CustomerCity", enabled=true)
	public void CustCity() 
	{
		anc.get_customerCity().sendKeys(customerCity);
		objLgr.info("CustCity entered");
		
	}
	
	@Test(dependsOnMethods="CustCity", description = "CustomerState", enabled=true)
	public void CustState() 
	{
		anc.get_customerCity().sendKeys(customerCity);
		objLgr.info("CustState entered");
	}
		
	@AfterTest(description = "Closing the Browser")
	public void tearDown() 
	{
		
		webdriver.close();
	}

}
