package com.project.pom;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class BasePage {
	

	public WebElement FindElement(By locator) {
		Driver.GetIntance().manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		return Driver.GetIntance().findElement(locator);
	}
	
	public List<WebElement> findElements(By locator){
		Driver.GetIntance().manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		return Driver.GetIntance().findElements(locator);
	}
	
	public String getText(WebElement element) {
		return element.getText();
	}
	
	public void typeText(String text, By locator) {
		FindElement(locator).sendKeys(text);
	}
	
	public void click(By locator) {
		FindElement(locator).click();
	}
	
	public Boolean isDisplayed(By locator) {
		return FindElement(locator).isDisplayed();
	}
}
