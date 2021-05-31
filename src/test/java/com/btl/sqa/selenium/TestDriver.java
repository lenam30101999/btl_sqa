package com.btl.sqa.selenium;

import org.openqa.selenium.chrome.ChromeDriver;

public class TestDriver {

  private static final String CHROMEDRIVER_EXE = "/Users/macbook/Downloads/chromedriver";

  public ChromeDriver getDriver(){
    System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_EXE);
    return new ChromeDriver();
  }
}
