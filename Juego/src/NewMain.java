import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.SlickException;
import otros.Gestion;
import personajes.Horacia;
import personajes.Jugador;
import personajes.Personaje;

public class NewMain {
    static ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    
    public static void main(String[] args) throws SlickException {
        /*BaseDatosEnemigos bd = new BaseDatosEnemigos();
        for (int i = 0; i < bd.getEnem().size(); i++) {
            System.out.println(bd.getEnem().get(i).size());
            for (int j = 0; j < bd.getEnem().get(i).size(); j++) {
                System.out.println(bd.getEnem().get(i).get(j).toString());
            }
        }*/
        Gestion ges = new Gestion();
        ges.enem = ges.cargarGrupoEnemigos("BaseDatos/enemigosBosque.dat");
        for (int i = 0; i < ges.enem.size(); i++) {
            System.out.println(ges.enem.get(i));
        }
        
        ArrayList<Personaje> partyEnemiga = new ArrayList<Personaje>();
        ArrayList<Personaje> party = new ArrayList<Personaje>();
        Random rand = new Random();
        int ini = 0; //dependiendo del nivel emepzamos a buscar en una pos u otra
        int fin = rand.nextInt(6);//Como hay 6 tipos de party elegimos entre la 0-5
        int total;
        Horacia h = new Horacia();
        party.add(h);
        int nivel=0;
        //Nos quedamos el mayor nivel
        for (int i = 0; i < party.size(); i++) {
            if (party.get(i).getNivel() > nivel)
                nivel = party.get(i).getNivel();
        }
        
        //Esto es la comprobacion dentro de bosque, en otro mapa sera distinto
        //tenemos que ver como optimizarlo
        if (nivel <= 1)
            ini = 0;
        else if (nivel <= 3)
            ini = 6;
        else
            ini = 12;
        
        //Con total sacamos la party que queremos para guardarla en una partyEnemiga
        //para que David la use en su Combate
        total = ini + fin;
        for (int i = 0; i < ges.enem.get(total).size(); i++) {
            partyEnemiga.add(ges.enem.get(total).get(i));
        }
        System.out.println("-----------------------");
        for (int i = 0; i < partyEnemiga.size(); i++) {
            System.out.println(partyEnemiga.get(i));
        }
        //Luego acordarse de darle la vida de nuevo como vimos ayer David
        
    }
    
}