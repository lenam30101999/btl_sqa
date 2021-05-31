package com.btl.sqa.selenium;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemo extends TestDriver {

  @Test
  public void getSearchPage() {
    this.driver.get("https://www.google.com");
    WebElement element = this.driver.findElement(By.name("q"));
    assertNotNull(element);
  }
}
