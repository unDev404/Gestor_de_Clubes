package Clases;

public class Enlace {
    
    private Proyecto_Arista proyecto;
    private Estudiante_Nodo destino;

    public Enlace(Proyecto_Arista proyecto, Estudiante_Nodo destino) {
        this.proyecto = proyecto;
        this.destino = destino;
    }

    public Proyecto_Arista getProyecto() { 
        return proyecto; 
    }
    
    public Estudiante_Nodo getDestino() { 
        return destino; 
    }
}