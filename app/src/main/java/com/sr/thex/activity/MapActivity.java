package com.sr.thex.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sr.thex.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, com.google.android.gms.location.LocationListener {

    public GoogleMap mMap;
    private final LatLng HAMBURG = new LatLng(30.039661, 31.265244);
    private final LatLng KIEL = new LatLng(30.039661, 31.265244);
    private final LatLng KIEL2 = new LatLng(30.039561, 31.265244);
    private final LatLng KIEL3 = new LatLng(30.039461, 31.265244);
    private final LatLng KIEL4 = new LatLng(30.039361, 31.265244);
    private final LatLng KIEL5 = new LatLng(30.039261, 31.265244);

    String address;
    String city;
    String state;
    String country;
    String postalCode;
    String knownName;
    LatLng latLng;
    String latlat;
    int gg=10;


    Bitmap bitmap;
    FloatingActionButton fabsetmap;

    LocationManager locationManager;
    private android.location.LocationListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);

        //    Toast.makeText(getApplicationContext(), address + " " + city + " " + country, Toast.LENGTH_LONG).show();

        //  Toast.makeText(getApplicationContext(),"Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude(), Toast.LENGTH_LONG).show();


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        fabsetmap=(FloatingActionButton)findViewById(R.id.fabsetmap) ;


         fabsetmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CaptureMapScreen();



            }
        });




        new CountDownTimer(2000, 100) {

            public void onTick(long millisUntilFinished) {
Log.i("SR","Start");
            }

            public void onFinish() {
                CaptureMapScreen();


            }

        }.start();


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Start your code
        } else {
            //Show snackbar

        }
    }

    @Override
    public void onLocationChanged(Location location) {

          Toast.makeText(getApplicationContext(),"Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude(), Toast.LENGTH_LONG).show();

        latLng = new LatLng(location.getLatitude(), location.getLongitude());
         CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 70);
        mMap.animateCamera(cameraUpdate);


        new GoogleMap.OnCameraMoveStartedListener() {
            @Override
            public void onCameraMoveStarted(int i) {
                Toast.makeText(getApplicationContext(), address + " " + city + " " + country, Toast.LENGTH_LONG).show();

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates((android.location.LocationListener) MapActivity.this);

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());



        Log.i("latitude", "latitude--" + latLng.latitude);
        Toast.makeText(getApplicationContext(),getString((int) latLng.latitude), Toast.LENGTH_LONG).show();


        try {
            Log.e("latitude", "inside latitude--" + location.getLatitude());
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);





            if (addresses != null && addresses.size() > 0) {
                  address = addresses.get(0).getAddressLine(0);
                  city = addresses.get(0).getLocality();
                  state = addresses.get(0).getAdminArea();
                  country = addresses.get(0).getCountryName();
                  postalCode = addresses.get(0).getPostalCode();
                  knownName = addresses.get(0).getFeatureName();

               // locationTxt.setText(address + " " + city + " " + country);
                Log.e("Sr-Location", address + " " + city + " " + country);

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Log.i("Sr-Location", address + " " + city + " " + country);
        Toast.makeText(getApplicationContext(), address + " " + city + " " + country, Toast.LENGTH_LONG).show();


    }

    private static boolean isInBackground;
    private static boolean isAwakeFromBackground;
    private static final int backgroundAllowance = 10000;

    public static boolean activityPaused() {
        isInBackground = true;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isInBackground) {
                    isAwakeFromBackground = true;
                }
            }
        }, backgroundAllowance);
        return true;

    }


    //saveImage

    private void saveImage(Bitmap bitmap) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 40, bytes);
        String appname = getString(R.string.app_name);
        File folder = new File(Environment.getExternalStorageDirectory() + File.separator + "/" + appname + "/Image/");
        if (!folder.exists()) {
            folder.mkdirs();
            Toast.makeText(getApplicationContext(), "Folder Maked", Toast.LENGTH_SHORT).show();

        } else {
            File f2 = new File(Environment.getExternalStorageDirectory() + File.separator + "/" + appname + "/Image/location_add.png");


            f2.createNewFile();
            FileOutputStream fo = new FileOutputStream(f2);
            fo.write(bytes.toByteArray());
            fo.close();
            Toast.makeText(getApplicationContext(), "Image Saved", Toast.LENGTH_LONG).show();


        }
    }
    //END//saveImage




    public void CaptureMapScreen() {


        GoogleMap.SnapshotReadyCallback callback = new GoogleMap.SnapshotReadyCallback() {


            @Override
            public void onSnapshotReady(Bitmap snapshot) {
                // TODO Auto-generated method stub
                bitmap = snapshot;

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 40, bytes);
                String appname = getString(R.string.app_name);
                File folder = new File(Environment.getExternalStorageDirectory() + File.separator + "/" + appname + "/Image/");
                if (!folder.exists()) {
                    folder.mkdirs();
                    Toast.makeText(getApplicationContext(), "Folder Maked", Toast.LENGTH_SHORT).show();

                } else {
                    File f2 = new File(Environment.getExternalStorageDirectory() + File.separator + "/" + appname + "/Image/location_add.png");


                    try {
                        f2.createNewFile();
                        FileOutputStream fo = new FileOutputStream(f2);
                        fo.write(bytes.toByteArray());
                        fo.close();


                            finish();

                            Intent intent = new Intent(MapActivity.this, AddEventActivity.class);
                            startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Location Update", Toast.LENGTH_SHORT).show();




                    } catch (IOException e) {
                        e.printStackTrace();
                    }



                }

            }


        };


        mMap.snapshot(callback);




    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        setupMap();
    }



    private void setupMap() {

        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setIndoorLevelPickerEnabled(true);
        mMap.setBuildingsEnabled(true);
        mMap.setIndoorEnabled(true);

        listener = new android.location.LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latLng = new LatLng(location.getLatitude(), location.getLongitude());
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 70);
                mMap.animateCamera(cameraUpdate);

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
        };


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setMapToolbarEnabled(false);


        Marker kiel = mMap.addMarker(new MarkerOptions()
                .position(KIEL)
                .title("Matab")
                .snippet("Sameh Say: Kiel is cool"+"   Sr Say: Kiel is cool"+"   Sr Say: Kiel is cool"+"   Sr Say: Kiel is cool"+"   Sr Say: Kiel is cool"+"   Sr Say: Kiel is cool")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.ic_warning_black_48dp)));
        Marker kiel2 = mMap.addMarker(new MarkerOptions()
                .position(KIEL2)
                .title("fire")
                .snippet("Kiel is cool")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.ic_warning_black_48dp)));
        Marker kiel3 = mMap.addMarker(new MarkerOptions()
                .position(KIEL3)
                .title("fire on Azhar")
                .snippet("Kiel is cool")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.ic_warning_black_48dp)));
        Marker kiel4 = mMap.addMarker(new MarkerOptions()
                .position(KIEL4)
                .title("fire4")
                .snippet("Kiel is cool")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.ic_warning_black_48dp)));
        Marker kiel5 = mMap.addMarker(new MarkerOptions()
                .position(KIEL5)
                .title("fire5")
                .snippet("Kiel is cool")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.ic_warning_black_48dp)));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(KIEL, 18));




      //  mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
    }
   public void getCurrentLocation() {
        Location myLocation  = mMap.getMyLocation();
        if(myLocation!=null)
        {
            double dLatitude = myLocation.getLatitude();
            double dLongitude = myLocation.getLongitude();
            Log.i("APPLICATION"," : "+dLatitude);
            Log.i("APPLICATION"," : "+dLongitude);
            Toast.makeText(this, dLatitude+","+dLongitude, Toast.LENGTH_SHORT).show();


        }
        else
        {
            Toast.makeText(this, "Unable to fetch the current location", Toast.LENGTH_SHORT).show();
        }

    }

}
