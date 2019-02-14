package com.nearking_web.model;

public class PaymentModel {
    private String id,title,methodTitle;

    public PaymentModel(String id, String title, String methodTitle) {
        this.id = id;
        this.title = title;
        this.methodTitle = methodTitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMethodTitle() {
        return methodTitle;
    }

    public void setMethodTitle(String methodTitle) {
        this.methodTitle = methodTitle;
    }
}
