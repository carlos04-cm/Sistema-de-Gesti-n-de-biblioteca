# 📚 Sistema de Gestión de Biblioteca

Proyecto académico desarrollado en **Java** utilizando **Java Swing**, **Maven** y la arquitectura **Modelo–Vista–Controlador (MVC)**.

El sistema permite registrar y consultar libros, almacenando la información de forma local en un archivo de texto.

## 👥 Integrantes

- Carlos Mario Sierra
- Mario Alemán

## 🎯 Objetivo del proyecto

Desarrollar un Sistema de Gestión de Biblioteca de manera incremental, aplicando principios de diseño de software y una arquitectura organizada que permita agregar nuevas funcionalidades a medida que evoluciona el proyecto.

## 🚀 Segundo incremento funcional

En este segundo incremento se mantuvo la funcionalidad de **Registrar Libro** desarrollada anteriormente y se incorporó la opción de **Buscar/Consultar Libro por ID**.

También se realizaron mejoras en la interfaz gráfica para hacerla más organizada, clara y fácil de utilizar.

## ⚙️ Funcionalidades actuales

### Registrar libro

El sistema permite registrar un libro con los siguientes datos:

- ID
- Título
- Autor
- Categoría
- Disponibilidad

Antes de guardar la información, el sistema realiza validaciones para comprobar que los campos estén completos y que el ID ingresado sea válido.

También se verifica que no exista previamente otro libro con el mismo ID.

### Buscar libro por ID

El usuario puede consultar un libro ingresando su ID.

Cuando el libro existe, el sistema muestra:

- ID
- Título
- Autor
- Categoría
- Disponibilidad

Si el libro no existe, se muestra un mensaje informando que no se encontró ningún registro con el ID ingresado.

### Mostrar y ocultar búsqueda

El módulo de búsqueda permanece **oculto por defecto**.

Cuando el usuario presiona el botón **Buscar Libro**, se muestra el módulo de consulta. El mismo botón permite posteriormente ocultarlo.

Esta decisión permite aprovechar mejor el espacio disponible y evita sobrecargar visualmente la interfaz.

### Limpiar formulario

El botón **Limpiar** permite borrar los datos ingresados en el formulario de registro para facilitar el ingreso de un nuevo libro.

## 💾 Persistencia de datos

Los libros registrados se almacenan en:

`libros.txt`

Cada libro se guarda utilizando una estructura similar a:

`ID;Título;Autor;Categoría;Disponibilidad`

Ejemplo:

`1;Cien años de soledad;Gabriel García Márquez;Literatura;true`

Esto permite conservar los registros después de cerrar la aplicación y posteriormente consultarlos mediante su ID.

## 🏗️ Arquitectura

El proyecto utiliza una organización basada en **MVC (Modelo–Vista–Controlador)**.

### Modelo

Contiene las clases relacionadas con los datos y su almacenamiento:

- `Libro.java`
- `LibroRepository.java`
- `LibroRepositoryArchivo.java`

`Libro` representa la entidad principal del sistema.

`LibroRepository` define las operaciones relacionadas con la persistencia.

`LibroRepositoryArchivo` implementa dichas operaciones utilizando el archivo `libros.txt`.

### Vista

La interfaz gráfica se encuentra principalmente en:

`FrmLibro.java`

Esta clase utiliza **Java Swing** para mostrar el formulario de registro, los botones y el módulo de búsqueda.

### Controlador

La lógica que comunica la interfaz con el repositorio se encuentra en:

`LibroController.java`

Actualmente permite realizar operaciones como:

- Registrar un libro.
- Validar IDs duplicados.
- Buscar un libro por ID.

## 📂 Estructura del proyecto

```text
SistemaBiblioteca/
│
├── src/
│   └── main/
│       ├── java/
│       │   ├── controller/
│       │   │   └── LibroController.java
│       │   │
│       │   ├── model/
│       │   │   ├── Libro.java
│       │   │   ├── LibroRepository.java
│       │   │   └── LibroRepositoryArchivo.java
│       │   │
│       │   └── view/
│       │       └── FrmLibro.java
│       │
│       └── resources/
│           └── imagenes/
│               └── logo_biblioteca.png
│
├── libros.txt
└── pom.xml
```

## 🛠️ Tecnologías utilizadas

- Java 21
- Java Swing
- Apache Maven
- NetBeans
- Programación orientada a objetos
- Arquitectura MVC
- Persistencia mediante archivos de texto

## 🧩 Principios de diseño

El proyecto busca mantener una separación entre las responsabilidades de cada componente.

El controlador trabaja con la interfaz `LibroRepository`, en lugar de depender directamente de una implementación específica de almacenamiento.

Esto facilita la evolución futura del sistema y permite que la persistencia pueda cambiar sin modificar innecesariamente otras partes de la aplicación.

## ▶️ Ejecución

1. Clonar o descargar el proyecto.
2. Abrir la carpeta `SistemaBiblioteca` en NetBeans.
3. Verificar que Java 21 esté configurado.
4. Compilar el proyecto con Maven.
5. Ejecutar la clase:

```text
view.FrmLibro
```

6. La ventana del Sistema de Gestión de Biblioteca se abrirá automáticamente.

## 🔄 Evolución del proyecto

El proyecto se desarrolla mediante incrementos.

### Primer incremento

- Diseño inicial de la interfaz.
- Registro de libros.
- Persistencia en `libros.txt`.
- Implementación de la arquitectura MVC.

### Segundo incremento

- Mejora visual de la interfaz.
- Incorporación del logo y encabezado.
- Botón Limpiar.
- Búsqueda de libros por ID.
- Validación de IDs.
- Prevención de registros con ID duplicado.
- Módulo de búsqueda oculto por defecto.
- Visualización de los datos del libro encontrado.
- Mensajes de validación y errores.

## 🔮 Próximas mejoras

Para futuros incrementos se podrán implementar funcionalidades como:

- Ver todos los libros registrados.
- Actualizar información de un libro.
- Eliminar libros.
- Mejorar la persistencia de datos.
- Incorporar una base de datos.
- Gestión de usuarios.
- Préstamos y devoluciones.
- Historial de préstamos.
- Sistema de inicio de sesión.
- Reportes.

## 📌 Estado actual

**Segundo incremento funcional desarrollado.**

Actualmente el sistema permite **registrar libros y buscar/consultar libros por ID**, manteniendo la información almacenada en `libros.txt` y una estructura organizada mediante MVC.
