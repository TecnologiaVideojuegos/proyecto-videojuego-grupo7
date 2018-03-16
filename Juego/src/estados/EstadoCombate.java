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


public class EstadoCombate extends BasicGameState{
    private static final int NUMOPCIONES = 3;
    private static final int ATACAR = 0;
    private static final int HABILIDAD = 1;
    private static final int CONSUMIBLE = 2;
    private String[] opciones = new String[NUMOPCIONES];
    private Font letraMenu;
    private TrueTypeFont opcionesJugadorTTF;
    private int eleccionJugador, idEstado;
    private Color notChosen = new Color(153, 204, 255);
    
    public EstadoCombate(int id) {
        idEstado = id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        letraMenu = new Font("Verdana", Font.BOLD, 30);
        opcionesJugadorTTF = new TrueTypeFont(letraMenu, true);
        opciones[0] = "Atacar";
        opciones[1] = "Habilidad";
        opciones[2] = "Consumible";
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        renderOpcionesJugador();
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
                case ATACAR:
                    //sbg.enterState(IceAdventure.GAMEPLAYSTATE);
                    break;
                case HABILIDAD:
                    //heropos = fileio.loadSave();
                    //((GamePlayState)sbg.getState(IceAdventure.GAMEPLAYSTATE)).setHeroPosition(heropos);
                    //sbg.enterState(IceAdventure.GAMEPLAYSTATE);
                    break;
                case CONSUMIBLE:
                    sbg.enterState(VenganzaBelial.STARTMENUSTATE);
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
}
