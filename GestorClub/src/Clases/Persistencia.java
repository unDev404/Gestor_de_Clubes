package Clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Persistencia {
    
    private static final String ARCHIVO_ESTUDIANTES = "estudiantes.txt";
    private static final String ARCHIVO_PROYECTOS = "proyectos.txt";

    /**
     * Guarda todas las listas actuales en archivos de texto.
     */
    public static void guardarDatos(SistemaClubes sistema) {
        // 1. Guardar Estudiantes
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_ESTUDIANTES))) {
            for (Estudiante_Nodo e : sistema.getTodosLosEstudiantes()) {
                // Formato: CI;nombre;apellido;carrera
                pw.println(e.getCI() + ";" + e.getNombre() + ";" + e.getApellido() + ";" + e.getCarrera());
            }
        } catch (IOException ex) {
            System.out.println("Error al guardar el archivo de estudiantes: " + ex.getMessage());
        }

        // 2. Guardar Proyectos
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_PROYECTOS))) {
            for (Proyecto_Arista p : sistema.getTodosLosProyectos()) {
                // Empezamos la línea con ID y Nombre del proyecto
                StringBuilder linea = new StringBuilder(p.getIdProyecto() + ";" + p.getNombreProyecto() + ";");
                
                // Añadimos las Cédulas de los participantes separadas por coma
                List<Estudiante_Nodo> participantes = p.getParticipantes();
                for (int i = 0; i < participantes.size(); i++) {
                    linea.append(participantes.get(i).getCI());
                    if (i < participantes.size() - 1) {
                        linea.append(",");
                    }
                }
                pw.println(linea.toString());
            }
        } catch (IOException ex) {
            System.out.println("Error al guardar el archivo de proyectos: " + ex.getMessage());
        }
    }

    /**
     * Lee los archivos de texto y reconstruye el estado del sistema.
     */
    public static void cargarDatos(SistemaClubes sistema) {
        // 1. Cargar Estudiantes
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_ESTUDIANTES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 4) {
                    Estudiante_Nodo e = new Estudiante_Nodo(datos[0], datos[1], datos[2], datos[3]);
                    sistema.registrarEstudiante(e);
                }
            }
        } catch (IOException ex) {
            System.out.println("Aviso: No se encontro el archivo estudiantes.txt. Se creara uno nuevo al guardar.");
        }

        // 2. Cargar Proyectos e integrarlos al Grafo
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_PROYECTOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length >= 2) {
                    // Instanciamos el proyecto
                    Proyecto_Arista p = new Proyecto_Arista(datos[0], datos[1]);
                    sistema.crearProyecto(p);
                    
                    // Si el arreglo tiene 3 posiciones, significa que hay estudiantes en ese proyecto
                    if (datos.length == 3 && !datos[2].isEmpty()) {
                        String[] cedulasParticipantes = datos[2].split(",");
                        for (String ci : cedulasParticipantes) {
                            // Esto asocia al alumno y automáticamente genera las aristas en el grafo
                            sistema.agregarEstudianteAProyecto(ci, datos[0]);
                        }
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Aviso: No se encontro el archivo proyectos.txt. Se creara uno nuevo al guardar.");
        }
    }
}