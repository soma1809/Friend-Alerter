package com.soma.wright;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Wlist extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor cursor;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wlist);
        listView=(ListView)findViewById(R.id.wright_list);
        Intent intent=getIntent();
        new ShowWlist().execute();
    }


    public class ShowWlist extends AsyncTask<Void,Void,Void> {


        protected void onPreExecute(){

        }
        protected Void doInBackground(Void... voids){
            try {
                SQLiteOpenHelper wlistopenhelper = new AddNameHelper(Wlist.this);
                db = wlistopenhelper.getReadableDatabase();
                cursor = db.query("WRIGHT_LIST", new String[]{"_id","CONTACT"}, null, null, null, null, "CONTACT ASC");
                SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(Wlist.this, android.R.layout.simple_list_item_1, cursor, new String[]{"CONTACT"},
                        new int[]{android.R.id.text1}, 0);
                listView.setAdapter(cursorAdapter);
            }catch (SQLException sqlException){
                sqlException.printStackTrace();
            }
            return null;
        }

    }
    public void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}