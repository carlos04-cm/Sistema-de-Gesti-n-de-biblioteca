package view;

import controller.LibroController;
import model.LibroRepository;
import model.LibroRepositoryArchivo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FrmLibro extends JFrame {

    // =====================================================
    // COLORES
    // =====================================================

    private final Color AZUL_OSCURO = new Color(20, 55, 84);
    private final Color AZUL_ACTIVO = new Color(38, 105, 150);
    private final Color VERDE = new Color(38, 145, 105);

    private final Color FONDO = new Color(245, 247, 250);
    private final Color TEXTO = new Color(35, 45, 55);
    private final Color TEXTO_SECUNDARIO = new Color(100, 112, 123);
    private final Color BORDE = new Color(215, 222, 228);

    // =====================================================
    // CAMPOS DEL FORMULARIO
    // =====================================================

    private JTextField txtId;
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtCategoria;

    private JCheckBox chkDisponible;

    private JButton btnRegistrar;
    private JButton btnLimpiar;

    // =====================================================
    // BOTONES DEL MENÚ
    // =====================================================

    private JButton btnMenuRegistrar;
    private JButton btnVerTodos;

    // =====================================================
    // ZONA DONDE CAMBIARÁ EL CONTENIDO
    // =====================================================

    private JPanel panelContenido;

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

        setTitle("Sistema de Gestión de Biblioteca");

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

        principal.setBackground(FONDO);

        // Menú izquierdo
        principal.add(
                crearMenuLateral(),
                BorderLayout.WEST
        );

        // Zona derecha
        principal.add(
                crearZonaPrincipal(),
                BorderLayout.CENTER
        );

        setContentPane(principal);
    }

    // =====================================================
    // MENÚ LATERAL
    // =====================================================

    private JPanel crearMenuLateral() {

        JPanel menu =
                new JPanel();

        menu.setLayout(
                new BoxLayout(
                        menu,
                        BoxLayout.Y_AXIS
                )
        );

        menu.setBackground(
                AZUL_OSCURO
        );

        menu.setPreferredSize(
                new Dimension(
                        260,
                        650
                )
        );

        menu.setBorder(
                new EmptyBorder(
                        35,
                        25,
                        25,
                        25
                )
        );

        // =================================================
        // LOGO
        // =================================================

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
                                    75,
                                    75,
                                    Image.SCALE_SMOOTH
                            );

            lblLogo.setIcon(
                    new ImageIcon(
                            imagenEscalada
                    )
            );
        }

        lblLogo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =================================================
        // NOMBRE BIBLIOTECA
        // =================================================

        JLabel lblBiblioteca =
                new JLabel(
                        "BIBLIOTECA"
                );

        lblBiblioteca.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        lblBiblioteca.setForeground(
                Color.WHITE
        );

        lblBiblioteca.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =================================================
        // SUBTÍTULO
        // =================================================

        JLabel lblSistema =
                new JLabel(
                        "Sistema de Gestión"
                );

        lblSistema.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        lblSistema.setForeground(
                new Color(
                        195,
                        213,
                        225
                )
        );

        lblSistema.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =================================================
        // AGREGAR LOGO Y NOMBRE
        // =================================================

        menu.add(lblLogo);

        menu.add(
                Box.createVerticalStrut(12)
        );

        menu.add(lblBiblioteca);

        menu.add(
                Box.createVerticalStrut(5)
        );

        menu.add(lblSistema);

        menu.add(
                Box.createVerticalStrut(45)
        );

        // =================================================
        // BOTÓN REGISTRAR LIBRO
        // =================================================

        btnMenuRegistrar =
                crearBotonMenu(
                        "Registrar Libro",
                        true
                );

        btnMenuRegistrar.addActionListener(
                e -> mostrarRegistro()
        );

        menu.add(
                btnMenuRegistrar
        );

        menu.add(
                Box.createVerticalStrut(12)
        );

        // =================================================
        // BOTÓN VER TODOS LOS LIBROS
        // =================================================

        btnVerTodos =
                crearBotonMenu(
                        "Ver todos los libros",
                        false
                );

        btnVerTodos.addActionListener(
                e -> mostrarListaLibros()
        );

        menu.add(
                btnVerTodos
        );

        // Espacio flexible
        menu.add(
                Box.createVerticalGlue()
        );

        // =================================================
        // VERSIÓN
        // =================================================

        JLabel lblVersion =
                new JLabel(
                        "Versión 1.0"
                );

        lblVersion.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        11
                )
        );

        lblVersion.setForeground(
                new Color(
                        170,
                        195,
                        210
                )
        );

        lblVersion.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        menu.add(
                lblVersion
        );

        return menu;
    }

    // =====================================================
    // ZONA PRINCIPAL
    // =====================================================

    private JPanel crearZonaPrincipal() {

        JPanel zona =
                new JPanel(
                        new BorderLayout()
                );

        zona.setBackground(
                FONDO
        );

        zona.setPreferredSize(
                new Dimension(
                        840,
                        650
                )
        );

        // =================================================
        // ENCABEZADO
        // =================================================

        zona.add(
                crearEncabezado(),
                BorderLayout.NORTH
        );

        // =================================================
        // PANEL DINÁMICO
        // Aquí aparecerá Registrar o Ver todos
        // =================================================

        panelContenido =
                new JPanel(
                        new BorderLayout()
                );

        panelContenido.setBackground(
                FONDO
        );

        // Al iniciar mostramos Registrar Libro
        mostrarRegistro();

        zona.add(
                panelContenido,
                BorderLayout.CENTER
        );

        return zona;
    }

    // =====================================================
    // ENCABEZADO
    // =====================================================

    private JPanel crearEncabezado() {

        JPanel encabezado =
                new JPanel(
                        new BorderLayout()
                );

        encabezado.setBackground(
                AZUL_OSCURO
        );

        encabezado.setPreferredSize(
                new Dimension(
                        840,
                        110
                )
        );

        encabezado.setBorder(
                new EmptyBorder(
                        20,
                        35,
                        20,
                        35
                )
        );

        // =================================================
        // TÍTULO Y SUBTÍTULO
        // =================================================

        JPanel textos =
                new JPanel();

        textos.setLayout(
                new BoxLayout(
                        textos,
                        BoxLayout.Y_AXIS
                )
        );

        textos.setBackground(
                AZUL_OSCURO
        );

        JLabel titulo =
                new JLabel(
                        "Sistema de Gestión de Biblioteca"
                );

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        titulo.setForeground(
                Color.WHITE
        );

        titulo.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel subtitulo =
                new JLabel(
                        "Registro y administración de libros"
                );

        subtitulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        subtitulo.setForeground(
                new Color(
                        195,
                        213,
                        225
                )
        );

        subtitulo.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        textos.add(
                titulo
        );

        textos.add(
                Box.createVerticalStrut(6)
        );

        textos.add(
                subtitulo
        );

        encabezado.add(
                textos,
                BorderLayout.WEST
        );

        // =================================================
        // MENSAJE DERECHO
        // =================================================

        JLabel mensaje =
                new JLabel(
                        "Organizando conocimiento"
                );

        mensaje.setFont(
                new Font(
                        "Segoe UI",
                        Font.ITALIC,
                        12
                )
        );

        mensaje.setForeground(
                new Color(
                        195,
                        213,
                        225
                )
        );

        encabezado.add(
                mensaje,
                BorderLayout.EAST
        );

        return encabezado;
    }

    // =====================================================
    // MOSTRAR REGISTRO
    // =====================================================

    private void mostrarRegistro() {

        if (panelContenido == null) {
            return;
        }

        // Quitamos lo que estuviera mostrado
        panelContenido.removeAll();

        // =================================================
        // CONTENEDOR PARA CENTRAR EL FORMULARIO
        // =================================================

        JPanel centro =
                new JPanel(
                        new GridBagLayout()
                );

        centro.setBackground(
                FONDO
        );

        centro.setBorder(
                new EmptyBorder(
                        25,
                        45,
                        25,
                        45
                )
        );

        centro.add(
                crearPanelRegistro()
        );

        panelContenido.add(
                centro,
                BorderLayout.CENTER
        );

        // =================================================
        // CAMBIAR COLOR DEL MENÚ
        // =================================================

        cambiarBotonActivo(
                btnMenuRegistrar,
                btnVerTodos
        );

        // =================================================
        // ACTUALIZAR PANTALLA
        // =================================================

        panelContenido.revalidate();
        panelContenido.repaint();

        SwingUtilities.invokeLater(
                () -> txtId.requestFocus()
        );
    }

    // =====================================================
    // MOSTRAR LISTA DE LIBROS
    // =====================================================

    private void mostrarListaLibros() {

        if (panelContenido == null) {
            return;
        }

        // Quitamos el formulario de registro
        panelContenido.removeAll();

        /*
         * FrmListaLibros se conservará como una clase
         * independiente, pero ahora será un JPanel.
         *
         * De esta forma NO se abre una segunda ventana.
         */
        FrmListaLibros listaLibros =
                new FrmListaLibros(
                        controller
                );

        panelContenido.add(
                listaLibros,
                BorderLayout.CENTER
        );

        // =================================================
        // CAMBIAR COLOR DEL MENÚ
        // =================================================

        cambiarBotonActivo(
                btnVerTodos,
                btnMenuRegistrar
        );

        // =================================================
        // ACTUALIZAR PANTALLA
        // =================================================

        panelContenido.revalidate();
        panelContenido.repaint();
    }

    // =====================================================
    // CAMBIAR BOTÓN ACTIVO DEL MENÚ
    // =====================================================

    private void cambiarBotonActivo(
            JButton activo,
            JButton inactivo) {

        if (activo != null) {

            activo.setBackground(
                    AZUL_ACTIVO
            );
        }

        if (inactivo != null) {

            inactivo.setBackground(
                    AZUL_OSCURO
            );
        }
    }

    // =====================================================
    // PANEL REGISTRO
    // =====================================================

    private JPanel crearPanelRegistro() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                Color.WHITE
        );

        panel.setPreferredSize(
                new Dimension(
                        650,
                        430
                )
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                BORDE
                        ),

                        new EmptyBorder(
                                28,
                                40,
                                28,
                                40
                        )
                )
        );

        // =================================================
        // PARTE SUPERIOR
        // =================================================

        JPanel superior =
                new JPanel();

        superior.setLayout(
                new BoxLayout(
                        superior,
                        BoxLayout.Y_AXIS
                )
        );

        superior.setBackground(
                Color.WHITE
        );

        JLabel titulo =
                new JLabel(
                        "REGISTRAR LIBRO"
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

        JLabel descripcion =
                new JLabel(
                        "Complete la información para registrar un nuevo libro."
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
                Box.createVerticalStrut(20)
        );

        panel.add(
                superior,
                BorderLayout.NORTH
        );

        // =================================================
        // FORMULARIO CENTRAL
        // =================================================

        JPanel formulario =
                new JPanel(
                        new GridBagLayout()
                );

        formulario.setBackground(
                Color.WHITE
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        6,
                        5,
                        6,
                        5
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.anchor =
                GridBagConstraints.WEST;

        // =================================================
        // CAMPOS
        // =================================================

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

        chkDisponible.setFocusPainted(
                false
        );

        // =================================================
        // FILAS
        // =================================================

        agregarFila(
                formulario,
                gbc,
                0,
                "ID:",
                txtId
        );

        agregarFila(
                formulario,
                gbc,
                1,
                "Título:",
                txtTitulo
        );

        agregarFila(
                formulario,
                gbc,
                2,
                "Autor:",
                txtAutor
        );

        agregarFila(
                formulario,
                gbc,
                3,
                "Categoría:",
                txtCategoria
        );

        // =================================================
        // DISPONIBILIDAD
        // =================================================

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;

        formulario.add(
                crearEtiqueta(
                        "Disponibilidad:"
                ),
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 1;

        formulario.add(
                chkDisponible,
                gbc
        );

        panel.add(
                formulario,
                BorderLayout.CENTER
        );

        // =================================================
        // BOTONES INFERIORES
        // =================================================

        JPanel zonaBotones =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                12,
                                10
                        )
                );

        zonaBotones.setBackground(
                Color.WHITE
        );

        zonaBotones.setPreferredSize(
                new Dimension(
                        570,
                        65
                )
        );

        btnRegistrar =
                crearBotonRegistrar();

        btnLimpiar =
                crearBotonLimpiar();

        zonaBotones.add(
                btnRegistrar
        );

        zonaBotones.add(
                btnLimpiar
        );

        panel.add(
                zonaBotones,
                BorderLayout.SOUTH
        );

        // =================================================
        // ACCIONES
        // =================================================

        btnRegistrar.addActionListener(
                e -> registrarLibro()
        );

        btnLimpiar.addActionListener(
                e -> limpiarCampos()
        );

        return panel;
    }

    // =====================================================
    // AGREGAR FILA
    // =====================================================

    private void agregarFila(
            JPanel panel,
            GridBagConstraints gbc,
            int fila,
            String texto,
            JComponent componente) {

        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.weightx = 0;

        panel.add(
                crearEtiqueta(
                        texto
                ),
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 1;

        panel.add(
                componente,
                gbc
        );
    }

    // =====================================================
    // CAMPO DE TEXTO
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
                        350,
                        36
                )
        );

        campo.setMinimumSize(
                new Dimension(
                        350,
                        36
                )
        );

        campo.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                BORDE
                        ),

                        new EmptyBorder(
                                5,
                                10,
                                5,
                                10
                        )
                )
        );

        return campo;
    }

    // =====================================================
    // ETIQUETA
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
                        13
                )
        );

        etiqueta.setForeground(
                TEXTO
        );

        etiqueta.setPreferredSize(
                new Dimension(
                        120,
                        34
                )
        );

        return etiqueta;
    }

    // =====================================================
    // BOTÓN REGISTRAR
    // =====================================================

    private JButton crearBotonRegistrar() {

        JButton boton =
                new JButton(
                        "Registrar Libro"
                );

        boton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        boton.setBackground(
                VERDE
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

        Dimension tamano =
                new Dimension(
                        180,
                        42
                );

        boton.setPreferredSize(
                tamano
        );

        boton.setMinimumSize(
                tamano
        );

        boton.setMaximumSize(
                tamano
        );

        return boton;
    }

    // =====================================================
    // BOTÓN LIMPIAR
    // =====================================================

    private JButton crearBotonLimpiar() {

        JButton boton =
                new JButton(
                        "Limpiar Campos"
                );

        boton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        boton.setBackground(
                new Color(
                        245,
                        247,
                        249
                )
        );

        boton.setForeground(
                new Color(
                        75,
                        85,
                        95
                )
        );

        boton.setFocusPainted(
                false
        );

        boton.setBorder(
                BorderFactory.createLineBorder(
                        BORDE
                )
        );

        boton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        Dimension tamano =
                new Dimension(
                        165,
                        42
                );

        boton.setPreferredSize(
                tamano
        );

        boton.setMinimumSize(
                tamano
        );

        boton.setMaximumSize(
                tamano
        );

        return boton;
    }

    // =====================================================
    // BOTONES DEL MENÚ
    // =====================================================

    private JButton crearBotonMenu(
            String texto,
            boolean activo) {

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

        boton.setForeground(
                Color.WHITE
        );

        boton.setBackground(
                activo
                        ? AZUL_ACTIVO
                        : AZUL_OSCURO
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

        boton.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        Dimension tamano =
                new Dimension(
                        210,
                        48
                );

        boton.setPreferredSize(
                tamano
        );

        boton.setMinimumSize(
                tamano
        );

        boton.setMaximumSize(
                tamano
        );

        boton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        return boton;
    }

    // =====================================================
    // REGISTRAR LIBRO
    // =====================================================

    private void registrarLibro() {

        try {

            // =================================================
            // VALIDAR CAMPOS VACÍOS
            // =================================================

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

            // =================================================
            // VALIDAR ID
            // =================================================

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

            // =================================================
            // OBTENER DATOS
            // =================================================

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

            // =================================================
            // REGISTRAR
            // =================================================

            controller.registrarLibro(
                    id,
                    titulo,
                    autor,
                    categoria,
                    disponible
            );

            // =================================================
            // MENSAJE
            // =================================================

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

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error al registrar",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // LIMPIAR CAMPOS
    // =====================================================

    private void limpiarCampos() {

        txtId.setText("");
        txtTitulo.setText("");
        txtAutor.setText("");
        txtCategoria.setText("");

        chkDisponible.setSelected(
                false
        );

        txtId.requestFocus();
    }

    // =====================================================
    // MAIN
    // =====================================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    FrmLibro ventana =
                            new FrmLibro();

                    ventana.setVisible(
                            true
                    );
                }
        );
    }
}