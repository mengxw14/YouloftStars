package com.example.youloftstars.bean;

import java.util.ArrayList;

public class OrderData {
    private int Count;
    private ArrayList<OrderDetailData> Data;

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public ArrayList<OrderDetailData> getList() {
        return Data;
    }

    public void setList(ArrayList<OrderDetailData> list) {
        this.Data = list;
    }

    @Override
    public String toString() {
        return "OrderData{" +
                "Count=" + Count +
                ", list=" + Data +
                '}';
    }
}
