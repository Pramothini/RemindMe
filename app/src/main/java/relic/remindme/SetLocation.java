package relic.remindme;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import entities.ListLocation;

/**
 * Created by srish on 7/18/15.
 */
public class SetLocation extends FragmentActivity implements LocationListener{

    GoogleMap mMap;
    private static final int GPS_ERRORDIALOG_REQUEST = 901;
    private double CURRENT_LAT = 37.411180;
    private double CURRENT_LNG = -122.059603;
    private static final float DEFAULT_ZOOM = 15;

    protected LocationManager locationManager;
    protected LocationListener locationListener;

    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (servicesOK()) {
            setContentView(R.layout.controller_setlocation);

            if (initMap()) {
                mMap.setMyLocationEnabled(true);

                Toast.makeText(this, "Ready", Toast.LENGTH_SHORT).show();

                LocationManager manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
                LocationListener listener = new LocationListener() {


                    @Override
                    public void onLocationChanged(Location location) {
                        location.getLatitude();
                        location.getLongitude();
                        SetLocation.this.CURRENT_LAT = location.getLatitude();
                        SetLocation.this.CURRENT_LNG = location.getLongitude();

                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {
                     /* toast message tells if GPS was enabled */
                        Toast.makeText(getApplicationContext(), "GPS Enabled", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                          /* toast message tells if GPS was disabled */
                        Toast.makeText(getApplicationContext(), "GPS Disabled", Toast.LENGTH_SHORT).show();


                    }

                };

                manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);

                gotoLocation(SetLocation.this.CURRENT_LAT, SetLocation.this.CURRENT_LNG, DEFAULT_ZOOM);
            } else {
                Toast.makeText(this, "Map not available", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public boolean servicesOK() {
        int isAvailable  = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if(isAvailable == ConnectionResult.SUCCESS) {
            return true;
        }
        else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)) {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable, this, GPS_ERRORDIALOG_REQUEST );
            dialog.show();
        }
        else {
            Toast.makeText(this, "Can't connect to google play services", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    private boolean initMap() {
        if(mMap == null) {
            SupportMapFragment frag_map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mMap = frag_map.getMap();
        }

        return (mMap!=null);
    }

    private void gotoLocation(double lat, double lng, float zoom) {

        LatLng ll = new LatLng(lat,lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mMap.moveCamera(update);

    }

    public void geoLocate(View v) throws IOException {
        hideSoftKeyboard(v);
        EditText et = (EditText) findViewById(R.id.editText1);
        String location = et.getText().toString();
        if(location.length()== 0) {
            Toast.makeText(this,"Please Enter a Location", Toast.LENGTH_LONG).show();
            return;
        }
        Geocoder gc = new Geocoder(this);
        List<Address> list = gc.getFromLocationName(location, 1);
        Address add = list.get(0);

        String Locality = add.getLocality();
        Toast.makeText(this,Locality, Toast.LENGTH_LONG).show();

        double latude = add.getLatitude();
        double lngtude = add.getLongitude();
        float zoom = 15;

     //   ListLocation item = new ListLocation(latude,lngtude, )

        gotoLocation(latude, lngtude, zoom);

        if(marker!=null)
        {
            marker.remove();
        }
        MarkerOptions options = new MarkerOptions()
                .title(Locality)
                .position(new LatLng(latude,lngtude));
        marker = mMap.addMarker(options);
    }

    private void hideSoftKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    @Override
    public void onLocationChanged(Location location) {
        location.getLatitude();
        location.getLongitude();
        SetLocation.this.CURRENT_LAT = location.getLatitude();
        SetLocation.this.CURRENT_LNG = location.getLongitude();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
    /* toast message tells if GPS was enabled */
        Toast.makeText(getApplicationContext(), "GPS Enabled", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
         /* toast message tells if GPS was disabled */
        Toast.makeText(getApplicationContext(), "GPS Disabled", Toast.LENGTH_SHORT).show();

    }
}

