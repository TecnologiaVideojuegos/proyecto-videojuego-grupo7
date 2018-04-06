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
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Dolores
 */
public class EscenaPrototipo extends BasicGameState{
    private int idEstado;
    private static final int POSICIONAVATARX = 30;
    private static final int POSICIONAVATARY = 620;
    private static final int TAMANYOAVATARX = 115;
    private static final int TAMANYOAVATARY = 115;
    //avatarDialogo.draw(30, 610, 115, 125);
    private static final int TILESIZE = 32;
    private String linea1="";
    private String linea2="";
    private String linea3="";
    private String linea4="";
    private String linea5="";
    private String linea6="";
    private String linea7="";
    private String linea8="";
    private static final int esquinaXMapa=550;
    private static final int esquinaYMapa=300;
    private Image hero1, hero2, hero3;
    private Image ventanaDialogo,avatarDialogo, avatarH,avatarM, avatarK, avatarHestia,avatarE;
    private Input input;
    private int estado;
    private Image fondo;
    private Image fondoHestia;//EDIT
    private TrueTypeFont texto;
    private Font letraMenu  =new Font("Verdana", Font.PLAIN, 15);    
    private Color rojo = new Color (256,0,0);
    private SpriteSheet hestia;
    private Animation animacionHestia;
    private Music musicaIntro;
    private Sound efecto;
    private Vector2f posicion;
    

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
        fondoHestia= new Image("Imagenes/Escenas/SalaInicial/SalaHestia.png");//EDIT
        ventanaDialogo= new Image("Imagenes/Avatar/cajaMensaje.png");
        hero1=new Image("Imagenes/HeroeMundo/her01.png");
        avatarH =  new Image("Imagenes/Personajes/HoraciaA.png");
        avatarM =  new Image("Imagenes/Personajes/MordeimA.png");
        avatarK =  new Image("Imagenes/Personajes/KibitoA.png");
        avatarHestia = new Image("Imagenes/Personajes/HestiaA.png");//EDIT
        avatarE = new Image ("Imagenes/Personajes/EncapuchadoA.png");
        texto= new TrueTypeFont(letraMenu, true);
        posicion = new Vector2f(esquinaXMapa+TILESIZE*2,esquinaYMapa+TILESIZE*2);
        this.hestia= new SpriteSheet("Imagenes/Animaciones/Sprites/Hestia.png",20,20);
        this.animacionHestia = new Animation(hestia,200);
        this.musicaIntro = new Music("Musica/BSO/Ablaze.wav");
        this.efecto = new Sound("Musica/Efectos/Cry2.wav");
        
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        //animacionHestia.draw(450, 300, 130, 192);
        if(estado<4){
            fondo.draw(0, 0, VenganzaBelial.WIDTH, VenganzaBelial.HEIGHT);
            renderDialogo1();
        }
        switch (estado){
            case 4:
                fondoHestia.draw(esquinaXMapa, esquinaYMapa);//EDIT
                texto.drawString(1050, 0, "Sala de Hestia");
                hero1.draw(posicion.x+40, posicion.y);
                break;
            case 5:
                fondoHestia.draw(esquinaXMapa, esquinaYMapa);//EDIT
                hero1.draw(posicion.x+40, posicion.y);
                renderDialogo();
                break;
            case 6:
                fondoHestia.draw(esquinaXMapa, esquinaYMapa);//EDIT
                hero1.draw(posicion.x+40, posicion.y);
                renderDialogo();
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
            }
            if(estado==6){
                sbg.enterState(VenganzaBelial.ESTADOMAPAJUEGO);
            }
            estado++;
        }
        switch (estado)
        {
            case 0:
                avatarDialogo=this.avatarE;
                linea5 = "El mundo de nuestros protegonistas se llama Reynos.";
                linea6 = "Hace muchos años había un demonio, Belial, que engaño";
                linea7 = "a la mitad de la población con otorgarles lo que";
                linea8 = "quisieran a cambio de abrir las puertas del Infierno.";
                break;
            case 1:
                avatarDialogo=this.avatarE;
                linea5 = "De esa forma Reynos se sumió en la Guerra Demoniaca.";
                linea6 = "A medida que avanzaba la guerra las puertas del";
                linea7 = "Infierno parecían abrirse, como si el derramamiento";
                linea8 = "de sangre fuera la llave.";
                break;
            case 2:
                avatarDialogo=this.avatarE;
                linea5 = "Cuando las puertas se iban a abrir y Belial entraría";
                linea6 = "a nuestro mundoaparecio una organización para preservar";
                linea7 = "la paz, Cardinal. Cardinal sello las puertas del Infierno con";
                linea8 = "un ritual y los sellos se exparcierón por todo el mundo de Reynos.";
                break;
            case 3:
                avatarDialogo=this.avatarE;
                linea5 = "Con el paso de los siglos los sellos se han debilitado y los";
                linea6 = "movimientos sospechosos de Cardinal han aumentado considerablemente.";
                linea7 = "";
                linea8 = "";
                break;
            
            case 5:
                avatarDialogo=this.avatarHestia;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Quién va?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 6:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Cap...capi...CAPITANA DEL ESCUADRÓN “F”, HORACIA LABELLE";
                linea2="A SU SERVICIO MI SEÑORA.";
                linea3="";
                linea4="";
                break;
        }
        
    }
    private void renderDialogo()
    {
        avatarDialogo.draw(POSICIONAVATARX, POSICIONAVATARY, TAMANYOAVATARX, TAMANYOAVATARY);
        this.ventanaDialogo.draw(0, 600, 1);
        ///////////////////////////////////,"////////////////////////////////////////////////////////"/;
        texto.drawString(160, 625,linea1 );
        texto.drawString(160, 640,linea2);
        texto.drawString(160, 655,linea3 );
        texto.drawString(160, 670,linea4);
    }
    private void renderDialogo1(){
        avatarDialogo.draw(POSICIONAVATARX, POSICIONAVATARY, TAMANYOAVATARX, TAMANYOAVATARY);
        texto.drawString(450, 300, linea5, rojo);
        texto.drawString(450, 350, linea6, rojo);
        texto.drawString(450, 400, linea7, rojo);
        texto.drawString(450, 450, linea8, rojo);
    }
}
