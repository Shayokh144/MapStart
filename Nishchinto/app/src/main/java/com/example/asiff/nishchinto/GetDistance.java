package com.example.asiff.nishchinto;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class GetDistance extends AppCompatActivity implements LocationListener{
    long d1, d2, d;

    double longi1, longi2, lati1, lati2;
    GPSTracker gps;
    GPSTracker gp;
    static final Double EARTH_RADIUS = 6371000.00;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_distance);
        setTitle("Speed & Distance");
        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        this.onLocationChanged(null);


        Button start=(Button)findViewById(R.id.buttonstart);
        Button stop =(Button)findViewById(R.id.buttonstop);



        gps = new GPSTracker(GetDistance.this);






        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // print the next time in milliseconds
                final Calendar c = Calendar.getInstance();

                  d1 = c.getTimeInMillis();



               // check if GPS enabled
                if (gps.canGetLocation()) {

                   lati1 = gps.getLatitude();
                     longi1 = gps.getLongitude();
                    if (lati1 == 0.0 && longi1 == 0.0) {
                        Toast.makeText(getApplicationContext(), "try again",
                                Toast.LENGTH_SHORT).show();

                    } else {

                        // \n is for new line
                        Toast.makeText(getApplicationContext(), "Lati: "+lati1+"  \nlongi : "+longi1,
                                Toast.LENGTH_SHORT).show();


                    }


                } else {
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }



            }
        });






        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // d2 = cal2.getTimeInMillis();
                gp = new GPSTracker(GetDistance.this);




              //  gps = new GPSTracker(GetDistance.this);

                // print the next time in milliseconds
                final Calendar c2 = Calendar.getInstance();
               d2 = c2.getTimeInMillis();



                // check if GPS enabled
             //   if (gp.canGetLocation())
             //
             {

                    lati2 = gp.getLatitude();
                    longi2 = gp.getLongitude();
                    if ((lati2 == 0.0 && longi2 == 0.0)||(lati2==lati1&&longi2==longi1)) {
                        Toast.makeText(getApplicationContext(), "try again",
                                Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Lati: "+lati2+"  \nlongi : "+longi2,
                                Toast.LENGTH_SHORT).show();

                        // \n is for new line
                        d=d2-d1;

                        double Radius = EARTH_RADIUS;
                        double dLat = Math.toRadians(lati2-lati1);
                        double dLon = Math.toRadians(longi2-longi1);
                        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                                Math.cos(Math.toRadians(lati1)) * Math.cos(Math.toRadians(lati2)) *
                                        Math.sin(dLon/2) * Math.sin(dLon/2);
                        double c = 2 * Math.asin(Math.sqrt(a));

                        double distance=c*EARTH_RADIUS;
                        d=d/1000;

                        double speed=distance/d;
                        Toast.makeText(GetDistance.this,"Total time= "+d+"second", Toast.LENGTH_LONG).show();



                        Toast.makeText(GetDistance.this, "Total Distance= "+distance, Toast.LENGTH_LONG).show();

                        Toast.makeText(GetDistance.this, "Avg Speed= "+speed, Toast.LENGTH_LONG).show();





                    }


                } /*else {
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }
*/






            }
        });





    }

    public void onLocationChanged(Location location){
        TextView txt = (TextView) this.findViewById(R.id.textView);
        if(location==null)
        {
            txt.setText("-.-m/s");
        }
        else
        {
            float nCurrentSpeed = location.getSpeed();
            txt.setText(nCurrentSpeed + "m/s");

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


    public void HelpGo(MenuItem item) {
        //Toast.makeText(getApplicationContext(), "Helppppp", Toast.LENGTH_SHORT).show();
        Intent h=new Intent(GetDistance.this,HelpActivity.class);
        startActivity(h);

    }

    public void HomeGo(MenuItem item) {
        // Toast.makeText(getApplicationContext(), "Helppppp", Toast.LENGTH_SHORT).show();
        Intent h=new Intent(GetDistance.this,MainActivity.class);
        startActivity(h);
        // finish();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_distance, menu);
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
