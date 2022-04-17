package PageObjects.LoanTap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.sun.corba.se.spi.orbutil.fsm.Action;

import driver.Base;

public class LoanTapHomePage extends Base{
	
	public WebDriver driver;

	
	/*=================================================Personal Loan=========================================================*/
	
	//Link
	
	By personalLoanSubMenuLink = By.xpath("(//ul[@role='menu']//a[text()='Personal Loan'])[1]");
	
	//lnkClaimsYear						= By.xpath(".//*[text()='"+year+" Claims']");
	
	
	//Text Msg
	
		By addNewCUstomer	= By.xpath("//*[text()='Add New Customer']");
	
	//Radio Button
	
		
		
	//By radioBtnClaimType				= By.xpath(".//*[@value='"+claimType+"']");
	
	
	//Button
	
		By plMenu = By.xpath("//li[contains(@id, 'menu-item')]/a[@title='Personal Loans']");
		
	
	//List Box
	
	
	

	//Text Box
	
	
	//Value
	

	
	
	//Check Box

    
    //Image
    
   
     
	 
	//Other Claim Types

    
	
	/**************************************Creating WebElement for Element Locators***********************************/
	/****************************************************************************************************************/
	
	public LoanTapHomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement get_plMenu()
	{
		return driver.findElement(plMenu);
	}
	
	public WebElement get_personalLoanSubMenuLink()
	{
		return driver.findElement(personalLoanSubMenuLink);
				
	}
	
}
