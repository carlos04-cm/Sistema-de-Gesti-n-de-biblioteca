package view;

import controller.LibroController;
import model.Libro;
import model.LibroRepository;
import model.LibroRepositoryArchivo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FrmLibro extends JFrame {

    // =====================================================
    // COLORES
    // =====================================================

    private final Color AZUL = new Color(25, 78, 95);
    private final Color AZUL_OSCURO = new Color(24, 48, 67);

    private final Color VERDE = new Color(38, 132, 105);
    private final Color AZUL_BOTON = new Color(52, 103, 140);
    private final Color GRIS = new Color(100, 112, 123);

    private final Color FONDO = new Color(246, 248, 250);
    private final Color TEXTO = new Color(35, 45, 55);
    private final Color BORDE = new Color(215, 222, 228);
    private final Color RESULTADO = new Color(237, 244, 246);

    // =====================================================
    // REGISTRO
    // =====================================================

    private JTextField txtId;
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtCategoria;

    private JCheckBox chkDisponible;

    private JButton btnRegistrar;
    private JButton btnLimpiar;
    private JButton btnMostrarBusqueda;

    // =====================================================
    // BÚSQUEDA
    // =====================================================

    private JPanel panelBusqueda;
    private JPanel panelResultado;

    private JTextField txtBuscarId;
    private JButton btnBuscar;

    private JLabel lblResultadoId;
    private JLabel lblResultadoTitulo;
    private JLabel lblResultadoAutor;
    private JLabel lblResultadoCategoria;
    private JLabel lblResultadoDisponible;

    private JLabel lblEstadoBusqueda;

    // =====================================================
    // SCROLL
    // =====================================================

    private JScrollPane scrollPrincipal;

    // =====================================================
    // CONTROLADOR
    // =====================================================

    private final LibroController controller;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public FrmLibro() {

        LibroRepository repository =
                new LibroRepositoryArchivo();

        controller =
                new LibroController(repository);

        setTitle(
                "Sistema de Gestión de Biblioteca"
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setResizable(false);

        crearInterfaz();

        pack();

        setLocationRelativeTo(null);
    }

    // =====================================================
    // INTERFAZ PRINCIPAL
    // =====================================================

    private void crearInterfaz() {

        JPanel principal =
                new JPanel(
                        new BorderLayout()
                );

        principal.setBackground(
                FONDO
        );

        // =========================
        // ENCABEZADO
        // =========================

        principal.add(
                crearEncabezado(),
                BorderLayout.NORTH
        );

        // =========================
        // CONTENIDO
        // =========================

        JPanel contenido =
                new JPanel();

        contenido.setLayout(
                new BoxLayout(
                        contenido,
                        BoxLayout.Y_AXIS
                )
        );

        contenido.setBackground(
                FONDO
        );

        contenido.setBorder(
                new EmptyBorder(
                        22,
                        35,
                        25,
                        35
                )
        );

        // Registrar
        contenido.add(
                crearPanelRegistro()
        );

        contenido.add(
                Box.createVerticalStrut(14)
        );

        // Buscar
        panelBusqueda =
                crearPanelBusqueda();

        panelBusqueda.setVisible(
                false
        );

        contenido.add(
                panelBusqueda
        );

        // =========================
        // SCROLL VERTICAL
        // =========================

        scrollPrincipal =
                new JScrollPane(
                        contenido
                );

        scrollPrincipal.setBorder(
                null
        );

        scrollPrincipal.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollPrincipal.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        scrollPrincipal.getVerticalScrollBar()
                .setUnitIncrement(16);

        scrollPrincipal.getViewport()
                .setBackground(FONDO);

        scrollPrincipal.setPreferredSize(
                new Dimension(
                        700,
                        500
                )
        );

        principal.add(
                scrollPrincipal,
                BorderLayout.CENTER
        );

        add(principal);
    }

    // =====================================================
    // ENCABEZADO CON LOGO
    // =====================================================

    private JPanel crearEncabezado() {

        JPanel encabezado =
                new JPanel();

        encabezado.setLayout(
                new BoxLayout(
                        encabezado,
                        BoxLayout.Y_AXIS
                )
        );

        encabezado.setBackground(
                AZUL
        );

        encabezado.setBorder(
                new EmptyBorder(
                        18,
                        30,
                        18,
                        30
                )
        );

        // =========================
        // LOGO
        // =========================

        JLabel lblLogo =
                new JLabel();

        java.net.URL rutaLogo =
                getClass().getResource(
                        "/imagenes/logo_biblioteca.png"
                );

        if (rutaLogo != null) {

            ImageIcon iconoOriginal =
                    new ImageIcon(
                            rutaLogo
                    );

            Image imagenEscalada =
                    iconoOriginal
                            .getImage()
                            .getScaledInstance(
                                    70,
                                    70,
                                    Image.SCALE_SMOOTH
                            );

            lblLogo.setIcon(
                    new ImageIcon(
                            imagenEscalada
                    )
            );

        } else {

            System.out.println(
                    "No se encontró el logo en "
                            + "/imagenes/logo_biblioteca.png"
            );
        }

        lblLogo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =========================
        // TÍTULO
        // =========================

        JLabel titulo =
                new JLabel(
                        "SISTEMA DE GESTIÓN DE BIBLIOTECA"
                );

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        23
                )
        );

        titulo.setForeground(
                Color.WHITE
        );

        titulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =========================
        // SUBTÍTULO
        // =========================

        JLabel subtitulo =
                new JLabel(
                        "Registro y consulta de libros"
                );

        subtitulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        subtitulo.setForeground(
                new Color(
                        220,
                        232,
                        235
                )
        );

        subtitulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        encabezado.add(
                lblLogo
        );

        encabezado.add(
                Box.createVerticalStrut(8)
        );

        encabezado.add(
                titulo
        );

        encabezado.add(
                Box.createVerticalStrut(5)
        );

        encabezado.add(
                subtitulo
        );

        return encabezado;
    }

    // =====================================================
    // PANEL REGISTRAR LIBRO
    // =====================================================

    private JPanel crearPanelRegistro() {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBackground(
                FONDO
        );

        panel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =========================
        // TÍTULO
        // =========================

        JLabel titulo =
                crearTituloSeccion(
                        "REGISTRAR LIBRO"
                );

        JLabel descripcion =
                crearDescripcion(
                        "Complete la información para registrar un nuevo libro"
                );

        panel.add(
                titulo
        );

        panel.add(
                Box.createVerticalStrut(4)
        );

        panel.add(
                descripcion
        );

        panel.add(
                Box.createVerticalStrut(15)
        );

        // =========================
        // FORMULARIO
        // =========================

        JPanel formulario =
                new JPanel(
                        new GridLayout(
                                5,
                                2,
                                14,
                                12
                        )
                );

        formulario.setBackground(
                Color.WHITE
        );

        formulario.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                BORDE
                        ),

                        new EmptyBorder(
                                20,
                                28,
                                20,
                                28
                        )
                )
        );

        formulario.setMaximumSize(
                new Dimension(
                        520,
                        250
                )
        );

        // =========================
        // CAMPOS
        // =========================

        txtId =
                crearCampo();

        txtTitulo =
                crearCampo();

        txtAutor =
                crearCampo();

        txtCategoria =
                crearCampo();

        chkDisponible =
                new JCheckBox(
                        "Sí, disponible"
                );

        chkDisponible.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        chkDisponible.setForeground(
                TEXTO
        );

        chkDisponible.setBackground(
                Color.WHITE
        );

        // =========================
        // AGREGAR CAMPOS
        // =========================

        formulario.add(
                crearEtiqueta("ID:")
        );

        formulario.add(
                txtId
        );

        formulario.add(
                crearEtiqueta("Título:")
        );

        formulario.add(
                txtTitulo
        );

        formulario.add(
                crearEtiqueta("Autor:")
        );

        formulario.add(
                txtAutor
        );

        formulario.add(
                crearEtiqueta("Categoría:")
        );

        formulario.add(
                txtCategoria
        );

        formulario.add(
                crearEtiqueta(
                        "Disponibilidad:"
                )
        );

        formulario.add(
                chkDisponible
        );

        panel.add(
                formulario
        );

        panel.add(
                Box.createVerticalStrut(18)
        );

        // =========================
        // BOTONES
        // =========================

        JPanel botones =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                12,
                                0
                        )
                );

        botones.setBackground(
                FONDO
        );

        btnRegistrar =
                crearBoton(
                        "Registrar Libro",
                        VERDE,
                        160
                );

        btnLimpiar =
                crearBoton(
                        "Limpiar",
                        GRIS,
                        110
                );

        btnMostrarBusqueda =
                crearBoton(
                        "Buscar Libro",
                        AZUL_BOTON,
                        150
                );

        botones.add(
                btnRegistrar
        );

        botones.add(
                btnLimpiar
        );

        botones.add(
                btnMostrarBusqueda
        );

        panel.add(
                botones
        );

        // =========================
        // ACCIONES
        // =========================

        btnRegistrar.addActionListener(
                e -> registrarLibro()
        );

        btnLimpiar.addActionListener(
                e -> limpiarCampos()
        );

        btnMostrarBusqueda.addActionListener(
                e -> mostrarOcultarBusqueda()
        );

        return panel;
    }

    // =====================================================
    // PANEL BUSCAR LIBRO
    // =====================================================

    private JPanel crearPanelBusqueda() {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBackground(
                Color.WHITE
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                BORDE
                        ),

                        new EmptyBorder(
                                18,
                                25,
                                18,
                                25
                        )
                )
        );

        panel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panel.setMaximumSize(
                new Dimension(
                        520,
                        350
                )
        );

        // =========================
        // TÍTULO
        // =========================

        JLabel titulo =
                crearTituloSeccion(
                        "BUSCAR LIBRO"
                );

        JLabel descripcion =
                crearDescripcion(
                        "Ingrese el ID del libro que desea consultar"
                );

        panel.add(
                titulo
        );

        panel.add(
                Box.createVerticalStrut(4)
        );

        panel.add(
                descripcion
        );

        panel.add(
                Box.createVerticalStrut(14)
        );

        // =========================
        // FILA DE BÚSQUEDA
        // =========================

        JPanel filaBusqueda =
                new JPanel(
                        new BorderLayout(
                                10,
                                0
                        )
                );

        filaBusqueda.setBackground(
                Color.WHITE
        );

        JLabel lblBuscar =
                crearEtiqueta(
                        "ID del libro:"
                );

        txtBuscarId =
                crearCampo();

        btnBuscar =
                crearBoton(
                        "Buscar",
                        AZUL_BOTON,
                        100
                );

        filaBusqueda.add(
                lblBuscar,
                BorderLayout.WEST
        );

        filaBusqueda.add(
                txtBuscarId,
                BorderLayout.CENTER
        );

        filaBusqueda.add(
                btnBuscar,
                BorderLayout.EAST
        );

        filaBusqueda.setMaximumSize(
                new Dimension(
                        460,
                        35
                )
        );

        filaBusqueda.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panel.add(
                filaBusqueda
        );

        panel.add(
                Box.createVerticalStrut(14)
        );

        // =========================
        // PANEL RESULTADO
        // =========================

        panelResultado =
                new JPanel();

        panelResultado.setLayout(
                new BoxLayout(
                        panelResultado,
                        BoxLayout.Y_AXIS
                )
        );

        panelResultado.setBackground(
                RESULTADO
        );

        panelResultado.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createTitledBorder(

                                BorderFactory.createLineBorder(
                                        new Color(
                                                190,
                                                205,
                                                210
                                        )
                                ),

                                "Resultado de la búsqueda"
                        ),

                        new EmptyBorder(
                                10,
                                15,
                                10,
                                15
                        )
                )
        );

        panelResultado.setMaximumSize(
                new Dimension(
                        460,
                        150
                )
        );

        panelResultado.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =========================
        // RESULTADOS
        // =========================

        lblResultadoId =
                crearResultado(
                        "ID: -"
                );

        lblResultadoTitulo =
                crearResultado(
                        "Título: -"
                );

        lblResultadoAutor =
                crearResultado(
                        "Autor: -"
                );

        lblResultadoCategoria =
                crearResultado(
                        "Categoría: -"
                );

        lblResultadoDisponible =
                crearResultado(
                        "Disponibilidad: -"
                );

        panelResultado.add(
                lblResultadoId
        );

        panelResultado.add(
                Box.createVerticalStrut(3)
        );

        panelResultado.add(
                lblResultadoTitulo
        );

        panelResultado.add(
                Box.createVerticalStrut(3)
        );

        panelResultado.add(
                lblResultadoAutor
        );

        panelResultado.add(
                Box.createVerticalStrut(3)
        );

        panelResultado.add(
                lblResultadoCategoria
        );

        panelResultado.add(
                Box.createVerticalStrut(3)
        );

        panelResultado.add(
                lblResultadoDisponible
        );

        panelResultado.setVisible(
                false
        );

        panel.add(
                panelResultado
        );

        panel.add(
                Box.createVerticalStrut(10)
        );

        // =========================
        // ESTADO
        // =========================

        lblEstadoBusqueda =
                new JLabel(
                        "Ingrese un ID para realizar la búsqueda."
                );

        lblEstadoBusqueda.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        lblEstadoBusqueda.setForeground(
                GRIS
        );

        lblEstadoBusqueda.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panel.add(
                lblEstadoBusqueda
        );

        // =========================
        // ACCIONES
        // =========================

        btnBuscar.addActionListener(
                e -> buscarLibro()
        );

        txtBuscarId.addActionListener(
                e -> buscarLibro()
        );

        return panel;
    }

    // =====================================================
    // MOSTRAR / OCULTAR BÚSQUEDA
    // =====================================================

    private void mostrarOcultarBusqueda() {

        boolean visible =
                panelBusqueda.isVisible();

        if (visible) {

            // Ocultar búsqueda
            panelBusqueda.setVisible(
                    false
            );

            btnMostrarBusqueda.setText(
                    "Buscar Libro"
            );

            actualizarVentana();

            // SUBIR AUTOMÁTICAMENTE
            SwingUtilities.invokeLater(
                    () -> scrollPrincipal
                            .getVerticalScrollBar()
                            .setValue(0)
            );

        } else {

            // Mostrar búsqueda
            panelBusqueda.setVisible(
                    true
            );

            btnMostrarBusqueda.setText(
                    "Ocultar Búsqueda"
            );

            txtBuscarId.setText(
                    ""
            );

            panelResultado.setVisible(
                    false
            );

            limpiarResultado();

            lblEstadoBusqueda.setText(
                    "Ingrese un ID para realizar la búsqueda."
            );

            lblEstadoBusqueda.setForeground(
                    GRIS
            );

            actualizarVentana();

            // =================================================
            // BAJAR AUTOMÁTICAMENTE LA BARRA
            // =================================================

            SwingUtilities.invokeLater(() -> {

                JScrollBar barra =
                        scrollPrincipal
                                .getVerticalScrollBar();

                barra.setValue(
                        barra.getMaximum()
                );

                txtBuscarId.requestFocus();
            });
        }
    }

    // =====================================================
    // REGISTRAR LIBRO
    // =====================================================

    private void registrarLibro() {

        try {

            // =========================
            // CAMPOS VACÍOS
            // =========================

            if (
                    txtId.getText().trim().isEmpty()
                            ||
                    txtTitulo.getText().trim().isEmpty()
                            ||
                    txtAutor.getText().trim().isEmpty()
                            ||
                    txtCategoria.getText().trim().isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Debe completar todos los campos.",
                        "Campos incompletos",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            // =========================
            // ID
            // =========================

            int id =
                    Integer.parseInt(
                            txtId
                                    .getText()
                                    .trim()
                    );

            if (id <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "El ID debe ser mayor que cero.",
                        "Dato incorrecto",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            // =========================
            // DATOS
            // =========================

            String titulo =
                    txtTitulo
                            .getText()
                            .trim();

            String autor =
                    txtAutor
                            .getText()
                            .trim();

            String categoria =
                    txtCategoria
                            .getText()
                            .trim();

            boolean disponible =
                    chkDisponible
                            .isSelected();

            // =========================
            // CONTROLADOR
            // =========================

            controller.registrarLibro(
                    id,
                    titulo,
                    autor,
                    categoria,
                    disponible
            );

            // =========================
            // ÉXITO
            // =========================

            JOptionPane.showMessageDialog(
                    this,
                    "El libro fue registrado correctamente.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limpiarCampos();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "El ID debe ser un número válido.",
                    "Dato incorrecto",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (RuntimeException e) {

            // =====================================================
            // AQUÍ SE MUESTRA EL MENSAJE DEL ID REPETIDO
            // =====================================================

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error al registrar",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // BUSCAR LIBRO
    // =====================================================

    private void buscarLibro() {

        panelResultado.setVisible(
                false
        );

        try {

            String textoId =
                    txtBuscarId
                            .getText()
                            .trim();

            // =========================
            // VACÍO
            // =========================

            if (textoId.isEmpty()) {

                mostrarEstadoError(
                        "Debe ingresar el ID del libro."
                );

                actualizarVentana();

                return;
            }

            // =========================
            // CONVERTIR ID
            // =========================

            int id =
                    Integer.parseInt(
                            textoId
                    );

            if (id <= 0) {

                mostrarEstadoError(
                        "El ID debe ser mayor que cero."
                );

                actualizarVentana();

                return;
            }

            // =========================
            // BUSCAR
            // =========================

            Libro libro =
                    controller.buscarLibro(
                            id
                    );

            // =========================
            // NO ENCONTRADO
            // =========================

            if (libro == null) {

                limpiarResultado();

                mostrarEstadoError(
                        "No se encontró un libro con el ID "
                                + id
                                + "."
                );

                actualizarVentana();

                return;
            }

            // =========================
            // MOSTRAR DATOS
            // =========================

            lblResultadoId.setText(
                    "ID: "
                            + libro.getId()
            );

            lblResultadoTitulo.setText(
                    "Título: "
                            + libro.getTitulo()
            );

            lblResultadoAutor.setText(
                    "Autor: "
                            + libro.getAutor()
            );

            lblResultadoCategoria.setText(
                    "Categoría: "
                            + libro.getCategoria()
            );

            lblResultadoDisponible.setText(
                    "Disponibilidad: "
                            + (
                            libro.isDisponible()
                                    ? "Disponible"
                                    : "No disponible"
                    )
            );

            panelResultado.setVisible(
                    true
            );

            lblEstadoBusqueda.setText(
                    "Libro encontrado correctamente."
            );

            lblEstadoBusqueda.setForeground(
                    VERDE
            );

            actualizarVentana();

            // Cuando aparece el resultado,
            // mantener visible la parte inferior.
            SwingUtilities.invokeLater(() -> {

                JScrollBar barra =
                        scrollPrincipal
                                .getVerticalScrollBar();

                barra.setValue(
                        barra.getMaximum()
                );
            });

        } catch (NumberFormatException e) {

            mostrarEstadoError(
                    "El ID debe ser un número válido."
            );

            actualizarVentana();

        } catch (RuntimeException e) {

            mostrarEstadoError(
                    "No fue posible realizar la búsqueda."
            );

            actualizarVentana();
        }
    }

    // =====================================================
    // CREAR CAMPO
    // =====================================================

    private JTextField crearCampo() {

        JTextField campo =
                new JTextField();

        campo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        campo.setForeground(
                TEXTO
        );

        campo.setBackground(
                Color.WHITE
        );

        campo.setPreferredSize(
                new Dimension(
                        210,
                        32
                )
        );

        return campo;
    }

    // =====================================================
    // CREAR ETIQUETA
    // =====================================================

    private JLabel crearEtiqueta(
            String texto) {

        JLabel etiqueta =
                new JLabel(
                        texto
                );

        etiqueta.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        etiqueta.setForeground(
                TEXTO
        );

        return etiqueta;
    }

    // =====================================================
    // CREAR TÍTULO DE SECCIÓN
    // =====================================================

    private JLabel crearTituloSeccion(
            String texto) {

        JLabel titulo =
                new JLabel(
                        texto
                );

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        19
                )
        );

        titulo.setForeground(
                AZUL_OSCURO
        );

        titulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        return titulo;
    }

    // =====================================================
    // CREAR DESCRIPCIÓN
    // =====================================================

    private JLabel crearDescripcion(
            String texto) {

        JLabel descripcion =
                new JLabel(
                        texto
                );

        descripcion.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        descripcion.setForeground(
                GRIS
        );

        descripcion.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        return descripcion;
    }

    // =====================================================
    // CREAR RESULTADO
    // =====================================================

    private JLabel crearResultado(
            String texto) {

        JLabel etiqueta =
                new JLabel(
                        texto
                );

        etiqueta.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        etiqueta.setForeground(
                TEXTO
        );

        return etiqueta;
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

        boton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        boton.setBackground(
                color
        );

        boton.setForeground(
                Color.WHITE
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

        boton.setPreferredSize(
                new Dimension(
                        ancho,
                        40
                )
        );

        return boton;
    }

    // =====================================================
    // MOSTRAR ERROR
    // =====================================================

    private void mostrarEstadoError(
            String mensaje) {

        panelResultado.setVisible(
                false
        );

        lblEstadoBusqueda.setText(
                mensaje
        );

        lblEstadoBusqueda.setForeground(
                new Color(
                        180,
                        65,
                        65
                )
        );
    }

    // =====================================================
    // LIMPIAR CAMPOS
    // =====================================================

    private void limpiarCampos() {

        txtId.setText(
                ""
        );

        txtTitulo.setText(
                ""
        );

        txtAutor.setText(
                ""
        );

        txtCategoria.setText(
                ""
        );

        chkDisponible.setSelected(
                false
        );

        txtId.requestFocus();
    }

    // =====================================================
    // LIMPIAR RESULTADO
    // =====================================================

    private void limpiarResultado() {

        lblResultadoId.setText(
                "ID: -"
        );

        lblResultadoTitulo.setText(
                "Título: -"
        );

        lblResultadoAutor.setText(
                "Autor: -"
        );

        lblResultadoCategoria.setText(
                "Categoría: -"
        );

        lblResultadoDisponible.setText(
                "Disponibilidad: -"
        );
    }

    // =====================================================
    // ACTUALIZAR VENTANA
    // =====================================================

    private void actualizarVentana() {

        revalidate();
        repaint();
    }

    // =====================================================
    // MAIN
    // =====================================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(
                () ->
                        new FrmLibro()
                                .setVisible(true)
        );
    }
}