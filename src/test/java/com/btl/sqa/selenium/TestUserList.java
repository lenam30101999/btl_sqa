//package com.btl.sqa.selenium;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Cookie;
//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TestUserList extends TestDriver {
//
//    ChromeDriver chromeDriver = getDriver();
//
//    @Test
//    public void resizeStu() {
//        chromeDriver.get("http://localhost:63342/sqa/com/btl/sqa/html/DanhSachSinhVien.html");
//        System.out.println("Test started");
//        Dimension dimension = new Dimension(480, 320);
//        System.out.println("Got dimensions");
//        chromeDriver.manage().window().setSize(dimension);
//        System.out.println("resizing");
//    }
//
//    @Test
//    public void guiListStudent() {
//        chromeDriver.get("http://localhost:63342/sqa/com/btl/sqa/html/DanhSachSinhVien.html");
//        String title = chromeDriver.getTitle();
//        String expected = "Danh sách sinh viên";
//        chromeDriver.close();
//        assertEquals(expected, title, "Failed");
//    }
//
//    @Test
//    public void guiListStudent1() {
//        chromeDriver.get("http://localhost:63342/sqa/com/btl/sqa/html/DanhSachSinhVien.html");
//            String innerText = chromeDriver.findElement(By.xpath("//table/thead/tr/th[1]"))
//            .getText();
//    System.out.println(innerText);
//        String expected = "Số thứ tự";
//        assertEquals(expected, innerText, "Failed");
//    }
//
//    @Test
//    public void guiAddStudent() {
//        chromeDriver.get("http://localhost:63342/sqa/com/btl/sqa/html/DanhSachSinhVien.html");
//        chromeDriver.findElement(By.id("addS")).click();
//        String title = chromeDriver.getTitle();
//        String expected = "Thêm người dùng";
//        chromeDriver.close();
//        assertEquals(expected, title, "Failed");
//    }
//
//    @Test
//    public void resizeWindowLec() {
//        chromeDriver.get("http://localhost:63342/sqa/com/btl/sqa/html/DanhSachGiangVien.html");
//        System.out.println("Test started");
//        Dimension dimension = new Dimension(480, 320);
//        System.out.println("Got dimensions");
//        chromeDriver.manage().window().setSize(dimension);
//        System.out.println("resizing");
//    }
//
//
//
//    @Test
//    public void guiLecList() {
//        chromeDriver.get("http://localhost:63342/sqa/com/btl/sqa/html/DanhSachGiangVien.html");
//        String title = chromeDriver.getTitle();
//        String expected = "Danh sách giảng viên";
//        chromeDriver.close();
//        assertEquals(expected, title, "Failed");
//    }
//
//    @Test
//    public void guiLecList2() {
//        chromeDriver.get("http://localhost:63342/sqa/com/btl/sqa/html/DanhSachGiangVien.html");
//        String innerText = chromeDriver.findElement(By.xpath("//table/thead/tr/th[1]"))
//                .getText();
//        System.out.println(innerText);
//        String expected = "Số thứ tự";
//        assertEquals(expected, innerText, "Failed");
//    }
//
//    @Test
//    public void guiAddLec() {
//        chromeDriver.get("http://localhost:63342/sqa/com/btl/sqa/html/DanhSachGiangVien.html");
//        chromeDriver.findElement(By.id("addL")).click();
//        String title = chromeDriver.getTitle();
//        String expected = "Thêm người dùng";
//        chromeDriver.close();
//        assertEquals(expected, title, "Failed");
//    }
//}
