//package com.btl.sqa.selenium;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.openqa.selenium.Cookie;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TestPoint extends TestDriver {
//
//  ChromeDriver chromeDriver = getDriver();
//  public static final String valueCookie = "{\"id\":2,\"name\":\"Nguyen Thi BC\",\"username\":\"B17DCCN123\",\"password\":\"12345678\",\"dob\":\"10/12/99, 12:00 AM\",\"phoneNo\":\"0984198412\",\"role\":\"STUDENT\",\"gender\":\"MALE\",\"address\":\"Hà Đông, Hà Nội\",\"email\":\"nguyenthiBc@gmail.com\",\"identifyCard\":\"B17DCCN123\",\"classId\":0,\"className\":\"E17CN02\",\"facultyName\":\"Công nghệ thông tin\"}";
//  public static final String allPoint = "[{\"id\":1,\"name\":\"Học kỳ 1\",\"description\":\"2017-2018\",\"points\":[{\"id\":1,\"studentName\":\"Nguyen Thi BC\",\"studentCode\":\"B17DCCN123\",\"diemCC\":7,\"diemTH\":0,\"diemBTL\":8,\"diemKT\":7,\"diemCuoiKy\":7,\"point4\":3,\"point10\":7.2,\"pointString\":\"B\",\"subjectName\":\"Java\",\"codeSubject\":\"INT123\"},{\"id\":2,\"studentName\":\"Nguyen Thi BC\",\"studentCode\":\"B17DCCN123\",\"diemCC\":8,\"diemTH\":0,\"diemBTL\":9,\"diemKT\":8.5,\"diemCuoiKy\":8,\"point4\":3,\"point10\":7.35,\"pointString\":\"B\",\"subjectName\":\"C++\",\"codeSubject\":\"INT456\"}]},{\"id\":2,\"name\":\"Học kỳ 2\",\"description\":\"2018-2019\",\"points\":[{\"id\":3,\"studentName\":\"Nguyen Thi BC\",\"studentCode\":\"B17DCCN123\",\"diemCC\":9,\"diemTH\":0,\"diemBTL\":8,\"diemKT\":7.5,\"diemCuoiKy\":9,\"point4\":3.7,\"point10\":8.5,\"pointString\":\"A+\",\"subjectName\":\"C\",\"codeSubject\":\"INT567\"},{\"id\":5,\"studentName\":\"Nguyen Thi BC\",\"studentCode\":\"B17DCCN123\",\"diemCC\":9,\"diemTH\":0,\"diemBTL\":9,\"diemKT\":9,\"diemCuoiKy\":9,\"point4\":4,\"point10\":9,\"pointString\":\"A+\",\"subjectName\":\"Java\",\"codeSubject\":\"INT123\"}]}]";
//
//  @Test
//  public void guiPointStudent() {
//    chromeDriver.get("http://localhost:63342/btl_sqa/com/btl/sqa/html/BangDiemCaNhanToanKhoa.html");
//    String title = chromeDriver.getTitle();
//    String expected = "Bảng điểm cá nhân";
//    chromeDriver.close();
//    assertEquals(expected, title, "Failed");
//  }
//
//  @Test
//  public void guiPointStudent1() {
//    chromeDriver.get("http://localhost:63342/btl_sqa/com/btl/sqa/html/BangDiemCaNhanToanKhoa.html");
//    Cookie cookie = new Cookie("user", valueCookie);
//    chromeDriver.manage().addCookie(cookie);
//    String title = chromeDriver.getTitle();
//    String expected = "Bảng điểm cá nhân";
//    chromeDriver.getSessionStorage();
//    chromeDriver.close();
//    assertEquals(expected, title, "Failed");
//  }
//
//  @Test
//  public void guiPointAllStudent() {
//    chromeDriver.get("http://localhost:63342/btl_sqa/com/btl/sqa/html/TheoDoiDiem.html");
//    String title = chromeDriver.getTitle();
//    String expected = "Theo dõi điểm";
//    chromeDriver.close();
//    assertEquals(expected, title, "Failed");
//  }
//
//  @Test
//  public void guiPointAllStudent2() {
//    chromeDriver.get("http://localhost:63342/btl_sqa/com/btl/sqa/html/TheoDoiDiem.html");
//    Cookie cookie = new Cookie("AllPoint", allPoint);
//    chromeDriver.manage().addCookie(cookie);
//    String title = chromeDriver.getTitle();
//    String expected = "Theo dõi điểm";
//    chromeDriver.close();
//    assertEquals(expected, title, "Failed");
//  }
//
//}
