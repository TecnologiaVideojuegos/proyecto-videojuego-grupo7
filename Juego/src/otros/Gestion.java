package otros;

import enemigos.Enemigo;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import personajes.Jugador;

public class Gestion implements Serializable{
    private ArrayList<Jugador> jugs;
    private ArrayList<ArrayList<Enemigo>> enem;
    private Inventario inv = new Inventario();
    private int mapaActual;
    private int controlEscenas;
    
    private static final long serialVersionUID = 10L;
    
    public Gestion(){
        jugs = new ArrayList<>();
        enem = new ArrayList<ArrayList<Enemigo>>();
        this.mapaActual=0;
        this.controlEscenas=0;
    }

    public ArrayList<Jugador> getJugs() {
        return jugs;
    }

    public void setJugs(ArrayList<Jugador> jugs) {
        this.jugs = jugs;
    }

    public ArrayList<ArrayList<Enemigo>> getEnem() {
        return enem;
    }

    public void setEnem(ArrayList<ArrayList<Enemigo>> enem) {
        this.enem = enem;
    }

    public Inventario getInv() {
        return inv;
    }

    public void setInv(Inventario inv) {
        this.inv = inv;
    }

    public int getMapaActual() {
        return mapaActual;
    }

    public void setMapaActual(int mapaActual) {
        this.mapaActual = mapaActual;
    }

    public int getControlEscenas() {
        return controlEscenas;
    }

    public void setControlEscenas(int controlEscenas) {
        this.controlEscenas = controlEscenas;
    }
    
    
    
    
    public ArrayList<Jugador> cargarJugadores() {
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        try {
            FileInputStream istreamPer = new FileInputStream("BaseDatos/jugadores.dat");
            ObjectInputStream oisPer = new ObjectInputStream(istreamPer);            
            jugadores = (ArrayList<Jugador>) oisPer.readObject();
            inv = jugadores.get(0).getInventario();
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
    
    public void guardarGrupoEnemigos(ArrayList<ArrayList<Enemigo>> enemigos, String ruta) {
        try {
            if (!enemigos.isEmpty()) {
                FileOutputStream ostreamPer = new FileOutputStream(ruta);
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
    
    public ArrayList<ArrayList<Enemigo>> cargarGrupoEnemigos(String ruta) {
        ArrayList<ArrayList<Enemigo>> enemigos = new ArrayList<ArrayList<Enemigo>>();
        try {
            FileInputStream istreamPer = new FileInputStream(ruta);
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
