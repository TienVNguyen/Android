/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.instagram_photo_viewer.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.training.tiennguyen.instagram_photo_viewer.R;
import com.training.tiennguyen.instagram_photo_viewer.model.CommentObject;

import java.util.List;

/**
 * CommentAdapter
 *
 * @author Created by TienVNguyen on 13/03/2016.
 */
public class CommentAdapter extends ArrayAdapter<CommentObject> {

    /**
     * Constructor
     *
     * @param context The current context.
     * @param objects The objects to represent in the ListView.
     */
    public CommentAdapter(Context context, List<CommentObject> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    private static class CommentHolder {
        private TextView txtName;
        private TextView txtComment;
        private RoundedImageView roundedImageCommentAvatar;
        private ProgressBar roundedImageViewProgress;
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
        CommentObject commentObject = getItem(position);
        CommentHolder commentHolder = new CommentHolder();

        // If recycled view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.comment_adapter, parent, false);
            commentHolder.txtName = (TextView) convertView.findViewById(R.id.comment_name);
            commentHolder.txtComment = (TextView) convertView.findViewById(R.id.comment_text);
            commentHolder.roundedImageCommentAvatar = (RoundedImageView) convertView.findViewById(R.id.comment_avatar);
            commentHolder.roundedImageViewProgress = (ProgressBar) convertView.findViewById(R.id.comment_progressBar);
            convertView.setTag(commentHolder);
        } else {
            commentHolder = (CommentHolder) convertView.getTag();
        }

        // Set data to view
        if (commentHolder.roundedImageCommentAvatar != null) {
            commentHolder.roundedImageViewProgress.setVisibility(View.VISIBLE);
            final CommentHolder finalCommentHolder = commentHolder;
            Picasso.with(getContext())
                    .load(commentObject.getAvatar())
                    .error(R.mipmap.ic_launcher)
                    .fit()
                    .into(commentHolder.roundedImageCommentAvatar, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            finalCommentHolder.roundedImageViewProgress.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            Log.e("Error", "Avatar Failed");
                        }
                    });
            commentHolder.roundedImageCommentAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Add/Remove 1 like of current user
                }
            });
        }
        commentHolder.txtName.setText(commentObject.getUser());
        commentHolder.txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: go to user's page
            }
        });
        commentHolder.txtComment.setText(commentObject.getText());
        commentHolder.txtComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: go to comments page and move to this comment
            }
        });

        return convertView;
    }

}
