package com.btl.sqa.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

@Component
public class ServiceUtil {
  public static Date formatDate(String date) throws ParseException {
    return new SimpleDateFormat("dd/MM/yyyy").parse(date);
  }

  public static boolean formatIdentifyCard(String identifyCard) {
    String REGEX = "^[bB][0-9]{2}[dDcC]{2}[a-zA-Z]{2}[0-9]{3}";
    return Pattern.matches(REGEX, identifyCard);
  }
}
