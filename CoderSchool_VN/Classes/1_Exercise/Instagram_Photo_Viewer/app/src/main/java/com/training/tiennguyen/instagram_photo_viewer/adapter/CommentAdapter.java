/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.instagram_photo_viewer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import com.makeramen.roundedimageview.RoundedImageView;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.training.tiennguyen.instagram_photo_viewer.R;
import com.training.tiennguyen.instagram_photo_viewer.model.CommentObject;
import com.training.tiennguyen.instagram_photo_viewer.utils.ExpandableTextView;

import java.util.List;

/**
 * CommentAdapter
 *
 * @author Created by TienVNguyen on 13/03/2016.
 */
public class CommentAdapter extends ArrayAdapter<CommentObject> {
    public int maxCommentCount = 2;

    /**
     * Constructor
     *
     * @param context The current context.
     * @param objects The objects to represent in the ListView.
     */
    public CommentAdapter(Context context, List<CommentObject> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    /**
     * {@inheritDoc}
     */
    /*@Override
    public int getCount() {
        int i = super.getCount();
        if (i > maxCommentCount) {
            return maxCommentCount;
        } else {
            return i;
        }
    }*/

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
        ExpandableTextView txtName = (ExpandableTextView) convertView.findViewById(R.id.comment_name);
        ExpandableTextView txtComment = (ExpandableTextView) convertView.findViewById(R.id.comment_text);
        RoundedImageView roundedImageCommentAvatar = (RoundedImageView) convertView.findViewById(R.id.comment_avatar);
        final ProgressBar roundedImageViewProgress = (ProgressBar) convertView.findViewById(R.id.comment_progressBar);


        // Set data to view
        if (roundedImageCommentAvatar != null) {
            roundedImageViewProgress.setVisibility(View.VISIBLE);
            Transformation transformation = new RoundedTransformationBuilder()
                    .borderColor(Color.BLACK)
                    .borderWidthDp(3)
                    .cornerRadiusDp(30)
                    .oval(false)
                    .build();
            Picasso.with(getContext())
                    .load(commentObject.getAvatar())
                    .error(R.mipmap.ic_launcher)
                    .fit()
                    .transform(transformation)
                    .into(roundedImageCommentAvatar, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            roundedImageViewProgress.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            Log.e("Error", "Avatar Failed");
                        }
                    });
            roundedImageCommentAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Add/Remove 1 like of current user
                }
            });
        }

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
