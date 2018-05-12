package basesdatos;

import enemigos.Capitan;
import enemigos.Enemigo;
import enemigos.Paladin;
import enemigos.Soldado;
import java.util.ArrayList;
import otros.Gestion;

public final class BaseDatosCardinal{
    Gestion ges = new Gestion();
    private ArrayList<Enemigo> party;
    private ArrayList<Enemigo> party2;    
    private ArrayList<Enemigo> party3;     
    private ArrayList<Enemigo> party4;     
    private ArrayList<Enemigo> party5;     
    private ArrayList<Enemigo> party6;    
    private ArrayList<ArrayList<Enemigo>> enem;

    private Soldado s211, s212, s213;
    private Soldado s231, s232, s233;
    private Soldado s251, s252, s253;
    private Paladin p211, p212, p213;
    private Paladin p231, p232, p233;
    private Paladin p251, p252, p253;
    private Capitan c211;
    private Capitan c231;
    private Capitan c251;
    //Base datos enemigos
    public BaseDatosCardinal(){
        //int nivel, int hp, int ataque, int defensa
        initArrays();
        enem = new ArrayList<ArrayList<Enemigo>>();

        enemigosCatacumba();
        partyNivel21();
        initArrays();
        partyNivel23();
        initArrays();
        partyNivel25();
        ges.guardarGrupoEnemigos(enem, "BaseDatos/enemigosCardinal.dat");
    }
    public void enemigosCatacumba(){
        //int id, int nivel, int hp, int ataque, int defensa
        s211 = new Soldado(1, 21, 1500, 350, 60);
        s212 = new Soldado(2, 21, 1500, 350, 60);
        s213 = new Soldado(3, 21, 1500, 350, 60);
        s231 = new Soldado(1, 23, 1800, 390, 65);
        s232 = new Soldado(2, 23, 1800, 390, 65);
        s233 = new Soldado(3, 23, 1800, 390, 65);
        s251 = new Soldado(1, 25, 2000, 430, 70);
        s252 = new Soldado(2, 25, 2000, 430, 70);
        s253 = new Soldado(3, 25, 2000, 430, 70);
        //
        p211 = new Paladin(1, 21, 1500, 400, 70);
        p212 = new Paladin(2, 21, 1500, 400, 70);
        p213 = new Paladin(3, 21, 1500, 400, 70);
        p231 = new Paladin(1, 23, 1830, 450, 80);
        p232 = new Paladin(2, 23, 1830, 450, 80);
        p233 = new Paladin(3, 23, 1830, 450, 80);
        p251 = new Paladin(1, 25, 2080, 480, 90);
        p252 = new Paladin(2, 25, 2080, 480, 90);
        p253 = new Paladin(3, 25, 2080, 480, 90);
        //
        c211 = new Capitan(1, 21, 2500, 350, 60);
        c231 = new Capitan(1, 23, 2700, 380, 70);
        c251 = new Capitan(1, 25, 2900, 420, 80);
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
    
    public void partyNivel21(){
        party.add(s211);
        party.add(s212);
        party.add(s213);
        enem.add(party);
        //
        party2.add(p211);
        party2.add(s211);
        party2.add(s212);
        enem.add(party2);
        //
        party3.add(c211);
        party3.add(s211);
        party3.add(s212);
        enem.add(party3);
        
        party4.add(s211);
        party4.add(p211);
        party4.add(c211);
        enem.add(party4);
        //
        party5.add(c211);
        party5.add(p211);
        party5.add(p212);
        enem.add(party5);
        //
        party6.add(p211);
        party6.add(p212);
        party6.add(p213);
        enem.add(party6);
        
    }
    public void partyNivel23(){
        party.add(s231);
        party.add(s232);
        party.add(s233);
        enem.add(party);
        //
        party2.add(p231);
        party2.add(s231);
        party2.add(s232);
        enem.add(party2);
        //
        party3.add(c231);
        party3.add(s231);
        party3.add(s232);
        enem.add(party3);
        
        party4.add(s231);
        party4.add(p231);
        party4.add(c231);
        enem.add(party4);
        //
        party5.add(c231);
        party5.add(p231);
        party5.add(p232);
        enem.add(party5);
        //
        party6.add(p231);
        party6.add(p232);
        party6.add(p233);
        enem.add(party6);
    }
    public void partyNivel25(){
        party.add(s251);
        party.add(s252);
        party.add(s253);
        enem.add(party);
        //
        party2.add(p251);
        party2.add(s251);
        party2.add(s252);
        enem.add(party2);
        //
        party3.add(c251);
        party3.add(s251);
        party3.add(s252);
        enem.add(party3);
        
        party4.add(s251);
        party4.add(p251);
        party4.add(c251);
        enem.add(party4);
        //
        party5.add(c251);
        party5.add(p251);
        party5.add(p252);
        enem.add(party5);
        //
        party6.add(p251);
        party6.add(p252);
        party6.add(p253);
        enem.add(party6);
    }
    
}
