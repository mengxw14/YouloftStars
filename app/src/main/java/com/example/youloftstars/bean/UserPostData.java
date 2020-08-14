package com.example.youloftstars.bean;

public class UserPostData {

    /**
     * Id : 12
     * Name : 姓名
     * Sex : 0
     * BirthDay : 2019-8-28
     * BirthTime : 14:54
     * BirthPlace : 北京
     * Lat : 21.1
     * Lng : 15.6
     */

    //private int Id;
    private String Name;
    private int Sex;
    private String BirthDay;
    private String BirthTime;
    private String BirthPlace;
    private String Lat;
    private String Lng;

//    public int getId() {
//        return Id;
//    }
//
//    public void setId(int Id) {
//        this.Id = Id;
//    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getSex() {
        return Sex;
    }

    public void setSex(int Sex) {
        this.Sex = Sex;
    }

    public String getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(String BirthDay) {
        this.BirthDay = BirthDay;
    }

    public String getBirthTime() {
        return BirthTime;
    }

    public void setBirthTime(String BirthTime) {
        this.BirthTime = BirthTime;
    }

    public String getBirthPlace() {
        return BirthPlace;
    }

    public void setBirthPlace(String BirthPlace) {
        this.BirthPlace = BirthPlace;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String Lat) {
        this.Lat = Lat;
    }

    public String getLng() {
        return Lng;
    }

    public void setLng(String Lng) {
        this.Lng = Lng;
    }
}
