package com.example.demo.test;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CrCardDataSTBRes {
    private String title;
    private int order;
    private String dataType;
    private Object childPages;
    private CrCardInternalDataSTBRes internalData;

    @JsonProperty(":id")
    private String id;

    @JsonProperty(":type")
    private String type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Object getChildPages() {
        return childPages;
    }

    public void setChildPages(Object childPages) {
        this.childPages = childPages;
    }

    public CrCardInternalDataSTBRes getInternalData() {
        return internalData;
    }

    public void setInternalData(CrCardInternalDataSTBRes internalData) {
        this.internalData = internalData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
