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


public class EscenaCardinalMiniBoss2 extends BasicGameState{
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
    private Animation horS,morS,kibS;
    private Image jinete;
    private Image wyvern;
    private Image fondo;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo,avatarH,avatarM,avatarK,avatarJ;
    /*Sonido*/
    private Sound sonidoSelect;
    int time;//EDIT
    private TrueTypeFont texto;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 15); 
    
    public EscenaCardinalMiniBoss2(int id) {
        this.idEstado=id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Image[] kibF={new Image("Imagenes/Animaciones/Sprites/kib8.png")};
        kibS=new Animation(kibF,200);
        Image[] morF={new Image("Imagenes/Animaciones/Sprites/mor8.png")};
        morS=new Animation(morF,200);
        Image[] horEnfrente={new Image("Imagenes/HeroeMundo/her11.png")};
        horS=new Animation(horEnfrente,200);
        hor=horS;
        kib=kibS;
        mor=morS;
        jinete= new Image("Imagenes/Animaciones/Sprites/riderwalk5.png");
        wyvern = new Image("Imagenes/Animaciones/Sprites/Wyvern8.png");
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
                hor.draw(posicion.x+64, posicion.y+160);
                mor.draw(posicion.x+96, posicion.y+128);
                kib.draw(posicion.x+32, posicion.y+128);
                jinete.draw(posicionE.x+192, posicionE.y+160);
                wyvern.draw(posicionL.x+160, posicionL.y+196);
                
                if(estado>=0){
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
                VenganzaBelial.controlMusica.cambiarMusica("Musica/BSO/Cardinal.wav");
                avatarDialogo=this.avatarJ;
                //////="////////////////////////////////////////////////////////";
                linea1="Imposible, IMPOSIBLE!!!!";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 1:
                avatarDialogo=this.avatarJ;
                //////="////////////////////////////////////////////////////////";
                linea1="Solo veo oscuridad ante mi.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 2:
                jinete.rotate(-90);
                estado++;
                break;
            case 3:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Jope con el Jinete Espectral, este si que ha sido";
                linea2="un hueso duro de roer, se nota que era de los más";
                linea3="poderosos de todo Cardinal.";
                linea4="";
                break;
            case 4:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Solo era fuerte porque tenia a la lagartija.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 5:
                avatarDialogo=this.avatarK;
                linea1="¿Creeis que Archi se tomará bien la noticia";
                linea2="de que el Jinete Espectral ha sido derrotado?";
                linea3="";
                linea4="";
                break;
            case 6:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="No lo creo, pero me preocupa la Sacerdotisa Hestia.";
                linea2="Pero nuestra prioridad es cumplir la misión, asi que";
                linea3="hay que seguir adelante.";
                linea4="";
                break;
            case 7:
                estado=0;
                VenganzaBelial.atributoGestion.setMapaActual(7);
                sbg.enterState(VenganzaBelial.ESTADOMAPAJUEGO);//EDIT:
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