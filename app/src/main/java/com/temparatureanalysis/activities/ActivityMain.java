package com.temparatureanalysis.activities;

/**
 * Created by Techno Blogger on 25/1/17.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.temparatureanalysis.constants.AppConstants;
import com.temparatureanalysis.parser.HttpWebservice;
import com.temparatureanalysis.R;
import com.temparatureanalysis.adapter.BasicListAdapter;
import com.temparatureanalysis.pojo.Temperature;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityMain extends AppCompatActivity {

    private RecyclerView basic_list;
    ArrayList<Temperature> temperatureArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        basic_list = (RecyclerView) findViewById(R.id.basic_list_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        basic_list.setLayoutManager(linearLayoutManager);


        callWebservice();
    }

    private ArrayList<Temperature> callWebservice() {

        final String url = AppConstants.JSON_PARAMS.URL;

        temperatureArrayList = new ArrayList<Temperature>();

        new HttpWebservice(ActivityMain.this, "Fetching...", new HttpWebservice.DataFetched() {
            @Override
            public void onDataRecieved(JSONObject jsonObject) {


                try {

                    JSONArray jsonArray = jsonObject.getJSONArray(AppConstants.JSON_PARAMS.PARAM_LIST);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject itemObject = jsonArray.getJSONObject(i);
                        String cityName = itemObject.getString(AppConstants.JSON_PARAMS.PARAM_NAME);

                        JSONObject mainObject = itemObject.getJSONObject(AppConstants.JSON_PARAMS.PARAM_MAIN);

                        String temparture = mainObject.getString(AppConstants.JSON_PARAMS.PARAM_TEMP);

                        temperatureArrayList.add(new Temperature(cityName, temparture));

                    }

                    BasicListAdapter basicListAdapter = new BasicListAdapter(ActivityMain.this, temperatureArrayList);

                    basic_list.setAdapter(basicListAdapter);


                } catch (Exception e) {

                }

            }
        }).execute(url);

        return temperatureArrayList;
    }
}
