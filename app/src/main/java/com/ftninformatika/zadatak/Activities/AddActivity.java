package com.ftninformatika.zadatak.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ftninformatika.zadatak.R;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String data = getIntent().getExtras().getString("Item", "");

        super.onCreate(savedInstanceState);
        if (data.equals("Jelo")) {
            setContentView(R.layout.activity_add_jelo);
        } else {
            setContentView(R.layout.activity_add_kategorija);
        }
    }
}