package com.training.tiennguyen.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.training.tiennguyen.demoday2.R;
import com.training.tiennguyen.helper.ImageHelper;
import com.training.tiennguyen.model.Student;
import com.training.tiennguyen.model.StudentHolder;

import java.util.List;

/**
 * Created by Win 8.1 VS8 X64 on 20/12/2015.
 */
public class StudentAdapter extends BaseAdapter {
    private Activity mActivity;
    private List<Student> mStudentList;
    private LayoutInflater inflater;

    public StudentAdapter(Activity activity, List<Student> studentList) {
        this.mActivity = activity;
        this.mStudentList = studentList;
        this.inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mStudentList.size();
    }

    @Override
    public Object getItem(int position) {
        return mStudentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Init content
        final StudentHolder studentHolder;
        // Create template memory
        if (convertView == null) {
            studentHolder = new StudentHolder();
            // Init layout
            convertView = inflater.inflate(R.layout.adapter_student, parent, false);
            // Set structure
            studentHolder.setIvAvatar((ImageView) convertView.findViewById(R.id.studentAvatar));
            studentHolder.setTvStudent((TextView) convertView.findViewById(R.id.studentName));
            convertView.setTag(studentHolder);
        } else {
            studentHolder = (StudentHolder) convertView.getTag();
        }

        // Set values
        studentHolder.getTvStudent().setText(mStudentList.get(position).getName());
        new AsyncTask<Void, Void, Void>() {
            Bitmap bm;

            @Override
            protected Void doInBackground(Void... params) {
                /*if(bm co trong cache) {
                    bm = getCached();
                } else {
                    bm = ImageHelper.getBitmapFromURL(mStudentList.get(position).getAvatar());
                }*/
                bm = ImageHelper.getBitmapFromURL(mStudentList.get(position).getAvatar());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if(bm != null) {
                    studentHolder.getIvAvatar().setImageBitmap(bm);
                } else {
                    studentHolder.getIvAvatar().setImageResource(R.drawable.androidimage);
                }
            }
        }.execute();

        return convertView;
    }
}
