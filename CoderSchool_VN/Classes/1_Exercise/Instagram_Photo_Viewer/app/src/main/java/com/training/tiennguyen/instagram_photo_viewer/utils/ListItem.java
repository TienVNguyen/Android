/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.instagram_photo_viewer.utils;

import android.view.View;

/**
 * ListItem
 *
 * @author Created by TienVNguyen on 14/03/2016.
 */
public interface ListItem {
    int getVisibilityPercents(View view);

    void setActive(View newActiveView, int newActiveViewPosition);

    void deactivate(View currentView, int position);
}
