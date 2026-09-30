package controller;

// NUEVO: necesario para devolver una lista
import java.util.List;

import model.Libro;
import model.LibroRepository;

public class LibroController {

    // DIP:
    // El controlador depende de la interfaz
    private final LibroRepository repository;

    public LibroController(
            LibroRepository repository) {

        this.repository = repository;
    }

    // REGISTRAR
    public void registrarLibro(
            int id,
            String titulo,
            String autor,
            String categoria,
            boolean disponible) {

        // VALIDAR QUE EL ID NO ESTÉ REGISTRADO
        Libro libroExistente =
                repository.buscarPorId(id);

        if (libroExistente != null) {

            throw new RuntimeException(
                    "Ya existe un libro registrado con el ID "
                            + id
                            + "."
            );
        }

        Libro libro =
                new Libro(
                        id,
                        titulo,
                        autor,
                        categoria,
                        disponible
                );

        repository.guardar(libro);
    }

    // BUSCAR
    public Libro buscarLibro(int id) {

        return repository.buscarPorId(id);
    }

    // =====================================================
    // INCREMENTO 3 - MARIO: VER TODOS LOS LIBROS
    // =====================================================

    /*
     * Solicita al repositorio todos los
     * libros registrados en el sistema.
     */
    public List<Libro> listarLibros() {

        return repository.listarTodos();
    }

    // =====================================================
    // INCREMENTO 3 - CARLOS: ACTUALIZAR LIBRO
    // =====================================================

    /*
     * Recibe los nuevos datos del libro desde la vista.
     * Primero comprueba que el ID exista y después envía
     * el libro actualizado al repositorio.
     */
    public void actualizarLibro(
            int id,
            String titulo,
            String autor,
            String categoria,
            boolean disponible) {

        // Verificamos que el libro exista antes de actualizarlo.
        Libro libroExistente =
                repository.buscarPorId(id);

        if (libroExistente == null) {

            throw new RuntimeException(
                    "No existe un libro registrado con el ID "
                            + id
                            + "."
            );
        }

        // Creamos un nuevo objeto con la información actualizada.
        Libro libroActualizado =
                new Libro(
                        id,
                        titulo,
                        autor,
                        categoria,
                        disponible
                );

        // Solicitamos al repositorio que guarde los cambios.
        boolean actualizado =
                repository.actualizar(
                        libroActualizado
                );

        if (!actualizado) {

            throw new RuntimeException(
                    "No fue posible actualizar el libro."
            );
        }
    }

    // =====================================================
    // INCREMENTO 3 - CARLOS: ELIMINAR LIBRO
    // =====================================================

    /*
     * Recibe el ID del libro seleccionado.
     * Comprueba que exista y después solicita
     * al repositorio que lo elimine.
     */
    public void eliminarLibro(int id) {

        // Verificamos que el libro exista antes de eliminarlo.
        Libro libroExistente =
                repository.buscarPorId(id);

        if (libroExistente == null) {

            throw new RuntimeException(
                    "No existe un libro registrado con el ID "
                            + id
                            + "."
            );
        }

        boolean eliminado =
                repository.eliminar(id);

        if (!eliminado) {

            throw new RuntimeException(
                    "No fue posible eliminar el libro."
            );
        }
    }
}