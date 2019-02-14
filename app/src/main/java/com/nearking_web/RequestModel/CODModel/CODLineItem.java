package com.nearking_web.RequestModel.CODModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CODLineItem {
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("variations")
    @Expose
    private CODVariations variations;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public CODVariations getVariations() {
        return variations;
    }

    public void setVariations(CODVariations variations) {
        this.variations = variations;
    }
}
