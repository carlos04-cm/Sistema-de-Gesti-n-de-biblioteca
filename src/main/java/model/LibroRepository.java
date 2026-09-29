package model;

import java.util.List;

public interface LibroRepository {

    // Primera operación del proyecto
    void guardar(Libro libro);

    // Nueva operación del segundo incremento
    Libro buscarPorId(int id);

    // =====================================================
    // INCREMENTO 3 - MARIO: VER TODOS LOS LIBROS
    // Obtiene la lista completa de libros registrados
    // =====================================================
    List<Libro> listarTodos();
}