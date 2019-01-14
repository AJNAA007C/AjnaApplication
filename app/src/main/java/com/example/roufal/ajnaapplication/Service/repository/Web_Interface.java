package com.example.roufal.ajnaapplication.Service.repository;

import com.example.roufal.ajnaapplication.Service.model.album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by az-sys on 19/8/17.
 */

public interface Web_Interface {

    @GET(" ")
    public Call<List<album>> getPhotos();

    @GET("gallery/")
    public Call<List<album>> getGallery();
}
