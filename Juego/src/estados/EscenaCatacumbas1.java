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

public class EscenaCatacumbas1 extends BasicGameState{
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
    private Vector2f posicion,posicionE,posicionS;
    private static final int esquinaXMapa=0;
    private static final int esquinaYMapa=0;
    /*Animaciones*/
    private SpriteSheet sheetExclamacion;
    private Animation exclamacion;
    private Animation hor,kib,mor;
    private Animation horD,kibD,morD;
    private Animation horS,kibS,morS;
    private Animation corrupto,corruptoI;
    private Animation misterio,misterioI,misterioA;
    private Image fondo;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo, avatarH,avatarM, avatarK, avatarCorrupto;
    /*Sonido*/
    private Sound sonidoSelect,grito;
    int time;//EDIT
    private TrueTypeFont texto;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 15); 
    
    public EscenaCatacumbas1(int id) {
        this.idEstado=id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Image[] horDere={new Image("Imagenes/HeroeMundo/her10.png"),new Image("Imagenes/HeroeMundo/her11.png"),new Image("Imagenes/HeroeMundo/her12.png")};
        horD=new Animation(horDere,200);
        Image[] morDere={new Image("Imagenes/Animaciones/Sprites/mor7.png"),new Image("Imagenes/Animaciones/Sprites/mor8.png"),new Image("Imagenes/Animaciones/Sprites/mor9.png")};
        morD=new Animation(morDere,200);
        Image[] kibDere={new Image("Imagenes/Animaciones/Sprites/kib7.png"),new Image("Imagenes/Animaciones/Sprites/kib8.png"),new Image("Imagenes/Animaciones/Sprites/kib9.png")};
        kibD=new Animation(kibDere,200);
        Image[] kibF={new Image("Imagenes/Animaciones/Sprites/kib8.png")};
        kibS=new Animation(kibF,200);
        Image[] morF={new Image("Imagenes/Animaciones/Sprites/mor8.png")};
        morS=new Animation(morF,200);
        Image[] horEnfrente={new Image("Imagenes/HeroeMundo/her11.png")};
        horS=new Animation(horEnfrente,200);
        hor=horD;
        kib=kibD;
        mor=morD;
        Image[] fanIzq={new Image("Imagenes/Animaciones/Sprites/corrupto1.png")};
        corruptoI=new Animation(fanIzq,200);
        corrupto=corruptoI;
        Image[] misIzq={new Image("Imagenes/Animaciones/Sprites/misterio4.png"),new Image("Imagenes/Animaciones/Sprites/misterio5.png"),new Image("Imagenes/Animaciones/Sprites/misterio6.png")};
        misterioI=new Animation(misIzq,200);
        Image[] misStop={new Image("Imagenes/Animaciones/Sprites/misterio5.png")};
        misterioA=new Animation(misStop,200);
        misterio=misterioI;
        fondo= new Image("Imagenes/Escenas/EscenaTroyia/DungeonCatacumbas.png");
        /**/
        this.sheetExclamacion= new SpriteSheet("Imagenes/Animaciones/puntos.png",32,33);
        this.exclamacion = new Animation(sheetExclamacion,200);
        /**/
        estado=0;
        this.input = gc.getInput();
        mensajePantalla= new TrueTypeFont(tipoLetra, true);
        posicion = new Vector2f(0,300);
        posicionE = new Vector2f(0,300);
        posicionS = new Vector2f(0,300);
        ventanaDialogo= new Image("Imagenes/Avatar/cajaMensaje.png");
        avatarH =  new Image("Imagenes/Personajes/HoraciaA.png");
        avatarM =  new Image("Imagenes/Personajes/MordeimA.png");
        avatarK =  new Image("Imagenes/Personajes/KibitoA.png");
        avatarCorrupto = new Image("Imagenes/Avatar/Caras/corrupted.png");
        avatarDialogo = avatarH;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        grito = new Sound("Musica/Efectos/Grito_corrupto.wav");
        texto= new TrueTypeFont(letraMenu, true);
        /**/
        
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
        
            fondo.draw(-332, -480);
            
            //EDIT:Rener Mordeim
            if(reproducirExclamacion){
                this.exclamacion.draw(posicion.x-64, posicion.y+176);
            }
            if(estado>=0){
                hor.draw(posicion.x, posicion.y);
                mor.draw(posicion.x, posicion.y+32);
                kib.draw(posicion.x, posicion.y-32);
                corrupto.draw(posicionS.x+364, posicionS.y);
                if(estado>=1 && estado!=6){
                renderDialogo();
                }
                if(estado>=6){
                    misterio.draw(posicionE.x+496, posicionE.y-32);
                    misterio.draw(posicionE.x+496, posicionE.y+32);
                }
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
                posicion.x+=0.1f*i;
                if(posicion.x>=256){
                    estado++;
                }
                break;
            case 1:
                hor=horS;
                kib=kibS;
                mor=morS;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Qué es eso?";
                linea2="Parece una armadura.";
                linea3="";
                linea4="";
                break;
            case 2:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Menuda armadura más fea. Es perfecta para tí Horacia.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 3:
                grito.play();
                avatarDialogo=this.avatarCorrupto;
                //////="////////////////////////////////////////////////////////";
                linea1="GRRRRRRRRRRRRRRRRRRRR!!!!!!!";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 4:
                grito.stop();
                avatarDialogo=this.avatarH;
                linea1="Esta vivo.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 5:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Debe ser una armadura maldita que deben de estar";
                linea2="usando esos fanáticos de antes con magia negra";
                linea3="a modo de guardian para protejer algo importante.";
                linea4="";
                break;
            case 6:
                posicionE.x-=0.1f*i;
                if(posicionE.x<=(-100)){
                    estado++;
                }
                break;
            case 7:
                misterio=misterioA;
                avatarDialogo=this.avatarCorrupto;
                //////="////////////////////////////////////////////////////////";
                linea1="OS DESTRUIRÉ!!!!!!!!!!!!!";
                linea2="NO QUEDARÁ NADA DE VOSOTROS!!!!!!!!!!!!!!!";
                linea3="";
                linea4="";
                break;
            case 8:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Supongo que habrá que luchar para seguir adelante.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 9:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Todo lo que sea matar me gusta.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 10:
                avatarDialogo=this.avatarCorrupto;
                linea1="ACABAD CON ELLOS!!!!!!!";
                linea2="POR EL GRAN MAESTRO!!!!!!!!!!";
                linea3="";
                linea4="";
                break;
            case 11:
                //Batalla contra dos fanáticos y corruptedknight
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
        mensajePantalla.drawString(160, 685, linea5);
    }
    
}