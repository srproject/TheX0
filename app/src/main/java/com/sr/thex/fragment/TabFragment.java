package com.sr.thex.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.sr.thex.R;
import com.sr.thex.activity.MainActivity;

/**
 * Created by sr on 2/22/17.
 */

public class TabFragment extends Fragment {





    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 5;
    LayoutInflater li;
    FloatingActionButton flphom;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        final View x = inflater.inflate(R.layout.tab_layout, null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);
        Context c;


        /**
         *Set an Apater for the View Pager
         */
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {

                    ((MainActivity) getActivity()).hideFloatingActionButton();



                }
                if (position == 1) {

                    tabLayout.setVisibility(View.VISIBLE); //VIEW.VISIBLE etc.
                    //  getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                    ((AppCompatActivity) getActivity()).getSupportActionBar().show();
                    ((MainActivity) getActivity()).showFloatingActionButton();


                }
                if (position == 2) {
                    // tabLayout.setVisibility(View.VISIBLE); //VIEW.VISIBLE etc.
                    //getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                    // ((AppCompatActivity) getActivity()).getSupportActionBar().show();
                    ((MainActivity) getActivity()).showFloatingActionButton();


                }
                if (position == 3) {
                    ((MainActivity) getActivity()).showFloatingActionButton();


                }
                if (position == 4) {
                    ((MainActivity) getActivity()).hideFloatingActionButton();
                    //  tabLayout.setVisibility(View.GONE); //VIEW.VISIBLE etc.
                    //   getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    //((AppCompatActivity) getActivity()).getSupportActionBar().hide();


                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return x;



       // View rootView = inflater.inflate(R.layout.home_layout, container, false);


      //  rootView.findViewById(R.id.icon).setBackgroundResource(R.mipmap.ic_home_white_48dp);
        //tabLayout.getTabAt(0).setCustomView(view0);

    }



    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0:
                    return new Home_Map();
                case 1:
                    return new HomeFragment();
                case 2:
                    return new NotiFragment();
                case 3:
                    return new NewProFragment();
                case 4:
                    return new CameraFragment();


            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
              case 0 :

                  return "MAP";
                case 1 :

                    return "timeline";

                case 2 :

                    return "Notices";

                case 3:

                    return "profile";
                case 4:

                    return "Camera";





            }
            return null;
        }


    }


}
