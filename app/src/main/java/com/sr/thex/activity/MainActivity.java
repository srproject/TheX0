package com.sr.thex.activity;

import android.Manifest;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.util.Log;

import com.sr.thex.R;
import com.sr.thex.fragment.TabFragment;

import java.util.Map;

import static android.R.attr.value;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

private static final int  REQUEST_ACCESS_FINE_LOCATION = 111;

        private Boolean isFabOpen = false;
        private FloatingActionButton fab,fab1,fab2,fab3,fab4;
        private Animation fab_open,fab_close,rotate_forward,rotate_backward;
        FragmentManager mFragmentManager;
        FragmentTransaction mFragmentTransaction;
        SearchView mSearchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


 



        //fab

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        fab3 = (FloatingActionButton)findViewById(R.id.fab3);
        fab4 = (FloatingActionButton)findViewById(R.id.fab4);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);
        fab.setOnClickListener(  this);
        fab1.setOnClickListener(  this);
        fab2.setOnClickListener(  this);
        fab3.setOnClickListener(  this);
        fab3.setOnClickListener(  this);



//pri

        permi();


        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */


//setup for tab
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();

//setup for nav

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

//setup fab animation

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.fab:

                animateFAB();
                break;
            case R.id.fab1:

                Log.d("Raj", "Fab 1");
                break;
            case R.id.fab2:

                Log.d("Raj", "Fab 2");
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//setup fab search

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        //final Toast toast = new Toast(mApp);

        if (mSearchView != null ) {
            mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            mSearchView.setIconifiedByDefault(false);

            SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
                public boolean onQueryTextChange(String newText) {
                    //mSearchString = newText;
                    //doFilterAsync(mSearchString);
                    Toast.makeText(getApplicationContext(), "Test1", Toast.LENGTH_LONG).show();
                    return true;
                }

                public boolean onQueryTextSubmit(String query) {
                    // mSearchString = query;
                    //doFilterAsync(mSearchString);
                    Toast.makeText(getApplicationContext(), "Test2", Toast.LENGTH_LONG).show();

                    return true;
                }
            };

            mSearchView.setOnQueryTextListener(queryTextListener);
        }
        return true;





    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
          //  return true;
        //}

        return super.onOptionsItemSelected(item);
    }


//setup for

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_item_Timeline) {
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            TabLayout.Tab tab = tabLayout.getTabAt(0);
            tab.select();
            // Handle the camera action
        } else if (id == R.id.nav_item_Notification) {
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            TabLayout.Tab tab = tabLayout.getTabAt(1);
            tab.select();

        } else if (id == R.id.nav_item_Profile) {
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            TabLayout.Tab tab = tabLayout.getTabAt(2);
            tab.select();

        } else if (id == R.id.nav_item_Contacts) {

        } else if (id == R.id.nav_item_setting) {
            Intent intent = new Intent(MainActivity.this, AddEventActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_item_help) {
            Intent intent = new Intent(MainActivity.this, MapActivity.class);
            startActivity(intent);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;



    }
//method for peremission
    public void permi(){



        boolean hasPermissionLocation = (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermissionLocation) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_ACCESS_FINE_LOCATION);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 3);
            requestPermissions(new String[]{Manifest.permission.LOCATION_HARDWARE}, 1);
            requestPermissions(new String[]{Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS}, 1);
            requestPermissions(new String[]{Manifest.permission.CONTROL_LOCATION_UPDATES}, 1);
            requestPermissions(new String[]{Manifest.permission.INSTALL_LOCATION_PROVIDER}, 1);


        }


        String[] mPermission = {Manifest.permission.READ_CONTACTS, Manifest.permission.READ_SMS,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION);

        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.


            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {

            case REQUEST_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(MainActivity.this, "Thank You Sir", Toast.LENGTH_SHORT).show();

                    //reload my activity with permission granted
                    finish();
                    startActivity(getIntent());

                } else
                {
                    Toast.makeText(MainActivity.this, "The app was not work well. Please get this permission", Toast.LENGTH_LONG).show();
                }
            }

        }

    }

    public void animateFAB(){

        if(isFabOpen){

            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.startAnimation(fab_close);
            fab4.startAnimation(fab_close);
            fab3.setClickable(false);
            fab4.setClickable(false);
            isFabOpen = false;
            Log.d("Raj", "close");

            //for undim screen

            final View shadowView =(View)findViewById(R.id.shadowView);
            shadowView.setVisibility(View.GONE);


        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.startAnimation(fab_open);
            fab4.startAnimation(fab_open);
            fab3.setClickable(true);
            fab4.setClickable(true);
            isFabOpen = true;
            Log.d("Raj","open");

            //for dim screen

            final View shadowView =(View)findViewById(R.id.shadowView);
             shadowView.getBackground().setAlpha(1);
            shadowView.setVisibility(View.VISIBLE);


        }
    }



}
