package Reservas;

import Reservas.Espacio;
import Reservas.Reserva;
import Reservas.Tramo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Aula extends Espacio {

    private ArrayList<LocalDate> diasExamen;

    public Aula(String nombre, String ubicacion, int capacidad) {
        super(nombre, ubicacion, capacidad);
        this.diasExamen = new ArrayList<>();
    }
    
    public Aula(String nombre, String ubicacion) {
        super(nombre, ubicacion);
        this.diasExamen = new ArrayList<>();
    }

 

    public ArrayList<LocalDate> getDiasExamen() {
        return new ArrayList<>(diasExamen);
    }

// Consulta respecto a los dias de examen
    public void setDiasExamen(LocalDate... fechas) {
        Collections.addAll(diasExamen, fechas);
    }
    //metodos sobreescritas
    @Override
    public boolean consultarDisponibilidad(LocalDate dia, Tramo tramo) {
        if (diasExamen.contains(dia)) {
            return false;
        }
        return super.consultarDisponibilidad(dia, tramo);
    }

    @Override
    public boolean usuarioPermitido(String usuario, LocalDate dia) {
        for (Reserva reserva : this.reservas) {
            String usuar = reserva.getUsuario();
            if (usuario.equals(usuar)) {
                if (reserva.getOcupacion().getDia().getDayOfMonth() == dia.getDayOfMonth()) {
                    return false;
                }
            }
        }

        return true;
    }

    
    
    //Metodo Clone
    @Override
    public Aula clone() {
        Aula copia = (Aula) super.clone();
        copia.diasExamen = new ArrayList<>(copia.diasExamen);
        return copia;
    }
    
    //Metodo toString
    @Override
    public String toString() {
        return "Aula{" + "diasExamen=" + diasExamen + '}';
    }
}
