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


public class EscenaCardinalMiniBoss1 extends BasicGameState{
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
    private static int esquinaYMapa=-960;
    /*Animaciones*/
    private SpriteSheet sheetExclamacion;
    private Animation exclamacion;
    private Animation hor,mor,kib;
    private Animation horD,morD,kibD;
    private Animation horS,morS,kibS;
    private Animation jinete,jineteS,jineteU;
    private Image wyvern,wyvern1,wyvern2,wyvern3,wyvern4,wyvern5,wyvern6,wyvern7;
    private Image fondo;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo,avatarH,avatarM,avatarK,avatarJ;
    /*Sonido*/
    private Sound sonidoSelect;
    int time;//EDIT
    private TrueTypeFont texto;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 15); 
    
    public EscenaCardinalMiniBoss1(int id) {
        this.idEstado=id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Image[] horDown={new Image("Imagenes/HeroeMundo/her00.png"),new Image("Imagenes/HeroeMundo/her01.png"),new Image("Imagenes/HeroeMundo/her02.png")};
        horD=new Animation(horDown,200);
        Image[] morDown={new Image("Imagenes/Animaciones/Sprites/mor1.png"),new Image("Imagenes/Animaciones/Sprites/mor2.png"),new Image("Imagenes/Animaciones/Sprites/mor3.png")};
        morD=new Animation(morDown,200);
        Image[] kibDown={new Image("Imagenes/Animaciones/Sprites/kib1.png"),new Image("Imagenes/Animaciones/Sprites/kib2.png"),new Image("Imagenes/Animaciones/Sprites/kib3.png")};
        kibD=new Animation(kibDown,200);
        Image[] kibF={new Image("Imagenes/Animaciones/Sprites/kib8.png")};
        kibS=new Animation(kibF,200);
        Image[] morF={new Image("Imagenes/Animaciones/Sprites/mor8.png")};
        morS=new Animation(morF,200);
        Image[] horEnfrente={new Image("Imagenes/HeroeMundo/her11.png")};
        horS=new Animation(horEnfrente,200);
        hor=horD;
        kib=kibD;
        mor=morD;
        Image[] riderUp={new Image("Imagenes/Animaciones/Sprites/riderwalk4.png"),new Image("Imagenes/Animaciones/Sprites/riderwalk5.png"),new Image("Imagenes/Animaciones/Sprites/riderwalk6.png")};
        jineteU=new Animation(riderUp,200);
        Image[] riderStop={new Image("Imagenes/Animaciones/Sprites/riderwalk5.png")};
        jineteS=new Animation(riderStop,200);
        jinete=jineteS;
        wyvern = new Image("Imagenes/Animaciones/Sprites/Wyvern1.png");
        wyvern1 = new Image("Imagenes/Animaciones/Sprites/Wyvern1.png");
        wyvern2 = new Image("Imagenes/Animaciones/Sprites/Wyvern2.png");
        wyvern3 = new Image("Imagenes/Animaciones/Sprites/Wyvern3.png");
        wyvern4 = new Image("Imagenes/Animaciones/Sprites/Wyvern4.png");
        wyvern5 = new Image("Imagenes/Animaciones/Sprites/Wyvern5.png");
        wyvern6 = new Image("Imagenes/Animaciones/Sprites/Wyvern6.png");
        wyvern7 = new Image("Imagenes/Animaciones/Sprites/Wyvern7.png");
        fondo= new Image("Imagenes/Escenas/SalaInicial/Cardinal.png");
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
        avatarH =  new Image("Imagenes/Personajes/HoraciaA.png");
        avatarM =  new Image("Imagenes/Personajes/MordeimA.png");
        avatarK =  new Image("Imagenes/Personajes/KibitoA.png");
        avatarJ = new Image("Imagenes/Personajes/Rider.png");
        avatarDialogo = avatarH;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        texto= new TrueTypeFont(letraMenu, true);
        /**/
        
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
        
            //EDIT:Rener Mordeim
            
            if(estado>=0 && estado<=22){
                fondo.draw(esquinaXMapa,esquinaYMapa);
                hor.draw(posicion.x+64, posicion.y+80);
                mor.draw(posicion.x+96, posicion.y+48);
                kib.draw(posicion.x+32, posicion.y+48);
                jinete.draw(posicionE.x+320, posicionE.y+160);
                wyvern1.draw(posicionL.x+160, posicionL.y+196);
                
                if(estado>=2 && estado!=8 && estado!=9 && estado!=10 && estado!=11 && estado!=12 && estado!=13 && estado!=14 && estado!=15 && estado!=16 && estado!=17){
                renderDialogo();
                }
                
            }
            
                
//            texto.drawString(1000, 0, "" + estado);
    }
    @Override
    //Muestra la actualización
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        exclamacion.update(i);
        if(input.isKeyPressed(Input.KEY_ENTER)){
                sonidoSelect.play(1, 0.2f);
                time=0;
                estado++;
            }
        
        switch (estado)
        {
            case 0:
                posicion.y+=0.1f*i;
                if(posicion.y>=384){
                    estado++;
                }
                break;
            case 1:
                hor=horS;
                mor=morS;
                kib=kibS;
                jinete=jineteU;
                posicionE.x-=0.1f*i;
                if(posicionE.x<=(-128)){
                    estado++;
                }
                break;
            case 2:
                jinete=jineteS;
                avatarDialogo=this.avatarJ;
                //////="////////////////////////////////////////////////////////";
                linea1="Vaya, vaya, vaya.";
                linea2="Pero si son el Escuadrón F.";
                linea3="";
                linea4="";
                break;
            case 3:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Es el Jinete Espectral.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 4:
                avatarDialogo=this.avatarJ;
                linea1="JAJAJAJA, habeís llegado muy lejos pero vuestra";
                linea2="aventura acabará aquí.";
                linea3="No podréis contra mi wyvern y yo.";
                linea4="";
                break;
            case 5:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Un wyvern? Vaaaaaaaaa, ya matamos a un dragón.";
                linea2="Esa lagartija no podrá conmigo.";
                linea3="";
                linea4="";
                break;
            case 6:
                avatarDialogo=this.avatarJ;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Lagartija? Pagarás muy cara tu osadía.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 7:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Mordeim siempre consigue cabrear al";
                linea2="enemigo muy fácilmente.";
                linea3="";
                linea4="";
                break;
            case 8:
                wyvern1=wyvern2;
                time+=i;
                if(time/1000>0.4)
                {
                    estado++;
                    time=0;
                }
                break;
            case 9:
                wyvern1=wyvern3;
                time+=i;
                if(time/1000>0.4)
                {
                    estado++;
                    time=0;
                }
                break;
            case 10:
                wyvern1=wyvern4;
                time+=i;
                if(time/1000>0.4)
                {
                    estado++;
                    time=0;
                }
                break;
            case 11:
                wyvern1=wyvern5;
                time+=i;
                if(time/1000>0.4)
                {
                    estado++;
                    time=0;
                }
                break;
            case 12:
                wyvern1=wyvern6;
                time+=i;
                if(time/1000>0.4)
                {
                    estado++;
                    time=0;
                }
                break;
            case 13:
                wyvern1=wyvern7;
                time+=i;
                if(time/1000>0.4)
                {
                    estado++;
                    time=0;
                }
                break;
            case 14:
                wyvern1=wyvern5;
                time+=i;
                if(time/1000>0.4)
                {
                    estado++;
                    time=0;
                }
                break;
            case 15:
                wyvern1=wyvern4;
                time+=i;
                if(time/1000>0.4)
                {
                    estado++;
                    time=0;
                }
                break;
            case 16:
                wyvern1=wyvern2;
                time+=i;
                if(time/1000>0.4)
                {
                    estado++;
                    time=0;
                }
                break;
            case 17:
                wyvern1=wyvern;
                time+=i;
                if(time/1000>0.4)
                {
                    estado++;
                    time=0;
                }
                break;
            case 18:
                avatarDialogo=this.avatarM;
                linea1="No me impresiona tu lagartija.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 19:
                avatarDialogo=this.avatarH;
                linea1="Yo no... no... ten...tengo...miedo de ti.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 20:
                avatarDialogo=this.avatarJ;
                linea1="JAJAJAJA, Archi debería de ser el que os sentenciara";
                linea2="pero seré yo el que le ahorre esa moletia.";
                linea3="Acabemos con ellos Igneel.";
                linea4="";
                break;
            case 21:
                //Combate contra Rider
                estado=0;
                //VenganzaBelial.MapaActual=10;//MINIBOSSCARDINAL //EDIT
                VenganzaBelial.atributoGestion.setMapaActual(10);
                sbg.enterState(VenganzaBelial.ESTADOCOMBATE);
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