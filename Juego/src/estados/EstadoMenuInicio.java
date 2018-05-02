
package estados;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import otros.Gestion;
import personajes.Horacia;
import personajes.Kibito;
import personajes.Mordeim;

public class EstadoMenuInicio extends BasicGameState {
    private static final int NUMOPCIONES = 8;//EDIT:Deben ser 3
    private static final int EMPEZAR = 0;
    private static final int CARGAR = 1;
    private static final int SALIR = 2;
    /*EDIT:Eliminar pruebas*/
    private static final int PRUEBASALBERT0=3;
    private static final int PRUEBASHISAM=4;
    private static final int PRUEBASDAVID=5;
    private static final int PRUEBASANGEL=6;
    private static final int PRUEBASPABLO=7;
    /*EDIT END*/
    private Image fondo;
    private String[] opciones = new String[NUMOPCIONES];
    private Font letraMenu, letraEquipo, letraTitulo;
    private TrueTypeFont opcionesJugadorTTF, equipoTTF, tituloTTF;
    private int eleccionJugador, idEstado;
    private Color notChosen = new Color(211,84,0);//EDIT
    //private Color notChosen = new Color(153, 204, 255);
    private static final String titulo = "La Venganza de Belial";
    private static final String[] equipo = {"David Ibañez (Jefe Proyecto)", "Alberto Murillo", 
        "Angel Oroquieta", "Hisam Moreno", "Pablo Contreras"};
    
