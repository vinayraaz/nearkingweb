package com.nearking_web.RequestModel.CODModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CODVariations {
    @SerializedName("pa_color")
    @Expose
    private String paColor;

    public String getPaColor() {
        return paColor;
    }

    public void setPaColor(String paColor) {
        this.paColor = paColor;
    }


}
