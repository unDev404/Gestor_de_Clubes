package Clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo {
    
    // Lista de adyacencia: Nodo -> Lista de sus conexiones (Enlaces)
    private Map<Estudiante_Nodo, List<Enlace>> listaAdyacencia;

    public Grafo() {
        // El HashMap en Java maneja colisiones por direccionamiento cerrado
        this.listaAdyacencia = new HashMap<>(); 
    }

    public void registrarNodo(Estudiante_Nodo e) {
        listaAdyacencia.putIfAbsent(e, new ArrayList<>());
    }

    public void limpiarAristas() {
        for (List<Enlace> enlaces : listaAdyacencia.values()) {
            enlaces.clear();
        }
    }

    public void agregarArista(Estudiante_Nodo origen, Estudiante_Nodo destino, Proyecto_Arista proyecto) {
        listaAdyacencia.get(origen).add(new Enlace(proyecto, destino));
    }

    public Map<Estudiante_Nodo, List<Enlace>> getListaAdyacencia() {
        return listaAdyacencia;
    }
}