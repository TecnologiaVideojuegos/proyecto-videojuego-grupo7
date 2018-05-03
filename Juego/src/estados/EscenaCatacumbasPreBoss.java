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
public class EscenaCatacumbasPreBoss extends BasicGameState{
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
    private Vector2f posicion,posicionE,posicionL,posicionP;
    private static final int esquinaXMapa=0;
    private static final int esquinaYMapa=0;
    /*Animaciones*/
    private SpriteSheet sheetExclamacion;
    private Animation exclamacion;
    private Animation hor,kib,mor;
    private Animation horD,kibD,morD;
    private Animation horS,kibS,morS;
    private Animation sac,sacI,sacE;
    private Animation lider,liderI,liderS;
    private Animation parca,parcaI,parcaS;
    private Image fondo;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo, avatarH,avatarM, avatarK, avatarSacerdote;
    private Image avatarLider,avatarParca;
    private Image salidaEscena;
    /*Sonido*/
    private Sound sonidoSelect;
    private Music battle;
    int time;//EDIT
    private TrueTypeFont texto;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 15); 
    
    public EscenaCatacumbasPreBoss(int id) {
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
        Image[] kibF={new Image("Imagenes/Animaciones/Sprites/kib8.png")};
        kibS=new Animation(kibF,200);
        Image[] morF={new Image("Imagenes/Animaciones/Sprites/mor8.png")};
        morS=new Animation(morF,200);
        Image[] horEnfrente={new Image("Imagenes/HeroeMundo/her11.png")};
        horS=new Animation(horEnfrente,200);
        hor=horD;
        kib=kibD;
        mor=morD;
        Image[] fanIzq={new Image("Imagenes/Animaciones/Sprites/fanatic5.png")};
        sacI=new Animation(fanIzq,200);
        Image[] fanDown={new Image("Imagenes/Animaciones/Sprites/fanatic11.png")};
        sacE=new Animation(fanDown,200);
        sac=sacI;
        Image[] liderIzq={new Image("Imagenes/Animaciones/Sprites/liderfanatico4.png"),new Image("Imagenes/Animaciones/Sprites/liderfanatico5.png"),new Image("Imagenes/Animaciones/Sprites/liderfanatico6.png")};
        liderI=new Animation(liderIzq,200);
        Image[] liderStop={new Image("Imagenes/Animaciones/Sprites/liderfanatico5.png")};
        liderS=new Animation(liderStop,200);
        lider=liderI;
        Image[] parcaIzq={new Image("Imagenes/Animaciones/Sprites/parca4.png"),new Image("Imagenes/Animaciones/Sprites/parca5.png"),new Image("Imagenes/Animaciones/Sprites/parca6.png")};
        parcaI=new Animation(parcaIzq,200);
        Image[] parcaStop={new Image("Imagenes/Animaciones/Sprites/parca5.png")};
        parcaS=new Animation(parcaStop,200);
        parca=parcaI;
        fondo= new Image("Imagenes/Escenas/EscenaTroyia/DungeonCatacumbas.png");
        /**/
        this.sheetExclamacion= new SpriteSheet("Imagenes/Animaciones/puntos.png",32,33);
        this.exclamacion = new Animation(sheetExclamacion,200);
        /**/
        estado=0;
        this.input = gc.getInput();
        mensajePantalla= new TrueTypeFont(tipoLetra, true);
        posicion = new Vector2f(0,300);
        posicionE = new Vector2f(0,300);
        posicionL = new Vector2f(0,300);
        posicionP = new Vector2f(0,300);
        ventanaDialogo= new Image("Imagenes/Avatar/cajaMensaje.png");
        avatarH =  new Image("Imagenes/Personajes/HoraciaA.png");
        avatarM =  new Image("Imagenes/Personajes/MordeimA.png");
        avatarK =  new Image("Imagenes/Personajes/KibitoA.png");
        avatarSacerdote = new Image("Imagenes/Avatar/Caras/fanatic.png");
        avatarLider = new Image("Imagenes/Avatar/Caras/liderfanatico.png");
        avatarParca = new Image("Imagenes/Avatar/Caras/parca.png");
        avatarDialogo = avatarH;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        texto= new TrueTypeFont(letraMenu, true);
        battle = new Music("Musica/BSO/FanaticBattle.wav");
        /**/
        
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
        
            fondo.draw(-1232, -160);
            
            //EDIT:Rener Mordeim
            if(reproducirExclamacion){
                this.exclamacion.draw(posicion.x-64, posicion.y+176);
            }
            if(estado>=0){
                hor.draw(posicion.x, posicion.y+240);
                mor.draw(posicion.x, posicion.y+272);
                kib.draw(posicion.x, posicion.y+208);
                sac.draw(posicionL.x+364, posicionL.y+272);
                if(estado>=1 && estado!=6 && estado!=12){
                renderDialogo();
                }
                if(estado>=6){
                    lider.draw(posicionE.x+664, posicionE.y+240);
                }
                if(estado>=12){
                    parca.draw(posicionP.x+664, posicionP.y+208);
                }
                
            }
//            texto.drawString(1000, 0, "" + estado);
    }
    @Override
    //Muestra la actualización
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        exclamacion.update(i);
        if(input.isKeyPressed(Input.KEY_ENTER)){
            if(estado!=30)
            {
                sonidoSelect.play(1, 0.2f);
                time=0;
                estado++;
                if(estado>=6){
                    battle.play(1, 0.2f);
                }
                if(estado<=19){
                        battle.stop();
                }
                
            }
            
        }
        switch (estado)
        {
            case 0:
                posicion.x+=0.1f*i;
                if(posicion.x>=256){
                    estado++;
                }
                break;
            case 1:
                hor=horS;
                kib=kibS;
                mor=morS;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¡¿Sacerdote Guilles, que hace ústed aquí?!";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 2:
                avatarDialogo=this.avatarSacerdote;
                //////="////////////////////////////////////////////////////////";
                linea1="Os felicito por haber llegado tan lejos Cardinal.";
                linea2="No obstante, debéis morir para que nuestro ritual";
                linea3="finalice.";
                linea4="";
                break;
            case 3:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Sabeís que vuestro ritual está resucitando a los";
                linea2="muertos de Troyia?";
                linea3="";
                linea4="";
                break;
            case 4:
                avatarDialogo=this.avatarSacerdote;
                linea1="JAJAJAJAJAJAJA.";
                linea2="IDIOTAS, POR ESO LO ESTAMOS HACIENDO,";
                linea3="PARA QUE LAS PUERTAS DEL INFIERNO SE";
                linea4="ABRAN Y VENGA NUESTRO SEÑOR BELIAL.";
                break;
            case 5:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Yo sabía que estabas loco, pero no a esos niveles.";
                linea2="Y da igual si resucita Belial, no podrá conmigo.";
                linea3="";
                linea4="";
                break;
            case 6:
                posicionE.x-=0.1f*i;
                if(posicionE.x<=(-300)){
                    estado++;
                }
                break;
            case 7:
                lider=liderS;
                avatarDialogo=this.avatarLider;
                //////="////////////////////////////////////////////////////////";
                linea1="Shurororororo, alguien se cree que puede con el señor";
                linea2="Belial.";
                linea3="";
                linea4="";
                break;
            case 8:
                sac=sacE;
                avatarDialogo=this.avatarSacerdote;
                //////="////////////////////////////////////////////////////////";
                linea1="Gran Maestro";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 9:
                sac=sacI;
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Quién es este tío?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 10:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="No lo se pero le diré algo:";
                linea2="Los insultos para Horacia, que pa' eso esta.";
                linea3="";
                linea4="";
                break;
            case 11:
                avatarDialogo=this.avatarLider;
                linea1="Lo sentimos mucho Cardinal, pero moriréis aquí y ahora.";
                linea2="¡¡¡CARONTE, ACUDE A MI LLAMADA!!!";
                linea3="";
                linea4="";
                break;
                
            case 12:
                posicionP.x-=0.1f*i;
                if(posicionP.x<=(-300)){
                    estado++;
                }
                break;
            case 13:
                parca=parcaS;
                avatarDialogo=this.avatarParca;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Me ha llamado?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 14:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Eso es la Muerte, la Parca, el arcángel Azra'il,";
                linea2="un Shinigami, el Dios Thanatos, el Dios Anubis,";
                linea3="el ángel Azrael?";
                linea4="";
                break;
            case 15:
                avatarDialogo=this.avatarParca;
                //////="////////////////////////////////////////////////////////";
                linea1="Si, tengo muchos nombres.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 16:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Me da igual como te llames, te derrotaré como hicimos";
                linea2="con ese tronco andante.";
                linea3="";
                linea4="";
                break;
            case 17:
                avatarDialogo=this.avatarLider;
                //////="////////////////////////////////////////////////////////";
                linea1="Shurororororo, prepararos para desaparecer.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 18:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Os derrotaremos y liberaremos Troyia de vuestros";
                linea2="planes malditos lunáticos sectarios.";
                linea3="";
                linea4="";
                break;
            case 19:
                estado=0;
                sbg.enterState(VenganzaBelial.ESTADOMENUINICIO);//EDIT:
                //Deberiamos entrar en estado Combate contra los 3 Bosses
                //Darklord, Death y fanático
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