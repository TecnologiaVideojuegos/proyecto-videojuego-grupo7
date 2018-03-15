import items.Arma;
import items.Armadura;
import otros.Habilidad;
import otros.Inventario;
import personajes.Horacia;
import personajes.Jugador;
import personajes.Kibito;
import personajes.Mordeim;
import java.util.ArrayList;

public class NewMain {
    
    private static ArrayList<String> pjs = null;
    public static void main(String[] args) {
        ArrayList<String> pjs = new ArrayList<String>();
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