package com.nearking_web.RequestModel.createMainModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateBilling {
    @SerializedName("phone")
    @Expose
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
