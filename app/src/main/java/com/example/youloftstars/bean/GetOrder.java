package com.example.youloftstars.bean;

public class GetOrder {
    private OrderData data;
    private Result result;

    public OrderData getData() {
        return data;
    }

    public void setData(OrderData data) {
        this.data = data;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "GetOrder{" +
                "data=" + data +
                ", result=" + result +
                '}';
    }
}
