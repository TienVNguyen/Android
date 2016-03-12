/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.instagram_photo_viewer.model;

/**
 * PhotoObject
 *
 * @author Created by TienVNguyen on 10/03/2016.
 */
public class PhotoObject {
    private String name;
    private String caption;
    private String imageUrl;
    private int imageHeight;
    private int likeCount;
    private String avatar;

    public PhotoObject() {
    }

    public PhotoObject(String name, String caption, String imageUrl, int imageHeight, int likeCount, String avatar) {
        this.name = name;
        this.caption = caption;
        this.imageUrl = imageUrl;
        this.imageHeight = imageHeight;
        this.likeCount = likeCount;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
