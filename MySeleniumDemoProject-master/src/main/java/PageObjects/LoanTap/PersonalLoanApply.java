package PageObjects.LoanTap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.sun.corba.se.spi.orbutil.fsm.Action;

import driver.Base;

public class PersonalLoanApply extends Base{
	
	public WebDriver driver;

	
	/*=================================================Personal Loan=========================================================*/
	
	//Link
	
	By personalLoanSubMenuLink = By.xpath("(//ul[@role='menu']//a[text()='Personal Loan'])[1]");
	
	//lnkClaimsYear						= By.xpath(".//*[text()='"+year+" Claims']");
	
	
	//Text Msg
	
		By addNewCUstomer	= By.xpath("//*[text()='Add New Customer']");
		By textverify       = By.xpath("//*[@class='font-size-smaller no-margin montserrat gray-dark']");
		By textverify1 		= By.xpath("//*[@class='no-margin font-semibold brand-third']");
		By getAmount		= By.xpath("//*[@id=\'eligibility_criteria\']/div/div/div[2]/div/div[3]/div[2]/p");
	
	//Radio Button
		
		By selectPub_PvtCo = By.xpath("//*[@id=\"EtivHTtJlDCq\"]/div/div[2]/div[2]/div/div[1]/div/div[2]/div/div/ul/li[2]/label/input");	
				
				//("//input[@value='public-or-private-limited-company']");
		
	
		
		
	//By radioBtnClaimType				= By.xpath(".//*[@value='"+claimType+"']");
	
	
	//Button
	
		By applyNowButton = By.xpath("//*[@id=\"post-707195\"]/div/div[1]/div/div/div/div/div/div/div[5]/div[2]/a");
				//("//div[@class='text-center noto font-size-big']//a[contains(@class, 'btn btn btn-secondary')]");
		By salariedButton = By.xpath("//div[@data-id='Salaried']");
		
	
	//List Box
	
	
	

	//Text Box
	
	
	//Value
	

	
	
	//Check Box

    
    //Image
    
   
     
	 
	//Other Claim Types

    
	
	/**************************************Creating WebElement for Element Locators***********************************/
	/****************************************************************************************************************/
	
	public PersonalLoanApply(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement get_applyNowButton()
	{
		return driver.findElement(applyNowButton);
	}
	public WebElement get_salariedButton()
	{
		return driver.findElement(salariedButton);
		
	}
//	public WebElement get_selectPub_PvtCo()
//	{
//		return driver.findElement(selectPub_PvtCo);
		
	public WebElement get_textverify()
	{
		return driver.findElement(textverify);
		
	}
	public WebElement get_textverify1()
	{
		return driver.findElement(textverify1);
		
	}
		
		public WebElement get_getamount()
		{
			return driver.findElement(getAmount);
			
		
		
	}
	
	
				
	
}
