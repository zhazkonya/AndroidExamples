package com.example.zhaziraoskenbayeva.servicesexample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button bnStDw;
    GridView gvCats;
    private static final int READ_EXTERNAL_STORAGE_PERMISSION_CODE = 1198;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gvCats = (GridView) findViewById(R.id.glCats);
        gvCats.setAdapter(new ImageAdapter(this));

        gvCats.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), DownloadIntentService.class);
                intent.setAction(DownloadIntentService.ACTION_DOWNLOAD);
                intent.putExtra(DownloadIntentService.EXTRA_URL,
                        ((ImageView) view).getTag().toString());
                intent.putExtra(DownloadIntentService.EXTRA_FILENAME, "cat"+position+".png");
                startService(intent);
            }
        });
    }

    private boolean checkPermission() {

        /*TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
            String phoneNumber = telephonyManager.getLine1Number();
            Log.d("cats", "Line1 number # " + phoneNumber);
            String portNumber = phoneNumber.substring(phoneNumber.length() - 4, phoneNumber.length());
            Log.d("cats", "Line1 number # " + portNumber);*/

        if(ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    READ_EXTERNAL_STORAGE_PERMISSION_CODE);

        }
        if(ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    READ_EXTERNAL_STORAGE_PERMISSION_CODE);
        }
        if(ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_SMS) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_SMS},
                    READ_EXTERNAL_STORAGE_PERMISSION_CODE);
        }
        return true;
    }


}
