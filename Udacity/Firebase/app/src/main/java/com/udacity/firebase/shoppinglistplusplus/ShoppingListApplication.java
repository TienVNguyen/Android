/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.udacity.firebase.shoppinglistplusplus;

import com.firebase.client.Firebase;

/**
 * Includes one-time initialization of Firebase related code
 *
 * @author TienVNguyen
 */
public class ShoppingListApplication extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /* Initialize Firebase */
        Firebase.setAndroidContext(this);
    }

}