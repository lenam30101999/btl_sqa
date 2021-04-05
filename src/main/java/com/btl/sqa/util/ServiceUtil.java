package com.btl.sqa.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ServiceUtil {
  public static Date formatDate(String date) throws ParseException {
    return new SimpleDateFormat("dd/MM/yyyy").parse(date);
  }
}
