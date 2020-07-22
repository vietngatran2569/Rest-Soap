//package com.example.restdemo2.dto;
//
//import java.util.List;
//
//public class DateRun {
//
//    public static void main(String[] args) {
//        String date = "27/02/2000";
//        int x = 3;
//
//        String[] dates = date.split("/");
//        int day = Integer.parseInt(dates[0]);
//        int month = Integer.parseInt(dates[1]);
//        int year = Integer.parseInt(dates[2]);
//
//        CalDate calDate = new CalDate(day, month, year);
//
//        int newDay = day + x;
//
//        calDate.addDay(newDay);
//        System.out.println(calDate.toString());
//    }
//
//}