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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.training.tiennguyen.instagram_photo_viewer.R;
import com.training.tiennguyen.instagram_photo_viewer.model.PhotoObject;

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
        PhotoObject photoObject = getItem(position);

        // If recycled view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.photo_adapter, parent, false);
        }
        TextView txtCaptionObject = (TextView) convertView.findViewById(R.id.txtCaption);
        TextView txtNameObject = (TextView) convertView.findViewById(R.id.user_name);
        ImageView imgPhotoObject = (ImageView) convertView.findViewById(R.id.user_photo);

        txtCaptionObject.setText(photoObject.getCaption());
        txtNameObject.setText(photoObject.getName());

        imgPhotoObject.setImageResource(0);
        Picasso.with(getContext()).load(
                photoObject.getImageUrl()).into(imgPhotoObject);

        return convertView;
    }
}
