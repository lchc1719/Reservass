package Reservas;

import java.util.Objects;

public class Reserva {
    
    private final String usuario;
    private final Espacio espacio;
    private final Ocupacion ocupacion;

    public Reserva(String usuario, Espacio espacio, Ocupacion ocupacion) {
        this.usuario = usuario;
        this.espacio = espacio;
        this.ocupacion = ocupacion;
    }
    
   
    public String getUsuario() {
        return usuario; }
    public Espacio getEspacio() { 
        return espacio; }
    public Ocupacion getOcupacion() {
        return ocupacion; }
    
    
    
    
    
    
    //Metodos de la Clase Object
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.usuario);
        hash = 67 * hash + Objects.hashCode(this.espacio);
        hash = 67 * hash + Objects.hashCode(this.ocupacion);
        return hash;
    }

    
    
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reserva other = (Reserva) obj;
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.espacio, other.espacio)) {
            return false;
        }
        return Objects.equals(this.ocupacion, other.ocupacion);
    }
}
