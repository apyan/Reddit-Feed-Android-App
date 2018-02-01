package com.personaldev.redditfeed.redditfeed;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.personaldev.redditfeed.redditfeed.AppObjects.TopicObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Adapter for the list view searching
 */

public class AdapterTopics extends BaseAdapter {

    // Variables for the adapter
    public static LayoutInflater inflater = null;
    public ArrayList<TopicObject> foundedTopics;
    Context eContext;

    // Constructor
    public AdapterTopics(Context context, ArrayList<TopicObject> results){
        eContext = context;
        foundedTopics = results;
        inflater = (LayoutInflater) eContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return foundedTopics.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    // List Row Attributes
    public class RowAttributes {
        TextView text_00, text_01, text_02, text_03, text_04, text_05, text_06;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        RowAttributes rowAttributes= new RowAttributes();
        View rowView;

        // Set up the UI elements
        rowView = inflater.inflate(R.layout.listview_layout_000, null);
        rowAttributes.text_00 = (TextView) rowView.findViewById(R.id.text_000);
        rowAttributes.text_01 = (TextView) rowView.findViewById(R.id.text_001);
        rowAttributes.text_02 = (TextView) rowView.findViewById(R.id.text_002);
        rowAttributes.text_03 = (TextView) rowView.findViewById(R.id.text_003);
        rowAttributes.text_04 = (TextView) rowView.findViewById(R.id.text_004);
        rowAttributes.text_05 = (TextView) rowView.findViewById(R.id.text_005);
        rowAttributes.text_06 = (TextView) rowView.findViewById(R.id.text_006);

        // Set up the display information
        rowAttributes.text_00.setText(foundedTopics.get(position).topicScore + "");

        // Check to see if positive or negative score of the topic
        if(foundedTopics.get(position).topicScore > 0) {
            // Color green if positive
            rowAttributes.text_00.setTextColor(Integer.parseInt("358245", 16) + 0xFF000000);
        }
        if(foundedTopics.get(position).topicScore < 0) {
            // Color red if negative
            rowAttributes.text_00.setTextColor(Integer.parseInt("823535", 16) + 0xFF000000);
        }

        // Fixes the title
        String placer_holder = foundedTopics.get(position).topicTitle;
        rowAttributes.text_01.setText(placer_holder);

        // Fixes the author
        placer_holder = rowAttributes.text_02.getText().toString();
        placer_holder = placer_holder.replace("(author)", foundedTopics.get(position).topicAuthor);
        rowAttributes.text_02.setText(placer_holder);

        // Fixes the subreddit
        placer_holder = rowAttributes.text_03.getText().toString();
        placer_holder = placer_holder.replace("(subreddit)", foundedTopics.get(position).topicSubRedditPrefixed);
        rowAttributes.text_03.setText(placer_holder);

        // Fixes the number of comments
        placer_holder = rowAttributes.text_04.getText().toString();
        placer_holder = placer_holder.replace("(num)", "" + foundedTopics.get(position).topicCommentsNum);
        rowAttributes.text_04.setText(placer_holder);

        // Fixes the domain
        placer_holder = rowAttributes.text_05.getText().toString();
        placer_holder = placer_holder.replace("domain", foundedTopics.get(position).topicDomain);
        rowAttributes.text_05.setText(placer_holder);

        // Fixes the created at UTC
        placer_holder = rowAttributes.text_06.getText().toString();
        Date date = new Date(foundedTopics.get(position).topicCreatedUTC);
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy - HH:mm:ss");
        placer_holder = placer_holder.replace("(date)", format.format(date)) + " UTC";
        rowAttributes.text_06.setText(placer_holder);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                // Head to the topic page screen
                /*Intent eIntent = new Intent(eContext, TopicPageScreen.class);
                eIntent.putExtra("urlLink",
                        "https://www.reddit.com" + foundedTopics.get(position).topicPermalink + ".json");
                v.getContext().startActivity(eIntent);*/

                // Open URL via Internet (on Hold)
                String url = "https://www.reddit.com" + foundedTopics.get(position).topicPermalink;
                Intent eIntent = new Intent(Intent.ACTION_VIEW);
                eIntent.setData(Uri.parse(url));
                v.getContext().startActivity(eIntent);
            }
        });
        return rowView;
    }

}