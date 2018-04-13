import enemigos.Enemigo;
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
import org.newdawn.slick.SlickException;
import otros.Gestion;
import personajes.Personaje;

public class NewMain {
    static ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    
    public static void main(String[] args) throws SlickException {
        
//        Gestion ges= new Gestion();
//        ArrayList<ArrayList<Enemigo>> enem;
//        enem= ges.cargarGrupoEnemigos();
//        for (int i = 0; i < enem.get(0).size(); i++) {
//            System.out.println(enem.get(0).get(i));    
//        }
//        System.out.println("-------");
//        ArrayList<Personaje> enemigos= new ArrayList<Personaje>();
//        enemigos.add((Personaje)enem.get(0).get(0));
//        enemigos.add((Personaje)enem.get(0).get(1));
//        enemigos.add((Personaje)enem.get(0).get(2));
//        enemigos.get(0).setHpActual(1);
//        for (int i = 0; i < enem.get(0).size(); i++) {
//            System.out.println(enem.get(0).get(i));    
//        }
//        System.out.println("-----");
//        for (int i = 0; i < enemigos.size(); i++) {
//            enemigos.get(i).setHpActual(enemigos.get(i).getHp());
//            
//        }
//        for (int i = 0; i < enem.get(0).size(); i++) {
//            System.out.println(enem.get(0).get(i));    
//        }
        
        //Horacia horacia = new Horacia();
        //jugadores.add(horacia);
        //System.out.println(jugadores.get(0).getNombre());
        //guardarJugadores();
        //cargarJugadores();
        //System.out.println(jugadores.get(0).getNombre());
        //System.out.println(jugadores.get(0).getHpActual());
        
//        BaseDatosEnemigos bd = new BaseDatosEnemigos();
//        for (int i = 0; i < bd.getEnem().size(); i++) {
//            System.out.println(bd.getEnem().get(i).size());
//            for (int j = 0; j < bd.getEnem().get(i).size(); j++) {
//                System.out.println(bd.getEnem().get(i).get(j).toString());
//            }
//        }
        //System.out.println(bd.getEnem().get(0).get(0).getNombre());
        
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
    
}