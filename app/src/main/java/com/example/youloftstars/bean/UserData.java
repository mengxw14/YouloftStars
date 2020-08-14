package com.example.youloftstars.bean;

public class UserData {

    /**
     * BirthDay : 2019-9-12
     * BirthPlace : 北京
     * BirthTime : 14:54
     * CreateTime : 2019-9-12 14:03:44
     * Did : 123
     * Id : 1
     * IsSubscription : true
     * LastModificationTime : 2019-9-12 14:04:33
     * Lat : 21.1
     * Lng : 15.6
     * Name : 姓名
     * Sex : 0
     */

    private String BirthDay;
    private String BirthPlace;
    private String BirthTime;
    private String CreateTime;
    private String Did;
    private int Id;
    private boolean IsSubscription;
    private String LastModificationTime;
    private String Lat;
    private String Lng;
    private String Name;
    private int Sex;

    public String getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(String BirthDay) {
        this.BirthDay = BirthDay;
    }

    public String getBirthPlace() {
        return BirthPlace;
    }

    public void setBirthPlace(String BirthPlace) {
        this.BirthPlace = BirthPlace;
    }

    public String getBirthTime() {
        return BirthTime;
    }

    public void setBirthTime(String BirthTime) {
        this.BirthTime = BirthTime;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getDid() {
        return Did;
    }

    public void setDid(String Did) {
        this.Did = Did;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public boolean isIsSubscription() {
        return IsSubscription;
    }

    public void setIsSubscription(boolean IsSubscription) {
        this.IsSubscription = IsSubscription;
    }

    public String getLastModificationTime() {
        return LastModificationTime;
    }

    public void setLastModificationTime(String LastModificationTime) {
        this.LastModificationTime = LastModificationTime;
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
}
