package com.Generic;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility 
{

	  public static String getScreenshot(WebDriver driver, String name) {
	        TakesScreenshot ts = (TakesScreenshot) driver;
	        File temp = ts.getScreenshotAs(OutputType.FILE);
	        String path = System.getProperty("user.dir") + "//Screenshots//" + name + System.currentTimeMillis() + ".png";
	        File dest = new File(path);
	        try {
	            FileHandler.copy(temp, dest);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return path;
	    }
}