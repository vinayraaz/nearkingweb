package com.nearking_web.model;

public class CategoryProductModel {
    private String proId, proName, proSlug, proType, proStatus, proShotDesc, proDesc, proPrice, proRegularprice, proSalePrice, proTaxStatus,
            stockStatus, averageRating, proImage,ratingCount,purchageNots;

    public CategoryProductModel(String proId, String proName, String proSlug, String proType, String proStatus, String proShotDesc, String proDesc, String proPrice, String proRegularprice, String proSalePrice, String proTaxStatus, String stockStatus, String averageRating, String proImage, String ratingCount, String purchageNots) {
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
        this.ratingCount = ratingCount;
        this.purchageNots = purchageNots;
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

    public String getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(String ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getPurchageNots() {
        return purchageNots;
    }

    public void setPurchageNots(String purchageNots) {
        this.purchageNots = purchageNots;
    }
}
