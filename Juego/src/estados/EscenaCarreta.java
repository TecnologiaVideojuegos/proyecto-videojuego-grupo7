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
public class EscenaCarreta extends BasicGameState{
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
    /*Mapa*/
    private Vector2f posicion;
    private static final int esquinaXMapa=550;
    private static final int esquinaYMapa=300;
    /*Animaciones*/
    private Image hero1, hero2, hero3;
    private Animation fondo;
    private Animation explosion;
    private Animation bandit, banditR, banditD;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo, avatarH,avatarM, avatarK, avatarB;
    private Image carretera;
    private Image carro;
    private Image salidaEscena;
    /*Sonido*/
    private Sound sonidoSelect;
    private Sound sonidoExplosion;
    private Music ost;
    int time;//EDIT
    
    public EscenaCarreta(int id) {
        this.idEstado=id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Image[] animationfondo ={new Image("Imagenes/Escenas/EscenaCarreta1/Carreta0.png"),new Image("Imagenes/Escenas/EscenaCarreta1/Carreta0.png"),new Image("Imagenes/Escenas/EscenaCarreta1/Carreta7.png"),new Image("Imagenes/Escenas/EscenaCarreta1/Carreta6.png"),new Image("Imagenes/Escenas/EscenaCarreta1/Carreta5.png"),new Image("Imagenes/Escenas/EscenaCarreta1/Carreta4.png"),new Image("Imagenes/Escenas/EscenaCarreta1/Carreta3.png"),new Image("Imagenes/Escenas/EscenaCarreta1/Carreta2.png"),new Image("Imagenes/Escenas/EscenaCarreta1/Carreta1.png"),new Image("Imagenes/Escenas/EscenaCarreta1/Carreta0.png")};
        fondo= new Animation(animationfondo,500);
        hero1=new Image("Imagenes/HeroeMundo/her01.png");
        hero2=new Image("Imagenes/Animaciones/Sprites/kib2.png");
        hero3=new Image("Imagenes/Animaciones/Sprites/mor8.png");
        Image[] explo ={new Image("Imagenes/Animaciones/Combate/ex1.png"),new Image("Imagenes/Animaciones/Combate/ex2.png"),new Image("Imagenes/Animaciones/Combate/ex3.png"),new Image("Imagenes/Animaciones/Combate/ex4.png"),new Image("Imagenes/Animaciones/Combate/ex5.png"),new Image("Imagenes/Animaciones/Combate/ex6.png"),new Image("Imagenes/Animaciones/Combate/ex7.png"),new Image("Imagenes/Animaciones/Combate/ex8.png"),new Image("Imagenes/Animaciones/Combate/ex9.png")};
        explosion = new Animation(explo,200);
        Image[] banRight={new Image("Imagenes/Animaciones/Sprites/ban2.png"),new Image("Imagenes/Animaciones/Sprites/ban3.png"),new Image("Imagenes/Animaciones/Sprites/ban4.png")};
        Image[] banDown={new Image("Imagenes/Animaciones/Sprites/ban1.png")};
        banditR= new Animation(banRight,200);
        banditD=new Animation(banDown,200);
        bandit=banditR;
        carretera= new Image("Imagenes/Escenas/EscenaCarreta1/carretera.png");
        carro= new Image("Imagenes/Escenas/EscenaCarreta1/carro.png");
        salidaEscena= new Image("Imagenes/Escenas/EscenaCarreta1/salidaEscena1.png");
        /**/
        estado=0;
        this.input = gc.getInput();
        mensajePantalla= new TrueTypeFont(tipoLetra, true);
        posicion = new Vector2f(esquinaXMapa+TILESIZE*2,esquinaYMapa+TILESIZE*2);
        ventanaDialogo= new Image("Imagenes/Avatar/cajaMensaje.png");
        avatarH =  new Image("Imagenes/Personajes/HoraciaA.png");
        avatarM =  new Image("Imagenes/Personajes/MordeimA.png");
        avatarK =  new Image("Imagenes/Personajes/KibitoA.png");
        avatarB = new Image("Imagenes/Avatar/Caras/banditFace.png");
        avatarDialogo = avatarH;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        sonidoExplosion=new Sound("Musica/Efectos/Explosion5.wav");
        //EDIT//ost= new Music("Musica/BSO/caminoMoria.wav");
        /**/
        
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
        
        if(estado<19){
            fondo.draw(esquinaXMapa, esquinaYMapa);
            hero1.draw(posicion.x+34, posicion.y);
            hero2.draw(posicion.x-34, posicion.y);
            hero3.draw(posicion.x-32, posicion.y+60);
            renderDialogo();
        }      
        else if(estado>18 && estado<23){
            carretera.draw(0, 0,VenganzaBelial.WIDTH, VenganzaBelial.HEIGHT);
            if(estado<21)
                carro.draw(400, 300);
        
            if(estado==19){
            explosion.draw(300, 200);
            explosion.draw(500, 200);
            explosion.draw(500, 400);
            explosion.draw(300, 400);   
            }
            if(estado==20){
                Rectangle rectan=new Rectangle(0,0,VenganzaBelial.WIDTH,VenganzaBelial.HEIGHT);
                grphcs.draw(rectan);
                grphcs.fill(rectan);
            }
            if(estado>20){
                bandit.draw(posicion.x, posicion.y);
                bandit.draw(posicion.x+20, posicion.y+50);
            }
            if(estado>21){
                renderDialogo();
            }
        }
        else if(estado==24){
            salidaEscena.draw(posicion.x, posicion.y);
        }
        //edit//mensajePantalla.drawString(800, 30,"ESTADO= "+estado );
    }

    @Override
    //Muestra la actualización
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

