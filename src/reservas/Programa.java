package Reservas;



import Reservas.Reserva;
import Reservas.Espacio;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Programa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Crea el aula A.01 en el Aulario Norte con 165 puestos de capacidad.
        Aula a1 = new Aula("A.01", "Aulario Norte", 165);
        
        //En el aula establece como días de exámenes 10 y 11 de enero de 2019
        a1.setDiasExamen(LocalDate.of(2019, Month.JANUARY, 10), LocalDate.of(2019, Month.JANUARY, 11));
        
        /*Crea dos copias del aula anterior. Establece el nombre de la primera como “A.02” y de la
        segunda como “A.03”*/
        Aula a2 = a1.clone();
        a2.setNombre("A.02");
        Aula a3 = a1.clone();
        a3.setNombre("A.03");
        
        /*Crea las salas de reuniones 2.01, 2.02 y 2.03 de la Facultad de Informática, las dos primeras
        para 12 personas, y la tercera con la capacidad por defecto.*/
        SalaReunion s1 = new SalaReunion("2.01", "Facultad de Informatica", 12);
        SalaReunion s2 = new SalaReunion("2.02", "Facultad de Informatica", 12);
        SalaReunion s3 = new SalaReunion("2.03", "Facultad de Informatica");
        
        //Crea una lista de espacios. Añade las aulas y salas de reuniones a la lista.
        List<Espacio> espacios = new ArrayList<>(6);
        Collections.addAll(espacios, a1, a2, a3, s1, s2, s3);
        
        /*Recorre la colección de espacios y muestra por la consola su información (método
        toString).*/
        for (Espacio e : espacios) {
            System.out.println(e.toString());
        }
        
        /*Recorre la colección de espacios y para cada uno: el usuario “juan@um.es” intenta realizar
        la reserva para el día 9 de enero por tarde. El usuario “pepe@um.es” intenta hacer dos
        reservas para el 9 de enero, una por la mañana y otra por la tarde, pero antes de solicitar la
        reserva consulta si el día y tramo están disponibles. El usuario “pepe@um.es” también
        intenta reservar el día 10 de enero por la mañana
        
        Para cada reserva solicitada, si ha sido aceptada, muestra la información por la consola
        (toString). En caso contrario, indica el espacio que no ha podido ser reservado.*/
        System.out.println("\n----------------------\n");
        for (Espacio e : espacios) {
            Reserva reserva;
            if (e.consultarDisponibilidad(LocalDate.of(2019, Month.JANUARY, 9), Tramo.TARDE)) {
                reserva = e.reservar("juan@um.es", LocalDate.of(2019, Month.JANUARY, 9), Tramo.TARDE);
                System.out.println(reserva.toString());
            }
            
            if (e.consultarDisponibilidad(LocalDate.of(2019, Month.JANUARY, 9), Tramo.MAÑANA)) {
                reserva = e.reservar("pepe@um.es", LocalDate.of(2019, Month.JANUARY, 9), Tramo.MAÑANA);
                System.out.println(reserva.toString());
            }
            
            if (e.consultarDisponibilidad(LocalDate.of(2019, Month.JANUARY, 9), Tramo.TARDE)) {
                reserva = e.reservar("pepe@um.es", LocalDate.of(2019, Month.JANUARY, 9), Tramo.TARDE);
                System.out.println(reserva.toString());
            }
            
            if (e.consultarDisponibilidad(LocalDate.of(2019, Month.JANUARY, 10), Tramo.MAÑANA)) {
                reserva = e.reservar("pepe@um.es", LocalDate.of(2019, Month.JANUARY, 10), Tramo.TARDE);
                System.out.println(reserva.toString());
            }
            System.out.println("\n----------------------\n");
        }
        
        //Recorre la colección de espacios y muestra por la consola su información (toString).
        for (Espacio e : espacios) {
            System.out.println(e.toString());
        }
        
        System.out.println("\n----------------------\n");
        /*Recorre la colección de espacios y para cada uno: si el espacio es de tipo sala de reunión,
        muestra los usuarios que han hecho reservas y las reservas efectuadas por “juan@um.es”.*/
        for (Espacio e : espacios) {
            if (e instanceof SalaReunion) {
                System.out.println("Usuarios Que han Hecho Reservas");
                System.out.println(((SalaReunion) e).getUsuarios());
                System.out.println("Reservas Hechas Por Juan");
                System.out.println(((SalaReunion) e).getReservas("juan@um.es"));
            }
        }
    }
}
