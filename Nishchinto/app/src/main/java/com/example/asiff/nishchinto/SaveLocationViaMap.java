package com.example.asiff.nishchinto;

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

public class SaveLocationViaMap extends AppCompatActivity {




    Button btnSaveLocation;
    Button btnChangeDate;
    EditText et;
    EditText rim;
    EditText city;
    EditText Ulong;
    EditText Ulat;
    String input;
    String rimainder;
    String cityName;
    private DatePicker dpResult;
    private int year;
    private int month;
    private int date;
    static final int DATE_DIALOG_ID = 999;

    // SQLiteDatabase myDB;
    SQLiteDatabase myDBs;
    String TableName = "myTables";




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_location_via_map);


       final double LN,LT;
        LN = getIntent().getDoubleExtra("LON",0.0);
        LT = getIntent().getDoubleExtra("LAT",0.0);


        //Toast.makeText(getApplicationContext(), "lat= "+LT+" long= "+LN, Toast.LENGTH_SHORT).show();

        et = (EditText) findViewById(R.id.editText1);
        rim = (EditText) findViewById(R.id.editText2);
        city=(EditText)findViewById(R.id.editTextCity);
       // setCurrentDateOnView();
       // addListenerOnButton();
        btnSaveLocation = (Button) findViewById(R.id.btnSaveMapLocation);




        btnSaveLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // create class object

                // check if GPS enabled

                        input = et.getText().toString().trim();
                        rimainder = rim.getText().toString().trim();
                        cityName=city.getText().toString().trim();

                        try {
                            myDBs = openOrCreateDatabase("agps_db", MODE_PRIVATE,
                                    null);

                            myDBs.execSQL("CREATE TABLE IF NOT EXISTS "
                                    + TableName
                                    + " (location VARCHAR,rimtion TEXT,longi REAL,lati REAL,cityname TEXT);");

                            myDBs.execSQL("INSERT INTO " + TableName + " Values ('"
                                    + input + "','" + rimainder + "','" + LN
                                    + "','" + LT + "','" + cityName + "');");


                            Toast.makeText(getApplicationContext(),
                                    "Successfully Saved", Toast.LENGTH_SHORT)
                                    .show();
                            // startActivity(new Intent(Second.this,Main.class));

                            Intent h=new Intent(SaveLocationViaMap.this,MainActivity.class);
                            startActivity(h);



                        } catch (Exception ex) {
                            Toast.makeText(getApplicationContext(), ex.toString(),
                                    Toast.LENGTH_LONG).show();
                        }
                        myDBs.close();

                        // \n is for new line
                        Toast.makeText(
                                getApplicationContext(),
                                "Your Location is - \nLat: " + LT
                                        + "\nLong: " + LN, Toast.LENGTH_LONG)
                                .show();
                    }





        });



        Typeface font = Typeface.createFromAsset(getAssets(),  "CantoraOne-Regular.ttf" );

        btnSaveLocation.setTypeface(font);
        et.setTypeface(font);
        city.setTypeface(font);
        rim.setTypeface(font);



    }



    public void HelpGo(MenuItem item) {
        //Toast.makeText(getApplicationContext(), "Helppppp", Toast.LENGTH_SHORT).show();
        Intent h=new Intent(SaveLocationViaMap.this,HelpActivity.class);
        startActivity(h);

    }

    public void HomeGo(MenuItem item) {
        // Toast.makeText(getApplicationContext(), "Helppppp", Toast.LENGTH_SHORT).show();
        Intent h=new Intent(SaveLocationViaMap.this,MainActivity.class);
        startActivity(h);
        // finish();


    }



    /*public void setCurrentDateOnView() {

        // tvDisplayDate = (TextView) findViewById(R.id.tvDate);
        dpResult = (DatePicker) findViewById(R.id.datePicker1);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        date = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
		/*
		 * tvDisplayDate.setText(new StringBuilder() // Month is 0 based, just
		 * add 1 .append(month + 1).append("-").append(day).append("-")
		 * .append(year).append(" "));
		 *
		 * // set current date into datepicker
		 */
        /*dpResult.init(year, month, date, null);

    }
    public void addListenerOnButton()
    {
        btnChangeDate=(Button)findViewById(R.id.button1);
        btnChangeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
    }
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener, year, month,
                        date);
        }
        return null;
    }*/

  /*  private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            date = selectedDay;
            // Toast.makeText(getApplicationContext(),""+ year+month+date,
            // Toast.LENGTH_LONG).show();

            // set selected date into textview
			/*
			 * tvDisplayDate.setText(new StringBuilder().append(month + 1)
			 * .append("-").append(date).append("-").append(year) .append(" "));
			 */

            // set selected date into datepicker also
          /*  dpResult.init(year, month, date, null);

        }
    };


*/




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_save_location_via_map, menu);
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
