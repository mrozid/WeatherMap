package com.arthateknologi.weathermap;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnInputLokasi, btnLokasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        btnInputLokasi = (Button) findViewById(R.id.btnInputLokasi);
        btnLokasi = (Button) findViewById(R.id.btnLokasi);
        btnInputLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InputKota.class);
                startActivity(intent);
            }
        });
        btnLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewWeather.class);
                startActivity(intent);
            }
        });
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Anda Yakin Mau Keluar")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                    }
                })
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("Konfirmasi Keluar")
                .setNegativeButton("No", null)
                .show();
    }
}
