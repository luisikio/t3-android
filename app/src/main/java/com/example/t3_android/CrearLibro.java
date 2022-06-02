package com.example.t3_android;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.t3_android.entitites.Libro;
import com.example.t3_android.services.servicesWeb;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrearLibro extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1000;
    static final int REQUEST_PICK_IMAGE = 1001;

    static final int REQUEST_CAMERA_PERMISSION = 100;
    ImageView imgA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_libro);


        Button btn = findViewById(R.id.btnCrear);
        EditText etTitulo=findViewById(R.id.etTitulo);
        EditText etresumen=findViewById(R.id.etResumen);
        EditText etAutor=findViewById(R.id.etAutor);
        EditText etFecha= findViewById(R.id.etDate);
        EditText etTienda1=findViewById(R.id.etTienda1);
        EditText etTienda2=findViewById(R.id.edTienda2);
        EditText etTienda3=findViewById(R.id.etTienda3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new  Retrofit.Builder()
                        .baseUrl("https://6284e8f8a48bd3c40b77c373.mockapi.io/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                servicesWeb services = retrofit.create(servicesWeb.class);

                Libro libro = new Libro();

                libro.titulo=String.valueOf(etTitulo.getText());
                libro.resumen=String.valueOf(etresumen.getText());
                libro.autor=String.valueOf(etAutor.getText());
                libro.fecha=String.valueOf(etFecha.getText());
                libro.tienda1=String.valueOf(etTienda1.getText());
                libro.tienda2=String.valueOf(etTienda2.getText());
                libro.tienda3=String.valueOf(etTienda3.getText());

                services.create(libro);
                Call<Libro> call = services.create(libro);

                call.enqueue(new Callback<Libro>() {
                    @Override
                    public void onResponse(Call<Libro> call, Response<Libro> response) {

                    }

                    @Override
                    public void onFailure(Call<Libro> call, Throwable t) {

                    }
                });


            }
        });

        Button btnFoto=findViewById(R.id.btnTomarFoto);

        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    takePhoto();
                } else {
                    requestPermissions(new String[] {Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                }
            }
        });

        Button btnGallery = findViewById(R.id.btnGallery);
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

    }

    private void takePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }
    }
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_PICK_IMAGE);
    }
}