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
public class EscenaBosquePostBoss extends BasicGameState{
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
    private boolean reproducirPuntos=false;
    private boolean reproducirExplosion=false;
    /*Mapa*/
    private Vector2f posicion,posicionE, posicionMordeim;
    private static final int esquinaXMapa=0;
    private static final int esquinaYMapa=0;
    /*Animaciones*/
    private SpriteSheet sheetPuntos;
    private Animation puntos;
    private SpriteSheet sheetExclamacion;
    private Animation exclamacion;
    private SpriteSheet sheetAtaque;
    private Animation ataque;
    private Animation explosion;
    
    private SpriteSheet sheetHablar;
    private Animation hablar;
    
    private Animation hor,kib,mor;
    private Animation horD,kibD,morD;//Movimiento a Derecha
    private Animation horDQ,kibDQ,morDQ;//DerechaQuietos
    private Animation horIQ;//IsquierdaQuieta
    private Animation kibAQ;//AbajoQuieto
    private Animation horEQ;//EspaldaQuieta
    private Image fondo;
    /*Imagenes*/
    private Image ventanaDialogo,avatarDialogo, avatarH,avatarM, avatarK, avatarDesconocido, avatarSello, narrador, fuego, arbolBoss;
    /*Sonido*/
    private Sound sonidoSelect,sonidoAtaque, sonidoSello;
    private Music battle;
    int time=0;//EDIT
    private TrueTypeFont texto;
    private Font letraMenu  = new Font("Arial Black", Font.PLAIN, 15); 
    
    public EscenaBosquePostBoss(int id) {
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
        horIQ=new Animation(horIzq,200);
        Image[] kibF={new Image("Imagenes/Animaciones/Sprites/kib8.png")};
        kibDQ=new Animation(kibF,200);
        Image[] morF={new Image("Imagenes/Animaciones/Sprites/mor8.png")};
        morDQ=new Animation(morF,200);
        Image[] kibAbajo={new Image("Imagenes/Animaciones/Sprites/kib2.png")};
        kibAQ=new Animation(kibAbajo,200);
        Image[] horaciaEspalda={new Image("Imagenes/HeroeMundo/her21.png")};
        horEQ=new Animation(horIzq,200);
        hor=horEQ;
        kib=kibAQ;
        mor=morDQ;
        
        fondo= new Image("Imagenes/Escenas/EscenaBosque1/mapaBosque.png");
        /**/
        this.sheetExclamacion= new SpriteSheet("Imagenes/Animaciones/exclamacion.png",32,33);
        this.exclamacion = new Animation(sheetExclamacion,200);
        this.sheetPuntos= new SpriteSheet("Imagenes/Animaciones/puntos.png",32,33);
        this.puntos = new Animation(sheetPuntos,200);
        this.sheetAtaque= new SpriteSheet("Imagenes/Animaciones/Combate/ataque1.png",190,181);
        this.ataque = new Animation(sheetAtaque,200);
        this.sheetHablar= new SpriteSheet("Imagenes/Animaciones/hablar.png",32,33);
        this.hablar = new Animation(sheetHablar,200);
        Image[] explo ={new Image("Imagenes/Animaciones/Combate/ex1.png"),new Image("Imagenes/Animaciones/Combate/ex2.png"),new Image("Imagenes/Animaciones/Combate/ex3.png"),new Image("Imagenes/Animaciones/Combate/ex4.png"),new Image("Imagenes/Animaciones/Combate/ex5.png"),new Image("Imagenes/Animaciones/Combate/ex6.png"),new Image("Imagenes/Animaciones/Combate/ex7.png"),new Image("Imagenes/Animaciones/Combate/ex8.png"),new Image("Imagenes/Animaciones/Combate/ex9.png")};
        explosion = new Animation(explo,200);
        /**/
        estado=0;
        this.input = gc.getInput();
        mensajePantalla= new TrueTypeFont(tipoLetra, true);
        /*Posiciones*/
        posicion = new Vector2f(800,600);
        posicionE = new Vector2f(800,650);
        posicionMordeim= new Vector2f(700,600);
        /**/
        ventanaDialogo= new Image("Imagenes/Avatar/cajaMensaje.png");
        avatarH =  new Image("Imagenes/Personajes/HoraciaA.png");
        avatarM =  new Image("Imagenes/Personajes/MordeimA.png");
        avatarK =  new Image("Imagenes/Personajes/KibitoA.png");
        avatarDesconocido = new Image("Imagenes/Personajes/Arbol.png");
        avatarSello= new Image("Imagenes/Personajes/sello.png");
        narrador= new Image ("Imagenes/Personajes/EncapuchadoA.png");
        arbolBoss= new Image("Imagenes/Animaciones/Sprites/arbolbueno5.png");
        arbolBoss.rotate(90);
        fuego= new Image("Imagenes/Escenas/Fire.png");
        avatarDialogo = avatarH;
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        sonidoAtaque=new Sound("Musica/Efectos/Sword4.wav");
        sonidoSello= new Sound("Musica/Efectos/selloApagado.wav");
        
        texto= new TrueTypeFont(letraMenu, true);
        /*Elcciones y menus*/
        opciones[0]="Patada en los cojines + Abdula dorito dorito";
        opciones[1]="Hace un frio del carajo + Abdula dorito dorito";
        opciones[2]="Hace un frio del carajo + Abdula pringles ylays";
        opciones[3]="¿Que mierda es esto?";
    }

