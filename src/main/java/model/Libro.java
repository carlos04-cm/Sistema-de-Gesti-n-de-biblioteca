package model;

public class Libro {

    private final int id;
    private final String titulo;
    private final String autor;
    private final String categoria;
    private final boolean disponible;

    public Libro(
            int id,
            String titulo,
            String autor,
            String categoria,
            boolean disponible) {

        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean isDisponible() {
        return disponible;
    }
}