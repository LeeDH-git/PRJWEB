package org.leedh.common.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StringToDate {

/*
    ■ String → java.sql.Date 변환

    String  day = "2021-01-25"; // 형식을 지켜야 함
    java.sql.Date d = java.sql.Date.valueOf(day);


    ■ String → java.sql.Timestamp 변환

    String day = "2021-01-25 00:00:00.0"; // 형식을 지켜야 함
    java.sql.Timestamp t = java.sql.Timestamp.valueOf(day);


    ■ String → java.util.Date 변환

    String  day = "20210125"; // SimpleDateFormat 생성자에 전달되는 형식과 일치해야 함
    java.util.Date d = new java.text.SimpleDateFormat("yyyyMMdd").parse(day);

*/
    // 년, 월, 일이 각각 입력되었을 경우 Date로 변경하는 메서드
    public Date transformDate(String year, String month, String day) {
        String date = year + "-" + month + "-" + day;

        return Date.valueOf(date);
    }


    // 날짜가 yyyyMMdd 형식으로 입력되었을 경우 Date로 변경하는 메서드
    public Date transformDate(String date) {
        SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyyMMdd");

        // Date로 변경하기 위해서는 날짜 형식을 yyyy-mm-dd로 변경해야 한다.
        SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-MM-dd");

        java.util.Date tempDate = null;

        try {
            // 현재 yyyymmdd로된 날짜 형식으로 java.util.Date객체를 만든다.
            tempDate = beforeFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // java.util.Date를 yyyy-mm-dd 형식으로 변경하여 String로 반환한다.
        String transDate = afterFormat.format(tempDate);

        // 반환된 String 값을 Date로 변경한다.

        return Date.valueOf(transDate);
    }


}
