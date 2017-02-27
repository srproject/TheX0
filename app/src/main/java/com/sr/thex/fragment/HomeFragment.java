package com.sr.thex.fragment;

import android.content.Intent;
import android.os.Bundle;
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

/**
 * Created by sr on 2/22/17.
 */

public class HomeFragment extends Fragment {





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.home_layout, container, false);

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

                sharenn +=1;
                sharen.setText(String.valueOf(sharenn) );
                //for share


                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Here is the share content body";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));




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

    public void calculcate(View v) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Here is the share content body";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
}
