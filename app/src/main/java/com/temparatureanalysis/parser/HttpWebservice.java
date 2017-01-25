package com.temparatureanalysis.parser;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Techno Blogger on 25/1/17.
 */
public class HttpWebservice extends AsyncTask<String, Void, JSONObject> {

    private ProgressDialog progressDialog;
    private Context context;
    private String loadingTitle;
    private DataFetched dataFeched;


    public HttpWebservice(Context context, String loadingTitle, DataFetched dateFeched) {
        this.context = context;
        this.loadingTitle = loadingTitle;
        this.dataFeched = dateFeched;
        progressDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(loadingTitle);
        progressDialog.show();
    }


    @Override
    protected JSONObject doInBackground(String... params) {
        return getJSONResponse(params[0]);
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        progressDialog.dismiss();
        dataFeched.onDataRecieved(jsonObject);

    }


    public JSONObject getJSONResponse(String urlString) {
        System.out.println("url" + urlString);
        HttpURLConnection urlConnection = null;
        URL url = null;
        JSONObject object = null;
        InputStream inStream = null;
        try {
            url = new URL(urlString.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.connect();
            inStream = urlConnection.getInputStream();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));
            String temp, response = "";
            while ((temp = bReader.readLine()) != null) {
                response += temp;
            }
            object = (JSONObject) new JSONTokener(response).nextValue();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inStream != null) {
                try {
                    // this will close the bReader as well
                    inStream.close();
                } catch (IOException ignored) {
                }
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        System.out.print("result" + object.toString());

        return object;
    }

    public interface DataFetched {
        void onDataRecieved(JSONObject jsonObject);
    }
}
