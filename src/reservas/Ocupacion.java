package Reservas;

import java.time.LocalDate;
import java.util.Objects;

public class Ocupacion {
    private final LocalDate dia;
    private final Tramo tramo;
    
    public Ocupacion(LocalDate dia, Tramo tramo) {
        this.dia = dia;
        this.tramo = tramo;
    }
    
    //Metodos de Consulta
    public LocalDate getDia() { 
        return dia; }
    public Tramo getTramo()
    { return tramo; }
    
    //Metodos de la clase Object
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.dia);
        hash = 11 * hash + Objects.hashCode(this.tramo);
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
        final Ocupacion other = (Ocupacion) obj;
        if (!Objects.equals(this.dia, other.dia)) {
            return false;
        }
        return this.tramo == other.tramo;
    }
    
    //Metodo toString
    @Override
    public String toString() {
        return "Ocupacion{" + "dia=" + dia + ", tramo=" + tramo + '}';
    } 
}
