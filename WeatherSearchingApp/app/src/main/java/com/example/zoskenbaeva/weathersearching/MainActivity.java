package com.example.zoskenbaeva.weathersearching;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

import static com.example.zoskenbaeva.weathersearching.GMAPProperties.*;
import static com.example.zoskenbaeva.weathersearching.OWMProperties.*;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.weather_info) TextView weather_info;
    @BindView(R.id.auLocation) AutoCompleteTextView acCities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getWeather("Almaty");


    }

    @OnTextChanged(R.id.auLocation)
    protected void onLocationNameChanged(CharSequence text) {
        search_city();
    }

    private void search_city() {
        String autocomplete_request =
                gmap_autocomplete_request_base+gmap_language+
                        "&"+gmap_input+acCities.getText().toString().trim()
                        +"&"+gmap_types+"&"+gmap_API_key;
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

        try {
            String cities = "";
            JSONObject obj = new JSONObject(result);
            JSONArray predictions = obj.getJSONArray("predictions");
            List<String> locs = new ArrayList<String>();
            for(int i=0; i < predictions.length(); i++){
                JSONObject pred = predictions.getJSONObject(i);
                String l = pred.getString("description");
                //cities += pred.getString("description");
                locs.add(l);
            }
            weather_info.setText(locs.toString());
            setAutoComplete(locs);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void setAutoComplete(List<String> locs) {
        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                locs);
        adapter.notifyDataSetChanged();
        acCities.setAdapter(adapter);

    }
    private void getWeather(String city){
        String owm_request = owm_r_base
                + openweathermap_API_Key + "&" + owm_city_ + city;
        Ion.with(this)
                .load(owm_request)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        weather_info.setText(result);
                    }
                });


    }

}
