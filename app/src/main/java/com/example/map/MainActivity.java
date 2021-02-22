package com.example.map;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button getLocationBtn;
    TextView locationText;
    LocationManager locationManager;
    double latitude;
    double longtitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLocationBtn = (Button)findViewById(R.id.getLocationBtn);
        locationText = (TextView)findViewById(R.id.locationText);

        getLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });
    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
         //   locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this::onLocationChanged);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }


    public void onLocationChanged(Location location) {
        locationText.setText("Current Location: " + location.getLatitude() + ", " + location.getLongitude());
        setLocation2(location.getLatitude(), location.getLongitude());

    }
 //my
    private void setLocation2(double x, double y){
        latitude=x;
        longtitude=y;
    }

    public void onProviderDisabled(String provider) {
        Toast.makeText(MainActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }


    public void onStatusChanged(String provider, int status, Bundle extras) {

    }


    public void onProviderEnabled(String provider) {

    }


    public void getMap(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        // intent.setData(geoLocation);
        intent.setData(Uri.parse("geo:"+latitude+","+longtitude+'"'));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void sendEmail(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "velislava_n@abv.bg");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Your location");
        intent.putExtra(Intent.EXTRA_TEXT,getLocationText() );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

   private String getLocationText(){
        String x=latitude+ ", " + longtitude;
        return x;
   }

    public void getLocation(View view) {
    }
}