        fondo.update(i);
        time+=i;
        if(input.isKeyPressed(Input.KEY_ENTER)){
            if(estado!=20)
            {
                sonidoSelect.play(1, 0.2f);
                estado++;
            }
            
        }
        switch (estado)
        {
            case 0:
                //ost.loop(1, 0.05f);
                estado++;
                break;
            case 1:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="No es que me importe, que lo hace pero, ¿Alguien me dice";
                linea2="por qué estamos en una carreta destartalada, rodeados de";
                linea3="cargamento de dudosa calidad?";
                linea4="";
                break;
            case 2:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Estoo, como dije durante la reunión...al ser una misión";
                linea2="secreta viajar como si entregaramos suministros de forma";
                linea3="poco llamativa es la mejor forma de actuar...";
                linea4="";
                break;
            case 3:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="(Mirada intensa)";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 4:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¡Eeeek!";
                linea2="¡Por favor no me pegues, no ha sido idea mia!";
                linea3="";
                linea4="";
                break;
            case 5:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Ignoremos por un segundo lo echa polvo que esta la ";
                linea2="carreta, centremonos en la ruta a seguir.";
                linea3="";
                linea4="";
                break;
            case 6:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Ssí,esto...veamos...utilizaremos la carretera secundaria";
                linea2="para dirigirnos a Tamberl, se supone que en sus cercanias";
                linea3="encontraremos el primer objetivo...";
                linea4="";
                break;
            case 7:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="La carretera secundaria...esa que circula por encima de ";
                linea2="un barranco, ese barranco en cuyo fondo se encuentra el";
                linea3="conocido bosque de Tamberl, ese bosque donde, según las";
                linea4="leyendas, nadie que haya entrado en los ultimos 50 años";
                break;
            case 8:
                //////="////////////////////////////////////////////////////////";
                linea1="ha regresado para contarlo, ¿Esa carretera secundaria?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 9:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Ssí";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 10:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Quiero bajarme.";
                linea2="";
                linea3="";
                linea4="";
                break;
                
            case 11:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Lady Hestia preparó el transporte para nosotros, estoy";
                linea2="segura de que a pesar de como se vea es perfectamente ";
                linea3="segura...creo..tal vez...¿con un poco de suerte?";
                linea4="";
                break;
            case 12:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="...";
                linea2="Se que lo digo en todas nuestras misiones pero, en esta";
                linea3="estoy seguro de que vamos a morir.";
                linea4="";
                break;
            case 13:
                //Explosión y movimiento de la carreta
                if(!sonidoExplosion.playing())
                {
                    sonidoExplosion.play();
                }
                break;
            case 14:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¡Eeek! ¡Mordeim, prometiste que no habría explosivos!";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 15:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Esta vez yo no he sido";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 16:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Kibito?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 17:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Claaaro, como soy el ¨Mago Negro¨ tengo que ir explotando";
                linea2="cosas..Eso es discriminación y pienso quejarme a Recursos";
                linea3="humanos.";
                linea4="";
                break;
            case 18:
                //Explosión
                if(!sonidoExplosion.playing())
                {
                    sonidoExplosion.play();
                }
                time=0;
                break;
            case 19://Temporización de animacion de explosiones
                time+=i;
                if(time/1000>3)//3 segundos de ejecución
                {
                    estado++;
                    time=0;
                }
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
                break;
            case 21:
                posicion.x+=0.1f*i;
                if(posicion.x>=400){
                    estado++;
                }
                break;
            case 22:
                this.bandit=this.banditD;
                avatarDialogo=this.avatarB;
                //////="////////////////////////////////////////////////////////";
                linea1="Para un jodido carro que podemos atracar en este truño";
                linea2="de carretera y vas,y lo despeñas soltandole encima todos";
                linea3="los explosivos que teniamos...el jefe se va a cabrear.";
                linea4="";
                break;
            case 23:
                posicion.x=0;
                posicion.y=0;
                estado++;
                break;
            case 24:
                posicion.y+=0.4f*i;
                if(posicion.y>=700){
                    estado++;
                }
                break;
            case 25:
                sbg.enterState(VenganzaBelial.ESCENABOSQUE1);//EDIT:Cambio de escena
                break;
        }
    }
    
    private void renderDialogo()
    {
        avatarDialogo.draw(POSICIONAVATARX, POSICIONAVATARY, TAMANYOAVATARX, TAMANYOAVATARY);
        this.ventanaDialogo.draw(0, 600, 1);
        ///////////////////////////////////,"////////////////////////////////////////////////////////"/;
        mensajePantalla.drawString(160, 625,linea1 );
        mensajePantalla.drawString(160, 640,linea2);
        mensajePantalla.drawString(160, 655,linea3 );
        mensajePantalla.drawString(160, 670,linea4);
    }
    
}

/*EJEMPLO DE ANIMACION POR TIEMPO LIMITADO*/
/*
switch (estado)
{
    case 0:
        if(input.isKeyPressed(Input.KEY_ENTER)){
            hero=this.movementDown;
            estado=1;
        }
        break;
    case 1:
        posicion.y+=0.1f*i;
        if(posicion.y>=600){
            estado=2;
        }
        break;
    case 2:
        hero=this.stillUp;
        break;
}
*/
/*Plantilla de dialogo
//////="////////////////////////////////////////////////////////";
linea1="";
linea2="";
linea3="";
linea4="";
avatarDialogo.draw(30, 610, 115, 125);
ventanaDialogo.draw(0, 600, 1);
mensajePantalla.drawString(160, 625,linea1 );
mensajePantalla.drawString(160, 640,linea2);
mensajePantalla.drawString(160, 655,linea3 );
mensajePantalla.drawString(160, 670,linea4);
*/
/*Ejemplo de timer con slick*/
/*
int time;
int segundos;
public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        time+=i;
        segundos=time/1000;
}
*/