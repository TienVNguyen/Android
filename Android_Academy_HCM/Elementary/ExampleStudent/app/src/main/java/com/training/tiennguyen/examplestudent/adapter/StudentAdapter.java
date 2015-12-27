/*
 * Copyright (c) 2015. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.examplestudent.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.training.tiennguyen.examplestudent.R;
import com.training.tiennguyen.examplestudent.model.Student;
import com.training.tiennguyen.examplestudent.model.StudentHolder;

import java.util.List;

/**
 * StudentAdapter
 *
 * @author TienNguyen
 */
public class StudentAdapter extends BaseAdapter {
    private Activity activity;
    private List<Student> studentList;
    private LayoutInflater layoutInflater;

    public StudentAdapter(Activity activity, List<Student> studentList) {
        this.activity = activity;
        this.studentList = studentList;
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return studentList.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return studentList.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Create temperate memory
        StudentHolder studentHolder;
        if (convertView == null) {
            // Create new Holder
            studentHolder = new StudentHolder();

            // Init layout
            convertView = layoutInflater.inflate(R.layout.adapter_list, parent, false);

            // Set structure
            studentHolder.setIvAvatar((ImageView) convertView.findViewById(R.id.imgStudent));
            studentHolder.setTxtName((TextView) convertView.findViewById(R.id.txtName));
            studentHolder.setTxtEmail((TextView) convertView.findViewById(R.id.txtEmail));
        } else {
            // set data back
            studentHolder = (StudentHolder) convertView.getTag();
        }

        // Set values
        studentHolder.getTxtName().setText(studentList.get(position).getName());
        studentHolder.getTxtEmail().setText(studentList.get(position).getEmail());
        Picasso.with(this.activity).load(studentList.get(position).getAvatar()).into(studentHolder.getIvAvatar());

        // Return view
        return convertView;
    }
}
