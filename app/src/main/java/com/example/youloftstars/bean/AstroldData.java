package com.example.youloftstars.bean;

public class AstroldData {
    private String Name;
    private AstroldMessage Yunshi_today;
    private AstroldMessage Yunshi_tomorrow;
    private AstroldMessage Yunshi_week;
    private AstroldMessage Yunshi_month;
    private AstroldMessage Yunshi_year;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public AstroldMessage getYunshi_today() {
        return Yunshi_today;
    }

    public void setYunshi_today(AstroldMessage yunshi_today) {
        Yunshi_today = yunshi_today;
    }

    public AstroldMessage getYunshi_tomorrow() {
        return Yunshi_tomorrow;
    }

    public void setYunshi_tomorrow(AstroldMessage yunshi_tomorrow) {
        Yunshi_tomorrow = yunshi_tomorrow;
    }

    public AstroldMessage getYunshi_week() {
        return Yunshi_week;
    }

    public void setYunshi_week(AstroldMessage yunshi_week) {
        Yunshi_week = yunshi_week;
    }

    public AstroldMessage getYunshi_month() {
        return Yunshi_month;
    }

    public void setYunshi_month(AstroldMessage yunshi_month) {
        Yunshi_month = yunshi_month;
    }

    public AstroldMessage getYunshi_year() {
        return Yunshi_year;
    }

    public void setYunshi_year(AstroldMessage yunshi_year) {
        Yunshi_year = yunshi_year;
    }
}
