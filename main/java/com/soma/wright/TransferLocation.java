package com.soma.wright;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class TransferLocation extends AsyncTask<String,Void,Void> {

    @Override
    protected Void doInBackground(String... loc) {
        try {
            Socket sock = new Socket("192.168.1.6", 8000);

                PrintWriter writer = new PrintWriter(sock.getOutputStream());
                writer.println(loc);
                writer.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
