package Clases;

import java.util.ArrayList;
import java.util.List;

public class Proyecto_Arista {
    
    private String idProyecto, nombreProyecto;
    private List<Estudiante_Nodo> participantes;

    public Proyecto_Arista(String idProyecto, String nombreProyecto) {
        this.idProyecto = idProyecto;
        this.nombreProyecto = nombreProyecto;
        this.participantes = new ArrayList<>();
    }

    public String getIdProyecto() { return idProyecto; }
    
    public String getNombreProyecto() { return nombreProyecto; }
    
    public List<Estudiante_Nodo> getParticipantes() { return participantes; }

    public void agregarParticipante(Estudiante_Nodo e) {
        if (!participantes.contains(e)) {
            participantes.add(e);
        }
    }

    public void eliminarParticipante(Estudiante_Nodo e) {
        participantes.remove(e);
    }
}
