package com.example.demo.test;


public class CrCardInfoSTBRes {
    private int statusCode;
    private String msg;
    private CrCardDataSTBRes data;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CrCardDataSTBRes getData() {
        return data;
    }

    public void setData(CrCardDataSTBRes data) {
        this.data = data;
    }
}

