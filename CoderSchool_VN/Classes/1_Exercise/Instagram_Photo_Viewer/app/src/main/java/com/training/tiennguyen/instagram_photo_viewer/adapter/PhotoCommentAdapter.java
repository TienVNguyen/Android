/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.instagram_photo_viewer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.training.tiennguyen.instagram_photo_viewer.R;
import com.training.tiennguyen.instagram_photo_viewer.model.CommentObject;

import java.util.List;

/**
 * PhotoCommentAdapter
 *
 * @author Created by TienVNguyen on 13/03/2016.
 */
public class PhotoCommentAdapter extends ArrayAdapter<CommentObject> {
    public int maxCommentCount = 2;

    /**
     * Constructor
     *
     * @param context The current context.
     * @param objects The objects to represent in the ListView.
     */
    public PhotoCommentAdapter(Context context, List<CommentObject> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount() {
        int i = super.getCount();
        if (i > maxCommentCount) {
            return maxCommentCount;
        } else {
            return i;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param position
     * @param convertView
     * @param parent
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get data item with the position
        CommentObject commentObject = getItem(position);

        // If recycled view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.photo_comment_adapter, parent, false);
        }

        // Initial view
        TextView txtName = (TextView) convertView.findViewById(R.id.photo_comment_user_adapter);
        TextView txtComment = (TextView) convertView.findViewById(R.id.photo_comment_comment_adapter);

        // Set data to view
        txtName.setText(commentObject.getUser());
        txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: go to user's page
            }
        });

        txtComment.setText(commentObject.getText());
        txtComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: go to comments page and move to this comment
            }
        });

        return convertView;
    }

}
