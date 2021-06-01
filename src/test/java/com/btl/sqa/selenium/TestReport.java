package com.btl.sqa.selenium;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestReport extends TestDriver{

    ChromeDriver chromeDriver = getDriver();

    @Test
    public void guiListStudentReport() {
        chromeDriver.get("http://localhost:63342/sqa/com/btl/sqa/html/BaoCao.html");
        String title = chromeDriver.getTitle();
        String expected = "Title";
        chromeDriver.close();
        assertEquals(expected, title, "Failed");
    }
    @Test
    public void guiListStudentReport1() {
        chromeDriver.get("http://localhost:63342/sqa/com/btl/sqa/html/BaoCao.html");
        String innerText = chromeDriver.findElement(By.xpath("//table/thead/tr/th[1]"))
                .getText();
        System.out.println(innerText);
        String expected = "Số thứ tự";
        assertEquals(expected, innerText, "Failed");
    }
    @Test
    public void guiListStudentReport2() {
        chromeDriver.get("http://localhost:63342/sqa/com/btl/sqa/html/DanhSachSinhVien.html");
        chromeDriver.findElement(By.id("stu_pass")).click();
        String innerText = chromeDriver.findElement(By.xpath("//table/thead/tr/th[1]"))
                .getText();
        System.out.println(innerText);
        String expected = "A";
        chromeDriver.close();
        assertEquals(expected, innerText, "Failed");
    }
//    @Test
//    public void guiListStudentReport1() {
//        chromeDriver.get("http://localhost:63342/sqa/com/btl/sqa/html/BaoCao.html");
//        String title = chromeDriver.getTitle();
//        String expected = "Danh sách sinh viên";
//        chromeDriver.close();
//        assertEquals(expected, title, "Failed");
//    }

    @Test
    public void resizeWindowLec() {
        chromeDriver.get("http://localhost:63342/sqa/com/btl/sqa/html/Baocao.html");
        System.out.println("Test started");
        Dimension dimension = new Dimension(480, 320);
        System.out.println("Got dimensions");
        chromeDriver.manage().window().setSize(dimension);
        System.out.println("resizing");
    }

}
