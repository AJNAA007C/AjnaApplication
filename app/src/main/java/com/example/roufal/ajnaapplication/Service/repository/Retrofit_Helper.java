package com.example.roufal.ajnaapplication.Service.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by az-sys on 19/8/17.
 */

public class Retrofit_Helper {

/************************  cloud test*********************************/

//onlistertest.ap-south-1.elasticbeanstalk.com/shop/api/
    //String base_url = "http://onlistertest.ap-south-1.elasticbeanstalk.com/api/";
    String base_url = "https://jsonplaceholder.typicode.com/photos/";

    public Retrofit_Helper() {
    }

    public Web_Interface getRetrofitBuilder() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(new BasicAuthInterceptor("onadminbusl", "onspls_12!@"))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Web_Interface web_interface = retrofit.create(Web_Interface.class);
        Log.i("url_base34", base_url );//+ "," + web_interface

        return web_interface;


    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
