package com.example.asiff.nishchinto;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ThirdActivity extends ListActivity {


    String Word;
    String city;
    String r;

    int mn;
    int yr;
    int dt;
    SQLiteDatabase myDBs;
    String TableName = "myTables";
    ArrayList<String> results = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        setTitle("View Saved Location");

        try {
            myDBs = openOrCreateDatabase("agps_db", MODE_PRIVATE, null);

            Cursor c = myDBs.rawQuery("SELECT location ,cityname FROM " + TableName   , null);


            if (c != null) {

                c.moveToFirst();

                do {

                    Word = c.getString(c.getColumnIndex("location"));
                    //city= c.getString(c.getColumnIndex("cityname"));
                    //  yr=c.getInt(c.getColumnIndex("year"));
                    // dt=c.getInt(c.getColumnIndex("date"));
                    // results.add("" + Word+" "+mn+" "+yr+" "+dt );
                    Word = c.getString(c.getColumnIndex("location"));
                    //r=c.getString(c.getColumnIndex("rimition"));
                     results.add("" + Word );
                    //results.add("" + Word+": "+mn );
                }while(c.moveToNext());
            }
            //this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,results));



            this.setListAdapter(new ArrayAdapter<String>(this, R.layout.customlist, android.R.id.text1, results));

           // TextView tv=(TextView) findViewById(R.id.text1);

        }

        catch(Exception ex){
            Toast.makeText(getBaseContext(), "No Previous Location is saved",
                    Toast.LENGTH_LONG).show();
            //Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();

        }
        myDBs.close();
    }
    @Override
    protected void onListItemClick(ListView l, View v, final int position, long id)


    {


        ///////////////////////new///////////////////////////////



        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Delete")
                .setMessage("Are you sure you want to Delete?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String item = (String) getListAdapter().getItem(position);
                       // Toast.makeText(this, item + " is deleted", Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), item +" is Deleted", Toast.LENGTH_SHORT).show();
                        //Log.d("x",""+position);
                        SQLiteDatabase db;
                        db = openOrCreateDatabase("agps_db", MODE_PRIVATE, null);
                        //Log.d("x", "before delete");
                        db.execSQL("delete from myTables where location = '"+item+"';");
                        //Log.d("x", "after delete");
                        //startActivity(new Intent(Third.this,Main.class));
                        db.close();
                        finish();


                    }

                })
                .setNegativeButton("No", null)
                .show();






        ////////////////////////////////////////////////////

       /* String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " is deleted", Toast.LENGTH_LONG).show();
        //Log.d("x",""+position);
        SQLiteDatabase db;
        db = openOrCreateDatabase("agps_db", MODE_PRIVATE, null);
        //Log.d("x", "before delete");
        db.execSQL("delete from myTable where location = '"+item+"';");
        //Log.d("x", "after delete");
        //startActivity(new Intent(Third.this,Main.class));
        db.close();
        finish();
        */

        /////////////////////////////////////////////////////////////////////////
    }
}

