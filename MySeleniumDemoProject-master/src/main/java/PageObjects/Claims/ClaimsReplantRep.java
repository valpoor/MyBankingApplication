package PageObjects.Claims;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class ClaimsReplantRep {
	
	public WebDriver driver;
	
	//Links
	
		By lnkYear2017	= By.xpath("//a[contains(@href, '/claims-2017-app')]");
		By retailerLookUp	= By.xpath("//a[contains(@href, 'showRetailerLookupScreen')]");
		 By growerLookUp	= By.xpath("//a[contains(@href, 'showGrowerLookupScreen')]");
		 By holdsTab	= By.xpath("//a[contains(@href, '/claims-2017-app/ViewHolds.do')]");
		 By claimDataTab	= By.xpath("//a[contains(@href, '/claims-2017-app/ViewClaim.do')]");
		 By approvalDenialsTab	= By.xpath("//a[contains(@href, '/claims-2017-app/ViewApprovals.do')]");
		 By settlementTab	= By.xpath("//a[contains(@href, '/claims-2017-app/ViewSettlement.do')]");
		 By paymentsTab	= By.xpath("//a[contains(@href, '/claims-2017-app/ViewPaymentRegister.do')]");

//List

	 By zipCodeRetailLookup = By.xpath(".//*[@id='territoryCode']");
	 By zipCodeGrowerLookup = By.xpath(".//*[@id='territoryCode']");
	 By corpName = By.xpath(".//*[@name='detailItemMap[1].specieCode']");
	 By traitName = By.xpath(".//*[@name='traitCategoryDescription1']");
	 By hybridName = By.xpath(".//*[@name='detailItemMap[1].acronymName']");
	 By seedSize = By.xpath(".//*[@name='detailItemMap[1].gradeSizeCode']");
	 By numberOfUnits = By.xpath(".//*[@name='detailItemMap[1].itemQuantity']");
	 By reasonForLoss = By.xpath(".//*[@name='detailItemMap[1].cropLossReasonId']");
	 By addAnotherReplant = By.xpath(".//*[@value='Add another Replant']");
	 By hybridNameAnotherReplant = By.xpath(".//*[@name='detailItemMap[2].acronymName']");
	 By numberOfUnitsAnotherReplant = By.xpath(".//*[@name='detailItemMap[2].itemQuantity']");
	 By corpNameAnotherReplant = By.xpath(".//*[@name='detailItemMap[2].specieCode']");
	 By traitNameAnotherReplant = By.xpath(".//*[@name='traitCategoryDescription2']");
	 By seedSizeAnotherPlant = By.xpath(".//*[@name='detailItemMap[2].gradeSizeCode']");
	 By paymentStatusType = By.xpath(".//*[@id='paymentStatusType']");
	
//Button

		 By findBtnRetailLookup = By.xpath(".//*[@value='Find']");
		 By findBtnGrowerLookup = By.xpath(".//*[@id='growerAcctFindButton']");
		 By saveClaimBtn = By.xpath(".//*[@class='grn_button']");
		 By okBtn = By.xpath("/html/body/div[7]/div[11]/div/button");
		 By releaseBtn = By.xpath(".//*[@id='releaseButton']");
		 By approveBtn = By.xpath(".//*[@id='approveButton']");
		 By continueBtn = By.xpath("/html/body/div[8]/div[11]/div/button[1]");
		 By saveSettlementBtn = By.xpath(".//*[@id='saveButton']");
		 By findButton = By.xpath(".//*[@value='Find']");
		 By sendToKMMBtn = By.xpath(".//*[@id='sendToKmmButton']");

//Text

		 By resultsPerPageRetailLookup = By.xpath(".//*[@class='alt_row']");
		
//TextBox

		 By growerLookUpTxt = By.xpath(".//*[@id='growerAccountId']");
		 By accomodationDescriptionTxt = By.xpath(".//*[@id='accommodationDescriptionTextBox']");
		 By accomodationImplicationTxt = By.xpath(".//*[@id='accommodationImplicationTextBox']");
		 By accomodationBusinessRationaleTxt = By.xpath(".//*[@id='accommodationBusinessRationaleTextBox']");
		
//CheckBox

		 By holdInsufficientSalesItem1 = By.xpath("//*[@id=\"releasableActiveHoldsForm\"]/table/tbody/tr[2]/td[1]/input");
		 By holdInsufficientSalesReplantItem2 = By.xpath("//*[@id=\"releasableActiveHoldsForm\"]/table/tbody/tr[3]/td[1]/input");
		 By holdPotentialDuplicate = By.xpath("//*[@id=\"releasableActiveHoldsForm\"]/table/tbody/tr[4]/td[1]/input");
		 By holdInvoiceAttachment = By.xpath("//*[@id=\"releasableActiveHoldsForm\"]/table/tbody/tr[2]/td[1]/input");
		 By typeOfApproval = By.xpath(".//*[@value='MONSANTO_REP']");
		 By customerAccomodationApproval1 = By.xpath(".//*[@value='CA_FINANCE_ANALYST']");
		 By customerAccomodationApproval2 = By.xpath(".//*[@value='CA_REGIONAL_MANAGER']");
				
	

		//Creating Constructor
	public ClaimsReplantRep(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
	

}
