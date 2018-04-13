/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estados;

import java.awt.Font;
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
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Dolores
 */
public class EscenaPrototipo extends BasicGameState{
    private int idEstado;
    private static final int POSICIONAVATARX = 30;
    private static final int POSICIONAVATARY = 620;
    private static final int TAMANYOAVATARX = 115;
    private static final int TAMANYOAVATARY = 115;
    //avatarDialogo.draw(30, 610, 115, 125);
    private boolean reproducirExclamacion=false;//EDIT    
    private boolean reproducirExclamacion1=false;//EDIT
    private boolean reproducirExclamacion2=false;//EDIT
    private SpriteSheet sheetExclamacion,sheetExclamacion1,sheetExclamacion2;//EDIT
    private Animation exclamacion;//EDIT
    private Animation exclamacion1;//EDIT
    private Animation exclamacion2;//EDIT
    int time;//EDIT
    private static final int TILESIZE = 32;
    private String linea1="";
    private String linea2="";
    private String linea3="";
    private String linea4="";
    private String linea5="";
    private String linea6="";
    private String linea7="";
    private String linea8="";
    private static final int esquinaXMapa=550;
    private static final int esquinaYMapa=300;
    private Image hes1, hes2, hes3;//EDIT
    private Image hor1;//EDIT
    private Image ventanaDialogo,avatarDialogo, avatarH,avatarM, avatarK, avatarHestia,avatarE;
    private Input input;
    private int estado;
    private Image fondo;
    private Image fondoHestia;//EDIT
    private TrueTypeFont texto, texto1;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 15);    
    private Font letraMenu1  = new Font("Verdana", Font.PLAIN, 15);
    private Color rojo = new Color (160,64,0);
    private SpriteSheet hestia;
    private Animation animacionHestia;
    private Music musicaIntro;
    private Sound efecto;
    private Sound sonidoPuerta;
    private Vector2f posicion;
    

    public EscenaPrototipo(int id) {
        this.idEstado=id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        estado=0;
        this.input = gc.getInput();
        this.sheetExclamacion= new SpriteSheet("Imagenes/Animaciones/malestar.png",32,33);//EDIT
        this.sheetExclamacion1= new SpriteSheet("Imagenes/Animaciones/puntos.png",32,33);//EDIT
        this.sheetExclamacion2= new SpriteSheet("Imagenes/Animaciones/amor.png",32,33);//EDIT
        this.exclamacion = new Animation(sheetExclamacion,200);//EDIT
        this.exclamacion1 = new Animation(sheetExclamacion1,200);//EDIT
        this.exclamacion2 = new Animation(sheetExclamacion2,200);//EDIT
        fondo= new Image("Imagenes/Fondos/FondoIntro.jpg");
        fondoHestia= new Image("Imagenes/Escenas/SalaInicial/SalaHestia.png");//EDIT
        ventanaDialogo= new Image("Imagenes/Avatar/cajaMensaje.png");
        hes1=new Image("Imagenes/Animaciones/Sprites/hes11.png");//EDIT
        hes2=new Image("Imagenes/Animaciones/Sprites/hes5.png");//EDIT
        hes3=new Image("Imagenes/Animaciones/Sprites/hes2.png");//EDIT
        hor1=new Image("Imagenes/HeroeMundo/her21.png");//EDIT
        avatarH =  new Image("Imagenes/Personajes/HoraciaA.png");
        avatarM =  new Image("Imagenes/Personajes/MordeimA.png");
        avatarK =  new Image("Imagenes/Personajes/KibitoA.png");
        avatarHestia = new Image("Imagenes/Personajes/HestiaA.png");//EDIT
        avatarE = new Image ("Imagenes/Personajes/EncapuchadoA.png");
        texto= new TrueTypeFont(letraMenu, true);
        texto1= new TrueTypeFont(letraMenu1, true);
        posicion = new Vector2f(esquinaXMapa+TILESIZE*2,esquinaYMapa+TILESIZE*2);
        this.hestia= new SpriteSheet("Imagenes/Animaciones/Sprites/Hestia.png",20,20);
        this.animacionHestia = new Animation(hestia,200);
        this.musicaIntro = new Music("Musica/BSO/Ablaze.wav");
        this.efecto = new Sound("Musica/Efectos/Cry2.wav");
        sonidoPuerta=new Sound("Musica/Efectos/Door.wav");
        
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        //animacionHestia.draw(450, 300, 130, 192);
        if(estado<4){
            fondo.draw(0, 0, VenganzaBelial.WIDTH, VenganzaBelial.HEIGHT);
            renderDialogo1();
        }
        if(estado==4){
            fondoHestia.draw(esquinaXMapa, esquinaYMapa);//EDIT
            if(reproducirExclamacion2){
            this.exclamacion2.draw(posicion.x+80, posicion.y-34);
            }
            texto1.drawString(1050, 0, "Sala de Hestia");
            hes1.draw(posicion.x+80, posicion.y);//EDIT
            renderDialogo();
        }
        if(estado>=5){
            fondoHestia.draw(esquinaXMapa, esquinaYMapa);//EDIT
            texto1.drawString(1050, 0, "Sala de Hestia");
            hes1.draw(posicion.x+80, posicion.y);//EDIT
            renderDialogo();
            
            if(estado>=7 && estado<19){
                hes3.draw(posicion.x+80, posicion.y);//EDIT
                if(estado==10){
                    if(reproducirExclamacion)
                    this.exclamacion.draw(posicion.x+80, posicion.y-34);
                }
                if(estado==12){
                    if(reproducirExclamacion1)
                    this.exclamacion1.draw(posicion.x+80, posicion.y-34);
                }
                if(estado<18){
                    hor1.draw(posicion.x+80, posicion.y+190);//EDIT
                }
            }
        }
        texto1.drawString(1000, 0, "" + estado);
        
    }

    @Override
    //Muestra la actualización
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        musicaIntro.play();
        if (input.isKeyPressed(Input.KEY_ENTER)){
            if(estado==4){
                musicaIntro.stop();
            }
            if(estado==18){
                sbg.enterState(VenganzaBelial.ESCENACARRETA);
            }
            estado++;
            time=0;
        }
        switch (estado)
        {
            case 0:
                avatarDialogo=this.avatarE;
                linea5 = "El mundo de nuestros protegonistas se llama Reynos.";
                linea6 = "Hace muchos años había un demonio, Belial, que engaño";
                linea7 = "a la mitad de la población con otorgarles lo que";
                linea8 = "quisieran a cambio de abrir las puertas del Infierno.";
                break;
            case 1:
                avatarDialogo=this.avatarE;
                linea5 = "De esa forma Reynos se sumió en la Guerra Demoniaca.";
                linea6 = "A medida que avanzaba la guerra las puertas del";
                linea7 = "Infierno parecían abrirse, como si el derramamiento";
                linea8 = "de sangre fuera la llave.";
                break;
            case 2:
                avatarDialogo=this.avatarE;
                linea5 = "Cuando las puertas se iban a abrir y Belial entraría";
                linea6 = "a nuestro mundoaparecio una organización para preservar";
                linea7 = "la paz, Cardinal. Cardinal sello las puertas del Infierno con";
                linea8 = "un ritual y los sellos se exparcierón por todo el mundo de Reynos.";
                break;
            case 3:
                avatarDialogo=this.avatarE;
                linea5 = "Con el paso de los siglos los sellos se han debilitado y los";
                linea6 = "movimientos sospechosos de Cardinal han aumentado considerablemente.";
                linea7 = "";
                linea8 = "";
                break;
            //Primera escena introducción
            case 4:
                time+=i;
                reproducirExclamacion2=true;
                if(time/1000>0.4f)//
                {
                    reproducirExclamacion2=false;
                    time=0;
                }
                avatarDialogo=this.avatarHestia;
                //////="////////////////////////////////////////////////////////";
                linea1="Que vidriera tan bonita.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 5:
                avatarDialogo=this.avatarHestia;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Quién va?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 6:
                time+=i;
                hes1=hes2;
                if(!sonidoPuerta.playing())
                {
                    sonidoPuerta.play();
                }
                if(time/1000>0.4f)//
                {
                    time=0;
                    estado++;
                }
                break;
            case 7:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Cap...capi...CAPITANA DEL ESCUADRÓN “F”, HORACIA";
                linea2="LABELLE A SU SERVICIO MI SEÑORA.";
                linea3="";
                linea4="";
                break;
            case 8:
                avatarDialogo=this.avatarHestia;
                linea1="¿Sabe porque le he hecho llamar capitana Labelle?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 9:
                avatarDialogo=this.avatarH;
                linea1="Le prometo que la explosión en Ciudad Deyolica no fue a";
                linea2="proposito.";
                linea3="Mordeim utilizo explosivos para detener a un ladrón y";
                linea4="Kibito no sabe usar magia de agua y...";
                break;
            case 10:
                time+=i;
                reproducirExclamacion=true;
                if(time/1000>0.4f)//
                {
                    reproducirExclamacion=false;
                    estado++;
                    time=0;
                }
                avatarDialogo=this.avatarHestia;
                linea1="¿Eh?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 11:
                avatarDialogo=this.avatarH;
                linea1="¿Eh?... quiero decir...eh... no se nada de una explosión";
                linea2="en la ciudad.";
                linea3="¿Para que me había llamado Sacerdotisa Hestia?";
                linea4="";
                break;
            case 12:
                time+=i;
                reproducirExclamacion1=true;
                if(time/1000>0.4f)//
                {
                    reproducirExclamacion1=false;
                    time=0;
                }
                avatarDialogo=this.avatarHestia;
                linea1="...";
                linea2="Tengo una misión de altísima importancia para tu equipo.";
                linea3="¿Conoceís los sellos de Luci capitana Labelle?";
                linea4="";
                break;
            case 13:
                avatarDialogo=this.avatarH;
                linea1="¿No son los sellos que impidierón que el Demonio Belial";
                linea2="llegará a nuestro mundo?";
                linea3="";
                linea4="";
                break;
            case 14:
                avatarDialogo=this.avatarHestia;
                linea1="En efecto capitana Labelle.";
                linea2="Han pasado varios siglos desde la Guerra Demoniaca y los";
                linea3="sellos se han debilitado, por ello, tu escuadrón deberá";
                linea4="reforzarlos con este objeto. ¿Alguna duda?";
                break;
            case 15:
                avatarDialogo=this.avatarH;
                linea1="Si, digo, no, digo...¿Está ústed segura de esto?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 16:
                avatarDialogo=this.avatarHestia;
                linea1="Esta es una misión ultrasecreta incluso para Archi.";
                linea2="Por ello, viajaréis de incognito sin que nadie sepa lo";
                linea3="que hareís. Os daré un mapa de la posición aproximada de";
                linea4="los sellos. Buena suerte capitana Labelle.";
                break;
            case 17:
                avatarDialogo=this.avatarH;
                linea1="No la defraudaremos Sacerdotisa Hestia.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 18:
                if(!sonidoPuerta.playing())
                {
                    sonidoPuerta.play();
                }
                avatarDialogo=this.avatarHestia;
                linea1="Espero que cumplan esta misión.";
                linea2="";
                linea3="";
                linea4="";
                break;
            //Segunda escena introducción.
            case 19:
                break;
            case 20:
                break;
        }
        
    }
    private void renderDialogo()
    {
        avatarDialogo.draw(POSICIONAVATARX, POSICIONAVATARY, TAMANYOAVATARX, TAMANYOAVATARY);
        this.ventanaDialogo.draw(0, 600, 1);
        ///////////////////////////////////,"////////////////////////////////////////////////////////"/;
        texto1.drawString(160, 625,linea1 );
        texto1.drawString(160, 640,linea2);
        texto1.drawString(160, 655,linea3 );
        texto1.drawString(160, 670,linea4);
    }
    private void renderDialogo1(){
        avatarDialogo.draw(POSICIONAVATARX, POSICIONAVATARY, TAMANYOAVATARX, TAMANYOAVATARY);
        texto.drawString(450, 300, linea5, rojo);
        texto.drawString(450, 350, linea6, rojo);
        texto.drawString(450, 400, linea7, rojo);
        texto.drawString(450, 450, linea8, rojo);
    }
}
