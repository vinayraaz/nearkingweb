package com.nearking_web.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BannerResponse {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("post_type")
    @Expose
    private String postType;
    @SerializedName("guid")
    @Expose
    private String guid;

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

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }


}
