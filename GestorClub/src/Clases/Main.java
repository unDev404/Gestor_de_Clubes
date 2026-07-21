package Clases;

public class Main {
    public static void main(String[] args) {
        SistemaClubes sistema = new SistemaClubes();
        
        // 1. Al iniciar el programa, cargar los datos previos (si existen)
        Persistencia.cargarDatos(sistema);

        // ... Aquí va toda tu lógica de interfaz o código (agregar alumnos, crear proyectos, etc.) ...

        // 2. Al cerrar el programa (o presionar un botón "Guardar"), guardar el estado actual
        Persistencia.guardarDatos(sistema);
    }
}