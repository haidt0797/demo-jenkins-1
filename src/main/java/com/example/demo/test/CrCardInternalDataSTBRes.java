package com.example.demo.test;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CrCardInternalDataSTBRes {

    @JsonProperty("product_newloyalty")
    private List<CrCardProductNewLoyaltySTBRes> productNewLoyalties;

    public List<CrCardProductNewLoyaltySTBRes> getProductNewLoyalties() {
        return productNewLoyalties;
    }

    public void setProductNewLoyalties(List<CrCardProductNewLoyaltySTBRes> productNewLoyalties) {
        this.productNewLoyalties = productNewLoyalties;
    }
}
