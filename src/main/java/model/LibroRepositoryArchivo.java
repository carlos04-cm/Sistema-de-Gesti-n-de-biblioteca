package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// NUEVO: necesario para trabajar con listas
import java.util.ArrayList;
import java.util.List;

public class LibroRepositoryArchivo
        implements LibroRepository {

    private final String nombreArchivo =
            "libros.txt";

    // GUARDAR LIBRO
    @Override
    public void guardar(Libro libro) {

        try (
            FileWriter archivo =
                    new FileWriter(
                            nombreArchivo,
                            true
                    );

            PrintWriter escritor =
                    new PrintWriter(archivo)
        ) {

            escritor.println(
                    libro.getId() + ";" +
                    libro.getTitulo() + ";" +
                    libro.getAutor() + ";" +
                    libro.getCategoria() + ";" +
                    libro.isDisponible()
            );

        } catch (IOException e) {

            throw new RuntimeException(
                    "Error al guardar el libro.",
                    e
            );
        }
    }

    // BUSCAR LIBRO POR ID
    @Override
    public Libro buscarPorId(int id) {

        /*
         * Si el archivo todavía no existe,
         * significa que no hay libros registrados.
         */
        File archivo = new File(nombreArchivo);

        if (!archivo.exists()) {
            return null;
        }

        try (
            BufferedReader lector =
                    new BufferedReader(
                            new FileReader(
                                    nombreArchivo
                            )
                    )
        ) {

            String linea;

            while ((linea =
                    lector.readLine()) != null) {

                String[] datos =
                        linea.split(";");

                if (datos.length < 5) {
                    continue;
                }

                int idLibro =
                        Integer.parseInt(
                                datos[0]
                        );

                if (idLibro == id) {

                    String titulo =
                            datos[1];

                    String autor =
                            datos[2];

                    String categoria =
                            datos[3];

                    boolean disponible =
                            Boolean.parseBoolean(
                                    datos[4]
                            );

                    return new Libro(
                            idLibro,
                            titulo,
                            autor,
                            categoria,
                            disponible
                    );
                }
            }

            return null;

        } catch (IOException e) {

            throw new RuntimeException(
                    "Error al buscar el libro.",
                    e
            );
        }
    }

    // =====================================================
    // INCREMENTO 3 - MARIO: VER TODOS LOS LIBROS
    // =====================================================

    @Override
    public List<Libro> listarTodos() {

        // Lista donde guardaremos todos los libros encontrados
        List<Libro> libros =
                new ArrayList<>();

        // Representa el archivo libros.txt
        File archivo =
                new File(nombreArchivo);

        /*
         * Si el archivo todavía no existe,
         * devolvemos una lista vacía.
         */
        if (!archivo.exists()) {
            return libros;
        }

        try (
            BufferedReader lector =
                    new BufferedReader(
                            new FileReader(
                                    nombreArchivo
                            )
                    )
        ) {

            String linea;

            /*
             * Recorremos todas las líneas
             * almacenadas en libros.txt.
             */
            while ((linea =
                    lector.readLine()) != null) {

                // Los datos están separados por ;
                String[] datos =
                        linea.split(";");

                /*
                 * Un libro debe tener:
                 * ID, título, autor,
                 * categoría y disponibilidad.
                 */
                if (datos.length < 5) {
                    continue;
                }

                // Convertimos el ID a número
                int id =
                        Integer.parseInt(
                                datos[0]
                        );

                String titulo =
                        datos[1];

                String autor =
                        datos[2];

                String categoria =
                        datos[3];

                // Convertimos la disponibilidad a boolean
                boolean disponible =
                        Boolean.parseBoolean(
                                datos[4]
                        );

                // Creamos el objeto Libro
                Libro libro =
                        new Libro(
                                id,
                                titulo,
                                autor,
                                categoria,
                                disponible
                        );

                // Agregamos el libro a la lista
                libros.add(libro);
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "Error al consultar los libros.",
                    e
            );
        }

        // Retornamos todos los libros encontrados
        return libros;
    }
}