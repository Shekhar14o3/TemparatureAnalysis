package com.temparatureanalysis.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.temparatureanalysis.R;

/**
 * Created by Techno Blogger on 25/1/17.
 */
public class ActivityDetails extends Activity {

    private TextView city, temparature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_details);


        city = (TextView) findViewById(R.id.city_name);
        temparature = (TextView) findViewById(R.id.temparature_value);

        if (getIntent() != null) {

            String cityCame = getIntent().getStringExtra("city");
            String temparatureCame = getIntent().getStringExtra("temparature");

            city.setText(cityCame);
            temparature.setText(temparatureCame);

        }
    }
}
