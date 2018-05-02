    package estados;

import java.awt.Font;
import java.util.Vector;
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
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;


public class EscenaPuebloMontana extends BasicGameState{
    private int idEstado;
    private static final int POSICIONAVATARX = 30;
    private static final int POSICIONAVATARY = 620;
    private static final int TAMANYOAVATARX = 115;
    private static final int TAMANYOAVATARY = 115;
    //avatarDialogo.draw(30, 610, 115, 125);
    private static final int TILESIZE = 32;
    /*Texto*/
    private TrueTypeFont mensajePantalla;
    private Font tipoLetra  =new Font("Verdana", Font.PLAIN, 15);
    private String linea1="";
    private String linea2="";
    private String linea3="";
    private String linea4="";
    private String linea5="";
    /*Control de escena*/
    private Input input;
    private int estado;
    private boolean reproducirExclamacion=false;
    /*Mapa*/
    private Vector2f posicion,posicionE,posicionL,posicionP;
    private static final int esquinaXMapa=0;
    private static final int esquinaYMapa=0;
    /*Animaciones*/
    private SpriteSheet sheetExclamacion;
    private Animation exclamacion;
    private Animation hor,kib,mor;
    private Animation horS,kibS,morS;
    private Image fondo;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo, avatarH,avatarM, avatarK;
    /*Sonido*/
    private Sound sonidoSelect;
    private Music battle;
    int time;//EDIT
    private TrueTypeFont texto;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 15); 
    
    public EscenaPuebloMontana(int id) {
        this.idEstado=id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Image[] morStop={new Image("Imagenes/Animaciones/Sprites/mor8.png")};
        morS=new Animation(morStop,200);
        Image[] horStop={new Image("Imagenes/HeroeMundo/her11.png")};
        horS=new Animation(horStop,200);
        Image[] kibStop={new Image("Imagenes/Animaciones/Sprites/kib8.png")};
        kibS=new Animation(kibStop,200);
        hor=horS;
        kib=kibS;
        mor=morS;
        fondo= new Image("Imagenes/Escenas/EscenaMontana/PuebloMontana.png");
        /**/
        this.sheetExclamacion= new SpriteSheet("Imagenes/Animaciones/exclamacion.png",32,33);
        this.exclamacion = new Animation(sheetExclamacion,200);
        /**/
        estado=0;
        this.input = gc.getInput();
        mensajePantalla= new TrueTypeFont(tipoLetra, true);
        posicion = new Vector2f(0,300);
        posicionE = new Vector2f(0,300);
        posicionL = new Vector2f(0,300);
        posicionP = new Vector2f(0,300);
        ventanaDialogo= new Image("Imagenes/Avatar/cajaMensaje.png");
        avatarH =  new Image("Imagenes/Personajes/HoraciaA.png");
        avatarM =  new Image("Imagenes/Personajes/MordeimA.png");
        avatarK =  new Image("Imagenes/Personajes/KibitoA.png");
        avatarDialogo = avatarH;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        texto= new TrueTypeFont(letraMenu, true);
        battle = new Music("Musica/BSO/DragonTheme.wav");
        /**/
        
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
        
            fondo.draw(0, 0);
            
            //EDIT:Rener Mordeim
            if(reproducirExclamacion){
                this.exclamacion.draw(posicion.x-64, posicion.y+176);
            }
            
            if(estado>=0){
                hor.draw(posicion.x, posicion.y);
                mor.draw(posicion.x, posicion.y+32);
                kib.draw(posicion.x, posicion.y-32);
                renderDialogo();
                
            }
//            texto.drawString(1000, 0, "" + estado);
    }
    @Override
    //Muestra la actualización
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        exclamacion.update(i);
        if(input.isKeyPressed(Input.KEY_ENTER)){
            if(estado!=30)
            {
                sonidoSelect.play(1, 0.2f);
                time=0;
                estado++;
                
            }
            
        }
        switch (estado)
        {
            case 0:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Bueno, hemos llegado a Olimpio.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 1:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Vaya birria de pueblo.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 2:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Según el mapa, el sello esta en lo más profundo";
                linea2="de la montaña Olimpo.";
                linea3="Según la leyenda, se cree que los antiguos dioses";
                linea4="vivian allí.";
                break;
            case 3:
                avatarDialogo=this.avatarH;
                linea1="Será mejor que nos preparemos para el viaje,";
                linea2="hace un frío que pela.";
                linea3="";
                linea4="";
                break;
            case 4:
                avatarDialogo=this.avatarM;
                linea1="Delicada.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 5:
                avatarDialogo=this.avatarK;
                linea1="Supongo que será lo mejor, además, así podemos";
                linea2="descansar, que llevamos muchas horas andando.";
                linea3="";
                linea4="";
                break;
            case 6:
                estado=0;
                sbg.enterState(VenganzaBelial.ESTADOMENUINICIO);//EDIT:
                break;

        }
    }
    
    private void renderDialogo()
    {
        avatarDialogo.draw(POSICIONAVATARX, POSICIONAVATARY, TAMANYOAVATARX, TAMANYOAVATARY);
        this.ventanaDialogo.draw(0, 600, 1);
        ///////////////////////////////////,"////////////////////////////////////////////////////////"/;
        mensajePantalla.drawString(160, 625,linea1);
        mensajePantalla.drawString(160, 640,linea2);
        mensajePantalla.drawString(160, 655,linea3);
        mensajePantalla.drawString(160, 670,linea4);
    }
    
}