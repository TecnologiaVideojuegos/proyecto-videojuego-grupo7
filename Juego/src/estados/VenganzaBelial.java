package estados;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import otros.Habilidad;
import otros.Inventario;
import items.Arma;
import items.Armadura;
import java.util.ArrayList;
import org.newdawn.slick.Image;
import otros.Combate;
import otros.Gestion;
/*Pruebas*/
import personajes.*;

public class VenganzaBelial extends StateBasedGame {
    public static final int ESTADOMENUINICIO = 0;
    public static final int ESTADOCOMBATE = 1;
    public static final int ESTADOMENU = 2;
    public static final int ESTADOESCENAPROTOTIPO = 3;//EDIT
    public static final int ESTADOMAPAJUEGO = 4;
    public static final int ESTADOCOMBATETUT=5;
    public static final int ESCENACARRETA=6;
    public static final int ESCENABOSQUE1=7;
    public static final int WIDTH = 1366;
    public static final int HEIGHT = 768;
    public static final boolean FULLSCREEN = false;
    /*EDIT:Atributos de pruebas*/
    public static Gestion atributoGestion = new Gestion();//EDIT:Importante comprobar esta inicializacion
    public static ArrayList<String> St = new ArrayList<String>();
    public static Arma arma = new Arma(5, 0, "Arma1","Descripcion" , St, 1, 1, 1);
    public static Armadura armadura = new Armadura(5, "Armadura1", "Descripcion", St, 1, 1, 1);
    public static ArrayList<Habilidad> habilidades= new ArrayList<Habilidad>();
    public static Habilidad Ataque = new Habilidad("Ataque Fuerte",1 , 2 , 5, "Golpea al objetivo muy fuerte (Daño x2)", Habilidad.TIPOATACAR);
    public static Habilidad Drenar = new Habilidad("Drena vida",1 , 2 , 5, "Daña al enemigo y recupera parte del daño infligido(Daño x1)", Habilidad.TIPODRENARVIDA);
    public static Habilidad AOE = new Habilidad("AOE",1 , 2 , 5, "Golpea a todos los enemigos (Daño x2)", Habilidad.TIPOAOE);
    public static Habilidad Curar = new Habilidad("Cura",1 , 2 , 5, "Recupera parte de los Hp de un aliado ", Habilidad.TIPOCURAR);
    public static Habilidad Resucitar = new Habilidad("Resucita",1 , 2 , 5, "Resucita un aliado", Habilidad.TIPORESUCITAR);
    
    public static Horacia horacia = new Horacia( arma,  armadura,  habilidades,  atributoGestion.inv);
    public static Mordeim mordeim = new Mordeim( arma,  armadura,  habilidades,  atributoGestion.inv);
    public static Kibito kibito= new Kibito( arma,  armadura,  habilidades,  atributoGestion.inv);
    public static int MapaActual=0;
    public static Horacia hori =new Horacia( arma,  armadura,  habilidades,  atributoGestion.inv);
    public static Mordeim mordi =new Mordeim( arma,  armadura,  habilidades,  atributoGestion.inv);
    public static Kibito kibi =new Kibito( arma,  armadura,  habilidades,  atributoGestion.inv);
    
    /*Atributos de pruebas END*/
    public VenganzaBelial() {
        super("La Venganza de Belial");
        addState(new EstadoMenuInicio(ESTADOMENUINICIO));
        addState(new EstadoCombate(ESTADOCOMBATE));
        addState(new EstadoMenu(ESTADOMENU));
        //EDIT
        addState(new EscenaInicio(ESTADOESCENAPROTOTIPO));
        addState(new EstadoMapaJuego(ESTADOMAPAJUEGO));
        addState(new EstadoCombateTutorial(ESTADOCOMBATETUT));
        addState(new EscenaCarreta(ESCENACARRETA));
        addState(new EscenaBosque1(ESCENABOSQUE1));
        //
        this.enterState(ESTADOMENUINICIO);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        getState(ESTADOMENUINICIO).init(gc, this);
        getState(ESTADOCOMBATE).init(gc, this);
        getState(ESTADOMENU).init(gc, this);
        //edit
        getState(ESCENABOSQUE1).init(gc, this);
        getState(ESCENACARRETA).init(gc, this);
        getState(ESTADOESCENAPROTOTIPO).init(gc, this);
        getState(ESTADOMAPAJUEGO).init(gc, this);
        getState(ESTADOCOMBATETUT).init(gc, this);
    }

    public static void main(String[] args) throws SlickException {
        /*ATRIBUTOS DE PRUEBA*/
        habilidades.add(Ataque);
        habilidades.add(Drenar);
        habilidades.add(Curar);
        habilidades.add(AOE);
        habilidades.add(Resucitar);
        horacia.setPJ(true);
        mordeim.setPJ(true);
        kibito.setPJ(true);
        /*Party mediante Gestion*/
        atributoGestion.jugs.add(horacia);
        atributoGestion.jugs.add(mordeim);
        atributoGestion.jugs.add(kibito);
        /*Inventario de Gestion*/
        atributoGestion.inv.addItem(arma);
        atributoGestion.inv.addItem(armadura);
        //Enemigos prueba
        hori.setPJ(false);
        hori.setNombre("Horacia(Dark)");
        mordi.setPJ(false);
        mordi.setNombre("Mordeim(Dark)");
        mordi.setVelocidad(80);//que vaya primero siempre
        kibi.setPJ(false);
        kibi.setNombre("Kibito(Dark)");
        //Combate com = new Combate(VenganzaBelial.Party, VenganzaBelial.MapaActual);
        //Edit: Pruebas de Cambio de armas/armadura con requisitos
        ArrayList<String> requisitos =new ArrayList<String>();
        requisitos.add("Mordeim");
        arma.setRequisitoCategoria(requisitos);
        /*ATRIBUTOS DE PRUEBA FIN*/
        AppGameContainer app = new AppGameContainer(new VenganzaBelial());
        app.setDisplayMode(WIDTH, HEIGHT, FULLSCREEN);
        app.start();
    }
}

/*EDIT: Mensaje para Alberto: Habilidades y Armas a implmentar:
Horacia:
    Habilidades:
        -Embestida(Uniobjetivo)
        -Apalea(Uniobjetivo)
        -Ira salvaje(Multiobjetivo Random.> De 2-4 ataques random hacia los enemigos)
        -Torbellino(Multiobjetivo a todos)
    Armas en orden de nivel: Garrote/Espada de bronce/Maza de Hierro/Hacha de Luz/ Espada de diamante/Espadón Matademonios
Kibito:
    Habilidades:
        -Bola de fuego(Multiobjetivo)
        -"Milagro sanitario" (Cura uniobjetivo)
        -Lanza de rayos(Uniobjetivo)
        -"Levantate gandul"(Resurrección uniobjetivo)
        -O. Arcano (Uniobjetivo)
    Armas:Palo/Vara de olivo/Bastón del Abuelo1/Bastón del Arcangel/"Sin definir"/Bastón del mago herrante
Mordiem:
    Habilidades:
        -Puñalada trapera(Uniobjetivo)
        -Saqueo(Aumenta el drop rate O consigue un item al azar,la que mas te guste)
        -"Lo tuyo es mio"(Robavida uniobjetivo)
        -Pirotecnia(Multiobjetivo)
        -Asesinato
    Armas:Lapiz,Navaja,Cuchillo jamonero, Puñal metalSlime, "Dardo", "Sin definir"
    */
