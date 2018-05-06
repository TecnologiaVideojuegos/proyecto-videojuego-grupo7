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

public class EscenaDeyolicaPostMontana extends BasicGameState{
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
    /*Mapa*/
    private Vector2f posicion,posicionE,posicionS;
    private static final int esquinaXMapa=0;
    private static final int esquinaYMapa=0;
    /*Animaciones*/
    private Animation hor,kib,mor;
    private Animation horD,kibD,morD;
    private Animation horS,kibS,morS;
    private Image cap,sol;
    private Image fondo;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo, avatarH,avatarM, avatarK,avatarCap;
    /*Sonido*/
    private Sound sonidoSelect;
    int time;//EDIT
    private TrueTypeFont texto;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 15); 
    
    public EscenaDeyolicaPostMontana(int id) {
        this.idEstado=id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Image[] horDere={new Image("Imagenes/HeroeMundo/her30.png"),new Image("Imagenes/HeroeMundo/her31.png"),new Image("Imagenes/HeroeMundo/her32.png")};
        horD=new Animation(horDere,200);
        Image[] morDere={new Image("Imagenes/Animaciones/Sprites/mor4.png"),new Image("Imagenes/Animaciones/Sprites/mor5.png"),new Image("Imagenes/Animaciones/Sprites/mor6.png")};
        morD=new Animation(morDere,200);
        Image[] kibDere={new Image("Imagenes/Animaciones/Sprites/kib4.png"),new Image("Imagenes/Animaciones/Sprites/kib5.png"),new Image("Imagenes/Animaciones/Sprites/kib6.png")};
        kibD=new Animation(kibDere,200);
        Image[] kibF={new Image("Imagenes/Animaciones/Sprites/kib5.png")};
        kibS=new Animation(kibF,200);
        Image[] morF={new Image("Imagenes/Animaciones/Sprites/mor5.png")};
        morS=new Animation(morF,200);
        Image[] horEnfrente={new Image("Imagenes/HeroeMundo/her31.png")};
        horS=new Animation(horEnfrente,200);
        hor=horD;
        kib=kibD;
        mor=morD;
        sol = new Image("Imagenes/Animaciones/Sprites/soldado1.png");
        cap = new Image("Imagenes/Animaciones/Sprites/capitan1.png");
        fondo= new Image("Imagenes/Escenas/SalaInicial/MapaTutorial.png");
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
        avatarCap = new Image("Imagenes/Avatar/Caras/Capitan.png");
        avatarDialogo = avatarH;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        texto= new TrueTypeFont(letraMenu, true);
        /**/
        
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
        
            fondo.draw(0, 0);
            
            //EDIT:Rener Mordeim
            
            if(estado>=0){
                hor.draw(posicion.x+1088, posicion.y-112);
                mor.draw(posicion.x+1088, posicion.y-80);
                kib.draw(posicion.x+1088, posicion.y-144);
                cap.draw(posicionE.x+640, posicionE.y-112);
                sol.draw(posicionE.x+640, posicionE.y-80);
                sol.draw(posicionE.x+640, posicionE.y-144);
                if(estado>=1){
                renderDialogo();
                }
            }
//            texto.drawString(1000, 0, "" + estado);
    }
    @Override
    //Muestra la actualización
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
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
                posicion.x-=0.1f*i;
                if(posicion.x<=(-256)){
                    estado++;
                }
                break;
            case 1:
                hor=horS;
                kib=kibS;
                mor=morS;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Por fin llegamos a Ciudad Deyolica.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 2:
                avatarDialogo=this.avatarCap;
                //////="////////////////////////////////////////////////////////";
                linea1="Alto hay Escuadrón F.";
                linea2="Quedaís arrestados por ordenes del General.";
                linea3="";
                linea4="";
                break;
            case 3:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Creo que Belerofonte ha informado a Archi";
                linea2="de nuestra misión y no le ha gustado si ha mandado";
                linea3="que el General mande a sus soldados ha arrestarnos";
                linea4="nada más llegar a la ciudad.";
                break;
            case 4:
                avatarDialogo=this.avatarM;
                linea1="Si creen que nos van ha arrestar por las buenas";
                linea2="van listos estos soldaduchos de pacotilla.";
                linea3="";
                linea4="";
                break;
            case 5:
                avatarDialogo=this.avatarCap;
                linea1="Si os resistis a la autoridad, el castigo será mayor";
                linea2="de lo que va ha ser.";
                linea3="";
                linea4="";
                break;
            case 6:
                avatarDialogo=this.avatarH;
                linea1="Tenemos que llegar hasta Cardinal para cumplir";
                linea2="nuestra misión, por ello debemos avanzar.";
                linea3="";
                linea4="";
                break;
            case 7:
                avatarDialogo=this.avatarCap;
                linea1="En ese caso no me dais alternativa.";
                linea2="¡¡¡ARRESTADLOS!!!";
                linea3="";
                linea4="";
                break;
            case 8:
                //Combate contra dos soldados y un capitán
                estado=0;
                VenganzaBelial.atributoGestion.setMapaActual(7);
                sbg.enterState(VenganzaBelial.ESTADOMAPAJUEGO);
//                sbg.enterState(VenganzaBelial.ESTADOMENUINICIO);//EDIT:
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