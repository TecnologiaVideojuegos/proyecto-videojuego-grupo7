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
/*Pruebas*/
import personajes.*;

public class VenganzaBelial extends StateBasedGame {
    public static final int ESTADOMENUINICIO = 0;
    public static final int ESTADOCOMBATE = 1;
    public static final int ESTADOMENU = 2;
    public static final int ESTADOESCENAPROTOTIPO = 3;//EDIT
    public static final int WIDTH = 1366;
    public static final int HEIGHT = 768;
    public static final boolean FULLSCREEN = false;
    /*Atributos de pruebas*/
    public static ArrayList<String> St = new ArrayList<String>();
    public static Arma arma = new Arma(5, 0, "Arma1","Descripcion" , St, 1, 1, 1);
    public static Armadura armadura = new Armadura(5, "Armadura1", "Descripcion", St, 1, 1, 1);
    public static ArrayList<Habilidad> habilidades= new ArrayList<Habilidad>();
    public static Habilidad Ataque = new Habilidad("Ataque Fuerte",1 , 2 , 5, "Golpea al objetivo muy fuerte (Daño x2)", Habilidad.TIPOATACAR);
    public static Habilidad Drenar = new Habilidad("Drena vida",1 , 2 , 5, "Golpea al objetivo muy fuerte (Daño x2)", Habilidad.TIPODRENARVIDA);
    public static Habilidad AOE = new Habilidad("AOE",1 , 2 , 5, "Golpea al objetivo muy fuerte (Daño x2)", Habilidad.TIPOAOE);
    public static Habilidad Curar = new Habilidad("Cura",1 , 2 , 5, "Golpea al objetivo muy fuerte (Daño x2)", Habilidad.TIPOCURAR);
    public static Habilidad Resucitar = new Habilidad("Resucita",1 , 2 , 5, "Golpea al objetivo muy fuerte (Daño x2)", Habilidad.TIPORESUCITAR);
    public static Inventario inventario = new Inventario();
    public static Horacia horacia = new Horacia( arma,  armadura,  habilidades,  inventario);
    public static Mordeim mordeim = new Mordeim( arma,  armadura,  habilidades,  inventario);
    public static Kibito kibito= new Kibito( arma,  armadura,  habilidades,  inventario);
    public static ArrayList<Personaje> Party= new ArrayList<Personaje>();
    public static int MapaActual=0;
    public static Horacia hori =new Horacia( arma,  armadura,  habilidades,  inventario);
    public static Mordeim mordi =new Mordeim( arma,  armadura,  habilidades,  inventario);
    public static Kibito kibi =new Kibito( arma,  armadura,  habilidades,  inventario);
    /*Atributos de pruebas END*/
    public VenganzaBelial() {
        super("La Venganza de Belial");
        addState(new EstadoMenuInicio(ESTADOMENUINICIO));
        addState(new EstadoCombate(ESTADOCOMBATE));
        addState(new EstadoMenu(ESTADOMENU));
        //EDIT
        addState(new EscenaPrototipo(ESTADOESCENAPROTOTIPO));
        this.enterState(ESTADOMENUINICIO);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        getState(ESTADOMENUINICIO).init(gc, this);
        getState(ESTADOCOMBATE).init(gc, this);
        getState(ESTADOMENU).init(gc, this);
        getState(ESTADOESCENAPROTOTIPO).init(gc, this);
    }

    public static void main(String[] args) throws SlickException {
        /*ATRIBUTOS DE PRUEBA*/
        habilidades.add(Ataque);
        habilidades.add(Drenar);
        habilidades.add(Curar);
        habilidades.add(AOE);
        habilidades.add(Resucitar);
        Party.add(horacia);
        Party.add(mordeim);
        Party.add(kibito);
        horacia.setPJ(true);
        mordeim.setPJ(true);
        kibito.setPJ(true);
        //Enemigos prueba
        hori.setPJ(false);
        hori.setNombre("Horacia(Dark)");
        mordi.setPJ(false);
        mordi.setNombre("Mordeim(Dark)");
        kibi.setPJ(false);
        kibi.setNombre("Kibito(Dark)");
        //Combate com = new Combate(VenganzaBelial.Party, VenganzaBelial.MapaActual);
        //com.finalize();
        //com.Atacar(VenganzaBelial.horaciaenemiga, com.getEnemigos().get(0));
        /*ATRIBUTOS DE PRUEBA FIN*/
        AppGameContainer app = new AppGameContainer(new VenganzaBelial());
        app.setDisplayMode(WIDTH, HEIGHT, FULLSCREEN);
        app.start();
    }
}
