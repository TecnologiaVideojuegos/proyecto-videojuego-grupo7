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


public class EscenaCardinalOpcional extends BasicGameState{
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
    private static int esquinaYMapa=-440;
    /*Animaciones*/
    private SpriteSheet sheetExclamacion;
    private Animation exclamacion;
    private Animation hor,mor,kib;
    private Animation horD,morD,kibD;
    private Animation horS,morS,kibS;
    private Animation horA,horR,morR,kibR;
    private Image fondo;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo,avatarH,avatarM,avatarK;
    /*Sonido*/
    private Sound sonidoSelect;
    int time;//EDIT
    private TrueTypeFont texto;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 15); 
    
    public EscenaCardinalOpcional(int id) {
        this.idEstado=id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Image[] horDown={new Image("Imagenes/HeroeMundo/her20.png"),new Image("Imagenes/HeroeMundo/her21.png"),new Image("Imagenes/HeroeMundo/her22.png")};
        horD=new Animation(horDown,200);
        Image[] morDown={new Image("Imagenes/Animaciones/Sprites/mor10.png"),new Image("Imagenes/Animaciones/Sprites/mor11.png"),new Image("Imagenes/Animaciones/Sprites/mor12.png")};
        morD=new Animation(morDown,200);
        Image[] kibDown={new Image("Imagenes/Animaciones/Sprites/kib10.png"),new Image("Imagenes/Animaciones/Sprites/kib11.png"),new Image("Imagenes/Animaciones/Sprites/kib12.png")};
        kibD=new Animation(kibDown,200);
        Image[] kibF={new Image("Imagenes/Animaciones/Sprites/kib11.png")};
        kibS=new Animation(kibF,200);
        Image[] morF={new Image("Imagenes/Animaciones/Sprites/mor11.png")};
        morS=new Animation(morF,200);
        Image[] horEnfrente={new Image("Imagenes/HeroeMundo/her21.png")};
        horS=new Animation(horEnfrente,200);
        hor=horD;
        kib=kibD;
        mor=morD;
        Image[] kibStop={new Image("Imagenes/Animaciones/Sprites/kib8.png")};
        kibR=new Animation(kibStop,200);
        Image[] morStop={new Image("Imagenes/Animaciones/Sprites/mor5.png")};
        morR=new Animation(morStop,200);
        Image[] horStop={new Image("Imagenes/HeroeMundo/her01.png")};
        horA=new Animation(horStop,200);
        Image[] horRight={new Image("Imagenes/HeroeMundo/her11.png")};
        horR=new Animation(horRight,200);
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
                hor.draw(posicion.x+272, posicion.y+128);
                mor.draw(posicion.x+304, posicion.y+128);
                kib.draw(posicion.x+240, posicion.y+128);
                
                if(estado>=1){
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
                posicion.y-=0.1f*i;
                if(posicion.y<=0){
                    estado++;
                }
                break;
            case 1:
                hor=horS;
                mor=morS;
                kib=kibS;
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Esta no era la zona del tesoro?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 2:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Efectivamente, pero parece ser que sin tesoro.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 3:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Alguien ha robado el tesoro de Cardinal?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 4:
                avatarDialogo=this.avatarK;
                linea1="Hay una nota en uno de los cofres.";
                linea2="'Archi, necesito el oro para pagar a los soldados";
                linea3="para que podamos capturar al Escuadrón F.";
                linea4="Firmado: General'";
                break;
            case 5:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Maldito cabr*n, el tesoro tenía que ser mio.";
                linea2="Cuando le vea, pienso sonsacarle todo el oro que tenga.";
                linea3="";
                linea4="";
                break;
            case 6:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Chicos, ¿os daís cuenta de que hemos";
                linea2="arruinado Cardinal con nuestra misión?";
                linea3="";
                linea4="";
                break;
            case 7:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Eso me temo Horacia.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 8:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Alto hay intelectual.";
                linea2="¿Eso significa que no recibiremos recompensa?";
                linea3="";
                linea4="";
                break;
            case 9:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Es muy probable Mordeim.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 10:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Pero si os capturo, recibiré vuestra recompensa.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 11:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¿No hablarás en serio verdad, después de todas";
                linea2="las aventuras que hemos pasado todos juntos,";
                linea3="solo te interesa el oro?";
                linea4="";
                break;
            case 12:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Si.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 13:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Si sigues con nosotros puede que recibas una mayor";
                linea2="recompensa, ya que la Sacerdotisa Hestia es rica.";
                linea3="Seguro que nos lo agradece si cumplimos nuestra misión.";
                linea4="";
                break;
            case 14:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Más te vale, sino os entregaré.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 15:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¿En serio crees que a tí no te detendrán por haber";
                linea2="estado con nosotros en nuestro viaje?";
                linea3="";
                linea4="";
                break;
            case 16:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="JOD*R, ES VERDAD!!!";
                linea2="ME UTILIZARÍAN COMO AL OSITO DE";
                linea3="PELUCHE DE HORACIA EN LA ALMOHADA.";
                linea4="";
                break;
            case 17:
                mor=morR;
                hor=horR;
                kib=kibR;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="MORDEIM, DIJISTE QUE NO SACARÍAS";
                linea2="ESE TEMA DE NUEVO.";
                linea3="PERVERTIDO!!!!";
                linea4="";
                break;
            case 18:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Sigo sin creerme que necesites un";
                linea2="peluche para poder dormir.";
                linea3="¿Dónde lo guardas Horacia?";
                linea4="";
                break;
            case 19:
                hor=horA;
                avatarDialogo=this.avatarH;
                linea1="Sigamos con nuestra misión.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 20:
                avatarDialogo=this.avatarK;
                linea1="(Me ha esquivado la pregunta.)";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 21:
                estado=0;
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