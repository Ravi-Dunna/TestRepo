package com.Varden.testcases;

import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.Varden.base.TestBase;

public class MylistTest extends TestBase{
	
/**
 * This method is used to navigate to an existing list  
 * Finding units with search (Example:Gotland)
 * Adding units/items to the list  
 * @throws InterruptedException 
 */	
@Test(priority=0)
public void myList() {
	log.debug("finding units in my list for Gotland");		
    //calling find element method and doing assertion
	Assert.assertTrue(findElement(OR.getProperty("coockieAcceptOKButton")));
	driver.findElement(By.xpath(OR.getProperty("createDeviceListButton"))).click();		
	driver.findElement(By.xpath(OR.getProperty("myListDefaultLink"))).click();		
	driver.findElement(By.xpath(OR.getProperty("searchDevicesTextBox"))).sendKeys("gotland");
	//calling explicit wait method
	waitForElement(OR.getProperty("dropdown"));
	//calling selectAllCheckboxes method
	selectAllCheckboxes(OR.getProperty("selectCheckboxes"));	
	driver.findElement(By.xpath(OR.getProperty("listNameCheckbox"))).click();
}




/**
 * This method is used to create a new list  
 * and add items to the list
 */	
@Test(priority=1)
public void createNewList() {	
	driver.findElement(By.xpath(OR.getProperty("backToMyDeviceListLink"))).click();		
	driver.findElement(By.xpath(OR.getProperty("createDeviceListButton"))).click();
	//Thread.sleep(1000);	
	driver.findElement(By.xpath("//a[@title='Min lista (2)']")).click();
	driver.findElement(By.xpath(OR.getProperty("searchDevicesTextBox"))).sendKeys("gotland");
	//calling explicit wait method
	waitForElement(OR.getProperty("dropdown"));
	//calling selectAllCheckboxes method
	selectAllCheckboxes(OR.getProperty("selectCheckboxes"));	
	driver.findElement(By.xpath(OR.getProperty("listNameCheckbox"))).click();
}




/**
 * This method is used to change the name of the list  
 */	
@Test(priority=2)
public void changeNameOfList() {
	driver.findElement(By.xpath(OR.getProperty("listNameCheckbox"))).clear();	
	driver.findElement(By.xpath(OR.getProperty("listNameCheckbox"))).sendKeys("Ravi List");
}




/**
 * This method is used to change the color of unit  
 */	
@Test(priority=3)
public void changeColorOfUnit() {   
	WebElement ele=driver.findElement(By.xpath(OR.getProperty("colorSelectionButton")));
    ele.click();
    //Thread.sleep(1000);
	driver.findElement(By.xpath(OR.getProperty("RedColorButton"))).click();
	String colorSelected = ele.getAttribute("color");
	System.out.println(colorSelected);	
}




/**
 * This method is used to remove the list 
 */	
@Test(priority=4)
public void removeList() {
	//Thread.sleep(1000);    
	List<WebElement> list = driver.findElements(By.xpath(OR.getProperty("removeListButton")));
	for(int i=0; i<list.size();i++) {			
		list.get(i).click();
	}
}




/**
 * This method is used to checking for row count 
 * after deleting all the units/items 
 */	
@Test(priority=5)
public void name() {	
	  WebElement table = driver.findElement(By.xpath(OR.getProperty("tableRowCount")));
      String rowCount = table.getAttribute("aria-rowcount");
      System.out.println(rowCount);
      Assert.assertEquals(1, Integer.parseInt(rowCount));
}


}

