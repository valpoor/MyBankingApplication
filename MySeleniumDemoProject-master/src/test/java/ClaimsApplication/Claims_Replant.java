/*package ClaimsApplication;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjects.ClaimsCropInjuryRep;
import PageObjects.ClaimsObjRep;
import PageObjects.PingLoginPage;
import driver.Driver;

public class Claims_Replant extends Driver {
	private static Logger objLgr=LogManager.getLogger(Claims_Replant.class.getName());
	public ClaimsObjRep cor;
	public PingLoginPage plp;
	public Claims_Replant crp;
	public ClaimsCropInjuryRep cci;
	public static HashMap<String, String> data = new HashMap<String, String>();
	String claimIDTxt;
	Iterator<Row> rowIterator;
	String[] claimID;
	String numberOfUnits, comment, growerLookUpValue, appComment, searchVal, retailerLookUpValue,crop, trait, year, claimType;
	String hybrid, seedSize, seedTreatment, reason, paymentStat, addOnTreat, treatmentLoc, zipCode, settlement, payee;
	String userName, userPassword, deny, updComment, percentPer, bioAg, name;
	By lnkClaimsYear;
	By radioBtnclaimType;
	
	public Claims_Replant(Hashtable<String, String> input) {
		// TODO Auto-generated constructor stub
		this.numberOfUnits = input.get("Number of Units");
		this.comment = input.get("Comment");
		this.appComment = input.get("AppComment");
		this.growerLookUpValue = input.get("Grower Look up");
		this.searchVal = input.get("SearchValue");
		this.retailerLookUpValue= input.get("Retailer Look Up");
		this.crop = input.get("Crop");
		this.trait = input.get("Trait");
		this.hybrid = input.get("Hybrid");
		this.seedSize = input.get("SeedSize");
		this.seedTreatment = input.get("SeedTreatment");
		this.reason = input.get("ReasonForLoss");
		this.paymentStat = input.get("Payment Status");
		this.addOnTreat = input.get("AddOnTreatment");
		this.treatmentLoc = input.get("TreatmentLoc");
		this.zipCode = input.get("ZipCode");
		this.settlement = input.get("SettlementType");
		this.payee = input.get("PayableTo");
		this.year = input.get("ClaimsYear");
		this.claimType = input.get("ClaimType");
		this.deny = input.get("Deny Reason");
		this.updComment = input.get("ModifiedComment");
		this.percentPer = input.get("Affected Acres");
		this.bioAg = input.get("BioAg Treatments");
		this.name = input.get("Name");
	}

	@BeforeTest (description = "Launching the Chrome Browser")
	public void claimsLaunch() throws IOException, InterruptedException {
		objLgr.info("Browser Launch");
		webdriver=initialize();
		webdriver.manage().window().maximize();
		webdriver.manage().deleteAllCookies();
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webdriver.get(prop.getProperty("ApplicationURL"));
		plp = new PingLoginPage(webdriver);
		cor = new ClaimsObjRep(webdriver);
		cci = new ClaimsCropInjuryRep(webdriver);
		if(cor.get_lnkClaims2018().isDisplayed())
		{
			objLgr.info("Navigated to Claims Homepage");
		}
		else
		{
			objLgr.error("Not Navigated to Claims Homepage");
		}
	}
	
	@Test(description = "Clicking on 2017 Link from Homepage", alwaysRun=true)
	public void claims2017lnk() {
		objLgr.info("Claim Year Selection - 2017");
		lnkClaimsYear= By.xpath(".//*[text()='"+year+" Claims']");
		//Clicking on Claims 2017 Link
		webdriver.findElement(lnkClaimsYear).click();
		//cor.get_lnkClaimsYear().click();
		//Wait for Element to Load Completely
		waitForElementLoad(cor.get_lnkClaimsEntry(), "Claims Entry");
			if ((cor.get_lnkClaimSearch().isDisplayed())
			&& (cor.get_lnkClaimsEntry().isDisplayed())
			&& (cor.get_lnkApprovalTab().isDisplayed())
			&& (cor.get_lnkPayments().isDisplayed())
			&& (cor.get_lnkAdminToolsTab().isDisplayed())) 
		{
			objLgr.info("User Navigated to Claims Search Screen Successfully");
			
		} else {
			objLgr.error("User not Navigated to Claims Search Screen");			
		}
	}
	
	@Test(dependsOnMethods="claims2017lnk", description = "Navigating to Claims Data page of Replant Claim type", enabled=true)
	public void claimDataPage() throws Exception {
		objLgr.info("Navigating to Claim Data Page");
		radioBtnclaimType = By.xpath(".//*[@value='"+claimType+"']");
		cor.get_lnkClaimsEntry().click();
		
		waitForElementLoad(webdriver.findElement(radioBtnclaimType), "Claim Radio Button");
		
		//Selecting Replant Crop Option
		webdriver.findElement(radioBtnclaimType).click();
		
		//Clicking on Continue Button
		cor.get_btnContinue().click();
		
		//Clicking On Back to Claims Entry Link
		
		cor.get_lnkBack2ClaimEntry().click();
		
		//Selecting Replant Crop Option
		webdriver.findElement(radioBtnclaimType).click();
		
		//Clicking on Continue Button
		cor.get_btnContinue().click();
		
		waitForElementLoad(cor.get_lnkRetailerLookUp(), "Retailer LookUp");
		if (cor.get_lnkRetailerLookUp().isDisplayed()) {
			objLgr.info("User navigated to Claims Data Screen for Replant Successfully");
		} else {
			objLgr.error("User not navigated to Claims Data for Replant Screen");
		}
		// Performing Retailer LookUp
		if(!(retailerLookUpValue.isEmpty()))
		{
			objLgr.info("Retailer Lookup Value from Data sheet is: "+retailerLookUpValue);
			cor.get_txtBoxRetailerLookUp().sendKeys(retailerLookUpValue);
			cor.get_btnFindRetailer().click();
		}
		else
		{	
			cor.get_lnkRetailerLookUp().click();
			Set<String> handles = webdriver.getWindowHandles();
			Iterator<String> it = handles.iterator();
			while (it.hasNext()) {
				String parentwindow = it.next();
				String nativewindow = it.next();
				webdriver.switchTo().window(nativewindow);
				webdriver.manage().window().maximize();
				// Selecting Value from the List Box
				Select stateVal = new Select(cor.get_lstBoxTerritoryCode());
				stateVal.selectByIndex(6);
				// Clicking on Find Button
				cor.get_btnFind().click();
				waitForElementLoad(cor.get_rsltRetailerVal(), "Option 6");
				cor.get_rsltRetailerVal().click();
				webdriver.switchTo().window(parentwindow);
		}
	}
		//Performing Grower Lookup
		if(!(growerLookUpValue.isEmpty()))
		{
			objLgr.info("Grower Lookup Value from Data sheet is: "+growerLookUpValue);
			cor.get_txtBoxGrowerLookUp().sendKeys(growerLookUpValue);
			cor.get_btnFindGrower().click();
		}
		else
		{	
			cor.get_lnkGrowerLookUp().click();
			Set<String> handles = webdriver.getWindowHandles();
			Iterator<String> it = handles.iterator();
			while (it.hasNext()) 
			{
				String parentwindow = it.next();
				String nativewindow = it.next();
				webdriver.switchTo().window(nativewindow);
				webdriver.manage().window().maximize();
				// Selecting Value from the List Box
				Select stateVal = new Select(cor.get_lstBoxState());
				stateVal.selectByIndex(15);
				
				//Entering Zip Code Value
				cor.get_txtBoxZipCode().sendKeys(zipCode);
				
				// Clicking on Find Button
				cor.get_btnFind().click();
				waitForElementLoad(cor.get_rsltRetailerVal(), "Option 6");
				cor.get_rsltRetailerVal().click();
				webdriver.switchTo().window(parentwindow);
		}
	}
		
	}

	@Test(dependsOnMethods="claimDataPage", description = "Data Entry for Replant Claim type and check the status of the claim Id", enabled=true)
	public void dataUpdateReplant() throws Exception {
		
		objLgr.info("Updation of Data for Replant Claim Type");
		
		//Updating fields in Claim Details Section
		
				======Original Crop(s)=========
		
		//Selecting Random value from Crop List Box 
		waitForElementLoad(cor.get_lstBoxOrigCrop(), "Crop List Box");
		Select valCrop = new Select(cor.get_lstBoxOrigCrop());
		valCrop.selectByVisibleText(crop);
		
		//Selecting Random value from Trait List Box
		Thread.sleep(1000);
		Select valTrait = new Select(cor.get_lstBoxOrigTrait());
		valTrait.selectByVisibleText(trait);
		
		//Selecting Random value from Hybrid/Variety List Box
		Thread.sleep(1000);
		Select valHybrid = new Select(cor.get_lstBoxOrigHybrid());
		valHybrid.selectByVisibleText(hybrid);
		
		//Selecting Random value from Seed Size List Box
		Thread.sleep(1000);
		Select valSeed = new Select(cor.get_lstBoxOrigSeedSize());
		valSeed.selectByVisibleText(seedSize);
		
		//Selecting Random value from Seed Treatment List Box
		Thread.sleep(1000);
		Select valSeedTreat	= new Select(cor.get_lstBoxSeedTreatment());
		valSeedTreat.selectByVisibleText(seedTreatment);
		
		//Selecting Random value from Add On Treatments List Box
		Thread.sleep(1000);
		Select valAddOnTreat = new Select(cor.get_lstBoxAddOnTreatment());
		valAddOnTreat.selectByVisibleText(addOnTreat);
		
		//Selecting Random value from BioAg Treatments List Box
		Thread.sleep(1000);
		Select valBioAg = new Select(cor.get_lstBoxBioAg());
		valBioAg.selectByVisibleText(bioAg);
		
		//Selecting Random value from Treatment Location List Box
		Thread.sleep(1000);
		Select valTreatment = new Select(cor.get_lstBoxTreatmentLoc());
		valTreatment.selectByVisibleText(treatmentLoc);
		
		//Entering value into # of Units Text Box
		Thread.sleep(1000);
		cor.get_txtBoxOrigUnits().sendKeys(numberOfUnits);
		
		//Selecting Random value from Reason For Loss List Box
		Thread.sleep(1000);
		Select valReason = new Select(cor.get_lstBoxOrigReason());
		valReason.selectByVisibleText(reason);
		
		//Clicking on Add Another Replant Button 
		cor.get_btnAddAnotherReplant().click();
		
		
		======Replanted Crop(s)=========
		
		//Selecting Random value from Crop List Box 
		Thread.sleep(1000);
		Select valCrop1 = new Select(cor.get_lstBoxOrigCrop2());
		valCrop1.selectByVisibleText(crop);
		
		//Selecting Random value from Trait List Box
		Thread.sleep(1000);
		Select valTrait2 = new Select(cor.get_lstBoxOrigTrait2());
		valTrait2.selectByVisibleText(trait);
		
		//Selecting Random value from Hybrid/Variety List Box
		Thread.sleep(1000);
		Select valHybdrid2 = new Select(cor.get_lstBoxOrigHybrid2());
		valHybdrid2.selectByVisibleText(hybrid);
		
		//Selecting Random value from Seed Size List Box
		Thread.sleep(1000);
		Select valSeed2 = new Select(cor.get_lstBoxOrigSeedSize2());
		valSeed2.selectByVisibleText(seedSize);
		
		//Selecting Random value from Seed Treatment List Box
		Thread.sleep(1000);
		Select valSeedTreat1 = new Select(cor.get_lstBoxOrigSeedTreatment2());
		valSeedTreat1.selectByVisibleText(seedTreatment);
		
		//Selecting Random value from Add On Treatments List Box
		Thread.sleep(1000);
		Select valAddOnTreat1 = new Select(cor.get_lstBoxOrigAddOnTreatment2());
		valAddOnTreat1.selectByVisibleText(addOnTreat);
		
		//Selecting Random value from BioAg Treatments List Box
		Thread.sleep(1000);
		Select valBioAg1 = new Select(cor.get_lstBoxBioAg());
		valBioAg1.selectByVisibleText(bioAg);
		
		//Selecting Random value from Treatment Location List Box
		Thread.sleep(1000);
		Select valTreatment1 = new Select(cor.get_lstBoxOrigTreatmentLoc());
		valTreatment1.selectByVisibleText(treatmentLoc);
		
		//Entering value into # of Units Text Box
		cor.get_txtBoxOrigUnits2().sendKeys(numberOfUnits);
		
		//Selecting Random value for Percent Priced
		Thread.sleep(1000);
		cor.get_txtBoxPercentPriced().sendKeys(percentPer);
		
		//Selecting Random value from Settlement Type List Box
		Thread.sleep(1000);
		Select valSettlement = new Select(cor.get_lstBoxSettlementType());
		System.out.println(settlement);
		valSettlement.selectByVisibleText(settlement);
		
		//Selecting Random value from Payable To List Box
		waitForElementLoad(cor.get_lstBoxPayableTo(), "Payable To");
		Select valPayee = new Select(cor.get_lstBoxPayableTo());
		System.out.println(payee);
		valPayee.selectByVisibleText(payee);
		
		objLgr.info("Fields Updated Successfully");
	}
	
	@Test(dependsOnMethods="dataUpdateReplant", description="Performing Save Claim Operation", enabled=true)
	public void saveClaim() throws Exception
	{
		objLgr.info("Saving Claim");
	
		//Clicking On Save Claim Button
		cor.get_btnSaveClaim().click();
	
		try {
		//Clicking on Accept Button
		cor.get_btnOK().click();
		//waitForElementLoad(cor.get_txtBoxDescription(), "Accomodation Text Box");
		
		//Entering Value in Description / Supporting Details Text Box
		cor.get_txtBoxDescription().sendKeys(comment);
		
		//Entering Value in Implication of not making the customer accommodation Text Box
		cor.get_txtBoxImplication().sendKeys(comment);
		
		//Entering Value in Business Rationale Text Box
		cor.get_txtBoxBusinessRationale().sendKeys(comment);
		
		//Clicking on Save Button
		cor.get_btnSaveClaim().click();
		}
		catch (Exception e)
		{
			objLgr.info("Accommodation Details not Required"+e.getMessage());
		}

		WebElement claimIDVal = cor.get_txtClaimID();
		claimIDTxt = claimIDVal.getText().replaceAll("[^a-zA-Z0-9]", " ");
		claimIDTxt = claimIDVal.getText().replaceAll("[^a-zA-Z0-9]", " ");
		claimID = claimIDTxt.split(" ");
		objLgr.info("Newly Created Claim ID for Replant Claim Type_" + claimID[2]);
		System.out.println("Claims ID Value is: " + claimID[2]);	
	}
	
	@Test(dependsOnMethods="saveClaim", description = "Status verification after creating new Claim Id", enabled=true)
	public void statusVerificationForNewClaimId() throws Exception
	{
		objLgr.info("Status Verification After Creating New Claim");
		WebElement claimtxt = cor.get_txtClaimStatus();
		String claimStatus = claimtxt.getText();
		System.out.println(claimStatus);
		objLgr.info("Status of the Claim ID is:"+claimStatus);
	}

	@Test(dependsOnMethods="statusVerificationForNewClaimId", description = "Releasing all the holds through Holds tab", enabled= true)
	public void releasingAllHolds() throws Exception
	{
		objLgr.info("Releasing Holds");	
		waitForElementLoad(cor.get_lnkHold(), "Hold Link");
		cor.get_lnkHold().click();
		
		waitForElementLoad(cor.get_txtBoxHoldComment(), "Hold Text Box");
		if(cor.get_txtBoxHoldComment().isDisplayed())
		{
			objLgr.info("Navigated to Hold Screen Successfully");
		}
		else
		{
			objLgr.error("Not Navigated to Hold Screen");
		}
			try {
					if(cor.get_chkBoxInsufficientSales1().isEnabled())
					{
						cor.get_chkBoxInsufficientSales1().click();
					}
					if(cor.get_chkBoxInsufficientSales2().isEnabled())
					{
						cor.get_chkBoxInsufficientSales2().click();
					}
			    }
			catch(Exception hold)
				{
					objLgr.info("No Hold is Raised for Insufficient Sales: "+hold.getMessage());
				}
		try 
		{
			if(cor.get_chkBoxPotentialDup().isEnabled())
			{
				cor.get_chkBoxPotentialDup().click();
			}
		}
		catch(Exception noHold)
		{
			objLgr.info("Potential Duplicate Hold is Not Required: "+noHold.getMessage());
		}
		finally 
		{
			cor.get_txtBoxHoldComment().sendKeys(comment);
		}
		
		//Clicking on Release Button
		cor.get_btnRelease().click();		
		}


	@Test(dependsOnMethods="releasingAllHolds", description = "Status verification after releasing all the holds through Holds tab", enabled=true)
	public void statusVerificationAfterReleasingHolds() throws Exception

	{
		objLgr.info("Status Verification After Releasing Hold");
		cor.get_lnkClaimData().click();
		WebElement claimtxt = cor.get_txtClaimStatus();
		String claimStatus = claimtxt.getText();
		System.out.println(claimStatus);
		objLgr.info("Status of the Claim ID After Releasing Hold is:"+claimStatus);
	}
	
	@Test(dependsOnMethods="statusVerificationAfterReleasingHolds", description="Navigating to Attachment Screen", enabled=true)
	public void attachmentScreen() throws Exception
	{
		objLgr.info("Navigating to Attachment Screen");
		cci.get_lnkAttachments().click();
		waitForElementLoad(cci.get_buttonAttachment(), "Add Attachment");
		if(cci.get_buttonAttachment().isDisplayed())
		{
			objLgr.info("Navigated to Attachment Screen Successfully");
		}
		else
		{
			objLgr.error("Not Navigated to Attachment Screen");
		}
		//Clicking on Attachment Button
		cci.get_buttonAttachment().click();
		
		//Clicking on Choose File Button
	    String filePath = new File("").getAbsolutePath();
	    System.out.println(filePath);
	    String path = new File("src/main/java/Sample Doc.docx").getAbsolutePath();
	    System.out.println(path);
		
		cci.get_buttonChooseFile().sendKeys(path);
		
		//Entering Name
		cci.get_txtBoxName().click();
		cci.get_txtBoxName().clear();
		cci.get_txtBoxName().sendKeys(name);
		
		//Selecting Invoice Check Box
		
		cci.get_chkBoxInvoice().click();
		
		//Entering Value in Description Field
		
		cci.get_txtBoxDescription().click();
		cci.get_txtBoxDescription().sendKeys(comment);
				
		
		//Clicking on Attach Button
		if(cci.get_buttonAttach().isEnabled())
		{
			cci.get_buttonAttach().click();
		}
		else
		{
			objLgr.error("Attach Button is not Enabled");
		}
		waitForElementLoad(cci.get_rsltAttachment(), "Result");

		if(cci.get_rsltAttachment().isDisplayed()) 
		{
			objLgr.info("User uploaded Attachments Succesfully");
		}
		else
		{
			objLgr.error("Document not uploaded Successfully");
		}
		
	}
	
	@Test(dependsOnMethods="attachmentScreen", description = "Status verification after Adding Attachment", enabled = true)
	public void statusVerificationAfterAttachment() throws Exception
	{
		objLgr.info("Status Verification After Attachment");
		cor.get_lnkClaimData().click();
		WebElement claimtxt = cor.get_txtClaimStatus();
		String claimStatus = claimtxt.getText();
		System.out.println(claimStatus);
		objLgr.error("Status of the Claim ID is:"+claimStatus);
	}

	@Test(dependsOnMethods="statusVerificationAfterAttachment", description = "Approve Claim Id through Approval/Denials tab", enabled=true)
	public void approvalRep() throws Exception
	{
		objLgr.info("Performing Approval");
		
		//Clicking on Approval Link
		cor.get_lnkApproval().click();
		waitForElementLoad(cor.get_lnkApproval(), "Approval Link");
		if(cor.get_txtBoxApprovalComment().isDisplayed())
		{
			objLgr.info("Navigated to Approval Screen Successfully");
		}
		else
		{
			objLgr.error("Not Navigated to Approval Screen");	
		}
		//Performing First Level of Approval
		try 
		{
		if(cor.get_chkBoxMonsantoRep().isDisplayed())
			{
				cor.get_chkBoxMonsantoRep().click();
				cor.get_txtBoxApprovalComment().click();
				cor.get_txtBoxApprovalComment().sendKeys(appComment);
				
				//Clicking on Approve Button
				cor.get_btnApprove().click();
				cor.get_btnAppContinue().click();
			}
		}
		catch(Exception e)
		{
			objLgr.info("No Approval Required for Monsanto Rep" +e.getMessage());
		}

		//Performing Second Level of Approval
	try 
	{
		if(cor.get_chkBoxFinAnlyst().isEnabled())
			{
				cor.get_chkBoxFinAnlyst().click();
			}
	}catch(Exception e)
		{
			objLgr.info("No Approval Required from Finance Analyst" +e.getMessage());
		}
	try
	{
		if(cor.get_chkBoxRegMgr().isEnabled())
			{
				cor.get_chkBoxRegMgr().click();
			}
	}catch(Exception e)
		{
			objLgr.info("No Approval Required from Regional Manager" +e.getMessage());
		}
	try 
	{
		if(cor.get_chkBoxAreaManager().isEnabled())
		{
			cor.get_chkBoxAreaManager().click();
		}
	}catch(Exception e)
		{
			objLgr.info("No Approval Required from Area Manager" +e.getMessage());
		}
		cor.get_txtBoxApprovalComment().click();
		cor.get_txtBoxApprovalComment().sendKeys(appComment);
		cor.get_btnApprove().click();
		
		if(cor.get_btnAppContinue().isDisplayed())
			{
				cor.get_btnAppContinue().click();
			}
				else
				{
					objLgr.info("No Pop Up appeared");
				}	
	}

	@Test(dependsOnMethods="approvalRep", description = "Status verification after approving Claim Id through Approval/Denials tab", enabled=true)
	public void statusVerificationAfterApprovingClaimID() throws Exception
	{
		objLgr.info("Status Verification After Approving Claim");
		cor.get_lnkClaimData().click();
		WebElement claimtxt = cor.get_txtClaimStatus();
		String claimStatus = claimtxt.getText();
		System.out.println(claimStatus);
		objLgr.info("Status of the Claim ID After Approval is: "+claimStatus);
	}

	@Test(dependsOnMethods="statusVerificationAfterApprovingClaimID", description = "Performing Settlement Functionality", enabled=true)
	public void settlementFunctionality() throws Exception
	{
		objLgr.info("Settlement Screen");
		
		//Clicking on Settlement Link
		cor.get_lnkSettlements().click();
		waitForElementLoad(cor.get_lstBoxPaymentStatus(), "Settlement Link");
		if(cor.get_lstBoxPaymentStatus().isDisplayed())
		{
			objLgr.info("Navigated to Settlement Screen");
		}
		else
		{
			objLgr.error("Not Navigated to Settlement Screen");
		}
		
		//Changing the Payment status from Pending --> Approved
		Select valPaymentStat = new Select(cor.get_lstBoxPaymentStatus());
		valPaymentStat.selectByVisibleText(paymentStat);
		
		//Clicking on Save Button
		cor.get_btnSave().click();
	}

	@Test(dependsOnMethods="settlementFunctionality", description = "Verification of status after performing Settlement Functionality", enabled=true)
	public void statusCheckAfterSettlementFunctionality() throws Exception
	{
		objLgr.info("Status Check After Settlement");
		cor.get_lnkClaimData().click();
		WebElement claimtxt = cor.get_txtClaimStatus();
		String claimStatus = claimtxt.getText();
		System.out.println(claimStatus);
		objLgr.info("Status of the Claim ID After Settlement is:"+claimStatus);
	}

	@Test(dependsOnMethods="statusCheckAfterSettlementFunctionality", description = "Performing Payment Procedure", enabled = true)
	public void paymentSection() throws Exception {	
		//Clicking on Payments Link
		cor.get_lnkPayments().click();
		objLgr.info("Payment Section");
		
		// Entering Claim ID in Search Text Box
		cor.get_txtBoxPayClaimSearch().click();
		cor.get_txtBoxPayClaimSearch().sendKeys(claimID[2]);

		// Clicking on Find Button
		cor.get_btnFind().click();
		waitForElementLoad(cor.get_rsltPaymentSection(), "Payment Result");
		if(cor.get_rsltPaymentSection().isDisplayed())
		{
			objLgr.info("Search Result displayed Successfully");
		}
		else
		{
			objLgr.error("Search Result Not Displayed");
		}
		
		//Clicking on Send to KMM Button
		waitForElementLoad(cor.get_btnSendToKmm(), "Send To KMM Button");
		cor.get_btnSendToKmm().click();
		WebElement batch = cor.get_rsltBatch();
		String txtBatch = batch.getText();
		if(cor.get_txtSuccessMsg().isDisplayed())
		{
			objLgr.info("Successfully Send to KMM & Batch ID is: " +txtBatch);
		}
		else
		{
			objLgr.error("Not Send to KMM");
		}
	}
	
	@Test(dependsOnMethods="paymentSection", description = "Navigated to Claim Search Page", enabled = true)
	public void searchClaims() throws Exception
	{
		objLgr.info("Search Screen");
		//Clicking on Claim Search Link
		waitForElementLoad(cor.get_lnkClaimSearch(), "Search Link");
		cor.get_lnkClaimSearch().click();
		
		//Entering Claim ID in Search Text Box
		cor.get_txtBoxPayClaimSearch().click();
		cor.get_txtBoxPayClaimSearch().sendKeys(searchVal);
		objLgr.info("Search Value passed by user is: "+searchVal);
		//Clicking on Find Button
		cor.get_btnFind().click();
		waitForElementLoad(cor.get_rsltClaimSearch(), "Claim ID");
		if(cor.get_rsltClaimSearch().isDisplayed())
		{
			objLgr.info("Claim Result displayed based on the user input");
		}
		else
		{
			objLgr.error("Claim Result not displayed properly");
		}
		//Clicking on Claim ID
		cor.get_rsltClaimSearch().click();
		if(cor.get_lnkCopyClaim().isDisplayed())
		{
			objLgr.info("Navigated to Claim Detail page successfully");
		}
		else
		{
			objLgr.error("Not Navigated to Claim Detail page");
		}
	}
	
	@Test(dependsOnMethods="searchClaims", description = "Creating New Claim With Same Retailer", enabled=true)
	public void creatingClaimWithSameRetailer() throws Exception {		
		
		objLgr.info("Creating New Claim With Same Retailer");
		waitForElementLoad(cor.get_lnkNewClaim(), "New Claim for Same Retailer");
		cor.get_lnkNewClaim().click();
		//Performing Grower Lookup
		if(!(growerLookUpValue.isEmpty()))
		{
			objLgr.info("Grower Lookup Value from Data sheet is: "+growerLookUpValue);
			cor.get_txtBoxGrowerLookUp().sendKeys(growerLookUpValue);
			cor.get_btnFindGrower().click();
		}
		else
		{	
			cor.get_lnkGrowerLookUp().click();
			Set<String> handles = webdriver.getWindowHandles();
			Iterator<String> it = handles.iterator();
			while (it.hasNext()) 
			{
				String parentwindow = it.next();
				String nativewindow = it.next();
				webdriver.switchTo().window(nativewindow);
				webdriver.manage().window().maximize();
				// Selecting Value from the List Box
				Select stateVal = new Select(cor.get_lstBoxState());
				stateVal.selectByIndex(15);
				
				//Entering Zip Code Value
				cor.get_txtBoxZipCode().sendKeys(zipCode);
				
				// Clicking on Find Button
				cor.get_btnFind().click();
				waitForElementLoad(cor.get_rsltRetailerVal(), "Option 6");
				cor.get_rsltRetailer().click();
				webdriver.switchTo().window(parentwindow);
		}
	}
		
		//Updating fields in Claim Details Section
		
		======Original Crop(s)=========

	//Selecting Random value from Crop List Box 
	waitForElementLoad(cor.get_lstBoxOrigCrop(), "Crop List Box");
	Select valCrop = new Select(cor.get_lstBoxOrigCrop());
	valCrop.selectByVisibleText(crop);
	
	//Selecting Random value from Trait List Box
	Thread.sleep(1000);
	Select valTrait = new Select(cor.get_lstBoxOrigTrait());
	valTrait.selectByVisibleText(trait);
	
	//Selecting Random value from Hybrid/Variety List Box
	Thread.sleep(1000);
	Select valHybrid = new Select(cor.get_lstBoxOrigHybrid());
	valHybrid.selectByVisibleText(hybrid);
	
	//Selecting Random value from Seed Size List Box
	Thread.sleep(1000);
	Select valSeed = new Select(cor.get_lstBoxOrigSeedSize());
	valSeed.selectByVisibleText(seedSize);
	
	//Selecting Random value from Seed Treatment List Box
	Thread.sleep(1000);
	Select valSeedTreat	= new Select(cor.get_lstBoxSeedTreatment());
	valSeedTreat.selectByVisibleText(seedTreatment);
	
	//Selecting Random value from Add On Treatments List Box
	Thread.sleep(1000);
	Select valAddOnTreat = new Select(cor.get_lstBoxAddOnTreatment());
	valAddOnTreat.selectByVisibleText(addOnTreat);
	
	//Selecting Random value from Treatment Location List Box
	Thread.sleep(1000);
	Select valTreatment = new Select(cor.get_lstBoxTreatmentLoc());
	valTreatment.selectByVisibleText(treatmentLoc);
	
	//Entering value into # of Units Text Box
	Thread.sleep(1000);
	cor.get_txtBoxOrigUnits().sendKeys(numberOfUnits);
	
	//Selecting Random value from Reason For Loss List Box
	Thread.sleep(1000);
	Select valReason = new Select(cor.get_lstBoxOrigReason());
	valReason.selectByVisibleText(reason);
	
	//Clicking on Add Another Replant Button 
	cor.get_btnAddAnotherReplant().click();
	
	
	======Replanted Crop(s)=========
	
	//Selecting Random value from Crop List Box 
	Thread.sleep(1000);
	Select valCrop1 = new Select(cor.get_lstBoxOrigCrop2());
	valCrop1.selectByVisibleText(crop);
	
	//Selecting Random value from Trait List Box
	Thread.sleep(1000);
	Select valTrait2 = new Select(cor.get_lstBoxOrigTrait2());
	valTrait2.selectByVisibleText(trait);
	
	//Selecting Random value from Hybrid/Variety List Box
	Thread.sleep(1000);
	Select valHybdrid2 = new Select(cor.get_lstBoxOrigHybrid2());
	valHybdrid2.selectByVisibleText(hybrid);
	
	//Selecting Random value from Seed Size List Box
	Thread.sleep(1000);
	Select valSeed2 = new Select(cor.get_lstBoxOrigSeedSize2());
	valSeed2.selectByVisibleText(seedSize);
	
	//Selecting Random value from Seed Treatment List Box
	Thread.sleep(1000);
	Select valSeedTreat1 = new Select(cor.get_lstBoxOrigSeedTreatment2());
	valSeedTreat1.selectByVisibleText(seedTreatment);
	
	//Selecting Random value from Add On Treatments List Box
	Thread.sleep(1000);
	Select valAddOnTreat1 = new Select(cor.get_lstBoxOrigAddOnTreatment2());
	valAddOnTreat1.selectByVisibleText(addOnTreat);
	
	//Selecting Random value from Treatment Location List Box
	Thread.sleep(1000);
	Select valTreatment1 = new Select(cor.get_lstBoxOrigTreatmentLoc());
	valTreatment1.selectByVisibleText(treatmentLoc);
	
	//Entering value into # of Units Text Box
	cor.get_txtBoxOrigUnits2().sendKeys(numberOfUnits);
	
	//Selecting Random value for Percent Priced
	Thread.sleep(1000);
	cor.get_txtBoxPercentPriced().sendKeys(percentPer);
	
	//Selecting Random value from Settlement Type List Box
	Thread.sleep(1000);
	Select valSettlement = new Select(cor.get_lstBoxSettlementType());
	System.out.println(settlement);
	valSettlement.selectByVisibleText(settlement);
	
	//Selecting Random value from Payable To List Box
	waitForElementLoad(cor.get_lstBoxPayableTo(), "Payable To");
	Select valPayee = new Select(cor.get_lstBoxPayableTo());
	System.out.println(payee);
	valPayee.selectByVisibleText(payee);
	
	objLgr.info("Fields Updated Successfully");
		
		//Clicking On Save Claim Button
		cor.get_btnSaveClaim().click();
		
		//Clicking on Accept Button
		try {
		waitForElementLoad(cor.get_btnOK(), "OK Button");
		cor.get_btnOK().click();
		
		//Entering Value in Description / Supporting Details Text Box
		cor.get_txtBoxDescription().sendKeys(comment);
		
		//Entering Value in Implication of not making the customer accommodation Text Box
		cor.get_txtBoxImplication().sendKeys(comment);
		
		//Entering Value in Business Rationale Text Box
		cor.get_txtBoxBusinessRationale().sendKeys(comment);
		
		//Clicking on Save Button
		cor.get_btnSaveClaim().click();
		}
		catch (Exception accSec)
		{
			objLgr.info("No Accommodation Details Required: "+accSec.getMessage());
		}

		WebElement claimIDVal = cor.get_txtClaimID();
		claimIDTxt = claimIDVal.getText().replaceAll("[^a-zA-Z0-9]", " ");
		claimIDTxt = claimIDVal.getText().replaceAll("[^a-zA-Z0-9]", " ");
		claimID = claimIDTxt.split(" ");
		objLgr.info("Created New Claim by using Same Retailer Value is: " + claimID[2]);
		System.out.println("Claims ID Value is: " + claimID[2]);
	}
	
	@Test(dependsOnMethods="creatingClaimWithSameRetailer", description = "Performing Deny Claim", enabled=true)
	public void denyClaim() throws Exception
	{
		objLgr.info("Deny Claim"); 
		//Clicking on Approval Link
		cor.get_lnkApproval().click();
		waitForElementLoad(cor.get_lstBoxDenyReason(), "Deny Reason");
		if(cor.get_lstBoxDenyReason().isDisplayed())
		{
			objLgr.info("Navigated to Approval Screen Successfully");
		}
		else
		{
			objLgr.error("Not Navigated to Approval Screen");
		}
		
		//Selecting Value from the Deny Reason List Box
		Select valDeny = new Select(cor.get_lstBoxDenyReason());
		valDeny.selectByVisibleText(deny);
		
		//Entering Comment in the Deny Reason Text Box
		cor.get_txtBoxDenialComment().sendKeys(comment);
		
		//Clicking on Deny Button		
		cor.get_btnDeny().click();
		
		//Navigating to Claim Data Screen & Validating the Status
		
		cor.get_lnkClaimData().click();
		WebElement claimtxt = cor.get_txtClaimStatus();
		String claimStatus = claimtxt.getText();
		System.out.println(claimStatus);
		waitForElementLoad(cor.get_txtClaimStatus(), "Denied");
		if(claimStatus.equals("Denied"))
		{
			objLgr.info("Created Claim Denied Successfully: " +claimStatus);
		}
		else
		{
			objLgr.error("Claim not Denied properly... Perform Deny Again");
		}
		
	}
	
	@Test(dependsOnMethods="denyClaim", description = "Performing Re-Open after Deny Claim", enabled=true)
	public void reOpenClaim() throws Exception
	{
		objLgr.info("Re-Open Claim");
		//Clicking on Approval Link
		cor.get_lnkApproval().click();
		waitForElementLoad(cor.get_txtBoxDenialComment(), "Deny Reason Comment");
		if(cor.get_txtBoxDenialComment().isDisplayed())
		{
			objLgr.info("Navigated to Approval Screen Successfully");
		}
		else
		{
			objLgr.error("Not Navigated to Approval Screen");
		}
		
		//Entering Comment in the Deny Reason Text Box
		cor.get_txtBoxDenialComment().sendKeys(comment);
		
		if(cor.get_btnReOpen().isEnabled())
		{
			cor.get_btnReOpen().click();
		}
		else
		{
			objLgr.error("Re-Open Button is not Enabled in Approval Screen");
		}
		
		//Navigating to Claim Data Screen & Validating the Status
		
		cor.get_lnkClaimData().click();
		webdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement claimtxt = cor.get_txtClaimStatus();
		String claimStatus = claimtxt.getText();
		System.out.println(claimStatus);
		waitForElementLoad(cor.get_txtClaimStatus(), "Denied");
		if(!(claimStatus.equals("Denied")))
		{
			objLgr.info("Denied Claim Re-Opened Successfully: "+claimStatus);
		}
		else
		{
			objLgr.error("Claim not Re-Opened properly... Perform Re-Open Again");
		}
		
		//Updating Fields after performing Deny Claim
		
		======Description / Supporting Details=========
		try {
		cor.get_txtBoxDescription().click();
		cor.get_txtBoxDescription().clear();
		cor.get_txtBoxDescription().sendKeys(updComment);
		
		======Implication of not making the customer accommodation=========
		cor.get_txtBoxImplication().click();
		cor.get_txtBoxImplication().clear();
		cor.get_txtBoxImplication().sendKeys(updComment);
		
		======Business Rationale=========
		cor.get_txtBoxBusinessRationale().click();
		cor.get_txtBoxBusinessRationale().clear();
		cor.get_txtBoxBusinessRationale().sendKeys(updComment);
		}
		catch (Exception e)
		{
			objLgr.info("No fields are updated: "+e.getMessage());
		}
		
		//Clicking on Save Claim
		cor.get_btnSaveClaim().click();
	}
	
	@Test(dependsOnMethods="reOpenClaim", description="Performing Cancel", enabled=true)
	public void cancelClaim() throws Exception
	{
		objLgr.info("Cancel Claim");
		//Clicking on Cancel Button
		cor.get_btnCancel().click();
		if(webdriver.findElement(radioBtnclaimType).isDisplayed())
		{
			objLgr.info("Claim Cancelled Successfully");
		}
		else
		{
			objLgr.error("Claim Not Cancelled");
		}
	}
	
	@AfterTest(description = "Closing the Browser")
	public void tearDown()
	{
		webdriver.close();
	}

}
*/