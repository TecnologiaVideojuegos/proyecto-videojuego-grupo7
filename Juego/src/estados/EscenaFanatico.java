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


public class EscenaFanatico extends BasicGameState{
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
    private Vector2f posicion,posicionA,posicionEn;
    private static final int esquinaXMapa=0;
    private static final int esquinaYMapa=0;
    /*Animaciones*/
    private SpriteSheet sheetExclamacion;
    private Animation exclamacion;
    private SpriteSheet sheetExclamacion1;
    private Animation exclamacion1;
    private SpriteSheet sheetEncapuchado;
    private Animation encapuchado;
    private Animation lider,liderS,liderU;
    private Animation fan1,fan2,fan3;
    private Animation fan1A,fan2A,fan3A;
    private Animation fanD,fanI,fanU;
    private Animation mis,misU,misD,misS;
    private Animation nar,narI,narE;
    private Animation ant,antM;
    private Image fondo;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo, avatarLider,avatarMisterio, avatarE;
    /*Sonido*/
    private Sound sonidoSelect;
    int time;//EDIT
    private TrueTypeFont texto;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 15); 
    
    public EscenaFanatico(int id) {
        this.idEstado=id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Image[] liderStop={new Image("Imagenes/Animaciones/Sprites/liderfanatico2.png")};
        liderS=new Animation(liderStop,200);
        Image[] liderUp={new Image("Imagenes/Animaciones/Sprites/liderfanatico11.png")};
        liderU=new Animation(liderUp,200);
        lider=liderS;
        Image[] fanDere={new Image("Imagenes/Animaciones/Sprites/misterio8.png")};
        fanD=new Animation(fanDere,200);
        Image[] fanIzq={new Image("Imagenes/Animaciones/Sprites/misterio5.png")};
        fanI=new Animation(fanIzq,200);
        Image[] fanUp={new Image("Imagenes/Animaciones/Sprites/misterio11.png")};
        fanU=new Animation(fanUp,200);
        Image[] fan1Down={new Image("Imagenes/Animaciones/Sprites/misterio2.png")};
        fan1A=new Animation(fan1Down,200);
        Image[] fan2Down={new Image("Imagenes/Animaciones/Sprites/misterio2.png")};
        fan2A=new Animation(fan2Down,200);
        Image[] fan3Down={new Image("Imagenes/Animaciones/Sprites/misterio2.png")};
        fan3A=new Animation(fan3Down,200);
        fan1=fanD;
        fan2=fanI;
        fan3=fanU;
        Image[] misUp={new Image("Imagenes/Animaciones/Sprites/misterio10.png"),new Image("Imagenes/Animaciones/Sprites/misterio11.png"),new Image("Imagenes/Animaciones/Sprites/misterio12.png")};
        misU=new Animation(misUp,200);
        Image[] misStop={new Image("Imagenes/Animaciones/Sprites/misterio11.png")};
        misS=new Animation(misStop,200);
        Image[] misDown={new Image("Imagenes/Animaciones/Sprites/misterio1.png"),new Image("Imagenes/Animaciones/Sprites/misterio2.png"),new Image("Imagenes/Animaciones/Sprites/misterio3.png")};
        misD=new Animation(misDown,200);
        mis=misU;
        Image[] narIzquierda={new Image("Imagenes/Animaciones/Sprites/nar4.png"),new Image("Imagenes/Animaciones/Sprites/nar5.png"),new Image("Imagenes/Animaciones/Sprites/nar6.png")};
        narI=new Animation(narIzquierda,200);
        Image[] narEnfrente={new Image("Imagenes/Animaciones/Sprites/nar2.png")};
        narE=new Animation(narEnfrente,200);
        nar=narI;
        Image[] antorchaMove = {new Image("Imagenes/Animaciones/Sprites/antorcha1.png"),new Image("Imagenes/Animaciones/Sprites/antorcha2.png"),new Image("Imagenes/Animaciones/Sprites/antorcha3.png"),new Image("Imagenes/Animaciones/Sprites/antorcha4.png")};
        antM=new Animation(antorchaMove,200);
        ant=antM;
        fondo= new Image("Imagenes/Escenas/EscenaFanatico/fanatico.png");
        /**/
        estado=0;
        this.input = gc.getInput();
        mensajePantalla= new TrueTypeFont(tipoLetra, true);
        posicion = new Vector2f(0,300);
        posicionA = new Vector2f(0,300);
        posicionEn = new Vector2f(0,300);
        ventanaDialogo= new Image("Imagenes/Avatar/cajaMensaje.png");
        avatarLider =  new Image("Imagenes/Avatar/Caras/liderfanatico.png");
        avatarMisterio =  new Image("Imagenes/Avatar/Caras/misterio.png");
        avatarE = new Image ("Imagenes/Personajes/EncapuchadoA.png");
        avatarDialogo = avatarLider;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        texto= new TrueTypeFont(letraMenu, true);
        /**/
        
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
        
            fondo.draw(0,0);
            
            //EDIT:Rener Mordeim
            if(estado>=0){
                lider.draw(posicion.x+224, posicion.y-160);
                fan1.draw(posicion.x+160, posicion.y-80);
                fan2.draw(posicion.x+288, posicion.y-80);
                fan3.draw(posicion.x+224, posicion.y-16);
                ant.draw(posicion.x+64, posicion.y-224);
                ant.draw(posicion.x+384, posicion.y-224);
                ant.draw(posicion.x+64, posicion.y-112);
                ant.draw(posicion.x+384, posicion.y-112);
                ant.draw(posicion.x+64, posicion.y);
                ant.draw(posicion.x+384, posicion.y);
                ant.draw(posicion.x+64, posicion.y+112);
                ant.draw(posicion.x+384, posicion.y+112);
                
                if(estado>1 && estado!=11 && estado!=12 && estado!=14){
                renderDialogo();
                }
                if(estado>=1 && estado<=11){
                    mis.draw(posicionA.x+224, posicionA.y+128);
                }
                if(estado>=14){
                    nar.draw(posicionEn.x+416, posicionEn.y+48);
                }
            }
                
//            texto.drawString(1000, 0, "" + estado);
    }
    @Override
    //Muestra la actualización
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(input.isKeyPressed(Input.KEY_ENTER)){
            if(estado!=24)
            {
                sonidoSelect.play(1, 0.2f);
                time=0;
                estado++;
            }
            
        }
        switch (estado)
        {
            case 0:
                VenganzaBelial.controlMusica.cambiarMusica("Musica/BSO/FanaticBattle.wav");
                time+=i;
                if(time/1000>0.4f)//
                {
                    time=0;
                    estado++;
                }
                break;
            case 1:
                posicionA.y-=0.1f*i;
                if(posicionA.y<=224){
                    estado++;
                }
            case 2:
                mis=misS;
                fan1=fan1A;
                fan2=fan2A;
                fan3=fan3A;
                avatarDialogo=this.avatarMisterio;
                //////="////////////////////////////////////////////////////////";
                linea1="Gran Maestro, los idiotas de Cardinal han llegado a";
                linea2="Troyia tal y como había predicho ústed.";
                linea3="";
                linea4="";
                break;
            case 3:
                avatarDialogo=this.avatarLider;
                //////="////////////////////////////////////////////////////////";
                linea1="Shurororororo, yo nunca me equivoco en mis predicciones";
                linea2="estupido, mis predicciones son infalibles.";
                linea3="";
                linea4="";
                break;
            case 4:
                avatarDialogo=this.avatarMisterio;
                //////="////////////////////////////////////////////////////////";
                linea1="Yo...yo nunca he dudado de sus predicciones Gran Maestro.";
                linea2="Pero, ¿qué hacemos con los de Cardinal?";
                linea3="";
                linea4="";
                break;
            case 5:
                lider=liderU;
                avatarDialogo=this.avatarLider;
                //////="////////////////////////////////////////////////////////";
                linea1="Mmmmmmmmmmmmmmmm, desearía que no se";
                linea2="metieran en mis planes ya que estamos muy cerca";
                linea3="de liberar a nuestro señor Belial.";
                linea4="Quiero que los elimineis inmediatamente.";
                break;
            case 6:
                avatarDialogo=this.avatarMisterio;
                //////="////////////////////////////////////////////////////////";
                linea1="Su voluntad es nuestra voluntad Gran Maestro.";
                linea2="¿Quiere que él se encarge de ellos?";
                linea3="";
                linea4="";
                break;
            case 7:
                lider=liderS;
                avatarDialogo=this.avatarLider;
                //////="////////////////////////////////////////////////////////";
                linea1="Si, él hará un buen trabajo con ellos.";
                linea2="Shurororororo.";
                linea3="";
                linea4="";
                break;
            case 8:
                avatarDialogo=this.avatarMisterio;
                //////="////////////////////////////////////////////////////////";
                linea1="Así se hará Gran Maestro.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 9:
                avatarDialogo=this.avatarLider;
                //////="////////////////////////////////////////////////////////";
                linea1="Ve y no permitas que esos idiotas se metan en nuestros";
                linea2="asuntos, ya que si fallas serás la comida de Caronte.";
                linea3="Shurororororo.";
                linea4="";
                break;
            case 10:
                avatarDialogo=this.avatarMisterio;
                //////="////////////////////////////////////////////////////////";
                linea1="Glup, le prometo que no fallaremos Gran Maestro.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 11:
                mis=misD;
                posicionA.y+=0.1f*i;
                if(posicionA.y>=320){
                    estado++;
                }
            case 12:
                lider=liderU;
                time+=i;
                if(time/1000>0.4f)//
                {
                    time=0;
                    estado++;
                }
                break;
            case 13:
                lider=liderS;
                fan1=fanD;
                fan2=fanI;
                fan3=fanU;
                avatarDialogo=this.avatarLider;
                linea1="Muchachos, debemos liberar a nuestro señor, por lo";
                linea2="que debemos continuar con el ritual.";
                linea3="Shurororororo";
                linea4="Muy pronto será liberado mi señor.";
                break;
            case 14:
                posicionEn.x-=0.1f*i;
                if(posicionEn.x<=(-192)){
                    estado++;
                }
                break;
            case 15:
                nar=narE;
                avatarDialogo=this.avatarE;
                linea1="Y así es como los locos estos intentaban liberar al";
                linea2="demonio Belial de su encarcelamiento.";
                linea3="Y lo peor de todo, ¿cómo he llegado aquí?";
                linea4="";
                break;
            case 16:
                VenganzaBelial.controlMusica.cambiarMusica("Musica/BSO/Catacumbas.wav");
                estado=0;
                VenganzaBelial.atributoGestion.setMapaActual(4);
                sbg.enterState(VenganzaBelial.ESTADOMAPAJUEGO);
//                VenganzaBelial.MapaActual=4;
                
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
        mensajePantalla.drawString(160, 685, linea5);
    }
    
}
