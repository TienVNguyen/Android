/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.instagram_photo_viewer.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.training.tiennguyen.instagram_photo_viewer.R;
import com.training.tiennguyen.instagram_photo_viewer.model.CommentObject;
import com.training.tiennguyen.instagram_photo_viewer.model.PhotoObject;
import com.training.tiennguyen.instagram_photo_viewer.ui.CommentsActivity;
import com.training.tiennguyen.instagram_photo_viewer.ui.VideoActivity;
import com.training.tiennguyen.instagram_photo_viewer.utils.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * PhotoAdapter
 *
 * @author Created by TienVNguyen on 10/03/2016.
 */
public class PhotoAdapter extends ArrayAdapter<PhotoObject> {

    /**
     * Constructor
     *
     * @param context The current context.
     * @param objects The objects to represent in the ListView.
     */
    public PhotoAdapter(Context context, List<PhotoObject> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    private static class PhotoHolder {
        private ExpandableTextView txtCaption1Object;
        private ExpandableTextView txtNameObject;
        private ExpandableTextView likeCount;
        private ImageView userComments;
        private ExpandableTextView userComments1;
        private ExpandableTextView userComments2;
        private ExpandableTextView userCommentsAll;
        private RoundedImageView roundedImageViewUserAvatar;
        private ImageView imgPhotoObject;
        private VideoView mVideoView;
        private ProgressBar roundedImageViewUserProgress;
        private ProgressBar imgPhotoProgress;
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
        final PhotoObject photoObject = getItem(position);
        PhotoHolder photoHolder = new PhotoHolder();

        // If recycled view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.photo_adapter, parent, false);
            photoHolder.txtCaption1Object = (ExpandableTextView) convertView.findViewById(R.id.user_caption);
            photoHolder.txtNameObject = (ExpandableTextView) convertView.findViewById(R.id.user_name);
            photoHolder.likeCount = (ExpandableTextView) convertView.findViewById(R.id.user_total_likes);
            photoHolder.userComments = (ImageView) convertView.findViewById(R.id.user_comments);
            photoHolder.userComments1 = (ExpandableTextView) convertView.findViewById(R.id.user_comment1);
            photoHolder.userComments2 = (ExpandableTextView) convertView.findViewById(R.id.user_comment2);
            photoHolder.userCommentsAll = (ExpandableTextView) convertView.findViewById(R.id.user_comment_all);
            photoHolder.roundedImageViewUserAvatar = (RoundedImageView) convertView.findViewById(R.id.user_avatar);
            photoHolder.imgPhotoObject = (ImageView) convertView.findViewById(R.id.user_photo);
            photoHolder.mVideoView = (VideoView) convertView.findViewById(R.id.user_video);
            photoHolder.roundedImageViewUserProgress = (ProgressBar) convertView.findViewById(R.id.user_avatar_progressBar);
            photoHolder.imgPhotoProgress = (ProgressBar) convertView.findViewById(R.id.user_photo_progressBar);
            convertView.setTag(photoHolder);
        } else {
            photoHolder = (PhotoHolder) convertView.getTag();
        }

        /**
         * Set data to views
         */
        if (photoObject.getCaption() != null && !photoObject.getCaption().isEmpty()) {
            photoHolder.txtCaption1Object.setText(photoObject.getCaption());
            photoHolder.txtCaption1Object.setVisibility(View.VISIBLE);
        } else {
            photoHolder.txtCaption1Object.setVisibility(View.GONE);
        }
        if (photoObject.getComment1() != null && !photoObject.getComment1().isEmpty()) {
            photoHolder.userComments1.setText(photoObject.getComment1());
            photoHolder.userComments1.setVisibility(View.VISIBLE);
        } else {
            photoHolder.userComments1.setVisibility(View.GONE);
        }
        if (photoObject.getComment2() != null && !photoObject.getComment2().isEmpty()) {
            photoHolder.userComments2.setText(photoObject.getComment2());
            photoHolder.userComments2.setVisibility(View.VISIBLE);
        } else {
            photoHolder.userComments2.setVisibility(View.GONE);
        }
        if (photoObject.getCommentsCount() > 2) {
            photoHolder.userCommentsAll.setText("There are " + photoObject.getCommentsCount() + " comments");
            photoHolder.userCommentsAll.setVisibility(View.VISIBLE);
        } else {
            photoHolder.userCommentsAll.setVisibility(View.GONE);
        }
        photoHolder.txtNameObject.setText(photoObject.getName());
        photoHolder.likeCount.setText(String.valueOf(photoObject.getLikeCount()));
        if (photoHolder.roundedImageViewUserAvatar != null) {
            photoHolder.roundedImageViewUserProgress.setVisibility(View.VISIBLE);
            final PhotoHolder finalPhotoHolder = photoHolder;
            Picasso.with(getContext())
                    .load(photoObject.getAvatar())
                    .error(R.mipmap.ic_launcher)
                    .fit()
                    .into(photoHolder.roundedImageViewUserAvatar, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            finalPhotoHolder.roundedImageViewUserProgress.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            Log.e("Error", "Avatar Failed");
                        }
                    });
            photoHolder.roundedImageViewUserAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Add/Remove 1 like of current user
                }
            });
        }
        if (photoObject.getType() == null) {
            photoHolder.imgPhotoProgress.setVisibility(View.GONE);
            photoHolder.imgPhotoObject.setVisibility(View.GONE);
            photoHolder.mVideoView.setVisibility(View.GONE);
        } else {
            photoHolder.imgPhotoProgress.setVisibility(View.VISIBLE);
            photoHolder.imgPhotoObject.setVisibility(View.INVISIBLE);
            photoHolder.mVideoView.setVisibility(View.GONE);
            final PhotoHolder finalPhotoHolder1 = photoHolder;
            Picasso.with(getContext())
                    .load(photoObject.getImageUrl())
                    .error(R.mipmap.ic_launcher)
                    .fit()
                    .into(photoHolder.imgPhotoObject, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            finalPhotoHolder1.imgPhotoProgress.setVisibility(View.GONE);
                            finalPhotoHolder1.imgPhotoObject.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onError() {
                            Log.e("Error", "Image Failed");
                        }
                    });
            if (photoObject.getType().equalsIgnoreCase("video")) {
                photoHolder.imgPhotoProgress.setVisibility(View.VISIBLE);
                photoHolder.imgPhotoObject.setVisibility(View.VISIBLE);
                photoHolder.imgPhotoProgress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), VideoActivity.class);
                        intent.putExtra("URL", photoObject.getVideoUrl());
                        intent.putExtra("TYPE", "video");
                        intent.putExtra("HEIGHT", photoObject.getVideoHeight());
                        getContext().startActivity(intent);
                    }
                });
                photoHolder.imgPhotoObject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), VideoActivity.class);
                        intent.putExtra("URL", photoObject.getVideoUrl());
                        intent.putExtra("TYPE", "video");
                        intent.putExtra("HEIGHT", photoObject.getVideoHeight());
                        getContext().startActivity(intent);
                    }
                });
            }
        }
        photoHolder.userComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentsPage(photoObject);
            }
        });

        return convertView;
    }

    /**
     * Internal Event
     *
     * @param photoObject PhotoObject
     */
    private void commentsPage(PhotoObject photoObject) {
        Intent intent = new Intent(getContext(), CommentsActivity.class);
        intent.putExtra("ID", photoObject.getId());
        ArrayList<CommentObject> commentObjects = photoObject.getComments();
        if (commentObjects != null && commentObjects.size() > 0) {
            ArrayList<String> username = new ArrayList<>();
            ArrayList<String> avatar = new ArrayList<>();
            ArrayList<String> text = new ArrayList<>();

            username.add(photoObject.getName());
            avatar.add(photoObject.getAvatar());
            text.add(photoObject.getCaption());
            for (CommentObject commentObject : commentObjects) {
                username.add(commentObject.getUser());
                avatar.add(commentObject.getAvatar());
                text.add(commentObject.getText());
            }
            intent.putStringArrayListExtra("COMMENTS_USERNAME", username);
            intent.putStringArrayListExtra("COMMENTS_AVATAR", avatar);
            intent.putStringArrayListExtra("COMMENTS_TEXT", text);

            getContext().startActivity(intent);
        }
    }
}
