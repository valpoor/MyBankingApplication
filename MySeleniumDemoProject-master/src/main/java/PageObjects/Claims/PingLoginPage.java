package PageObjects.Claims;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class PingLoginPage {
	
	public WebDriver driver;
    public RemoteWebDriver rmWebDriver;
    
    By username = By.id("username");
    By password = By.id("password");  
    By loginButton = By.xpath("//*[@id='my-tab-content']/form/div[2]/button[@class='amp-button normal allow']");
    By grantButton = By.xpath("//*[@id='b_js_sigBtn']");
    By loginerror = By.xpath("/html/body/div[1]/div/i/div[1]/div[2]");
    
    
    public PingLoginPage(WebDriver driver) {
              this.driver=driver;
    }

    public PingLoginPage(RemoteWebDriver driver) {
              this.rmWebDriver=driver;
    }        
    
    public WebElement get_username() {
              return driver.findElement(username);
    }

    public WebElement get_password() {
              return driver.findElement(password);
    }
    public WebElement get_loginButton() {
              return driver.findElement(loginButton);
    }
    
    public WebElement get_loginerror() {
              return driver.findElement(loginerror);
    }
    
    public WebElement get_grantButton() {
              return driver.findElement(grantButton);
    }


}
