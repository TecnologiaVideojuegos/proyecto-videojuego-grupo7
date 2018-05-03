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


public class EscenaMontanaPostBoss extends BasicGameState{
    private int idEstado;
    private static final int POSICIONAVATARX = 30;
    private static final int POSICIONAVATARY = 620;
    private static final int TAMANYOAVATARX = 115;
    private static final int TAMANYOAVATARY = 115;
    //avatarDialogo.draw(30, 610, 115, 125);
    private static final int TILESIZE = 32;
    //Control de Menu y elecciones
    private int eleccionJugador=0;
    private String[] opciones = new String[4];
    private boolean arbolAsesinado=false;
    private boolean normalEnter=true;
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
    private Vector2f posicion,posicionE,posicionL,posicionP;
    private static final int esquinaXMapa=0;
    private static final int esquinaYMapa=0;
    /*Animaciones*/
    private SpriteSheet sheetExclamacion;
    private Animation exclamacion;
    private SpriteSheet sheetAtaque;
    private Animation ataque;
    private Animation hor,kib,mor;
    private Animation horD,kibD,morD;
    private Animation horS,kibS,morS;
    private Image dragon;
    private Image fondo;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo, avatarH,avatarM, avatarK,avatarSello,avatarNarrador;
    /*Sonido*/
    private Sound sonidoSelect,sonidoAtaque,sonidoSello;
    int time;//EDIT
    private TrueTypeFont texto;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 15); 
    
    public EscenaMontanaPostBoss(int id) {
        this.idEstado=id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Image[] horDere={new Image("Imagenes/HeroeMundo/her20.png"),new Image("Imagenes/HeroeMundo/her21.png"),new Image("Imagenes/HeroeMundo/her22.png")};
        horD=new Animation(horDere,200);
        Image[] morDere={new Image("Imagenes/Animaciones/Sprites/mor10.png"),new Image("Imagenes/Animaciones/Sprites/mor11.png"),new Image("Imagenes/Animaciones/Sprites/mor12.png")};
        morD=new Animation(morDere,200);
        Image[] kibDere={new Image("Imagenes/Animaciones/Sprites/kib10.png"),new Image("Imagenes/Animaciones/Sprites/kib11.png"),new Image("Imagenes/Animaciones/Sprites/kib12.png")};
        kibD=new Animation(kibDere,200);
        Image[] kibF={new Image("Imagenes/Animaciones/Sprites/kib8.png")};
        kibS=new Animation(kibF,200);
        Image[] morF={new Image("Imagenes/Animaciones/Sprites/mor8.png")};
        morS=new Animation(morF,200);
        Image[] horEnfrente={new Image("Imagenes/HeroeMundo/her11.png")};
        horS=new Animation(horEnfrente,200);
        hor=horS;
        kib=kibS;
        mor=morS;
        dragon = new Image("Imagenes/Animaciones/Sprites/dragon1.png");
        fondo= new Image("Imagenes/Escenas/EscenaMontana/Montana.png");
        /**/
        this.sheetExclamacion= new SpriteSheet("Imagenes/Animaciones/exclamacion.png",32,33);
        this.exclamacion = new Animation(sheetExclamacion,200);
        this.sheetAtaque= new SpriteSheet("Imagenes/Animaciones/Combate/ataque1.png",190,181);
        this.ataque = new Animation(sheetAtaque,200);
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
        avatarNarrador =  new Image("Imagenes/Personajes/EncapuchadoA.png");
        avatarSello= new Image("Imagenes/Personajes/sello.png");
        avatarDialogo = avatarH;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        sonidoAtaque=new Sound("Musica/Efectos/Sword4.wav");
        sonidoSello= new Sound("Musica/Efectos/selloApagado.wav");
        texto= new TrueTypeFont(letraMenu, true);
        /*Elcciones y menus*/
        opciones[0]="Acaba en tragedia + Abdula dorito dorito";
        opciones[1]="Comienza con calor y termina con frío + Abdula dorito dorito";
        opciones[2]="Acaba con un frio del carajo + Abdula pringles ylays";
        opciones[3]="Comienza el partido + Abdula dorito dorito";
        /**/
        
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
        
            fondo.draw(-1200, -1100);
            
            //EDIT:Rener Mordeim
            if(reproducirExclamacion){
                this.exclamacion.draw(posicion.x-64, posicion.y+176);
            }
            
            if(this.reproducirAtaque)
            this.ataque.draw(posicion.x+916, posicion.y+152);
            
            if(estado>=0){
                hor.draw(posicion.x+932, posicion.y+240);
                mor.draw(posicion.x+900, posicion.y+272);
                kib.draw(posicion.x+900, posicion.y+208);
                dragon.draw(posicionE.x+964, posicionE.y+200);
                
                if(estado==15)
                    renderOpcionesJugador();
                
                if(estado>=0 && estado!=2 && estado!=11){
                renderDialogo();
                }
                
            }
//            texto.drawString(1000, 0, "" + estado);
    }
    @Override
    //Muestra la actualización
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        exclamacion.update(i);
        if(normalEnter)
        {
            if(input.isKeyPressed(Input.KEY_ENTER)){
                sonidoSelect.play(1, 0.2f);
                time=0;
                estado++;
            }//
            
        }
        switch (estado)
        {
            case 0:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Ya está muerto el bicharraco?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 1:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Por si acaso...";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 2:
                this.reproducirAtaque=true;
                time+=i;
                if(!sonidoAtaque.playing())
                {
                    sonidoAtaque.play();
                }
                if(time/1000>0.8f)//
                {
                    time=0;
                    estado++;
                }
                break;
            case 3:
                this.reproducirAtaque=false;
                avatarDialogo=this.avatarK;
                linea1="Primero la Muerte y ahora un Dragón, esto";
                linea2="no acaba nunca.";
                linea3="";
                linea4="";
                break;
            case 4:
                avatarDialogo=this.avatarH;
                linea1="No puedo creer que existiera todavía un dragón";
                linea2="en el mundo.";
                linea3="¿Cómo creeis que se llamaría?";
                linea4="";
                break;
            case 5:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Fafnir.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 6:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Pero, ¿cómo diablos sabes como se llamaba este bicho?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 7:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Lo pone en el collarín.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 8:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="...";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 9:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="En fin, vayamos a por el sello.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 10:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="¿No es eso de hay?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 11:
                hor=horD;
                kib=kibD;
                mor=morD;
                posicion.y-=0.1f*i;
                if(posicion.y<=128){
                    estado++;
                }
                break;
            case 12:
                hor=horS;
                kib=kibS;
                mor=morS;
                avatarDialogo=this.avatarSello;
                //////="////////////////////////////////////////////////////////";
                linea1="El matrimonio...";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 13:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Más acertijos no.";
                linea2="¿Quién es el culpable de ponerles acertijos";
                linea3="a los sellos?";
                linea4="";
                break;
            case 14:
                avatarDialogo=this.avatarSello;
                //////="////////////////////////////////////////////////////////";
                linea1="El matrimonio, al contrario que la fiebre...";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 15:
                normalEnter=false;
                resuelveAcertijo();
                //EDIT:Reproducir algun sonido de apagado
                if(linea1.equals("Afirmativo, protocolo Abdula Dorito Dorito activado."))
                {
                    if(!sonidoSello.playing())
                        sonidoSello.play();
                    time+=i;
                    if(time/1000>2f)//
                    {
                        time=0;
                        estado++;
                    }
                }
                break;
            case 16:
                normalEnter=true;
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Guau, eso ha sido muy duro.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 17:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Al menos hemos terminado con los sellos.";
                linea2="Pero, ¿dónde está el último Kibito?";
                linea3="";
                linea4="";
                break;
            case 18:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="En Cardinal.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 19:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="¿¡ME ESTÁS VACILANDO!?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 20:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¡¿El último sello esta en Cardinal?!";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 21:
                avatarDialogo=this.avatarNarrador;
                //////="////////////////////////////////////////////////////////";
                linea1="Y así nuestros penosos aventureros tenían que";
                linea2="regresar a Cardinal para cumplir su misión.";
                linea3="";
                linea4="";
                break;
            case 22:
                estado=0;
                sbg.enterState(VenganzaBelial.ESCENAARCHI2);//EDIT:
                //Deberiamos entrar en estado Combate contra el Dragon
                break;

        }
    }
    
    private void resuelveAcertijo(){
        if (input.isKeyPressed(Input.KEY_DOWN)) 
        {
            if (eleccionJugador == (4 - 1)) 
            {
                eleccionJugador = 0;
            } 
            else 
            {
                eleccionJugador++;
            }
        }
        if (input.isKeyPressed(Input.KEY_UP)) 
        {
            if (eleccionJugador == 0) {
                eleccionJugador = 4 - 1;
            } else {
                eleccionJugador--;
            }
        }
        if(input.isKeyPressed(Input.KEY_ENTER))
        {
            switch(eleccionJugador)
            {
                case 1:
                    //////="////////////////////////////////////////////////////////";
                    linea1="Afirmativo, protocolo Abdula Dorito Dorito activado.";
                    linea2="";
                    linea3="";
                    linea4="";
                    break;
                default:
                    //////="////////////////////////////////////////////////////////";
                    linea1="No creia que existiera alguien como tu...lo repetire";
                    linea2="MUUUUY despacito: El matrimonio, al contrario que";
                    linea3="la fiebre...";
                    linea4="";
                    break;
            }
        }
    }
    
    private void renderOpcionesJugador()
    {
        Font letra = new Font("Verdana", Font.ROMAN_BASELINE, 25);
        TrueTypeFont opcionesJugadorTTF = new TrueTypeFont(letra, true);
        for (int i = 0; i < 4; i++) 
        {
            if (eleccionJugador == i) {
                opcionesJugadorTTF.drawString(10, i * 20 + 400, opciones[i]);
            } else {
                opcionesJugadorTTF.drawString(10, i * 20 + 400, opciones[i], new Color(211,84,0));
            }
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