/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.phonebook.activeLists;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.training.tiennguyen.phonebook.R;
import com.training.tiennguyen.phonebook.model.AuthorDetails;
import com.training.tiennguyen.phonebook.utils.Constants;

/**
 * @author Created by TienVNguyen on 09/03/2016.
 */
public class AuthorFragment extends Fragment {
    private TextView mTextViewAuthorName;
    private TextView mTextViewAuthorPhone;
    private TextView mTextViewAuthorEmail;
    private TextView mTextViewAuthorGit;
    private TextView mTextViewAuthorGitExercise;
    private ImageView mImageViewAuthorAvatar;
    private static final String LOG_TAG = AuthorFragment.class.getSimpleName();

    public AuthorFragment() {
        /* Required empty public constructor */
    }

    public static AuthorFragment newInstance() {
        /* Arguments*/
        Bundle args = new Bundle();

        /* Fragment */
        AuthorFragment authorFragment = new AuthorFragment();
        authorFragment.setArguments(args);
        return authorFragment;
    }

    /**
     * Link layout elements from XML
     */
    private void initializeScreen(View rootView) {
        mTextViewAuthorName = (TextView) rootView.findViewById(R.id.textViewAuthorName);
        mTextViewAuthorPhone = (TextView) rootView.findViewById(R.id.textViewAuthorPhone);
        mTextViewAuthorEmail = (TextView) rootView.findViewById(R.id.textViewAuthorEmail);
        mTextViewAuthorGit = (TextView) rootView.findViewById(R.id.textViewAuthorGit);
        mTextViewAuthorGitExercise = (TextView) rootView.findViewById(R.id.textViewAuthorGitExercise);
        mImageViewAuthorAvatar = (ImageView) rootView.findViewById(R.id.imageViewAuthorAvatar);
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     * <p/>
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         * Initialize UI elements
         */
        View rootView = inflater.inflate(R.layout.fragment_author_details, container, false);
        initializeScreen(rootView);

        /**
         * Create Firebase references
         * https://phone-book-db.firebaseio.com/authorDetails
         * DataSnapshot { key = authorDetails, value = {urlGitExercise=https://github.com/TienVNguyen/Android/tree/master/Android_Academy_HCM/Technologies/Firebase_Example/PhoneBook, urlGit=https://github.com/TienVNguyen, phone=0908881066, email=tien.workinfo@gmail.com, urlAvatar=https://scontent-hkg3-1.xx.fbcdn.net/hphotos-xfl1/v/t1.0-9/12705618_512169552299044_5796142661014467161_n.jpg?oh=0bfdb32ee4389674fb6cbd8f58679a3c&oe=57577136, name=Tien Nguyen} }
         */
        Firebase refAuthor = new Firebase(Constants.FIREBASE_URL_AUTHOR_DETAILS);
        refAuthor.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                AuthorDetails authorDetails = dataSnapshot.getValue(AuthorDetails.class);

                mTextViewAuthorName.setText(authorDetails.getName());
                mTextViewAuthorPhone.setText(authorDetails.getPhone());
                mTextViewAuthorEmail.setText(authorDetails.getEmail());
                mTextViewAuthorGit.setText(authorDetails.getUrlGit());
                mTextViewAuthorGitExercise.setText(authorDetails.getUrlGitExercise());

                Picasso.with(getContext()).load(authorDetails.getUrlAvatar()).into(mImageViewAuthorAvatar);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e(LOG_TAG, "ERROR:" + firebaseError.getMessage());
            }
        });

        return rootView;
    }
}
