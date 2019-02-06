package com.nearking_web.model;

public class AddCartList {
    String pid, product_name,product_price,product_image,product_type;

    public AddCartList() {
    }

    public AddCartList( String pid,String product_name, String product_price, String product_image, String product_type) {
       this.pid = pid;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_image = product_image;
        this.product_type = product_type;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }
}

