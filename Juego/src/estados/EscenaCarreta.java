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
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Dolores
 */
public class EscenaCarreta extends BasicGameState{
    private int idEstado;
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
    TiledMap map;
    private Vector2f posicion;
    private static final int esquinaXMapa=550;
    private static final int esquinaYMapa=300;
    /*Animaciones*/
    private Animation hero, movementUp, movementDown, movementLeft, movementRight, stillUp, stillDown, stillLeft, stillRight;
    private Animation fondo;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo, avatarH,avatarM, avatarK;
    /*Sonido*/
    private Sound sonidoSelect;
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
        Image[] animationUp = {new Image("Imagenes/HeroeMundo/her20.png"), new Image("Imagenes/HeroeMundo/her22.png")};
        Image[] animationDown = {new Image("Imagenes/HeroeMundo/her00.png"), new Image("Imagenes/HeroeMundo/her02.png")};
        Image[] animationLeft = {new Image("Imagenes/HeroeMundo/her30.png"), new Image("Imagenes/HeroeMundo/her32.png")};
        Image[] animationRight = {new Image("Imagenes/HeroeMundo/her10.png"), new Image("Imagenes/HeroeMundo/her12.png")};
        Image[] up = {new Image("Imagenes/HeroeMundo/her21.png")};
        Image[] down = {new Image("Imagenes/HeroeMundo/her01.png")};
        Image[] left = {new Image("Imagenes/HeroeMundo/her31.png")};
        Image[] right = {new Image("Imagenes/HeroeMundo/her11.png")};
        Image[] animationfondo ={new Image("Imagenes/Escenacarro/Carreta0.png"),new Image("Imagenes/Escenacarro/Carreta0.png"),new Image("Imagenes/Escenacarro/Carreta7.png"),new Image("Imagenes/Escenacarro/Carreta6.png"),new Image("Imagenes/Escenacarro/Carreta5.png"),new Image("Imagenes/Escenacarro/Carreta4.png"),new Image("Imagenes/Escenacarro/Carreta3.png"),new Image("Imagenes/Escenacarro/Carreta2.png"),new Image("Imagenes/Escenacarro/Carreta1.png"),new Image("Imagenes/Escenacarro/Carreta0.png"),};
        stillUp = new Animation(up, 500);
        stillDown = new Animation(down, 500);
        stillLeft = new Animation(left, 500);
        stillRight = new Animation(right, 500);
        movementUp = new Animation(animationUp, 500);
        movementDown = new Animation(animationDown, 500);
        movementLeft = new Animation(animationLeft, 500);
        movementRight = new Animation(animationRight, 500);
        fondo= new Animation(animationfondo,500);
        hero=stillDown;
        /**/
        estado=0;
        this.input = gc.getInput();
        mensajePantalla= new TrueTypeFont(tipoLetra, true);
        map = new TiledMap("Imagenes/Escenapruebas/Carreta.tmx");
        posicion = new Vector2f(esquinaXMapa+map.getTileWidth()*2,esquinaYMapa+map.getTileHeight()*2);
        ventanaDialogo= new Image("Imagenes/Avatar/cajaMensaje.png");
        avatarH =  new Image("Imagenes/Personajes/HoraciaA.png");
        avatarM =  new Image("Imagenes/Personajes/MordeimA.png");
        avatarK =  new Image("Imagenes/Personajes/KibitoA.png");
        avatarDialogo = avatarH;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        ost= new Music("Musica/BSO/caminoMoria.wav");
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
                //map.render(esquinaXMapa, esquinaYMapa);
                fondo.draw(esquinaXMapa, esquinaYMapa);
                hero.draw(posicion.x, posicion.y);
                renderDialogo();
                mensajePantalla.drawString(200, 200, ""+time/1000);
    }

    @Override
    //Muestra la actualización
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        hero.update(i);
        fondo.update(i);
        time+=i;
        if(input.isKeyPressed(Input.KEY_ENTER)){
            sonidoSelect.play(1, 0.2f);
            estado++;
        }
        switch (estado)
        {
            case 0:
                ost.loop(1, 0.05f);
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
                linea2="carreta, centremonos en la ruta a seguir";
                linea3="";
                linea4="";
                break;
            case 6:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 7:
                //////="////////////////////////////////////////////////////////";
                linea1="";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 8:
                //////="////////////////////////////////////////////////////////";
                linea1="";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 9:
                //////="////////////////////////////////////////////////////////";
                linea1="";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 10:
                //////="////////////////////////////////////////////////////////";
                linea1="";
                linea2="";
                linea3="";
                linea4="";
                break;
        }
    }
    
    private void renderDialogo()
    {
        avatarDialogo.draw(30, 610, 115, 125);
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