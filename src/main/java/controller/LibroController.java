package controller;

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
}