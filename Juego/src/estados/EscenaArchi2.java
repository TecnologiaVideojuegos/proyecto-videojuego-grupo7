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


public class EscenaArchi2 extends BasicGameState{
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
    private Animation archi,archiS,archiD;
    private Animation hestia,hestiaS,hestiaD;
    private Animation general,generalD,generalS;
    private Animation rider,riderI,riderS;
    private Image fondo;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo,avatarH,avatarA,avatarR,avatarG;
    /*Sonido*/
    private Sound sonidoSelect;
    private Music battle;
    int time;//EDIT
    private TrueTypeFont texto;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 15); 
    
    public EscenaArchi2(int id) {
        this.idEstado=id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Image[] archiStop={new Image("Imagenes/Animaciones/Sprites/archi8.png")};
        archiS=new Animation(archiStop,200);
        Image[] archiDer={new Image("Imagenes/Animaciones/Sprites/archi7.png"),new Image("Imagenes/Animaciones/Sprites/archi8.png"),new Image("Imagenes/Animaciones/Sprites/archi9.png")};
        archiD=new Animation(archiDer,200);
        archi=archiD;
        Image[] hestiaDer={new Image("Imagenes/Animaciones/Sprites/hes7.png"),new Image("Imagenes/Animaciones/Sprites/hes8.png"),new Image("Imagenes/Animaciones/Sprites/hes9.png")};
        hestiaD=new Animation(hestiaDer,200);
        Image[] hestiaStop={new Image("Imagenes/Animaciones/Sprites/hes8.png")};
        hestiaS=new Animation(hestiaStop,200);
        hestia=hestiaD;
        Image[] genDer={new Image("Imagenes/Animaciones/Sprites/general7.png"),new Image("Imagenes/Animaciones/Sprites/general8.png"),new Image("Imagenes/Animaciones/Sprites/general9.png")};
        generalD=new Animation(genDer,200);
        Image[] genStop={new Image("Imagenes/Animaciones/Sprites/general8.png")};
        generalS=new Animation(genStop,200);
        general=generalD;
        Image[] riderIzq={new Image("Imagenes/Animaciones/Sprites/riderwalk4.png"),new Image("Imagenes/Animaciones/Sprites/riderwalk5.png"),new Image("Imagenes/Animaciones/Sprites/riderwalk6.png")};
        riderI=new Animation(riderIzq,200);
        Image[] riderStop={new Image("Imagenes/Animaciones/Sprites/riderwalk5.png")};
        riderS=new Animation(riderStop,200);
        rider=riderI;
        fondo= new Image("Imagenes/Escenas/SalaInicial/SalaCardinal.png");
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
        avatarR =  new Image("Imagenes/Personajes/Rider.png");
        avatarG =  new Image("Imagenes/Personajes/General.png");
        avatarDialogo = avatarH;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        texto= new TrueTypeFont(letraMenu, true);
        battle = new Music("Musica/BSO/Archi.wav");
        /**/
        
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
        
            //EDIT:Rener Mordeim
            
            if(estado>=0 && estado<=23){
                fondo.draw(esquinaXMapa,esquinaYMapa);
                archi.draw(posicion.x+224, posicion.y-144);
                general.draw(posicion.x+224, posicion.y-176);
                
                if(estado>=0 && estado<16){
                    hestia.draw(posicionL.x+224, posicionL.y-112);
                }
                
                if(estado>=1 && estado<=23){
                    rider.draw(posicionE.x+576, posicionE.y-151);
                }
                
                if(estado>=2 && estado!=15){
                renderDialogo();
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
                posicionL.x+=0.1f*i;
                posicion.x+=0.1f*i;
                if(posicionL.x>=224){
                    estado++;
                }
                break;
            case 1:
                hestia=hestiaS;
                archi=archiS;
                general=generalS;
                posicionE.x-=0.1f*i;
                if(posicionE.x<=(-96)){
                    estado++;
                }
                break;
            case 2:
                rider = riderS;
                avatarDialogo=this.avatarR;
                //////="////////////////////////////////////////////////////////";
                linea1="Archi, General, Sacerdotisa.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 3:
                avatarDialogo=this.avatarA;
                //////="////////////////////////////////////////////////////////";
                linea1="Jinete Especral, ¿qué haces aquí?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 4:
                avatarDialogo=this.avatarR;
                linea1="Belerofonte ha regresado pero fue";
                linea2="derrotado por el Escuadrón F.";
                linea3="";
                linea4="";
                break;
            case 5:
                avatarDialogo=this.avatarG;
                //////="////////////////////////////////////////////////////////";
                linea1="¿El líder del Escuadrón Z derrotado por";
                linea2="los 3 miembros más inutiles de Cardinal?";
                linea3="Eso es nuevo.";
                linea4="";
                break;
            case 6:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="(Me alegro de que Belerofonte no los haya derrotado.)";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 7:
                avatarDialogo=this.avatarA;
                //////="////////////////////////////////////////////////////////";
                linea1="Esos idiotas habrán llegado al 3º sello.";
                linea2="Pero, el 4º sello se halla aquí, en Cardinal.";
                linea3="Por ello, cuando vuelvan, serán apresados.";
                linea4="";
                break;
            case 8:
                avatarDialogo=this.avatarR;
                //////="////////////////////////////////////////////////////////";
                linea1="¿No quiere que vaya a por ellos personalmente?";
                linea2="Mi wyvern se encargará de ellos. JAJAJAJA.";
                linea3="";
                linea4="";
                break;
            case 9:
                avatarDialogo=this.avatarG;
                //////="////////////////////////////////////////////////////////";
                linea1="Sugiero lo que ha dicho ústed Archi.";
                linea2="Deje que mis soldados se encargen de atraparlos";
                linea3="nada más pisen la ciudad.";
                linea4="";
                break;
            case 10:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="(Ojalá no les pase nada.)";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 11:
                avatarDialogo=this.avatarR;
                linea1="Sacerdotisa Hestia, esta ústed muy callada.";
                linea2="¿No estará preocupada por esos idiotas?";
                linea3="";
                linea4="";
                break;
                
            case 12:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Me preocupa lo que puedan hacer al toquetear los";
                linea2="sellos de forma indebida.";
                linea3="Eso es todo.";
                linea4="";
                break;
            case 13:
                avatarDialogo=this.avatarR;
                //////="////////////////////////////////////////////////////////";
                linea1="Siento mucho mi descortesía Sacerdotisa Hestia.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 14:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="No pasa nada Jinete Espectral.";
                linea2="Seguire con mi trabajo.";
                linea3="Hasta luego señores.";
                linea4="";
                break;
            case 15:
                hestia=hestiaD;
                posicionL.x+=0.1f*i;
                if(posicionL.x>=352){
                    estado++;
                }
                break;
            case 16:
                avatarDialogo=this.avatarG;
                //////="////////////////////////////////////////////////////////";
                linea1="Deberíamos tomar su ejemplo y seguir trabajando.";
                linea2="Mandaré a mis hombres la búsqueda y captura del";
                linea3="Escuadron F.";
                linea4="";
                break;
            case 17:
                avatarDialogo=this.avatarA;
                //////="////////////////////////////////////////////////////////";
                linea1="...";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 18:
                avatarDialogo=this.avatarR;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Archi?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 19:
                avatarDialogo=this.avatarA;
                //////="////////////////////////////////////////////////////////";
                linea1="Perdón, estaba pensando en mis cosas.";
                linea2="Lo siento General por tal falta de respeto.";
                linea3="";
                linea4="";
                break;
            case 20:
                avatarDialogo=this.avatarG;
                //////="////////////////////////////////////////////////////////";
                linea1="JAJAJAJA, no se preocupe Archi, todos estamos";
                linea2="preocupados por el futuro del mundo.";
                linea3="Es natural, por ello, no se preocupe ústed.";
                linea4="JAJAJAJA.";
                break;
            case 21:
                avatarDialogo=this.avatarR;
                //////="////////////////////////////////////////////////////////";
                linea1="El General tiene razón Archi, será mejor que descanse.";
                linea2="Ya nos ocuparemos el General y yo del Escuadrón F.";
                linea3="";
                linea4="";
                break;
            case 22:
                avatarDialogo=this.avatarA;
                //////="////////////////////////////////////////////////////////";
                linea1="Muchas gracias.";
                linea2="Y buena suerte, la necesitaremos.";
                linea3="";
                linea4="";
                break;
            case 23:
                estado=0;
                sbg.enterState(VenganzaBelial.ESCENADEYOLICAPOSTMONTANA);
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