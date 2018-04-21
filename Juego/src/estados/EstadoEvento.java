package estados;

import java.awt.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class EstadoEvento extends BasicGameState {
    private int idEstado;
    private Input input;
    private TrueTypeFont mensajePantalla;
    private Font TipoLetra  =new Font("Verdana", Font.PLAIN, 12);  
    private int estado = 0;
    private String linea = "";
    
    
    public EstadoEvento(int id) {
        idEstado = id;
    }

    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.input = gc.getInput();
        mensajePantalla = new TrueTypeFont(TipoLetra, true);
        
    }
@Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        if(estado>0)
            renderDialogo();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(input.isKeyPressed(Input.KEY_ENTER))
             estado++;
        switch(estado){
            case 1:
                linea = "Hola mi nimbre es: " + VenganzaBelial.eventos.getEvento().getNombre();
                break;
            case 2:
                linea = VenganzaBelial.eventos.getEvento().getSaludo();
                break;
            case 3:
                linea = VenganzaBelial.eventos.getEvento().getHistoria();
                break;
            case 4:
                estado = 0;
                sbg.enterState(VenganzaBelial.ESTADOMAPAJUEGO);
                break;
                
        }
         
             
    }
    private void renderDialogo()
    {
        mensajePantalla.drawString(20, 20,linea);
    }
    
    
    
}
