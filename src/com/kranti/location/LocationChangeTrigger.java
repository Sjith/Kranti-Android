package com.kranti.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;


public class LocationChangeTrigger {

    private LocationManager locationManager;
    private LocationHelper locationHelper;
    private Location lastKnownLocation;
    private boolean gpsSupported = false;
    private boolean networkSupported = false;


    public LocationChangeTrigger(Context context) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        this.locationHelper = new LocationHelper(locationManager);

    }

    public void fetchLatestLocation() {

        gpsSupported = locationHelper.isGPSSupported();
        networkSupported = locationHelper.isNetworkSupported();

        //We have no way to query the locations as nothing is supported
        if (!networkSupported && !gpsSupported) {
            Log.i("Location Update", "No location information available!");
            return;
        }

        if (gpsSupported) {
            LocationListener locListener = new GPSLocationListener();
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
        }

        if (networkSupported) {
            LocationListener locListener = new NetworkLocationListener();
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locListener);
        }

    }

    public String getLocation() {
        lastKnownLocation = locationHelper.getBestLastKnownLocation();
        if (lastKnownLocation != null)
            return lastKnownLocation.getLatitude() + "," + lastKnownLocation.getLongitude();
        else
            return "0,0";
    }
}


