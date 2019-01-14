package com.example.roufal.ajnaapplication.Service.repository;

import com.google.gson.JsonArray;

import retrofit2.Call;

/**
 * Created by az-sys on 19/8/17.
 */

public interface ResponseCallback {

    public void getResponse(int code, JsonArray jsonObject);

    public void getError(Call<JsonArray> call);
}
