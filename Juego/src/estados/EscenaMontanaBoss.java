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


public class EscenaMontanaBoss extends BasicGameState{
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
    private Animation horS,kibS,morS,morI;
    private Animation dragon,dragonV;
    private Animation sombra;
    private Image fondo;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo, avatarH,avatarM, avatarK, avatarDragon;
    /*Sonido*/
    private Sound sonidoSelect,vuelo,roar,dragonSonido;
    int time;//EDIT
    private TrueTypeFont texto;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 15); 
    
    public EscenaMontanaBoss(int id) {
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
        Image[] morIzq={new Image("Imagenes/Animaciones/Sprites/mor5.png")};
        morI=new Animation(morIzq,200);
        hor=horD;
        kib=kibD;
        mor=morD;
        Image[] dragonVolador={new Image("Imagenes/Animaciones/Sprites/dragon5.png"),new Image("Imagenes/Animaciones/Sprites/dragon6.png"),new Image("Imagenes/Animaciones/Sprites/dragon7.png"),new Image("Imagenes/Animaciones/Sprites/dragon8.png")};
        dragonV=new Animation(dragonVolador,200);
        dragon=dragonV;
        Image[] dragonSombra={new Image("Imagenes/Animaciones/Sprites/sombra.png")};
        sombra=new Animation(dragonSombra,200);
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
        avatarDragon = new Image("Imagenes/Personajes/Dragon.png");
        avatarDialogo = avatarH;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        vuelo = new Sound("Musica/Efectos/Vuelo.wav");
        roar = new Sound("Musica/Efectos/Grito_dragon.wav");
        dragonSonido = new Sound("Musica/Efectos/Dragon.wav");
        texto= new TrueTypeFont(letraMenu, true);
        /**/
        
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
        
            fondo.draw(-1200, -1100);
            
            //EDIT:Rener Mordeim
            if(reproducirExclamacion){
                this.exclamacion.draw(posicion.x-64, posicion.y+176);
            }
            
            if(estado>=0){
                hor.draw(posicion.x+632, posicion.y+240);
                mor.draw(posicion.x+600, posicion.y+272);
                kib.draw(posicion.x+600, posicion.y+208);
                if(estado>=1 && estado!=3 && estado!=7 && estado!=11 &&estado!=16){
                renderDialogo();
                }
                if(estado>=3){
                    sombra.draw(posicionL.x, posicionL.y+200);
                }
                if(estado>=7){
                    dragon.draw(posicionE.x+1350, posicionE.y+200);
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
                time+=i;
                if(!dragonSonido.playing())
                {
                    dragonSonido.play();
                }
                if(time/1000>0.5f)//
                {
                    time=0;
                    estado++;
                }
                hor=horS;
                kib=kibS;
                mor=morS;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¡¿Qué ha sido eso?!";
                linea2="Mordeim, ¿ha sido tu estómago?";
                linea3="";
                linea4="";
                break;
            case 2:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Habrá sido el de este tío, que es un glotón.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 3:
                posicionL.x+=0.9f*i;
                if(posicionL.x>=1400){
                    estado++;
                }
                break;
            case 4:
                time+=i;
                if(!dragonSonido.playing())
                {
                    dragonSonido.play();
                }
                avatarDialogo=this.avatarH;
                linea1="¡¡¡NO ME HACE NINGUNA GRACIA!!!";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 5:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="No hemos sido ninguno de nosotros Horacia.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 6:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Glup. Entonces, ¿quién hace ese ruido?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 7:
                vuelo.play();
                posicionE.x-=0.25f*i;
                if(posicionE.x<=(-404)){
                    estado++;
                }
                break;
            case 8:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="HOS*** PU**, ESTE BICHO ES ENORME!!!!!";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 9:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="¿POR QUÉ UN DRAGÓN?";
                linea2="¿POR QUÉ UN MALDITO DRAGÓN?";
                linea3="";
                linea4="";
                break;
            case 10:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¿NO SE HABÍAN EXTINGUIDO EN LA";
                linea2="GUERRA DEMONIACA?";
                linea3="";
                linea4="";
                break;
            case 11:
                time+=i;
                if(!roar.playing())
                {
                    roar.play();
                }
                if(time/1000>0.7f)//
                {
                    time=0;
                    estado++;
                }
                break;
            case 12:
                avatarDialogo=this.avatarDragon;
                //////="////////////////////////////////////////////////////////";
                linea1="GRRRRRRRRRRRRR!!!!!!!";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 13:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Propongo dar media vuelta y huir ahora que podemos.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 14:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Y que pasa con el sello?";
                linea2="No podemos dejarlo por ahí.";
                linea3="";
                linea4="";
                break;
            case 15:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Lo siento, soy alérgico a la muerte.";
                linea2="Yo me largo.";
                linea3="";
                linea4="";
                break;
            case 16:
                mor=morI;
                time+=i;
                if(!roar.playing())
                {
                    roar.play();
                }
                if(time/1000>0.7f)//
                {
                    time=0;
                    estado++;
                }
                break;
            case 17:
                mor=morS;
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="JOD*R, os ayudaré a matar a este bicharraco.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 18:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Dónde esta mi pluma para mi testamento?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 19:
                estado=0;
//                VenganzaBelial.MapaActual=10;//BOSSMONTAÑA //EDIT
                VenganzaBelial.atributoGestion.setMapaActual(16);
                sbg.enterState(VenganzaBelial.ESTADOCOMBATE);
                //Deberiamos entrar en estado Combate contra el Dragon
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