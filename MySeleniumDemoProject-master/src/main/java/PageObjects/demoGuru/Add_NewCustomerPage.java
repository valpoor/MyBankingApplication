package PageObjects.demoGuru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import driver.Base;


public class Add_NewCustomerPage extends Base
{
	public WebDriver driver;

	
	/*=================================================Seed Product Performance Inquiry=========================================================*/
	
	//Link
	//lnkClaimsYear						= By.xpath(".//*[text()='"+year+" Claims']");
	
	
	//Text Msg
		By addNewCUstomer	= By.xpath("//*[text()='Add New Customer']");
	
	//Radio Button
		By gender	= By.xpath("//input[@type='radio'][1]");
		
		
	//By radioBtnClaimType				= By.xpath(".//*[@value='"+claimType+"']");
	
	
	//Button
		
	By plMenu = By.xpath("//li[contains(@id, 'menu-item')]/a[@title='Personal Loans']");
	
	
	//List Box
	
	
	

	//Text Box
	
	By customerName					= By.xpath("//*[@name='name']");
	By dateOfBirth					= By.xpath("//input[@name='dob']");
	By customerAddress				= By.xpath("//*[@name='addr']");
	By customerCity					= By.xpath("//*[@name='city']");
	By customerState				= By.xpath("//*[@name='state']");
	
	//Value
	

	
	
	//Check Box

    
    //Image
    
   
     
	 
	//Other Claim Types

    
	
	/**************************************Creating WebElement for Element Locators***********************************/
	/****************************************************************************************************************/
	
	public Add_NewCustomerPage(WebDriver driver) {
		this.driver = driver;
	}
	
	/*public WebElement get_lnkClaimsYear()
	{
		return driver.findElement(lnkClaimsYear);
	}*/
	
	public WebElement get_addNewCUstomer()
	{
		return driver.findElement(addNewCUstomer);
	}
	
	public WebElement get_customerName()
	{
		return driver.findElement(customerName);
	}
	
	public WebElement get_gender()
	{
		return driver.findElement(gender);
	}
	
	public WebElement get_dateOfBirth()
	{
		return driver.findElement(dateOfBirth);
	}
	public WebElement get_customerAddress()
	{
		return driver.findElement(customerAddress);
		
	}
	
	public WebElement get_customerCity()
	{
		return driver.findElement(customerCity);
	}
	public WebElement get_customerState()
	{
		return driver.findElement(customerState);
		
	}
}
