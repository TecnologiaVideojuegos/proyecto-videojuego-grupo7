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

public class EscenaTroyiaPostBoss2 extends BasicGameState{
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
    private int eleccionJugador=0;
    private String[] opciones = new String[4];
    private boolean normalEnter=true;
    private Input input;
    private int estado;
    private boolean reproducirExclamacion=false;
    /*Mapa*/
    private Vector2f posicion,posicionP,posicionA,posicionC;
    private static int esquinaXMapa=96;
    private static int esquinaYMapa=160;
    private static int esquinaXMapa2=-100;
    private static int esquinaYMapa2=0;
    /*Animaciones*/
    private SpriteSheet sheetExclamacion;
    private Animation exclamacion;
    private Animation hor,kib,mor;
    private Animation horI,kibI,morI;
    private Animation horS,kibS,morS;
    private Animation nar,narI,narE;
    private Animation archi,archiD,archiS;
    private Animation cab,cabS,cabD;
    private Image abu1,abu2,nino1,nino2,joven1,joven2,adulto1,adulto2;
    private Image fondo,fondoFanatico;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo, avatarH,avatarM, avatarK;
    private Image avatarN,avatarA,avatarCaballero,avatarPueblo;
    /*Sonido*/
    private Sound sonidoSelect;
    int time;//EDIT
    private TrueTypeFont texto;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 15); 
    
    public EscenaTroyiaPostBoss2(int id) {
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
        Image[] narIzq={new Image("Imagenes/Animaciones/Sprites/nar8.png")};
        narI=new Animation(narIzq,200);
        Image[] narStop={new Image("Imagenes/Animaciones/Sprites/nar2.png")};
        narE=new Animation(narStop,200);
        nar=narI;
        Image[] archiDer={new Image("Imagenes/Animaciones/Sprites/archi7.png"),new Image("Imagenes/Animaciones/Sprites/archi8.png"),new Image("Imagenes/Animaciones/Sprites/archi9.png")};
        archiD=new Animation(archiDer,200);
        Image[] archiStop={new Image("Imagenes/Animaciones/Sprites/archi8.png")};
        archiS=new Animation(archiStop,200);
        archi=archiS;
        Image[] cabDer={new Image("Imagenes/Animaciones/Sprites/pegaso7.png"),new Image("Imagenes/Animaciones/Sprites/pegaso8.png"),new Image("Imagenes/Animaciones/Sprites/pegaso9.png")};
        cabD=new Animation(cabDer,200);
        Image[] cabStop={new Image("Imagenes/Animaciones/Sprites/pegaso5.png")};
        cabS=new Animation(cabStop,200);
        cab=cabS;
        abu1 = new Image("Imagenes/Animaciones/Sprites/abuelo1.png");
        abu2 = new Image("Imagenes/Animaciones/Sprites/abuela1.png");
        nino1 = new Image("Imagenes/Animaciones/Sprites/nino1.png");
        nino2 = new Image("Imagenes/Animaciones/Sprites/nina1.png");
        joven1 = new Image("Imagenes/Animaciones/Sprites/joven1.png");
        joven2 = new Image("Imagenes/Animaciones/Sprites/joven2.png");
        adulto1 = new Image("Imagenes/Animaciones/Sprites/aldeano1.png");
        adulto2 = new Image("Imagenes/Animaciones/Sprites/aldeana1.png");
        fondo= new Image("Imagenes/Escenas/EscenaTroyia/CiudadCatacumbas.png");
        fondoFanatico = new Image("Imagenes/Escenas/SalaInicial/MapaTutorial.png");
        /**/
        this.sheetExclamacion= new SpriteSheet("Imagenes/Animaciones/puntos.png",32,33);
        this.exclamacion = new Animation(sheetExclamacion,200);
        /**/
        estado=0;
        this.input = gc.getInput();
        mensajePantalla= new TrueTypeFont(tipoLetra, true);
        posicion = new Vector2f(0,300);
        posicionP = new Vector2f(0,300);
        posicionA = new Vector2f(0,300);
        posicionC = new Vector2f(0,300);
        ventanaDialogo= new Image("Imagenes/Avatar/cajaMensaje.png");
        avatarH =  new Image("Imagenes/Personajes/HoraciaA.png");
        avatarM =  new Image("Imagenes/Personajes/MordeimA.png");
        avatarK =  new Image("Imagenes/Personajes/KibitoA.png");
        avatarN= new Image("Imagenes/Personajes/EncapuchadoA.png");
        avatarA =  new Image("Imagenes/Personajes/Archi.png");
        avatarCaballero =  new Image("Imagenes/Avatar/Caras/Caballero.png");
        avatarPueblo = new Image("Imagenes/Avatar/Caras/Aldeano.png");
        avatarDialogo = avatarH;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        texto= new TrueTypeFont(letraMenu, true);
        
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
        
            //EDIT:Rener Mordeim
            if(reproducirExclamacion){
                this.exclamacion.draw(posicion.x-64, posicion.y+176);
            }
            if(estado>=0 && estado<=9){
                fondo.draw(esquinaXMapa,esquinaYMapa);
                hor.draw(posicion.x+1024, posicion.y+240);
                mor.draw(posicion.x+1024, posicion.y+272);
                kib.draw(posicion.x+1024, posicion.y+208);
                nar.draw(posicionP.x+682, posicionP.y+240);
                abu1.draw(posicionP.x+650, posicionP.y+208);
                abu2.draw(posicionP.x+650, posicionP.y+272);
                nino1.draw(posicionP.x+618, posicionP.y+240);
                nino2.draw(posicionP.x+618, posicionP.y+208);
                adulto1.draw(posicionP.x+618, posicionP.y+272);
                adulto2.draw(posicionP.x+618, posicionP.y+304);
                joven1.draw(posicionP.x+618, posicionP.y+176);
                joven2.draw(posicionP.x+650, posicionP.y+176);
                
                if(estado>=1 && estado!=9){
                renderDialogo();
                }
                
            }
            if(estado>=10){
                    fondoFanatico.draw(esquinaXMapa2,esquinaYMapa2);
                    archi.draw(posicionA.x, posicionA.y-80);
                    
                    if(estado<14){
                    cab.draw(posicionC.x+64, posicionC.y-110);
                    }
                    
                    if(estado!=10 && estado!=13 && estado!=15){
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
            }//
        
        switch (estado)
        {
            case 0:
                VenganzaBelial.controlMusica.cambiarMusica("Musica/BSO/Catacumbas.wav");
                posicion.x-=0.1f*i;
                if(posicion.x<=(-160)){
                    estado++;
                }
                break;
            case 1:
                hor=horS;
                kib=kibS;
                mor=morS;
                avatarDialogo=this.avatarN;
                //////="////////////////////////////////////////////////////////";
                linea1="¡¡¡¡VIVA CARDINAL!!!!";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 2:
                avatarDialogo=this.avatarPueblo;
                //////="////////////////////////////////////////////////////////";
                linea1="¡¡¡¡VIVAAAAAAAA!!!!";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 3:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Y esto?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 4:
                avatarDialogo=this.avatarPueblo;
                linea1="Gracias por derrotar a esos malditos fanáticos.";
                linea2="Sin su lider salierón huyendo con el rabo entre";
                linea3="las piernas y ya no hay más zombies.";
                linea4="MUCHAS GRACIAS.";
                break;
            case 5:
                avatarDialogo=this.avatarM;
                linea1="Queremos una recompensa.";
                linea2="Y esta vez será oro.";
                linea3="";
                linea4="";
                break;
            case 6:
                nar=narE;
                avatarDialogo=this.avatarN;
                linea1="Y asi nuestros aclamados aventureros liberaron Troyia";
                linea2="de los fanáticos y de los zombies.";
                linea3="Pero su viaje continua. ¿Verdad?";
                linea4="";
                break;
            case 7:
                nar=narI;
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Asi es, ¿cúal es nuestro siguiente destino Horacia?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 8:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="El último sello según el mapa esta en Olimpio.";
                linea2="Vamos equipo.";
                linea3="";
                linea4="";
                break;
            case 9:
                posicion.x-=1f*i;
                posicionP.x-=1f*i;
                esquinaXMapa-=1f*i;
                if(esquinaXMapa<=(-3000)){
                    estado++;
                }
                break;
            case 10:
                posicionA.x+=1f*i;
                posicionC.x+=1f*i;
                esquinaXMapa2+=1f*i;
                if(esquinaXMapa2>=500){
                    estado++;
                }
                break;
            case 11:
                avatarDialogo=this.avatarA;
                linea1="Capitán, quiero que reuna a sus hombres y parta";
                linea2="para encontrar a esos idiotas del Escuadron F.";
                linea3="Confio en ústed.";
                linea4="";
                break;
            case 12:
                avatarDialogo=this.avatarCaballero;
                //////="////////////////////////////////////////////////////////";
                linea1="No le decepcionaré Archi.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 13:
                cab=cabD;
                posicionC.x+=0.2f*i;
                if(posicionC.x>=1350){
                    estado++;
                }
                break;
            case 14:
                avatarDialogo=this.avatarA;
                //////="////////////////////////////////////////////////////////";
                linea1="Espero que detengan a esos idiotas, cuando los traigan";
                linea2="de vuelta se van a enteran de quien soy yo.";
                linea3="";
                linea4="";
                break;
            case 15:
                estado=0;
                sbg.enterState(VenganzaBelial.ESCENAPUEBLOMONTANA);
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