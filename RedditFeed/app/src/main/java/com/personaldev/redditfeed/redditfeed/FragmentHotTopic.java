package com.personaldev.redditfeed.redditfeed;

import android.content.Context;
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
 * Fragment displaying Hot Reddit topics.
 */

public class FragmentHotTopic extends Fragment implements View.OnClickListener {

    // Variables used for the fragment
    public AppConnect appConnect;
    public Context context;
    public static String jsonURL = "https://www.reddit.com/r/all/hot.json";
    public ArrayList<TopicObject> topicsFound;

    public RequestQueue requestQueue;
    public JsonObjectRequest jsonObjectRequest;

    public LinearLayout linear_00, linear_01;
    public ListView list_00;
    public Button button_00;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Set up the fragment
        View v = inflater.inflate(R.layout.fragment_topic_listing, container, false);

        // Set up needed classes
        context = getActivity();
        appConnect = new AppConnect(getActivity());
        requestQueue = Volley.newRequestQueue(context);
        topicsFound = new ArrayList<>();

        // Set up UI elements
        linear_00 = (LinearLayout) v.findViewById(R.id.loading_linear);
        linear_01 = (LinearLayout) v.findViewById(R.id.connect_linear);
        list_00 = (ListView) v.findViewById(R.id.listView_00);
        button_00 = (Button) v.findViewById(R.id.connect_button);

        // Button onClick Inputs
        button_00.setOnClickListener(this);

