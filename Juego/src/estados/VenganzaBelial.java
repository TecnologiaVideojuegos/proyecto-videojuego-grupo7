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
/*Pruebas*/
import personajes.*;

public class VenganzaBelial extends StateBasedGame {
    public static final int STARTMENUSTATE = 0;
    public static final int ESTADOCOMBATE = 1;
    public static final int MENUSTATE = 2;
    public static final int WIDTH = 1366;
    public static final int HEIGHT = 768;
    public static final boolean FULLSCREEN = false;
    /*Atributos de pruebas*/
    public static ArrayList<String> St = new ArrayList<String>();
    public static Arma arma = new Arma(5, 0, "Arma1","Descripcion" , St, 1, 1, 1);
    public static Armadura armadura = new Armadura(5, "Armadura1", "Descripcion", St, 1, 1, 1);
    public static ArrayList<Habilidad> habilidades= new ArrayList<Habilidad>();
    public static Inventario inventario = new Inventario();
    public static Horacia horacia = new Horacia( arma,  armadura,  habilidades,  inventario);
    public static Mordeim mordeim = new Mordeim( arma,  armadura,  habilidades,  inventario);
    public static Kibito kibito = new Kibito( arma,  armadura,  habilidades,  inventario);
    /*Atributos de pruebas END*/
    public VenganzaBelial() {
        super("La Venganza de Belial");
        addState(new EstadoMenuInicio(STARTMENUSTATE));
        addState(new EstadoCombate(ESTADOCOMBATE));
        //addState(new MenuState(MENUSTATE));
        this.enterState(STARTMENUSTATE);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        getState(STARTMENUSTATE).init(gc, this);
        getState(ESTADOCOMBATE).init(gc, this);
        //getState(MENUSTATE).init(gc, this);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new VenganzaBelial());
        app.setDisplayMode(WIDTH, HEIGHT, FULLSCREEN);
        app.start();
    }
}
