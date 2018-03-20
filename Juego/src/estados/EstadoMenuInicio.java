
package estados;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class EstadoMenuInicio extends BasicGameState {
    private static final int NUMOPCIONES = 3;
    private static final int EMPEZAR = 0;
    private static final int CARGAR = 1;
    private static final int SALIR = 2;
    private String[] opciones = new String[NUMOPCIONES];
    private Font letraMenu, letraEquipo, letraTitulo;
    private TrueTypeFont opcionesJugadorTTF, equipoTTF, tituloTTF;
    private int eleccionJugador, idEstado;
    private Color notChosen = new Color(153, 204, 255);
    private static final String titulo = "La Venganza de Belial";
    private static final String[] equipo = {"David Ibañez (Jefe Proyecto)", "Alberto Murillo", 
        "Angel Oroquieta", "Hisam Moreno", "David Contreras"};
    
    public EstadoMenuInicio(int id) {
        idEstado = id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        letraMenu = new Font("Verdana", Font.BOLD, 30);
        letraEquipo = new Font("Verdana", Font.PLAIN, 20);
        letraTitulo = new Font("Verdana", Font.BOLD, 40);
        opcionesJugadorTTF = new TrueTypeFont(letraMenu, true);
        equipoTTF = new TrueTypeFont(letraEquipo, true);
        tituloTTF = new TrueTypeFont(letraTitulo, true);
        opciones[0] = "Empezar una nueva partida";
        opciones[1] = "Cargar partida";
        opciones[2] = "Salir";
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
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
                    //sbg.enterState(IceAdventure.GAMEPLAYSTATE);
                    sbg.enterState(VenganzaBelial.ESTADOCOMBATE);
                    break;
                case CARGAR:
                    //heropos = fileio.loadSave();
                    //((GamePlayState)sbg.getState(IceAdventure.GAMEPLAYSTATE)).setHeroPosition(heropos);
                    //sbg.enterState(IceAdventure.GAMEPLAYSTATE);
                    break;
                case SALIR:
                    gc.exit();
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
            equipoTTF.drawString(100, i * 20 + 520, equipo[i]);
        }
    }
    
    private void renderTitulo() {
        tituloTTF.drawString(400, 20, titulo);
    }
    
}
