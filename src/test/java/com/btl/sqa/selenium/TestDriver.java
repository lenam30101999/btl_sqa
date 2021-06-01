package com.btl.sqa.selenium;

import org.openqa.selenium.chrome.ChromeDriver;

public class TestDriver {

  private static final String CHROMEDRIVER_EXE = "/Users/Admin/Desktop/chromedriver/chromedriver.exe";

  public ChromeDriver getDriver(){
    System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_EXE);
    return new ChromeDriver();
  }
}
