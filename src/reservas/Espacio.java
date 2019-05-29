package Reservas;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public abstract class Espacio implements Cloneable {

    protected String nombre;
    protected String ubicacion;
    protected String descripcion;
    protected int capacidad;
    protected HashSet<Reserva> reservas;

    protected Espacio(String nombre, String ubicacion, int capacidad) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.descripcion = nombre + " - " + ubicacion;
        this.capacidad = capacidad;
        this.reservas = new HashSet<>();
    }
    
    protected Espacio(String nombre, String ubicacion) {
        this(nombre, ubicacion, 30);
    this.reservas = new HashSet<>();
    }


    
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    //Metodos
    public boolean consultarDisponibilidad(LocalDate dia, Tramo tramo) {
        for (Reserva it : reservas) {
            Ocupacion op = it.getOcupacion();
            if (dia.equals(op.getDia()) && tramo.equals(op.getTramo())) {
                return false;
            }
        }

        return true;
    }

    public abstract boolean usuarioPermitido(String usuario, LocalDate dia);

   /* Metodo Plantilla*/
    
    public Reserva reservar(String usuario, LocalDate dia, Tramo tramo) {
        if (consultarDisponibilidad(dia, tramo)) {
            if (usuarioPermitido(usuario, dia)) {
                Reserva reserva = new Reserva(usuario, this, new Ocupacion(dia, tramo));
                this.reservas.add(reserva);
                return reserva;
            }
        }

        return null;
    }

    public Reserva reservar(String usuario, LocalDate dia) {
        return reservar(usuario, dia, Tramo.MAÑANA);
    }

    public boolean cancelarReseva(Reserva reserva) {
        return this.reservas.remove(reserva);
    }

    //Metodo Clone
    @Override
    protected Espacio clone() {
        try {
            Espacio copia = (Espacio) super.clone();
            copia.reservas = new HashSet<>();
            return copia;
        } catch (CloneNotSupportedException e) {
            System.err.println("ERROR EN LA COPIA: " + e.getMessage());
        }
        
        return null;
    }
    
    //Metodo toString
    @Override
    public String toString() {
        return "Espacio{" + "Nombre :" + nombre + ", ubicacion :" + ubicacion + ", descripcion :" + descripcion + ", capacidad :" + capacidad + ", reservas:" + reservas + '}';
    }
}
