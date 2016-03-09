/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.phonebook.utils;

import android.content.Context;

import java.text.SimpleDateFormat;

/**
 * Utility class
 *
 * @author Created by TienVNguyen on 09/03/2016.
 */
public class Utils {
    /**
     * Format the timestamp with SimpleDateFormat
     */
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private Context context;

    /**
     * Public constructor that takes mContext for later use
     */
    public Utils(Context context) {
        this.context = context;
    }
}
