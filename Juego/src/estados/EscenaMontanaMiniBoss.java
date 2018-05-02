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


public class EscenaMontanaMiniBoss extends BasicGameState{
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
    private Animation horS,kibS,morS;
    private Animation horE,kibE,morE;
    private Animation pegaso,pegasoV,pegasoS;
    private Animation horI,kibI,morI;
    private Image fondo;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo, avatarH,avatarM, avatarK, avatarPegaso;
    /*Sonido*/
    private Sound sonidoSelect;
    private Music battle;
    int time;//EDIT
    private TrueTypeFont texto;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 15); 
    
    public EscenaMontanaMiniBoss(int id) {
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
        Image[] horIzq={new Image("Imagenes/HeroeMundo/her31.png")};
        horI=new Animation(horIzq,200);
        Image[] kibIzq={new Image("Imagenes/Animaciones/Sprites/kib5.png")};
        kibI=new Animation(kibIzq,200);
        Image[] morDown={new Image("Imagenes/Animaciones/Sprites/mor11.png")};
        morE=new Animation(morDown,200);
        Image[] horDown={new Image("Imagenes/HeroeMundo/her01.png")};
        horE=new Animation(horDown,200);
        Image[] kibDown={new Image("Imagenes/Animaciones/Sprites/kib2.png")};
        kibE=new Animation(kibDown,200);
        hor=horD;
        kib=kibD;
        mor=morD;
        Image[] cabDer={new Image("Imagenes/Animaciones/Sprites/pegaso7.png"),new Image("Imagenes/Animaciones/Sprites/pegaso8.png"),new Image("Imagenes/Animaciones/Sprites/pegaso9.png")};
        pegasoV=new Animation(cabDer,200);
        Image[] cabStop={new Image("Imagenes/Animaciones/Sprites/pegaso8.png")};
        pegasoS=new Animation(cabStop,200);
        pegaso=pegasoV;
        fondo= new Image("Imagenes/Escenas/EscenaBosque1/mapaBosque.png");
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
        avatarPegaso = new Image("Imagenes/Avatar/Caras/Caballero.png");
        avatarDialogo = avatarH;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        texto= new TrueTypeFont(letraMenu, true);
        battle = new Music("Musica/BSO/DragonTheme.wav");
        /**/
        
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
        
            fondo.draw(0, 0);
            
            //EDIT:Rener Mordeim
            if(reproducirExclamacion){
                this.exclamacion.draw(posicion.x-64, posicion.y+176);
            }
            
            if(estado>=0){
                hor.draw(posicion.x+320, posicion.y+240);
                mor.draw(posicion.x+288, posicion.y+272);
                kib.draw(posicion.x+288, posicion.y+208);
                if(estado>=1 && estado!=4 && estado!=16){
                renderDialogo();
                }
                if(estado>=4){
                    pegaso.draw(posicionL.x, posicionL.y+208);
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
                if(estado>=7){
                    battle.play(1, 0.2f);
                }
                if(estado==19){
                        battle.stop();
                }
                
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
                hor=horI;
                kib=kibS;
                mor=morS;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Cúanto falta para llegar al sello Kibito?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 2:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Clarooooooo, como soy el mago tengo que saberlo todo.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 3:
                avatarDialogo=this.avatarPegaso;
                linea1="¡¡¡ALTO HAY ESCUADRÓN F!!!";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 4:
                kib=kibI;
                mor=morI;
                posicionL.x+=0.2f*i;
                if(posicionL.x>=416){
                    estado++;
                }
                
                break;
            case 5:
                pegaso=pegasoS;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Belerofonte?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 6:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Qué hace aquí el capitán del Escuadrón Z?";
                linea2="¿No será que te estás escaqueando del trabajo?";
                linea3="";
                linea4="";
                break;
            case 7:
                avatarDialogo=this.avatarPegaso;
                //////="////////////////////////////////////////////////////////";
                linea1="Vigila esa lengua Mordeim.";
                linea2="Archi se ha enterado de lo que habéis hecho y ha";
                linea3="decidido mandarme a mi para que volvaís a Deyolica";
                linea4="para vuestro castigo.";
                break;
            case 8:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Lo sentimos mucho Belerofonte pero no podemos";
                linea2="regresar aún a Deyolica hasta haber acabado ciertos";
                linea3="asuntos de máxima importancia.";
                linea4="";
                break;
            case 9:
                avatarDialogo=this.avatarPegaso;
                //////="////////////////////////////////////////////////////////";
                linea1="Si os digo la verdad, es mejor que no volvaís a la";
                linea2="ciudad. Sin vosotros Archi no ha tenido que aguantar";
                linea3="vuestra conducta pero, las órdenes son las órdenes.";
                linea4="No os resistais y prometo no haceros daño.";
                break;
            case 10:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="No tenemos que luchar, cuando acabemos nuestra";
                linea2="misión...";
                linea3="";
                linea4="";
                break;
            case 11:
                avatarDialogo=this.avatarPegaso;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Misión?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 12:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Muy bien Horacia, dile también que duermes";
                linea2="con un osito de peluche rosa.";
                linea3="";
                linea4="";
                break;
            case 13:
                hor=horE;
                mor=morE;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¡¡¡MORDEIM!!!";
                linea2="¡¡¡NO DEBES ESPIAR A UNA DAMA POR LAS NOCHES!!!";
                linea3="¡¡¡PERVERTIDO!!!";
                linea4="";
                break;
            case 14:
                kib=kibE;
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Horacia, ¿en serio duermes con un osito de";
                linea2="peluche rosa por las noches?";
                linea3="";
                linea4="";
                break;
            case 15:
                avatarDialogo=this.avatarPegaso;
                //////="////////////////////////////////////////////////////////";
                linea1="¡¡¡YA BASTA!!!";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 16:
                mor=morI;
                hor=horI;
                kib=kibI;
                time+=i;
                if(time/1000>0.7f)//
                {
                    time=0;
                    estado++;
                }
                break;
            case 17:
                pegaso=pegasoV;
                avatarDialogo=this.avatarPegaso;
                //////="////////////////////////////////////////////////////////";
                linea1="¡¡¡VOY A ENVIAROS A DEYÓLICA AUNQUE SEA A RASTRAS";
                linea2="POR TODO EL REINO!!!¡¡¡PREPARAOS ESCUADRÓN F!!!";
                linea3="";
                linea4="";
                break;
            case 18:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Que mal genio tiene Belerofonte.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 19:
                estado=0;
                sbg.enterState(VenganzaBelial.ESCENAMONTANAMINIBOSS2);//EDIT:
                //Deberiamos entrar en estado Combate contra Pegasus
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