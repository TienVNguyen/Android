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
    private String type;
    private String videoUrl;
    private int videoHeight;
    private String imageUrl;
    private int imageHeight;
    private int likeCount;
    private String avatar;
    private String comment1;
    private String comment2;
    private int commentsCount;
    private String id;

    public PhotoObject() {
    }

    public PhotoObject(String name, String caption, String type, String videoUrl, int videoHeight, String imageUrl, int imageHeight, int likeCount, String avatar, String comment1, String comment2, int commentsCount, String id) {
        this.name = name;
        this.caption = caption;
        this.type = type;
        this.videoUrl = videoUrl;
        this.videoHeight = videoHeight;
        this.imageUrl = imageUrl;
        this.imageHeight = imageHeight;
        this.likeCount = likeCount;
        this.avatar = avatar;
        this.comment1 = comment1;
        this.comment2 = comment2;
        this.commentsCount = commentsCount;
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getVideoHeight() {
        return videoHeight;
    }

    public void setVideoHeight(int videoHeight) {
        this.videoHeight = videoHeight;
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

    public String getComment1() {
        return comment1;
    }

    public void setComment1(String comment1) {
        this.comment1 = comment1;
    }

    public String getComment2() {
        return comment2;
    }

    public void setComment2(String comment2) {
        this.comment2 = comment2;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
