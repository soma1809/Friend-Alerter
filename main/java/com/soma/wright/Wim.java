package com.soma.wright;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Wim extends AppCompatActivity {

    private LocationService locationService;
    private boolean bound=false;
    private String name;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            LocationService.LocationBinder locationBinder=(LocationService.LocationBinder)binder;
            locationService=locationBinder.getLocation();
            bound=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound=false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wim);
        Intent intent=getIntent();
        //sendLocation();
    }

    @Override
    protected void onStart(){
        super.onStart();
        Intent intent = new Intent(this,LocationService.class);
        bindService(intent,connection, Context.BIND_AUTO_CREATE);
        System.out.println(bound);
    }

    @Override
    protected void onStop(){
        super.onStop();
        if(bound){
            unbindService(connection);
            bound=false;
        }
    }

    public void sendLocation(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(bound && locationService!=null){
                    new TransferLocation().execute(LocationService.latitude);
                }
                handler.postDelayed(this,5000);
            }
        });
    }
/*
    public void AddToDatabase(View view){
        EditText Name=(EditText)findViewById(R.id.edittext);
        name=Name.getText().toString();
        new UpdateWrightDatabase().execute(name);
    }

    public class UpdateWrightDatabase extends AsyncTask<String,Void,Boolean> {

        private ContentValues nameValues;

        protected void onPreExecute(){
            nameValues=new ContentValues();
            nameValues.put("CONTACT", name);
        }
        @Override
        protected Boolean doInBackground(String... contact){
            SQLiteOpenHelper addcontacthelper=new AddNameHelper(Wim.this);
            try{
                SQLiteDatabase db=addcontacthelper.getWritableDatabase();
                db.insert("WRIGHT_LIST",null,nameValues);
                db.close();
                return true;
            }catch (SQLException exception){
                exception.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Boolean success){
            if(success){
                Toast toast=Toast.makeText(Wim.this,"Contact added",Toast.LENGTH_SHORT);
                toast.show();
                System.out.println("Contact added");
            }
            else{
                Toast toast=Toast.makeText(Wim.this,"Datababse unavailable",Toast.LENGTH_SHORT);
                toast.show();
                System.out.println("Database unavailable");
            }
        }

    }
*/
}