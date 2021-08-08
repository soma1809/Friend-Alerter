package com.soma.wright;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Contact.options);
        ListView list = (ListView) findViewById(R.id.list_options);
        list.setAdapter(listAdapter);

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast toast=Toast.makeText(MainActivity.this,Integer.toString(position),Toast.LENGTH_SHORT);
                toast.show();
                if(position==0) {
                    Intent intent = new Intent(MainActivity.this, Wim.class);
                    startActivity(intent);
                }
                else if(position==2){
                    Intent intent=new Intent(MainActivity.this,Wlist.class);
                    startActivity(intent);
                }
            }

        };
    list.setOnItemClickListener(listener);
    }


}

