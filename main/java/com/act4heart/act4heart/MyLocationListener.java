package com.act4heart.act4heart;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MyLocationListener implements LocationListener {

    private boolean isTerminated;

    private Context context;

    protected LocationManager locationManager;
    private TextView locationText;

    public MyLocationListener(Context context, TextView _locationText){
        this.context = context;

        //Save the link to the text we want to change
        locationText = _locationText;
        startService();
    }

    //When called, we update the textview in the view that wants a streetname.
    @Override
    public void onLocationChanged(Location location) {

        //If we have sent a link to a text
        if(locationText != null){
            Geocoder geocoder = new Geocoder(this.context, Locale.getDefault());

            //Gets the latitude and longitude from our new location
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            try {

                //Get geolocation information
                List<Address> currentStreet = geocoder.getFromLocation(latitude,longitude, 1);
                String _Location = null;

                //If geolocation is found, get the address and writes to the text
                if(null!=currentStreet&&currentStreet.size() > 0) {
                    _Location = currentStreet.get(0).getAddressLine(0);
                    locationText.setText(_Location);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void terminateService(){
        locationManager.removeUpdates(this);
        isTerminated = true;
    }

    public void startService(){
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);

        isTerminated = false;
    }

    public boolean isTerminated(){
        return isTerminated;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    public void onProviderEnabled(String s) {

    }

    public void onProviderDisabled(String s) {

    }


}