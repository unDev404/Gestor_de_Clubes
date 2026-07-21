package Clases;

import java.util.Objects;

public class Estudiante_Nodo {
    
    private String nombre, apellido, CI, carrera;

    public Estudiante_Nodo(String CI, String nombre, String apellido, String carrera) {
        this.CI = CI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carrera = carrera;
    }

    public String getCI() { return CI; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getCarrera() { return carrera; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudiante_Nodo that = (Estudiante_Nodo) o;
        return Objects.equals(CI, that.CI);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CI);
    }
}