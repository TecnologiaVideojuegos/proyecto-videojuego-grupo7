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


public class EscenaFinalBueno extends BasicGameState{
    private int idEstado;
    private static final int POSICIONAVATARX = 30;
    private static final int POSICIONAVATARY = 620;
    private static final int TAMANYOAVATARX = 115;
    private static final int TAMANYOAVATARY = 115;
    //avatarDialogo.draw(30, 610, 115, 125);
    private static final int TILESIZE = 32;
    private Color negro = new Color (0,0,0);
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
    private boolean reproducirAtaque=false;
    /*Mapa*/
    private Vector2f posicion,posicionHestia;
    private Vector2f posicionPortal,posicionArchi,posicionBelial;
    private static final int esquinaXMapa2=320;
    private static final int esquinaYMapa2=144;
    /*Animaciones*/
    private Image ataque;
    
    private Animation hor,kib,mor;
    private Animation horD,kibD,morD;
    private Animation horS,kibS,morS;
    private Animation archi,archiU,archiS;
    private Image hestia,hestiaKO;
    private Animation general,generalU,generalS;
    private Animation portal,portal1;
    private Image belial;
    private Image fondo1,fondoBelial;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo,avatarH,avatarM,avatarK,avatarArchi;
    private Image avatarGeneral,avatarHestiaDem,avatarBelial,avatarNarrador;
    /*Sonido*/
    private Sound sonidoSelect;
    private Sound sonidoExplosion;
    int time=0;//EDIT
    private TrueTypeFont texto,texto1;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 45); 
    
    public EscenaFinalBueno(int id) {
        this.idEstado=id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Image[] horDown={new Image("Imagenes/HeroeMundo/her01.png")};
        horD=new Animation(horDown,200);
        Image[] morDown={new Image("Imagenes/Animaciones/Sprites/mor2.png")};
        morD=new Animation(morDown,200);
        Image[] kibDown={new Image("Imagenes/Animaciones/Sprites/kib2.png")};
        kibD=new Animation(kibDown,200);
        Image[] horStopI={new Image("Imagenes/HeroeMundo/her21.png")};
        horS=new Animation(horStopI,200);
        Image[] kibStopI={new Image("Imagenes/Animaciones/Sprites/kib11.png")};
        kibS=new Animation(kibStopI,200);
        Image[] morStopI={new Image("Imagenes/Animaciones/Sprites/mor11.png")};
        morS=new Animation(morStopI,200);
        hor=horS;
        kib=kibS;
        mor=morS;
        /**/
        Image[] archiUp={new Image("Imagenes/Animaciones/Sprites/archi10.png"),new Image("Imagenes/Animaciones/Sprites/archi11.png"),new Image("Imagenes/Animaciones/Sprites/archi12.png")};
        archiU=new Animation(archiUp,200);
        Image[] archiStop={new Image("Imagenes/Animaciones/Sprites/archi11.png")};
        archiS=new Animation(archiStop,200);
        archi=archiS;
        Image[] generalUp={new Image("Imagenes/Animaciones/Sprites/general10.png"),new Image("Imagenes/Animaciones/Sprites/general11.png"),new Image("Imagenes/Animaciones/Sprites/general12.png")};
        generalU=new Animation(generalUp,200);
        Image[] generalStop={new Image("Imagenes/Animaciones/Sprites/general11.png")};
        generalS=new Animation(generalStop,200);
        general=generalS;
        hestia=new Image("Imagenes/Animaciones/Sprites/hestia2.png");
        hestiaKO=new Image("Imagenes/Animaciones/Sprites/hestia2.png");
        hestiaKO.rotate(180);
        /**/
        fondo1= new Image("Imagenes/Escenas/FinalBueno.png");
        fondoBelial= new Image("Imagenes/Escenas/SalaInicial/SalaCardinalB.png");
        /**/
        belial=new Image("Imagenes/Animaciones/Sprites/belial2.png");
        /**/
        Image[] portalB={new Image("Imagenes/Animaciones/portal1.png"),new Image("Imagenes/Animaciones/portal2.png"),new Image("Imagenes/Animaciones/portal3.png")};
        portal1= new Animation(portalB,200);
        portal=portal1;
        /**/
        ataque = new Image("Imagenes/Animaciones/Combate/Scratch.png");
        /**/
        estado=0;
        this.input = gc.getInput();
        mensajePantalla= new TrueTypeFont(tipoLetra, true);
        texto1= new TrueTypeFont(letraMenu, true);
        
        /*Posiciones*/
        posicion = new Vector2f(0,300);
        posicionHestia= new Vector2f(0,300);
        posicionPortal= new Vector2f(0,300);
        posicionBelial = new Vector2f(0,300);
        posicionArchi= new Vector2f(0,300);
        /**/
        ventanaDialogo= new Image("Imagenes/Avatar/cajaMensaje.png");
        avatarH =  new Image("Imagenes/Personajes/HoraciaA.png");
        avatarM =  new Image("Imagenes/Personajes/MordeimA.png");
        avatarK =  new Image("Imagenes/Personajes/KibitoA.png");
        avatarArchi = new Image("Imagenes/Personajes/Archi.png");
        avatarGeneral = new Image("Imagenes/Personajes/General.png");
        avatarHestiaDem = new Image("Imagenes/Personajes/HestiaDem.png");
        avatarBelial = new Image("Imagenes/Personajes/Belial.png");
        avatarNarrador =  new Image("Imagenes/Personajes/EncapuchadoA.png");
        avatarDialogo = avatarH;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        sonidoExplosion = new Sound("Musica/Efectos/Portal.wav");
        
        texto= new TrueTypeFont(letraMenu, true);
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
        
        if(estado>=0 && estado<48){
            fondoBelial.draw(esquinaXMapa2, esquinaYMapa2);
            hor.draw(posicion.x+624, posicion.y);
            kib.draw(posicion.x+656, posicion.y);
            mor.draw(posicion.x+592, posicion.y);
            archi.draw(posicionArchi.x+624, posicionArchi.y+96);
            general.draw(posicionArchi.x+592, posicionArchi.y+96);
            hestia.draw(posicionHestia.x+720, posicionHestia.y-96);
        }
        
        if(estado>=1 && estado!=5 && estado!=7 && estado!=15 && estado!=21 && estado!=33 && estado<48){
                renderDialogo();
        }
        if(estado<21){
            portal.draw(posicionPortal.x+608, posicionPortal.y-256);
            belial.draw(posicionBelial.x+624, posicionBelial.y-160);
        }
        if(estado==21){//Pantalla en blanco
            Rectangle rectan=new Rectangle(0,0,VenganzaBelial.WIDTH,VenganzaBelial.HEIGHT);
            grphcs.draw(rectan);
            grphcs.fill(rectan);
        }
        if(estado>=48){
            fondo1.draw(0, 0, VenganzaBelial.WIDTH, VenganzaBelial.HEIGHT);
            renderDialogoCreditos();
        }
        if(estado==5){
            ataque.draw(posicionHestia.x+644, posicionHestia.y-160);
        }
        
//        texto.drawString(1000, 0, "" + estado);
        
    }
    @Override
    //Muestra la actualización
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if (input.isKeyPressed(Input.KEY_ENTER)){
            sonidoSelect.play(1, 0.2f);
            estado++;
            time=0;
        }
        switch (estado)
        {
            case 0:
                time+=i;
                //ost.loop(1, 0.05f);
                if(time/1000>2)//3 segundos de ejecución
                {
                    estado++;
                    time=0;
                }
                break;
            case 1:
                avatarDialogo=this.avatarBelial;
                //////="////////////////////////////////////////////////////////";
                linea1="¡¡¡MALDITOS MORTALES!!!";
                linea2="¿¿¿CÓMO UNOS IDIOTAS HAN SIDO CAPACES";
                linea3="DE DERROTARNOS, HESTIA???";
                linea4="";
                break;
            case 2:
                avatarDialogo=this.avatarHestiaDem;
                //////="////////////////////////////////////////////////////////";
                linea1="Nunca pense que serían tan poderosos tras su viaje.";
                linea2="Creía que ellos serían los candidatos perfectos para ser";
                linea3="de los nuestros, pero me equivocaba.";
                linea4="Fui una idiota.";
                break;
            case 3:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Sacerdotisa Hestia...";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 4:
                avatarDialogo=this.avatarHestiaDem;
                //////="////////////////////////////////////////////////////////";
                linea1="Lo siento chicos, pero no quiero que sintaís pena de";
                linea2="mi. Me he portado mal con vosotros.";
                linea3="Pero disfrute mucho estando con todos vosotros y...";
                linea4="";
                break;
            case 5:
                this.reproducirAtaque=true;
                time+=i;
                if(time/1000>0.5f)//
                {
                    time=0;
                    estado++;
                }
                break;
            case 6:
                reproducirAtaque=false;
                avatarDialogo=this.avatarHestiaDem;
                //////="////////////////////////////////////////////////////////";
                linea1="¡¡¡!!!";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 7:
                time+=i;
                hestia=hestiaKO;
                if(time/1000>0.8f)//
                {
                    time=0;
                    estado++;
                }
                break;
            case 8:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Bueno, pues se ha cargado a su hija.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 9:
                avatarDialogo=this.avatarArchi;
                //////="////////////////////////////////////////////////////////";
                linea1="¡¿Cómo es posible que pueda hacer algo así?!";
                linea2="¡¡¡BELIAL!!! ¿SABES LO QUE";
                linea3="HAS HECHO?";
                linea4="";
                break;
            case 10:
                avatarDialogo=this.avatarBelial;
                //////="////////////////////////////////////////////////////////";
                linea1="¡¡¡CLARO QUE LO SE ESTUPIDO MORTAL!!!";
                linea2="HE ELIMINADO UN LASTRE.";
                linea3="";
                linea4="";
                break;
            case 11:
                avatarDialogo=this.avatarGeneral;
                linea1="¡¿UN LASTRE!?";
                linea2="¡HESTIA ERA TU HIJA!";
                linea3="NO ES POR DEFENDERLA PERO,";
                linea4="¿SABES QUE LO HIZO TODO POR TÍ?";
                break;
                
            case 12:
                avatarDialogo=this.avatarBelial;
                //////="////////////////////////////////////////////////////////";
                linea1="¿EL SER DÉBIL CON MIS ENEMIGOS ES";
                linea2="POR MI? ¿EL TENER SENTIMIENTOS ES";
                linea3="POR MI?";
                linea4="FUE UNA INUTIL HASTA EL FINAL.";
                break;
            case 13:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Y yo me quejaba de mis padres pero lo mio es";
                linea2="una tontería comparado con lo tuyo.";
                linea3="Me alegro de que mis padres no fueran como tú con sus";
                linea4="propios hijos. Gracias papa y mama por ser más buenos.";
                break;
            case 14:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="ERES UN MONSTRUO BELIAL. Snif.";
                linea2="OJALÁ DESAPAREZCAS. Snif.";
                linea3="";
                linea4="";
                break;
            case 15:
                time+=i;
                if(!sonidoExplosion.playing())
                {
                    sonidoExplosion.play();
                }
                if(time/1000>0.8f)//
                {
                    time=0;
                    estado++;
                }
                break;
            case 16:
                avatarDialogo=this.avatarArchi;
                //////="////////////////////////////////////////////////////////";
                linea1="El portal.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 17:
                time+=i;
                if(time/1000<0.2){
                    posicionBelial.y-=0.1f*i;
                    if(posicionBelial.y>=(-208)){
                        belial=belial;
                    }
                }
                avatarDialogo=this.avatarBelial;
                //////="////////////////////////////////////////////////////////";
                linea1="NOOOOOOOOOO, NO ME ENVIARÁS";
                linea2="A ESA MALDITA PRISIÓN DE NUEVO.";
                linea3="";
                linea4="";
                break;
            case 18:
                avatarDialogo=this.avatarGeneral;
                //////="////////////////////////////////////////////////////////";
                linea1="El portal lo está absorbiendo.";
                linea2="Es increible.";
                linea3="";
                linea4="";
                break;
            case 19:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="NOOOOOOOOOO, quiero derrotarlo yo, no un";
                linea2="estúpido portal de los c*jones.";
                linea3="";
                linea4="";
                break;
            case 20:
                avatarDialogo=this.avatarBelial;
                //////="////////////////////////////////////////////////////////";
                linea1="¡¡¡¡¡¡NOOOOOOOOOOOOO!!!!!!";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 21:
                time+=i;
                if(time/1000>3)//3 segundos de ejecución
                {
                    estado++;
                    time=0;
                    this.posicion.x=0;
                    this.posicion.y=300;
                }
                break;
            case 22:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="...";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 23:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="...";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 24:
                avatarDialogo=this.avatarGeneral;
                //////="////////////////////////////////////////////////////////";
                linea1="...";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 25:
                avatarDialogo=this.avatarArchi;
                //////="////////////////////////////////////////////////////////";
                linea1="...";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 26:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Ya no está ni Belial, ni el portal.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 27:
                avatarDialogo=this.avatarGeneral;
                //////="////////////////////////////////////////////////////////";
                linea1="No puedo creerlo, lo han logrado.";
                linea2="Han vencido a Belial.";
                linea3="";
                linea4="";
                break;
            case 28:
                avatarDialogo=this.avatarArchi;
                //////="////////////////////////////////////////////////////////";
                linea1="¿En serio hemos ganado?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 29:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="SIIIIIIIII, VICTORIA.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 30:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="MALDITO PORTAL, QUIERO ABRIRLO";
                linea2="DE NUEVO PARA DERROTAR A BELIAL.";
                linea3="";
                linea4="";
                break;
            case 31:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Lo siento mucho Mordeim pero creo que la conexión";
                linea2="que había entre los dos mundos se ha cerrado para";
                linea3="siempre.";
                linea4="No existe ninguna forma de abrirlo de nuevo.";
                break;
            case 32:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="¡¡¡NOOOOOOOOOOOOOOOO!!!";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 33:
                hor=horD;
                mor=morD;
                kib=kibD;
                posicionArchi.y-=0.1f*i;
                if(posicionArchi.y<=(224)){
                    estado++;
                }
                break;
            case 34:
                avatarDialogo=this.avatarArchi;
                //////="////////////////////////////////////////////////////////";
                linea1="Enhorabuena Escuadrón F, habeís derrotado a Belial.";
                linea2="Tal y como os prometí os cumpliré cualquier deseo.";
                linea3="";
                linea4="";
                break;
            case 35:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Puedo pedir tener más deseos?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 36:
                avatarDialogo=this.avatarArchi;
                //////="////////////////////////////////////////////////////////";
                linea1="JAJAJAJAJA, no.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 37:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Pues vaya con el genio Archi.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 38:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Yo quiero abrir el portal otra vez.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 39:
                avatarDialogo=this.avatarArchi;
                //////="////////////////////////////////////////////////////////";
                linea1="...";
                linea2="No Mordeim.";
                linea3="";
                linea4="";
                break;
            case 40:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Dijiste que cumplirías cualquier deseo.";
                linea2="CUALQUIER, DESEO.";
                linea3="";
                linea4="";
                break;
            case 41:
                avatarDialogo=this.avatarGeneral;
                //////="////////////////////////////////////////////////////////";
                linea1="¿No te conformarías con ser el capitán del";
                linea2="ejercito reyniense?";
                linea3="";
                linea4="";
                break;
            case 42:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="No, quiero matar a Belial";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 43:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Yo con tener un lugar donde descansar estaría bien.";
                linea2="Además de un novio guapo, listo, rico, atractivo, con";
                linea3="vehículo propio, con una mansión en la playa,...";
                linea4="";
                break;
            case 44:
                avatarDialogo=this.avatarArchi;
                //////="////////////////////////////////////////////////////////";
                linea1="...";
                linea2="¿Porque estos idiotas son nuestros héroes?";
                linea3="";
                linea4="";
                break;
            case 45:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Porque los programadores lo decidierón así.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 46:
                avatarDialogo=this.avatarNarrador;
                //////="////////////////////////////////////////////////////////";
                linea1="Y de está forma, nuestros patéticos héroes derrotarón";
                linea2="al demonio Belial y a su hija Hestia y salvarón el";
                linea3="reino de sus garras.";
                linea4="";
                break;
            case 47:
                avatarDialogo=this.avatarNarrador;
                //////="////////////////////////////////////////////////////////";
                linea1="Lo peor es que aposté todo mi dinero en que no lo";
                linea2="conseguirían.";
                linea3="Estoy arruinado.";
                linea4="";
                break;
            case 48:
                linea5="GRACIAS POR JUGAR";
                break;
            case 49:
                linea5="David: Los esqueletos no fue idea mia.";
                break;
            case 50:
                linea5="Alberto: Se lo dedico a la IA.";
                break;
            case 51:
                linea5="Hisam: Dedicatoria a mis grandes amigos.";
                break;
            case 52:
                linea5="Angel: La vida hay que tomarsela con humor.";
                break;
            case 53:
                linea5="Pablo: ";
                break;
            case 54:
                estado=0;
//                VenganzaBelial.controlMusica.cambiarMusica("Musica/BSO/Music_City.wav");//EDIT
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
    }
    
    private void renderDialogoCreditos()
    {
        ///////////////////////////////////,"////////////////////////////////////////////////////////"/;
        texto1.drawString(160, 339,linea5,negro);
    }
}