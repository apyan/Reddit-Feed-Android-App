package com.personaldev.redditfeed.redditfeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TopicPageScreen extends AppCompatActivity {

    // Variables for the activity
    public String jsonURL = "urlLink";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_page_screen);

        // Retrieve the JSON url
        Bundle bundle = getIntent().getExtras();
        jsonURL = bundle.getString("urlLink");

    }
}
