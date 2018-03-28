/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estados;

import java.awt.Font;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Dolores
 */
public class EscenaPrototipo extends BasicGameState{
    private int idEstado;
    private Input input;
    private int estado;
    private Image fondo;
    private TrueTypeFont texto;
    private Font letraMenu  =new Font("Verdana", Font.PLAIN, 20);    
    private Color rojo = new Color (256,0,0);
    private SpriteSheet hestia;
    private Animation animacionHestia;
    private Music musicaIntro;
    private Sound efecto;

    public EscenaPrototipo(int id) {
        this.idEstado=id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        estado=0;
        this.input = gc.getInput();
        fondo= new Image("Imagenes/BackBattle/Bosque.jpg");
        texto= new TrueTypeFont(letraMenu, true);
        this.hestia= new SpriteSheet("Imagenes/Animaciones/Sprites/Hestia.png",20,20);
        this.animacionHestia = new Animation(hestia,200);
        this.musicaIntro = new Music("Musica/BSO/Ablaze.wav");
        this.efecto = new Sound("Musica/Efectos/Cry2.wav");
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        fondo.draw(0, 0, VenganzaBelial.WIDTH, VenganzaBelial.HEIGHT);
        animacionHestia.draw(450, 300, 130, 192);
        
        switch (estado){
            case 0:
                texto.drawString(300, 300, "Hola Jugador");
                break;
            case 1:
                texto.drawString(300, 300, "Adios Jugador");
                break;
            case 2:
                break;
        }
    }

    @Override
    //Muestra la actualización
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        
        switch (estado){
            case 0:
                if (input.isKeyPressed(Input.KEY_ENTER)){
                    estado=1;
                    musicaIntro.play();
                    
                }
                break;
            case 1:
                if (input.isKeyPressed(Input.KEY_ENTER)){
                    estado=2;
                    this.efecto.play();
                }                
                break;
            case 2:
                if (input.isKeyPressed(Input.KEY_ENTER)){
                    estado=0;
                    sbg.enterState(VenganzaBelial.ESTADOMENUINICIO);
                }                
                break;
        }
    }
    
}
