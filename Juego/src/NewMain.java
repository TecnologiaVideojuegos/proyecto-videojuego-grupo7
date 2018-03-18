import items.Arma;
import items.Armadura;
import otros.Habilidad;
import otros.Inventario;
import personajes.Horacia;
import personajes.Jugador;
import personajes.Kibito;
import personajes.Mordeim;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import personajes.Personaje;

public class NewMain {
    static ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    
    public static void main(String[] args) {
        
        //Horacia horacia = new Horacia();
        //jugadores.add(horacia);
        //System.out.println(jugadores.get(0).getNombre());
        //guardarJugadores();
        cargarJugadores();
        System.out.println(jugadores.get(0).getNombre());
        System.out.println(jugadores.get(0).getHpActual());
        
        
        
        /*ArrayList<String> pjs = new ArrayList<String>();
        pjs.add("hasas");
        pjs.add("Mordeim");
        Arma arma = new Arma(10,10,"aRMITA","desc", pjs, 1, 100000, 100000);
        Arma armaBuena = new Arma(99,99,"aRMITAcrema","desc", pjs, 1, 100000, 100000);
        Armadura armadura = new Armadura(10, "armadurita", "desc", pjs,1,100000,999999);
        Armadura armaduraBuena = new Armadura(10, "armadurita", "desc", pjs,1,100000,999999);
        ArrayList<Habilidad> hab = new ArrayList<Habilidad>();
        /*Inventario in = new Inventario();
        Mordeim m = new Mordeim(arma, armadura, hab, in);
        System.out.println(m.toString());
        m.setExp(102);
        if(m.puedeSubir())
            m.subirNivel();
        System.out.println(m.toString());
        m.setExp(120);
        if(m.puedeSubir())
            m.subirNivel();
        System.out.println(m.toString());
        
        System.out.println(m.getArma().getNombre());
        m.cambiarArma(armaBuena);
        System.out.println(m.toString());
        System.out.println(m.getArma().getNombre());*/
    }
    public static void cargarJugadores() {
        try {
            FileInputStream istreamPer = new FileInputStream("jugadores.dat");
            ObjectInputStream oisPer = new ObjectInputStream(istreamPer);            
            jugadores = (ArrayList<Jugador>) oisPer.readObject();
            istreamPer.close();
        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error de clase no encontrada: " + cnfe.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void guardarJugadores() {
        try {
            if (!jugadores.isEmpty()) {
                FileOutputStream ostreamPer = new FileOutputStream("jugadores.dat");
                ObjectOutputStream oosPer = new ObjectOutputStream(ostreamPer);
                oosPer.writeObject(jugadores);
                ostreamPer.close();
            } else {
                System.out.println("Error: No hay datos...");
            }

        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}