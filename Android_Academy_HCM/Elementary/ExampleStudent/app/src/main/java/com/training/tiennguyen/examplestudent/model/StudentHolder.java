/*
 * Copyright (c) 2015. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.examplestudent.model;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * StudentHolder
 *
 * @author TienNguyen
 */
public class StudentHolder {
    private ImageView ivAvatar;
    private TextView txtName;
    private TextView txtEmail;

    public ImageView getIvAvatar() {
        return ivAvatar;
    }

    public void setIvAvatar(ImageView ivAvatar) {
        this.ivAvatar = ivAvatar;
    }

    public TextView getTxtName() {
        return txtName;
    }

    public void setTxtName(TextView txtName) {
        this.txtName = txtName;
    }

    public TextView getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(TextView txtEmail) {
        this.txtEmail = txtEmail;
    }
}
