package com.example.t3_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.t3_android.entitites.Libro;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class minListLibros extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1000;
    static final int REQUEST_PICK_IMAGE = 1001;

    static final int REQUEST_CAMERA_PERMISSION = 100;
    ImageView imgA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_min_list_libros);

        String pokemonJson = getIntent().getStringExtra("Pokemon");
        Libro libro = new Gson().fromJson(pokemonJson,Libro.class);

        imgA=findViewById(R.id.imgAvatar);
        TextView tvTitulo=findViewById(R.id.tvTitulomin);
        TextView tvmResumen=findViewById(R.id.tvResumenmin);
        TextView tvmAutor=findViewById(R.id.tvAutormin);
        TextView tvFecha=findViewById(R.id.tvFechamin);


        String url="https://i.ytimg.com/vi/1roy4o4tqQM/maxresdefault.jpg";
        Picasso.get().load(url).into(imgA);

        tvTitulo.setText(libro.titulo);
        tvmResumen.setText(libro.resumen);
        tvmAutor.setText(libro.autor);
        tvFecha.setText(libro.fecha);
//        tvLatitude.setText(toString().valueOf(pokemon.latitude));
//        tvLongitude.setText(toString().valueOf(pokemon.longitude));

        Button btn1T=findViewById(R.id.btnTienda1);
        Button btn2T=findViewById(R.id.btnTienda2);
        Button btn3T=findViewById(R.id.btnTienda3);

        btn1T.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                intent.putExtra("tienda1",libro.tienda1);
                startActivity(intent);
            }
        });

        btn2T.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MapsActivity2.class);
                intent.putExtra("tienda2",libro.tienda2);
                startActivity(intent);
            }
        });
        btn3T.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MapsActivity3.class);
                intent.putExtra("tienda3",libro.tienda3);
                startActivity(intent);
            }
        });
    }
}