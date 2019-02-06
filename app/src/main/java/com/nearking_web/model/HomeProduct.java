package com.nearking_web.model;

public class HomeProduct {

    private String proId, proName, proSlug, proType, proStatus, proShotDesc, proDesc, proPrice, proRegularprice, proSalePrice, proTaxStatus,
            stockStatus, averageRating, proImage;

    private Integer id;
    private String postType;
    private String postName;
    private String guid;
    private String postTitle;
    private String postContent;

    public HomeProduct(String proId, String proName, String proSlug, String proType, String proStatus, String proShotDesc, String proDesc, String proPrice, String proRegularprice, String proSalePrice, String proTaxStatus, String stockStatus, String averageRating, String proImage) {
        this.proId = proId;
        this.proName = proName;
        this.proSlug = proSlug;
        this.proType = proType;
        this.proStatus = proStatus;
        this.proShotDesc = proShotDesc;
        this.proDesc = proDesc;
        this.proPrice = proPrice;
        this.proRegularprice = proRegularprice;
        this.proSalePrice = proSalePrice;
        this.proTaxStatus = proTaxStatus;
        this.stockStatus = stockStatus;
        this.averageRating = averageRating;
        this.proImage = proImage;
    }

    public HomeProduct(Integer id, String postType, String postName, String guid, String postTitle, String postContent) {
        this.id = id;
        this.postType = postType;
        this.postName = postName;
        this.guid = guid;
        this.postTitle = postTitle;
        this.postContent = postContent;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProSlug() {
        return proSlug;
    }

    public void setProSlug(String proSlug) {
        this.proSlug = proSlug;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public String getProStatus() {
        return proStatus;
    }

    public void setProStatus(String proStatus) {
        this.proStatus = proStatus;
    }

    public String getProShotDesc() {
        return proShotDesc;
    }

    public void setProShotDesc(String proShotDesc) {
        this.proShotDesc = proShotDesc;
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getProPrice() {
        return proPrice;
    }

    public void setProPrice(String proPrice) {
        this.proPrice = proPrice;
    }

    public String getProRegularprice() {
        return proRegularprice;
    }

    public void setProRegularprice(String proRegularprice) {
        this.proRegularprice = proRegularprice;
    }

    public String getProSalePrice() {
        return proSalePrice;
    }

    public void setProSalePrice(String proSalePrice) {
        this.proSalePrice = proSalePrice;
    }

    public String getProTaxStatus() {
        return proTaxStatus;
    }

    public void setProTaxStatus(String proTaxStatus) {
        this.proTaxStatus = proTaxStatus;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public String getProImage() {
        return proImage;
    }

    public void setProImage(String proImage) {
        this.proImage = proImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }


}
