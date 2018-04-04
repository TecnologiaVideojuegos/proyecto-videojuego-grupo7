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
    private Image fondoHestia;//EDIT
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
        fondo= new Image("Imagenes/Fondos/FondoIntro.jpg");
        fondoHestia= new Image("Imagenes/Fondos/FondoHestia.jpg");//EDIT
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
        //animacionHestia.draw(450, 300, 130, 192);
        
        switch (estado){
            case 0:
                texto.drawString(450, 300, "El mundo de nuestros protegonistas se llama Reynos.");
                texto.drawString(450, 350, "Hace muchos años había un demonio, Belial, que engaño a la");
                texto.drawString(450, 400, "mitad de la población con otorgarles lo que quisieran a cambio");
                texto.drawString(450, 450, "de abrir las puertas del Infierno.");
                break;
            case 1:
                texto.drawString(450, 300, "De esa forma Reynos se sumió en la Guerra Demoniaca.");
                texto.drawString(450, 350, "A medida que avanzaba la guerra las puertas del Infierno parecían");
                texto.drawString(450, 400, "abrirse, como si el derramamiento de sangre fuera la llave.");
                break;
            case 2:
                texto.drawString(450, 300, "Cuando las puertas se iban a abrir y Belial iba a entrar a nuestro mundo");
                texto.drawString(450, 350, "aparecio una organización para preservar la paz, Cardinal.");
                texto.drawString(450, 400, "Cardinal sello las puertas del Infierno con un ritual y los sellos");
                texto.drawString(450, 450, "se exparcierón por todo el mundo de Reynos.");
                break;
            case 3:
                texto.drawString(450, 300, "Con el paso de los siglos los sellos se han debilitado y los");
                texto.drawString(450, 350, "movimientos sospechosos de Cardinal han aumentado considerablemente.");
                break;
            case 4:
                fondoHestia.draw(0, 0, VenganzaBelial.WIDTH, VenganzaBelial.HEIGHT);//EDIT
                texto.drawString(1050, 0, "Sala de Hestia");
                break;
            
        }
    }

    @Override
    //Muestra la actualización
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        musicaIntro.play();
        if (input.isKeyPressed(Input.KEY_ENTER)){
            if(estado==4){
                musicaIntro.stop();
                sbg.enterState(VenganzaBelial.ESTADOMAPAJUEGO);
            }
            estado++;
        }
        
    }
    
}
