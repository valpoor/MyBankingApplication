package driver;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import driver.Base;

public class listeners implements ITestListener,IInvokedMethodListener  {
	Base b=new Base();
	private static Logger objLgr = LogManager.getLogger(listeners.class.getName());
	
	
	public void onFinish(IInvokedMethod result) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

		try {			
			b.getScreenshot(result.getName());			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(b == null){
				objLgr.info("b is null");
			} else {
				objLgr.info("b is NOT null");
			}
		}
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub
		//objLgr.info("Finishing executing method : " + arg0.getTestMethod());
		
	}

	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub
		//objLgr.info("Beginning executing  method : " + arg0.getTestMethod());
		
	}

}
