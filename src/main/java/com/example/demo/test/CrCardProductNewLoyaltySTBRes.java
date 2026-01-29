package com.example.demo.test;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CrCardProductNewLoyaltySTBRes {
    private CrCardHeadingSTBRes heading;
    private String header;
    private List<CrCardItemSTBRes> items;
    private String anchorId;
    private String anchorTitle;

    @JsonProperty(":type")
    private String type;

    @JsonProperty(":id")
    private String id;

    public CrCardHeadingSTBRes getHeading() {
        return heading;
    }

    public void setHeading(CrCardHeadingSTBRes heading) {
        this.heading = heading;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<CrCardItemSTBRes> getItems() {
        return items;
    }

    public void setItems(List<CrCardItemSTBRes> items) {
        this.items = items;
    }

    public String getAnchorId() {
        return anchorId;
    }

    public void setAnchorId(String anchorId) {
        this.anchorId = anchorId;
    }

    public String getAnchorTitle() {
        return anchorTitle;
    }

    public void setAnchorTitle(String anchorTitle) {
        this.anchorTitle = anchorTitle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
