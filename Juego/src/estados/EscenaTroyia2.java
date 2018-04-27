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

/**
 *
 * @author Dolores
 */
public class EscenaTroyia2 extends BasicGameState{
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
    private Animation horI,kibI,morI;
    private Animation sac,sacI;
    private Animation misterio,misterioI,misterioD,misterioA;
    private Image fondo;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo, avatarH,avatarM, avatarK, avatarSacerdote;
    private Image avatarMisterio;
    /*Sonido*/
    private Sound sonidoSelect;
    int time;//EDIT
    private TrueTypeFont texto;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 15); 
    
    public EscenaTroyia2(int id) {
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
        Image[] horIzq={new Image("Imagenes/HeroeMundo/her30.png"),new Image("Imagenes/HeroeMundo/her31.png"),new Image("Imagenes/HeroeMundo/her32.png")};
        horI=new Animation(horIzq,200);
        Image[] morIzq={new Image("Imagenes/Animaciones/Sprites/mor4.png"),new Image("Imagenes/Animaciones/Sprites/mor5.png"),new Image("Imagenes/Animaciones/Sprites/mor6.png")};
        morI=new Animation(morIzq,200);
        Image[] kibIzq={new Image("Imagenes/Animaciones/Sprites/kib4.png"),new Image("Imagenes/Animaciones/Sprites/kib5.png"),new Image("Imagenes/Animaciones/Sprites/kib6.png")};
        kibI=new Animation(kibIzq,200);
        hor=horD;
        kib=kibD;
        mor=morD;
        Image[] fanIzq={new Image("Imagenes/Animaciones/Sprites/fanatic5.png")};
        sacI=new Animation(fanIzq,200);
        sac=sacI;
        Image[] misIzq={new Image("Imagenes/Animaciones/Sprites/misterio4.png"),new Image("Imagenes/Animaciones/Sprites/misterio5.png"),new Image("Imagenes/Animaciones/Sprites/misterio6.png")};
        misterioI=new Animation(misIzq,200);
        Image[] misStop={new Image("Imagenes/Animaciones/Sprites/misterio2.png")};
        misterioA=new Animation(misStop,200);
        Image[] misDer={new Image("Imagenes/Animaciones/Sprites/misterio7.png"),new Image("Imagenes/Animaciones/Sprites/misterio8.png"),new Image("Imagenes/Animaciones/Sprites/misterio9.png")};
        misterioD=new Animation(misDer,200);
        misterio=misterioI;
        fondo= new Image("Imagenes/Escenas/EscenaBosque1/mapaBosque.png");
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
        avatarSacerdote = new Image("Imagenes/Avatar/Caras/fanatic.png");
        avatarMisterio = new Image("Imagenes/Avatar/Caras/misterio.png");
        avatarDialogo = avatarH;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        texto= new TrueTypeFont(letraMenu, true);
        /**/
        
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
        
            fondo.draw(-1800, -1184);
            
            //EDIT:Rener Mordeim
            if(reproducirExclamacion){
                this.exclamacion.draw(posicion.x-64, posicion.y+176);
            }
            if(estado>=0){
                hor.draw(posicion.x, posicion.y+240);
                mor.draw(posicion.x, posicion.y+272);
                kib.draw(posicion.x, posicion.y+208);
                sac.draw(posicionS.x+364, posicionS.y+240);
                if(estado>=1 && estado!=6 && estado!=10 && estado!=13){
                renderDialogo();
                }
                if(estado>=6){
                    misterio.draw(posicionE.x+664, posicionE.y+208);
                }
            }
            texto.drawString(1000, 0, "" + estado);
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
                avatarDialogo=this.avatarSacerdote;
                //////="////////////////////////////////////////////////////////";
                linea1="Bienvenidos a Troyia jóvenes aventureros,";
                linea2="soy Guilles,el sacerdote de esta ciudad.";
                linea3="";
                linea4="";
                break;
            case 2:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Encantada, yo soy Horacia.";
                linea2="Estos de aquí son mis compañeros";
                linea3="Mordeim y Kibito";
                linea4="";
                break;
            case 3:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Sabeís por casualidad si están resucitando muertos";
                linea2="vivientes en Troyia sacerdote Guilles?";
                linea3="";
                linea4="";
                break;
            case 4:
                avatarDialogo=this.avatarSacerdote;
                linea1="¿Muertos vivientes?";
                linea2="Es la primera vez que oigo un disparate tan gracioso.";
                linea3="JAJAJAJAJAJAJA";
                linea4="";
                break;
            case 5:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Ese maldito viejo nos engaño, te dije que no te";
                linea2="fiaras de un viejo con una guapísima cicatriz en";
                linea3="medio del ojo Horacia.";
                linea4="";
                break;
            case 6:
                posicionE.x-=0.1f*i;
                if(posicionE.x<=(-300)){
                    estado++;
                }
                break;
            case 7:
                misterio=misterioA;
                avatarDialogo=this.avatarMisterio;
                //////="////////////////////////////////////////////////////////";
                linea1="(Susurro)";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 8:
                avatarDialogo=this.avatarSacerdote;
                //////="////////////////////////////////////////////////////////";
                linea1="Vaya, me necesitan en otro lugar.";
                linea2="En fin, disfrutad de vuestra estancia en Troyia.";
                linea3="";
                linea4="";
                break;
            case 9:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Muy bien Sacertode Guilles, hasta luego.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 10:
                hor=horI;
                mor=morI;
                kib=kibI;
                posicion.x-=0.1f*i;
                if(posicion.x<=(-256)){
                    estado++;
                }
                break;
            case 11:
                avatarDialogo=this.avatarSacerdote;
                linea1="Así que estos idiotas son lo de Cardinal.";
                linea2="Veo que saben lo de los muertos vivientes pero nada";
                linea3="del ritual.";
                linea4="JAJAJAJAJAJA, pobrecillos.";
                linea5="Quiero que avises al Gran Maestro inmediatamente.";
                break;
            case 12:
                avatarDialogo=this.avatarMisterio;
                linea1="Como ordene señor.";
                linea2="";
                linea3="";
                linea4="";
                linea5="";
                break;
            case 13:
                misterio=misterioD;
                posicionE.x+=0.1f*i;
                if(posicionE.x>=100){
                    estado++;
                }
                break;
            case 14:
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