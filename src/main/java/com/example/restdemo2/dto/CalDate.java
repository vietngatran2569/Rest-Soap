//package com.example.restdemo2.dto;
//
//public class CalDate {
//    private int day;
//    private int month;
//    private int year;
//    private int[] monthInYear = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
//
//    public CalDate() {
//    }
//
//    public CalDate(int day, int month, int year) {
//        this.day = day;
//        this.month = month;
//        this.year = year;
//    }
//
//    public int getDay() {
//        return day;
//    }
//
//    public void setDay(int day) {
//        this.day = day;
//    }
//
//    public int getMonth() {
//        return month;
//    }
//
//    public void setMonth(int month) {
//        this.month = month;
//    }
//
//    public int getYear() {
//        return year;
//    }
//
//    public void setYear(int year) {
//        this.year = year;
//    }
//
//    @Override
//    public String toString() {
//        return this.day + "/" + this.month + "/" + this.year;
//    }
//
//    private boolean isLeapYear(int year) {
//        return (year % 4 == 0 & year % 100 != 0) || this.year % 400 == 0;
//    }
//
//    void addDay(int amount) {
//        if (isLeapYear(year)) {
//            this.monthInYear[1] = 29;
//        }
//        while (amount > this.monthInYear[this.month - 1]) {
//            amount -= this.monthInYear[this.month - 1];
//            this.month++;
//            if (this.month > 12) {
//                this.year++;
//                this.month = 1;
//            }
//        }
//        this.day = amount;
//    }
//
//}
