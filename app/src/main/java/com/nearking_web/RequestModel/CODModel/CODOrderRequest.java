package com.nearking_web.RequestModel.CODModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CODOrderRequest {
    @SerializedName("payment_method")
    @Expose
    private String paymentMethod;
    @SerializedName("payment_method_title")
    @Expose
    private String paymentMethodTitle;
    @SerializedName("set_paid")
    @Expose
    private Boolean setPaid;
    @SerializedName("billing")
    @Expose
    private CODBilling billing;
    @SerializedName("shipping")
    @Expose
    private CODShipping shipping;
    @SerializedName("line_items")
    @Expose
    private List<CODLineItem> lineItems = null;

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethodTitle() {
        return paymentMethodTitle;
    }

    public void setPaymentMethodTitle(String paymentMethodTitle) {
        this.paymentMethodTitle = paymentMethodTitle;
    }

    public Boolean getSetPaid() {
        return setPaid;
    }

    public void setSetPaid(Boolean setPaid) {
        this.setPaid = setPaid;
    }

    public CODBilling getBilling() {
        return billing;
    }

    public void setBilling(CODBilling billing) {
        this.billing = billing;
    }

    public CODShipping getShipping() {
        return shipping;
    }

    public void setShipping(CODShipping shipping) {
        this.shipping = shipping;
    }

    public List<CODLineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<CODLineItem> lineItems) {
        this.lineItems = lineItems;
    }


}
