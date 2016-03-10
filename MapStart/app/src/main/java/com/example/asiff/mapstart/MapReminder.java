package com.example.asiff.mapstart;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapReminder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_reminder);

        double LN,LT;
        LN = getIntent().getDoubleExtra("LON",0.0);
        LT = getIntent().getDoubleExtra("LAT",0.0);




        //new========================================================================


        List<Address> addresses = null;
        Geocoder geocoder = new Geocoder(this,
                Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(LT, LN, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder  str = new StringBuilder();
        if (geocoder.isPresent())
        {
            Toast.makeText(getApplicationContext
            (), "geocoder present",
                    Toast.LENGTH_SHORT).show();
            Address returnAddress =
                    addresses.get(0);

            String subLocatity=
                    returnAddress.getSubLocality();
            String localityString =
                    returnAddress.getLocality();
            String state =
                    returnAddress.getAddressLine(0);
            String subAdminArea =
                    returnAddress.getSubAdminArea();
            String adminArea=
                    returnAddress.getAdminArea();
            String countryName =
                    returnAddress.getCountryName();
            String knownName = returnAddress.getFeatureName();

            Toast.makeText(getApplicationContext
                            (), countryName+"  "+adminArea+"  "+subAdminArea+"  "+subLocatity+"  "+state+" \n name=   "+knownName,
                    Toast.LENGTH_SHORT).show();






            //address.setText(str);
           // textView.setText(str);



        } else
        {
            Toast.makeText(getApplicationContext
            (), "geocoder not present",
                    Toast.LENGTH_SHORT).show();

        }


///////////////new end===============================================================================

        Toast.makeText(getApplicationContext(), "MapReminder ", Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "lat= " + LT + " long= " + LN, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map_reminder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