    @Override
    //Muestra por pantalla
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { 
        fondo.draw(-1800, -1184);
        if(posicionMordeim.x<1000)
            mor.draw(posicionMordeim.x, posicionMordeim.y);
        if(posicion.x+50<1000)
            hor.draw(posicion.x+50, posicion.y);
        if(posicion.x<1000)
            kib.draw(posicion.x, posicion.y-50);
        
        arbolBoss.draw(762, 600);
        if(estado==18)
            hablar.draw(780, 600);
        
        if(estado==15)
            renderOpcionesJugador();
        if(estado==23)
            renderDecisionJugador();
            
        if(this.reproducirAtaque)
            this.ataque.draw(posicionMordeim.x, posicionMordeim.y-80);
        if(this.reproducirPuntos)
            this.puntos.draw(posicionMordeim.x+5, posicionMordeim.y-32);
        if(this.reproducirExclamacion){
            this.exclamacion.draw(posicionMordeim.x+5, posicionMordeim.y-32);
            this.exclamacion.draw(posicion.x+55, posicion.y-32);
            this.exclamacion.draw(posicion.x+5, posicion.y-32-50);
        }
        if(this.reproducirExplosion){
            this.explosion.draw(400, 400);
        }
        if(estado==6){
            Rectangle rect= new Rectangle(400,200,200,200);
            grphcs.draw(rect);
            grphcs.fill(rect);
            avatarSello.draw(400, 200,200,200);
        }
        if(estado>=28 && arbolAsesinado==true){
                fuego.draw(0,0,VenganzaBelial.WIDTH,VenganzaBelial.HEIGHT);
            }
        
        this.renderDialogo();
        
        texto.drawString(1000, 0, "" + estado);
        texto.drawString(1000, 50, "" + eleccionJugador);
        texto.drawString(1000, 100, "Arbolmuerto" + arbolAsesinado);
        
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
        }/*if(normalEnter)*/
        switch (estado)
        {
            case 0:
                    time=0;
                    estado++;
                break;
            case 1:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Esta...muerto...por fin?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 2:
                this.reproducirPuntos=true;
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="...";
                linea2="";
                linea3="";
                linea4="";
                time+=i;
                if(time/1000>0.7f)//
                {
                    time=0;
                    estado++;
                }
                break;
            case 3:
                this.reproducirPuntos=false;
                mor=this.morD;
                posicionMordeim.x+=0.05f*i;
                if(posicionMordeim.x>=150){
                    estado++;
                }
                break;
            case 4:
                mor=this.morDQ;
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
            case 5:
                reproducirAtaque=false;
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Por si acaso...";
                linea2="Ey, ¿Que es eso de ahí?";
                linea3="";
                linea4="";
                break;
            case 6:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="¡¡!!";
                linea2="Es uno de los sellos de Luci, ¿Pero que esta haciendo";
                linea3="aquí?";
                linea4="";
                break;
            case 7:
               avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="El sello estaba cerca de Tambler en algún punto sin";
                linea2="especificar, supongo que el bosque era el punto.";
                linea3="Como sea sugiero reparar el sello y largarnos de ";
                linea4="este poco agradable bosque antes de que algo más";
                break;
            case 8:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="intente matarnos.";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 9:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Coincido con el intelectual...¿Como lo reparamos?";
                linea2="";
                linea3="";
                linea4="";
                break;
            case 10:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Eso...lo ponía por aquí...ah, aquí esta, veamos:acercar ";
                linea2="la esfera al sello, contestar a la contraseña, luego";
                linea3="utilizar las palabras ¨abdula dorito dorito¨ ";
                linea4="";
                break;
            case 11:
                avatarDialogo=this.avatarM;
                linea1="Tu padre, por si acaso.";
                linea2="";
                linea3="";
                linea4="";
                break;
                
            case 12:
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="Ignoremos las impertinencias y vamos a ello, acercamos";
                linea2="la llave y..";
                linea3="";
                linea4="";
                break;
            case 13:
                avatarDialogo=avatarSello;
                //////="////////////////////////////////////////////////////////";
                linea1="(Al acercar la llave una voz de ultratumba pronuncia";
                linea2="unas palabras al aire)";
                linea3="";
                linea4="";
                break;
            case 14:
                linea1="Cuando el grajo vuela bajo...";
                linea2="";
                linea3="";
                linea4="";
                time=0;
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
                linea1="¿Ya está? ¿Con eso esta arreglado?";
                linea2="Me esperaba algo más...épico, místico o...intelectual";
                linea3="que un refran medio acabar para reparar un sello ";
                linea4="legendario.";
                break;
            case 17:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Lo legendario esta sobrevalorado, como la utilidad de";
                linea2="Horacia en este equipo...¿Nos vamos?";
                linea3=" ";
                linea4="";
                break;
            case 18:
                //Arbol moviendose
                break;
            case 19:
                this.reproducirExclamacion=true;
                time+=i;
                if(time/1000>0.6f)//
                {
                    time=0;
                    estado++;
                }
                break;
            case 20:
                this.reproducirExclamacion=false;
                avatarDialogo=this.avatarK;
                //////="////////////////////////////////////////////////////////";
                linea1="¿Todavía esta vivo?";
                linea2="Mordeim no te confiaré acabar con los enemigos nunca más.";
                linea3=" ";
                linea4="";
                break;
            case 21:
                avatarDialogo=this.avatarM;
                //////="////////////////////////////////////////////////////////";
                linea1="Sutilmente te mandaré a la ****** y diré que no soy";
                linea2="herbologo para saber cuando una planta esta muerta.";
                linea3="Como sea, hora de seguir re-partiendo leña. ";//EDIT
                linea4="";
                break;
            case 22:
                avatarDialogo=this.avatarH;
                //////="////////////////////////////////////////////////////////";
                linea1="Esperad, parece algo...diferente a antes, ya no parece..";
                linea2="cabreado con el mundo.";
                linea3="";
                linea4="";
                opciones[0]="No pienso darle la oportunidad de atacarme de nuevo.";
                opciones[1]="(Esperar a ver que pasa)";
                eleccionJugador=0;
                break;
            case 23:
                normalEnter=false;
                tomaDecision();
                break;
            case 24:
                normalEnter=true;
                if(arbolAsesinado)
                {
                    mor=morD;
                    avatarDialogo=this.avatarM;
                    //////="////////////////////////////////////////////////////////";
                    linea1="Quedate tu a averiguarlo.";
                    linea2="";
                    linea3="";
                    linea4="";
                    posicionMordeim.x+=0.1f*i;
                    posicionMordeim.y+=0.05f*i;
                    if(posicion.x>=900){
                        estado++;
                    }
                }
                else{
                   avatarDialogo=this.avatarM;
                    //////="////////////////////////////////////////////////////////";
                    linea1="Te hago caso capi, pero como mate a Kibito te mato.";
                    linea2="";
                    linea3="";
                    linea4=""; 
                }
                
                break;
            case 25:
                if(arbolAsesinado)
                {
                    avatarDialogo=this.avatarH;
                    //////="////////////////////////////////////////////////////////";
                    linea1="¡¿Mordeim donde vas?!";
                    linea2="";
                    linea3="";
                    linea4="";
                }
                else{
                   avatarDialogo=this.avatarK;
                    //////="////////////////////////////////////////////////////////";
                    linea1="Espera, ¿Porque tengo que ser yo el que muera si Horacia";
                    linea2="la caga con sus decisiones?";
                    linea3="";
                    linea4=""; 
                }
                break;
            case 26:
                if(arbolAsesinado)
                {
                    avatarDialogo=this.avatarK;
                    //////="////////////////////////////////////////////////////////";
                    linea1="...¿Eso del suelo no son...?";
                    linea2="¡BOMBAS! maldito piromano. ";
                    linea3="";
                    linea4="";
                }
                else{
                    this.arbolBoss=new Image("Imagenes/Animaciones/Sprites/arbolbueno4.png");
                   avatarDialogo=this.avatarDesconocido;
                    //////="////////////////////////////////////////////////////////";
                    linea1="Uuughh, ¿Que ha pasado? ¿donde estoy? ¿Quienes soys?";
                    linea2="";
                    linea3="";
                    linea4=""; 
                }
                break;
            case 27:
                if(arbolAsesinado)
                {
                    kib=kibD;
                    hor=horD;
                    avatarDialogo=this.avatarH;
                    //////="////////////////////////////////////////////////////////";
                    linea1="¡Mierda siempre igual!";
                    linea2="";
                    linea3="";
                    linea4="";
                    posicion.x+=0.1f*i;
                    if(posicion.x>=1000)
                        estado++;
                }
                else{
                   avatarDialogo=this.avatarH;
                    //////="////////////////////////////////////////////////////////";
                    linea1="(Parece que se ha calmado)";
                    linea2="Esto...somos miembros de Cardinal en misión.";
                    linea3="";
                    linea4=""; 
                }
                time=0;
                break;
            case 28:
                if(arbolAsesinado)
                {
                    reproducirExplosion=true;
                    time+=i;
                    if(time/1000>1f)
                    {
                        time=0;
                        estado++;
                    }
                }
                else{
                   avatarDialogo=this.avatarDesconocido;
                    //////="////////////////////////////////////////////////////////";
                    linea1="¿Cardinal?, yo ayude a fundar Cardinal, lo recuerdo ";
                    linea2="como si fuese ayer, los heroes antiguos y yo luchando";
                    linea3="contra las horadas demoniacas y ...";
                    linea4=""; 
                }
                break;
            case 29:
                if(arbolAsesinado)
                {
                    reproducirExplosion=false;
                    avatarDialogo=this.narrador;
                    //////="////////////////////////////////////////////////////////";
                    linea1="Y así, nuestros poco heroicos amigos, habían dado el";
                    linea2="paso en su aventura, ¿Habrían tomado las decisiones";
                    linea3="correctas? Solo el tiempo lo diría...";
                    linea4="Pero sí sabemos unas cosa";
                }
                else{
                   avatarDialogo=this.avatarM;
                    //////="////////////////////////////////////////////////////////";
                    linea1="Oh, mierda el jefe de zona se ha convertido en un viejo";
                    linea2="cuenta batallitas.";
                    linea3="";
                    linea4=""; 
                }
                break;
            case 30:
                if(arbolAsesinado)
                {
                    avatarDialogo=this.narrador;
                    //////="////////////////////////////////////////////////////////";
                    linea1="Desde ahora hay un bosque menos en este mundo...";
                    linea2="";
                    linea3="";
                    linea4="";
                }
                else{
                   avatarDialogo=this.avatarDesconocido;
                    //////="////////////////////////////////////////////////////////";
                    linea1="Y entonces descubrimos el gran temos de Belial: los";
                    linea2="pimientos verdes. Si, fue sin duda uno de los hallazgos";
                    linea3="más importantes durante la guerra junto con muchos otros";
                    linea4="como ...."; 
                }
                break;
            case 31:
                if(arbolAsesinado){
                    sbg.enterState(VenganzaBelial.ESCENAPUERTO1);
                }
                else{
                   avatarDialogo=this.avatarM;
                    //////="////////////////////////////////////////////////////////";
                    linea1="Hubiera preferido la muerte de Kibito a aguantar este ";
                    linea2="tostón. Me largo.";
                    linea3="";
                    linea4=""; 
                    mor=morD;
                    posicionMordeim.x+=0.1f*i;
                    posicionMordeim.y+=0.05f*i;
                    if(posicion.x>=900){
                        estado++;
                    }
                }
                break;
            case 32:
                   avatarDialogo=this.avatarK;
                    //////="////////////////////////////////////////////////////////";
                    linea1="¿Estas segura de dejar solo a ese?";
                    linea2="Su seguridad realmente me preocupa poco, por otra parte";
                    linea3="su historial de trifulcas en ciudades es...bastante largo";
                    linea4=""; 
                break;
            case 33:
                   avatarDialogo=this.avatarH;
                    //////="////////////////////////////////////////////////////////";
                    linea1="Oh no, Archi me a echar la bronca si hay otra de esas.";
                    linea2="";
                    linea3="";
                    linea4=""; 
                break;
            case 34:
                    kib=kibD;
                    hor=horD;
                    posicion.x+=0.1f*i;
                    if(posicion.x>=1000){
                        estado++;
                    }
                break;
            case 35:
                    avatarDialogo=this.avatarDesconocido;
                    //////="////////////////////////////////////////////////////////";
                    linea1="Y entonces el nigromante cojo utilizo su pie para...";
                    linea2="¿Chicos?...¿A donde han ido?";
                    linea3="¿Mm? ¿Que hace el sello tan al borde del bosque?";
                    linea4=""; 
                break;
            case 36:
                    avatarDialogo=this.avatarDesconocido;
                    //////="////////////////////////////////////////////////////////";
                    linea1="¡¡!!";
                    linea2="¡¿Pero que puñetas?!";
                    linea3="¡Vostros volved aquí!";
                    linea4="Ay mierda, estoy muy viejo para correr."; 
                break;
            case 37:
                    avatarDialogo=this.narrador;
                    //////="////////////////////////////////////////////////////////";
                    linea1="Y así, nuestros poco heroicos amigos, habían dado el";
                    linea2="paso en su aventura, ¿Habrían tomado las decisiones";
                    linea3="correctas? Solo el tiempo lo diría...";
                    linea4="Pero sí sabemos unas cosa";
                break;
            case 38:
                    avatarDialogo=this.narrador;
                    //////="////////////////////////////////////////////////////////";
                    linea1="Este anciano Dios del Bosque vivirá para contar sus";
                    linea2="batallitas un día más.";
                    linea3="";
                    linea4="";
                break;
            case 39:
                    sbg.enterState(VenganzaBelial.ESCENAPUERTO1);
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
                    linea2="MUUUUY despacito: Cuando el grajo vuela bajo...";
                    linea3="";
                    linea4="";
                    break;
            }
        }
    }
    
    private void tomaDecision()
    {
        
        if (input.isKeyPressed(Input.KEY_DOWN)) 
        {
            if (eleccionJugador == (2 - 1)) 
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
                eleccionJugador = 2 - 1;
            } else {
                eleccionJugador--;
            }
        }
        if(input.isKeyPressed(Input.KEY_ENTER))
        {
            switch(eleccionJugador){
                case 0:
                    arbolAsesinado=true;
                    setArbolAsesinado(true);
                    break;
                case 1:
                    arbolAsesinado=false;
                    setArbolAsesinado(false);
                    break;
            }
            estado++;
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
                opcionesJugadorTTF.drawString(10, i * 20 + 400, opciones[i], new Color(153, 204, 255));
            }
        }
    }/*private void renderOpcionesJugador()*/
    
    private void renderDecisionJugador()
    {
        Font letra = new Font("Verdana", Font.ROMAN_BASELINE, 25);
        TrueTypeFont opcionesJugadorTTF = new TrueTypeFont(letra, true);
        for (int i = 0; i < 2; i++) 
        {
            if (eleccionJugador == i) {
                opcionesJugadorTTF.drawString(10, i * 20 + 400, opciones[i]);
            } else {
                opcionesJugadorTTF.drawString(10, i * 20 + 400, opciones[i], new Color(153, 204, 255));
            }
        }
    }/*private void renderOpcionesJugador()*/
    
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

    public boolean isArbolAsesinado() {
        return arbolAsesinado;
    }

    public void setArbolAsesinado(boolean arbolAsesinado) {
        this.arbolAsesinado = arbolAsesinado;
    }
    
    
    
    
}