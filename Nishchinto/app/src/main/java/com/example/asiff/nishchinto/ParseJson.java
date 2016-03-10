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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseJson extends AppCompatActivity implements View.OnClickListener{


    private String myJSONString;
    SQLiteDatabase myDBs;
    String TableName = "myTables";

    private static final String JSON_ARRAY ="result";

    String s;
    private static final String NAME = "Lname";
    private static final String CITY= "Lcity";
    private static final String REM = "Lrem";
    private static final String LONGI = "LLongi";
    private static final String LATI= "LLati";
    private static final String ID = "Lid";

    private JSONArray res = null;
    double aLat,aLong;

    private int TRACK = 0;

    private EditText editTextLname;
    private EditText editTextLcity;
    private EditText editTextLLongi;
    private EditText editTextLLati;
    private EditText editTextLrem;
    private EditText editTextLid;
    private TextView textView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;

  String sname,scity,srem,slongi,slati;



    Button btnPrev;
    Button btnNext;
    Button addItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_json);
        setTitle("Get Web Service");


        Bundle bundle = getIntent().getExtras();
        myJSONString  = bundle.getString("s");




        /*editTextLname = (EditText) findViewById(R.id.editTextLname);
        editTextLcity = (EditText) findViewById(R.id.editTextLcity);
        editTextLrem = (EditText) findViewById(R.id.editTextLrem);
        editTextLLati=(EditText)findViewById(R.id.editTextLLati);
        editTextLLongi=(EditText)findViewById(R.id.editTextLLongi);
        editTextLid=(EditText)findViewById(R.id.editTextLid);*/
        textView=(TextView)findViewById(R.id.textView);
        textView1=(TextView)findViewById(R.id.textView1);
        textView2=(TextView)findViewById(R.id.textView2);
        textView3=(TextView)findViewById(R.id.textView3);
        textView4=(TextView)findViewById(R.id.textView4);
       // textView5=(TextView)findViewById(R.id.textView5);

        btnPrev = (Button) findViewById(R.id.buttonPrev);
        btnNext = (Button) findViewById(R.id.buttonNext);
        addItem=(Button)findViewById(R.id.add);

        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        addItem.setOnClickListener(this);





        Typeface font = Typeface.createFromAsset(getAssets(),  "CantoraOne-Regular.ttf" );


        extractJSON();

        showData();
        textView.setTypeface(font);
        textView1.setTypeface(font);
        textView2.setTypeface(font);
        textView3.setTypeface(font);
        textView4.setTypeface(font);










    }




    private void extractJSON(){
        try {
            JSONObject jsonObject = new JSONObject(myJSONString);
            res = jsonObject.optJSONArray("result");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void moveNext(){
        if(TRACK<res.length()){
            TRACK++;
        }
        showData();
    }

    private void movePrev(){
        if(TRACK>0){
            TRACK--;
        }
        showData();
    }

    private void showData(){
        try {



            JSONObject jsonObject = res.getJSONObject(TRACK);

           // editTextLname.setText(jsonObject.optString(NAME));
            //editTextLcity.setText(jsonObject.optString(CITY));


            sname=jsonObject.optString(NAME);

             scity=jsonObject.optString(CITY);
             srem=jsonObject.optString(REM);
             slongi=jsonObject.optString(LONGI);
             slati=jsonObject.optString(LATI);




            textView.setText("Location: "+sname);
            textView1.setText("City: "+scity);
            textView2.setText("SubLocality: "+srem);
            textView3.setText("Longitude: "+slongi);
            textView4.setText("Latitude: "+slati);

            Typeface font = Typeface.createFromAsset(getAssets(),  "CantoraOne-Regular.ttf" );
            textView.setTypeface(font);
            textView1.setTypeface(font);
            textView2.setTypeface(font);
            textView3.setTypeface(font);
            textView4.setTypeface(font);







           /* editTextLcity.setText(jsonObject.getString(CITY));
            editTextLrem.setText(jsonObject.getString(REM));
            editTextLLongi.setText((int) jsonObject.getDouble(LONGI));
            editTextLLati.setText((int) jsonObject.getDouble(LATI));
            editTextLid.setText(jsonObject.getInt(ID));

*/

           // s=jsonObject.optString("Lname").toString();
            // Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }




    @Override
    public void onClick(View v) {
        if(v == btnNext){

            moveNext();
        }
        if(v == btnPrev){

            movePrev();
        }
        if(v==addItem)
        {

          aLong = Double.parseDouble(slongi);
            aLat = Double.parseDouble(slati);

            //Toast.makeText(getApplicationContext(), sname, Toast.LENGTH_SHORT).show();


            try {
                               myDBs = openOrCreateDatabase("agps_db", MODE_PRIVATE,
                        null);

                myDBs.execSQL("CREATE TABLE IF NOT EXISTS "
                        + TableName
                        + " (location VARCHAR,rimtion TEXT,longi REAL,lati REAL,cityname TEXT);");

                myDBs.execSQL("INSERT INTO " + TableName + " Values ('"
                        + sname + "','" + srem + "','" + aLong
                        + "','" + aLat + "','" + scity + "');");

                Toast.makeText(getApplicationContext(),
                        "Successfully Saved", Toast.LENGTH_SHORT)
                        .show();



            } catch (Exception ex)
            {
                Toast.makeText(getApplicationContext(), ex.toString(),
                        Toast.LENGTH_LONG).show();
            }
            myDBs.close();


        }
    }



    public void HelpGo(MenuItem item) {
        //Toast.makeText(getApplicationContext(), "Helppppp", Toast.LENGTH_SHORT).show();
        Intent h=new Intent(ParseJson.this,HelpActivity.class);
        startActivity(h);

    }

    public void HomeGo(MenuItem item) {
        // Toast.makeText(getApplicationContext(), "Helppppp", Toast.LENGTH_SHORT).show();
        Intent h=new Intent(ParseJson.this,MainActivity.class);
        startActivity(h);
        // finish();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_parse_json, menu);
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
