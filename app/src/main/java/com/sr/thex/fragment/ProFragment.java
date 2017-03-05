package com.sr.thex.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.sr.thex.R;
import com.sr.thex.activity.EditprofileActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by sr on 2/22/17.
 */

public class ProFragment extends Fragment {



    ImageView imageViewprofile;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.pro_layout, container, false);
        imageViewprofile=(ImageView) rootView.findViewById(R.id.imageViewprofile);



       final FloatingActionButton fabeditphoto =(FloatingActionButton)rootView.findViewById(R.id.fabeditphoto);

        fabeditphoto.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {


            }

        });



        return rootView;





    }



    //load Image From Storage
    private void loadImageFromStorage( )
    {
        String appname =  getString(R.string.app_name);


        try {

            File f = new File(Environment.getExternalStorageDirectory() + File.separator + "/" + appname + "/Image/profile_image.png");
            if(f.exists()) {

                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));

                imageViewprofile.setImageBitmap(b);
            }
            else {
                //Intent locIntent = new Intent(getApplication(),MapActivity.class);
                //startActivity(locIntent);


            }
        }
        catch (FileNotFoundException e)
        {
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
