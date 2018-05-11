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


public class EscenaFinalMalo extends BasicGameState{
    private int idEstado;
    private static final int POSICIONAVATARX = 30;
    private static final int POSICIONAVATARY = 620;
    private static final int TAMANYOAVATARX = 115;
    private static final int TAMANYOAVATARY = 115;
    //avatarDialogo.draw(30, 610, 115, 125);
    private static final int TILESIZE = 32;
    private Color negro = new Color (255,255,255);
    /*Texto*/
    private TrueTypeFont mensajePantalla;
    private Font tipoLetra  =new Font("Verdana", Font.PLAIN, 15);
    private String linea1="";
    private String linea2="";
    private String linea3="";
    private String linea4="";
    private String linea5="";
    private String linea6="";
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
    private Image archi,archiKO;
    private Animation hestia,hestiaD,hestiaS,hestiaI;
    private Image general,generalKO;
    private Animation portal,portal1;
    private Animation belial,belialD,belialS;
    private Image fondo1,fondoBelial;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo,avatarH,avatarM,avatarK,avatarArchi;
    private Image avatarGeneral,avatarHestiaDem,avatarBelial,avatarNarrador;
    /*Sonido*/
    private Sound sonidoSelect;
    private Sound sonidoExplosion;
    int time=0;//EDIT
    private TrueTypeFont texto,texto1,texto2;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 45); 
    private Font letraCreditos  = new Font("Arial Black", Font.PLAIN, 30);
    
    public EscenaFinalMalo(int id) {
        this.idEstado=id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Image[] horDown={new Image("Imagenes/HeroeMundo/her21.png")};
        horD=new Animation(horDown,200);
        Image[] morDown={new Image("Imagenes/Animaciones/Sprites/mor11.png")};
        morD=new Animation(morDown,200);
        Image[] kibDown={new Image("Imagenes/Animaciones/Sprites/kib11.png")};
        kibD=new Animation(kibDown,200);
        Image[] horStopI={new Image("Imagenes/HeroeMundo/her01.png")};
        horS=new Animation(horStopI,200);
        Image[] kibStopI={new Image("Imagenes/Animaciones/Sprites/kib2.png")};
        kibS=new Animation(kibStopI,200);
        Image[] morStopI={new Image("Imagenes/Animaciones/Sprites/mor2.png")};
        morS=new Animation(morStopI,200);
        hor=horS;
        kib=kibS;
        mor=morS;
        /**/
        archiKO=new Image("Imagenes/Animaciones/Sprites/archi2.png");
        archiKO.rotate(180);
        archi=new Image("Imagenes/Animaciones/Sprites/archi11.png");
        generalKO=new Image("Imagenes/Animaciones/Sprites/general2.png");
        generalKO.rotate(180);
        general=new Image("Imagenes/Animaciones/Sprites/general11.png");
        Image[] hestiaAbajo={new Image("Imagenes/Animaciones/Sprites/hestia1.png"),new Image("Imagenes/Animaciones/Sprites/hestia2.png"),new Image("Imagenes/Animaciones/Sprites/hestia3.png")};
        hestiaD=new Animation(hestiaAbajo,200);
        Image[] hestiaStop={new Image("Imagenes/Animaciones/Sprites/hestia2.png")};
        hestiaS=new Animation(hestiaStop,200);
        Image[] hestiaIzq={new Image("Imagenes/Animaciones/Sprites/hestia5.png")};
        hestiaI=new Animation(hestiaIzq,200);
        hestia=hestiaS;
        /**/
        fondo1= new Image("Imagenes/Escenas/FinalMalo.png");
        fondoBelial= new Image("Imagenes/Escenas/SalaInicial/SalaCardinalB.png");
        /**/
        Image[] belialAbajo={new Image("Imagenes/Animaciones/Sprites/belial1.png"),new Image("Imagenes/Animaciones/Sprites/belial2.png"),new Image("Imagenes/Animaciones/Sprites/belial3.png")};
        belialD=new Animation(belialAbajo,200);
        Image[] belialStop={new Image("Imagenes/Animaciones/Sprites/belial2.png")};
        belialS=new Animation(belialStop,200);
        belial=belialS;
        /**/
        Image[] portalB={new Image("Imagenes/Animaciones/portal1.png"),new Image("Imagenes/Animaciones/portal2.png"),new Image("Imagenes/Animaciones/portal3.png")};
        portal1= new Animation(portalB,200);
        portal=portal1;
        /**/
        estado=0;
        this.input = gc.getInput();
        mensajePantalla= new TrueTypeFont(tipoLetra, true);
        texto1= new TrueTypeFont(letraCreditos, true);
        texto2= new TrueTypeFont(letraCreditos, true);
        
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
        
        if(estado>=0 && estado<24){
            fondoBelial.draw(esquinaXMapa2, esquinaYMapa2);
            portal.draw(posicionPortal.x+608, posicionPortal.y-256);
            belial.draw(posicionBelial.x+624, posicionBelial.y-160);
            hor.draw(posicion.x+624, posicion.y);
            kib.draw(posicion.x+656, posicion.y);
            mor.draw(posicion.x+592, posicion.y);
            archi.draw(posicionArchi.x+624, posicionArchi.y+96);
            general.draw(posicionArchi.x+592, posicionArchi.y+96);
            hestia.draw(posicionHestia.x+720, posicionHestia.y-96);
        }
        
        if(estado>=1 && estado!=8 && estado!=9 && estado<24){
                renderDialogo();
        }
        
        if(estado>=24){
            fondo1.draw(0, 0, VenganzaBelial.WIDTH, VenganzaBelial.HEIGHT);
            renderDialogoCreditos();
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
                avatarDialogo=this.avatarArchi;
                //////="////////////////////////////////////////////////////////";
                linea1="¡¡¡MALDITOS TRAIDORES!!!";
                linea2="¿¿¿CÓMO UNOS IDIOTAS HAN SIDO CAPACES";
                linea3="DE DERROTARNOS???";
                linea4="";
                break;
            case 2:
                avatarDialogo=this.avatarGeneral;
                //////="////////////////////////////////////////////////////////";
                linea1="Nunca pense que seríamos derrotados por el escuadrón";
                linea2="más débil de Cardinal.";
                linea3="Qu humillación más grande.";
                linea4="";
                break;
            case 3:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Os lo mereceís por querer nuestras cabezas colgadas";
                linea2="como un trofeo.";
                linea3="Se que soy guapo pero me gusta mi cabeza en su sitio.";
                linea4="";
                break;
            case 4:
                avatarDialogo=this.avatarHestiaDem;
                //////="////////////////////////////////////////////////////////";
                linea1="Sabía que vosotros seríais unos compañeros perfectos";
                linea2="para mi padre y yo.";
                linea3="Nunca dude de vosotros chicos.";
                linea4="";
                break;
            case 5:
                avatarDialogo=this.avatarBelial;
                //////="////////////////////////////////////////////////////////";
                linea1="Ciertamente Hestia, has elegido unos nuevos";
                linea2="comandantes de nuestro ejercito perfectos.";
                linea3="Archi, General, habeís perdido.";
                linea4="";
                break;
            case 6:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Sinceramente, ser el comandante de un ejercito";
                linea2="demoniaco es mejor que ser un mago de Cardinal.";
                linea3="Creo que hemos cogido el mejor bando.";
                linea4="";
                break;
            case 7:
                avatarDialogo=this.avatarArchi;
                //////="////////////////////////////////////////////////////////";
                linea1="¡¡¡YO OS MALDIGO ESCUADRÓN F!!!";
                linea2="¡¡¡HABEIS CONDENADO AL MUNDO ENTERO!!!";
                linea3="";
                linea4="";
                break;
            case 8:
                time+=i;
                archi=archiKO;
                general=generalKO;
                if(time/1000>0.8f)//
                {
                    time=0;
                    estado++;
                }
                break;
            case 9:
                belial=belialD;
                hestia=hestiaD;
                hor=horD;
                kib=kibD;
                mor=morD;
                posicionBelial.y+=0.1f*i;
                posicionHestia.y+=0.1f*i;
                if(posicionBelial.y>=(402)){
                    estado++;
                }
                break;
            case 10:
                belial=belialS;
                hestia=hestiaI;
                avatarDialogo=this.avatarBelial;
                //////="////////////////////////////////////////////////////////";
                linea1="Buen trabajo Escuadrón F.";
                linea2="Por ciertos, ¿qué es lo de la F?";
                linea3="¿F de 'Fuerza'?";
                linea4="";
                break;
            case 11:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="...";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 12:
                avatarDialogo=this.avatarHestiaDem;
                linea1="Desde ahora ya no sereís el Escuadrón F.";
                linea2="Yo os nombro Escuadrón Belial.";
                linea3="El Escuadrón que liderará nuestro ejercito";
                linea4="de demonios a la conquista de Reynos.";
                break;
            case 13:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Chicos, hemos ascendido a Comandantes.";
                linea2="Aunque sea de un ejercito que lo destruirá todo a";
                linea3="su paso y maté inocente, mola.";
                linea4="";
                break;
            case 14:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Por cierto, dijiste que nos darías lo que más";
                linea2="deseamos.";
                linea3="Yo quiero ser el líder de tus mejores demonios.";
                linea4="";
                break;
            case 15:
                avatarDialogo=this.avatarHestiaDem;
                //////="////////////////////////////////////////////////////////";
                linea1="Ese deseo será muy fácil de cumplir Mordeim.";
                linea2="¿Y los demás, que queréis?";
                linea3="";
                linea4="";
                break;
            case 16:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Yo con tener un lugar donde descansar estaría bien.";
                linea2="Además de un novio guapo, listo, rico, atractivo, con";
                linea3="vehículo propio, con una mansión en la playa,...";
                linea4="";
                break;
            case 17:
                avatarDialogo=this.avatarHestiaDem;
                //////="////////////////////////////////////////////////////////";
                linea1="Eso también lo quiero yo Labelle.";
                linea2="Se nota que me comprendes.";
                linea3="";
                linea4="";
                break;
            case 18:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Puedo pedir tener más deseos?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 19:
                avatarDialogo=this.avatarBelial;
                //////="////////////////////////////////////////////////////////";
                linea1="JAJAJAJAJA, vuelve a decirlo y te aniquilo.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 20:
                avatarDialogo=this.avatarHestiaDem;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Cómo es posible que ellos sean tán idiotas?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 21:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Porque los programadores lo decidierón así.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 22:
                avatarDialogo=this.avatarNarrador;
                //////="////////////////////////////////////////////////////////";
                linea1="Y de está forma, nuestros patéticos héroes derrotarón";
                linea2="al líder de Cardinal Archi y al General sumiendo al";
                linea3="reino en la oscuridad.";
                linea4="";
                break;
            case 23:
                avatarDialogo=this.avatarNarrador;
                //////="////////////////////////////////////////////////////////";
                linea1="Lo peor es que aposté todo mi dinero en que no lo";
                linea2="conseguirían.";
                linea3="Estoy arruinado.";
                linea4="";
                break;
            case 24:
                linea6="GRACIAS POR JUGAR";
                break;
            case 25:
                linea5="David: Esto es una mierda.";
                break;
            case 26:
                linea5="Alberto: Murciegalo era una broma.";
                break;
            case 27:
                linea5="Hisam: Dedicartoria a los hijos de p*ta de mis amigos xD.";
                break;
            case 28:
                linea5="Angel: Me hubiera gustado tener artilugios de mayores.";
                break;
            case 29:
                linea5="Pablo: ...";
                break;
            case 30:
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
        texto2.drawString(352, 339, linea6,negro);
    }
}