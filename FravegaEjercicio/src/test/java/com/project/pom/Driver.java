package com.project.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
     private static WebDriver driver;

     public static WebDriver GetIntance()
     {
         return driver;
     }
     
     public static void Init() {
    	 System.setProperty("webdriver.chrome.driver", "C:\\Users\\wcabezas\\eclipse-workspace\\FravegaEjercicio\\src\\test\\resources\\Drivers\\chromedriver.exe");
    	 driver = new ChromeDriver(); 
     }
     
}
