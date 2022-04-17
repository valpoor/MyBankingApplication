package driver;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
//import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import io.appium.java_client.remote.MobileCapabilityType;

import org.apache.logging.log4j.*;


public class Base {
	private static Logger objLgr = LogManager.getLogger(Base.class.getName());
	protected String propertyFileLocation = "src/main/java/driver/AutomationParameters.properties";
	private static final String True = null;
	public static WebDriver webdriver;
	public static RemoteWebDriver remWebDriver;
	public static Actions actn;
    public static Properties prop;
    public static FileInputStream fisPropfile;
    protected String demoSleepTime;
    protected Boolean safariBrowserUsed = false;
    public static String PingId;
    public static String pwd;
    

	public WebDriver initialize() throws IOException {
		//objLgr.info("Test Execution begins");
		prop=new Properties();
		fisPropfile = new FileInputStream(propertyFileLocation);
		if (fisPropfile == null) {
			objLgr.error("Test Execution begins");
		} else {
			objLgr.info("Property file " + propertyFileLocation + " read");
		}
		
		prop.load(fisPropfile);
		//objLgr.info("Testing with " + prop.getProperty("Browser") + " browser.");
		
//		Load driver for Chrome
		
		if(prop.getProperty("Browser").equalsIgnoreCase("Chrome")) {	
			try {
			
				chromedriver();		
			}
			catch(Exception e)
			{
				objLgr.error("Failed to launch " + prop.getProperty("Browser") + " browser.");
			}

		}
		
		
//		Load driver for Internet Explorer
		else if(prop.getProperty("Browser").equalsIgnoreCase("Internet Explorer")) {
			//Create Internet Explorer Webdriver object
			IEdriver();
		}
//		Load driver for Safari Browser
		else if(prop.getProperty("Browser").equalsIgnoreCase("Safari")) {
			
			//Create Safari webdriver object
			SafariDriver();
			
		}
//		Load driver for Mobile Emulator Browser
		else if(prop.getProperty("Browser").equalsIgnoreCase("MobileChrome")) {
			
			//Create Mobile Emulator webdriver object
			mobilechrome();
		}
		else {
			objLgr.error("Browser is not configured."+ " failed.");
		}
	    
		
	    if(webdriver == null) {
	    	objLgr.error("Webdriver object creation for " + prop.getProperty("Browser") + " Failed.");
	    } else {
	    	//objLgr.info("Webdriver object created for " + prop.getProperty("Browser") + " Passed.");
	    }
		//Create action object
		actn = new Actions(webdriver);
		if(prop.getProperty("RunType").equalsIgnoreCase("Demo")) {
			webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		demoSleepTime = prop.getProperty("DemoSleepTime");
		
		//Maximize browser for Windows and Mac
	if (prop.getProperty("Platform").equalsIgnoreCase("Windows")) {
		webdriver.manage().window().maximize();
	}
	else {
		//webdriver.manage().window().maximize();
	}

		return webdriver;		
	} 
	
	public void chromedriver(){
		try {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("useAutomationExtension", false);
		System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverLocation"));
		webdriver= new ChromeDriver(chromeOptions);
		/*webdriver= new ChromeDriver(chromeOptions);*/	
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}	
		
	}
	
	public void IEdriver() {
		System.setProperty("webdriver.ie.driver", prop.getProperty("IEDriverLocation") );
		webdriver= new InternetExplorerDriver();
	}
	
	public void SafariDriver() {
		webdriver = new SafariDriver();
		safariBrowserUsed = true;
	}
	
	public void mobilechrome() {
		String deviceName = prop.getProperty("Device");
    	objLgr.info("deviceName=" + deviceName);
    	Map<String, String> mobileEmulation = new HashMap<String, String>();      
        mobileEmulation.put("deviceName", deviceName);
        System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverLocation") );
        Map<String, Object> chromeOptions = new HashMap<String, Object>();
        chromeOptions.put("mobileEmulation", mobileEmulation);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        webdriver = new ChromeDriver(capabilities);
	}
	public WebDriver initAndroidMobWebDriver() throws IOException {
		DesiredCapabilities objDCap = new DesiredCapabilities(); 
		objDCap.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus_5X_API_25");
		objDCap.setCapability("platformName", "Android");
		objDCap.setCapability("browserName", "chrome");
		webdriver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), objDCap);
		return webdriver;		
	}
	
	public WebDriver initiOSMobWebDriver() throws MalformedURLException, InterruptedException {
/*		 This method uses the xCode simulator to run tests on.  However xCode simulator does not allow to install chrome on it
		 So testing with xCode simulator using Chrome will not be possible
		 Alternative to this is to use iPhone emulator provided by Chrome.  Function mobileEmulation has been defined for the same*/
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "iPhone 6");
//		capabilities.setCapability("udid", "");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("platformVersion", "11.0");
//		capabilities.setCapability("automationName", "XCUITest");
		capabilities.setCapability("browserName", "Safari");
		capabilities.setCapability("newCommandTimeout",180);
		capabilities.setCapability("webkitResponseTimeout", 70000);
		capabilities.setCapability("networkConnectionEnabled",true);
		capabilities.setCapability("–session-override",true);

		webdriver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(1000);
		return webdriver;
//		wd.get("https://www.westbred.com/us/en.html");
//		Thread.sleep(1000);
		
	}	
	
    public WebDriver mobileEmulation(){
    	String deviceName = "iPhone 6";
    	objLgr.info("deviceName=" + deviceName);
    	Map<String, String> mobileEmulation = new HashMap<String, String>();      
        mobileEmulation.put("deviceName", deviceName);
        System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverLocation") );
        Map<String, Object> chromeOptions = new HashMap<String, Object>();
        chromeOptions.put("mobileEmulation", mobileEmulation);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        webdriver = new ChromeDriver(capabilities);
        return webdriver;
    } 	
	
	public void getScreenshot(String result) throws IOException
	{
		if(webdriver == null){
			objLgr.info("Webdriver is null.  Cannot take screenshot");
		} else {
			File src=((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File(prop.getProperty("ScreenshotLocation")+result+prop.getProperty("Screenshot")+"screenshot.png"));
		}	
	}
	
	public void waitForElementLoad(WebElement webElement, String elementName) 
	{
		WebDriverWait wait = new WebDriverWait(webdriver,60);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(webElement));
		}catch(ElementNotFoundException exp) {
			objLgr.error("Failed to load element "+elementName+" Failed");
		}
	}
		
	public void mouseHover(WebElement webElement, String elementName)
	{
		try 
		{
		Actions a = new Actions(webdriver);
		a.moveToElement(webElement).click().perform();
		}
		catch(ElementNotFoundException exp) 
		{
			objLgr.error("Failed to load element "+elementName+" Failed");
		}
		
	}
	
}
