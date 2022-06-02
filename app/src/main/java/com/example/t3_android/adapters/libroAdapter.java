package com.example.t3_android.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.t3_android.R;
import com.example.t3_android.entitites.Libro;
import com.example.t3_android.minListLibros;
import com.example.t3_android.services.servicesWeb;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class libroAdapter extends RecyclerView.Adapter<libroAdapter.libroViewHolder> {
    List<Libro> libros;

    public libroAdapter(List<Libro> libros) {
        this.libros = libros;
    }

    @NonNull
    @Override
    public libroAdapter.libroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_libro,parent,false);
        libroAdapter.libroViewHolder vh= new libroAdapter.libroViewHolder(view);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull libroAdapter.libroViewHolder holder, int position) {
        View vw=holder.itemView;

        Libro libro = libros.get(position);
        TextView itemTitulo=holder.itemView.findViewById(R.id.tvTitulo);
        ImageView itemImg=holder.itemView.findViewById(R.id.ivAvatar);

        itemTitulo.setText(libro.titulo);

        String url="https://i.ytimg.com/vi/1roy4o4tqQM/maxresdefault.jpg";
        Picasso.get().load(url).into(itemImg);

        vw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new  Retrofit.Builder()
                        .baseUrl("https://6284e8f8a48bd3c40b77c373.mockapi.io/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                servicesWeb services = retrofit.create(servicesWeb.class);
                Call<Libro> call=services.findContact(libro.id);

                call.enqueue(new Callback<Libro>() {
                    @Override
                    public void onResponse(Call<Libro> call, Response<Libro> response) {
                        if (!response.isSuccessful()){
                            Log.e("asd1234", "error");
                        }else {

                            Log.i("asdasd12312", new Gson().toJson(response.body()));
                            Log.i("asd32", "Respuesta correcta por id");

                            Intent intent= new Intent(vw.getContext(), minListLibros.class);


                            Log.i("asd32", "Respuesta correcta por id------------ ");
//                            intent.putExtra("img",pokemon.img);
//                            intent.putExtra("name",pokemon.name);
//                            intent.putExtra("number",pokemon.number);
//                            intent.putExtra("region",pokemon.region);
//                            intent.putExtra("tipo",pokemon.tipo);
                            String pokemonJson = new Gson().toJson(libro);
                            intent.putExtra("Pokemon",pokemonJson);

                            vw.getContext().startActivity(intent);

                        }
                    }

                    @Override
                    public void onFailure(Call<Libro> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return libros.size();
    }

    public class libroViewHolder extends RecyclerView.ViewHolder {
        public libroViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
