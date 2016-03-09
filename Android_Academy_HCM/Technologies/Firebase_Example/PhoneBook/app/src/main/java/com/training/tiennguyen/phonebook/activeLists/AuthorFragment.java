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
public class AuthorFragment extends Fragment {

    public AuthorFragment() {
        /* Required empty public constructor */
    }

    public static AuthorFragment newInstance() {
        /* Arguments*/
        Bundle args = new Bundle();

        /* Fragment */
        AuthorFragment authorFragment = new AuthorFragment();
        authorFragment.setArguments(args);
        return authorFragment;
    }
}
