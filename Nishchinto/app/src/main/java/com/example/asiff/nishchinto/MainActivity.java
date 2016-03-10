package com.example.asiff.nishchinto;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

import static android.widget.Button.OnClickListener;

public class MainActivity extends AppCompatActivity  {

   public PendingIntent pendingIntent;

 public    Intent myIntent;// = new Intent(MainActivity.this, MyAlarmService.class);
  public   AlarmManager alarmManager;
    public static final String MY_JSON ="MY_JSON";

    private static final String JSON_URL = "http://shayokhasif.netne.net/Fdata.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // final PendingIntent[] pendingIntent = new PendingIntent[1];

       // final Intent myIntent = new Intent(MainActivity.this, MyAlarmService.class);
       // pendingIntent = PendingIntent.getService(MainActivity.this, 0, myIntent, 0);
        Button  SaveLocation=(Button)findViewById(R.id.SaveLocation);
       // Button help=(Button)findViewById(R.id.Helpbutton1);
        Button  ViewSavedLocation=(Button)findViewById(R.id.ViewSavedLocation);
        //Button start=(Button)findViewById(R.id.StartAppbutton);
       // Button stop=(Button)findViewById(R.id.StopAppbutton);
        Button GetMapLocation=(Button)findViewById(R.id.GetMap);
        Button GetWebData=(Button)findViewById(R.id.GetWebLocation);
        Button GetDistance=(Button)findViewById(R.id.GetDistance);


        GetDistance.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent a=new Intent(MainActivity.this,GetDistance.class);
                startActivity(a);



            }
        });







        GetWebData.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {




             if(isNetworkAvailable())
                {
                    getJSON(JSON_URL);
                }
                else {
                 //  Toast.makeText(MainActivity.this, "Check your Internet connection", Toast.LENGTH_LONG).show();

                Alert();


             }





            }
        });








        SaveLocation.setOnClickListener(

                new OnClickListener() {

                    public void onClick(View v) {

                        Intent a=new Intent(MainActivity.this,AndroidGPSTrackingActivity.class);
                        startActivity(a);


                    }
                }

        );







       GetMapLocation.setOnClickListener(

               new OnClickListener() {

                   public void onClick(View v) {

                       Intent a = new Intent(MainActivity.this, GetLocationViaMap.class);
                       startActivity(a);


                   }
               }

       );







       /* help.setOnClickListener(

                new OnClickListener() {

                    public void onClick(View v) {

                        Intent h=new Intent(MainActivity.this,HelpActivity.class);
                        startActivity(h);


                    }
                }

    );*/





        //....................STOP BUTTON START

        /*


       // final PendingIntent finalPendingIntent = pendingIntent[0];
        stop.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0)
            {
                // TODO Auto-generated method stub
                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

                alarmManager.cancel(pendingIntent);
              //  Intent myIntent = new Intent(MainActivity.this, MyAlarmService.class);
                stopService(myIntent);




                // Tell the user about what we did.
                Toast.makeText(MainActivity.this, "Service Stopped", Toast.LENGTH_LONG).show();

            }
        });



        */


        //............STOP END.........................



        ViewSavedLocation.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent it=new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(it);
            }
        });






        //..........START BUTTON.......................................




     /*   start.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
        // TODO Auto-generated method stub

                //http://javatechig.com/android/repeat-alarm-example-in-android


        Toast.makeText(getApplicationContext(), "START", Toast.LENGTH_SHORT).show();


      pendingIntent = PendingIntent.getService(MainActivity.this, 0, myIntent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 1);//the time interval between first button press & start of service (directly in second unit)
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 120 * 1000, pendingIntent);
                startService(myIntent);
    }
});


//...............................START END
*/






        Typeface font = Typeface.createFromAsset(getAssets(),  "CantoraOne-Regular.ttf" );

        SaveLocation.setTypeface(font);
        GetMapLocation.setTypeface(font);
        ViewSavedLocation.setTypeface(font);
        GetWebData.setTypeface(font);
        GetDistance.setTypeface(font);


}



 public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



    public  void Alert()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Conectivity Error")
                .setMessage("Check Network Connection")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit")
                .setMessage("Are you sure you want to Exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME));
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }





    private void showParseActivity(String s) {
        if(s.length()!=0)
        {
            Intent intent = new Intent(this, ParseJson.class);
            // intent.putExtra(MY_JSON,textViewJSON.getText().toString());
            intent.putExtra("s", s);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "START", Toast.LENGTH_SHORT).show();
        }

    }



    private void getJSON(String url) {
        class GetJSON extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
              //  Toast.makeText(getApplicationContext(), "onPreExecute()", Toast.LENGTH_SHORT).show();
                loading = ProgressDialog.show(MainActivity.this, "Please Wait...",null,true,true);

            }

            @Override
            protected String doInBackground(String... params) {
              //  Toast.makeText(getApplicationContext(), " doInBackground", Toast.LENGTH_SHORT).show();

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }


                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }

            }

            @Override
            protected void onPostExecute(String s) {
               // if(s.length()!=0)
                {
                    super.onPostExecute(s);
                    loading.dismiss();
                    // textViewJSON.setText(s);
                    showParseActivity(s);
                }
               // else
                {
                   // loading.dismiss();
                }

            }
        }
        GetJSON gj = new GetJSON();
        gj.execute(url);

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);






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



    public void HelpGo(MenuItem item) {
        ///Toast.makeText(getApplicationContext(), "Helppppp", Toast.LENGTH_SHORT).show();
        Intent h=new Intent(MainActivity.this,HelpActivity.class);
        startActivity(h);

    }



    public void MenuStart(MenuItem item) {

        //http://javatechig.com/android/repeat-alarm-example-in-android

        //  Toast.makeText(getApplicationContext(), "Start......", Toast.LENGTH_SHORT).show();

      // Toast.makeText(getApplicationContext(), "START", Toast.LENGTH_SHORT).show();

        /////////////
        myIntent = new Intent(MainActivity.this, MyAlarmService.class);
/////////////////////////////////////

        pendingIntent = PendingIntent.getService(MainActivity.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //AlarmManager
                alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 1);//the time interval between first button press & start of service (directly in second unit)
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
       alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 120 * 1000, pendingIntent);
        startService(myIntent);
       // Intent h=new Intent(MainActivity.this,HelpActivity.class);
       // startActivity(h);

    }


    public void MenuStop(MenuItem item) {
       // Toast.makeText(getApplicationContext(), "Stop.....", Toast.LENGTH_SHORT).show();


        /////////////////
        myIntent = new Intent(MainActivity.this, MyAlarmService.class);
//////////////////////////////////////////
      // pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0);
        //AlarmManager

        //////new



        pendingIntent = PendingIntent.getService(MainActivity.this, 0, myIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        ///////////new

                alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        alarmManager.cancel(pendingIntent);


        ////////////////new///////////////////////////////////


      //  PendingIntent.getService(MainActivity.this, 0, myIntent,
        //        PendingIntent.FLAG_UPDATE_CURRENT).cancel();

        ////////////////new////////////////////////////////////


        // Intent myIntent = new Intent(MainActivity.this, MyAlarmService.class);
        stopService(myIntent);




        // Tell the user about what we did.
        Toast.makeText(MainActivity.this, "Service Stopped", Toast.LENGTH_LONG).show();


        //Intent h=new Intent(MainActivity.this,HelpActivity.class);
        //startActivity(h);

    }






}
