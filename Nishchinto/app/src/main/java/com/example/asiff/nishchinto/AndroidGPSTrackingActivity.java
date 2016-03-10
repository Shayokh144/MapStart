package com.example.asiff.nishchinto;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class AndroidGPSTrackingActivity extends AppCompatActivity {



    Button btnShowLocation;
    Button btnChangeDate;
    EditText et;
    EditText rim;
    EditText Ulong;
    EditText Ulat;
    EditText city;
    String input,iit;
    String rimainder,remmd;
    String cityName,ctnm;
    private DatePicker dpResult;
    private int year;
    private int month;
    private int date;


    // SQLiteDatabase myDB;
    SQLiteDatabase myDBs;
    String TableName = "myTables";

    // GPSTracker class
    GPSTracker gps;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_gpstracking);

        setTitle("Save Current Location");

        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
        et = (EditText) findViewById(R.id.editText1);
        rim = (EditText) findViewById(R.id.editText2);
        city=(EditText)findViewById(R.id.editTextCity);

       // setCurrentDateOnView();
        //addListenerOnButton();
        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                iit = et.getText().toString().trim();
                remmd = rim.getText().toString().trim();
                ctnm = city.getText().toString().trim();

                if(iit.length()==0||remmd.length()==0||ctnm.length()==0)
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(AndroidGPSTrackingActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Fill the Blank Field");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }


              else  {
                    // create class object
                    gps = new GPSTracker(AndroidGPSTrackingActivity.this);

                    // check if GPS enabled
                    if (gps.canGetLocation()) {

                        double latitude = gps.getLatitude();
                        double longitude = gps.getLongitude();
                        if (latitude == 0.0 && longitude == 0.0) {
                            Toast.makeText(getApplicationContext(), "try again",
                                    Toast.LENGTH_SHORT).show();
                            Intent h = new Intent(AndroidGPSTrackingActivity.this, MainActivity.class);
                            startActivity(h);

                        } else {
                            input = et.getText().toString().trim();
                            rimainder = rim.getText().toString().trim();
                            cityName = city.getText().toString().trim();

                            try {
                                myDBs = openOrCreateDatabase("agps_db", MODE_PRIVATE,
                                        null);

                                myDBs.execSQL("CREATE TABLE IF NOT EXISTS "
                                        + TableName
                                        + " (location VARCHAR,rimtion TEXT,longi REAL,lati REAL,cityname TEXT);");

                                myDBs.execSQL("INSERT INTO " + TableName + " Values ('"
                                        + input + "','" + rimainder + "','" + longitude
                                        + "','" + latitude + "','" + cityName + "');");

                                Toast.makeText(getApplicationContext(),
                                        "Successfully Saved", Toast.LENGTH_SHORT)
                                        .show();

                                Intent h = new Intent(AndroidGPSTrackingActivity.this, MainActivity.class);
                                startActivity(h);
                                // startActivity(new Intent(Second.this,Main.class));

                            } catch (Exception ex) {
                                Toast.makeText(getApplicationContext(), ex.toString(),
                                        Toast.LENGTH_LONG).show();
                            }
                            myDBs.close();

                            // \n is for new line
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Your Location is - \nLat: " + latitude
                                            + "\nLong: " + longitude, Toast.LENGTH_LONG)
                                    .show();
                        }


                    } else {
                        // can't get location
                        // GPS or Network is not enabled
                        // Ask user to enable GPS/network in settings
                        gps.showSettingsAlert();
                    }

                }
            }

        });


        Typeface font = Typeface.createFromAsset(getAssets(),  "CantoraOne-Regular.ttf" );

        btnShowLocation.setTypeface(font);
       et.setTypeface(font);
        city.setTypeface(font);
        rim.setTypeface(font);



    }


    public void HelpGo(MenuItem item) {
        //Toast.makeText(getApplicationContext(), "Helppppp", Toast.LENGTH_SHORT).show();
        Intent h=new Intent(AndroidGPSTrackingActivity.this,HelpActivity.class);
        startActivity(h);

    }

    public void HomeGo(MenuItem item) {
        // Toast.makeText(getApplicationContext(), "Helppppp", Toast.LENGTH_SHORT).show();
        Intent h=new Intent(AndroidGPSTrackingActivity.this,MainActivity.class);
        startActivity(h);
       // finish();


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_android_gpstracking, menu);
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
