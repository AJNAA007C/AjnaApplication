package com.example.roufal.ajnaapplication.view.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.roufal.ajnaapplication.R;
import com.example.roufal.ajnaapplication.Service.model.album;
import com.example.roufal.ajnaapplication.Service.repository.Retrofit_Helper;
import com.example.roufal.ajnaapplication.view.Adapter.AlbumAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
AlbumAdapter adapter;
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Call<List<album>> call = new Retrofit_Helper().getRetrofitBuilder().getPhotos();
        call.enqueue(new Callback<List<album>>() {
            @Override
            public void onResponse(Call<List<album>> call, Response<List<album>> response) {

                List<album> rs = response.body();
                Log.i("sadasda",response.body()+"");
                adapter=new AlbumAdapter(getApplicationContext(),rs);
                recyclerView=findViewById(R.id.recyclerView);
                LinearLayoutManager llm=new LinearLayoutManager(getApplicationContext());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(llm);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<album>> call, Throwable t) {

            }
        });

    }
}
