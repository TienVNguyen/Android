/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.phonebook.activeLists;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * @author Created by TienVNguyen on 09/03/2016.
 */
public class PhoneListFragment extends Fragment {

    public PhoneListFragment() {
        /* Required empty public constructor */
    }

    public static PhoneListFragment newInstance() {
        /* Arguments*/
        Bundle args = new Bundle();

        /* Fragment */
        PhoneListFragment phoneListFragment = new PhoneListFragment();
        phoneListFragment.setArguments(args);
        return phoneListFragment;
    }
}
