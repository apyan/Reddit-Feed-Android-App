package com.personaldev.redditfeed.redditfeed;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * The adapter for the tabs on the pager.
 */

public class AdapterTabPager extends FragmentStatePagerAdapter {

    // Variables of the adapter
    int tabAmount;

    public AdapterTabPager(FragmentManager fm, int tabNumber) {
        super(fm);
        this.tabAmount = tabNumber;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                // Returns the fragment of Reddit's New topics
                FragmentNewTopic fragmentNewTopic = new FragmentNewTopic();
                return fragmentNewTopic;
            case 1:
                // Returns the fragment of Reddit's Hot topics
                FragmentHotTopic fragmentHotTopic = new FragmentHotTopic();
                return fragmentHotTopic;
            case 2:
                // Returns the fragment of Reddit's Random topics
                FragmentRandomTopic fragmentRandomTopic = new FragmentRandomTopic();
                return fragmentRandomTopic;
            case 3:
                // Returns the fragment of Reddit's Rising topics
                FragmentRisingTopic fragmentRisingTopic = new FragmentRisingTopic();
                return fragmentRisingTopic;
            case 4:
                // Returns the fragment of Reddit's Top topics
                FragmentTopTopic fragmentTopTopic = new FragmentTopTopic();
                return fragmentTopTopic;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabAmount;
    }

}
