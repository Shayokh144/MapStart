package com.example.asiff.nishchinto;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GetLocationViaMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;// Might be null if Google Play services APK is not available.
    double lat;
    double lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location_via_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        mMap=mapFragment.getMap();

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {

                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting the position for the marker
                markerOptions.position(latLng);

                // Setting the title for the marker.
                // This will be displayed on taping the marker
                //  markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                lat=latLng.latitude;
                lon=latLng.longitude;
               // Toast.makeText(getApplicationContext(), "lat= "+lat+" long= "+lon, Toast.LENGTH_SHORT).show();


                // Clears the previously touched position
                mMap.clear();

                // Animating to the touched position
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                // Placing a marker on the touched position
                mMap.addMarker(markerOptions);


               Intent intent=new Intent(GetLocationViaMap.this,SaveLocationViaMap.class);

                intent.putExtra("LAT",lat);
                intent.putExtra("LON",lon);

                startActivity(intent);


            }
        });






    }




    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */


    /*private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     *//*
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }*/



    @Override
    public void onMapReady(GoogleMap map) {

        // Add a marker in Sydney, Australia, and move the camera.
        //LatLng sydney = new LatLng(-34, 151);



        LatLng bang = new LatLng(23.73126, 90.41245);
        map.addMarker(new MarkerOptions().position(bang).title("Marker in Bangladesh"));
        map.moveCamera(CameraUpdateFactory.newLatLng(bang));

        String TableName = "myTables";
        SQLiteDatabase myDBs  ;
        double longi1,lati1;

        myDBs = openOrCreateDatabase("agps_db", MODE_PRIVATE, null);
        Cursor c =myDBs.rawQuery("SELECT location ,rimtion ,longi ,lati ,cityname FROM " + TableName, null);
        if (c != null)
        {
            boolean  match=false;
            c.moveToFirst();



            do
            {


                //  Log.d("x", "Do while loop");

                longi1 = c.getDouble(c.getColumnIndex("longi"));
                lati1=c.getDouble(c.getColumnIndex("lati"));

                LatLng bd = new LatLng(lati1, longi1);
                map.addMarker(new MarkerOptions().position(bd));







            }while(c.moveToNext());


        }








    }
}
