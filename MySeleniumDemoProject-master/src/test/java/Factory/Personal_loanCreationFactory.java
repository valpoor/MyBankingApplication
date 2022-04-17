package Factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import FunctionLibrary.ReusableFunctions;
import LoanTapApplication.Personal_loanCreation;
 
public class Personal_loanCreationFactory {
	private static Logger objLgr = LogManager.getLogger(Personal_loanCreationFactory.class.getName());
    
    @Factory(dataProvider="ClaimsTestData")
	public Object[] Personal_loanCreation(Hashtable<String,String> data)
    {  
    	return new Object[] {new Personal_loanCreation(data)};
    	//return new Object[] {new Claims_ReplantMisc(data)};
    }
    
    @DataProvider(name="ClaimsTestData")
    public static Object[][] claimdatasets() throws IOException{
    	ArrayList<Hashtable<String, String>> refinedDataset = new ArrayList<Hashtable<String, String>>();
    	ReusableFunctions rf = new ReusableFunctions();
    	Object[][] rawdata = rf.universalDataProvider_v2("Data/DataSheet.xlsx","BankingData");
    	objLgr.info("Number of records in excel workbook: " + rawdata.length);
    	for (int i=0;i<rawdata.length;i++){
    		Hashtable<String,String> datainstance = (Hashtable<String, String>) rawdata[i][0];
    		if (datainstance.get("Execute(Y/N)").equalsIgnoreCase("Yes")){
    			refinedDataset.add((Hashtable<String, String>) rawdata[i][0]);
    		}
    	}
    	objLgr.info("Number of records in the datasheet with Execute(Y/N) = 'Yes': " + refinedDataset.size());
    	Object[][] finalDataSet=new Object[refinedDataset.size()][1];
    	for (int i=0;i<refinedDataset.size();i++){
    		Hashtable<String,String> datainstance = refinedDataset.get(i);
    		finalDataSet[i][0] = datainstance;
    	}
    	return finalDataSet;
    }    
    
}