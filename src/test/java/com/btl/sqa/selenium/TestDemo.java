package com.btl.sqa.selenium;

import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemo extends TestDriver {

  ChromeDriver chromeDriver = getDriver();

  @Test
  @Order(1)
  public void loginCorrect() {
    chromeDriver.get("http://localhost:63342/btl_sqa/com/btl/sqa/html/Login.html");
    WebElement username = chromeDriver.findElement(By.name("username"));
    username.sendKeys("admin");
    WebElement password = chromeDriver.findElement(By.name("password"));
    password.sendKeys("admin");
    chromeDriver.findElement(By.id("login")).click();
    String title = chromeDriver.getTitle();
    String expected = "Trang chủ";
    chromeDriver.close();
    assertEquals(expected, title, "Failed");
  }

  @Test
  @Order(2)
  public void guiLogin() {
    chromeDriver.get("http://localhost:63342/btl_sqa/com/btl/sqa/html/Login.html");
    String title = chromeDriver.getTitle();
    String expected = "Quản lý đào tạo";
    chromeDriver.close();
    assertEquals(expected, title, "Failed");
  }

  @Test
  public void loginInCorrect1() {
    chromeDriver.get("http://localhost:63342/btl_sqa/com/btl/sqa/html/Login.html");
    WebElement username = chromeDriver.findElement(By.name("username"));
    username.sendKeys("");
    WebElement password = chromeDriver.findElement(By.name("password"));
    password.sendKeys("");
    chromeDriver.findElement(By.id("login")).click();
    String title = chromeDriver.getTitle();
    String expected = "Quản lý đào tạo";
    chromeDriver.close();
    assertEquals(expected, title, "Failed");
  }

  @Test
  public void loginInCorrect2() {
    chromeDriver.get("http://localhost:63342/btl_sqa/com/btl/sqa/html/Login.html");
    WebElement username = chromeDriver.findElement(By.name("username"));
    username.sendKeys("admin");
    WebElement password = chromeDriver.findElement(By.name("password"));
    password.sendKeys("");
    chromeDriver.findElement(By.id("login")).click();
    String title = chromeDriver.getTitle();
    String expected = "Quản lý đào tạo";
    chromeDriver.close();
    assertEquals(expected, title, "Failed");
  }

  @Test
  public void loginInCorrect3() {
    chromeDriver.get("http://localhost:63342/btl_sqa/com/btl/sqa/html/Login.html");
    WebElement username = chromeDriver.findElement(By.name("username"));
    username.sendKeys("admin");
    WebElement password = chromeDriver.findElement(By.name("password"));
    password.sendKeys("1");
    chromeDriver.findElement(By.id("login")).click();
    String title = chromeDriver.getTitle();
    String expected = "Quản lý đào tạo";
    chromeDriver.close();
    assertEquals(expected, title, "Failed");
  }

  @Test
  public void loginInCorrect4() {
    chromeDriver.get("http://localhost:63342/btl_sqa/com/btl/sqa/html/Login.html");
    WebElement username = chromeDriver.findElement(By.name("username"));
    username.sendKeys("1827318391");
    WebElement password = chromeDriver.findElement(By.name("password"));
    password.sendKeys("1");
    chromeDriver.findElement(By.id("login")).click();
    String title = chromeDriver.getTitle();
    String expected = "Quản lý đào tạo";
    chromeDriver.close();
    assertEquals(expected, title, "Failed");
  }

  @Test
  public void loginInCorrect5() {
    chromeDriver.get("http://localhost:63342/btl_sqa/com/btl/sqa/html/Login.html");
    WebElement username = chromeDriver.findElement(By.name("username"));
    username.sendKeys("admin");
    WebElement password = chromeDriver.findElement(By.name("password"));
    password.sendKeys("adminnnnnn");
    chromeDriver.findElement(By.id("login")).click();
    String title = chromeDriver.getTitle();
    String expected = "Quản lý đào tạo";
    chromeDriver.close();
    assertEquals(expected, title, "Failed");
  }
}
