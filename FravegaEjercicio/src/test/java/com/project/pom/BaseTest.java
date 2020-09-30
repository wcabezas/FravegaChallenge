package com.project.pom;



import org.junit.After;
import org.junit.Before;

public abstract class BaseTest {
	
	String URL = "https://www.fravega.com"; 
	
	@Before
	public void setUp() throws Exception {
		Driver.Init();
		Driver.GetIntance().get(URL);
		Driver.GetIntance().manage().window().maximize();
	}

	@After
	public void TearDown() throws Exception {
		Driver.GetIntance().quit();
	}
	

}
