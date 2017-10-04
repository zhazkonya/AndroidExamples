package com.example.zhaziraoskenbayeva.restexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView conRecView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conRecView = (RecyclerView) findViewById(R.id.my_recycler_view);
        conRecView.setHasFixedSize(true);
        GridLayoutManager glm = new GridLayoutManager(this, 1);
        conRecView.setLayoutManager(glm);


        Ion.with(this)
                .load("https://api.androidhive.info/contacts/")
                .asString()
                .setCallback(new FutureCallback<String>(){
                    @Override
                    public void onCompleted(Exception e, String result) {
                        try {
                            processData(result);
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
    }

    private void processData(String result) throws JSONException {
        JSONObject conObj = new JSONObject(result);
        JSONArray conArray = conObj.getJSONArray("contacts");
        List<Contact> cList = new ArrayList<>();

        for(int i =0 ; i < conArray.length(); i++){
            JSONObject con = conArray.getJSONObject(i);
            String id = con.getString("id");
            String name = con.getString("name");
            String email = con.getString("email");
            String address = con.getString("address");
            String gender = con.getString("gender");
            JSONObject phone = con.getJSONObject("phone");
            String mobile = phone.getString("mobile");
            String home = phone.getString("home");
            String office = phone.getString("office");

            Contact c =
                    new Contact(id, name, email,
                            address, gender, mobile, home, office);
            cList.add(c);
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        }

        showContact(cList);

    }

    private void showContact(List<Contact> cList) {
        ContactsAdapter ca = new ContactsAdapter(cList);
        conRecView.setAdapter(ca);
    }


}
