package com.example.zhaziraoskenbayeva.miniquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class AnimalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);

        TextView txtView = (TextView) findViewById(R.id.animalTxtView);


    }

    public void bnBack(View view) {
    }
}
