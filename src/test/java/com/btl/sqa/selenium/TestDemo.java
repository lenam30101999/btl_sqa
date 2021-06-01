package com.btl.sqa.selenium;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.junit.Test;
import org.openqa.selenium.Dimension;
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
  public void testGetAll() {
    chromeDriver.get("http://localhost:63342/btl_sqa/com/btl/sqa/html/CauHinhDiem.html");
    chromeDriver.navigate().refresh();

    Dimension searchresult = chromeDriver.findElement(By.id("bodyList")).getSize();
    Dimension expected=new Dimension(871, 86);
    chromeDriver.close();
    assertEquals(expected, searchresult, "Success");
  }

  @Test
  public void testSearch() {
    chromeDriver.get("http://localhost:63342/btl_sqa/com/btl/sqa/html/CauHinhDiem.html");
    WebElement searchbar = chromeDriver.findElement(By.name("searchSub"));
    searchbar.sendKeys("Java");
    chromeDriver.findElement(By.id("search")).click();
    int searchresult = chromeDriver.findElement(By.id("bodyList")).getText().length();
    int expected=27;
    chromeDriver.close();
    assertEquals(expected, searchresult, "Success");
  }
  @Test
  public void testConfig1() {

    chromeDriver.get("http://localhost:63342/btl_sqa/com/btl/sqa/html/SuaCauHinhDiem.html?id=1");
    chromeDriver.navigate().refresh();

    WebElement th = chromeDriver.findElement(By.name("TH"));
    WebElement cc = chromeDriver.findElement(By.name("CC"));
    WebElement kt = chromeDriver.findElement(By.name("KT"));
    WebElement btl = chromeDriver.findElement(By.name("BTL"));
    WebElement cuoiKy = chromeDriver.findElement(By.name("CuoiKy"));
    th.clear();
    cc.clear();
    kt.clear();
    btl.clear();
    cuoiKy.clear();
    th.sendKeys("10");
    cc.sendKeys("10");
    kt.sendKeys("10");
    btl.sendKeys("10");
    cuoiKy.sendKeys("10");
    chromeDriver.findElement(By.id("confirm")).click();
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    String result=chromeDriver.switchTo().alert().getText();
    chromeDriver.switchTo().alert().accept();
    String expected="Tổng các đầu điểm khác 100 hoặc bị bỏ trống";
    chromeDriver.close();
    assertEquals(expected, result, "Success");
  }

  @Test
  public void testConfig2() {

    chromeDriver.get("http://localhost:63342/btl_sqa/com/btl/sqa/html/SuaCauHinhDiem.html?id=1");
    chromeDriver.navigate().refresh();

    WebElement th = chromeDriver.findElement(By.name("TH"));
    WebElement cc = chromeDriver.findElement(By.name("CC"));
    WebElement kt = chromeDriver.findElement(By.name("KT"));
    WebElement btl = chromeDriver.findElement(By.name("BTL"));
    WebElement cuoiKy = chromeDriver.findElement(By.name("CuoiKy"));
    th.clear();
    cc.clear();
    kt.clear();
    btl.clear();
    cuoiKy.clear();
    th.sendKeys("20");
    cc.sendKeys("20");
    kt.sendKeys("20");
    btl.sendKeys("20");
    cuoiKy.sendKeys("20");
    chromeDriver.findElement(By.id("confirm")).click();
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    String result=chromeDriver.switchTo().alert().getText();
    chromeDriver.switchTo().alert().accept();
    String expected="Sửa thành công";
    chromeDriver.close();
    assertEquals(expected, result, "Success");
  }

  @Test
  public void testConfig3() {

    chromeDriver.get("http://localhost:63342/btl_sqa/com/btl/sqa/html/SuaCauHinhDiem.html?id=1");
    chromeDriver.navigate().refresh();

    WebElement th = chromeDriver.findElement(By.name("TH"));
    WebElement cc = chromeDriver.findElement(By.name("CC"));
    WebElement kt = chromeDriver.findElement(By.name("KT"));
    WebElement btl = chromeDriver.findElement(By.name("BTL"));
    WebElement cuoiKy = chromeDriver.findElement(By.name("CuoiKy"));
    th.clear();
    cc.clear();
    kt.clear();
    btl.clear();
    cuoiKy.clear();
    th.sendKeys("a");
    cc.sendKeys("%");
    kt.sendKeys("2");
    btl.sendKeys("20");
    cuoiKy.sendKeys("20");
    chromeDriver.findElement(By.id("confirm")).click();
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    String result=chromeDriver.switchTo().alert().getText();
    chromeDriver.switchTo().alert().accept();
    String expected="Tổng các đầu điểm khác 100 hoặc bị bỏ trống";
    chromeDriver.close();
    assertEquals(expected, result, "Success");
  }



