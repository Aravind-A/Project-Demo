package com.example.aravind.sonyespndemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by aravind on 29/6/16.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int numTabs;
    public PagerAdapter(FragmentManager fm, int numTabs) {
        super(fm);
        this.numTabs = numTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :    return new BplFragment();
            case 1 :    return new EuroFragment();
            default:    return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
