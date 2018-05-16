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


public class EscenaBosque1 extends BasicGameState{
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
    private Vector2f posicion;
    private static final int esquinaXMapa=0;
    private static final int esquinaYMapa=0;
    /*Animaciones*/
    private SpriteSheet sheetExclamacion;
    private Animation exclamacion;
    private Image horac, horacKO, horacL, horacR,horacF;
    private Image mor, morKO, morF, morB;
    private Image kib, kibKO, kibF, kibiR;
    private Image fondo;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo, avatarH,avatarM, avatarK;
    private Image salidaEscena;
    /*Sonido*/
    private Sound sonidoSelect;
    int time;//EDIT
    
    public EscenaBosque1(int id) {
        this.idEstado=id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        horacF=new Image("Imagenes/HeroeMundo/her01.png");
        horacKO= new Image("Imagenes/HeroeMundo/her21.png");
        horacKO.rotate(180);
        horacL= new Image("Imagenes/HeroeMundo/her31.png");
        horacR= new Image("Imagenes/HeroeMundo/her11.png");
        horac=horacKO;
        /**/
        kibF=new Image("Imagenes/Animaciones/Sprites/kib2.png");
        kibKO= new Image("Imagenes/Animaciones/Sprites/kib11.png");
        kibKO.rotate(90);
        kibiR= new Image("Imagenes/Animaciones/Sprites/kib8.png");
        kib=kibKO;
        /**/
        morF=new Image("Imagenes/Animaciones/Sprites/mor2.png");
        morKO= new Image("Imagenes/Animaciones/Sprites/mor11.png");
        morKO.rotate(-90);
        morB= new Image("Imagenes/Animaciones/Sprites/mor11.png");
        mor=morKO;
        
        fondo= new Image("Imagenes/Escenas/EscenaBosque1/mapaBosque.png");
        //salidaEscena= new Image("Imagenes/Escenas/EscenaCarreta1/salidaEscena1.png");
        /**/
        this.sheetExclamacion= new SpriteSheet("Imagenes/Animaciones/exclamacion.png",32,33);
        this.exclamacion = new Animation(sheetExclamacion,200);
        /**/
        estado=0;
        this.input = gc.getInput();
        mensajePantalla= new TrueTypeFont(tipoLetra, true);
        posicion = new Vector2f(esquinaXMapa+TILESIZE*2,esquinaYMapa+TILESIZE*2);
        ventanaDialogo= new Image("Imagenes/Avatar/cajaMensaje.png");
        avatarH =  new Image("Imagenes/Personajes/HoraciaA.png");
        avatarM =  new Image("Imagenes/Personajes/MordeimA.png");
        avatarK =  new Image("Imagenes/Personajes/KibitoA.png");
        avatarDialogo = avatarH;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        /**/
        
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
        
            fondo.draw(0, 0);
            horac.draw(posicion.x+34, posicion.y);
            kib.draw(posicion.x-34, posicion.y);
            mor.draw(posicion.x+34, posicion.y+64);
            //EDIT:Rener Mordeim
            if(reproducirExclamacion)
                this.exclamacion.draw(posicion.x+34, posicion.y-34);
            
            if(estado>1)
                renderDialogo();
    }

    @Override
    //Muestra la actualización
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        exclamacion.update(i);
        if(input.isKeyPressed(Input.KEY_ENTER)){
            if(estado!=20)
            {
                sonidoSelect.play(1, 0.2f);
                time=0;
                estado++;
            }
            
        }
        switch (estado)
        {
            case 0:
                VenganzaBelial.controlMusica.cambiarMusica("Musica/BSO/Music_Forest.wav");
                time+=i;
                //ost.loop(1, 0.05f);
                if(time/1000>2)//3 segundos de ejecución
                {
                    estado++;
                    time=0;
                }
                break;
            case 1:
                time+=i;
                reproducirExclamacion=true;
                if(time/1000>0.4f)//
                {
                    reproducirExclamacion=false;
                    estado++;
                    time=0;
                }
                break;
            case 2:
                horac=horacL;
                time+=i;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¡AAAAAHH";
                if(time/1000>0.5f)//3 segundos de ejecución
                {
                    estado++;
                    time=0;
                }
                break;
            case 3:
                horac=horacR;
                time+=i;
                if(time/1000>0.5f)//3 segundos de ejecución
                {
                    estado++;
                    time=0;
                }
                break;
            case 4:
                horac=horacF;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¡Chicos!";
                linea2="Oh dios mio..snif..estan muertos...no me trataban bien,";
                linea3="eran los peores compañeros que uno podría tener pero..";
                linea4="no quería que se muriesen..snif.";
                break;
            case 5:
                horac=horacF;
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Ni se te ocurra matarme sin mi permiso.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 6:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Lamento no cumplir con tus expectativas, pero tambien";
                linea2="sigo respirando. ";
                linea3="";
                linea4="";
                break;
            case 7:
                time+=i;
                reproducirExclamacion=true;
                if(time/1000>0.4f)//
                {
                    reproducirExclamacion=false;
                    estado++;
                    time=0;
                }
                break;
            case 8:
                //EDIT Mordeim
                kib=kibiR;
                mor=morB;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¡Estais vivos!";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 9:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="No gracias a ti y tu carreta, ¿Que puñetas ha pasado?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 10:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Mi deducción lógica es que nos hemos despeñado a causa";
                linea2="de algún tipo de explosión y, declarando lo obvio, ";
                linea3="ahora estamos en un bosque, el bosque de Tamberl a no";
                linea4="ser que hayamos sido teletransportados.";
                break;
                
            case 11:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Y eso que mierdas significa?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 12:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Significa, mi ignorante amigo, que estamos condenados.";
                linea2="No obstante en un intento por escapar del destino";
                linea3="sugiero que intentemos salir antes de que aparezca";
                linea4="algo que intente matarnos...";
                break;
            case 13:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Los insultos para Horacia que para eso esta.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 14:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Eee...chicos..creo que tenemos compañia..";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 15:
                kib=kibF;
                mor=morF;
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Para que hablaré...";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 16:
                VenganzaBelial.controlMusica.cambiarMusica("Musica/BSO/BattleOst.wav");
                sbg.enterState(VenganzaBelial.ESTADOCOMBATETUT);
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