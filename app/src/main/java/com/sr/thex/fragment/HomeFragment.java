package com.sr.thex.fragment;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.sr.thex.R;
import com.sr.thex.activity.CommentActivity;
import com.sr.thex.activity.EditprofileActivity;
import com.sr.thex.activity.MainActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by sr on 2/22/17.
 */

public class HomeFragment extends Fragment implements KeyListener {

    ImageView hprofile, imageView1;

    static boolean scroll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.home_layout, container, false);

        //((MainActivity) getActivity()).hideFloatingActionButton();


        rootView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY < scrollX) {
                    scroll = true;

                }
                if (scrollY < oldScrollY) {

                    scroll = false;


                }
            }
        });


        //for profile image
        hprofile = (ImageView) rootView.findViewById(R.id.hprofile);
        imageView1 = (ImageView) rootView.findViewById(R.id.imageView1);


        //for nteraction
        final TextView sharen = (TextView) rootView.findViewById(R.id.sharen);
        final TextView likehn = (TextView) rootView.findViewById(R.id.likehn);
        final TextView dislikehn = (TextView) rootView.findViewById(R.id.dislikehn);

        final FloatingActionButton fabhshare1 = (FloatingActionButton) rootView.findViewById(R.id.fabhshare1);
        final FloatingActionButton fabhlike = (FloatingActionButton) rootView.findViewById(R.id.fabhlike);
        final FloatingActionButton fabhdislike = (FloatingActionButton) rootView.findViewById(R.id.fabhdislike);
        final FloatingActionButton fabhcomm = (FloatingActionButton) rootView.findViewById(R.id.fabhcomm);

        fabhcomm.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), CommentActivity.class);
                startActivity(intent);

            }

        });

        fabhshare1.setOnClickListener(new View.OnClickListener() {
            int sharenn=0;
            @Override
            public void onClick(View v) {
                //update text


                //for share


                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Here is the share content body";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                sharenn += 1;
                sharen.setText(String.valueOf(sharenn));



            }

        });

        fabhlike.setOnClickListener(new View.OnClickListener() {
            int liken=0;
            @Override
            public void onClick(View v) {
                //update text

                liken +=1;
                likehn.setText(String.valueOf(liken) );
                //for share






            }

        });

        fabhdislike.setOnClickListener(new View.OnClickListener() {
            int disliken=0;
            @Override
            public void onClick(View v) {
                //update text

                disliken +=1;
                dislikehn.setText(String.valueOf(disliken) );
                //for share






            }

        });


            return rootView;

    }


    @Override
    public void onResume() {
        super.onResume();

        loadImageFromStorage();


    }


    //load Image From Storage
    private void loadImageFromStorage() {
        String appname = getString(R.string.app_name);


        try {

            File f = new File(Environment.getExternalStorageDirectory() + File.separator + "/" + appname + "/Image/profile_image.png");
            if (f.exists()) {

                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));

                hprofile.setImageBitmap(b);
            }
            File locaionf = new File(Environment.getExternalStorageDirectory() + File.separator + "/" + appname + "/Image/location_add.png");
            if (locaionf.exists()) {

                Bitmap b2 = BitmapFactory.decodeStream(new FileInputStream(locaionf));

                imageView1.setImageBitmap(b2);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    @Override
    public int getInputType() {
        return 0;
    }

    private long lastPressedTime;
    private static final int PERIOD = 2000;


    @Override
    public boolean onKeyDown(View view, Editable text, int keyCode, KeyEvent event) {

        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            switch (event.getAction()) {
                case KeyEvent.ACTION_DOWN:
                    if (event.getDownTime() - lastPressedTime < PERIOD) {
                        getActivity().finish();
                    } else {
                        Toast.makeText(getContext(), "Press again to exit.",
                                Toast.LENGTH_SHORT).show();
                        lastPressedTime = event.getEventTime();
                    }
                    return true;
            }
        }
        return false;

    }

    @Override
    public boolean onKeyUp(View view, Editable text, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onKeyOther(View view, Editable text, KeyEvent event) {
        return false;
    }

    @Override
    public void clearMetaKeyState(View view, Editable content, int states) {

    }


}
