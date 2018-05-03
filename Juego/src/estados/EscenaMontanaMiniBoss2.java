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


public class EscenaMontanaMiniBoss2 extends BasicGameState{
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
    private Animation horD,kibD,morD;
    private Animation horS,kibS,morS;
    private Animation pegaso,pegasoV,pegasoS;
    private Animation horI,kibI,morI;
    private Image fondo;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo, avatarH,avatarM, avatarK, avatarPegaso;
    /*Sonido*/
    private Sound sonidoSelect;
    int time;//EDIT
    private TrueTypeFont texto;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 15); 
    
    public EscenaMontanaMiniBoss2(int id) {
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
        Image[] morIzq={new Image("Imagenes/Animaciones/Sprites/mor5.png")};
        morI=new Animation(morIzq,200);
        Image[] horIzq={new Image("Imagenes/HeroeMundo/her31.png")};
        horI=new Animation(horIzq,200);
        Image[] kibIzq={new Image("Imagenes/Animaciones/Sprites/kib5.png")};
        kibI=new Animation(kibIzq,200);
        Image[] morStop={new Image("Imagenes/Animaciones/Sprites/mor8.png")};
        morS=new Animation(morStop,200);
        Image[] horStop={new Image("Imagenes/HeroeMundo/her11.png")};
        horS=new Animation(horStop,200);
        Image[] kibStop={new Image("Imagenes/Animaciones/Sprites/kib8.png")};
        kibS=new Animation(kibStop,200);
        hor=horI;
        kib=kibI;
        mor=morI;
        Image[] cabDer={new Image("Imagenes/Animaciones/Sprites/pegaso4.png"),new Image("Imagenes/Animaciones/Sprites/pegaso5.png"),new Image("Imagenes/Animaciones/Sprites/pegaso6.png")};
        pegasoV=new Animation(cabDer,200);
        Image[] cabStop={new Image("Imagenes/Animaciones/Sprites/pegaso8.png")};
        pegasoS=new Animation(cabStop,200);
        pegaso=pegasoS;
        fondo= new Image("Imagenes/Escenas/EscenaMontana/Montana.png");
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
        avatarPegaso = new Image("Imagenes/Avatar/Caras/Caballero.png");
        avatarDialogo = avatarH;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        texto= new TrueTypeFont(letraMenu, true);
        /**/
        
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
        
            fondo.draw(-800, -932);
            
            //EDIT:Rener Mordeim
            if(reproducirExclamacion){
                this.exclamacion.draw(posicion.x-64, posicion.y+176);
            }
            
            if(estado>=0){
                hor.draw(posicion.x+576, posicion.y+240);
                mor.draw(posicion.x+544, posicion.y+272);
                kib.draw(posicion.x+544, posicion.y+208);
                if(estado>=0 && estado!=4 && estado!=16 && estado!=18){
                renderDialogo();
                }
                if(estado<=16){
                    pegaso.draw(posicionL.x+416, posicionL.y+208);
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
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Uffff, se nota que Belerofonte es el líder";
                linea2="del Escuadrón Z, el escuadrón personal de Archi.";
                linea3="";
                linea4="";
                break;
            case 1:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Vaaaaaaa, era un flipao de la vida.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 2:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Propongo que sigamos nuestro camino.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 3:
                avatarDialogo=this.avatarH;
                linea1="Si, será lo mejor.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 4:
                hor=horD;
                kib=kibD;
                mor=morD;
                posicion.x+=0.1f*i;
                if(posicion.x>=128){
                    estado++;
                }
                break;
            case 5:
                hor=horS;
                kib=kibS;
                mor=morS;
                avatarDialogo=this.avatarPegaso;
                //////="////////////////////////////////////////////////////////";
                linea1="Esperad Escuadrón F.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 6:
                hor=horI;
                kib=kibI;
                mor=morI;
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Quereís que le de el golpe de gracia?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 7:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="No creo que sea muy buena idea Mordeim.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 8:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Vaaaaaaaaa. Aguafiestas.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 9:
                avatarDialogo=this.avatarPegaso;
                //////="////////////////////////////////////////////////////////";
                linea1="Habeís cometido un grave error.";
                linea2="Me ire a Deyólica pero, cuando Archi se entere de que";
                linea3="no volveís, os enfrentaréis a él.";
                linea4="";
                break;
            case 10:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="¿A Archi?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 11:
                avatarDialogo=this.avatarPegaso;
                //////="////////////////////////////////////////////////////////";
                linea1="No, al Jinete Espectral.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 12:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Y?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 13:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Mordeim, el Jinete Espectral es uno de los líderes";
                linea2="de Cardinal y un domador de wyverns.";
                linea3="";
                linea4="";
                break;
            case 14:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Horacia, lo repetire una vez más: ¿Y?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 15:
                avatarDialogo=this.avatarPegaso;
                //////="////////////////////////////////////////////////////////";
                linea1="JAJAJAJA, prepararos para lo peor.";
                linea2="Me voy.";
                linea3="";
                linea4="";
                break;
            case 16:
                pegaso=pegasoV;
                posicionL.x-=0.2f*i;
                if(posicionL.x<=(-480)){
                    estado++;
                }
                break;
            case 17:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Chicos, debemos encontrar rápidamente el sello";
                linea2="o Belerofonte le contará a Archi lo que hemos hecho";
                linea3="y la Sacerdotisa Hestia entará en un aprieto.";
                linea4="Vamos.";
                break;
            case 18:
                hor=horD;
                kib=kibD;
                mor=morD;
                posicion.x+=0.2f*i;
                if(posicion.x>=256){
                    estado++;
                }
                break;
            case 19:
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