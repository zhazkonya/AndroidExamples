package com.example.zhaziraoskenbayeva.a2danimo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //Smile smile = new Smile(this);
        //setContentView(smile);

        //Ball ballView = new Ball(this);
        //setContentView(ballView);

        Raindrop rView = new Raindrop(this);
        setContentView(rView);
    }
}
