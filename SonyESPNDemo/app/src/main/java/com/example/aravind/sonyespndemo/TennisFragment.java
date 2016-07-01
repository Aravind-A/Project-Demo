package com.example.aravind.sonyespndemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by aravind on 29/6/16.
 */
public class TennisFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.tennis_frag,container,false);
        WebView tennis = (WebView) v.findViewById(R.id.tennis);
        tennis.loadUrl("http://www.espn.in/tennis/");
        return v;
    }
}
