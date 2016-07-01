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
public class LiveFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.live_frag,container,false);
        WebView live = (WebView) v.findViewById(R.id.live);
        live.loadUrl("http://www.espn.in/");
        return v;
    }
}
