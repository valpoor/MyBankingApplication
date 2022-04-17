package LoanTapApplication;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjects.LoanTap.LoanTapHomePage;
import PageObjects.LoanTap.PersonalLoanApply;
import PageObjects.demoGuru.Add_NewCustomerPage;
import driver.Base;

public class Personal_loanCreation extends Base {
	private static Logger objLgr = LogManager.getLogger(Personal_loanCreation.class.getName());
	public LoanTapHomePage anc;
	public PersonalLoanApply pla;
	public WebDriver webdriver;
	public static HashMap<String, String> data = new HashMap<String, String>();
	String customerName, textverify1, getAmount;
	

	public Personal_loanCreation(Hashtable<String, String> input) {
		// TODO Auto-generated constructor stub
		this.customerName = input.get("Customer Name");
		this.textverify1 = input.get("Text Verify");
		this.getAmount = input.get("Amount Verify");
		

	}

	@BeforeTest(description = "Launching the Chrome Browser")
	public void claimsLaunch() throws IOException, InterruptedException {
		objLgr.info("Browser Launch");
		webdriver = initialize();
		webdriver.manage().window().maximize();
		webdriver.manage().deleteAllCookies();
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webdriver.get(prop.getProperty("ApplicationURL"));
		anc = new LoanTapHomePage(webdriver);
		pla = new PersonalLoanApply(webdriver);

		/*
		 * if(cor.get_lnkClaims2018().isDisplayed()) {
		 * objLgr.info("Navigated to Claims Homepage"); } else {
		 * objLgr.error("Not Navigated to Claims Homepage"); }
		 */
	}

	@Test(description = "Clicking on 2017 Link from Homepage", alwaysRun = true)
	public void LoanTapHomePage() {

		waitForElementLoad(anc.get_plMenu(), "Claims Entry");
		if ((anc.get_plMenu().isDisplayed())) {
			objLgr.info("User Navigated to Add New Customer Screen Successfully");

		}

		else {
			objLgr.error("User not Navigated to Add New Customer Screen Search Screen");
		}
	}

	@Test(dependsOnMethods = "LoanTapHomePage", description = "Click Personal loan menu", enabled = true)
	public void pLoanmenu() {
		anc.get_plMenu().click();
		objLgr.info("Entered PLM");
	}

	@Test(dependsOnMethods = "pLoanmenu", description = "Select Personal loan Sub Menu", enabled = true)
	public void selectPersonalLoan() {
		mouseHover(anc.get_personalLoanSubMenuLink(), "Personal Loan Sub Menu");
		objLgr.info("Click PL sub Menu");
	}

	@Test(dependsOnMethods = "selectPersonalLoan", description = "Amount Verifed", enabled = true)
	public void getAmount() {
		String gm = pla.get_getamount().getText();
		
		String  nu = gm.replaceAll("\\D","");
		
		Assert.assertEquals(getAmount, nu);
		
	    objLgr.info("Amount Verifed" + nu);
	    
	}
	@Test(dependsOnMethods = "getAmount", description = "Click Personal ApplyNow", enabled = true)
	public void applyNow() {
		pla.get_applyNowButton().click();
		objLgr.info("Click Applynow");
		
	}
	@Test(dependsOnMethods = "applyNow", description = "Text Validate1", enabled = true)
	public void textverify1() {
		
	
		String tvv = pla.get_textverify1().getText();
		Assert.assertFalse(tvv.contains(textverify1));
		objLgr.info("Text Validate1");
	}
	
	@Test(dependsOnMethods = "textverify1", description = "Text Validate", enabled = true)
	public void textverify() throws InterruptedException {
		
		String tv = pla.get_textverify().getText();
		
		Assert.assertEquals(tv, "Knowing your profession will help us craft credit products best suited for you");
		
		objLgr.info("Text Validate");



	
	}
	// @Test(dependsOnMethods="pLaonmenu", description = "Enter Customer Name",
	// enabled=true)
	// public void CustomerName()
	// {
	// anc.get_customerName().sendKeys(customerName);
	// objLgr.info("Entered Customer Name");

	
		
	

	@AfterTest(description = "Closing the Browser")
	public void tearDown()
	{
	
	 webdriver.close();

}
}
