package com.soma.wright;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

public class LocationService extends Service {
    private LocationListener locationListener;
    private LocationManager locationManager;
    private static Location lastLocation=null;
    public static String latitude=null;
    public static final String PERMISSION= Manifest.permission.ACCESS_FINE_LOCATION;
    private final IBinder binder=new LocationBinder();
    public class LocationBinder extends Binder {
        LocationService getLocation(){
            return LocationService.this;
        }
    }
    public LocationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                if(lastLocation==null){
                    lastLocation=location;
                    System.out.println(lastLocation.getLatitude());
                    latitude=Double.toString(lastLocation.getLatitude());
                }
            }
        };
        locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if(ContextCompat.checkSelfPermission(this,PERMISSION)== PackageManager.PERMISSION_GRANTED){
            String provider=locationManager.getBestProvider(new Criteria(),true);
            if(provider!=null){
                locationManager.requestLocationUpdates(provider,5000,5,locationListener);
            }
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(locationManager!=null && locationListener!=null){
            if(ContextCompat.checkSelfPermission(this,PERMISSION)==PackageManager.PERMISSION_GRANTED){
                locationManager.removeUpdates(locationListener);
            }
            locationManager=null;
            locationListener=null;
        }
    }
}