package view;

import controller.LibroController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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

        String[] columnas = {
            "ID",
            "Título",
            "Autor",
            "Categoría",
            "Disponibilidad"
        };

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

        tablaLibros =
                new JTable(
                        modeloTabla
                );

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
         * Botón creado por Mario.
         * Vuelve a cargar la información de la tabla.
         */
        JButton btnActualizarLista =
                crearBoton(
                        "Actualizar lista",
                        AZUL
                );

        // =================================================
        // INCREMENTO 3 - CARLOS
        // NUEVOS BOTONES: ACTUALIZAR Y ELIMINAR LIBRO
        // =================================================

        JButton btnActualizarLibro =
                crearBoton(
                        "Actualizar libro",
                        AZUL_OSCURO
                );

        JButton btnEliminarLibro =
                crearBoton(
                        "Eliminar libro",
                        new Color(198, 40, 40)
                );

        JButton btnCerrar =
                crearBoton(
                        "Cerrar",
                        GRIS
                );

        // Botón de Mario: vuelve a cargar la tabla
        btnActualizarLista.addActionListener(
                e -> cargarLibros()
        );

        // =================================================
        // INCREMENTO 3 - CARLOS
        // EVENTOS DE LOS NUEVOS BOTONES
        // =================================================

        btnActualizarLibro.addActionListener(
                e -> actualizarLibroSeleccionado()
        );

        btnEliminarLibro.addActionListener(
                e -> eliminarLibroSeleccionado()
        );

        btnCerrar.addActionListener(
                e -> dispose()
        );

        panelBotones.add(
                btnActualizarLista
        );

        // NUEVO - CARLOS
        panelBotones.add(
                btnActualizarLibro
        );

        // NUEVO - CARLOS
        panelBotones.add(
                btnEliminarLibro
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

        modeloTabla.setRowCount(
                0
        );

        try {

            List<Libro> libros =
                    controller.listarLibros();

            if (libros.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "No hay libros registrados.",
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }

            for (Libro libro : libros) {

                String disponibilidad =
                        libro.isDisponible()
                                ? "Disponible"
                                : "No disponible";

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

    // =====================================================
    // INCREMENTO 3 - CARLOS: ACTUALIZAR LIBRO
    // =====================================================

    /**
     * Permite actualizar el libro seleccionado en la tabla.
     * El ID se conserva y se pueden modificar el título,
     * autor, categoría y disponibilidad.
     */
    private void actualizarLibroSeleccionado() {

        // Obtenemos la fila seleccionada
        int filaSeleccionada =
                tablaLibros.getSelectedRow();

        /*
         * -1 significa que el usuario
         * todavía no ha seleccionado un libro.
         */
        if (filaSeleccionada == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un libro de la tabla para actualizar.",
                    "Libro no seleccionado",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Obtenemos el ID del libro seleccionado
        int id =
                Integer.parseInt(
                        modeloTabla
                                .getValueAt(
                                        filaSeleccionada,
                                        0
                                )
                                .toString()
                );

        // Obtenemos los datos actuales
        String tituloActual =
                modeloTabla
                        .getValueAt(
                                filaSeleccionada,
                                1
                        )
                        .toString();

        String autorActual =
                modeloTabla
                        .getValueAt(
                                filaSeleccionada,
                                2
                        )
                        .toString();

        String categoriaActual =
                modeloTabla
                        .getValueAt(
                                filaSeleccionada,
                                3
                        )
                        .toString();

        boolean disponibleActual =
                modeloTabla
                        .getValueAt(
                                filaSeleccionada,
                                4
                        )
                        .toString()
                        .equalsIgnoreCase(
                                "Disponible"
                        );

        /*
         * Creamos los campos del formulario
         * con los datos actuales del libro.
         */
        JTextField txtTitulo =
                new JTextField(
                        tituloActual
                );

        JTextField txtAutor =
                new JTextField(
                        autorActual
                );

        JTextField txtCategoria =
                new JTextField(
                        categoriaActual
                );

        JCheckBox chkDisponible =
                new JCheckBox(
                        "Disponible",
                        disponibleActual
                );

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                0,
                                1,
                                5,
                                5
                        )
                );

        /*
         * El ID solamente se muestra.
         * No permitimos modificarlo.
         */
        panel.add(
                new JLabel(
                        "ID: " + id
                )
        );

        panel.add(
                new JLabel(
                        "Título:"
                )
        );

        panel.add(
                txtTitulo
        );

        panel.add(
                new JLabel(
                        "Autor:"
                )
        );

        panel.add(
                txtAutor
        );

        panel.add(
                new JLabel(
                        "Categoría:"
                )
        );

        panel.add(
                txtCategoria
        );

        panel.add(
                chkDisponible
        );

        /*
         * Mostramos el formulario.
         * El usuario puede aceptar o cancelar.
         */
        int opcion =
                JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "Actualizar libro",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

        // Si cancela, no hacemos ningún cambio
        if (opcion != JOptionPane.OK_OPTION) {
            return;
        }

        // Obtenemos la nueva información
        String nuevoTitulo =
                txtTitulo.getText().trim();

        String nuevoAutor =
                txtAutor.getText().trim();

        String nuevaCategoria =
                txtCategoria.getText().trim();

        // Validamos campos vacíos
        if (nuevoTitulo.isEmpty()
                || nuevoAutor.isEmpty()
                || nuevaCategoria.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe completar todos los campos.",
                    "Campos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            /*
             * Enviamos los nuevos datos
             * al controlador.
             */
            controller.actualizarLibro(
                    id,
                    nuevoTitulo,
                    nuevoAutor,
                    nuevaCategoria,
                    chkDisponible.isSelected()
            );

            JOptionPane.showMessageDialog(
                    this,
                    "El libro fue actualizado correctamente.",
                    "Actualización exitosa",
                    JOptionPane.INFORMATION_MESSAGE
            );

            /*
             * Recargamos la tabla para que
             * aparezcan inmediatamente los cambios.
             */
            cargarLibros();

        } catch (RuntimeException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // INCREMENTO 3 - CARLOS: ELIMINAR LIBRO
    // =====================================================

    /**
     * Elimina el libro seleccionado.
     * Antes de eliminar solicita confirmación.
     */
    private void eliminarLibroSeleccionado() {

        int filaSeleccionada =
                tablaLibros.getSelectedRow();

        // Validamos que el usuario seleccione un libro
        if (filaSeleccionada == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un libro de la tabla para eliminar.",
                    "Libro no seleccionado",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Obtenemos el ID
        int id =
                Integer.parseInt(
                        modeloTabla
                                .getValueAt(
                                        filaSeleccionada,
                                        0
                                )
                                .toString()
                );

        // Obtenemos el título para mostrarlo en la confirmación
        String titulo =
                modeloTabla
                        .getValueAt(
                                filaSeleccionada,
                                1
                        )
                        .toString();

        /*
         * Solicitamos confirmación para evitar
         * eliminar un libro accidentalmente.
         */
        // =====================================================
// INCREMENTO 3 - CARLOS
// CONFIRMACIÓN DE ELIMINACIÓN EN ESPAÑOL
// =====================================================

// Opciones que aparecerán en la ventana
Object[] opciones = {
    "Sí",
    "No"
};

            /*
             * Mostramos una ventana de confirmación
             * con las opciones Sí y No en español.
             */
            int confirmacion =
                    JOptionPane.showOptionDialog(
                            this,
                            "¿Está seguro de eliminar el libro '"
                                    + titulo
                                    + "' con ID "
                                    + id
                                    + "?",
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE,
                            null,
                            opciones,
                            opciones[1]
                    );

            // La opción 0 corresponde a "Sí".
            // Si selecciona "No" o cierra la ventana,
            // se cancela la eliminación.
            if (confirmacion != 0) {
                return;
            }

        try {

            // Solicitamos al controlador eliminar el libro
            controller.eliminarLibro(
                    id
            );

            JOptionPane.showMessageDialog(
                    this,
                    "El libro fue eliminado correctamente.",
                    "Eliminación exitosa",
                    JOptionPane.INFORMATION_MESSAGE
            );

            /*
             * Recargamos la tabla para retirar
             * inmediatamente el libro eliminado.
             */
            cargarLibros();

        } catch (RuntimeException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
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