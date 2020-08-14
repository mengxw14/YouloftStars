package com.example.youloftstars.bean;

import java.util.ArrayList;

public class GetProduct {
    private ArrayList<ProductInfo> data;
    private Result result;

    public ArrayList<ProductInfo> getData() {
        return data;
    }

    public void setData(ArrayList<ProductInfo> data) {
        this.data = data;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
