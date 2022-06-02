package com.example.t3_android.entitites;

public class Libro {
    public int id;
    public String titulo;
    public String resumen;
    public String autor;
    public String tienda1;
    public String tienda2;
    public String tienda3;
    public String fecha;
    public int img;

    public Libro() {
    }

    public Libro(int id, String titulo, String resumen, String autor, String tienda1, String tienda2, String tienda3, String fecha, int img) {
        this.id = id;
        this.titulo = titulo;
        this.resumen = resumen;
        this.autor = autor;
        this.tienda1 = tienda1;
        this.tienda2 = tienda2;
        this.tienda3 = tienda3;
        this.fecha = fecha;
        this.img = img;
    }
}
