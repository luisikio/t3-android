package com.example.t3_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.t3_android.adapters.libroAdapter;
import com.example.t3_android.entitites.Libro;
import com.example.t3_android.services.servicesWeb;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class listarLibro extends AppCompatActivity {

    public RecyclerView rv;
    List<Libro> libros= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_libro);

        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl("https://6284e8f8a48bd3c40b77c373.mockapi.io/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicesWeb services = retrofit.create(servicesWeb.class);
        Call<List<Libro>> call=services.getContacts();

        call.enqueue(new Callback<List<Libro>>() {
            @Override
            public void onResponse(Call<List<Libro>> call, Response<List<Libro>> response) {
                if (!response.isSuccessful()){
                    Log.e("asd1234", "error");
                }else{
                    Log.i("asdasd12312", new Gson().toJson(response.body()));
                    Log.i("asd32", "Respuesta correcta");

                    libros=response.body();

                    libroAdapter adapter=new libroAdapter(libros);


                    rv= findViewById(R.id.rvLibro);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv.setHasFixedSize(true);
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Libro>> call, Throwable t) {
                Log.e("asd1234", "no hay conexion");
            }
        });
    }
}