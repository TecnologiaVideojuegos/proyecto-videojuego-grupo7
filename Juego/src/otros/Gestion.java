package otros;

import enemigos.Enemigo;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import personajes.Jugador;

public class Gestion {
    public ArrayList<Jugador> jugs;
    public ArrayList<ArrayList<Enemigo>> enem;
    
    public Gestion(){
        jugs = new ArrayList<>();
        enem = new ArrayList<ArrayList<Enemigo>>();
    }
    
    public ArrayList<Jugador> cargarJugadores() {
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        try {
            FileInputStream istreamPer = new FileInputStream("BaseDatos/jugadores.dat");
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
        return jugadores;
    }

    public void guardarJugadores(ArrayList<Jugador> jugadores) {
        try {
            if (!jugadores.isEmpty()) {
                FileOutputStream ostreamPer = new FileOutputStream("BaseDatos/jugadores.dat");
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
    
    public void guardarGrupoEnemigos(ArrayList<ArrayList<Enemigo>> enemigos) {
        try {
            if (!enemigos.isEmpty()) {
                FileOutputStream ostreamPer = new FileOutputStream("BaseDatos/enemigos.dat");
                ObjectOutputStream oosPer = new ObjectOutputStream(ostreamPer);
                oosPer.writeObject(enemigos);
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
    
    public ArrayList<ArrayList<Enemigo>> cargarGrupoEnemigos() {
        ArrayList<ArrayList<Enemigo>> enemigos = new ArrayList<ArrayList<Enemigo>>();
        try {
            FileInputStream istreamPer = new FileInputStream("BaseDatos/enemigos.dat");
            ObjectInputStream oisPer = new ObjectInputStream(istreamPer);            
            enemigos = (ArrayList<ArrayList<Enemigo>>) oisPer.readObject();
            istreamPer.close();
        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error de clase no encontrada: " + cnfe.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return enemigos;
    }
}
