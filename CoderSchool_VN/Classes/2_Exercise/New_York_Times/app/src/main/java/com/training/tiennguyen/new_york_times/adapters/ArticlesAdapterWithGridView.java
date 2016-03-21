/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.new_york_times.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.training.tiennguyen.new_york_times.R;
import com.training.tiennguyen.new_york_times.models.ArticlesObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**ArticlesAdapterWithGridView
 * @author Created by TienVNguyen on 21/03/2016.
 */
public class ArticlesAdapterWithGridView extends ArrayAdapter<ArticlesObject> {
    private ArrayList<ArticlesObject> responses;
    private Context context;

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public ArticlesAdapterWithGridView(Context context, int resource, List<ArticlesObject> objects) {
        super(context, resource, objects);
    }

    /**
     * Provide a direct reference to each of the views within a data item
     */
    public static class ArticlesHolder {
        @Bind(R.id.imgImage)
        protected ImageView imgImage;
        @Bind(R.id.txtText)
        protected TextView txtName;
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
        final ArticlesObject articlesObject = getItem(position);
        ArticlesHolder viewHolder;
        if (convertView != null) {
            viewHolder = new ArticlesHolder();
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.holder_articles_list, parent, false);
            viewHolder.imgImage = (ImageView)convertView.findViewById(R.id.imgArticle);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtText);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ArticlesHolder) convertView.getTag();
        }

        viewHolder.txtName.setText(articlesObject.getHeadline().get("main").toString());
        Glide.with(getContext()).load(articlesObject.getMultimedia().get(0).getUrl()).into(viewHolder.imgImage);

        return convertView;
    }
}
