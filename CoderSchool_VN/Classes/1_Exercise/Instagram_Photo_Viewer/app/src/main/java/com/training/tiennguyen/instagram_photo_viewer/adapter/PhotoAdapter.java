/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.instagram_photo_viewer.adapter;

import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.training.tiennguyen.instagram_photo_viewer.R;
import com.training.tiennguyen.instagram_photo_viewer.dialog.CommentsDialogFragment;
import com.training.tiennguyen.instagram_photo_viewer.model.PhotoObject;

import java.util.List;

/**
 * PhotoAdapter
 *
 * @author Created by TienVNguyen on 10/03/2016.
 */
public class PhotoAdapter extends ArrayAdapter<PhotoObject> {

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount() {
        return super.getCount();
    }

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
        PhotoObject photoObject = getItem(position);

        // If recycled view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.photo_adapter, parent, false);
        }

        // Initial view
        TextView txtCaption1Object = (TextView) convertView.findViewById(R.id.user_caption);
        TextView txtNameObject = (TextView) convertView.findViewById(R.id.user_name);
        TextView likeCount = (TextView) convertView.findViewById(R.id.user_total_likes);
        RoundedImageView roundedImageViewUserAvatar = (RoundedImageView) convertView.findViewById(R.id.user_avatar);
        ImageView imgPhotoObject = (ImageView) convertView.findViewById(R.id.user_photo);
        final ProgressBar roundedImageViewUserProgress = (ProgressBar) convertView.findViewById(R.id.user_avatar_progressBar);
        final ProgressBar imgPhotoProgress = (ProgressBar) convertView.findViewById(R.id.user_photo_progressBar);

        // Set data to view
        txtCaption1Object.setText(photoObject.getCaption());
        txtNameObject.setText(photoObject.getName());
        likeCount.setText(String.valueOf(photoObject.getLikeCount()));

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

        imgPhotoProgress.setVisibility(View.VISIBLE);
        Picasso.with(getContext())
                .load(photoObject.getImageUrl())
                .error(R.mipmap.ic_launcher)
                .fit()
                .into(imgPhotoObject, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        imgPhotoProgress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        Log.e("Error", "Image Failed");
                    }
                });
        imgPhotoObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Go to user's information page
            }
        });

        ImageView userComments = (ImageView) convertView.findViewById(R.id.user_comments);
        userComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* TODO: Create an instance of the dialog fragment and show it */
                DialogFragment dialogFragment = CommentsDialogFragment.newInstance();
                /*FragmentManager manager = getContext().getFragmentManager();
                dialogFragment.show(manager, "CommentsDialogFragment");*/
            }
        });

        ImageView userLike = (ImageView) convertView.findViewById(R.id.user_like);
        userLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Add/Remove 1 like of current user

            }
        });

        return convertView;
    }
}
