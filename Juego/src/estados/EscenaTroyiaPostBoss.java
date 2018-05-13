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
public class EscenaTroyiaPostBoss extends BasicGameState{
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
    private int eleccionJugador=0;
    private String[] opciones = new String[4];
    private boolean normalEnter=true;
    private Input input;
    private int estado;
    private boolean reproducirExclamacion=false;
    /*Mapa*/
    private Vector2f posicion,posicionE,posicionL,posicionP,posicionX,posicionX1,posicionM;
    private static int esquinaXMapa=-1232;
    private static int esquinaYMapa=-160;
    private static int esquinaXMapa2=0;
    private static int esquinaYMapa2=0;
    /*Animaciones*/
    private SpriteSheet sheetExclamacion;
    private Animation exclamacion;
    private Animation hor,kib,mor,ant;
    private Animation horD,kibD,morD;
    private Animation horS,kibS,morS;
    private Animation horU,kibU,morU;
    private Animation horDown,kibDown,morDown;
    private Image sac;
    private Image lider;
    private Image parca;
    private Image fondo,fondoFanatico;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo, avatarH,avatarM, avatarK;
    private Image avatarSello;
    /*Sonido*/
    private Sound sonidoSelect,sonidoSello;
    int time;//EDIT
    private TrueTypeFont texto;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 15); 
    
    public EscenaTroyiaPostBoss(int id) {
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
        Image[] kibUp={new Image("Imagenes/Animaciones/Sprites/kib11.png")};
        kibU=new Animation(kibUp,200);
        Image[] morUp={new Image("Imagenes/Animaciones/Sprites/mor11.png")};
        morU=new Animation(morUp,200);
        Image[] horUp={new Image("Imagenes/HeroeMundo/her21.png")};
        horU=new Animation(horUp,200);
        Image[] morDown1={new Image("Imagenes/Animaciones/Sprites/mor1.png"),new Image("Imagenes/Animaciones/Sprites/mor2.png"),new Image("Imagenes/Animaciones/Sprites/mor3.png")};
        morDown=new Animation(morDown1,200);
        Image[] kibDown1={new Image("Imagenes/Animaciones/Sprites/kib1.png"),new Image("Imagenes/Animaciones/Sprites/kib2.png"),new Image("Imagenes/Animaciones/Sprites/kib3.png")};
        kibDown=new Animation(kibDown1,200);
        Image[] horDown1={new Image("Imagenes/HeroeMundo/her00.png"),new Image("Imagenes/HeroeMundo/her01.png"),new Image("Imagenes/HeroeMundo/her02.png")};
        horDown=new Animation(horDown1,200);
        hor=horS;
        kib=kibS;
        mor=morS;
        sac=new Image("Imagenes/Animaciones/Sprites/fanatic5.png");
        sac.rotate(-90);
        lider=new Image("Imagenes/Animaciones/Sprites/liderfanatico5.png");
        lider.rotate(90);
        parca=new Image("Imagenes/Animaciones/Sprites/parca13.png");
        Image[] antorchaMove = {new Image("Imagenes/Animaciones/Sprites/antorcha1.png"),new Image("Imagenes/Animaciones/Sprites/antorcha2.png"),new Image("Imagenes/Animaciones/Sprites/antorcha3.png"),new Image("Imagenes/Animaciones/Sprites/antorcha4.png")};
        ant=new Animation(antorchaMove,200);
        fondo= new Image("Imagenes/Escenas/EscenaTroyia/DungeonCatacumbas.png");
        fondoFanatico = new Image("Imagenes/Escenas/EscenaFanatico/fanatico.png");
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
        posicionX = new Vector2f(0,300);
        posicionX1 = new Vector2f(0,300);
        posicionM = new Vector2f(0,300);
        ventanaDialogo= new Image("Imagenes/Avatar/cajaMensaje.png");
        avatarH =  new Image("Imagenes/Personajes/HoraciaA.png");
        avatarM =  new Image("Imagenes/Personajes/MordeimA.png");
        avatarK =  new Image("Imagenes/Personajes/KibitoA.png");
        avatarSello= new Image("Imagenes/Personajes/sello.png");
        avatarDialogo = avatarH;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        sonidoSello= new Sound("Musica/Efectos/selloApagado.wav");
        texto= new TrueTypeFont(letraMenu, true);
        /*Elcciones y menus*/
        opciones[0]="Yo-sama + abdula dorito dorito";
        opciones[1]="Josue Yrion-sama + abdula dorito dorito";
        opciones[2]="Tennosuke-sama + abdula pringles ylays";
        opciones[3]="Ni p*ta idea-sama + abdula dorito dorito";
        /**/
        
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
        
            //EDIT:Rener Mordeim
            if(reproducirExclamacion){
                this.exclamacion.draw(posicion.x-64, posicion.y+176);
            }
            if(estado>=0 && estado<=5){
                fondo.draw(esquinaXMapa,esquinaYMapa);
                hor.draw(posicion.x+256, posicion.y+240);
                mor.draw(posicion.x+256, posicion.y+272);
                kib.draw(posicion.x+256, posicion.y+208);
                
                if(estado>=0 && estado!=5 && estado!=6){
                renderDialogo();
                }
                if(estado<=5){
                    sac.draw(posicionL.x+396, posicionL.y+272);
                    lider.draw(posicionE.x+332, posicionE.y+240);
                    parca.draw(posicionP.x+428, posicionP.y+208);
                }
                
            }
            if(estado>=6){
                    fondoFanatico.draw(esquinaXMapa2,esquinaYMapa2);
                    hor.draw(posicionX.x+224, posicionX.y+144);
                    kib.draw(posicionX.x+192, posicionX.y+144);
                    ant.draw(posicionX1.x+64, posicionX1.y-224);
                    ant.draw(posicionX1.x+384, posicionX1.y-224);
                    ant.draw(posicionX1.x+64, posicionX1.y-112);
                    ant.draw(posicionX1.x+384, posicionX1.y-112);
                    ant.draw(posicionX1.x+64, posicionX1.y);
                    ant.draw(posicionX1.x+384, posicionX1.y);
                    ant.draw(posicionX1.x+64, posicionX1.y+112);
                    ant.draw(posicionX1.x+384, posicionX1.y+112);
                    
                    if(estado!=6 && estado!=9 && estado!=21 && estado!=23){
                        renderDialogo();
                    }
                    if(estado<=21){
                        mor.draw(posicionM.x+256, posicionM.y+144);
                    }
                    if(estado==15)
                        renderOpcionesJugador();
                    
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
                VenganzaBelial.controlMusica.cambiarMusica("Musica/BSO/Catacumbas.wav");
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Uffff, estos tíos eran duros de roer.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 1:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="No puedo creer que lucharamos contra la Muerte.";
                linea2="Tengo que apuntarlo en mi diario.";
                linea3="";
                linea4="";
                break;
            case 2:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Acaso creiaís que no íbamos ha ganar?";
                linea2="Me teneis a mi, con eso es suficiente.";
                linea3="";
                linea4="";
                break;
            case 3:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Creeis que su ritual ya no funciona?";
                linea2="Lo digo porque aunque hallamos derrotado a su lider, una";
                linea3="magia tan poderosa como para resucitar a los muertos";
                linea4="no debe de ser tan fácil de detener.";
                break;
            case 4:
                avatarDialogo=this.avatarH;
                linea1="Creo que deberíamos ir a su base para comprobarlo.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 5:
                posicionL.x-=1f*i;
                posicionE.x-=1f*i;
                posicionP.x-=1f*i;
                posicion.x-=1f*i;
                esquinaXMapa-=1f*i;
                if(esquinaXMapa<=(-3000)){
                    estado++;
                }
                break;
            case 6:
                hor=horU;
                kib=kibU;
                mor=morU;
                posicionX.x+=1f*i;
                posicionX1.x+=1f*i;
                posicionM.x+=1f*i;
                esquinaXMapa2+=1f*i;
                if(esquinaXMapa2>=500){
                    estado++;
                }
                break;
            case 7:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Asi que esta es su base de operaciones.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 8:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Eso de ahí es un sello?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 9:
                hor=horD;
                kib=kibD;
                mor=morD;
                posicionM.y-=0.1f*i;
                posicionX.y-=0.1f*i;
                if(posicionX.y<=128){
                    estado++;
                }
                break;
            case 10:
                hor=horU;
                kib=kibU;
                mor=morU;
                avatarDialogo=this.avatarSello;
                //////="////////////////////////////////////////////////////////";
                linea1="Bienvenidos a SINO SA, una empresa dedicada a la";
                linea2="liberación de nuestro señor Belial.";
                linea3="";
                linea4="";
                break;
            case 11:
                avatarDialogo=this.avatarM;
                linea1="¿Pero que m*erda es esto, más acertijos?";
                linea2="";
                linea3="";
                linea4="";
                break;
                
            case 12:
                avatarDialogo=this.avatarSello;
                //////="////////////////////////////////////////////////////////";
                linea1="Por favor, introduzca la contraseña.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 13:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Contraseña?";
                linea2="Chicos, ¿sabeís alguna contraseña?";
                linea3="";
                linea4="";
                break;
            case 14:
                avatarDialogo=this.avatarSello;
                //////="////////////////////////////////////////////////////////";
                linea1="Si ha olvidado la contraseña, la pista es la siguente:";
                linea2="¿Quién es nuestro mesías?";
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
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Queremos sellar el sello por favor.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 17:
                avatarDialogo=this.avatarSello;
                //////="////////////////////////////////////////////////////////";
                linea1="Afirmativo, sello sellado.";
                linea2="¿Desea alguna transacción Josue?";
                linea3="";
                linea4="";
                break;
            case 18:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Quiero que saques todos nuestros ahorros y";
                linea2="los metas a esta cuenta.";
                linea3="";
                linea4="";
                break;
            case 19:
                avatarDialogo=this.avatarSello;
                //////="////////////////////////////////////////////////////////";
                linea1="Lo sentimos, esta cuenta ha sido requisada.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 20:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Anda y vete por ahi.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 21:
                mor=morDown;
                posicionM.y+=0.1f*i;
                if(posicionM.y>=320){
                    estado++;
                }
                break;
            case 22:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Sera mejor que nos vayamos de este escalofriante";
                linea2="y tétricas ruinas abandonadas.";
                linea3="";
                linea4="";
                break;
            case 23:
                hor=horDown;
                kib=kibDown;
                posicionX.y+=0.1f*i;
                if(posicionX.y>=320){
                    estado++;
                }
                break;
            case 24:
                VenganzaBelial.controlMusica.pararMusica();
                estado=0;
                sbg.enterState(VenganzaBelial.ESCENAARCHI1);//EDIT:
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
                    linea2="MUUUUY despacito: ¿Quién es nuestro mesias?";
                    linea3="";
                    linea4="";
                    break;
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
    
    private void renderOpcionesJugador() throws SlickException
    {
        Font letra = new Font("Verdana", Font.ROMAN_BASELINE, 25);
        TrueTypeFont opcionesJugadorTTF = new TrueTypeFont(letra, true);
        Image fondoDecision= new Image("Imagenes/Avatar/decisionFondo.png");
        fondoDecision.draw(-50, 380, 750, 150);
        for (int i = 0; i < 4; i++) 
        {
            if (eleccionJugador == i) {
                opcionesJugadorTTF.drawString(10, i * 20 + 400, opciones[i]);
            } else {
                opcionesJugadorTTF.drawString(10, i * 20 + 400, opciones[i], new Color(153, 204, 255));
            }
        }
    }
}