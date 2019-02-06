package com.nearking_web.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductResponse {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("post_type")
    @Expose
    private String postType;
    @SerializedName("post_name")
    @Expose
    private String postName;
    @SerializedName("guid")
    @Expose
    private String guid;
    @SerializedName("post_title")
    @Expose
    private String postTitle;
    @SerializedName("post_content")
    @Expose
    private String postContent;

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
