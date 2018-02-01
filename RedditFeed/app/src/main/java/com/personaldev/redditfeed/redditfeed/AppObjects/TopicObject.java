package com.personaldev.redditfeed.redditfeed.AppObjects;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Object to hold the information of the topic results
 * of the Reddit category.
 */

public class TopicObject implements Parcelable {

    // Variables of the object
    public String topicDomain;
    public String topicFlairText;
    public int topicScore;
    public String topicSubReddit;
    public String topicAuthor;
    public String topicPermalink;
    public int topicCreated;
    public String topicURL;
    public String topicTitle;
    public int topicCreatedUTC;
    public String topicSubRedditPrefixed;
    public int topicCommentsNum;

    // Constructor
    public TopicObject() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Storing the data to Parcel object
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(topicDomain);
        dest.writeString(topicFlairText);
        dest.writeInt(topicScore);
        dest.writeString(topicSubReddit);
        dest.writeString(topicAuthor);
        dest.writeString(topicPermalink);
        dest.writeInt(topicCreated);
        dest.writeString(topicURL);
        dest.writeString(topicTitle);
        dest.writeInt(topicCreatedUTC);
        dest.writeString(topicSubRedditPrefixed);
        dest.writeInt(topicCommentsNum);
    }

    // For Creator
    private TopicObject(Parcel in){
        this.topicDomain = in.readString();
        this.topicFlairText = in.readString();
        this.topicScore = in.readInt();
        this.topicSubReddit = in.readString();
        this.topicAuthor = in.readString();
        this.topicPermalink = in.readString();
        this.topicCreated = in.readInt();
        this.topicURL = in.readString();
        this.topicTitle = in.readString();
        this.topicCreatedUTC = in.readInt();
        this.topicSubRedditPrefixed = in.readString();
        this.topicCommentsNum = in.readInt();
    }

    public static final Parcelable.Creator<TopicObject> CREATOR = new Parcelable.Creator<TopicObject>() {
        @Override
        public TopicObject createFromParcel(Parcel source) {
            return new TopicObject(source);
        }

        @Override
        public TopicObject[] newArray(int size) {
            return new TopicObject[size];
        }
    };

}