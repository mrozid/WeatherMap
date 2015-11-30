package com.arthateknologi.weathermap;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.arthateknologi.weathermap.model.City;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class InputKota extends AppCompatActivity {
    Button btnproses;
    EditText input;
    private ArrayList<City> beanPostArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_kota);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        input = (EditText) findViewById(R.id.kota);

        btnproses = (Button) findViewById(R.id.btnProses);
        btnproses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String par = input.getText().toString();
                if (TextUtils.isEmpty(par)) {
                    input.setError("tidak boleh kosong");
                } else {
                    Intent intent = new Intent(getApplicationContext(), ViewWeather.class);
                    intent.putExtra("kota", par);
                    startActivity(intent);
                }
            }
        });

    }


}
