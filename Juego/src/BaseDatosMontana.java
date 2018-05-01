import enemigos.Enemigo;
import enemigos.MiniGrifo;
import enemigos.Minotauro;
import enemigos.Murciegalo;
import enemigos.Slime;
import java.util.ArrayList;
import otros.Gestion;

public final class BaseDatosMontana{
    Gestion ges = new Gestion();
    private ArrayList<Enemigo> party;
    private ArrayList<Enemigo> party2;    
    private ArrayList<Enemigo> party3;     
    private ArrayList<Enemigo> party4;     
    private ArrayList<Enemigo> party5;     
    private ArrayList<Enemigo> party6;    
    private ArrayList<ArrayList<Enemigo>> enem;

    private Slime slime161, slime162; 
    private Slime slime181, slime182; 
    private Slime slime201, slime202; 
    private Murciegalo mur161, mur162;
    private Murciegalo mur181, mur182;
    private Murciegalo mur201, mur202;
    private MiniGrifo mini161, mini162, mini163;
    private MiniGrifo mini181, mini182, mini183;
    private MiniGrifo mini201, mini202, mini203;
    private Minotauro mi161, mi162;
    private Minotauro mi181, mi182;
    private Minotauro mi201, mi202;
    //Base datos enemigos
    public BaseDatosMontana(){
        //int nivel, int hp, int ataque, int defensa
        initArrays();
        enem = new ArrayList<ArrayList<Enemigo>>();

        enemigosCatacumba();
        partyNivel16();
        initArrays();
        partyNivel18();
        initArrays();
        partyNivel20();
        ges.guardarGrupoEnemigos(enem, "BaseDatos/enemigosMontana.dat");
    }
    public void enemigosCatacumba(){
        //int id, int nivel, int hp, int ataque, int defensa
        slime161 = new Slime(1, 16, 300, 50, 50);
        slime162 = new Slime(2, 16, 300, 50, 50);
        slime181 = new Slime(1, 18, 320, 55, 55);
        slime182 = new Slime(2, 18, 320, 55, 55);
        slime201 = new Slime(1, 20, 340, 60, 60);
        slime202 = new Slime(2, 20, 340, 60, 60);
        mur161 = new Murciegalo(1, 16, 320, 55, 55);
        mur162 = new Murciegalo(2, 16, 320, 55, 55);
        mur181 = new Murciegalo(1, 18, 330, 60, 60);
        mur182 = new Murciegalo(2, 18, 330, 60, 60);
        mur201 = new Murciegalo(1, 20, 370, 70, 70);
        mur202 = new Murciegalo(2, 20, 370, 70, 70);
        mini161 = new MiniGrifo(1, 16, 280, 45, 55);
        mini162 = new MiniGrifo(2, 16, 280, 45, 55);
        mini163 = new MiniGrifo(3, 16, 280, 45, 55);
        mini181 = new MiniGrifo(1, 18, 310, 50, 65);
        mini182 = new MiniGrifo(2, 18, 310, 50, 65);
        mini183 = new MiniGrifo(3, 18, 310, 50, 65);
        mini201 = new MiniGrifo(1, 20, 340, 60, 75);
        mini202 = new MiniGrifo(2, 20, 340, 60, 75);
        mini203 = new MiniGrifo(3, 20, 340, 60, 75);
        mi161 = new Minotauro(1, 16, 400, 70, 50);
        mi162 = new Minotauro(2, 16, 400, 70, 50);
        mi181 = new Minotauro(1, 18, 420, 75, 55);
        mi182 = new Minotauro(2, 18, 420, 75, 55);
        mi201 = new Minotauro(1, 20, 450, 85, 65);
        mi202 = new Minotauro(2, 20, 450, 85, 65);
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
    
    public void partyNivel16(){
        party.add(slime161);
        party.add(mur161);
        party.add(mini161);
        enem.add(party);
        //
        party2.add(mur161);
        party2.add(mini161);
        party2.add(mi161);
        enem.add(party2);
        //
        party3.add(slime161);
        party3.add(mi161);
        party3.add(mi162);
        enem.add(party3);
        
        party4.add(slime161);
        party4.add(slime162);
        party4.add(mini161);
        enem.add(party4);
        //
        party5.add(mur161);
        party5.add(mur162);
        party5.add(mi161);
        enem.add(party5);
        //
        party6.add(mini161);
        party6.add(mini162);
        party6.add(mini163);
        enem.add(party6);
        
    }
    public void partyNivel18(){
        party.add(slime181);
        party.add(mur181);
        party.add(mini181);
        enem.add(party);
        //
        party2.add(mur181);
        party2.add(mini181);
        party2.add(mi181);
        enem.add(party2);
        //
        party3.add(slime181);
        party3.add(mi181);
        party3.add(mi182);
        enem.add(party3);
        
        party4.add(slime181);
        party4.add(slime182);
        party4.add(mini181);
        enem.add(party4);
        //
        party5.add(mur181);
        party5.add(mur182);
        party5.add(mi181);
        enem.add(party5);
        //
        party6.add(mini181);
        party6.add(mini182);
        party6.add(mini183);
        enem.add(party6);
    }
    public void partyNivel20(){
        party.add(slime201);
        party.add(mur201);
        party.add(mini201);
        enem.add(party);
        //
        party2.add(mur201);
        party2.add(mini201);
        party2.add(mi201);
        enem.add(party2);
        //
        party3.add(slime201);
        party3.add(mi201);
        party3.add(mi202);
        enem.add(party3);
        
        party4.add(slime201);
        party4.add(slime202);
        party4.add(mini201);
        enem.add(party4);
        //
        party5.add(mur201);
        party5.add(mur202);
        party5.add(mi201);
        enem.add(party5);
        //
        party6.add(mini201);
        party6.add(mini202);
        party6.add(mini203);
        enem.add(party6);
    }
    
}
