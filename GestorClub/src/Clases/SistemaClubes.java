package Clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaClubes {
    
    private Map<String, Estudiante_Nodo> todosLosEstudiantes;
    private Map<String, Proyecto_Arista> todosLosProyectos;
    private Grafo grafo;

    public SistemaClubes() {
        this.todosLosEstudiantes = new HashMap<>();
        this.todosLosProyectos = new HashMap<>();
        this.grafo = new Grafo();
    }

    public void registrarEstudiante(Estudiante_Nodo e) {
        // Se usa la CI como clave principal para búsquedas rápidas
        todosLosEstudiantes.put(e.getCI(), e);
        grafo.registrarNodo(e);
    }

    public void crearProyecto(Proyecto_Arista p) {
        todosLosProyectos.put(p.getIdProyecto(), p);
    }

    public void agregarEstudianteAProyecto(String CI, String idProyecto) {
        Estudiante_Nodo e = todosLosEstudiantes.get(CI);
        Proyecto_Arista p = todosLosProyectos.get(idProyecto);

        if (e != null && p != null) {
            p.agregarParticipante(e);
            reconstruirGrafo();
        }
    }
    
    public Estudiante_Nodo buscarEstudiantePorCI(String CI) {
        return todosLosEstudiantes.get(CI);
    }

    
    public Proyecto_Arista buscarProyectoPorId(String idProyecto) {
        return todosLosProyectos.get(idProyecto);
    }

    public void eliminarEstudianteDeProyecto(String CI, String idProyecto) {
        Estudiante_Nodo e = todosLosEstudiantes.get(CI);
        Proyecto_Arista p = todosLosProyectos.get(idProyecto);

        if (e != null && p != null) {
            p.eliminarParticipante(e);
            reconstruirGrafo();
        }
    }

    private void reconstruirGrafo() {
        // 1. Limpiamos las conexiones actuales del grafo
        grafo.limpiarAristas();

        // 2. Volvemos a conectar a todos los integrantes de cada proyecto (Clique)
        for (Proyecto_Arista p : todosLosProyectos.values()) {
            List<Estudiante_Nodo> miembros = p.getParticipantes();

            for (int i = 0; i < miembros.size(); i++) {
                for (int j = i + 1; j < miembros.size(); j++) {
                    Estudiante_Nodo e1 = miembros.get(i);
                    Estudiante_Nodo e2 = miembros.get(j);

                    // Se crean las aristas bidireccionales en el grafo
                    grafo.agregarArista(e1, e2, p);
                    grafo.agregarArista(e2, e1, p);
                }
            }
        }
    }

    // Métodos útiles para integrarlos con interfaces gráficas
    public Grafo getGrafo() { return grafo; }
    
    public List<Proyecto_Arista> getTodosLosProyectos() { 
        return new ArrayList<>(todosLosProyectos.values()); 
    }
    
    public List<Estudiante_Nodo> getTodosLosEstudiantes() { 
        return new ArrayList<>(todosLosEstudiantes.values()); 
    }
}