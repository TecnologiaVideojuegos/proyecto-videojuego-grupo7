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


public class EscenaPuerto2 extends BasicGameState{
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
    private Vector2f posicion,posicionE;
    private static final int esquinaXMapa=0;
    private static final int esquinaYMapa=0;
    /*Animaciones*/
    private SpriteSheet sheetExclamacion;
    private Animation exclamacion;
    private SpriteSheet sheetExclamacion1;
    private Animation exclamacion1;
    private SpriteSheet sheetEncapuchado;
    private Animation encapuchado;
    private Image alcalde;
    private Animation hor,horA;
    private Animation mor,morA;
    private Animation kib,kibA;
    private Animation nar,narI,narE;
    private Image fondo;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo, avatarH,avatarM, avatarK; 
    private Image avatarAlcalde,avatarE;
    private Image salidaEscena;
    /*Sonido*/
    private Sound sonidoSelect,rugido;
    int time;//EDIT
    private TrueTypeFont texto;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 15); 
    
    public EscenaPuerto2(int id) {
        this.idEstado=id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Image[] horAbajo={new Image("Imagenes/HeroeMundo/her01.png")};
        horA=new Animation(horAbajo,200);
        Image[] horIzq={new Image("Imagenes/HeroeMundo/her31.png")};
        hor=new Animation(horIzq,200);
        Image[] morS={new Image("Imagenes/Animaciones/Sprites/mor5.png")};
        mor=new Animation(morS,200);
        Image[] morAbajo={new Image("Imagenes/Animaciones/Sprites/mor2.png")};
        morA=new Animation(morAbajo,200);
        Image[] kibS={new Image("Imagenes/Animaciones/Sprites/kib5.png")};
        kib=new Animation(kibS,200);
        Image[] kibAbajo={new Image("Imagenes/Animaciones/Sprites/kib2.png")};
        kibA=new Animation(kibAbajo,200);
        Image[] narIzquierda={new Image("Imagenes/Animaciones/Sprites/nar4.png"),new Image("Imagenes/Animaciones/Sprites/nar5.png"),new Image("Imagenes/Animaciones/Sprites/nar6.png")};
        narI=new Animation(narIzquierda,200);
        Image[] narEnfrente={new Image("Imagenes/Animaciones/Sprites/nar2.png")};
        narE=new Animation(narEnfrente,200);
        nar=narI;
        alcalde=new Image("Imagenes/Animaciones/Sprites/alcalde8.png");
        fondo= new Image("Imagenes/Escenas/EscenaPuerto/Puerto1.png");
        /**/
        this.sheetExclamacion= new SpriteSheet("Imagenes/Animaciones/puntos.png",32,33);
        this.exclamacion = new Animation(sheetExclamacion,200);
        this.sheetExclamacion1= new SpriteSheet("Imagenes/Animaciones/puntos.png",32,33);
        this.exclamacion1 = new Animation(sheetExclamacion,200);
        this.sheetEncapuchado= new SpriteSheet("Imagenes/Animaciones/interrogacion.png",32,33);
        this.encapuchado = new Animation(sheetEncapuchado,200);
        /**/
        estado=0;
        this.input = gc.getInput();
        mensajePantalla= new TrueTypeFont(tipoLetra, true);
        posicion = new Vector2f(0,300);
        posicionE = new Vector2f(0,300);
        ventanaDialogo= new Image("Imagenes/Avatar/cajaMensaje.png");
        avatarH =  new Image("Imagenes/Personajes/HoraciaA.png");
        avatarM =  new Image("Imagenes/Personajes/MordeimA.png");
        avatarK =  new Image("Imagenes/Personajes/KibitoA.png");
        avatarAlcalde = new Image("Imagenes/Personajes/alcalde.png");
        avatarE = new Image ("Imagenes/Personajes/EncapuchadoA.png");
        avatarDialogo = avatarH;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        rugido=new Sound("Musica/Efectos/rugido1.wav");
        texto= new TrueTypeFont(letraMenu, true);
        /**/
        
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
        
            fondo.draw(-1200, -200);
            
            //EDIT:Rener Mordeim
            if(reproducirExclamacion){
                this.exclamacion.draw(posicion.x+300, posicion.y-96);
            }
            if(reproducirExclamacion1){
                this.exclamacion1.draw(posicion.x+364, posicion.y-128);
            }
            if(reproducirEncapuchado){
                this.encapuchado.draw(posicion.x+300, posicion.y-96);
            }
            if(estado>=0){
                hor.draw(posicion.x+300, posicion.y-64);
                mor.draw(posicion.x+364, posicion.y-32);
                kib.draw(posicion.x+364, posicion.y-96);
                alcalde.draw(posicion.x+236, posicion.y-64);
                if(estado!=16){
                renderDialogo();
                }
                if(estado>=16){
                    nar.draw(posicionE.x+344, posicionE.y+96);
                }
            }
                
//            texto.drawString(1000, 0, "" + estado);
    }
    @Override
    //Muestra la actualización
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        exclamacion.update(i);
        if(input.isKeyPressed(Input.KEY_ENTER)){
            if(estado!=23)
            {
                sonidoSelect.play(1, 0.2f);
                time=0;
                estado++;
            }
            
        }
        switch (estado)
        {
            case 0://EDIT
//                time+=i;
//                if(time/1000>0.4f)//
//                {
//                    time=0;
//                    estado++;
//                }
                estado++;
                break;
            case 1:
                avatarDialogo=this.avatarAlcalde;
                //////="////////////////////////////////////////////////////////";
                linea1="Muchísimas gracias por liberar";
                linea2="nuestro puerto de la banda del Cuervo.";
                linea3="";
                linea4="";
                break;
            case 2:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="No ha sido nada alcalde.";
                linea2="Jijiji";
                linea3="";
                linea4="";
                break;
            case 3:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Creida.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 4:
                avatarDialogo=this.avatarAlcalde;
                //////="////////////////////////////////////////////////////////";
                linea1="Por vuestros esfuerzos por derrotar a los bandidos;";
                linea2="haremos todo lo que pidaís excepto ser alcalde de";
                linea3="Tamberl, daros sangre de vírgenes, todo nuestro oro,";
                linea4="obligarnos a morir, ...";
                break;
            case 5:
                reproducirExclamacion=true;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Ehhh...";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 6:
                reproducirExclamacion=false;
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Alcalde, solo necesitamos ir a Troyia.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 7:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Y todo vuestro oro tampoco estaría mal.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 8:
                avatarDialogo=this.avatarAlcalde;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Solo queríais ir a Troyia?";
                linea2="¿Esa ciudad montañosa donde la gente esta";
                linea3="misteriosamente resucitando volviendo como";
                linea4="no muertos?";
                break;
            case 9:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Solo era eso, si, ehhh, nada más.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 10:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Y el oro, sobretodo el oro.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 11:
                avatarDialogo=this.avatarK;
                linea1="En cualquier caso, gracias por la información de";
                linea2="Troyia.";
                linea3="";
                linea4="";
                break;
                
            case 12:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Pero que pasa con nuestra recompensa?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 13:
                reproducirExclamacion1=true;
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="...";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 14:
                reproducirExclamacion1=false;
                avatarDialogo=this.avatarAlcalde;
                linea1="No os preocupeís, recibiréis una recompensa, pero tened";
                linea2="mucho cuidado jóvenes aventureros.";
                linea3="Y no os preocupeis por el viaje, podeis ir en este";
                linea4="barco, os lo habeis ganado.";
                break;
            case 15:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Muchas gracias alcalde.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 16:
                nar=narI;
                posicionE.x-=0.1f*i;
                if(posicionE.x<=(-50)){
                    estado++;
                }
                break;
            case 17:
                nar=narE;
                avatarDialogo=this.avatarE;
                linea1="Y asi nuestros patéticos héroes consiguieron el pase.";
                linea2="para ir al segundo sello de Luci en Troyia.";
                linea3="Pero su aventura continuara.";
                linea4="";
                break;
            case 18:
                hor=horA;
                mor=morA;
                kib=kibA;
                time+=i;
                reproducirEncapuchado=true;
                if(time/1000>1f)//
                {
                    reproducirEncapuchado=false;
                    time=0;
                    estado++;
                }
                avatarDialogo=this.avatarH;
                linea1="¿Quién es este tío?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 19:
                estado=0;
                VenganzaBelial.controlMusica.cambiarMusica("Musica/BSO/Catacumbas.wav");
                sbg.enterState(VenganzaBelial.ESCENATROYIA1);
                //EDIT enlazar escena con la siguiente
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
