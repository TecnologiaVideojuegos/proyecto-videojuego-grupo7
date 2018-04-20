/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Dolores
 */
public class EscenaBosque2 extends BasicGameState{
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
    private boolean reproducirSusto=false;
    /*Mapa*/
    private Vector2f posicion;
    private static final int esquinaXMapa=0;
    private static final int esquinaYMapa=0;
    /*Animaciones*/
    private SpriteSheet sheetExclamacion;
    private Animation exclamacion;
    private SpriteSheet sheetSusto;
    private Animation susto;
    private Animation hor,kib,mor;
    private Animation horD,kibD,morD;
    private Animation horI,kibS,morS;
    private Animation horE;
    private Image fondo;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo, avatarH,avatarM, avatarK, avatarDesconocido;
    private Image salidaEscena;
    /*Sonido*/
    private Sound sonidoSelect,rugido;
    private Music ost;
    int time;//EDIT
    private TrueTypeFont texto;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 15); 
    
    public EscenaBosque2(int id) {
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
        Image[] horIzq={new Image("Imagenes/HeroeMundo/her31.png")};
        horI=new Animation(horIzq,200);
        Image[] kibF={new Image("Imagenes/Animaciones/Sprites/kib8.png")};
        kibS=new Animation(kibF,200);
        Image[] morF={new Image("Imagenes/Animaciones/Sprites/mor8.png")};
        morS=new Animation(morF,200);
        Image[] horEnfrente={new Image("Imagenes/HeroeMundo/her11.png")};
        horE=new Animation(horEnfrente,200);
        hor=horD;
        kib=kibD;
        mor=morD;
        fondo= new Image("Imagenes/Escenas/EscenaBosque1/mapaBosque.png");
        /**/
        this.sheetExclamacion= new SpriteSheet("Imagenes/Animaciones/puntos.png",32,33);
        this.exclamacion = new Animation(sheetExclamacion,200);
        this.sheetSusto= new SpriteSheet("Imagenes/Animaciones/exclamacion.png",32,33);
        this.susto = new Animation(sheetSusto,200);
        /**/
        estado=0;
        this.input = gc.getInput();
        mensajePantalla= new TrueTypeFont(tipoLetra, true);
        posicion = new Vector2f(0,300);
        ventanaDialogo= new Image("Imagenes/Avatar/cajaMensaje.png");
        avatarH =  new Image("Imagenes/Personajes/HoraciaA.png");
        avatarM =  new Image("Imagenes/Personajes/MordeimA.png");
        avatarK =  new Image("Imagenes/Personajes/KibitoA.png");
        avatarDesconocido = new Image("Imagenes/Personajes/Arbol.png");
        avatarDialogo = avatarH;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");

        rugido=new Sound("Musica/Efectos/rugido1.wav");
        texto= new TrueTypeFont(letraMenu, true);
        /**/
        
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
        
            fondo.draw(-1200, -160);
            
            //EDIT:Rener Mordeim
            if(reproducirExclamacion)
                this.exclamacion.draw(posicion.x-64, posicion.y-64);
            if(reproducirSusto){
                this.susto.draw(posicion.x, posicion.y-32);
            }
            if(estado>=0){
                hor.draw(posicion.x, posicion.y);
                mor.draw(posicion.x-64, posicion.y+32);
                kib.draw(posicion.x-64, posicion.y-32);
                if(estado>=2 && estado<23){
                renderDialogo();
                }
                
            }
            //texto.drawString(1000, 0, "" + estado);
    }
    @Override
    //Muestra la actualización
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        exclamacion.update(i);
        if(input.isKeyPressed(Input.KEY_ENTER)){
            if(estado!=25)
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
                if(posicion.x>=300){
                    estado++;
                }
                break;
            case 1:
                time+=i;
                reproducirSusto=true;
                if(!rugido.playing())
                {
                    rugido.play();
                }
                if(time/1000>0.4f)//
                {
                    time=0;
                    estado++;
                }
                break;
            case 2:
                time+=i;
                reproducirSusto=true;
                if(time/1000>1f)//
                {
                    reproducirSusto=false;
                    time=0;
                    estado++;
                }
                hor=horI;
                kib=kibS;
                mor=morS;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Habeis oído e...ee...eso?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 3:
                reproducirSusto=false;
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Ya esta otra vez, serán más monstruos,";
                linea2="nada nuevo en este bosque.";
                linea3="";
                linea4="";
                break;
            case 4:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Pero...esto sonaba más fuerte, y... y el ambiente se";
                linea2="está volviendo más oscuro, y... y los monstruos son";
                linea3="más fuertes y...";
                linea4="";
                break;
            case 5:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Bla, bla, bla, deja de lloriquear y tira pa'lante.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 6:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Chicos, ahora en serío.";
                linea2="¿No sabeís la historia de este bosque?";
                linea3="";
                linea4="";
                break;
            case 7:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Tú te sabes la his...hist...historia Kibito?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 8:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Según la leyenda existe un tesoro oculto en este bosque";
                linea2="maldito tras la Guerra Demoniaca...";
                linea3="";
                linea4="";
                break;
            case 9:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Bla,bla,bla, solamente me interesa saber donde está el";
                linea2="tesoro y acabar con los monstruos.";
                linea3="";
                linea4="";
                break;
            case 10:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Ejem, respecto al tesoro, nadie ha sido capaz de";
                linea2="encontrarlo.";
                linea3="Lo bueno es que el Dios del Bosque es un ser";
                linea4="amable y simpático según la leyenda...";
                break;
            case 11:
                time+=i;
                if(!rugido.playing())
                {
                    rugido.play();
                }
                if(time/1000>1f)//
                {
                    time=0;
                    estado++;
                }
                avatarDialogo=this.avatarDesconocido;
                linea1="GRRRRRRRRRRRRRRRRRRRR!!!!!!";
                linea2="";
                linea3="";
                linea4="";
                break;
                
            case 12:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="IAAAAAAAAAAAAAAAAAAAHHHHHHHHHHHHH!!!!!!!!";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 13:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Vaya capitana tan cobarde...";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 14:
                time+=i;
                if(!rugido.playing())
                {
                    rugido.play();
                }
                if(time/1000>1f)//
                {
                    time=0;
                }
                avatarDialogo=this.avatarDesconocido;
                linea1="¡¡¡¡¡¡FUEEERAAAAA DE ESTE BOSQUEEEEEEEEEEEEE!!!!!!";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 15:
                
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¡¡¡¡¡QUIERO IRME A MI CASA!!!!!";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 16:
                time+=i;
                reproducirExclamacion=true;
                if(time/1000>1f)//
                {
                    reproducirExclamacion=false;
                    time=0;
                    estado++;
                }
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Porque ella es nuestra capitana?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 17:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Menuda capitana estás echa.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 18:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Sugiero que continuemos por este camino, a lo mejor";
                linea2="encontramos la forma de salir de este bosque maldito";
                linea3="del que no ha salido nadie en los últimos 50 años.";
                linea4="";
                break;
            case 19:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Pero si nadie ha salido de este bosque en los últimos";
                linea2="50 años, eso significa que el Dios del Bosque no es tan";
                linea3="amable como cuentas.";
                linea4="";
                break;
            case 20:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Solo digo lo que dicen las leyendas que oigo en los";
                linea2="bares.";
                linea3="";
                linea4="";
                break;
            case 21:
                hor=horE;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Muy bien equipo, sugiero seguir el camino que ha dicho";
                linea2="Kibito, ha lo mejor salimos de este bosque.";
                linea3="Vamos equipo.";
                linea4="";
                break;
            case 22:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Pero si estás temblando de miedo gallina.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 23:
                hor=horD;
                kib=kibD;
                mor=morD;
                posicion.x+=0.1f*i;
                if(posicion.x>=600){
                    estado++;
                }
                break;
            case 24:
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
    }
    
}