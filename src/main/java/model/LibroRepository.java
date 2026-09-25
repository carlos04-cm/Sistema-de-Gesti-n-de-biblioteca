package model;

public interface LibroRepository {

    // Primera operación del proyecto
    void guardar(Libro libro);

    // Nueva operación del segundo incremento
    Libro buscarPorId(int id);
}