//  @Test
//  public void loginCorrect() {
//    chromeDriver.get("http://localhost:63342/btl_sqa/com/btl/sqa/html/Login.html");
//    WebElement username = chromeDriver.findElement(By.name("username"));
//    username.sendKeys("admin");
//    WebElement password = chromeDriver.findElement(By.name("password"));
//    password.sendKeys("admin");
//    chromeDriver.findElement(By.id("login")).click();
//    String title = chromeDriver.getTitle();
//    String expected = "Trang chủ";
//    chromeDriver.close();
//    assertEquals(expected, title, "Failed");
//  }
//
//  @Test
//  public void loginInCorrect1() {
//    chromeDriver.get("http://localhost:63342/btl_sqa/com/btl/sqa/html/Login.html");
//    WebElement username = chromeDriver.findElement(By.name("username"));
//    username.sendKeys("");
//    WebElement password = chromeDriver.findElement(By.name("password"));
//    password.sendKeys("");
//    chromeDriver.findElement(By.id("login")).click();
//    String title = chromeDriver.getTitle();
//    String expected = "Quản lý đào tạo";
//    chromeDriver.close();
//    assertEquals(expected, title, "Failed");
//  }
//
//  @Test
//  public void loginInCorrect2() {
//    chromeDriver.get("http://localhost:63342/btl_sqa/com/btl/sqa/html/Login.html");
//    WebElement username = chromeDriver.findElement(By.name("username"));
//    username.sendKeys("admin");
//    WebElement password = chromeDriver.findElement(By.name("password"));
//    password.sendKeys("");
//    chromeDriver.findElement(By.id("login")).click();
//    String title = chromeDriver.getTitle();
//    String expected = "Quản lý đào tạo";
//    chromeDriver.close();
//    assertEquals(expected, title, "Failed");
//  }
//
//  @Test
//  public void loginInCorrect3() {
//    chromeDriver.get("http://localhost:63342/btl_sqa/com/btl/sqa/html/Login.html");
//    WebElement username = chromeDriver.findElement(By.name("username"));
//    username.sendKeys("admin");
//    WebElement password = chromeDriver.findElement(By.name("password"));
//    password.sendKeys("1");
//    chromeDriver.findElement(By.id("login")).click();
//    String title = chromeDriver.getTitle();
//    String expected = "Quản lý đào tạo";
//    chromeDriver.close();
//    assertEquals(expected, title, "Failed");
//  }
//
//  @Test
//  public void loginInCorrect4() {
//    chromeDriver.get("http://localhost:63342/btl_sqa/com/btl/sqa/html/Login.html");
//    WebElement username = chromeDriver.findElement(By.name("username"));
//    username.sendKeys("1827318391");
//    WebElement password = chromeDriver.findElement(By.name("password"));
//    password.sendKeys("1");
//    chromeDriver.findElement(By.id("login")).click();
//    String title = chromeDriver.getTitle();
//    String expected = "Quản lý đào tạo";
//    chromeDriver.close();
//    assertEquals(expected, title, "Failed");
//  }
//
//  @Test
//  public void loginInCorrect5() {
//    chromeDriver.get("http://localhost:63342/btl_sqa/com/btl/sqa/html/Login.html");
//    WebElement username = chromeDriver.findElement(By.name("username"));
//    username.sendKeys("admin");
//    WebElement password = chromeDriver.findElement(By.name("password"));
//    password.sendKeys("adminnnnnn");
//    chromeDriver.findElement(By.id("login")).click();
//    String title = chromeDriver.getTitle();
//    String expected = "Quản lý đào tạo";
//    chromeDriver.close();
//    assertEquals(expected, title, "Failed");
//  }
}
