package com.personaldev.redditfeed.redditfeed;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.personaldev.redditfeed.redditfeed.AppFunctions.AppConnect;
import com.personaldev.redditfeed.redditfeed.AppObjects.TopicObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Fragment displaying Random Reddit topics.
 */

public class FragmentRandomTopic extends Fragment implements View.OnClickListener {

    // Variables used for the fragment
    public AppConnect appConnect;
    public Context context;
    public static String jsonURL = "https://www.reddit.com/r/all/random.json";

    public LinearLayout linear_00, linear_01;
    public Button button_00, button_01;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Set up the fragment
        View v = inflater.inflate(R.layout.fragment_random_topic, container, false);

        // Set up needed classes
        context = getActivity();
        appConnect = new AppConnect(getActivity());

        // Set up UI elements
        linear_00 = (LinearLayout) v.findViewById(R.id.random_linear);
        linear_01 = (LinearLayout) v.findViewById(R.id.connect_linear);
        button_00 = (Button) v.findViewById(R.id.connect_button);
        button_01 = (Button) v.findViewById(R.id.random_button);

        // Button onClick Inputs
        button_00.setOnClickListener(this);
        button_01.setOnClickListener(this);

        // Reddit Seeking Operations Begin
        // Check to see if Internet connection is available
        if(!appConnect.connectionAvailable()) {

            // Appropriate the screen output (No Connection)
            linear_00.setVisibility(View.GONE);
            linear_01.setVisibility(View.VISIBLE);

        } else {

            // Appropriate the screen output (To Randomize)
            linear_00.setVisibility(View.VISIBLE);
            linear_01.setVisibility(View.GONE);
        }

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Connect Button
            case R.id.connect_button:

                // Check to see if Internet connection is available (No Connection)
                if(!appConnect.connectionAvailable()) {

                    // Appropriate the screen output
                    linear_00.setVisibility(View.GONE);
                    linear_01.setVisibility(View.VISIBLE);
                    Toast.makeText(context,getText(R.string.connect_000),Toast.LENGTH_SHORT).show();

                } else {

                    // Appropriate the screen output (To Randomize)
                    linear_00.setVisibility(View.VISIBLE);
                    linear_01.setVisibility(View.GONE);
                }

                break;

            // Randomize Button
            case R.id.random_button:

                // Head to the topic page screen
                Intent eIntent = new Intent(context, TopicPageScreen.class);
                eIntent.putExtra("urlLink", jsonURL);
                v.getContext().startActivity(eIntent);
                break;
            default:
                break;
        }
    }

}
