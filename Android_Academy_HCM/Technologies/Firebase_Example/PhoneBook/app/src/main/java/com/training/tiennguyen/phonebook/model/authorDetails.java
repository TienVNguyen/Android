/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.phonebook.model;

/**
 * Defines the data structure for Author Details objects.
 *
 * @author Created by TienVNguyen on 12/03/2016.
 */
public class AuthorDetails {
    private String email;
    private String name;
    private String phone;
    private String urlAvatar;
    private String urlGit;
    private String urlGitExercise;

    public AuthorDetails() {
    }

    public AuthorDetails(String email, String name, String phone, String urlAvatar, String urlGit, String urlGitExercise) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.urlAvatar = urlAvatar;
        this.urlGit = urlGit;
        this.urlGitExercise = urlGitExercise;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public String getUrlGit() {
        return urlGit;
    }

    public String getUrlGitExercise() {
        return urlGitExercise;
    }
}
