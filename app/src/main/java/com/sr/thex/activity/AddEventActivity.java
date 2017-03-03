package com.sr.thex.activity;

import android.app.DatePickerDialog;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.vision.barcode.Barcode;
import com.sr.thex.R;
import com.sr.thex.adapter.GPSTracker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddEventActivity extends AppCompatActivity implements LocationListener {

    public GoogleMap mMap;
    ImageView imageaddmatab;
    ImageView samplemap;
    FloatingActionButton fabloc;
    int CAMERA_PIC_REQUEST = 2;
    int  TAKE_PICTURE=0;
    Camera camera;
    Bitmap bitmap;
    int year,mon,day;
    EditText datematab,timematab,loc,locname,detailsmatab;
    String cityname,cityname2;

    private LocationManager locationManager;
    private LocationListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //edit enable

           // datematab.setEnabled(false);
          // timematab.setEnabled(false);
         // loc.setEnabled(false);
        // locname.setEnabled(false);

        detailsmatab=(EditText)findViewById(R.id.detailsmatab) ;
        detailsmatab.requestFocus();


        //for location

        loc=(EditText)findViewById(R.id.locationmatab) ;
        locname=(EditText)findViewById(R.id.locname) ;


        loc.setText("Waiting For GPS");
        locname.setText("Waiting For GPS");


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
               loc.setText( location.getLongitude() + "," + location.getLatitude());

                //locname.setText( location.getLongitude() + "," + location.getLatitude());
                List<Address> addresses;
                Geocoder gcd=new Geocoder(getBaseContext(), Locale.getDefault());

                try {
                    addresses=gcd.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                    if(addresses.size()>0)

                    {
                       // while(locname.getText().toString()==null) {
                        cityname = addresses.get(0).getAddressLine(0).toString();
                        if(addresses.get(0).getLocality()==null) {
                            cityname2 = addresses.get(0).getCountryName();
                            Toast.makeText(getApplicationContext(), "Location Updated", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            cityname2 = addresses.get(0).getLocality() + " - " + addresses.get(0).getCountryName();
                            Toast.makeText(getApplicationContext(), "Location Updated", Toast.LENGTH_SHORT).show();


                        }
                        locname.setText(cityname+" - "+cityname2);
                      //  }
                    }

                } catch (IOException e) {
                    e.printStackTrace();

                }

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

                loc.setText("Waiting For GPS...");
                locname.setText("Waiting For GPS...");

            }

            @Override
            public void onProviderEnabled(String s) {

                loc.setText("Search For Location");
                locname.setText("Search For Location");

            }

            @Override
            public void onProviderDisabled(String s) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };

        configure_button();



        //addtime
        final InputMethodManager imm = (InputMethodManager)this.getSystemService(Service.INPUT_METHOD_SERVICE);


 //for date

        /*
        Calendar mcurrentTime = Calendar.getInstance();
          year = mcurrentTime.get(Calendar.YEAR);
          mon = mcurrentTime.get(Calendar.MONTH);
          day = mcurrentTime.get(Calendar.DAY_OF_MONTH);

        datematab =(EditText) findViewById(R.id.datematab);
        datematab.setText(day + "/" + (mon+1) + "/" + year);

        */

       // datematab.setOnClickListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View view) {
             //   imm.hideSoftInputFromWindow(datematab.getWindowToken(), 0);



//                DatePickerDialog datePickerDialog  ;


  //              datePickerDialog = new DatePickerDialog(AddEventActivity.this, new DatePickerDialog.OnDateSetListener() {

    //                @Override
      //              public void onDateSet(DatePicker datePicker, int years, int mons, int days) {

        //                datematab.setText(days + " / " + (mons+1) + " / " + years);

            //        }
          //      }, year, mon, day);
              //  datePickerDialog.setTitle("Select Date");
                //datePickerDialog.show();
          //  }
        //});

/*
        Date dt = new Date();
        int hours = dt.getHours();
        int minutes = dt.getMinutes();
        int seconds = dt.getSeconds();
        String curTime = hours + ":" + minutes + ":" + seconds;
        fillTextView(R.id.timematab,curTime);

        //addtime
        timematab = (EditText) findViewById(R.id.timematab);

        */

        timematab = (EditText) findViewById(R.id.timematab);


        new CountDownTimer(300000000, 1000) {

            public void onTick(long millisUntilFinished) {

                DateFormat tf = new SimpleDateFormat("HH:mm:ss");
                String time = tf.format(Calendar.getInstance().getTime());
                timematab.setText(time);


                datematab =(EditText) findViewById(R.id.datematab);


                DateFormat df = new SimpleDateFormat("EEE, dd/MM/yyyy");
                String date = df.format(Calendar.getInstance().getTime());
                datematab.setText(date);



            }

            public void onFinish() {
               // timematab.setText("Unlocking!");
            }
        }.start();



        // FindViewById
        imageaddmatab = (ImageView) findViewById(R.id.imageaddmatab);
        samplemap = (ImageView) findViewById(R.id.samplemap);

        // FindViewById//

        fabloc = (FloatingActionButton) findViewById(R.id.fabloc);



        //ClickListener

        imageaddmatab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                setResult(RESULT_OK, cameraIntent);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);


            }
        });


      //  fabloc.setOnClickListener(new View.OnClickListener() {
        //    @Override
          //  public void onClick(View view) {

            //    Intent locIntent = new Intent(getApplication(), MapActivity.class);
              //  startActivity(locIntent);


            //}
        //});

        //ClickListener
    }


    //load Image From Storage
    private void loadImageFromStorage( )
    {
        String appname =  getString(R.string.app_name);


        try {

            File f = new File(Environment.getExternalStorageDirectory() + File.separator + "/" + appname + "/Image/location_add.png");
            if(f.exists()) {

                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));

                samplemap.setImageBitmap(b);
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




    //scale Down Bitmap

    public static Bitmap scaleDownBitmap(Bitmap photo, int newHeight, Context context) {

        final float densityMultiplier = context.getResources().getDisplayMetrics().density;

        int h= (int) (newHeight*densityMultiplier);
        int w= (int) (h * photo.getWidth()/((double) photo.getHeight()));

        photo=Bitmap.createScaledBitmap(photo, w, h, true);

        return photo;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK && data != null)
        {
            bitmap = (Bitmap) data.getExtras().get("data");
            imageaddmatab.setImageBitmap(bitmap);

        }
        else
        {
            Toast.makeText(getApplicationContext(), "Picture Not taken", Toast.LENGTH_LONG);
        }
    }

    // for re
    // sume activity

    @Override
    protected void onResume() {
        super.onResume();
        if( MapActivity.activityPaused()==true){
            loadImageFromStorage();

        }
    }

    private void fillTextView (int id, String text) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(text); // tv is null
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                configure_button();
                break;
            default:
                break;
        }
    }

    void configure_button(){
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                        ,10);
            }
            return;
        }
        // this code won't execute IF permissions are not allowed, because in the line above there is return statement.

                //noinspection MissingPermission
                locationManager.requestLocationUpdates("gps", 0, 0, listener);

    }

    @Override
    public void onLocationChanged(Location location) {
      //  loc.setText(  location.getLongitude() + "," + location.getLatitude());


        List<Address> addresses;
        Geocoder gcd=new Geocoder(getBaseContext(), Locale.ENGLISH);

        try {
            addresses=gcd.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            if(addresses.size()>0)

            {
               // while(locname.getText().toString()==null) {
                   // cityname = addresses.get(0).getCountryName().toString();
                   // loc.setText(cityname);
                //}
            }

        } catch (IOException e) {
            e.printStackTrace();

        }

    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
