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

    private Rata rat11, rat12, rat13;
    private Rata rat31, rat32, rat33;
    private Rata rat51, rat52, rat53;
    private Goblin gob11, gob12, gob13;
    private Goblin gob31, gob32, gob33;
    private Goblin gob51, gob52, gob53;
    private Spider sp11, sp12, sp13;
    private Spider sp31, sp32, sp33;
    private Spider sp51, sp52, sp53;
    //Base datos enemigos
    public BaseDatosEnemigos() throws SlickException{
        //int nivel, int hp, int ataque, int defensa
        initArrays();
        enem = new ArrayList<ArrayList<Enemigo>>();

        enemigosBosque();
        partyNivel1();
        initArrays();
        partyNivel3();
        initArrays();
        partyNivel5();
        ges.guardarGrupoEnemigos(enem, "BaseDatos/enemigosBosque.dat");
    }
    public void enemigosBosque() throws SlickException{
        rat11 = new Rata(1, 1, 100, 50, 30);
        rat12 = new Rata(2, 1, 100, 50, 30);
        rat13 = new Rata(3, 1, 100, 50, 30);
        rat31 = new Rata(1, 3, 150, 60, 40);
        rat32 = new Rata(2, 3, 150, 60, 40);
        rat33 = new Rata(3, 3, 150, 60, 40);
        rat51 = new Rata(1, 5, 200, 70, 50);
        rat52 = new Rata(2, 5, 200, 70, 50);
        rat53 = new Rata(3, 5, 200, 70, 50);
        gob11 = new Goblin(1, 1, 110, 55, 40);
        gob12 = new Goblin(2, 1, 110, 55, 40);
        gob13 = new Goblin(3, 1, 110, 55, 40);
        gob31 = new Goblin(1, 3, 150, 65, 50);
        gob32 = new Goblin(2, 3, 150, 65, 50);
        gob33 = new Goblin(3, 3, 150, 65, 50);
        gob51 = new Goblin(1, 5, 200, 75, 60);
        gob52 = new Goblin(2, 5, 200, 75, 60);
        gob53 = new Goblin(3, 5, 200, 75, 60);
        sp11 = new Spider(1, 1, 80, 45, 20);
        sp12 = new Spider(2, 1, 80, 45, 20);
        sp13 = new Spider(3, 1, 80, 45, 20);
        sp31 = new Spider(1, 3, 100, 55, 30);
        sp32 = new Spider(2, 3, 100, 55, 30);
        sp33 = new Spider(3, 3, 100, 55, 30);
        sp51 = new Spider(1, 5, 120, 70, 40);
        sp52 = new Spider(2, 5, 120, 70, 40);
        sp53 = new Spider(3, 5, 120, 70, 40);
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
        party.add(rat11);
        party.add(rat12);
        party.add(rat13);
        enem.add(party);
        //
        party2.add(gob11);
        party2.add(gob12);
        party2.add(gob13);
        enem.add(party2);
        //
        party3.add(sp11);
        party3.add(sp12);
        party3.add(sp13);
        enem.add(party3);
        
        party4.add(gob11);
        party4.add(sp11);
        party4.add(rat11);
        enem.add(party4);
        //
        party5.add(gob11);
        party5.add(gob12);
        party5.add(rat11);
        enem.add(party5);
        //
        party6.add(sp11);
        party6.add(sp12);
        party6.add(rat11);
        enem.add(party6);
        
    }
    public void partyNivel3(){
        party.add(rat31);
        party.add(rat32);
        party.add(rat33);
        enem.add(party);
        //
        party2.add(gob31);
        party2.add(gob32);
        party2.add(gob33);
        enem.add(party2);
        //
        party3.add(sp31);
        party3.add(sp32);
        party3.add(sp33);
        enem.add(party3);
        //
        party4.add(gob31);
        party4.add(sp31);
        party4.add(rat31);
        enem.add(party4);
        //
        party5.add(gob31);
        party5.add(gob32);
        party5.add(rat31);
        enem.add(party5);
        //
        party6.add(sp31);
        party6.add(sp32);
        party6.add(rat31);
        enem.add(party6);
    }
    public void partyNivel5(){
        //Nivel 5
        party.add(rat51);
        party.add(rat52);
        party.add(rat53);
        enem.add(party);
        //
        party2.add(gob51);
        party2.add(gob52);
        party2.add(gob53);
        enem.add(party2);
        //
        party3.add(sp51);
        party3.add(sp52);
        party3.add(sp53);
        enem.add(party3);
        //
        party4.add(gob51);
        party4.add(sp51);
        party4.add(rat51);
        enem.add(party4);
        //
        party5.add(gob51);
        party5.add(gob52);
        party5.add(rat51);
        enem.add(party5);
        //
        party6.add(sp51);
        party6.add(sp52);
        party6.add(rat51);
        enem.add(party6);
    }
    
}
