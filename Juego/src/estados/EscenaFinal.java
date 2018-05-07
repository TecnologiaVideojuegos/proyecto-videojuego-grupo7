package estados;

import java.awt.Font;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
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


public class EscenaFinal extends BasicGameState{
    private int idEstado;
    private static final int POSICIONAVATARX = 30;
    private static final int POSICIONAVATARY = 620;
    private static final int TAMANYOAVATARX = 115;
    private static final int TAMANYOAVATARY = 115;
    //avatarDialogo.draw(30, 610, 115, 125);
    private static final int TILESIZE = 32;
    //Control de Menu y elecciones
    private int eleccionJugador=0;
    private String[] opciones = new String[4];
    private boolean eleccionBuena=false;
    private boolean normalEnter=true;
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
    private Vector2f posicion,posicionE,posicionH,posicionHestia;
    private Vector2f posicionPortal,posicionHeroes,posicionArchi,posicionBelial;
    private static int esquinaXMapa=320;
    private static final int esquinaYMapa=144;
    private static final int esquinaXMapa2=320;
    private static final int esquinaYMapa2=144;
    /*Animaciones*/
    private SpriteSheet sheetExclamacion;
    private Animation exclamacion;
    
    private Animation hor,kib,mor;
    private Animation horI,kibI,morI;
    private Animation horS,kibS,morS;
    private Animation archi,archiD,archiS;
    private Animation hestia,hestiaD,hestiaS,hestiaUp,hestiaE;
    private Animation general,generalD,generalS;
    private Animation portal,portal1;
    private Animation belial,belialDownS,belialD,belialDS,belialI,belialIS,belialE;
    private Image hes,hesV,hesS,hesDown;
    private Image horKO,kibKO,morKO,archiKO,generalKO;
    private Image horU,kibU,morU,archiU,generalU,hestiaU;
    private Image horD,kibD,morD,horIzquierda;
    private Image fondo1,fondoBelial;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo,avatarH,avatarM,avatarK,avatarArchi;
    private Image avatarGeneral,avatarHestia,avatarHestiaMal,avatarBelial;
    private Image avatarHestiaDem;
    /*Sonido*/
    private Sound sonidoSelect;
    private Sound sonidoExplosion;
    int time=0;//EDIT
    private TrueTypeFont texto;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 15); 
    
    public EscenaFinal(int id) {
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
        Image[] horStopI={new Image("Imagenes/HeroeMundo/her31.png")};
        horS=new Animation(horStopI,200);
        Image[] kibStopI={new Image("Imagenes/Animaciones/Sprites/kib5.png")};
        kibS=new Animation(kibStopI,200);
        Image[] morStopI={new Image("Imagenes/Animaciones/Sprites/mor5.png")};
        morS=new Animation(morStopI,200);
        hor=horI;
        kib=kibI;
        mor=morI;
        /**/
        Image[] archiDer={new Image("Imagenes/Animaciones/Sprites/archi7.png"),new Image("Imagenes/Animaciones/Sprites/archi8.png"),new Image("Imagenes/Animaciones/Sprites/archi9.png")};
        archiD=new Animation(archiDer,200);
        Image[] archiStop={new Image("Imagenes/Animaciones/Sprites/archi8.png")};
        archiS=new Animation(archiStop,200);
        archi=archiS;
        Image[] generalDer={new Image("Imagenes/Animaciones/Sprites/general7.png"),new Image("Imagenes/Animaciones/Sprites/general8.png"),new Image("Imagenes/Animaciones/Sprites/general9.png")};
        generalD=new Animation(generalDer,200);
        Image[] generalStop={new Image("Imagenes/Animaciones/Sprites/general8.png")};
        generalS=new Animation(generalStop,200);
        general=generalS;
        Image[] hestiaDer={new Image("Imagenes/Animaciones/Sprites/hes7.png"),new Image("Imagenes/Animaciones/Sprites/hes8.png"),new Image("Imagenes/Animaciones/Sprites/hes9.png")};
        hestiaD=new Animation(hestiaDer,200);
        Image[] hestiaStop={new Image("Imagenes/Animaciones/Sprites/hes8.png")};
        hestiaS=new Animation(hestiaStop,200);
        Image[] hestiaArriba={new Image("Imagenes/Animaciones/Sprites/hes11.png")};
        hestiaUp=new Animation(hestiaArriba,200);
        Image[] hestiaAbajo={new Image("Imagenes/Animaciones/Sprites/hes2.png")};
        hestiaE=new Animation(hestiaAbajo,200);
        hestia=hestiaS;
        /**/
        horKO= new Image("Imagenes/HeroeMundo/her21.png");
        horKO.rotate(90);
        horU= new Image("Imagenes/HeroeMundo/her21.png");
        horD= new Image("Imagenes/HeroeMundo/her01.png");
        horIzquierda= new Image("Imagenes/HeroeMundo/her31.png");
        kibKO= new Image("Imagenes/Animaciones/Sprites/kib11.png");
        kibKO.rotate(90);
        kibU= new Image("Imagenes/Animaciones/Sprites/kib11.png");
        kibD= new Image("Imagenes/Animaciones/Sprites/kib2.png");
        morKO= new Image("Imagenes/Animaciones/Sprites/mor11.png");
        morKO.rotate(90);
        morU= new Image("Imagenes/Animaciones/Sprites/mor11.png");
        morD= new Image("Imagenes/Animaciones/Sprites/mor2.png");
        archiKO= new Image("Imagenes/Animaciones/Sprites/archi11.png");
        archiKO.rotate(90);
        archiU = new Image("Imagenes/Animaciones/Sprites/archi11.png");
        generalKO= new Image("Imagenes/Animaciones/Sprites/general11.png");
        generalKO.rotate(90);
        generalU = new Image("Imagenes/Animaciones/Sprites/general11.png");
        hestiaU = new Image("Imagenes/Animaciones/Sprites/hes11.png");
        /**/
        fondo1= new Image("Imagenes/Escenas/SalaInicial/SalaCardinal.png");
        fondoBelial= new Image("Imagenes/Escenas/SalaInicial/SalaCardinalB.png");
        /**/
        Image[] belialStop1={new Image("Imagenes/Animaciones/Sprites/belial2.png")};
        belialDownS= new Animation(belialStop1,200);
        Image[] belialDer={new Image("Imagenes/Animaciones/Sprites/belial7.png"),new Image("Imagenes/Animaciones/Sprites/belial8.png"),new Image("Imagenes/Animaciones/Sprites/belial9.png")};
        belialD = new Animation(belialDer,200);
        Image[] belialDerS={new Image("Imagenes/Animaciones/Sprites/belial8.png")};
        belialDS = new Animation(belialDerS,200);
        Image[] belialIzq={new Image("Imagenes/Animaciones/Sprites/belial4.png"),new Image("Imagenes/Animaciones/Sprites/belial5.png"),new Image("Imagenes/Animaciones/Sprites/belial6.png")};
        belialI = new Animation(belialIzq,200);
        Image[] belialIzqS={new Image("Imagenes/Animaciones/Sprites/belial5.png")};
        belialIS = new Animation(belialIzqS,200);
        Image[] belialDownS={new Image("Imagenes/Animaciones/Sprites/belial2.png")};
        belialE= new Animation(belialDownS,200);
        belial=belialE;
        /**/
        Image[] portalB={new Image("Imagenes/Animaciones/portal1.png"),new Image("Imagenes/Animaciones/portal2.png"),new Image("Imagenes/Animaciones/portal3.png")};
        portal1= new Animation(portalB,200);
        portal=portal1;
        hesV=new Image("Imagenes/Animaciones/Sprites/hestia5.png");
        hesV.rotate(-20);
        hesS=new Image("Imagenes/Animaciones/Sprites/hestia5.png");
        hesDown=new Image("Imagenes/Animaciones/Sprites/hestia2.png");
        hes=hesV;
        /**/
        this.sheetExclamacion= new SpriteSheet("Imagenes/Animaciones/exclamacion.png",32,33);
        this.exclamacion = new Animation(sheetExclamacion,200);
        /**/
        estado=0;
        this.input = gc.getInput();
        mensajePantalla= new TrueTypeFont(tipoLetra, true);
        /*Posiciones*/
        posicion = new Vector2f(0,300);
        posicionE = new Vector2f(0,300);
        posicionH= new Vector2f(0,300);
        posicionHestia= new Vector2f(0,300);
        posicionPortal= new Vector2f(0,300);
        posicionBelial = new Vector2f(0,300);
        posicionHeroes= new Vector2f(0,300);
        posicionArchi= new Vector2f(0,300);
        /**/
        ventanaDialogo= new Image("Imagenes/Avatar/cajaMensaje.png");
        avatarH =  new Image("Imagenes/Personajes/HoraciaA.png");
        avatarM =  new Image("Imagenes/Personajes/MordeimA.png");
        avatarK =  new Image("Imagenes/Personajes/KibitoA.png");
        avatarArchi = new Image("Imagenes/Personajes/Archi.png");
        avatarGeneral = new Image("Imagenes/Personajes/General.png");
        avatarHestia = new Image("Imagenes/Personajes/HestiaA.png");
        avatarHestiaDem = new Image("Imagenes/Personajes/HestiaDem.png");
        avatarHestiaMal = new Image("Imagenes/Personajes/Hestia.png");
        avatarBelial = new Image("Imagenes/Personajes/Belial.png");
        avatarDialogo = avatarH;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        
        texto= new TrueTypeFont(letraMenu, true);
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
        
        if(estado>=0 && estado<20){
            fondo1.draw(esquinaXMapa,esquinaYMapa);
            hor.draw(posicion.x+896, posicion.y);
            kib.draw(posicion.x+896, posicion.y+32);
            mor.draw(posicion.x+896, posicion.y-32);
            archi.draw(posicionE.x+320, posicionE.y);
            general.draw(posicionE.x+320, posicionE.y+32);
            hestia.draw(posicionH.x+320, posicionH.y-32);
        }
        
        if(estado>=1 && estado!=2 && estado!=12 && estado!=21 && estado!=22 && estado!=30 && estado!=35
            && estado!=36 && estado!=59){
                renderDialogo();
        }
        
        if(estado==20){//Pantalla en blanco
            Rectangle rectan=new Rectangle(0,0,VenganzaBelial.WIDTH,VenganzaBelial.HEIGHT);
            grphcs.draw(rectan);
            grphcs.fill(rectan);
        }
        
        if(estado>=21){
            fondoBelial.draw(esquinaXMapa2, esquinaYMapa2);
            portal.draw(posicionPortal.x+608, posicionPortal.y-256);
            horKO.draw(posicionHeroes.x+896, posicionHeroes.y);
            morKO.draw(posicionHeroes.x+568, posicionHeroes.y-32);
            kibKO.draw(posicionHeroes.x+704, posicionHeroes.y+32);
            archiKO.draw(posicionArchi.x+600, posicionArchi.y+96);
            generalKO.draw(posicionArchi.x+568, posicionArchi.y+96);
        }
        if(estado>=30){
            belial.draw(posicionBelial.x+624, posicionBelial.y-240);
        }
        if(estado>=59){
            hes.draw(posicionHestia.x+1350, posicionHestia.y-128);
        }
        if(estado==77){
            renderDecisionJugador();
        }
        texto.drawString(1000, 0, "" + estado);
        texto.drawString(1000, 100, "EleccionBuena" + eleccionBuena);
        
    }
    @Override
    //Muestra la actualización
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        exclamacion.update(i);
        if(normalEnter)
        {
            if(input.isKeyPressed(Input.KEY_ENTER)){
                sonidoSelect.play(1, 0.2f);
                time=0;
                estado++;
            }//
        }/*if(normalEnter)*/
        switch (estado)
        {
            case 0:
                posicion.x-=0.1f*i;
                if(posicion.x<=(-128)){
                    estado++;
                }
                break;
            case 1:
                hor=horS;
                mor=morS;
                kib=kibS;
                avatarDialogo=this.avatarArchi;
                //////="////////////////////////////////////////////////////////";
                linea1="No puedo creer lo que ven mis ojos.";
                linea2="El Escuadrón F.";
                linea3="";
                linea4="";
                break;
            case 2:
                archi=archiD;
                hestia=hestiaD;
                general=generalD;
                posicionE.x+=0.1f*i;
                posicionH.x+=0.1f*i;
                if(posicionE.x>=196){
                    estado++;
                }
                break;
            case 3:
                archi=archiS;
                general=generalS;
                hestia=hestiaS;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Sacerdotisa Hestia.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 4:
                avatarDialogo=this.avatarHestia;
                //////="////////////////////////////////////////////////////////";
                linea1="Me alegro mucho de veros chicos.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 5:
                avatarDialogo=this.avatarGeneral;
                //////="////////////////////////////////////////////////////////";
                linea1="Chicos, rendiros y os prometemos que";
                linea2="nadie resultará herido.";
                linea3="";
                linea4="";
                break;
            case 6:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="¡¿Te crees que somos idiotas o qué?!";
                linea2="Si nos arrestaís no podremos recibir nuestra";
                linea3="tan merecida recompensa.";
                linea4="";
                break;
            case 7:
               avatarDialogo=this.avatarArchi;
                //////="////////////////////////////////////////////////////////";
                linea1="¡¿Con quién crees que estas hablando?!";
                linea2="¡¿Acaso sabeís lo que habeís hecho?!";
                linea3="";
                linea4="";
                break;
            case 8:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Si, hemos reparado los sellos de Luci que";
                linea2="estaban desperdigados por todo el reino.";
                linea3="";
                linea4="";
                break;
            case 9:
                avatarDialogo=this.avatarArchi;
                //////="////////////////////////////////////////////////////////";
                linea1="¡¿Reparar los sellos?!";
                linea2="Más bien al contrario.";
                linea3="Habeís liberado el poder de los sellos,";
                linea4="los habeís debilitado.";
                break;
            case 10:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Eso...eso es imposible, la llave servía para";
                linea2="reparar los sellos, ¿no Sacerdotisa Hestia?";
                linea3="";
                linea4="";
                break;
            case 11:
                avatarDialogo=this.avatarArchi;
                linea1="¡¿QUÉ!?";
                linea2="¡¿FUISTE TÚ HESTIA?!";
                linea3="";
                linea4="";
                break;
                
            case 12:
                hestia=hestiaD;
                posicionH.x+=0.1f*i;
                if(posicionH.x>=320){
                    estado++;
                }
                break;
            case 13:
                hestia=hestiaUp;
                avatarDialogo=this.avatarHestiaMal;
                //////="////////////////////////////////////////////////////////";
                linea1="JAJAJAJAJA";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 14:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Sacerdotisa Hestia?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 15:
                hestia=hestiaE;
                avatarDialogo=this.avatarHestiaMal;
                //////="////////////////////////////////////////////////////////";
                linea1="IDIOTAS, TODOS SOIS UNOS IDIOTAS.";
                linea2="¿DE VERDAD CREÍAIS QUE ESA LLAVE";
                linea3="REPARABA LOS SELLOS?";
                linea4="";
                break;
            case 16:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Yo en realidad si sabía su complejo funcionamiento.";
                linea2="Lo que pasa es que por no hacerme el listo del";
                linea3="grupo decidí quedarme callado respecto ese tema.";
                linea4="";
                break;
            case 17:
                avatarDialogo=this.avatarGeneral;
                //////="////////////////////////////////////////////////////////";
                linea1="Sacerdotisa Hestia, quedas arrestada por intentar";
                linea2="liberar al demonio Belial de su prisión.";
                linea3=" ";
                linea4="";
                break;
            case 18:
                avatarDialogo=this.avatarHestiaMal;
                //////="////////////////////////////////////////////////////////";
                linea1="JAJAJAJAJA, ya es muy tarde para detenerme.";
                linea2="En pocos segundos, Belial será liberado de su sello.";
                linea3="";
                linea4="";
                break;
            case 19:
                avatarDialogo=this.avatarHestiaMal;
                //////="////////////////////////////////////////////////////////";
                linea1="Belial, ven a nuestro mundo.";
                linea2="JAJAJAJAJA.";
                linea3="";
                linea4="";
                break;
            case 20://Temporizacion de pantalla en blanco
                time+=i;
                if(time/1000>3)//3 segundos de ejecución
                {
                    estado++;
                    time=0;
                    this.posicion.x=0;
                    this.posicion.y=300;
                }
            case 21:
                time+=i;
                if(time/1000>2)//3 segundos de ejecución
                {
                    estado++;
                    time=0;
                }
                break;
            case 22:
                time+=i;
                reproducirExclamacion=true;
                if(time/1000>0.4f)//
                {
                    reproducirExclamacion=false;
                    estado++;
                    time=0;
                }
                break;
            case 23:
                horKO=horU;
                time+=i;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¡AAAAAHH";
                linea2="(Creo que esto ya lo he vivido antes.)";
                linea3="";
                linea4="";
                if(time/1000>1f)
                {
                    estado++;
                    time=0;
                }
                break;
            case 24:
                kibKO=kibU;
                morKO=morU;
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Qué ha pasado?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 25:
                archiKO=archiU;
                generalKO=generalU;
                avatarDialogo=this.avatarArchi;
                //////="////////////////////////////////////////////////////////";
                linea1="Mi cabeza.";
                linea2="¿Qué ha ocurrido?";
                linea3="";
                linea4="";
                break;
            case 26:
                horKO=horD;
                morKO=morD;
                kibKO=kibD;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="General, Archi, ¿están bien?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 27:
                avatarDialogo=this.avatarGeneral;
                //////="////////////////////////////////////////////////////////";
                linea1="Yo estoy perfectamente.";
                linea2="¿Pero y vosotros?";
                linea3="";
                linea4="";
                break;
            case 28:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="¿En serio lo preguntas viejo?";
                linea2="Estamos perfectamente.";
                linea3="";
                linea4="";
                break;
            case 29:
                kibKO=kibU;
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Siento ser el que os fastidie la fiesta, pero,";
                linea2="se ha abierto un portal a otro mundo; probablemente";
                linea3="el mundo del que proviene Belial.";
                linea4="Aun estamos a tiempo de cerrarlo.";
                break;
            case 30:
                horKO=horU;
                morKO=morU;
                posicionBelial.y+=0.1f*i;
                if(posicionBelial.y>=384){
                    estado++;
                }
                break;
            case 31:
                belial=belialE;
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Para que hablaré.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 32:
                avatarDialogo=this.avatarArchi;
                //////="////////////////////////////////////////////////////////";
                linea1="No es posible.";
                linea2="¡¡BELIAL!!";
                linea3="";
                linea4="";
                break;
            case 33:
                avatarDialogo=this.avatarBelial;
                //////="////////////////////////////////////////////////////////";
                linea1="...";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 34:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Ese es Belial?";
                linea2="No me impresiona.";
                linea3="";
                linea4="";
                break;
            case 35:
                time+=i;
                belial=belialIS;
                if(time/1000>0.4){
                    estado++;
                    time=0;
                }
                break;
            case 36:
                time+=i;
                belial=belialDS;
                if(time/1000>0.4){
                    estado++;
                    time=0;
                }
                break;
            case 37:
                belial=belialE;
                avatarDialogo=this.avatarBelial;
                //////="////////////////////////////////////////////////////////";
                linea1="Asi que esto es Reynos.";
                linea2="Sinceramente me lo esperaba más... Vivo.";
                linea3="";
                linea4="";
                break;
            case 38:
                avatarDialogo=this.avatarGeneral;
                //////="////////////////////////////////////////////////////////";
                linea1="Si ese es Belial, el mundo está condenado.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 39:
                avatarDialogo=this.avatarBelial;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Condenado? Yo lo llamaría 'Liberado'.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 40:
                time+=i;
                if(time/1000<1.6){
                    belial=belialD;
                    posicionBelial.x+=0.1f*i;
                    if(posicionBelial.x>=180){
                        belial=belialDS;
                    }
                }
                avatarDialogo=this.avatarBelial;
                //////="////////////////////////////////////////////////////////";
                linea1="Vereís, antiguamente este mundo se sometería a mi";
                linea2="voluntad, después de todo soy... UN DIOS.";
                linea3="";
                linea4="";
                break;
            case 41:
                time+=i;
                if(time/1000<3.2){
                    belial=belialI;
                    posicionBelial.x-=0.1f*i;
                    if(posicionBelial.x<=(-180)){
                        belial=belialIS;
                    }
                }
                avatarDialogo=this.avatarBelial;
                //////="////////////////////////////////////////////////////////";
                linea1="Con el paso del tiempo, la gente llamaba a su DIOS.";
                linea2="Querían que les ayudará, pero yo no ayudo gratis.";
                linea3="";
                linea4="";
                break;
            case 42:
                time+=i;
                if(time/1000<1.6){
                    belial=belialD;
                    posicionBelial.x+=0.1f*i;
                    if(posicionBelial.x>=(-32)){
                        belial=belialDS;
                    }
                }
                avatarDialogo=this.avatarBelial;
                //////="////////////////////////////////////////////////////////";
                linea1="La gente estaba desesperada por mi ayuda, por eso";
                linea2="empezó lo que llamaís Guerra Demoniaca.";
                linea3="";
                linea4="";
                break;
            case 43:
                belial=belialE;
                avatarDialogo=this.avatarBelial;
                //////="////////////////////////////////////////////////////////";
                linea1="Cuando por fin iba a gobernar sobre este mundo, vuestros";
                linea2="antepasados; los fundadores de Cardinal, me sellarón en";
                linea3="esta horrible prisión donde llevo más de 3000 años.";
                linea4="Y ahora que soy libre, pienso gobernar este mundo.";
                break;
            case 44:
                avatarDialogo=this.avatarArchi;
                //////="////////////////////////////////////////////////////////";
                linea1="¿En serio crees que te dejaremos gobernar este mundo";
                linea2="solo porque a tí te de la gana demonio?";
                linea3="";
                linea4="";
                break;
            case 45:
                avatarDialogo=this.avatarBelial;
                //////="////////////////////////////////////////////////////////";
                linea1="JAJAJAJAJAJAJA.";
                linea2="Los humanos sois tan divertidos y tan débiles.";
                linea3="";
                linea4="";
                break;
            case 46:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="No...nosot...nosotros no somos débiles.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 47:
                avatarDialogo=this.avatarBelial;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Y tú quién diablos eres?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 48:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Cap...Cap...Capitana Horacia Labelle.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 49:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Yo soy Mordeim, el más fuerte y guapo del grupo.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 50:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Yo me llamo Kibito, encantado señor Belial.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 51:
                avatarDialogo=this.avatarGeneral;
                //////="////////////////////////////////////////////////////////";
                linea1="¿En serio le habéis dicho vuestros nombres al demonio";
                linea2="más peligroso que ha existido?";
                linea3="Definitivamente sois idiotas.";
                linea4="";
                break;
            case 52:
                avatarDialogo=this.avatarArchi;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Como han podido estos idiotas liberar los sellos?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 53:
                avatarDialogo=this.avatarBelial;
                //////="////////////////////////////////////////////////////////";
                linea1="Espera, ¿sois vosotros los que me habeís liberado?";
                linea2="¿Vosotros? ¿En serio?";
                linea3="";
                linea4="";
                break;
            case 54:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Técnicamente hemos debilitado tres de los cuatro sellos";
                linea2="de Luci. La Sacerdotisa Hestia fue la que debilitó el";
                linea3="último de los sellos, pero desapareció tras la explosión";
                linea4="que ha destruido la parte superior de Cardinal.";
                break;
            case 55:
                avatarDialogo=this.avatarBelial;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Hestia?";
                linea2="JAJAJAJAJAJA.";
                linea3="";
                linea4="";
                break;
            case 56:
                avatarDialogo=this.avatarArchi;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Qué te hace tanta gracia Belial?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 57:
                avatarDialogo=this.avatarBelial;
                //////="////////////////////////////////////////////////////////";
                linea1="Hestia es mi hija.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 58:
                avatarDialogo=this.avatarHestiaDem;
                //////="////////////////////////////////////////////////////////";
                linea1="Así es Padre.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 59:
                posicionHestia.x-=0.1f*i;
                if(posicionHestia.x<=(-512)){
                    estado++;
                }
                break;
            case 60:
                hes=hesS;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Sacerdotisa Hestia? ¿Eres tú?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 61:
                hes=hesDown;
                avatarDialogo=this.avatarHestiaDem;
                //////="////////////////////////////////////////////////////////";
                linea1="Así es mi querida capitana Labelle.";
                linea2="Esta es mi verdadera forma.";
                linea3="";
                linea4="";
                break;
            case 62:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="No puedo creer que trabajaramos para un demonio.";
                linea2="Tengo que anotarlo en mi diario.";
                linea3="";
                linea4="";
                break;
            case 63:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Sinceramente no me parece nada impresionante";
                linea2="que esa sea tu verdadera forma.";
                linea3="Hasta el osito de Horacia da más miedo.";
                linea4="";
                break;
            case 64:
                horKO=horIzquierda;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¡¡¡MORDEIM, ESO NO DEBÍAN DE SABERLO!!!";
                linea2="¡¡¡PERVERTIDO!!!";
                linea3="";
                linea4="";
                break;
            case 65:
                horKO=horU;
                avatarDialogo=this.avatarBelial;
                //////="////////////////////////////////////////////////////////";
                linea1="JAJAJAJAJAJA, me recuerda a mi hija antes de irse de";
                linea2="casa. Le escondí su peluche favorito para que no se";
                linea3="fuera JAJAJAJAJAJA.";
                linea4="";
                break;
            case 66:
                hes=hesS;
                avatarDialogo=this.avatarHestiaDem;
                //////="////////////////////////////////////////////////////////";
                linea1="¡¡¡PAPA, ESO NO DEBÍAN DE SABERLO!!!";
                linea2="¡¡¡IMBECIL!!!";
                linea3="";
                linea4="";
                break;
            case 67:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Te comprendo Hestia.";
                linea2="Pero, ¿por qué nos utilizaste?";
                linea3="";
                linea4="";
                break;
            case 68:
                hes=hesDown;
                avatarDialogo=this.avatarHestiaDem;
                //////="////////////////////////////////////////////////////////";
                linea1="Mi pobre capitana Labelle, os utilice básicamente";
                linea2="porque sabía que podríais completar la misión de";
                linea3="liberar a Belial.";
                linea4="(En realidad fue porque sois idiotas.)";
                break;
            case 69:
                avatarDialogo=this.avatarGeneral;
                //////="////////////////////////////////////////////////////////";
                linea1="Y pensar que nos engañaste a todos todo este tiempo.";
                linea2="Esto no te lo perdonaré Hestia.";
                linea3="";
                linea4="";
                break;
            case 70:
                avatarDialogo=this.avatarHestiaDem;
                //////="////////////////////////////////////////////////////////";
                linea1="JAJAJAJAJAJA, me da igual si me perdonas o no General.";
                linea2="Solo tengo que destruirte a tí y a Archi para que mi";
                linea3="padre y yo gobernemos este mundo.";
                linea4="";
                break;
            case 71:
                horKO=horD;
                morKO=morD;
                kibKO=kibD;
                avatarDialogo=this.avatarArchi;
                //////="////////////////////////////////////////////////////////";
                linea1="Maldito demonio, os destruiré a ti y a Belial para";
                linea2="poner fin a vuestra existencia.";
                linea3="Siento pediros esto chicos pero necesitamos vuestra";
                linea4="ayuda.";
                break;
            case 72:
                horKO=horU;
                morKO=morU;
                kibKO=kibU;
                avatarDialogo=this.avatarBelial;
                //////="////////////////////////////////////////////////////////";
                linea1="JAJAJAJAJAJA, ¿en serio quereís enfrentaros a mi y a";
                linea2="mi hija? JAJAJAJAJAJA";
                linea3="Si os unís a nosotros, os daremos lo que más deseaís.";
                linea4="";
                break;
            case 73:
                horKO=horD;
                morKO=morD;
                kibKO=kibD;
                avatarDialogo=this.avatarGeneral;
                //////="////////////////////////////////////////////////////////";
                linea1="Chicos, no caigais en su trampa, os quiere utilizar.";
                linea2="Sabeís que lo correcto es detener a Belial de";
                linea3="una vez y para siempre.";
                linea4="Por eso os pedimos que nos ayudeis.";
                break;
            case 74:
                horKO=horU;
                morKO=morU;
                kibKO=kibU;
                avatarDialogo=this.avatarHestiaDem;
                //////="////////////////////////////////////////////////////////";
                linea1="¿En serio os uniríais a los que querían vuestras";
                linea2="cabezas separadas de vuestros cuerpos?";
                linea3="Uniros a nosotros, sabeis que yo siempre cumplo mis";
                linea4="promesas y os prometo lo que pidaís.";
                break;
            case 75:
                horKO=horD;
                morKO=morD;
                kibKO=kibD;
                avatarDialogo=this.avatarArchi;
                //////="////////////////////////////////////////////////////////";
                linea1="Chicos, no os dejeís engañar por ellos.";
                linea2="Ayudadnos y os prometo cumplir cualquier deseo por";
                linea3="imposible que parezca.";
                linea4="Por favor.";
                break;
            case 76:
                horKO=horU;
                morKO=morU;
                kibKO=kibU;
                avatarDialogo=this.avatarBelial;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Os fiáis de él después de lo que os ha hecho pasar?";
                linea2="Se que sois más listos que esto.";
                linea3="Uniros a nosotros, al bando ganador.";
                linea4="¿Qué contestaís?";
                
                opciones[0]="Apoyar a Archi";
                opciones[1]="Apoyar a Belial.";
                eleccionJugador=0;
                break;
            case 77:
                normalEnter=false;
                tomaDecision();
                break;
            case 78:
                normalEnter=true;
                if(eleccionBuena){
                    avatarDialogo=this.avatarH;
                    //////="////////////////////////////////////////////////////////";
                    linea1="Os ayudaremos Archi a detener a estos monstruos.";
                    linea2="No quiero un mundo lleno de tinieblas gobernado";
                    linea3="por estos monstruos.";
                    linea4="";
                }
                else{
                    horKO=horD;
                    morKO=morD;
                    kibKO=kibD;
                    avatarDialogo=this.avatarM;
                    //////="////////////////////////////////////////////////////////";
                    linea1="Yo estoy del lado de un tío casí tan poderoso como yo.";
                    linea2="Al menos él no quiere mi cabeza y como mando yo, mis";
                    linea3="esclavos me ayudarán.";
                    linea4="";
                }
                break;
            case 79:
                if(eleccionBuena){
                    avatarDialogo=this.avatarArchi;
                    //////="////////////////////////////////////////////////////////";
                    linea1="Sabía que podía contar con vosotros chicos.";
                    linea2="";
                    linea3="";
                    linea4="";
                }
                else{
                    avatarDialogo=this.avatarBelial;
                    //////="////////////////////////////////////////////////////////";
                    linea1="Habeis elegido la elección correcta mortales.";
                    linea2="";
                    linea3="";
                    linea4="";
                }
                break;
            case 80:
                if(eleccionBuena){
                    horKO=horU;
                    morKO=morU;
                    kibKO=kibU;
                    avatarDialogo=this.avatarHestiaDem;
                    //////="////////////////////////////////////////////////////////";
                    linea1="¿Osaís desafiarnos a mi y a Belial?";
                    linea2="¡¡¡IDIOTAS, OS DESTRUIRÉ PERSONALMENTE!!!";
                    linea3="";
                    linea4="";
                }
                else{
                    avatarDialogo=this.avatarGeneral;
                    //////="////////////////////////////////////////////////////////";
                    linea1="No puedo creerlo, sois unos viles traidores.";
                    linea2="¡¡¡PIENSO ACABAR CON VOSOTROS AUNQUE";
                    linea3="SEA LO ÚLTIMO QUE HAGA!!!";
                    linea4="";
                }
                break;
            case 81:
                if(eleccionBuena){
                    avatarDialogo=this.avatarBelial;
                    //////="////////////////////////////////////////////////////////";
                    linea1="¡¡¡OS ANIQUILAREMOS!!!";
                    linea2="¡¡¡NO QUEDARÁ NADA DE VOSOTROS!!!";
                    linea3="";
                    linea4="";
                }
                else{
                    avatarDialogo=this.avatarArchi;
                    //////="////////////////////////////////////////////////////////";
                    linea1="¡¿CÓMO OSAÍS ENFRENTAROS A NOSOTROS?!";
                    linea2="¡¡¡NO SALDREIS IMPUNE DE ESTA";
                    linea3="TRAICIÓN MALDITOS IDIOTAS!!!";
                    linea4="";
                }
                break;
            case 82:
                if(eleccionBuena){
                    estado=0;
                    //ENTRAMOS EN COMBATE CONTRA EL BOSS
                    //APLICAMOS IDENTIFICADOR DE MAPA ESPECIAL Y ENTRAMOS EN COMBATE
//                    VenganzaBelial.MapaActual=17;//BOSSFINAL BELIAL Y HESTIA;
                    VenganzaBelial.atributoGestion.setMapaActual(17);
                    sbg.enterState(VenganzaBelial.ESTADOCOMBATE);
                }
                else{
                    estado=0;
                    //ENTRAMOS EN COMBATE CONTRA EL BOSS
                    //APLICAMOS IDENTIFICADOR DE MAPA ESPECIAL Y ENTRAMOS EN COMBATE
//                    VenganzaBelial.MapaActual=18;//BOSSFINAL ARCHI Y GENERAL;
                    VenganzaBelial.atributoGestion.setMapaActual(18);
                    sbg.enterState(VenganzaBelial.ESTADOCOMBATE);
                }
                break;

        }
    }
    
    private void tomaDecision()
    {
        
        if (input.isKeyPressed(Input.KEY_DOWN)) 
        {
            if (eleccionJugador == (2 - 1)) 
            {
                eleccionJugador = 0;
            } 
            else 
            {
                eleccionJugador++;
            }
        }
        if (input.isKeyPressed(Input.KEY_UP)) 
        {
            if (eleccionJugador == 0) {
                eleccionJugador = 2 - 1;
            } else {
                eleccionJugador--;
            }
        }
        if(input.isKeyPressed(Input.KEY_ENTER))
        {
            switch(eleccionJugador){
                case 0:
                    eleccionBuena=true;
                    setEleccionBuena(true);
                    break;
                case 1:
                    eleccionBuena=false;
                    setEleccionBuena(false);
                    break;
            }
            estado++;
        }
    }
    
    private void renderDecisionJugador()
    {
        Font letra = new Font("Verdana", Font.ROMAN_BASELINE, 25);
        TrueTypeFont opcionesJugadorTTF = new TrueTypeFont(letra, true);
        for (int i = 0; i < 2; i++) 
        {
            if (eleccionJugador == i) {
                opcionesJugadorTTF.drawString(10, i * 20 + 400, opciones[i]);
            } else {
                opcionesJugadorTTF.drawString(10, i * 20 + 400, opciones[i], new Color(211,84,0));
            }
        }
    }/*private void renderOpcionesJugador()*/
    
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

    public boolean isEleccionBuena() {
        return eleccionBuena;
    }

    public void setEleccionBuena(boolean eleccionBuena) {
        this.eleccionBuena = eleccionBuena;
    }

    
}