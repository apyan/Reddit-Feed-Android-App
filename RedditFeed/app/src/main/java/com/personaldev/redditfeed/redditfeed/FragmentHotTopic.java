package com.personaldev.redditfeed.redditfeed;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.personaldev.redditfeed.redditfeed.AppFunctions.AppConnect;

/**
 * Fragment displaying Hot Reddit topics.
 */

public class FragmentHotTopic extends Fragment implements View.OnClickListener {

    // Variables used for the fragment
    public AppConnect appConnect;
    public Button button_00;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Set up the fragment
        View v = inflater.inflate(R.layout.fragment_topic_listing, container, false);

        // Set up needed classes
        appConnect = new AppConnect(getActivity());

        // Set up UI elements
        button_00 = (Button) v.findViewById(R.id.connect_button);
        // Button onClick Inputs
        button_00.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Search Button
            case R.id.connect_button:

                break;
            default:
                break;
        }
    }

}
