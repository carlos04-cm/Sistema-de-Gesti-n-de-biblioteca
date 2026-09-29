package view;

import controller.LibroController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.Libro;

/**
 * =========================================================
 * INCREMENTO 3 - MARIO
 * FUNCIONALIDAD: VER TODOS LOS LIBROS
 * =========================================================
 *
 * Esta ventana permite visualizar en una tabla
 * todos los libros registrados en el sistema.
 */
public class FrmListaLibros extends JFrame {

    // Controlador que permite consultar los libros
    private final LibroController controller;

    // Tabla donde se mostrarán los libros
    private JTable tablaLibros;

    // Modelo utilizado para manejar las filas de la tabla
    private DefaultTableModel modeloTabla;

    // Colores utilizados en la interfaz
    private final Color AZUL =
            new Color(41, 98, 255);

    private final Color AZUL_OSCURO =
            new Color(25, 55, 109);

    private final Color GRIS =
            new Color(108, 117, 125);

    private final Color FONDO =
            new Color(245, 247, 250);

    private final Color BORDE =
            new Color(220, 225, 230);

    /**
     * Constructor.
     *
     * Recibe el mismo controlador utilizado
     * por la ventana principal.
     */
    public FrmListaLibros(
            LibroController controller) {

        this.controller = controller;

        configurarVentana();

        crearInterfaz();

        // Al abrir la ventana cargamos los libros
        cargarLibros();
    }

    /**
     * Configuración de la ventana.
     */
    private void configurarVentana() {

        setTitle(
                "Libros registrados"
        );

        setSize(
                850,
                500
        );

        // Centrar ventana
        setLocationRelativeTo(null);

        /*
         * Cierra solamente esta ventana.
         * La ventana principal continúa abierta.
         */
        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setMinimumSize(
                new Dimension(
                        750,
                        450
                )
        );
    }

    /**
     * Crea todos los componentes visuales.
     */
    private void crearInterfaz() {

        // PANEL PRINCIPAL
        JPanel panelPrincipal =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        panelPrincipal.setBackground(
                FONDO
        );

        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        // =================================================
        // ENCABEZADO
        // =================================================

        JPanel panelEncabezado =
                new JPanel(
                        new BorderLayout()
                );

        panelEncabezado.setBackground(
                FONDO
        );

        // Título de la ventana
        JLabel lblTitulo =
                new JLabel(
                        "LIBROS REGISTRADOS"
                );

        lblTitulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        lblTitulo.setForeground(
                AZUL_OSCURO
        );

        lblTitulo.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        // Descripción
        JLabel lblDescripcion =
                new JLabel(
                        "Listado de todos los libros registrados en el sistema"
                );

        lblDescripcion.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        lblDescripcion.setForeground(
                GRIS
        );

        lblDescripcion.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        panelEncabezado.add(
                lblTitulo,
                BorderLayout.NORTH
        );

        panelEncabezado.add(
                lblDescripcion,
                BorderLayout.SOUTH
        );

        // =================================================
        // TABLA
        // =================================================

        /*
         * Columnas que aparecerán
         * en la tabla.
         */
        String[] columnas = {
            "ID",
            "Título",
            "Autor",
            "Categoría",
            "Disponibilidad"
        };

        /*
         * Creamos el modelo de la tabla.
         *
         * isCellEditable retorna false
         * para evitar modificar los datos
         * directamente desde la tabla.
         */
        modeloTabla =
                new DefaultTableModel(
                        columnas,
                        0
                ) {

            @Override
            public boolean isCellEditable(
                    int fila,
                    int columna) {

                return false;
            }
        };

        // Creamos la tabla
        tablaLibros =
                new JTable(
                        modeloTabla
                );

        // Altura de las filas
        tablaLibros.setRowHeight(
                28
        );

        tablaLibros.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        // Diseño del encabezado
        tablaLibros
                .getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                13
                        )
                );

        tablaLibros
                .getTableHeader()
                .setBackground(
                        AZUL_OSCURO
                );

        tablaLibros
                .getTableHeader()
                .setForeground(
                        Color.WHITE
                );

        tablaLibros.setGridColor(
                BORDE
        );

        /*
         * JScrollPane permite desplazarse
         * cuando existen muchos libros.
         */
        JScrollPane scroll =
                new JScrollPane(
                        tablaLibros
                );

        scroll.setBorder(
                BorderFactory.createLineBorder(
                        BORDE
                )
        );

        // =================================================
        // BOTONES
        // =================================================

        JPanel panelBotones =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        panelBotones.setBackground(
                FONDO
        );

        /*
         * Este botón vuelve a consultar
         * libros.txt y actualiza la tabla.
         *
         * NO actualiza un libro.
         * Solamente actualiza el listado.
         */
        JButton btnActualizarLista =
                crearBoton(
                        "Actualizar lista",
                        AZUL
                );

        // Botón para cerrar la ventana
        JButton btnCerrar =
                crearBoton(
                        "Cerrar",
                        GRIS
                );

        // Recargar la tabla
        btnActualizarLista.addActionListener(
                e -> cargarLibros()
        );

        // Cerrar esta ventana
        btnCerrar.addActionListener(
                e -> dispose()
        );

        panelBotones.add(
                btnActualizarLista
        );

        panelBotones.add(
                btnCerrar
        );

        // =================================================
        // AGREGAR COMPONENTES
        // =================================================

        panelPrincipal.add(
                panelEncabezado,
                BorderLayout.NORTH
        );

        panelPrincipal.add(
                scroll,
                BorderLayout.CENTER
        );

        panelPrincipal.add(
                panelBotones,
                BorderLayout.SOUTH
        );

        add(
                panelPrincipal
        );
    }

    /**
     * Consulta todos los libros registrados
     * y los muestra en la tabla.
     */
    private void cargarLibros() {

        /*
         * Primero limpiamos la tabla
         * para evitar registros repetidos.
         */
        modeloTabla.setRowCount(
                0
        );

        try {

            /*
             * Solicitamos al controlador
             * todos los libros registrados.
             */
            List<Libro> libros =
                    controller.listarLibros();

            /*
             * Si la lista está vacía,
             * significa que todavía no existen
             * libros registrados.
             */
            if (libros.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "No hay libros registrados.",
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }

            /*
             * Recorremos todos los libros.
             */
            for (Libro libro : libros) {

                /*
                 * Convertimos el boolean
                 * en un texto más comprensible.
                 */
                String disponibilidad =
                        libro.isDisponible()
                                ? "Disponible"
                                : "No disponible";

                /*
                 * Agregamos una nueva fila
                 * con los datos del libro.
                 */
                modeloTabla.addRow(
                        new Object[]{
                            libro.getId(),
                            libro.getTitulo(),
                            libro.getAutor(),
                            libro.getCategoria(),
                            disponibilidad
                        }
                );
            }

        } catch (RuntimeException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "No fue posible cargar los libros.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Método auxiliar para crear botones
     * manteniendo el mismo estilo.
     */
    private JButton crearBoton(
            String texto,
            Color color) {

        JButton boton =
                new JButton(
                        texto
                );

        boton.setBackground(
                color
        );

        boton.setForeground(
                Color.WHITE
        );

        boton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        boton.setFocusPainted(
                false
        );

        boton.setPreferredSize(
                new Dimension(
                        140,
                        35
                )
        );

        return boton;
    }
}