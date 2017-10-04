package com.example.zhaziraoskenbayeva.miniquiz;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements AnimalListFragment.OnFragmentInteractionListener{


    private static final int REQ_CODE_SEC_ACTIVTY = 5545;
    EditText ansText;
    TextView txtViewFile;
    ListView lView;
    ArrayAdapter<String> wAraay;
    ArrayAdapter<String> animalArray;
    public static final int REQ_CODE_ANIMAL_ACC = 4456;
    final String TAG = "Zhaz_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate");

        LinearLayout linearLayoutFlags = (LinearLayout) findViewById(R.id.linLayoutFlags);

        ImageView imgFlag = new ImageView(this);
        imgFlag.setImageResource(R.drawable.kz_flag);
        imgFlag.setAdjustViewBounds(true);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        TextView countryName = new TextView(this);
        countryName.setText("Kazakhstan");
        countryName.setTextSize(20);
        countryName.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
        countryName.setLayoutParams(params);

        linearLayoutFlags.addView(imgFlag);
        linearLayoutFlags.addView(countryName);
    }


    public void onCheck(View view) {
        if (ansText.getText().toString().trim().equals("4")) {
            Toast.makeText(getApplicationContext(), R.string.correctMes, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), R.string.incorrectMes, Toast.LENGTH_LONG).show();
        }
    }

    public void bnWrite(View view) {

    }

    public void bnRead(View view) {
        txtViewFile.setText("Hello World");
        //refresh();
    }

    public void bnNext(View view) {
        Intent intent = new Intent(
                this, SecondActivity.class);
        intent.putExtra("animal1", "Tiger");
        intent.putExtra("animal2", "Bear");
        //startActivity(intent);
        startActivityForResult(intent, REQ_CODE_SEC_ACTIVTY);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_CODE_SEC_ACTIVTY){
            String mess = data.getStringExtra("message");
            Toast.makeText(getApplicationContext(),
                    mess, Toast.LENGTH_LONG).show();
        }
    }

    private void refresh() {
        for(int i=0; i < lView.getChildCount(); i++){
            Toast.makeText(getApplicationContext(), String.valueOf(lView.getAdapter().getItem(i)), Toast.LENGTH_LONG).show();
            lView.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.white));
            //lView.setSelector(R.color.white);
        }
    }


    public void flagImgOnClick(View view) {

        String flagName = getResources().getResourceEntryName(R.drawable.kz_flag);
        Toast.makeText(this, flagName, Toast.LENGTH_LONG).show();
    }

            public void imgFlagClick(View view) {
                String fileName = getResources().getResourceEntryName(R.drawable.kz_flag);
                Toast.makeText(this,fileName, Toast.LENGTH_LONG).show();
            }

    public void onFlagClick(View view) {
        String flag = getResources().getResourceEntryName(R.drawable.kz_flag);
        Toast.makeText(this,
                flag, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
