package com.example.roufal.ajnaapplication.Service.repository;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by az-sys on 19/8/17.
 */

public class ResponseHandler implements Callback<JsonArray> {

    private Context context;
    private ResponseCallback web_interface;
    private ProgressDialog progressDialog;



    public ResponseHandler(Context context, ResponseCallback web_interface, Call<JsonArray> JsonArrayCall) {
        this.context = context;
        this.web_interface = web_interface;

        if(!context.getClass().getName().contains("LivetrackActivity")) {
            progressDialog = new ProgressDialog(context);
            if(!progressDialog.isShowing()) {
                progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("Loading.....");
                progressDialog.setCancelable(false);
                try {
                    if (!progressDialog.isShowing())
                        progressDialog.show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }



    @Override
    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        switch (response.code()) {
            case Responsecode.ok:
                progressDialog.dismiss();
                if (web_interface != null) {
                    if (response.body() != null) {
                        web_interface.getResponse(response.code(), response.body());
                    }
                }
                break;
            case Responsecode.server_error:
                progressDialog.dismiss();
                web_interface.getError(call);
                break;
            case Responsecode.notfound:
                progressDialog.dismiss();
                web_interface.getError(call);
                break;
        }

    }

    @Override
    public void onFailure(Call<JsonArray> call, Throwable t) {

        progressDialog.dismiss();

        if (!Retrofit_Helper.isNetworkAvailable(context)) {
            web_interface.getError(call);
        } else {
            web_interface.getError(call);
        }

    }




}
