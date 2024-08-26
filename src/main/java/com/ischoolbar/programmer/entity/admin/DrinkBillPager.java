package com.ischoolbar.programmer.entity.admin;

import com.ischoolbar.programmer.entity.admin.Pager;

public class DrinkBillPager extends Pager {
    private Integer providerId;
    private String productName;

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