    public EstadoMenuInicio(int id) {
        idEstado = id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
        fondo= new Image("Imagenes/Fondos/Fondo.jpg");
        letraMenu = new Font("Verdana", Font.BOLD, 30);
        letraEquipo = new Font("Verdana", Font.PLAIN, 20);
        letraTitulo = new Font("Verdana", Font.BOLD, 40);
        opcionesJugadorTTF = new TrueTypeFont(letraMenu, true);
        equipoTTF = new TrueTypeFont(letraEquipo, true);
        tituloTTF = new TrueTypeFont(letraTitulo, true);
        opciones[0] = "Empezar una nueva partida";
        opciones[1] = "Cargar partida";
        opciones[2] = "Salir";
        /*EDIT*/
        opciones[3] = "Pruebas de Alberto";
        opciones[4] = "Pruebas de Hisam";
        opciones[5] = "Pruebas de David";
        opciones[6] = "Pruebas de Angel";
        opciones[7] = "Pruebas de Pablo";
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        fondo.draw(0, 0, VenganzaBelial.WIDTH, VenganzaBelial.HEIGHT);
        renderOpcionesJugador();
        renderEquipo();
        renderTitulo();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        if (input.isKeyPressed(Input.KEY_DOWN)) {
            if (eleccionJugador == (NUMOPCIONES - 1)) {
                eleccionJugador = 0;
            } else {
                eleccionJugador++;
            }
        }
        if (input.isKeyPressed(Input.KEY_UP)) {
            if (eleccionJugador == 0) {
                eleccionJugador = NUMOPCIONES - 1;
            } else {
                eleccionJugador--;
            }
        }
        if (input.isKeyPressed(Input.KEY_ENTER)) {
            switch (eleccionJugador) {
                case EMPEZAR:
                    sbg.enterState(VenganzaBelial.ESTADOESCENAPROTOTIPO);
                    break;
                case CARGAR:
                    //heropos = fileio.loadSave();
                    //sbg.enterState(VenganzaBelial.ESTADOESCENAPROTOTIPO);//EDIT
                    //((GamePlayState)sbg.getState(IceAdventure.GAMEPLAYSTATE)).setHeroPosition(heropos);
                    //sbg.enterState(IceAdventure.GAMEPLAYSTATE);
                    break;
                case SALIR:
                    gc.exit();
                    break;
                case PRUEBASALBERT0:
                    sbg.enterState(VenganzaBelial.ESTADOMAPAJUEGO); //EDIT
                    break;
                case PRUEBASHISAM:
                    //sbg.enterState(VenganzaBelial.ESTADOESCENAPROTOTIPO);//EDIT 
                    //sbg.enterState(VenganzaBelial.ESCENABOSQUE2);//EDIT
                    //sbg.enterState(VenganzaBelial.ESCENABOSQUEPREBOSS);//EDIt
                    //sbg.enterState(VenganzaBelial.ESCENAPUERTO2);//EDIT
                    //sbg.enterState(VenganzaBelial.ESCENAPUERTO1);//EDIT
                    //sbg.enterState(VenganzaBelial.ESCENATROYIA1);//EDIT
                    //sbg.enterState(VenganzaBelial.ESCENAFANATICO);//EDIT
                    //sbg.enterState(VenganzaBelial.ESCENACATACUMBASPREBOSS);//EDIT
                    //sbg.enterState(VenganzaBelial.ESCENATROYIA2);//EDIT
                    //sbg.enterState(VenganzaBelial.ESCENACATACUMBAS1);//EDIT//Sonido de la armadura un po irritante Xd
                    //sbg.enterState(VenganzaBelial.ESCENAMONTANABOSS);//EDIT
                    sbg.enterState(VenganzaBelial.ESCENADEYOLICA);//EDIT
                    //sbg.enterState(VenganzaBelial.ESCENATROYIAPOSTBOSS);//EDIT
                    //sbg.enterState(VenganzaBelial.ESCENAARCHI1);//EDIT
                    //sbg.enterState(VenganzaBelial.ESCENATROYIAPOSTBOSS2);//EDIT
                    //sbg.enterState(VenganzaBelial.ESCENAMONTANAMINIBOSS);//EDIT
                    //sbg.enterState(VenganzaBelial.ESCENAMONTANAMINIBOSS2);//EDIT
                    //sbg.enterState(VenganzaBelial.ESCENAARCHI2);//EDIT
                    //sbg.enterState(VenganzaBelial.ESCENAPUEBLOMONTANA);//EDIT
                    //sbg.enterState(VenganzaBelial.ESCENAMONTANAPOSTBOSS);//EDIT
                    //sbg.enterState(VenganzaBelial.ESCENACARDINAL1);//EDIT
                    //sbg.enterState(VenganzaBelial.ESCENACARDINAL2);//EDIT

                    break;
                case PRUEBASDAVID:
                    //sbg.enterState(VenganzaBelial.ESCENACARRETA);//EDIT
                    //sbg.enterState(VenganzaBelial.ESTADOMENU);//EDIT
                    //sbg.enterState(VenganzaBelial.ESTADOCOMBATE);//EDIT
                    //sbg.enterState(VenganzaBelial.ESTADOCOMBATETUT);//EDIT
                    //sbg.enterState(VenganzaBelial.ESCENABOSQUEPOSTBOSS);//EDIT
                    //sbg.enterState(VenganzaBelial.ESTADOTIENDA);//EDIT
                    sbg.enterState(VenganzaBelial.ESCENABOSQUEPREBOSS);//EDIt
                    break;
                case PRUEBASANGEL:
                    break;
                case PRUEBASPABLO:
                    //sbg.enterState(VenganzaBelial.ESCENATUTORIAL);
                    break;
                    
            }
        }
    }
    
    private void renderOpcionesJugador() {
        for (int i = 0; i < NUMOPCIONES; i++) {
            if (eleccionJugador == i) {
                opcionesJugadorTTF.drawString(100, i * 50 + 200, opciones[i]);
            } else {
                opcionesJugadorTTF.drawString(100, i * 50 + 200, opciones[i], notChosen);
            }
        }
    }
    
    private void renderEquipo() {
        for (int i = 0; i < equipo.length; i++) {
            equipoTTF.drawString(800, i * 20 + 520, equipo[i]);
        }
    }
    
    private void renderTitulo() {
        tituloTTF.drawString(400, 20, titulo);
    }
    
}
