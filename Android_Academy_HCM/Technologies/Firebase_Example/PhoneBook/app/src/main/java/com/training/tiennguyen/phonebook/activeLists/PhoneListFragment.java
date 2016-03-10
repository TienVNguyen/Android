/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.phonebook.activeLists;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.training.tiennguyen.phonebook.R;
import com.training.tiennguyen.phonebook.model.PhoneBook;
import com.training.tiennguyen.phonebook.utils.Constants;

/**
 * A simple {@link Fragment} subclass that shows a list of all phone lists a user can see.
 *
 * @author Created by TienVNguyen on 09/03/2016.
 */
public class PhoneListFragment extends Fragment {
    private ListView mListView;
    private PhoneListAdapter mPhoneListAdapter;

    public PhoneListFragment() {
        /* Required empty public constructor */
    }

    public static PhoneListFragment newInstance() {
        /* Arguments*/
        Bundle args = new Bundle();

        /* Fragment */
        PhoneListFragment phoneListFragment = new PhoneListFragment();
        phoneListFragment.setArguments(args);
        return phoneListFragment;
    }

    /**
     * Link layout elements from XML
     */
    private void initializeScreen(View rootView) {
        mListView = (ListView) rootView.findViewById(R.id.phone_list);
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
        View rootView = inflater.inflate(R.layout.fragment_phone_list, container, false);
        initializeScreen(rootView);

        /**
         * Create Firebase references
         * https://phone-book-db.firebaseio.com/phoneBooks
         */
        Firebase refListName = new Firebase(Constants.FIREBASE_URL_ACTIVE_LISTS);

        /**
         * Create the adapter, giving it the activity, model class, layout for each row in
         * the list and finally, a reference to the Firebase location with the list data
         */
        mPhoneListAdapter = new PhoneListAdapter(getActivity(), PhoneBook.class,
                R.layout.fragment_phone_list_adapter, refListName);

        /**
         * Set the adapter to the mListView
         */
        mListView.setAdapter(mPhoneListAdapter);

        /**
         * Set interactive bits, such as click events and adapters
         */
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhoneBook selectedList = mPhoneListAdapter.getItem(position);
                if (selectedList != null) {
                    /*Intent intent = new Intent(getActivity(), ActiveListDetailsActivity.class);
                    String listId = mPhoneListAdapter.getRef(position).getKey();
                    intent.putExtra(Constants.KEY_LIST_ID, listId);
                    startActivity(intent);*/
                }
            }
        });

        return rootView;
    }

    /**
     * Called when the fragment is no longer in use.  This is called
     * after {@link #onStop()} and before {@link #onDetach()}.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mPhoneListAdapter.cleanup();
    }
}
