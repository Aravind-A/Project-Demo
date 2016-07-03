package com.example.aravind.sonyespndemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity{
    private NavigationView mNavDrawerList;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavDrawerList = (NavigationView) findViewById(R.id.navList);
        setUpDrawer();
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        else
            Log.i("getSupportActionBar(): ","is null");
        mNavDrawerList.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home: switchFragment(0);break;
                    case R.id.football: switchFragment(1);break;
                    case R.id.cricket:  switchFragment(2);break;
                    case R.id.tennis:   switchFragment(3);break;
                    case R.id.exit:     mDrawerLayout.closeDrawer(mNavDrawerList);
                                        exitFunction();break;
                }
                return true;
            }
        });
        switchFragment(0);
    }

    private void switchFragment(int position){
        Fragment fragment = null;
        switch (position){
            case 0 :    mActivityTitle = "Live News";
                        fragment = new LiveFragment();
                        break;
            case 1 :    mActivityTitle = "Football";
                        fragment = new FootballFragment();
                        break;
            case 2 :    mActivityTitle = "Cricket";
                        fragment = new CricketFragment();
                        break;
            case 3 :    mActivityTitle = "Tennis";
                        fragment = new TennisFragment();
                        break;
            default:    break;
        }
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragmentHolder, fragment, mActivityTitle).commit();
        getSupportActionBar().setTitle(mActivityTitle);
        mDrawerLayout.closeDrawer(mNavDrawerList);
    }

    private void setUpDrawer(){
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.drawer_open,R.string.drawer_close){
            public void onDrawerOpened(View drawerView){
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Make a selection");
                invalidateOptionsMenu();
            }
            public void onDrawerClosed(View drawerView){
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu();
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration configuration){
        super.onConfigurationChanged(configuration);
        mDrawerToggle.onConfigurationChanged(configuration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(mDrawerToggle.onOptionsItemSelected(item))
            return true;
            startActivity(new Intent(this,SettingsActivity.class));
        if (id == R.id.action_settings) { return true; }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed(){
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START))
            mDrawerLayout.closeDrawer(mNavDrawerList);
        else {
            Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragmentHolder);
            if (f instanceof LiveFragment)
                exitFunction();
            else
                switchFragment(0);
        }
    }
    public void exitFunction(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quit Sony ESPN Demo").setMessage("Are you sure you want to quit ?")
                .setPositiveButton("QUIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builder.create().show();
    }
}
