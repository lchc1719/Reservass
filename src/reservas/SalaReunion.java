package Reservas;

import Reservas.Espacio;
import Reservas.Reserva;
import Reservas.Tramo;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SalaReunion extends Espacio {

    private HashMap<String, ArrayList<Reserva>> controlUs;

    public SalaReunion(String nombre, String ubicacion, int capacidad) {
        super(nombre, ubicacion, capacidad);
        this.controlUs = new HashMap<>();
    }

    
     public HashSet<String> getUsuarios() {
        return new HashSet<>(controlUs.keySet());
    }
    
    public SalaReunion(String nombre, String ubicacion) {
        super(nombre, ubicacion);
        this.controlUs = new HashMap<>();
    }

       //Funcionalidades respecto al ControlUS
    public ArrayList<Reserva> getReservas(String usuario) 
    {
        if (this.controlUs.containsKey(usuario)) {
            return this.controlUs.get(usuario);
        }
        return null;
    }

    //Funciones sobreescritas
    @Override
    public boolean consultarDisponibilidad(LocalDate dia, Tramo tramo) {
        DayOfWeek val = dia.getDayOfWeek();
        if (val.equals(DayOfWeek.SATURDAY) || val.equals(DayOfWeek.SUNDAY)) {
            return false;
        }
        return super.consultarDisponibilidad(dia, tramo);
    }

    @Override
    public Reserva reservar(String usuario, LocalDate dia, Tramo tramo) {
        ArrayList<Reserva> lista;
        Reserva reserva = super.reservar(usuario, dia, tramo);
        if (controlUs.containsKey(usuario)) {
            lista = controlUs.get(usuario);
            lista.add(reserva);
            controlUs.replace(usuario, lista);
        } else {
            lista = new ArrayList<>();
            lista.add(reserva);
            controlUs.put(usuario, lista);
        }

        return reserva;
    }

    @Override
    public boolean usuarioPermitido(String usuario, LocalDate dia) {
        LocalDate fechaActual = LocalDate.now();
        ArrayList<Reserva> lista = getReservas(usuario);
        if (lista != null) {
            for (Reserva reserva : lista) {
                if (fechaActual.equals(reserva.getOcupacion().getDia())) {
                    return false;
                } else if (reserva.getOcupacion().getDia().isAfter(fechaActual)) {
                    return false;
                }
            }
        }

        return true;
    }

    
    //Metodo Clone
    @Override
    protected SalaReunion clone() {
        SalaReunion copia = (SalaReunion) super.clone();
        copia.controlUs = new HashMap<>(copia.controlUs);
        return copia;
    }

    //Metodo toString
    @Override
    public String toString() {
        return "SalaReunion{" + "control=" + controlUs + '}';
    }
}
