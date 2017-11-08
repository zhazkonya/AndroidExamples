package com.example.zhaziraoskenbayeva.broadcastexample;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String BROADCAST_DW =
            "com.example.zhaziraoskenbayeva.servicesexample.downloadintentservice";
    private static final int READ_EXTERNAL_STORAGE_PERMISSION_CODE = 123;
    ImageView ivCat;
    TextView tvCatUri;
    private static final String ACTION_DONWLOAD = "download";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivCat = (ImageView) findViewById(R.id.catIV);
        tvCatUri = (TextView) findViewById(R.id.tvCatUri);

        checkPermission();

        TelephonyManager telephonyManager = (TelephonyManager)
                this.getSystemService(Context.TELEPHONY_SERVICE);
        String phoneNumber = telephonyManager.getLine1Number();
        Log.d("cats", "Line1 number # " + phoneNumber);
        String portNumber = phoneNumber.substring(phoneNumber.length() - 4, phoneNumber.length());
        Log.d("cats", "Line1 number # " + portNumber);

        //"android.provider.Telephony.SMS_RECEIVED"

        registerReceiver(new DownloadReceiver(), new IntentFilter(BROADCAST_DW));

        try{
            if(getIntent() != null){
                String file = getIntent().getStringExtra("file");
                tvCatUri.setText(file);
                Ion.with(this)
                        .load(new File(file))
                        .intoImageView(ivCat);
            }
        }catch(Exception e){

        }
    }

    private void checkPermission() {
        if(ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_DENIED){

            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    234);

        }
    }

    public class DownloadReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("cats", intent.getStringExtra("filepath"));

            createNotification(intent.getStringExtra("filepath"));
        }
    }

    private void createNotification(String filepath) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("file", filepath);
        PendingIntent pending = PendingIntent.getActivity(
                this, (int) System.currentTimeMillis(), intent, 0);

        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.cat_ico)
                .setContentTitle("New cat image!")
                .setContentText(filepath)
                .setLargeIcon(BitmapFactory
                        .decodeResource(getResources(), R.drawable.open))
                .setAutoCancel(true)
                .setContentIntent(pending);

        Notification notify = builder.build();
        NotificationManager manager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify((int) System.currentTimeMillis(), notify);

    }


    private void handleDownload(String filename) {
        //Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("file", filename);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        //intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        /*PendingIntent pending = PendingIntent.getActivity(this, (int) System.currentTimeMillis(),
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.cat_ico)
                .setContentTitle("New Cat image")
                .setContentText(filename)
                .setAutoCancel(true)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.open))
                .setContentIntent(pending)
                ;

        Notification notify = builder.build();
        NotificationManager manager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify((int) System.currentTimeMillis() ,notify);*/


        //String file = getIntent().getStringExtra("file");
        /*tvCatUri.setText(filename);
        Ion.with(this)
                .load(new File(filename))
                .intoImageView(ivCat);*/
    }
}
