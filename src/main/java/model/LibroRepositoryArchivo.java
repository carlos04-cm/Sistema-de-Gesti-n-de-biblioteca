package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
}