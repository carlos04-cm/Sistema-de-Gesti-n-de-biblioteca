package view;

import controller.LibroController;
import model.Libro;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.util.List;
import java.util.regex.Pattern;

public class FrmListaLibros extends JPanel {

    // =====================================================
    // CONTROLADOR
    // =====================================================

    private final LibroController controller;

    // =====================================================
    // COMPONENTES
    // =====================================================

    private JTable tablaLibros;
    private DefaultTableModel modeloTabla;

    private JTextField txtBuscar;

    private JLabel lblTotal;

    private TableRowSorter<DefaultTableModel> ordenador;

    // =====================================================
    // COLORES
    // =====================================================

    private final Color AZUL_OSCURO =
            new Color(20, 55, 84);

    private final Color AZUL_ACTIVO =
            new Color(38, 105, 150);

    private final Color ROJO =
            new Color(198, 40, 40);

    private final Color FONDO =
            new Color(245, 247, 250);

    private final Color TEXTO =
            new Color(35, 45, 55);

    private final Color TEXTO_SECUNDARIO =
            new Color(100, 112, 123);

    private final Color BORDE =
            new Color(215, 222, 228);

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public FrmListaLibros(
            LibroController controller) {

        this.controller = controller;

        crearInterfaz();

        cargarLibros();
    }

    // =====================================================
    // CREAR INTERFAZ
    // =====================================================

    private void crearInterfaz() {

        setLayout(
                new BorderLayout()
        );

        setBackground(
                FONDO
        );

        setBorder(
                new EmptyBorder(
                        22,
                        28,
                        22,
                        28
                )
        );

        add(
                crearContenido(),
                BorderLayout.CENTER
        );
    }

    // =====================================================
    // CONTENIDO PRINCIPAL
    // =====================================================

    private JPanel crearContenido() {

        JPanel contenido =
                new JPanel(
                        new BorderLayout(
                                0,
                                15
                        )
                );

        contenido.setBackground(
                FONDO
        );

        // =================================================
        // PARTE SUPERIOR
        // =================================================

        contenido.add(
                crearParteSuperior(),
                BorderLayout.NORTH
        );

        // =================================================
        // TABLA
        // =================================================

        contenido.add(
                crearPanelTabla(),
                BorderLayout.CENTER
        );

        // =================================================
        // PARTE INFERIOR
        // =================================================

        contenido.add(
                crearPanelInferior(),
                BorderLayout.SOUTH
        );

        return contenido;
    }

    // =====================================================
    // PARTE SUPERIOR
    // TÍTULO + BÚSQUEDA
    // =====================================================

