package com.sr.thex.fragment;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sr.thex.R;
import com.sr.thex.activity.EditprofileActivity;
import com.sr.thex.activity.MainActivity;
import com.sr.thex.activity.RegistrationActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class NewProFragment extends Fragment {

    TextView editprofiletext;
    ImageView imageprofile;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //getActivity().setTitle("Team B");


        View rootView = inflater.inflate(R.layout.new_profile_layout, container, false);
//typecode

        editprofiletext = (TextView) rootView.findViewById(R.id.editprofiletext);

        editprofiletext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditprofileActivity.class);
                startActivity(intent);

            }
        });

        imageprofile = (ImageView) rootView.findViewById(R.id.imageprofile);


//ENDcode
        return rootView;
    }

    private void loadImageFromStorage() {
        String appname = getString(R.string.app_name);


        try {

            File f = new File(Environment.getExternalStorageDirectory() + File.separator + "/" + appname + "/Image/profile_image.png");
            if (f.exists()) {

                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));

                imageprofile.setImageBitmap(b);
            } else {


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    //END//load Image From Storage


    @Override
    public void onResume() {
        super.onResume();

        loadImageFromStorage();


    }


}
