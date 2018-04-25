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


public class EscenaTroyia1 extends BasicGameState{
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
    private boolean reproducirExclamacion1=false;
    private boolean reproducirEncapuchado=false;
    /*Mapa*/
    private Vector2f posicion,posicionE,posicionA,posicionEn;
    private static final int esquinaXMapa=0;
    private static final int esquinaYMapa=0;
    /*Animaciones*/
    private Animation hor,horI,horS;
    private Animation mor,morI,morS;
    private Animation kib,kibI,kibS;
    private Animation mis,misU,misD,misS;
    private Image fondo;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo, avatarH,avatarM, avatarK;
    private Image avatarMisterio,avatarE;
    private Image salidaEscena;
    /*Sonido*/
    private Sound sonidoSelect;
    private Music ost;
    int time;//EDIT
    private TrueTypeFont texto;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 15); 
    
    public EscenaTroyia1(int id) {
        this.idEstado=id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Image[] horIzq={new Image("Imagenes/HeroeMundo/her30.png"),new Image("Imagenes/HeroeMundo/her31.png"),new Image("Imagenes/HeroeMundo/her32.png")};
        horI=new Animation(horIzq,200);
        Image[] morIzq={new Image("Imagenes/Animaciones/Sprites/mor4.png"),new Image("Imagenes/Animaciones/Sprites/mor5.png"),new Image("Imagenes/Animaciones/Sprites/mor6.png")};
        morI=new Animation(morIzq,200);
        Image[] kibIzq={new Image("Imagenes/Animaciones/Sprites/kib4.png"),new Image("Imagenes/Animaciones/Sprites/kib5.png"),new Image("Imagenes/Animaciones/Sprites/kib6.png")};
        kibI=new Animation(kibIzq,200);
        Image[] horF={new Image("Imagenes/HeroeMundo/her31.png")};
        horS=new Animation(horF,200);
        Image[] kibF={new Image("Imagenes/Animaciones/Sprites/kib5.png")};
        kibS=new Animation(kibF,200);
        Image[] morF={new Image("Imagenes/Animaciones/Sprites/mor5.png")};
        morS=new Animation(morF,200);
        hor=horI;
        kib=kibI;
        mor=morI;
        Image[] misUp={new Image("Imagenes/Animaciones/Sprites/misterio10.png"),new Image("Imagenes/Animaciones/Sprites/misterio11.png"),new Image("Imagenes/Animaciones/Sprites/misterio12.png")};
        misU=new Animation(misUp,200);
        Image[] misDown={new Image("Imagenes/Animaciones/Sprites/misterio1.png"),new Image("Imagenes/Animaciones/Sprites/misterio2.png"),new Image("Imagenes/Animaciones/Sprites/misterio3.png")};
        misD=new Animation(misDown,200);
        Image[] misStop={new Image("Imagenes/Animaciones/Sprites/misterio11.png")};
        misS=new Animation(misStop,200);
        mis=misU;
        fondo= new Image("Imagenes/Escenas/EscenaPuerto/Puerto.png");
        /**/
        estado=0;
        this.input = gc.getInput();
        mensajePantalla= new TrueTypeFont(tipoLetra, true);
        posicion = new Vector2f(0,300);
        posicionE = new Vector2f(0,300);
        posicionA = new Vector2f(0,300);
        posicionEn = new Vector2f(0,300);
        ventanaDialogo= new Image("Imagenes/Avatar/cajaMensaje.png");
        avatarH =  new Image("Imagenes/Personajes/HoraciaA.png");
        avatarM =  new Image("Imagenes/Personajes/MordeimA.png");
        avatarK =  new Image("Imagenes/Personajes/KibitoA.png");
        avatarMisterio = new Image("Imagenes/Avatar/Caras/misterio.png");
        avatarDialogo = avatarH;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        texto= new TrueTypeFont(letraMenu, true);
        /**/
        
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
        
            fondo.draw(0,0);
            
            //EDIT:Rener Mordeim
            if(estado>=0){
                hor.draw(posicion.x+1024, posicion.y+64);
                mor.draw(posicion.x+1024, posicion.y+32);
                kib.draw(posicion.x+1024, posicion.y+96);
                if(estado>0 && estado!=6 && estado!=7 && estado!=8 && estado!=10){
                renderDialogo();
                }
                if(estado>=7){
                    mis.draw(posicionE.x+640, posicionE.y+500);
                }
            }
                
            texto.drawString(1000, 0, "" + estado);
    }
    @Override
    //Muestra la actualización
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(input.isKeyPressed(Input.KEY_ENTER)){
            if(estado!=24)
            {
                sonidoSelect.play(1, 0.2f);
                time=0;
                estado++;
            }
            
        }
        switch (estado)
        {
            case 0:
                posicion.x-=0.1f*i;
                if(posicion.x<=(-160)){
                    estado++;
                }
                break;
            case 1:
                hor=horS;
                kib=kibS;
                mor=morS;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¿A si qué esto es Troyia?";
                linea2="Me esperaba algo mucho peor, pero";
                linea3="parece que no hay nada fuera de lo normal.";
                linea4="";
                break;
            case 2:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Por lo que dijo el alcalde de Tambler la gente esta";
                linea2="resucitando en forma de muertos vivientes que destruyen";
                linea3="todo a su paso.";
                linea4="Tengamos mucho cuidado.";
                break;
            case 3:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Pues a mi no me parece que esta ciudad sea peligrosa.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 4:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Ja, nada es peligroso para mi.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 5:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="En fin, será mejor ir a donde este el sello.";
                linea2="Vamos equipo.";
                linea3="";
                linea4="";
                break;
            case 6:
                hor=horI;
                mor=morI;
                kib=kibI;
                posicion.x-=0.2f*i;
                if(posicion.x<=(-1184)){
                    estado++;
                }
                break;
            case 7:
                time+=i;
                if(time/1000>0.4)
                {
                    estado++;
                    time=0;
                }
                break;
            case 8:
                posicionE.y-=0.1f*i;
                if(posicionE.y<=(-96)){
                    estado++;
                }
                break;
            case 9:
                mis=misS;
                avatarDialogo=this.avatarMisterio;
                //////="////////////////////////////////////////////////////////";
                linea1="Será mejor informar al maestro que estos idiotas";
                linea2="han llegado a la ciudad.";
                linea3="";
                linea4="";
                break;
            case 10:
                mis=misD;
                posicionE.y+=0.1f*i;
                if(posicionE.y>=560){
                    estado++;
                }
                break;
            case 11:
                estado=0;
                sbg.enterState(VenganzaBelial.ESTADOMENUINICIO);
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
