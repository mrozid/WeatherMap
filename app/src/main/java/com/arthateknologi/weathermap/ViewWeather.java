package com.arthateknologi.weathermap;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arthateknologi.weathermap.api.WheatherApi;
import com.arthateknologi.weathermap.lib.GPSTracker;
import com.arthateknologi.weathermap.model.Mweather;
import com.arthateknologi.weathermap.model.Weather;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ViewWeather extends AppCompatActivity {
    public static int TYPE_WIFI = 1, TYPE_MOBILE = 2, TYPE_NOT_CONNECTED = 0;
    ImageView imgCuaa;
    String BASE_URL = "http://api.openweathermap.org";
    GPSTracker gps;
    TextView textKotaNegara, txtSuhu, txtCuaca, txtWaktu, txtViewWind, txtCloud, txtPresure, txtHumidity, txtSunrise, txtSunset, txtGeoCord;
    View mProgressView, conten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_weather);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        imgCuaa = (ImageView) findViewById(R.id.imgCuaca);
        textKotaNegara = (TextView) findViewById(R.id.textKotaNegara);
        txtSuhu = (TextView) findViewById(R.id.txtSuhu);
        txtCuaca = (TextView) findViewById(R.id.txtCuaca);
        txtWaktu = (TextView) findViewById(R.id.txtWaktu);
        txtViewWind = (TextView) findViewById(R.id.txtViewWind);
        txtCloud = (TextView) findViewById(R.id.txtCloud);
        txtPresure = (TextView) findViewById(R.id.txtPresure);
        txtHumidity = (TextView) findViewById(R.id.txtHumidity);
        txtSunrise = (TextView) findViewById(R.id.txtSunrise);
        txtSunset = (TextView) findViewById(R.id.txtSunset);
        txtGeoCord = (TextView) findViewById(R.id.txtGeoCord);
        mProgressView = findViewById(R.id.login_progress);
        conten = findViewById(R.id.conten);
        viewProgres(false);
        cekKoneksi(getApplicationContext());

        tampilData();

    }

    public void viewCuacaKOta(String kota) {

      /*  Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        WheatherApi apiWeather = retrofit.create(WheatherApi.class);
        Call call = apiWeather.getDataKota(kota);
        call.enqueue(new Callback<Mweather>() {

            @Override
            public void onResponse(Response<Mweather> response, Retrofit retrofit) {
                Mweather cuaca = response.body();
                if (response.isSuccess()) {
                    viewIsi(cuaca);
                    viewProgres(false);
                } else {
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        try {
                            finish();
                            Toast.makeText(getApplicationContext(), "Gagal ambil data " + responseBody.string(),
                                    Toast.LENGTH_LONG).show();

                        } catch (IOException e) {
                            finish();
                            Toast.makeText(getApplicationContext(), "Gagal ambil data " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    } else {
                        finish();
                        Toast.makeText(getApplicationContext(), "Gagal ambil data ",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                finish();
                Toast.makeText(getApplicationContext(), "Gagal ambil data " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
                // Log.d("eror : ", t.getMessage());
            }
        });

    }

    public void viewCuacaLatling(String lat, String lon) {

      /*  Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        final WheatherApi apiWeather = retrofit.create(WheatherApi.class);
        Call call = apiWeather.getDataLatling(lat, lon);
        call.enqueue(new Callback<Mweather>() {

            @Override
            public void onResponse(Response<Mweather> response, Retrofit retrofit) {
                Mweather cuaca = response.body();
                if (response.isSuccess()) {

                    viewIsi(cuaca);
                    viewProgres(false);
                } else {
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        try {
                            finish();
                            Toast.makeText(getApplicationContext(), "Gagal ambil data " + responseBody.string(),
                                    Toast.LENGTH_LONG).show();

                        } catch (IOException e) {
                            finish();
                            Toast.makeText(getApplicationContext(), "Gagal ambil data " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    } else {
                        finish();
                        Toast.makeText(getApplicationContext(), "Gagal ambil data ",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                finish();
                Toast.makeText(getApplicationContext(), "Gagal ambil data " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    private void viewIsi(Mweather cuaca) {
        List<Weather> we = new ArrayList<Weather>();
        we = cuaca.getWeather();
        Weather wea = we.get(0);
        textKotaNegara.setText("City of " + cuaca.getName() + ", " + cuaca.getSys().getCountry());
        txtCuaca.setText(wea.getMain());
        DecimalFormat df = new DecimalFormat("#.##");
        txtSuhu.setText(String.valueOf(df.format(cuaca.getMain().getTemp() - 273)));
        Calendar c1 = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        String strdate1 = sdf.format(c1.getTime());
        txtWaktu.setText("Get At " + strdate1);
        txtViewWind.setText("Speed : " + cuaca.getWind().getSpeed() + " m/s \n" + "Direction : " + cuaca.getWind().getDeg());
        txtCloud.setText(wea.getDescription());
        txtPresure.setText(cuaca.getMain().getPressure() + " Hpa");
        txtHumidity.setText(cuaca.getMain().getHumidity() + " %");
        txtSunrise.setText(convertUTC(cuaca.getSys().getSunrise()));
        txtSunset.setText(convertUTC(cuaca.getSys().getSunset()));
        txtGeoCord.setText("[" + cuaca.getCoord().getLon() + ", " + cuaca.getCoord().getLat() + "]");
        Glide.with(this)
                .load("http://openweathermap.org/img/w/" + wea.getIcon() + ".png")
                .into(imgCuaa);
    }

    public void tampilData() {
        viewProgres(true);
        String ret = null;
        Intent intent = getIntent();
        String kota = intent.getStringExtra("kota");
        if (kota == null) {
            gps = new GPSTracker(ViewWeather.this);
            if (gps.canGetLocation()) {

                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();
                viewCuacaLatling(String.valueOf(latitude), String.valueOf(longitude));
            } else {
                gps.showSettingsAlert();
            }
        } else {
            //kota = "2172797";
            viewCuacaKOta(kota);
        }


    }

    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    public void cekKoneksi(Context context) {
        int conn = getConnectivityStatus(context);
        if (conn == TYPE_NOT_CONNECTED) {
            Toast.makeText(getApplicationContext(), "Periksa koneksi internet anda",
                    Toast.LENGTH_LONG).show();
            finish();
        }

    }

    public void viewProgres(final boolean show) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            conten.setVisibility(show ? View.GONE : View.VISIBLE);
            conten.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    conten.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            conten.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    String convertUTC(String utc) {
        long unixTime = Long.parseLong(utc);
        long javaTime = unixTime * 1000L;
        Date date2 = new Date(javaTime);
        String time = new SimpleDateFormat("hh:mm").format(date2);
        return time;
    }

}
