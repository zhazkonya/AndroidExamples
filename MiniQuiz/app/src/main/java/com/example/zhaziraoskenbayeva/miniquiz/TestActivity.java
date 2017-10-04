package com.example.zhaziraoskenbayeva.miniquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        Toast.makeText(getApplicationContext(), name,
                Toast.LENGTH_SHORT).show();
    }

    public void bnBack(View view) {
        setResult(RESULT_OK);
        finish();
    }
}
