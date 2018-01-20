package com.example.zoskenbaeva.weathersearching;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

import static com.example.zoskenbaeva.weathersearching.GMAPProperties.*;
import static com.example.zoskenbaeva.weathersearching.OWMProperties.*;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.etLocationName) EditText etLocationName;
    @BindView(R.id.lvLocationsAndWeatherInfo)
    RecyclerView lvLocationsAndWeatherInfo;
    List<WeatherInfo> wiList;
    final String TAG="WEATHER_PROJECT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        GridLayoutManager glm = new GridLayoutManager(this,1);
        lvLocationsAndWeatherInfo.setLayoutManager(glm);
    }

    @OnTextChanged(R.id.etLocationName)
    protected void onLocationNameChanged(CharSequence text) {
        if(etLocationName.getText().length() >= 2){
            wiList = new ArrayList<WeatherInfo>();
            search_city();
        }
    }

    private void search_city() {
        String autocomplete_request =
                GMAP_AUTOCOM_API_BASE + GMAP_AUTOCOM_PARAMS
                       + etLocationName.getText().toString().trim()
                       + "&"+ GMAP_KEY;
        Ion.with(this)
                .load(autocomplete_request)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        processData(result);
                    }
                });
    }

    private void processData(String result) {
        List<String> regionsList = parseGmapJSON(result);

        for(String p_id: regionsList){
            getPlaceDetails(p_id);
        }
    }

    private List<String> parseGmapJSON(String result) {
        List<String> locs = new ArrayList<String>();
        try {
            String cities = "";
            JSONObject obj = new JSONObject(result);
            JSONArray predictions = obj.getJSONArray("predictions");
            for(int i=0; i < predictions.length(); i++){
                JSONObject pred = predictions.getJSONObject(i);
                String l = pred.getString("description");
                String place_id = pred.getString("place_id");
                Log.d(TAG, l);
                locs.add(place_id);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return locs;
    }

    private void getPlaceDetails(String p_id) {
        String placedeatil_request =
                GMAP_PLACE_API_BASE+GMAP_KEY
                +"&"+GMAP_PLACE_PARAMS+p_id;

        new ProcessJSON().execute(placedeatil_request);

        /*Ion.with(this)
                .load(placedeatil_request)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        processResult(result);
                    }
                });*/

    }

    private void processResult(String result) {
        String[] details = parsePlaceDeatilJson(result);
        getWeatherFromOWM( details[1],  details[2]);
    }

    private String[] parsePlaceDeatilJson(String result) {
        String[] details = new String[3];
        try {
            JSONObject obj = new JSONObject(result);
            JSONObject placeobj = obj.getJSONObject("result");
            details[0] = placeobj.getString("formatted_address");
            JSONObject p_location = placeobj.getJSONObject("geometry").getJSONObject("location");
            details[1] = p_location.getString("lat");
            details[2] = p_location.getString("lng");
            Log.d(TAG, details[1]+""+details[2]);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return details;
    }

    private void getWeatherFromOWM(String lat, String lng) {
        String request = OWM_API_BASE+OWM_API_KEY
                + "&"+OWM_PARAMS_LAT + lat
                + "&"+OWM_PARAMS_LNG + lng;
        Ion.with(this)
                .load(request)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        processOWMResult(result);
                    }
                });
    }

    private void processOWMResult(String result) {
        WeatherInfo wi = parseOWMJson(result);
        wiList.add(wi);
        fillLocationsAndWeatherInfo();
    }

    private WeatherInfo parseOWMJson(String result) {
        WeatherInfo wi = new WeatherInfo();
        try {
            JSONObject obj = new JSONObject(result);
            JSONArray w_details = obj.getJSONArray("weather");
            for(int i=0; i < w_details.length();i++){
                JSONObject d = w_details.getJSONObject(i);
                wi.setDescription(d.getString("main"));
                wi.setRegionName(obj.getString("name"));
                wi.setIconId(d.getString("icon"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return wi;
    }

    public void fillLocationsAndWeatherInfo() {
        WeatherInfoAdapter wia = new WeatherInfoAdapter(wiList);
        lvLocationsAndWeatherInfo.setAdapter(wia);
    }

    private class ProcessJSON extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... strings){
            String stream = null;
            String urlString = strings[0];

            HTTPDataHandler hh = new HTTPDataHandler();
            stream = hh.GetHTTPData(urlString);

            // Return the data from specified url
            return stream;
        }

        protected void onPostExecute(String stream) {
            Log.d(TAG, stream);
            processResult(stream);

        }
    }

}
