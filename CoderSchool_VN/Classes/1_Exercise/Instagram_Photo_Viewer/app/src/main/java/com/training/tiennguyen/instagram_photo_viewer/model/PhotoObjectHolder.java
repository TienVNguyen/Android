/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.instagram_photo_viewer.model;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Created by TienVNguyen on 10/03/2016.
 */
public class PhotoObjectHolder {
    private ImageView imgAvatar;
    private TextView txtName;
    private ImageView imgPicture;

    public PhotoObjectHolder() {
    }

    public PhotoObjectHolder(ImageView imgAvatar, TextView txtName, ImageView imgPicture) {
        this.imgAvatar = imgAvatar;
        this.txtName = txtName;
        this.imgPicture = imgPicture;
    }

    public ImageView getImgAvatar() {
        return imgAvatar;
    }

    public void setImgAvatar(ImageView imgAvatar) {
        this.imgAvatar = imgAvatar;
    }

    public TextView getTxtName() {
        return txtName;
    }

    public void setTxtName(TextView txtName) {
        this.txtName = txtName;
    }

    public ImageView getImgPicture() {
        return imgPicture;
    }

    public void setImgPicture(ImageView imgPicture) {
        this.imgPicture = imgPicture;
    }
}
