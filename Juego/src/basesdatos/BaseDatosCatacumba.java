package basesdatos;

import enemigos.Enemigo;
import enemigos.Esqueleto;
import enemigos.Fanatico;
import enemigos.Mimico;
import enemigos.Zombie;
import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import otros.Gestion;

public final class BaseDatosCatacumba{
    Gestion ges = new Gestion();
    private ArrayList<Enemigo> party;
    private ArrayList<Enemigo> party2;    
    private ArrayList<Enemigo> party3;     
    private ArrayList<Enemigo> party4;     
    private ArrayList<Enemigo> party5;     
    private ArrayList<Enemigo> party6;    
    private ArrayList<ArrayList<Enemigo>> enem;

    private Esqueleto esq81, esq82; 
    private Esqueleto esq111, esq112;
    private Esqueleto esq141, esq142;
    private Zombie zom81, zom82;
    private Zombie zom111, zom112;
    private Zombie zom141, zom142;
    private Fanatico fana81, fana82;
    private Fanatico fana111, fana112;
    private Fanatico fana141, fana142;
    private Mimico mi81;
    private Mimico mi111;
    private Mimico mi141;
    //Base datos enemigos
    public BaseDatosCatacumba(){
        //int nivel, int hp, int ataque, int defensa
        initArrays();
        enem = new ArrayList<ArrayList<Enemigo>>();

        enemigosCatacumba();
        partyNivel8();
        initArrays();
        partyNivel11();
        initArrays();
        partyNivel14();
        ges.guardarGrupoEnemigos(enem, "BaseDatos/enemigosCatacumba.dat");
    }
    public void enemigosCatacumba(){
        //int id, int nivel, int hp, int ataque, int defensa  //Bandido ban12=new Bandido(2,6,650,250,100);
        esq81 = new Esqueleto(1, 8, 400, 160, 30);
        esq82 = new Esqueleto(2, 8, 400, 160, 30);
        esq111 = new Esqueleto(1, 11, 800, 255, 50);
        esq112 = new Esqueleto(2, 11, 800, 255, 50);
        esq141 = new Esqueleto(1, 14, 1000, 275, 60);
        esq142 = new Esqueleto(2, 14, 1000, 275, 60);
        //
        zom81 = new Zombie(1, 8, 1000, 170, 40);
        zom82 = new Zombie(2, 8, 1000, 170, 40);
        zom111 = new Zombie(1, 11, 1300, 250, 50);
        zom112 = new Zombie(2, 11, 1300, 250, 50);
        zom141 = new Zombie(1, 14, 1700, 260, 60);
        zom142 = new Zombie(2, 14, 1700, 260, 60);
        //
        fana81 = new Fanatico(1, 8, 400, 190, 40);
        fana82 = new Fanatico(2, 8, 400, 190, 40);
        fana111 = new Fanatico(1, 11, 700, 250, 50);
        fana112 = new Fanatico(2, 11, 700, 250, 50);
        fana141 = new Fanatico(1, 14, 900, 260, 60);
        fana142 = new Fanatico(2, 14, 900, 260, 60);
        //
        mi81 = new Mimico(1, 8, 3, 1, 3000);
        mi111 = new Mimico(1, 11, 4, 1, 3000);
        mi141 = new Mimico(1, 14, 5, 1, 3200);
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
    
    public void partyNivel8(){
        party.add(esq81);
        party.add(zom81);
        party.add(fana81);
        enem.add(party);
        //
        party2.add(esq81);
        party2.add(zom81);
        party2.add(zom82);
        enem.add(party2);
        //
        party3.add(mi81);
        party3.add(fana81);
        party3.add(esq81);
        enem.add(party3);
        
        party4.add(mi81);
        party4.add(zom81);
        party4.add(fana81);
        enem.add(party4);
        //
        party5.add(esq81);
        party5.add(fana81);
        party5.add(fana82);
        enem.add(party5);
        //
        party6.add(esq81);
        party6.add(esq82);
        party6.add(zom81);
        enem.add(party6);
        
    }
    public void partyNivel11(){
        party.add(esq111);
        party.add(zom111);
        party.add(fana111);
        enem.add(party);
        //
        party2.add(esq111);
        party2.add(zom111);
        party2.add(zom112);
        enem.add(party2);
        //
        party3.add(mi111);
        party3.add(fana111);
        party3.add(esq111);
        enem.add(party3);
        
        party4.add(mi111);
        party4.add(zom111);
        party4.add(fana111);
        enem.add(party4);
        //
        party5.add(esq111);
        party5.add(fana111);
        party5.add(fana112);
        enem.add(party5);
        //
        party6.add(esq111);
        party6.add(esq112);
        party6.add(zom111);
        enem.add(party6);
    }
    public void partyNivel14(){
        party.add(esq141);
        party.add(zom141);
        party.add(fana141);
        enem.add(party);
        //
        party2.add(esq141);
        party2.add(zom141);
        party2.add(zom142);
        enem.add(party2);
        //
        party3.add(mi141);
        party3.add(fana141);
        party3.add(esq141);
        enem.add(party3);
        
        party4.add(mi141);
        party4.add(zom141);
        party4.add(fana141);
        enem.add(party4);
        //
        party5.add(esq141);
        party5.add(fana141);
        party5.add(fana142);
        enem.add(party5);
        //
        party6.add(esq141);
        party6.add(esq142);
        party6.add(zom141);
        enem.add(party6);
    }
    
}
