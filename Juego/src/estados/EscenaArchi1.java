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


public class EscenaArchi1 extends BasicGameState{
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
    private Vector2f posicion,posicionE,posicionL;
    private static int esquinaXMapa=0;
    private static int esquinaYMapa=0;
    /*Animaciones*/
    private SpriteSheet sheetExclamacion;
    private Animation exclamacion;
    private Animation archi,archiS1,archiS,archiDown;
    private Animation nar,narI,narS;
    private Animation hestia,hestiaS,hestiaU,hestiaDown,hestiaD,hestiaEnfrente;
    private Image fondo;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo, avatarH,avatarA, avatarN;
    /*Sonido*/
    private Sound sonidoSelect,sonidoPuerta;
    private Music battle;
    int time;//EDIT
    private TrueTypeFont texto;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 15); 
    
    public EscenaArchi1(int id) {
        this.idEstado=id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Image[] archiUp={new Image("Imagenes/Animaciones/Sprites/archi11.png")};
        archiS=new Animation(archiUp,200);
        Image[] archiDown1={new Image("Imagenes/Animaciones/Sprites/archi1.png"),new Image("Imagenes/Animaciones/Sprites/archi2.png"),new Image("Imagenes/Animaciones/Sprites/archi3.png")};
        archiDown=new Animation(archiDown1,200);
        Image[] archiStop={new Image("Imagenes/Animaciones/Sprites/archi2.png")};
        archiS1=new Animation(archiStop,200);
        archi=archiS;
        Image[] hestiaDer={new Image("Imagenes/Animaciones/Sprites/hes9.png"),new Image("Imagenes/Animaciones/Sprites/hes8.png"),new Image("Imagenes/Animaciones/Sprites/hes7.png")};
        hestiaD=new Animation(hestiaDer,200);
        Image[] hestiaStop={new Image("Imagenes/Animaciones/Sprites/hes11.png")};
        hestiaS=new Animation(hestiaStop,200);
        Image[] hestiaUp={new Image("Imagenes/Animaciones/Sprites/hes10.png"),new Image("Imagenes/Animaciones/Sprites/hes11.png"),new Image("Imagenes/Animaciones/Sprites/hes12.png")};
        hestiaU=new Animation(hestiaUp,200);
        Image[] hestiaDown1={new Image("Imagenes/Animaciones/Sprites/hes2.png")};
        hestiaDown=new Animation(hestiaDown1,200);
        Image[] hestiaE={new Image("Imagenes/Animaciones/Sprites/hes5.png")};
        hestiaEnfrente=new Animation(hestiaE,200);
        hestia=hestiaU;
        Image[] narIzq={new Image("Imagenes/Animaciones/Sprites/nar4.png"),new Image("Imagenes/Animaciones/Sprites/nar5.png"),new Image("Imagenes/Animaciones/Sprites/nar6.png")};
        narI=new Animation(narIzq,200);
        Image[] narStop={new Image("Imagenes/Animaciones/Sprites/nar2.png")};
        narS=new Animation(narStop,200);
        nar=narI;
        fondo= new Image("Imagenes/Escenas/SalaInicial/SalaArchi.png");
        /**/
        this.sheetExclamacion= new SpriteSheet("Imagenes/Animaciones/enfado.png",32,33);
        this.exclamacion = new Animation(sheetExclamacion,200);
        /**/
        estado=0;
        this.input = gc.getInput();
        mensajePantalla= new TrueTypeFont(tipoLetra, true);
        posicion = new Vector2f(0,300);
        posicionE = new Vector2f(0,300);
        posicionL = new Vector2f(0,300);
        ventanaDialogo= new Image("Imagenes/Avatar/cajaMensaje.png");
        avatarH =  new Image("Imagenes/Personajes/HestiaA.png");
        avatarA =  new Image("Imagenes/Personajes/Archi.png");
        avatarN =  new Image("Imagenes/Personajes/EncapuchadoA.png");
        avatarDialogo = avatarH;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        sonidoPuerta=new Sound("Musica/Efectos/Door.wav");
        texto= new TrueTypeFont(letraMenu, true);
        battle = new Music("Musica/BSO/Archi.wav");
        /**/
        
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
        
            //EDIT:Rener Mordeim
            
            if(estado>=0 && estado<=20){
                fondo.draw(esquinaXMapa,esquinaYMapa);
                
                if(estado>=0 && estado<17){
                archi.draw(posicion.x+224, posicion.y-96);
                }
                
                if(estado==7 && estado==9){
                    if(reproducirExclamacion){
                        this.exclamacion.draw(posicion.x+224, posicion.y-192);
                    }
                }
                
                if(estado>=0 && estado!=1 && estado!=14 && estado!=15 && estado!=16 && estado!=18){
                renderDialogo();
                }
                
                if(estado>=1){
                    hestia.draw(posicionL.x+224, posicionL.y+128);
                    }
                
                if(estado>=18){
                    nar.draw(posicionE.x+384, posicionE.y+48);
                }
                
            }
            
                
            texto.drawString(1000, 0, "" + estado);
    }
    @Override
    //Muestra la actualización
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        exclamacion.update(i);
        if(input.isKeyPressed(Input.KEY_ENTER)){
                sonidoSelect.play(1, 0.2f);
                time=0;
                estado++;
                if(estado>=1){
                    battle.play();
                }
                if(estado==19){
                    battle.stop();
                }
            }
        
        switch (estado)
        {
            case 0:
                avatarDialogo=this.avatarA;
                //////="////////////////////////////////////////////////////////";
                linea1="...";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 1:
                time+=i;
                if(!sonidoPuerta.playing())
                {
                    sonidoPuerta.play();
                }
                posicionL.y-=0.1f*i;
                if(posicionL.y<=224){
                    estado++;
                }
                break;
            case 2:
                hestia = hestiaS;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Quería verme Archi?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 3:
                archi=archiS1;
                avatarDialogo=this.avatarA;
                //////="////////////////////////////////////////////////////////";
                linea1="Asi es Sacerdotisa Hestia.";
                linea2="Quiero que me respondas con total sinceridad.";
                linea3="¿Sabes por casualidad donde esta el escuadrón F?";
                linea4="";
                break;
            case 4:
                avatarDialogo=this.avatarH;
                linea1="¿Por qué lo dice? ¿Acaso han hecho algo otra vez?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 5:
                avatarDialogo=this.avatarA;
                //////="////////////////////////////////////////////////////////";
                linea1="Por eso se lo digo, llevan días sin que nadie venga";
                linea2="para que me digan que la han vuelto a cagar.";
                linea3="";
                linea4="";
                break;
            case 6:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Pero, ¿eso no es una buena noticia?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 7:
                reproducirExclamacion=true;
                avatarDialogo=this.avatarA;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Te crees que no me he enterado de lo que esos idiotas";
                linea2="han hecho en el Bosque de Tambler, o en Tambler, o en";
                linea3="Troyia?";
                linea4="";
                break;
            case 8:
                reproducirExclamacion=false;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Ahi Dios, ¿qué es lo que han hecho esta vez?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 9:
                reproducirExclamacion=true;
                avatarDialogo=this.avatarA;
                //////="////////////////////////////////////////////////////////";
                linea1="Han estado toqueteando los sellos con mi llave.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 10:
                reproducirExclamacion=false;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="(Les dije que no llamarán la atención.)";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 11:
                avatarDialogo=this.avatarA;
                linea1="¿Has dicho algo Sacerdotisa Hestia?";
                linea2="";
                linea3="";
                linea4="";
                break;
                
            case 12:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Creo que lo mejor será detener lo que quiera que esten";
                linea2="haciendo con los sellos, ya que sabemos el peligro";
                linea3="que conllevaría si Belial llega a nuestro mundo.";
                linea4="";
                break;
            case 13:
                avatarDialogo=this.avatarA;
                //////="////////////////////////////////////////////////////////";
                linea1="Asi es Sacerdotisa Hestia.";
                linea2="Por eso haré que vayan tras ellos.";
                linea3="";
                linea4="";
                break;
            case 14:
                archi=archiDown;
                posicion.y+=0.1f*i;
                if(posicion.y>=412){
                    estado++;
                }
                break;
            case 15:
                archi=archiS1;
                hestia=hestiaD;
                posicionL.x-=0.1f*i;
                if(posicionL.x<=(-64)){
                    estado++;
                }
                break;
            case 16:
                archi=archiDown;
                hestia=hestiaS;
                posicion.y+=0.1f*i;
                if(posicion.y>=548){
                    estado++;
                }
                break;
            case 17:
                hestia=hestiaDown;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Espero que Archi no los encuentre o será su fin.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 18:
                posicionE.x-=0.1f*i;
                if(posicionE.x<=(-96)){
                    estado++;
                }
                break;
            case 19:
                nar=narS;
                avatarDialogo=this.avatarN;
                //////="////////////////////////////////////////////////////////";
                linea1="Y asi Archi decidio ir a por nuestros pobres";
                linea2="aventureros.";
                linea3="¿Cómo es que llego a todos los sitios si estaba en";
                linea4="el bar intentando ligar con la dueña?";
                break;
            case 20:
                estado=0;
                sbg.enterState(VenganzaBelial.ESCENATROYIAPOSTBOSS2);//EDIT:
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