/*Se han tenido que crear seis ArrayList porque con uno y el metodo clear o remove, no estaba
dejando mas que guardar la ultima party n veces. No importa porque esta clase solo es para crear 
partys y no importa mucho que sobrecarge un poco porque se va usar una sola vez antes de entregar
el juego para crear la base datos de enemigos, es mas es una clase que no deberia estar incluida dentro del juego,
pero yo la subo para que vayamos creando las partys segun luego las necesitemos
Ahora mismo esta con los enemigos que tiene el primer mapa, y como dijimos solo le saldran los
enemigos correspondientes a tu nivel, es decir, si eres 4 los del 3, ya que los del 1 no
tendria mucha logica, pero esto se debe implementar despues en combate con el metodo cargar BaseDatosEnemigos
e ir recorriendo el array que se nos generara y quedarnos solo con los enemigos de nuestro nivel
Una idea que se me ocurre es luego dividir la "base de datos" en arrays por nivel para no tener
que recorrer n partys que no necesitamos, es mas en el mapa bosque con tener los tres arrays de nivel 1, 3 y 5 
nos valdria. Se genera numero aleatorio de 1 a 6 para que tipo party te toca y se elige la mas acorde a tu nivel,
pero esto podemos verlo en la reunion mejor si no es demasiado correcta mi idea

Para DAVID: Si puedes hacer alguna mini prueba de enemigos generados desde el fichero en tu estado de 
combate simplemente por ver si funciona o tenemos que cambiar algo, con que te cojas la primera party despues 
de cargar el fichero. Por si tienes dudas de como coger un enemigo de un ArrayList<ArrayList> simplemnte
encadenas dos get (ejemplo en el main )y te lo puedes pillar. Tienen foto pero como bien dijiste petan si no se 
llaman en entorno slick, asique eso habra que hacer una ñapa despues*/

import enemigos.Enemigo;
import enemigos.Goblin;
import enemigos.Rata;
import enemigos.Spider;
import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import otros.Gestion;

public final class BaseDatosEnemigos{
    Gestion ges = new Gestion();
    private ArrayList<Enemigo> party;
    private ArrayList<Enemigo> party2;    
    private ArrayList<Enemigo> party3;     
    private ArrayList<Enemigo> party4;     
    private ArrayList<Enemigo> party5;     
    private ArrayList<Enemigo> party6;    
    private ArrayList<ArrayList<Enemigo>> enem;
    private Rata rat1;
    private Rata rat3;
    private Rata rat5;
    private Goblin gob1;
    private Goblin gob3;
    private Goblin gob5;
    private Spider sp1;
    private Spider sp3;
    private Spider sp5;
    //Base datos enemigos
    public BaseDatosEnemigos() throws SlickException{
        //int nivel, int hp, int ataque, int defensa
        initArrays();
        enem = new ArrayList<ArrayList<Enemigo>>();
        rat1 = new Rata(1, 100, 50, 30);
        rat3 = new Rata(3, 150, 60, 40);
        rat5 = new Rata(5, 200, 70, 50);
        gob1 = new Goblin(1, 130, 40, 40);
        gob3 = new Goblin(3, 170, 50, 50);
        gob5 = new Goblin(5, 220, 60, 60);
        sp1 = new Spider(1, 80, 60, 20);
        sp3 = new Spider(3, 120, 70, 30);
        sp5 = new Spider(5, 160, 80, 40);
        partyNivel1();
        initArrays();
        partyNivel3();
        initArrays();
        partyNivel5();
        ges.guardarGrupoEnemigos(enem);
    }
    public void initArrays(){
        party = new ArrayList<>();
        party2 = new ArrayList<>();
        party3 = new ArrayList<>();
        party4 = new ArrayList<>();                
        party5 = new ArrayList<>();
        party6 = new ArrayList<>();        
    }
    public ArrayList<ArrayList<Enemigo>> getEnem() {
        return enem;
    }
    
    public void partyNivel1(){
        party.add(rat1);
        party.add(rat1);
        party.add(rat1);
        enem.add(party);
        //
        party2.add(gob1);
        party2.add(gob1);
        party2.add(gob1);
        enem.add(party2);
        //
        party3.add(sp1);
        party3.add(sp1);
        party3.add(sp1);
        enem.add(party3);
        
        party4.add(gob1);
        party4.add(sp1);
        party4.add(rat1);
        enem.add(party4);
        //
        party5.add(gob1);
        party5.add(gob1);
        party5.add(rat1);
        enem.add(party5);
        //
        party6.add(sp1);
        party6.add(sp1);
        party6.add(rat1);
        enem.add(party6);
        
    }
    public void partyNivel3(){
        party.add(rat3);
        party.add(rat3);
        party.add(rat3);
        enem.add(party);
        //
        party2.add(gob3);
        party2.add(gob3);
        party2.add(gob3);
        enem.add(party2);
        //
        party3.add(sp3);
        party3.add(sp3);
        party3.add(sp3);
        enem.add(party3);
        //
        party4.add(gob3);
        party4.add(sp3);
        party4.add(rat3);
        enem.add(party4);
        //
        party5.add(gob3);
        party5.add(gob3);
        party5.add(rat3);
        enem.add(party5);
        //
        party6.add(sp3);
        party6.add(sp3);
        party6.add(rat3);
        enem.add(party6);
    }
    public void partyNivel5(){
        //Nivel 5
        party.add(rat5);
        party.add(rat5);
        party.add(rat5);
        enem.add(party);
        //
        party2.add(gob5);
        party2.add(gob5);
        party2.add(gob5);
        enem.add(party2);
        //
        party3.add(sp5);
        party3.add(sp5);
        party3.add(sp5);
        enem.add(party3);
        //
        party4.add(gob5);
        party4.add(sp5);
        party4.add(rat5);
        enem.add(party4);
        //
        party5.add(gob5);
        party5.add(gob5);
        party5.add(rat5);
        enem.add(party5);
        //
        party6.add(sp5);
        party6.add(sp5);
        party6.add(rat5);
        enem.add(party6);
    }
    
}
