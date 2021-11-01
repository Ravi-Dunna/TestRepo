package com.Varden.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class TestBase {
	
	public static WebDriver driver;	
	public static Properties config=new Properties();
	public static Properties OR=new Properties();
	public static FileInputStream fis;
	public static Logger log=Logger.getLogger("devpinoyLogger");
	String path;	
	
/**
 * This method is used to load the config files 
 * and instantiate the drivers
 * and navigate to the url
 */		
@BeforeSuite
public void setUp() throws InterruptedException {		
	if(driver==null) {				
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
		} catch (FileNotFoundException e) {				
			e.printStackTrace();
		}
		try {
			config.load(fis);
			log.debug("config file loaded");
		} catch (IOException e) {				
			e.printStackTrace();
		}			
		
		try {
			fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}
		try {
			OR.load(fis);
		} catch (IOException e) {				
			e.printStackTrace();
		}
				
		if(config.getProperty("browser").equalsIgnoreCase("firefox")) {
			path=System.getProperty("user.dir");				
			System.setProperty("webdriver.gecko.driver", path+"\\drivers\\geckodriver\\geckodriver.exe");				
			driver=new FirefoxDriver();
		}else if(config.getProperty("browser").equalsIgnoreCase("chrome")) {			
			System.setProperty("webdriver.chrome.driver", path+"/src/test/resources/executables/chromedriver.exe");
			driver=new ChromeDriver();
		}else if(config.getProperty("browser").equalsIgnoreCase("ie")) {		
			System.setProperty("webdriver.ie.driver", path+"\\drivers\\geckodriver\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		driver.navigate().to(config.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),TimeUnit.SECONDS);
		
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = config.getProperty("url");
		Assert.assertEquals(expectedUrl, actualUrl);		
	}
}

	
/**
 * This method is used to wait for the element 
 * until it is visible
 * @param element 
 */		
public void waitForElement(String element) {
	WebDriverWait wait = new WebDriverWait(driver,90);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));		 
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(config.getProperty(element))));
}


	
/**
 * This method is used to find the element
 * @param by 
 */	
protected boolean findElement(String by) {
	try {			 
		driver.findElement(By.xpath(by)).click();		
		return true;			
	} catch (NoSuchElementException e) {
		return false;		
	}
}

	
		
	
/**
 * This method is used to wait for element till visibility of element.
 * 
 * @param driver
 * @param attributeValue
 *            - provide locator value of element till it is visible on
 *            application and then click that element.
 * @param waitTime
 *            - provide maximum wait time in seconds for driver
 */
/*public boolean waitForElementToBeVisible(WebDriver driver, By attributeValue, int waitTime) {
	boolean flag = false;
	try {
		new WebDriverWait(driver, waitTime).ignoring(StaleElementReferenceException.class);
				.until(ExpectedConditions.visibilityOfElementLocated(attributeValue));
		flag=true;
		return flag;
	} catch (Exception Ex) {
		return flag;
	}
}
*/	



/**
 * This method is used to verify the page title
 * @param expectedPageTitle 
 */	
public boolean verifyPageTitle(String expectedPageTitle)
{
	String expected=config.getProperty(expectedPageTitle);
	String actual=driver.getTitle();
	if(expected.equals(actual))
		return true;
	else
		return false;
}
 


/**
 * This method is used to select all checkboxes
 * @param by 
 */	
public void selectAllCheckboxes(String by) {
	List<WebElement> list = driver.findElements(By.xpath(by));
	if (list.size()!=0) {	
		for(int i=0; i<list.size()-1;i++) {
			boolean checked = list.get(i).isSelected();
			if (!checked) {
				list.get(i).click();
			}			
		}
}   }




/**
 * This method is used to all the browser windows 
 * and terminates the WebDriver session.  
 */	
@AfterSuite
public void tearDown() {
//	if (driver!= null) {
//		driver.quit();
//	}
}
 
}