        // Reddit Seeking Operations Begin
        // Check to see if Internet connection is available
        if(!appConnect.connectionAvailable()) {

            // Appropriate the screen output (No Connection)
            linear_00.setVisibility(View.GONE);
            linear_01.setVisibility(View.VISIBLE);
            list_00.setVisibility(View.GONE);

        } else {

            // Appropriate the screen output (Searching)
            linear_00.setVisibility(View.VISIBLE);
            linear_01.setVisibility(View.GONE);
            list_00.setVisibility(View.GONE);

            // Initialize JsonObjectRequest for Volley JSON retrieval
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                    jsonURL, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            // Appropriate the screen output (Viewing Topics)
                            linear_00.setVisibility(View.GONE);
                            linear_01.setVisibility(View.GONE);
                            list_00.setVisibility(View.VISIBLE);

                            // Process the JSON file
                            try {

                                // Get the JSON attributes
                                JSONObject dataObject_00 = response.getJSONObject("data");
                                JSONArray childrenArray = dataObject_00.getJSONArray("children");

                                // Loop through the children array
                                for(int index = 0; index < childrenArray.length(); index++) {

                                    // Create a new Topic
                                    TopicObject newTopicObject = new TopicObject();

                                    // Get current child JSON object
                                    JSONObject topicEntry = childrenArray.getJSONObject(index);
                                    JSONObject dataObject_01 = topicEntry.getJSONObject("data");

                                    // Get the current child JSON attributes
                                    newTopicObject.topicDomain = dataObject_01.getString("domain");
                                    newTopicObject.topicSubReddit = dataObject_01.getString("subreddit");
                                    newTopicObject.topicFlairText = dataObject_01.getString("link_flair_text");
                                    newTopicObject.topicAuthor = dataObject_01.getString("author");
                                    newTopicObject.topicScore = dataObject_01.getInt("score");
                                    newTopicObject.topicPermalink = dataObject_01.getString("permalink");
                                    newTopicObject.topicCreated = dataObject_01.getInt("created");
                                    newTopicObject.topicURL = dataObject_01.getString("url");
                                    newTopicObject.topicTitle = dataObject_01.getString("title");
                                    newTopicObject.topicCreatedUTC = dataObject_01.getInt("created_utc");
                                    newTopicObject.topicSubRedditPrefixed = dataObject_01.getString("subreddit_name_prefixed");
                                    newTopicObject.topicCommentsNum = dataObject_01.getInt("num_comments");

                                    // Add the new topic into the Topic Array
                                    topicsFound.add(newTopicObject);
                                }

                            } catch(JSONException e) {
                                e.printStackTrace();
                            }

                            // Populate ListView
                            list_00.setAdapter(new AdapterTopics(getActivity(), topicsFound));
                        }
                    },
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error){
                            // Do something when error occurred

                            // Appropriate the screen output (No Connection)
                            linear_00.setVisibility(View.GONE);
                            linear_01.setVisibility(View.VISIBLE);
                            list_00.setVisibility(View.GONE);
                        }
                    }
            );

            // Add JsonObjectRequest to the RequestQueue
            requestQueue.add(jsonObjectRequest);
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
                    list_00.setVisibility(View.GONE);
                    Toast.makeText(context,getText(R.string.connect_000),Toast.LENGTH_SHORT).show();

                } else {

                    // Appropriate the screen output (Searching)
                    linear_00.setVisibility(View.VISIBLE);
                    linear_01.setVisibility(View.GONE);
                    list_00.setVisibility(View.GONE);

                    // Initialize JsonObjectRequest for Volley JSON retrieval
                    jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                            jsonURL, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {

                                    // Appropriate the screen output (Viewing Topics)
                                    linear_00.setVisibility(View.GONE);
                                    linear_01.setVisibility(View.GONE);
                                    list_00.setVisibility(View.VISIBLE);

                                    // Process the JSON file
                                    try {

                                        // Get the JSON attributes
                                        JSONObject dataObject_00 = response.getJSONObject("data");
                                        JSONArray childrenArray = dataObject_00.getJSONArray("children");

                                        // Loop through the children array
                                        for(int index = 0; index < childrenArray.length(); index++) {

                                            // Create a new Topic
                                            TopicObject newTopicObject = new TopicObject();

                                            // Get current child JSON object
                                            JSONObject topicEntry = childrenArray.getJSONObject(index);
                                            JSONObject dataObject_01 = topicEntry.getJSONObject("data");

                                            // Get the current child JSON attributes
                                            newTopicObject.topicDomain = dataObject_01.getString("domain");
                                            newTopicObject.topicSubReddit = dataObject_01.getString("subreddit");
                                            newTopicObject.topicFlairText = dataObject_01.getString("link_flair_text");
                                            newTopicObject.topicAuthor = dataObject_01.getString("author");
                                            newTopicObject.topicScore = dataObject_01.getInt("score");
                                            newTopicObject.topicPermalink = dataObject_01.getString("permalink");
                                            newTopicObject.topicCreated = dataObject_01.getInt("created");
                                            newTopicObject.topicURL = dataObject_01.getString("url");
                                            newTopicObject.topicTitle = dataObject_01.getString("title");
                                            newTopicObject.topicCreatedUTC = dataObject_01.getInt("created_utc");
                                            newTopicObject.topicSubRedditPrefixed = dataObject_01.getString("subreddit_name_prefixed");
                                            newTopicObject.topicCommentsNum = dataObject_01.getInt("num_comments");

                                            // Add the new topic into the Topic Array
                                            topicsFound.add(newTopicObject);
                                        }

                                    } catch(JSONException e) {
                                        e.printStackTrace();
                                    }

                                    // Populate ListView
                                    list_00.setAdapter(new AdapterTopics(getActivity(), topicsFound));
                                }
                            },
                            new Response.ErrorListener(){
                                @Override
                                public void onErrorResponse(VolleyError error){
                                    // Do something when error occurred

                                    // Appropriate the screen output (No Connection)
                                    linear_00.setVisibility(View.GONE);
                                    linear_01.setVisibility(View.VISIBLE);
                                    list_00.setVisibility(View.GONE);
                                }
                            }
                    );

                    // Add JsonObjectRequest to the RequestQueue
                    requestQueue.add(jsonObjectRequest);

                }

                break;
            default:
                break;
        }
    }

}
