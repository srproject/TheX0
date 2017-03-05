package com.sr.thex.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sr.thex.R;
import com.sr.thex.activity.CommentActivity;
import com.sr.thex.activity.EditprofileActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by sr on 2/22/17.
 */

public class HomeFragment extends Fragment {

    ImageView hprofile, imageView1;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.home_layout, container, false);

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



    public void calculcate(View v) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Here is the share content body";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
}
