package com.btl.sqa.util;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

@Component
public class ServiceUtil {
  public static Date formatDate(String date) throws ParseException {
    if (date != null && !date.equals("")){
      return new SimpleDateFormat("dd/MM/yyyy").parse(date);
    }else return null;
  }

  public static boolean formatIdentifyCard(String identifyCard) {
    String REGEX = "^[bB][0-9]{2}[dDcC]{2}[a-zA-Z]{2}[0-9]{3}";
    return Pattern.matches(REGEX, identifyCard);
  }

  public static Float formatNumber(float number){
    if (number >= 0.0 && number <= 10.0){
      return checkDecimal(number);
    }else return null;
  }

  public static Float formatPercent(float percent){
    if (percent >= 0.0 && percent <= 100.0){
      return checkDecimal(percent / 100.0f);
    }else return null;
  }

  public static float checkDecimal(float number){
    return Float.parseFloat(new DecimalFormat("##.#").format(number));
  }
}
