/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.instagram_photo_viewer.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.training.tiennguyen.instagram_photo_viewer.R;
import com.training.tiennguyen.instagram_photo_viewer.model.CommentObject;
import com.training.tiennguyen.instagram_photo_viewer.model.PhotoObject;
import com.training.tiennguyen.instagram_photo_viewer.ui.CommentsActivity;
import com.training.tiennguyen.instagram_photo_viewer.ui.PhotoActivity;
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

        // If recycled view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.photo_adapter, parent, false);
        }

        // Initial view
        ExpandableTextView txtCaption1Object = (ExpandableTextView) convertView.findViewById(R.id.user_caption);
        ExpandableTextView txtNameObject = (ExpandableTextView) convertView.findViewById(R.id.user_name);
        ExpandableTextView likeCount = (ExpandableTextView) convertView.findViewById(R.id.user_total_likes);
        ImageView userComments = (ImageView) convertView.findViewById(R.id.user_comments);
        ExpandableTextView userComments1 = (ExpandableTextView) convertView.findViewById(R.id.user_comment1);
        ExpandableTextView userComments2 = (ExpandableTextView) convertView.findViewById(R.id.user_comment2);
        ExpandableTextView userCommentsAll = (ExpandableTextView) convertView.findViewById(R.id.user_comment_all);
        RoundedImageView roundedImageViewUserAvatar = (RoundedImageView) convertView.findViewById(R.id.user_avatar);
        final ImageView imgPhotoObject = (ImageView) convertView.findViewById(R.id.user_photo);
        final VideoView mVideoView = (VideoView) convertView.findViewById(R.id.user_video);
        final ProgressBar roundedImageViewUserProgress = (ProgressBar) convertView.findViewById(R.id.user_avatar_progressBar);
        final ProgressBar imgPhotoProgress = (ProgressBar) convertView.findViewById(R.id.user_photo_progressBar);
        final ImageView userLikeDislike = (ImageView) convertView.findViewById(R.id.user_like_dislike);

        // Set data to view
        if (photoObject.getCaption() != null && !photoObject.getCaption().isEmpty()) {
            txtCaption1Object.setText(photoObject.getCaption());
            txtCaption1Object.setVisibility(View.VISIBLE);
        } else {
            txtCaption1Object.setVisibility(View.GONE);
        }
        if (photoObject.getComment1() != null && !photoObject.getComment1().isEmpty()) {
            userComments1.setText(photoObject.getComment1());
            userComments1.setVisibility(View.VISIBLE);
        } else {
            userComments1.setVisibility(View.GONE);
        }
        if (photoObject.getComment2() != null && !photoObject.getComment2().isEmpty()) {
            userComments2.setText(photoObject.getComment2());
            userComments2.setVisibility(View.VISIBLE);
        } else {
            userComments2.setVisibility(View.GONE);
        }
        if (photoObject.getCommentsCount() > 2) {
            userCommentsAll.setText("There are " + photoObject.getCommentsCount() + " comments");
            userCommentsAll.setVisibility(View.VISIBLE);

            // TODO: not support at the moment
            userCommentsAll.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    commentsPage(photoObject.getId(), photoObject.getComments());
                    return true;
                }
            });
        } else {
            userCommentsAll.setVisibility(View.GONE);
        }
        txtNameObject.setText(photoObject.getName());
        likeCount.setText(String.valueOf(photoObject.getLikeCount()));

        if (roundedImageViewUserAvatar != null) {
            roundedImageViewUserProgress.setVisibility(View.VISIBLE);
            Transformation transformation = new RoundedTransformationBuilder()
                    .borderColor(Color.BLACK)
                    .borderWidthDp(3)
                    .cornerRadiusDp(30)
                    .oval(false)
                    .build();
            Picasso.with(getContext())
                    .load(photoObject.getAvatar())
                    .error(R.mipmap.ic_launcher)
                    .fit()
                    .transform(transformation)
                    .into(roundedImageViewUserAvatar, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            roundedImageViewUserProgress.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            Log.e("Error", "Avatar Failed");
                        }
                    });
            roundedImageViewUserAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Add/Remove 1 like of current user
                }
            });
        }

        if (photoObject.getType() != null && photoObject.getType().equalsIgnoreCase("image")) {
            imgPhotoProgress.setVisibility(View.VISIBLE);
            imgPhotoObject.setVisibility(View.INVISIBLE);
            mVideoView.setVisibility(View.GONE);
            Picasso.with(getContext())
                    .load(photoObject.getImageUrl())
                    .error(R.mipmap.ic_launcher)
                    .fit()
                    .into(imgPhotoObject, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            imgPhotoProgress.setVisibility(View.GONE);
                            imgPhotoObject.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onError() {
                            Log.e("Error", "Image Failed");
                        }
                    });
            //imgPhotoObject.setMinimumHeight(photoObject.getImageHeight()); // TODO: height will be use in the detail page
            imgPhotoObject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Go to user's information page
                    if (photoObject.getLikeCount() == 0) {
                        userLikeDislike.setImageLevel(0);
                    } else {
                        userLikeDislike.setImageLevel(0);
                    }
                }
            });
        } else if (photoObject.getType() != null && photoObject.getType().equalsIgnoreCase("video")) {
            imgPhotoProgress.setVisibility(View.VISIBLE);
            imgPhotoObject.setVisibility(View.INVISIBLE);
            mVideoView.setVideoPath(photoObject.getVideoUrl());
            MediaController mediaController = new MediaController(getContext());
            mediaController.setAnchorView(mVideoView);
            mVideoView.setMediaController(mediaController);
            mVideoView.setVideoURI(Uri.parse(photoObject.getVideoUrl()));
            mVideoView.requestFocus();
            //mVideoView.setMinimumHeight(photoObject.getVideoHeight()); // TODO: height will be use in the detail page
            mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // TODO: At the moment still not run
                // Close the progress bar and play the video
                public void onPrepared(MediaPlayer mp) {
                    imgPhotoProgress.setVisibility(View.GONE);
                    mVideoView.setVisibility(View.VISIBLE);
                    mVideoView.start();
                }
            });
            imgPhotoProgress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Video temperately not support!!! =)", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), PhotoActivity.class);
                    intent.putExtra("URL", photoObject.getVideoUrl());
                    intent.putExtra("TYPE", "video");
                    getContext().startActivity(intent);
                }
            });
            mVideoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Video temperately not support!!! =)", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), PhotoActivity.class);
                    intent.putExtra("URL", photoObject.getVideoUrl());
                    intent.putExtra("TYPE", "video");
                    getContext().startActivity(intent);
                }
            });
        } else {
            imgPhotoProgress.setVisibility(View.GONE);
            imgPhotoObject.setVisibility(View.GONE);
            mVideoView.setVisibility(View.GONE);
        }

        userComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* TODO: Create an instance of the dialog fragment and show it */
                /*DialogFragment dialogFragment = CommentsDialogFragment.newInstance();
                FragmentManager manager = getContext().getFragmentManager();
                dialogFragment.show(manager, "CommentsDialogFragment");*/
                commentsPage(photoObject.getId(), photoObject.getComments());
            }
        });

        userLikeDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Add/Remove 1 like of current user
                if (photoObject.getLikeCount() == 0) {
                    userLikeDislike.setImageLevel(0);
                } else {
                    userLikeDislike.setImageLevel(0);
                }
            }
        });

        return convertView;
    }

    /**
     * Internal Event
     *
     * @param id id
     */
    private void commentsPage(String id, ArrayList<CommentObject> commentObjects) {
        Intent intent = new Intent(getContext(), CommentsActivity.class);
        intent.putExtra("ID", id);
        if (commentObjects != null && commentObjects.size() > 0) {
            ArrayList<String> username = new ArrayList<>();
            ArrayList<String> avatar = new ArrayList<>();
            ArrayList<String> text = new ArrayList<>();
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
