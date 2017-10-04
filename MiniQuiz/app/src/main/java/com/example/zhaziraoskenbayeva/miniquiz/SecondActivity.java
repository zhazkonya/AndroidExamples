package com.example.zhaziraoskenbayeva.miniquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();

        TextView t1 = (TextView) findViewById(R.id.txtViewAnimal1);
        TextView t2 = (TextView) findViewById(R.id.txtViewAnimal2);

        t1.setText(intent.getStringExtra("animal1"));
        t2.setText(intent.getStringExtra("animal2"));

    }

    public void bnBack(View view) {
        Intent intent = new Intent();
        intent.putExtra("message", "we are back");
        setResult(RESULT_OK, intent);
        finish();
    }
}
