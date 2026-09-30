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

    // =====================================================
    // INCREMENTO 3 - CARLOS: ACTUALIZAR LIBRO
    // Actualiza los datos de un libro existente.
    // El libro se identifica por medio de su ID.
    // =====================================================
    boolean actualizar(Libro libro);

    // =====================================================
    // INCREMENTO 3 - CARLOS: ELIMINAR LIBRO
    // Elimina un libro registrado utilizando su ID.
    // =====================================================
    boolean eliminar(int id);
}