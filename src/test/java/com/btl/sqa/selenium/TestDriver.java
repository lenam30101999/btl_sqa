package com.btl.sqa.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

public abstract class TestDriver {

  private static final String CHROMEDRIVER_EXE = "chromedriver.exe";
  protected WebDriver driver;

  @Before
  public void setUp() {
    String driverFile = findFile();
    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    ChromeDriverService service = new ChromeDriverService.Builder()
        .usingDriverExecutable(new File(driverFile))
        .build();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--no-sandbox");
    options.addArguments("--headless");
    options.setExperimentalOption("useAutomationExtension", false);
    options.addArguments("start-maximized");
    options.addArguments("disable-infobars");
    options.addArguments("--disable-extensions");
    options.addArguments("--disable-dev-shm-usage");
    options.merge(capabilities);
    this.driver = new ChromeDriver(service, options);
  }

  private String findFile() {
    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource(CHROMEDRIVER_EXE);
    return url.getFile();
  }

  @After
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}
