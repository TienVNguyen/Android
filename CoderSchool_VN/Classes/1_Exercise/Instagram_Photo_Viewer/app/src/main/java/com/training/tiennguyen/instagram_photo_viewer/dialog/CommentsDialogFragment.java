/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.instagram_photo_viewer.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.training.tiennguyen.instagram_photo_viewer.R;

/**
 * CommentsDialogFragment
 *
 * @author Created by TienVNguyen on 13/03/2016.
 */
public class CommentsDialogFragment extends DialogFragment {
    public static DialogFragment newInstance() {
        /* Arguments*/
        Bundle args = new Bundle();

        /* Fragment */
        CommentsDialogFragment commentsDialogFragment = new CommentsDialogFragment();
        commentsDialogFragment.setArguments(args);
        return commentsDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    /**
     * Override to build your own custom Dialog container.
     *
     * @param savedInstanceState The last saved instance state of the Fragment,
     *                           or null if this is a freshly created Fragment.
     * @return Return a new Dialog instance to be displayed by the Fragment.
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        /**
         *  Use the Builder class for convenient dialog construction
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), 1);

        /**
         *  Get the layout inflater
         */
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View rootView = layoutInflater.inflate(R.layout.dialog_comments_list, null);

        /**
         * Inflate and set the layout for the dialog
         * Pass null as the parent view because its going in the dialog layout
         */
        builder.setView(rootView);

        return builder.create();
    }
}
