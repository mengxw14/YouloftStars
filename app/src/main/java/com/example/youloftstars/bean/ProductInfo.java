package com.example.youloftstars.bean;

public class ProductInfo {

    /**
     * Id : 2
     * IsMain : true
     * IsNative : false
     * Type : 2
     * Title : 占星
     * Url : 222
     * Content : 占星占星
     * Picture : 22222
     * IsOrder : true
     * PayStatus : 1
     * OrderID : 11111
     * Sort : 2
     */

    private int Id;
    private boolean IsMain;
    private boolean IsNative;
    private int Type;
    private String Title;
    private String Url;
    private String Content;
    private String Picture;
    private boolean IsOrder;
    private int PayStatus;
    private String OrderID;
    private int Sort;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public boolean isIsMain() {
        return IsMain;
    }

    public void setIsMain(boolean IsMain) {
        this.IsMain = IsMain;
    }

    public boolean isIsNative() {
        return IsNative;
    }

    public void setIsNative(boolean IsNative) {
        this.IsNative = IsNative;
    }

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String Picture) {
        this.Picture = Picture;
    }

    public boolean isIsOrder() {
        return IsOrder;
    }

    public void setIsOrder(boolean IsOrder) {
        this.IsOrder = IsOrder;
    }

    public int getPayStatus() {
        return PayStatus;
    }

    public void setPayStatus(int PayStatus) {
        this.PayStatus = PayStatus;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public int getSort() {
        return Sort;
    }

    public void setSort(int Sort) {
        this.Sort = Sort;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "Id=" + Id +
                ", IsMain=" + IsMain +
                ", IsNative=" + IsNative +
                ", Type=" + Type +
                ", Title='" + Title + '\'' +
                ", Url='" + Url + '\'' +
                ", Content='" + Content + '\'' +
                ", Picture='" + Picture + '\'' +
                ", IsOrder=" + IsOrder +
                ", PayStatus=" + PayStatus +
                ", OrderID='" + OrderID + '\'' +
                ", Sort=" + Sort +
                '}';
    }
}