    private JPanel crearParteSuperior() {

        JPanel superior =
                new JPanel();

        superior.setLayout(
                new BoxLayout(
                        superior,
                        BoxLayout.Y_AXIS
                )
        );

        superior.setBackground(
                FONDO
        );

        // =================================================
        // TÍTULO
        // =================================================

        JLabel titulo =
                new JLabel(
                        "LIBROS REGISTRADOS"
                );

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        21
                )
        );

        titulo.setForeground(
                AZUL_OSCURO
        );

        titulo.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        // =================================================
        // DESCRIPCIÓN
        // =================================================

        JLabel descripcion =
                new JLabel(
                        "Consulta, busca, actualiza o elimina los libros registrados."
                );

        descripcion.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        descripcion.setForeground(
                TEXTO_SECUNDARIO
        );

        descripcion.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        superior.add(
                titulo
        );

        superior.add(
                Box.createVerticalStrut(5)
        );

        superior.add(
                descripcion
        );

        superior.add(
                Box.createVerticalStrut(18)
        );

        // =================================================
        // BÚSQUEDA
        // =================================================

        JPanel busqueda =
                crearPanelBusqueda();

        busqueda.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        superior.add(
                busqueda
        );

        return superior;
    }

    // =====================================================
    // PANEL DE BÚSQUEDA
    // =====================================================

    private JPanel crearPanelBusqueda() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                12,
                                0
                        )
                );

        panel.setBackground(
                Color.WHITE
        );

        panel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        72
                )
        );

        panel.setPreferredSize(
                new Dimension(
                        760,
                        72
                )
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                BORDE
                        ),

                        new EmptyBorder(
                                15,
                                18,
                                15,
                                18
                        )
                )
        );

        // =================================================
        // ETIQUETA
        // =================================================

        JLabel lblBuscar =
                new JLabel(
                        "Buscar:"
                );

        lblBuscar.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        lblBuscar.setForeground(
                AZUL_OSCURO
        );

        // =================================================
        // CAMPO
        // =================================================

        txtBuscar =
                new JTextField();

        txtBuscar.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        txtBuscar.setForeground(
                TEXTO
        );

        txtBuscar.setToolTipText(
                "Buscar por ID, título, autor, categoría o disponibilidad"
        );

        txtBuscar.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                BORDE
                        ),

                        new EmptyBorder(
                                6,
                                10,
                                6,
                                10
                        )
                )
        );

        panel.add(
                lblBuscar,
                BorderLayout.WEST
        );

        panel.add(
                txtBuscar,
                BorderLayout.CENTER
        );

        // =================================================
        // BÚSQUEDA AUTOMÁTICA
        // =================================================

        txtBuscar
                .getDocument()
                .addDocumentListener(
                        new DocumentListener() {

                            @Override
                            public void insertUpdate(
                                    DocumentEvent e) {

                                filtrarTabla();
                            }

                            @Override
                            public void removeUpdate(
                                    DocumentEvent e) {

                                filtrarTabla();
                            }

                            @Override
                            public void changedUpdate(
                                    DocumentEvent e) {

                                filtrarTabla();
                            }
                        }
                );

        return panel;
    }

    // =====================================================
    // PANEL DE TABLA
    // =====================================================

    private JPanel crearPanelTabla() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                Color.WHITE
        );

        panel.setBorder(
                BorderFactory.createLineBorder(
                        BORDE
                )
        );

        // =================================================
        // COLUMNAS
        // =================================================

        String[] columnas = {
            "ID",
            "Título",
            "Autor",
            "Categoría",
            "Disponibilidad"
        };

        // =================================================
        // MODELO
        // =================================================

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

        // =================================================
        // TABLA
        // =================================================

        tablaLibros =
                new JTable(
                        modeloTabla
                );

        tablaLibros.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        tablaLibros.setForeground(
                TEXTO
        );

        tablaLibros.setBackground(
                Color.WHITE
        );

        tablaLibros.setRowHeight(
                34
        );

        tablaLibros.setGridColor(
                BORDE
        );

        tablaLibros.setShowVerticalLines(
                true
        );

        tablaLibros.setShowHorizontalLines(
                true
        );

        tablaLibros.setSelectionBackground(
                new Color(
                        220,
                        235,
                        246
                )
        );

        tablaLibros.setSelectionForeground(
                TEXTO
        );

        tablaLibros.setFillsViewportHeight(
                true
        );

        tablaLibros.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        // =================================================
        // ENCABEZADO TABLA
        // =================================================

        tablaLibros
                .getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
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

        tablaLibros
                .getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                36
                        )
                );

        tablaLibros
                .getTableHeader()
                .setReorderingAllowed(
                        false
                );

        // =================================================
        // ANCHO COLUMNAS
        // =================================================

        tablaLibros
                .getColumnModel()
                .getColumn(0)
                .setPreferredWidth(50);

        tablaLibros
                .getColumnModel()
                .getColumn(1)
                .setPreferredWidth(210);

        tablaLibros
                .getColumnModel()
                .getColumn(2)
                .setPreferredWidth(180);

        tablaLibros
                .getColumnModel()
                .getColumn(3)
                .setPreferredWidth(150);

        tablaLibros
                .getColumnModel()
                .getColumn(4)
                .setPreferredWidth(140);

        // =================================================
        // FILTRO
        // =================================================

        ordenador =
                new TableRowSorter<>(
                        modeloTabla
                );

        tablaLibros.setRowSorter(
                ordenador
        );

        // =================================================
        // SCROLL
        // =================================================

        JScrollPane scroll =
                new JScrollPane(
                        tablaLibros
                );

        scroll.setBorder(
                BorderFactory.createEmptyBorder()
        );

        scroll.getViewport()
                .setBackground(
                        Color.WHITE
                );

        panel.add(
                scroll,
                BorderLayout.CENTER
        );

        return panel;
    }

    // =====================================================
    // PANEL INFERIOR
    // =====================================================

    private JPanel crearPanelInferior() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                FONDO
        );

        panel.setBorder(
                new EmptyBorder(
                        3,
                        0,
                        0,
                        0
                )
        );

        // =================================================
        // TOTAL
        // =================================================

        lblTotal =
                new JLabel(
                        "0 libros registrados"
                );

        lblTotal.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        lblTotal.setForeground(
                TEXTO_SECUNDARIO
        );

        panel.add(
                lblTotal,
                BorderLayout.WEST
        );

        // =================================================
        // BOTONES
        // =================================================

        JPanel botones =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        botones.setBackground(
                FONDO
        );

        JButton btnActualizarLista =
                crearBoton(
                        "Actualizar lista",
                        AZUL_ACTIVO,
                        145
                );

        JButton btnActualizarLibro =
                crearBoton(
                        "Actualizar libro",
                        AZUL_OSCURO,
                        145
                );

        JButton btnEliminarLibro =
                crearBoton(
                        "Eliminar libro",
                        ROJO,
                        140
                );

        // =================================================
        // EVENTOS
        // =================================================

        btnActualizarLista.addActionListener(
                e -> cargarLibros()
        );

        btnActualizarLibro.addActionListener(
                e -> actualizarLibroSeleccionado()
        );

        btnEliminarLibro.addActionListener(
                e -> eliminarLibroSeleccionado()
        );

        botones.add(
                btnActualizarLista
        );

        botones.add(
                btnActualizarLibro
        );

        botones.add(
                btnEliminarLibro
        );

        panel.add(
                botones,
                BorderLayout.EAST
        );

        return panel;
    }

    // =====================================================
    // CARGAR LIBROS
    // =====================================================

    private void cargarLibros() {

        modeloTabla.setRowCount(
                0
        );

        try {

            List<Libro> libros =
                    controller.listarLibros();

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

            actualizarTotal();

            filtrarTabla();

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
    // FILTRAR TABLA
    // =====================================================

    private void filtrarTabla() {

        if (ordenador == null
                || txtBuscar == null) {

            return;
        }

        String texto =
                txtBuscar
                        .getText()
                        .trim();

        if (texto.isEmpty()) {

            ordenador.setRowFilter(
                    null
            );

        } else {

            ordenador.setRowFilter(
                    RowFilter.regexFilter(
                            "(?i)"
                            + Pattern.quote(texto)
                    )
            );
        }

        actualizarTotal();
    }

    // =====================================================
    // ACTUALIZAR TOTAL
    // =====================================================

    private void actualizarTotal() {

        if (lblTotal == null
                || tablaLibros == null
                || modeloTabla == null) {

            return;
        }

        int visibles =
                tablaLibros.getRowCount();

        int total =
                modeloTabla.getRowCount();

        if (txtBuscar != null
                && !txtBuscar
                        .getText()
                        .trim()
                        .isEmpty()) {

            lblTotal.setText(
                    visibles
                    + " de "
                    + total
                    + " libros encontrados"
            );

        } else {

            if (total == 1) {

                lblTotal.setText(
                        "1 libro registrado"
                );

            } else {

                lblTotal.setText(
                        total
                        + " libros registrados"
                );
            }
        }
    }

    // =====================================================
    // ACTUALIZAR LIBRO SELECCIONADO
    // =====================================================

    private void actualizarLibroSeleccionado() {

        int filaVista =
                tablaLibros.getSelectedRow();

        if (filaVista == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un libro de la tabla para actualizar.",
                    "Libro no seleccionado",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // =================================================
        // CONVERTIR FILA VISIBLE A FILA REAL
        // =================================================

        int filaSeleccionada =
                tablaLibros.convertRowIndexToModel(
                        filaVista
                );

        // =================================================
        // ID
        // =================================================

        int id =
                Integer.parseInt(
                        modeloTabla
                                .getValueAt(
                                        filaSeleccionada,
                                        0
                                )
                                .toString()
                );

        // =================================================
        // DATOS ACTUALES
        // =================================================

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

        // =================================================
        // CAMPOS DEL FORMULARIO
        // =================================================

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
                        "Sí, disponible",
                        disponibleActual
                );

        txtTitulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        txtAutor.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        txtCategoria.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        chkDisponible.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        // =================================================
        // PANEL DEL FORMULARIO
        // =================================================

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                0,
                                1,
                                5,
                                5
                        )
                );

        panel.setPreferredSize(
                new Dimension(
                        350,
                        245
                )
        );

        JLabel lblId =
                new JLabel(
                        "ID: " + id
                );

        lblId.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        panel.add(
                lblId
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

        // =================================================
        // MOSTRAR DIÁLOGO
        // =================================================

        int opcion =
                JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "Actualizar libro",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

        if (opcion
                != JOptionPane.OK_OPTION) {

            return;
        }

        // =================================================
        // NUEVOS DATOS
        // =================================================

        String nuevoTitulo =
                txtTitulo
                        .getText()
                        .trim();

        String nuevoAutor =
                txtAutor
                        .getText()
                        .trim();

        String nuevaCategoria =
                txtCategoria
                        .getText()
                        .trim();

        // =================================================
        // VALIDACIÓN
        // =================================================

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

        // =================================================
        // ACTUALIZAR
        // =================================================

        try {

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
    // ELIMINAR LIBRO
    // =====================================================

    private void eliminarLibroSeleccionado() {

        int filaVista =
                tablaLibros.getSelectedRow();

        if (filaVista == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un libro de la tabla para eliminar.",
                    "Libro no seleccionado",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // =================================================
        // CONVERTIR FILA
        // =================================================

        int filaSeleccionada =
                tablaLibros.convertRowIndexToModel(
                        filaVista
                );

        // =================================================
        // OBTENER ID
        // =================================================

        int id =
                Integer.parseInt(
                        modeloTabla
                                .getValueAt(
                                        filaSeleccionada,
                                        0
                                )
                                .toString()
                );

        // =================================================
        // OBTENER TÍTULO
        // =================================================

        String titulo =
                modeloTabla
                        .getValueAt(
                                filaSeleccionada,
                                1
                        )
                        .toString();

        // =================================================
        // CONFIRMACIÓN
        // =================================================

        Object[] opciones = {
            "Sí",
            "No"
        };

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

        if (confirmacion != 0) {

            return;
        }

        // =================================================
        // ELIMINAR
        // =================================================

        try {

            controller.eliminarLibro(
                    id
            );

            JOptionPane.showMessageDialog(
                    this,
                    "El libro fue eliminado correctamente.",
                    "Eliminación exitosa",
                    JOptionPane.INFORMATION_MESSAGE
            );

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
    // CREAR BOTÓN
    // =====================================================

    private JButton crearBoton(
            String texto,
            Color color,
            int ancho) {

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
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        boton.setFocusPainted(
                false
        );

        boton.setBorderPainted(
                false
        );

        boton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        Dimension tamano =
                new Dimension(
                        ancho,
                        40
                );

        boton.setPreferredSize(
                tamano
        );

        boton.setMinimumSize(
                tamano
        );

        return boton;
    }
}