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


public class EscenaPuerto1 extends BasicGameState{
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
    private SpriteSheet sheetExclamacion;
    private Animation exclamacion;
    private SpriteSheet sheetExclamacion1;
    private Animation exclamacion1;
    private SpriteSheet sheetEncapuchado;
    private Animation encapuchado;
    private Animation alcalde,alcaldeD,alcaldeI,alcaldeA,alcaldeM,alcaldeMo;
    private Animation hor,horD,horS;
    private Animation mor,morD,morS;
    private Animation kib,kibD,kibS;
    private Animation nar,narI,narE;
    private Animation ban,banD,banI;
    private Image fondo;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo, avatarH,avatarM, avatarK, avatarAlcalde;
    private Image avatarBandido,avatarE;
    private Image salidaEscena;
    /*Sonido*/
    private Sound sonidoSelect,help;
    private Music ost;
    int time;//EDIT
    private TrueTypeFont texto;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 15); 
    
    public EscenaPuerto1(int id) {
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
        Image[] horIzq={new Image("Imagenes/HeroeMundo/her11.png")};
        horS=new Animation(horIzq,200);
        Image[] kibF={new Image("Imagenes/Animaciones/Sprites/kib8.png")};
        kibS=new Animation(kibF,200);
        Image[] morF={new Image("Imagenes/Animaciones/Sprites/mor8.png")};
        morS=new Animation(morF,200);
        hor=horD;
        kib=kibD;
        mor=morD;
        Image[] banDere={new Image("Imagenes/Animaciones/Sprites/ban2.png"),new Image("Imagenes/Animaciones/Sprites/ban3.png"),new Image("Imagenes/Animaciones/Sprites/ban4.png")};
        banD=new Animation(banDere,200);
        Image[] banIzq={new Image("Imagenes/Animaciones/Sprites/ban5.png")};
        banI=new Animation(banIzq,200);
        ban=banI;
        Image[] narIzquierda={new Image("Imagenes/Animaciones/Sprites/nar5.png")};
        narI=new Animation(narIzquierda,200);
        Image[] narEnfrente={new Image("Imagenes/Animaciones/Sprites/nar2.png")};
        narE=new Animation(narEnfrente,200);
        nar=narI;
        Image[] alcaldeDere = {new Image("Imagenes/Animaciones/Sprites/alcalde8.png")};
        alcaldeD= new Animation(alcaldeDere,200);
        Image[] alcaldeIzq = {new Image("Imagenes/Animaciones/Sprites/alcalde5.png")};
        alcaldeI= new Animation(alcaldeIzq,200);
        Image[] alcaldeAbajo = {new Image("Imagenes/Animaciones/Sprites/alcalde2.png")};
        alcaldeA= new Animation(alcaldeAbajo,200);
        Image[] alcaldeMove1 = {new Image("Imagenes/Animaciones/Sprites/alcalde1.png"),new Image("Imagenes/Animaciones/Sprites/alcalde2.png"),new Image("Imagenes/Animaciones/Sprites/alcalde5.png")};
        alcaldeM= new Animation(alcaldeMove1,200);
        Image[] alcaldeMove2 = {new Image("Imagenes/Animaciones/Sprites/alcalde5.png"),new Image("Imagenes/Animaciones/Sprites/alcalde6.png"),new Image("Imagenes/Animaciones/Sprites/alcalde5.png")};
        alcaldeMo= new Animation(alcaldeMove2,200);
        alcalde=alcaldeD;
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
        avatarBandido = new Image("Imagenes/Avatar/Caras/banditFace.png");
        avatarAlcalde = new Image("Imagenes/Personajes/alcalde.png");
        avatarE = new Image ("Imagenes/Personajes/EncapuchadoA.png");
        avatarDialogo = avatarH;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        help=new Sound("Musica/Efectos/Help.wav");
        texto= new TrueTypeFont(letraMenu, true);
        /**/
        
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
        
            fondo.draw(0,0);
            
            //EDIT:Rener Mordeim
            if(estado>=0){
                hor.draw(posicion.x, posicion.y+64);
                mor.draw(posicion.x, posicion.y+32);
                kib.draw(posicion.x, posicion.y+96);
                alcalde.draw(posicionA.x+256, posicionA.y);
                ban.draw(posicionE.x+640, posicionE.y+400);
                nar.draw(posicionEn.x+608, posicionEn.y+400);
                if(estado>0 && estado!=7 && estado!=10){
                renderDialogo();
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
                posicion.x+=0.1f*i;
                if(posicion.x>=160){
                    estado++;
                }
                break;
            case 1:
                hor=horS;
                kib=kibS;
                mor=morS;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Por fin hemos llegado a Tambler.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 2:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="No puedo creer que saliéramos del Bosque de Tambler.";
                linea2="Hemos pasado por muchas malas experiencias pero";
                linea3="parece que todo va ha ir sobre ruedas...";
                linea4="";
                break;
            case 3:
                time+=i;
                if(!help.playing())
                {
                    help.play();
                }
                if(time/1000>0.4f)//
                {
                    time=0;
                    estado++;
                }
                avatarDialogo=this.avatarE;
                //////="////////////////////////////////////////////////////////";
                linea1="Ayuda.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 4:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Alguien esta en apuros.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 5:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="No me importa.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 6:
                avatarDialogo=this.avatarBandido;
                //////="////////////////////////////////////////////////////////";
                linea1="Jajajaja, tengo tu bolso.";
                linea2="Si quieres recuperarlo ve al SS.Anne y derrotanos.";
                linea3="Jajajajaja.";
                linea4="";
                break;
            case 7:
                ban=banD;
                posicionE.x+=0.4f*i;
                if(posicionE.x>=1000){
                    estado++;
                }
                break;
            case 8:
                alcalde=alcaldeA;
                avatarDialogo=this.avatarAlcalde;
                //////="////////////////////////////////////////////////////////";
                linea1="Malditos bandidos, se han vuelto con la suya otra vez.";
                linea2="¿No habrá por aqui un trío de héroes para ayudarnos";
                linea3="como los suele haber en los RPGs?";
                linea4="";
                break;
            case 9:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Nosotros os podemos ayudar.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 10:
                alcalde=alcaldeM;
                posicionA.y+=0.2f*i;
                if(posicionA.y>=368){
                        estado++;
                }
                break;
            case 11:
                alcalde=alcaldeMo;
                posicionA.x-=0.2f*i;
                    if(posicionA.x<=(-64)){
                        alcalde=alcaldeI;
                        estado++;
                    }
            case 12:
                avatarDialogo=this.avatarAlcalde;
                linea1="Muchisimas gracias por ofreceros voluntarios para";
                linea2="derrotar a la Banda del Cuervo jóvenes aventureros.";
                linea3="";
                linea4="";
                break;
            case 13:
                avatarDialogo=this.avatarM;
                linea1="En menudo marrón nos has metido de nuevo Horacia.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 14:
                avatarDialogo=this.avatarK;
                linea1="Perdone, ¿pero quienes son la Banda del Cuervo?";
                linea2="Y lo más importante, ¿quien es ústed?";
                linea3="";
                linea4="";
                break;
            case 15:
                avatarDialogo=this.avatarAlcalde;
                linea1="Cierto, que descortés por mi parte, soy el alcalde de";
                linea2="esta magnífica ciudad.";
                linea3="";
                linea4="";
                break;
            case 16:
                avatarDialogo=this.avatarH;
                linea1="Encantada, soy Horacia y estos son mis compañeros";
                linea2="Mordeim y Kibito.";
                linea3="";
                linea4="";
                break;
            case 17:
                avatarDialogo=this.avatarK;
                linea1="Respecto a los ladrones...";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 18:
                avatarDialogo=this.avatarAlcalde;
                linea1="Cierto. La Banda del Cuervo son unos ladrones de poca";
                linea2="monta que se instalaron en nuestro puerto.";
                linea3="Y lo peor de todo es que tienen en su poder el SS.Anne,";
                linea4="nuestro único barco capaz de atravesar el mar para";
                linea5="poder ir a Troyia.";
                break;
            case 19:
                avatarDialogo=this.avatarM;
                linea1="Pero nosotros no trabajamos gratis.";
                linea2="";
                linea3="";
                linea4="";
                linea5="";
                break;
            case 20:
                avatarDialogo=this.avatarAlcalde;
                linea1="Si nos ayudais recibiréis una recompensa.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 21:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Dónde hay que firmar?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 22:
                nar=narE;
                avatarDialogo=this.avatarE;
                linea1="Y con esto nuestros patéticos héroes se encontrarón";
                linea2="en una situación peliaguda para llegar a Troyia.";
                linea3="";
                linea4="";
                break;
            case 23:
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
