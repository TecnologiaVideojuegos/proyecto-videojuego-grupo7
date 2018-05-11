package estados;

import enemigos.Enemigo;
import static estados.VenganzaBelial.ESTADOMENUINICIO;
import items.Consumible;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.*;
import personajes.*;
import otros.Combate;
import otros.Habilidad;

public class EstadoCombate extends BasicGameState{
    /*Estados Inercombate*/
    private Input input;
    private int Estado=0;
    private static final int NESTADOS=10;
    private static final int OPCIONESBASE=0;//Opciones base del pj
    private static final int ATACANDO=1;//Seleccion del enemigo al que atacar
    private static final int SELHABILIDAD=2;//Seleccion de habilidad en funcion del pj
    private static final int SELCONSUMIBLE=3;//Seleccion de consumible a usar
    private static final int HUYENDO=4;//Intento de huida
    private static final int TURNOENEMIGO=5;//Turno automatico del enemigo
    private static final int SELOBJETIVO=6;//Selesccion de enemigo al que tirar habilidad
    private static final int SELALIADO=7;//Seleccion de pj al que tirar habilidad
    private static final int FINTURNO=8;
    private static final int FINCOMBATE=9;
    /*Opciones OPCIONESBASE*/
    private static final int NUMOPCIONESBASE = 4;
    private static final int ATACAR = 0;
    private static final int HABILIDAD = 1;
    private static final int CONSUMIBLE = 2;
    private static final int HUIR = 3;
    /*opciones SELHABILIDAD*/
    private static final int NHABILIDADES=5;
    private String[] listaHabilidades= new String[NHABILIDADES];
    /*opciones SELCONSUMIBLE*/
    private static final int NCONSUMIBLES=3;
    /*Tipos de letra y Mensajes*/
    private String[] opciones = new String[NUMOPCIONESBASE];
    private Font letraMenu;
    private TrueTypeFont opcionesJugadorTTF;
    private int eleccionJugador, idEstado, habilidadSeleccionada,consumibleSeleccionado, estadoAnterior;
    private Color notChosen = new Color(153, 204, 255);
    private boolean NuevoCombate = true;
    /*Control de Combate*/
    //private ArrayList<Personaje> ordenPersonajes = new ArrayList<Personaje>();7
    private Combate NewCombate;
    /*Avatar Image Control*/
    private Image fondo;
    private Image contenedor,hp, mp,avatarVivo, avatarMuerto,avatarSeleccionado,marco,marcoL, Avatar1, Avatar2, Avatar3;
    private TrueTypeFont mensajePantalla;
    private Font TipoLetra  =new Font("Verdana", Font.PLAIN, 15);    
    private Color rojo = new Color (256,0,0);
    private Color verde = new Color (0,256,0);
    private Color azul = new Color (0,0,256);    
    private Music OST;
    private Sound sonidoAtaque,sonidoSelect, sonidoError, sonidoMuerto;
    private String mensajeSistema= "";
    //Animaciones combate
    private Vector2f posicionSkill;
    private boolean renderSkill=false;
     private boolean renderAE=false;//Ataque Enemigo
      private boolean renderAA=false;//AtaqueAliado
    private Image skill;
    private Animation ataqueEnemigo;
    private Animation ataqueAliado;
    int time=0;
    
    //
    private boolean flagHuida=false;
    private float tasaHuida=0.4f;
    private Image marcoOpciones;
    
    public EstadoCombate(int id) {
        idEstado = id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
    {
        marcoOpciones= new Image("Imagenes/Avatar/marcoOpciones.png");
        /*ost*/
        OST= new Music("Musica/Efectos/Sword4.wav");
        sonidoAtaque= new Sound("Musica/Efectos/Sword4.wav");
        sonidoMuerto= new Sound("Musica/Efectos/Cry2.wav");
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        sonidoError=new Sound("Musica/Efectos/error.wav");
        /*Input*/
        this.input = gc.getInput();
        /**/
        mensajePantalla = new TrueTypeFont(TipoLetra, true);
        letraMenu = new Font("Verdana", Font.ROMAN_BASELINE, 25);
        opcionesJugadorTTF = new TrueTypeFont(letraMenu, true);
        opciones[0] = "Atacar";
        opciones[1] = "Habilidad";
        opciones[2] = "Consumible";
        opciones[3]= "Huir";
        /**/
        marco= new Image("Imagenes/Avatar/marco.png");
        marcoL=new Image("Imagenes/Avatar/marcoD.png");
        contenedor=new Image("Imagenes/Avatar/Contenedor.png");
        hp=new Image("Imagenes/Avatar/Hp.png");
        mp=new Image("Imagenes/Avatar/Mp.png");
        avatarVivo=new Image("Imagenes/Avatar/fondoVivo.png");
        avatarMuerto=new Image("Imagenes/Avatar/fondoMuerto.png");
        avatarSeleccionado=new Image("Imagenes/Avatar/fondoSeleccion.png");
        /*EDIT:Test*/
        Avatar1 =  new Image("Imagenes/Personajes/HoraciaA.png");
        Avatar2 =  new Image("Imagenes/Personajes/MordeimA.png");
        Avatar3 =  new Image("Imagenes/Personajes/KibitoA.png");
        //Animaciones
        SpriteSheet sheetEnemigo= new SpriteSheet("Imagenes/Animaciones/Combate/ataqueEnemigo.png",180,192);//EDIT
        SpriteSheet sheetAliado= new SpriteSheet("Imagenes/Animaciones/Combate/ataqueAliado.png",190,183);//EDIT
        this.ataqueAliado= new Animation(sheetAliado,200);
        this.ataqueEnemigo= new Animation(sheetEnemigo,200);
        posicionSkill= new Vector2f(0,0);
        //this.sprite= new SpriteSheet("Imagenes/Animaciones/ataque.png",200,200);
        //this.animacion= new Animation(sprite, 200);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
    {
        renderEnemigos();
        renderAvatars();
        
        /*Switch Case para visualizar opciones en funcion del estado*/
        switch (Estado)
            
        {
            case OPCIONESBASE:
                marcoOpciones.draw(0, 385, 280, 220);
                renderOpcionesJugador();
                break;
            case SELHABILIDAD:
                marcoOpciones.draw(0, 385, 280, 220);
                renderHabilidades();
                break;
            case SELOBJETIVO :
                marcoOpciones.draw(0, 385, 280, 220);
                renderSelObjetivo();
                break;
            case ATACANDO:
                marcoOpciones.draw(0, 385, 280, 220);
                renderSelObjetivo();
                //this.animacion.draw(750, 200, 200, 200);
                break;
            case SELCONSUMIBLE:
                marcoOpciones.draw(0, 385, 280, 220);
                renderObjetos();
                break;
            case SELALIADO:
                marcoOpciones.draw(0, 385, 280, 220);
                renderSelAliados();
                break;
            case FINTURNO:
                this.renderSkill();
                renderMensajeSistema();
                break;
            case FINCOMBATE:
                renderMensajeSistema();
                break;
        }
        //Animaciones combate
        this.renderAtaqueAliado();
        this.renderAtaqueEnemigo();
//        this.renderSkill();
        
        //Debug Prints
//        mensajePantalla.drawString(0, 80, "Turno "+NewCombate.getTurno());
//        mensajePantalla.drawString(0, 100, "Enemigos "+NewCombate.getEnemigosRestantes());
//        mensajePantalla.drawString(0, 120, "Aliados "+NewCombate.getAliadosRestantes());
//        mensajePantalla.drawString(0, 140, "Participantes "+NewCombate.getnParticipantes());
//        mensajePantalla.drawString(0, 160, "Estado "+this.Estado);
//        mensajePantalla.drawString(0, 180, "eleccionJugador "+this.eleccionJugador);
//        mensajePantalla.drawString(0, 200, "CombateOver "+NewCombate.CombateAcabado());
        for (int i = 0; i < NewCombate.getOrdenPersonajes().size(); i++) {
            if(NewCombate.getTurno()==i)
                mensajePantalla.drawString(1100, 20*i, " "+NewCombate.getOrdenPersonajes().get(i).getNombre(),new Color(100,255,100)); 
            else    
                mensajePantalla.drawString(1100, 20*i, " "+NewCombate.getOrdenPersonajes().get(i).getNombre());  
        }

        if(this.mensajeSistema.equals("GAME OVER")){
            Image over= new Image("Imagenes/Fondos/GameOver.png");
            over.draw(0, 0, VenganzaBelial.WIDTH, VenganzaBelial.HEIGHT);
        }
        
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException 
    {
        if(NuevoCombate)//Ejecutar Con Cada nuevo combate
        {
            /*MUSICA*/
            VenganzaBelial.controlMusica.cambiarMusica("Musica/BSO/BattleOst.wav");
            /*Eleccion mapa*/
            if(VenganzaBelial.atributoGestion.getMapaActual()==1 || VenganzaBelial.atributoGestion.getMapaActual()==10)
                fondo = new Image("Imagenes/BackBattle/Bosque.jpg");
            else if(VenganzaBelial.atributoGestion.getMapaActual()==11 || VenganzaBelial.atributoGestion.getMapaActual()==12)
                fondo = new Image("Imagenes/BackBattle/Barco.png");
            else if(VenganzaBelial.atributoGestion.getMapaActual()==4 || VenganzaBelial.atributoGestion.getMapaActual()==13|| VenganzaBelial.atributoGestion.getMapaActual()==14)
                fondo = new Image("Imagenes/BackBattle/Catacumbas.png");
            else if(VenganzaBelial.atributoGestion.getMapaActual()==6 || VenganzaBelial.atributoGestion.getMapaActual()==15|| VenganzaBelial.atributoGestion.getMapaActual()==16)
                fondo = new Image("Imagenes/BackBattle/Montana.png");
            else if(VenganzaBelial.atributoGestion.getMapaActual()==7 || VenganzaBelial.atributoGestion.getMapaActual()==17|| VenganzaBelial.atributoGestion.getMapaActual()==18)
                fondo = new Image("Imagenes/BackBattle/Cardinal.png");
            
            /*Resetea tasa huida*/
            this.tasaHuida=0.35f;
            /*Genera Nuevo Combate*/
            ArrayList<Personaje> party= new ArrayList<Personaje>();
            party.add(VenganzaBelial.atributoGestion.getJugs().get(0));
            party.add(VenganzaBelial.atributoGestion.getJugs().get(1));
            party.add(VenganzaBelial.atributoGestion.getJugs().get(2));
            NewCombate= new Combate(party, VenganzaBelial.atributoGestion.getMapaActual());//
            mensajeSistema="";
            //Cragar Imagenes especiales
            if(VenganzaBelial.atributoGestion.getMapaActual()==10)//Boss Bosque
                for (int j = 0; j < NewCombate.getEnemigos().size(); j++) {
                NewCombate.getEnemigos().get(j).setImagen("Imagenes/Monstruos/Bosque/Arbol_Boss.png"); 
                this.tasaHuida=0;
                VenganzaBelial.controlMusica.cambiarMusica("Musica/BSO/Yggdrasil_Battle.wav");
            }
            if(VenganzaBelial.atributoGestion.getMapaActual()==11)//Bandidos Normales
            {
                for (int j = 0; j < NewCombate.getEnemigos().size(); j++) {
                    NewCombate.getEnemigos().get(j).setImagen("Imagenes/Monstruos/Puerto/Bandido.png"); 
                    //this.tasaHuida=0;
                }
            }
            if(VenganzaBelial.atributoGestion.getMapaActual()==12)//Bandidos Normales
            {
                for (int j = 0; j < NewCombate.getEnemigos().size(); j++) {
                    if(NewCombate.getEnemigos().get(j).getNombre().equals("Gran Bandido Crow"))
                        NewCombate.getEnemigos().get(j).setImagen("Imagenes/Monstruos/Puerto/Crow.png");
                    if(NewCombate.getEnemigos().get(j).getNombre().equals("Bandido"))
                        NewCombate.getEnemigos().get(j).setImagen("Imagenes/Monstruos/Puerto/Bandido.png"); 
                    this.tasaHuida=0;
                    
                }
            }
            if(VenganzaBelial.atributoGestion.getMapaActual()==13)//Bandidos Normales
            {
                for (int j = 0; j < NewCombate.getEnemigos().size(); j++) {
                    if(NewCombate.getEnemigos().get(j).getNombre().equals("Fanatico"))
                        NewCombate.getEnemigos().get(j).setImagen("Imagenes/Monstruos/Catacumbas/Fanatic.png");
                }
            }
            if(VenganzaBelial.atributoGestion.getMapaActual()==14)//Bandidos Normales
            {
                VenganzaBelial.controlMusica.cambiarMusica("Musica/BSO/FanaticBattle.wav");
                for (int j = 0; j < NewCombate.getEnemigos().size(); j++) {
                    if(NewCombate.getEnemigos().get(j).getNombre().equals("Fanatico"))
                        NewCombate.getEnemigos().get(j).setImagen("Imagenes/Monstruos/Catacumbas/Fanatic.png");
                    if(NewCombate.getEnemigos().get(j).getNombre().equals("Muerte"))
                        NewCombate.getEnemigos().get(j).setImagen("Imagenes/Monstruos/Catacumbas/Fanatic.png");
                    if(NewCombate.getEnemigos().get(j).getNombre().equals("LiderFanatico"))
                        NewCombate.getEnemigos().get(j).setImagen("Imagenes/Monstruos/Catacumbas/Fanatic.png");
                }
            }
            if(VenganzaBelial.atributoGestion.getMapaActual()==15)//Bandidos Normales
            {
                for (int j = 0; j < NewCombate.getEnemigos().size(); j++) {
                    if(NewCombate.getEnemigos().get(j).getNombre().equals("Bellafonte"))
                        NewCombate.getEnemigos().get(j).setImagen("Imagenes/Monstruos/Montaña/Pegasus.png");
                }
            }
            if(VenganzaBelial.atributoGestion.getMapaActual()==16)//Bandidos Normales
            {
                for (int j = 0; j < NewCombate.getEnemigos().size(); j++) {
                    if(NewCombate.getEnemigos().get(j).getNombre().equals("Dragón"))
                        NewCombate.getEnemigos().get(j).setImagen("Imagenes/Monstruos/Montaña/dragon.png");
                }
            }
            if(VenganzaBelial.atributoGestion.getMapaActual()==17)//Bandidos Normales
            {
                for (int j = 0; j < NewCombate.getEnemigos().size(); j++) {
                    if(NewCombate.getEnemigos().get(j).getNombre().equals("Jinete Espectral"))
                        NewCombate.getEnemigos().get(j).setImagen("Imagenes/Monstruos/Cardinal/Rider.png");
                }
            }
            
            //NewCombate= new Combate(VenganzaBelial.Party, VenganzaBelial.MapaActual);//
            if(NewCombate.GestionaPrimerTurno())
            {
                Estado=OPCIONESBASE;
            }
            else{
                Estado=TURNOENEMIGO;
            }
            /*EDIT: Mirar donde añadir imagenes al los enemigos*/
            //switch Case 
            //EDIT END
            NuevoCombate = false;
            //OST.loop(1, 0.1f);
        }
        else
        {
            switch (Estado)
            {
                case  OPCIONESBASE://Opciones base del pj
                    OpcionControl(NUMOPCIONESBASE);
                    //Opciones Base Enter Control
                    OpcionBase();
                    break;
                case  ATACANDO://Opciones base del pj
                    OpcionControl(NewCombate.getEnemigos().size());
                    //Opcion de Enemigo al que atacar
                    Atacando();
                    break;
                case  SELHABILIDAD://Seleccion de habilidad a usar
                    OpcionControl(NHABILIDADES);
                    //Opcion de Seleccion de Habilidades
                    selHabilidades();
                    break;
                case  SELCONSUMIBLE://Seleccion Consumible a utilizar
                    OpcionControl(NCONSUMIBLES);
                    //Opcion Seleccion de Consumible
                    selConsumible();
                    break;
                case  HUYENDO://Opciones base del pj
                    Huir();
                    break;
                case  TURNOENEMIGO://Turno automatico Enemigo
                    //EDIT: 
                    Enemigo enem=(Enemigo)NewCombate.getOrdenPersonajes().get(NewCombate.getTurno());
                    mensajeSistema=enem.estrategiaAtacar(VenganzaBelial.atributoGestion.getJugs());
                    this.renderAE=true;
                    estadoAnterior=TURNOENEMIGO;
                    Estado=FINTURNO;
                    break;
                case  SELOBJETIVO://Uso de habilidad/consumible sobre objetivo Enemigo
                    OpcionControl(NewCombate.getEnemigos().size());
                    //Seleccion de objetivo al que lanzar Habilidad
                    selObjetivo();
                    break;
                case  SELALIADO://Uso de habilidad/Consumible sobre objetivo Aliado
                    OpcionControl(VenganzaBelial.atributoGestion.getJugs().size());
                    //Seleccion de aliado al que lanzar Habilidad
                    selAliado();
                    break;
                case FINTURNO:
                    /*Gestiona el final del turno, comprobando si el combate esta acabado o no*/
                    FinTurno();
                    break;
                case FINCOMBATE:
                    FinCombate(gc,sbg);
                    //sbg.enterState(VenganzaBelial.ESTADOMENUINICIO);//EDIT:Eliminar
                    OST.stop();
                    break;
            }
            /*Escape vuelve al estado inicial*/
            ReiniciarSeleccion();
            this.controlRenderAtaqueAliado(i);
            this.controlRenderSkill(i);
            this.controlRenderAtaqueEnemigo(i);
        }
    }
    
    private void OpcionControl(int NumeroOpciones)
    {
        /*Selector de Acciones de Usuario*/
            if (input.isKeyPressed(Input.KEY_DOWN)) 
            {
                if (eleccionJugador == (NumeroOpciones - 1)) 
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
                    eleccionJugador = NumeroOpciones - 1;
                } else {
                    eleccionJugador--;
                }
            }                       
    }/*private void OpcionesBase()*/
    
    //Anula la seleccion y retorna al estado incial*/
    private void ReiniciarSeleccion()
    {
        if(Estado!=TURNOENEMIGO && Estado!=HUYENDO && Estado!=FINTURNO)
        {
            if(input.isKeyPressed(Input.KEY_ESCAPE))
            {
                estadoAnterior=OPCIONESBASE;
                Estado=OPCIONESBASE;
                sonidoSelect.play(1, 0.2f);
            }/**/
        }/*if(Estado!=TURNOENEMIGO && Estado!=HUYENDO && Estado!=FINTURNO)*/
    }/*private void ReiniciarSeleccion()*/
    
    private void OpcionBase()
    {
        if (input.isKeyPressed(Input.KEY_ENTER))
        {  
            sonidoSelect.play(1, 0.2f);
            estadoAnterior=OPCIONESBASE;
            switch (eleccionJugador) 
            {
                case ATACAR:
                    Estado=ATACANDO;//Cambio de estado
                    break;
                case HABILIDAD:
                    Estado=SELHABILIDAD;
                    break;
                case CONSUMIBLE:
                    Estado=SELCONSUMIBLE;
                    break;
                case HUIR:
                    Estado=HUYENDO;
                    break;
            }/*switch (eleccionJugador) */
            /*EDIT: elecciónJugador=0?*/
            eleccionJugador=0;//Resetea el indice de seleccion de opciones para el nuevo estado*/
        }/*if (input.isKeyPressed(Input.KEY_ENTER))*/
    }/*private void OpcionBase()*/
    
    private void Atacando()
    {
        int IndiceTurno;
        /*EDIT: elecciónJugador=0?*/
        if(input.isKeyPressed(Input.KEY_ENTER))
        {   
            IndiceTurno=NewCombate.getTurno();
            //
            this.sonidoAtaque.play();
            this.mensajeSistema=NewCombate.Atacar(NewCombate.getOrdenPersonajes().get(IndiceTurno), NewCombate.getEnemigos().get(eleccionJugador)); 
            if(!NewCombate.getEnemigos().get(eleccionJugador).estaVivo())
            {
                this.sonidoMuerto.play();
            }
            /*Cambio de estado a turno final*/
            eleccionJugador=0;//Resetea el indice de seleccion de opciones para el nuevo estado*/
            estadoAnterior=ATACANDO;
            renderAA=true;
            Estado=FINTURNO; 
        }/**/       
    }/*private void Atacando()*/
    
    private void selHabilidades()/*EDIT: Bajo Pruebas*/
    {
        int tipo;
        int IndiceTurno=NewCombate.getTurno();
        Jugador pj= (Jugador)NewCombate.getOrdenPersonajes().get(IndiceTurno);
        if(input.isKeyPressed(Input.KEY_ENTER))
        {
            if(pj.habilidadUsable(this.eleccionJugador))
            {
               //Guarda la habilidad seleccionada a utilizar
                habilidadSeleccionada=eleccionJugador;
                tipo=pj.getHabilidades().get(eleccionJugador).getTipoHabilidad();
                eleccionJugador=0;
                //Comprueba el tipo de habilidad para decidir contra quien usarla
                estadoAnterior=SELHABILIDAD;
                switch (tipo)
                {
                    case Habilidad.TIPOCURAR:
                        Estado=SELALIADO;
                        break;
                    case Habilidad.TIPORESUCITAR:
                        Estado=SELALIADO;
                        break;
                    case Habilidad.TIPODRENARVIDA:
                        Estado=SELOBJETIVO;
                        break;
                    case Habilidad.TIPOATACAR:
                        Estado=SELOBJETIVO;
                        break;
                    case Habilidad.TIPOAOE:
                        //Ejecuta Habilidad contra todos los enemigos y pasa al siguiente turno
                        pj.usarHabilidad(habilidadSeleccionada, NewCombate.getEnemigos());
                        this.renderSkill=true;
                        this.mensajeSistema=(pj.getNombre()+" ha usado "+pj.getHabilidades().get(habilidadSeleccionada).getNombre());
                        Estado=FINTURNO;
                        break;
                }
            }
            else{
                /*Habilidad no usable*/
                sonidoError.play(1,0.3f);
            }
        }/**/
    }/*private void Habilidades()*/
    
    private void selConsumible()
    {
        if(input.isKeyPressed(Input.KEY_ENTER))
        { 
            sonidoSelect.play(1, 0.2f);
            consumibleSeleccionado=eleccionJugador;
            estadoAnterior=SELCONSUMIBLE;
            eleccionJugador=0;
            Estado=SELALIADO;
        }
    }/*private void selConsumible()*/
    
    private void selObjetivo()
    {
        if(input.isKeyPressed(Input.KEY_ENTER))
        {  
            int IndiceTurno=NewCombate.getTurno();
            Jugador pj= (Jugador)NewCombate.getOrdenPersonajes().get(IndiceTurno);
            /*EDIT: Cambiar Mensaje Sistema*/
            this.mensajeSistema=(pj.getNombre()+" ha utilizado "+pj.getHabilidades().get(habilidadSeleccionada).getNombre());
            //Ejecutar habilidad con el pj correcpondiente sobre el Objetivo designado
            pj.usarHabilidad(this.habilidadSeleccionada, NewCombate.getEnemigos().get(eleccionJugador));
            //pj.getHabilidades().get(this.habilidadSeleccionada).usarHabilidad(pj, NewCombate.getEnemigos().get(eleccionJugador));
            eleccionJugador=0;
            estadoAnterior=SELOBJETIVO;
            renderSkill=true;
            Estado=FINTURNO;
        }
    }/*private void selObjetivo()*/
    
    private void selAliado()
    {
       if(input.isKeyPressed(Input.KEY_ENTER))
        { 
            int IndiceTurno=NewCombate.getTurno();
            Jugador pj= (Jugador)NewCombate.getOrdenPersonajes().get(IndiceTurno);
            switch (estadoAnterior)
            {
                case SELHABILIDAD:
                    
                    //Ejecutar habilidad con el pj correcpondiente sobre el Aliado designado y comprobar que se puede
                    //Si se puede se ejecutará y devolvera true
                    if(pj.usarHabilidad(this.habilidadSeleccionada, VenganzaBelial.atributoGestion.getJugs().get(eleccionJugador)))
                    {
                        /*EDIT: Cambiar Mensaje Sistema*/
                        this.mensajeSistema=(pj.getNombre()+" ha utilizado "+pj.getHabilidades().get(habilidadSeleccionada).getNombre()+" sobre "+VenganzaBelial.atributoGestion.getJugs().get(eleccionJugador).getNombre());
                        if(pj.getHabilidades().get(habilidadSeleccionada).getTipoHabilidad()==Habilidad.TIPORESUCITAR){
                            NewCombate.GestionaResurrecion(VenganzaBelial.atributoGestion.getJugs().get(eleccionJugador));
                        }
                        eleccionJugador=0;
                        estadoAnterior=SELALIADO;
                        Estado=FINTURNO;
                    }
                    else{
                        //EDIT:Posible mensaje de sistema informando de que no se puede usar la habilidad
                        sonidoError.play(1,0.3f);
                        this.mensajeSistema="Imposible utilizar esta habilidad en esta situación";
                    }
                    break;
                case SELCONSUMIBLE:
                    this.mensajeSistema=("Imposible utilizar objeto en esta situación");
                    switch (consumibleSeleccionado)
                    {
                        case 0:
                            if(pj.getInventario().usarPocionVida((Jugador)VenganzaBelial.atributoGestion.getJugs().get(eleccionJugador))){
                                this.mensajeSistema=("Utilizada "+pj.getInventario().getItems().get(consumibleSeleccionado).getNombre());
                                estadoAnterior=SELALIADO;//EDIT:Buscar forma optima de cambiar de estado
                                eleccionJugador=0;
                                Estado=FINTURNO;
                            }
                            else{
                                sonidoError.play(1,0.3f);
                            }
                            break;
                        case 1:
                            if(pj.getInventario().usarPocionMana((Jugador)VenganzaBelial.atributoGestion.getJugs().get(eleccionJugador))){
                                this.mensajeSistema=("Utilizada "+pj.getInventario().getItems().get(consumibleSeleccionado).getNombre());
                                estadoAnterior=SELALIADO;//EDIT:Buscar forma optima de cambiar de estado
                                eleccionJugador=0;
                                Estado=FINTURNO;
                            }
                            else{
                                sonidoError.play(1,0.3f);
                            }
                            break;
                        case 2:
                            if(pj.getInventario().usarPocionRes((Jugador)VenganzaBelial.atributoGestion.getJugs().get(eleccionJugador))){
                                this.mensajeSistema=("Utilizada "+pj.getInventario().getItems().get(consumibleSeleccionado).getNombre());
                                NewCombate.GestionaResurrecion(VenganzaBelial.atributoGestion.getJugs().get(eleccionJugador));
                                estadoAnterior=SELALIADO;//EDIT:Buscar forma optima de cambiar de estado
                                eleccionJugador=0;
                                Estado=FINTURNO;
                            }
                            else{
                                sonidoError.play(1,0.3f);
                            }
                            break;
                    }/*switch (consumibleSeleccionado)*/
                    break;
            }
        } 
    }/*private void selAliado()*/
        
    private void Huir()
    {
        Random rand= new Random();
        float tasaHuida=rand.nextFloat();
        if(tasaHuida<this.tasaHuida)/*EDIT:0.9*/
        {
            //Objetivo Huye y se finaliza el combate 
            eleccionJugador=0;
            Estado=FINCOMBATE; 
            flagHuida=true;
            
        }
        else
        {
           //Objetivo no huye y pierde turno
            this.mensajeSistema="No se ha conseguido huir";
            estadoAnterior=HUYENDO;
            eleccionJugador=0;
            Estado=FINTURNO;          
        }
    }/*private void Huir()*/
    
    private void FinTurno()
    {
        eleccionJugador=0;
        if(input.isKeyPressed(Input.KEY_ENTER))
        {
            estadoAnterior=FINTURNO;
            /*Gestiona muertes del ultimo turno*/
            NewCombate.GestionaMuertes();
            /*Comprueba que el combate no se haya acabado*/
            if(NewCombate.CombateAcabado())
            {
                Estado=FINCOMBATE;
            }/*if(NewCombate.CombateAcabado())*/
            else
            {
                if(NewCombate.GestionaSiguienteTurno())//Si el siguiente turno es de jugador
                {
                    Estado=OPCIONESBASE;
                }
                else{
                    Estado=TURNOENEMIGO;
                }
            }/*else*/
        }/*if(input.isKeyPressed(Input.KEY_ENTER))*/
    }/*private void FinTurno()*/
    
    private void FinCombate(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        /*Comprobar quien ha ganado el combate y actuar concorde*/
        if(flagHuida)//Si consigue huir se devuelve al mapa sin ganar o perder
        {
            flagHuida=false;
            NuevoCombate=true;
            retornoAlMapa(sbg);
        }
        else if(NewCombate.CombateGanado())
        {
            //Si el combate ha sido ganado-> EXP+Drop+Devolver al mapa 
            this.mensajeSistema="YOU WIN/n EXP Recibida: "+NewCombate.getExpCombate()+"/n Oro: "+NewCombate.getOroCombate();
            //this.mensajeSistema="YOU WIN";
            if(input.isKeyPressed(Input.KEY_ENTER))
            {
                int aux=0;
                int exp=NewCombate.getExpCombate();
                for(aux=0;aux<VenganzaBelial.atributoGestion.getJugs().size();aux++)
                {
                    Jugador pj=(Jugador)VenganzaBelial.atributoGestion.getJugs().get(aux);
                    //Recibe la experiencia solo si no esta muerto al final del combate
                    if(pj.estaVivo())
                    {
                        pj.setExp(pj.getExp()+exp);
                        if(pj.puedeSubir())
                        {
                            pj.subirNivel();
                        }/*if(pj.puedeSubir())*/
                    }/* if(pj.estaVivo())*/
                }/*for(aux=0;aux<VenganzaBelial.atributoGestion.jugs.size();aux++)*/
                if(VenganzaBelial.atributoGestion.getJugs().get(1).getNivel()<5)
                    VenganzaBelial.atributoGestion.getInv().setDinero(VenganzaBelial.atributoGestion.getInv().getDinero()+NewCombate.getOroCombate());
                else
                    VenganzaBelial.atributoGestion.getInv().setDinero(VenganzaBelial.atributoGestion.getInv().getDinero()+NewCombate.getOroCombate()*2);
                //Reactiva Flag para la proxima vez que se genera un combate
                NuevoCombate=true;
                //EDIT:ELIMINAR OBJETO COMBATE O VACIAR
                retornoAlMapa(sbg);
                //gc.exit();  
            }/*if(input.isKeyPressed(Input.KEY_ENTER))*/
        }/*if(NewCombate.CombateGanado())*/
        else
        {
            /*EDIT:cambiar mensaje de GAME OVER*/
            this.mensajeSistema="GAME OVER";
            //Eliminar objeto combate
            if(input.isKeyPressed(Input.KEY_ENTER))
            {
             NuevoCombate=true;sbg.enterState(VenganzaBelial.ESTADOMENUINICIO); 
            }
            //Si se ha perdido el combate recargar datos del ultimo punto de control
        }
    }/*private void FinComabte()*/
    
    
    private void retornoAlMapa(StateBasedGame sbg) throws SlickException//Origen sera de un id de donde proviene el combate(mapax o eventox)
    {
        //Edit, Comprobar casos especiales como Bosses
        switch(VenganzaBelial.atributoGestion.getMapaActual()){
            case 0:
//                VenganzaBelial.MapaActual=1;//MapaBosque
                VenganzaBelial.controlMusica.cambiarMusica("Musica/BSO/Music_Forest.wav");
                VenganzaBelial.atributoGestion.setMapaActual(1);
                break;
            case 10:
                VenganzaBelial.controlMusica.pararMusica();
                sbg.enterState(VenganzaBelial.ESCENABOSQUEPOSTBOSS);
                break;
            case 11: 
//                VenganzaBelial.MapaActual=2;//Mapa Puerto
                VenganzaBelial.controlMusica.cambiarMusica("Musica/BSO/Music_City.wav");
                VenganzaBelial.atributoGestion.setMapaActual(2);
                sbg.enterState(VenganzaBelial.ESTADOMAPAJUEGO);
                break;
            case 12:
                VenganzaBelial.controlMusica.cambiarMusica("Musica/BSO/Music_City.wav");
                sbg.enterState(VenganzaBelial.ESCENAPUERTO2);
                break;
            case 13:
                //Escena despues de los fanaticos??
//                VenganzaBelial.MapaActual=4;
                VenganzaBelial.atributoGestion.setMapaActual(4);
                sbg.enterState(VenganzaBelial.ESTADOMAPAJUEGO);
                break;
            case 14:
                //Derrotado Boss de Cataumbas
                sbg.enterState(VenganzaBelial.ESCENATROYIAPOSTBOSS);
                break;
            case 15:
                //Derrotado MiniBoss Montaña
                VenganzaBelial.controlMusica.pararMusica();
                sbg.enterState(VenganzaBelial.ESCENAMONTANAMINIBOSS2);
                break;
            case 16://Derrotado Boss de montaña
                sbg.enterState(VenganzaBelial.ESCENAMONTANAPOSTBOSS);
                break;
            case 17:
                //VenganzaBelial.atributoGestion.setMapaActual(7);
                sbg.enterState(VenganzaBelial.ESCENACARDINALMINIBOSS2);
                break;
            case 18:
                break;
            case 19:
                break;
            default:
                VenganzaBelial.controlMusica.cambiaMusicaMapa(VenganzaBelial.atributoGestion.getMapaActual());
                sbg.enterState(VenganzaBelial.ESTADOMAPAJUEGO);     
        }
        
    }/*private void retornoAlMapa()*/
    
    private void renderOpcionesJugador()
    {
        for (int i = 0; i < NUMOPCIONESBASE; i++) 
        {
            if (eleccionJugador == i) {
                opcionesJugadorTTF.drawString(20, i * 20 + 400, opciones[i]);
            } else {
                opcionesJugadorTTF.drawString(20, i * 20 + 400, opciones[i], notChosen);
            }
        }
//        for (int i = 0; i < NUMOPCIONESBASE; i++) 
//        {
//            if (eleccionJugador == i) {
//                opcionesJugadorTTF.drawString(10, i * 20 + 400, opciones[i]);
//            } else {
//                opcionesJugadorTTF.drawString(10, i * 20 + 400, opciones[i], notChosen);
//            }
//        }
    }/*private void renderOpcionesJugador()*/
    
    private void renderHabilidades()
    {
        Jugador PJ= (Jugador)NewCombate.getOrdenPersonajes().get(NewCombate.getTurno());
        for (int i=0;i<NHABILIDADES;i++)
        {
            if(eleccionJugador==i)
            {
               opcionesJugadorTTF.drawString(20,i*20+400,PJ.getHabilidades().get(i).getNombre());
            }
            else{
                opcionesJugadorTTF.drawString(20, i * 20 + 400, PJ.getHabilidades().get(i).getNombre(), notChosen);
            }
        }
    }/* private void renderHabilidades()*/
    
    private void renderObjetos()
    {
        for (int i=0;i<NCONSUMIBLES;i++)
        {
            //EDIT: Ruta de Inventario
            Consumible consumible=(Consumible)VenganzaBelial.atributoGestion.getInv().getItems().get(i);
            if(eleccionJugador==i)
            {
               opcionesJugadorTTF.drawString(20,i*20+400,consumible.getNombre()+" "+consumible.getNumero()+"/10");
            }
            else{
                opcionesJugadorTTF.drawString(20, i * 20 + 400, consumible.getNombre()+" "+consumible.getNumero()+"/10", notChosen);
            }
        }
    }
    
    private void renderSelAliados()
    {
        
        opcionesJugadorTTF.drawString(20,20+400, VenganzaBelial.atributoGestion.getJugs().get(this.eleccionJugador).getNombre());
    }/*private void renderSelAliados()*/
    
    private void renderSelObjetivo()
    {
        Enemigo enem= (Enemigo)NewCombate.getEnemigos().get(this.eleccionJugador);
        if(enem.getId()!=0)
            opcionesJugadorTTF.drawString(20,20+400, enem.getNombre()+" "+enem.getId());
        else 
            opcionesJugadorTTF.drawString(20,20+400, enem.getNombre());
    }/*private void renderSelObjetivo()*/
    
    private void renderEnemigos() throws SlickException
    {
        //EDIT
       /*Render Enemigos si no estan muertos*/
        int aux;
        int nEnemigos= NewCombate.getEnemigos().size();
        for(aux=0;aux<nEnemigos;aux++)
        {
            if(NewCombate.getEnemigos().get(aux).estaVivo())
            {
                /*Debug prints*/
                Personaje debugPJ= NewCombate.getEnemigos().get(aux);
                mensajePantalla.drawString(700, 20*aux, debugPJ.getNombre()+"/LVL: "+debugPJ.getNivel()+"/HP: "+debugPJ.getHpActual()+"|"+debugPJ.getHp());
                //NewCombate.getEnemigos().get(aux).getImagen().draw(aux*300+200, 200, 350, 400);
                //enemigo.draw(aux*200+200, 200, 0.5f);
                NewCombate.getEnemigos().get(aux).getImagen().draw(aux*200+400, 200, 0.5f);
            }
        }
    }/*private void renderEnemigos() throws SlickException*/
    
    private void renderAvatars() throws SlickException
    {
         /*Update Grafico*/
        float porcentajeBarra=1;//Auxuliar de calculo
        /*Imagenes del fondo*/
            
            /*EDIT:Imagenes de los personajes*/
            
            
            fondo.draw(0, 0, 1366, 768);
            /*EDIT END*/
            /*Horacia status update*/
            //if(VenganzaBelial.horacia.estaVivo()){
            if(VenganzaBelial.atributoGestion.getJugs().get(0).estaVivo()){
                if(NewCombate.getTurno()>=NewCombate.getOrdenPersonajes().size())
                {
                   this.avatarVivo.draw(0, 618, 300, 150); 
                }
                else if(NewCombate.getOrdenPersonajes().get(NewCombate.getTurno())==VenganzaBelial.atributoGestion.getJugs().get(0))
                {
                   this.avatarSeleccionado.draw(0, 618, 300, 150);
                }
                else{
                    this.avatarVivo.draw(0, 618, 300, 150);
                }                
                mensajePantalla.drawString(110, 658, "HP"+VenganzaBelial.atributoGestion.getJugs().get(0).getHpActual()+ "/"+VenganzaBelial.atributoGestion.getJugs().get(0).getHp(),rojo);
            }
            else{
                this.avatarMuerto.draw(0, 618, 300, 150);
                mensajePantalla.drawString(110, 658, "HP"+0+ "/"+VenganzaBelial.atributoGestion.getJugs().get(0).getHp(),rojo);
            }
            this.contenedor.draw(110, 638, 100, 20);
            porcentajeBarra=(float)VenganzaBelial.atributoGestion.getJugs().get(0).getHpActual()/(float)VenganzaBelial.atributoGestion.getJugs().get(0).getHp();
            porcentajeBarra=compruebaPorcentajeBarra(porcentajeBarra);
            this.hp.draw(111, 643, 97*porcentajeBarra, 10);
            this.contenedor.draw(110, 688, 100, 20);
            porcentajeBarra=(float)VenganzaBelial.atributoGestion.getJugs().get(0).getMpActual()/(float)VenganzaBelial.atributoGestion.getJugs().get(0).getMp();
            porcentajeBarra=compruebaPorcentajeBarra(porcentajeBarra);
            this.mp.draw(111, 693, 97*porcentajeBarra, 10);
            mensajePantalla.drawString(110, 708, "MP "+VenganzaBelial.atributoGestion.getJugs().get(0).getMpActual()+ "/"+VenganzaBelial.atributoGestion.getJugs().get(0).getMp(),azul);
            Avatar1.draw(0, 618, 100, 150);        
            /*MORDEIM STATUS UPDATE*/
            if(VenganzaBelial.atributoGestion.getJugs().get(1).estaVivo()){
                if(NewCombate.getTurno()>=NewCombate.getOrdenPersonajes().size())//If de seguridad para coordeinar render y update
                {
                   this.avatarVivo.draw(300, 618, 300, 150);
                }
                else if(NewCombate.getOrdenPersonajes().get(NewCombate.getTurno())==VenganzaBelial.atributoGestion.getJugs().get(1))
                {
                   this.avatarSeleccionado.draw(300, 618, 300, 150);
                }
                else{
                    this.avatarVivo.draw(300, 618, 300, 150);
                }                
                mensajePantalla.drawString(410, 658, "HP"+VenganzaBelial.atributoGestion.getJugs().get(1).getHpActual()+ "/"+VenganzaBelial.atributoGestion.getJugs().get(1).getHp(),rojo);
            }
            else{
                this.avatarMuerto.draw(300, 618, 300, 150);
                mensajePantalla.drawString(410, 658, "HP"+0+ "/"+VenganzaBelial.atributoGestion.getJugs().get(1).getHp(),rojo);
            }
            this.contenedor.draw(410, 638, 100, 20);
            porcentajeBarra=(float)VenganzaBelial.atributoGestion.getJugs().get(1).getHpActual()/(float)VenganzaBelial.atributoGestion.getJugs().get(1).getHp();
            porcentajeBarra=compruebaPorcentajeBarra(porcentajeBarra);
            this.hp.draw(411, 643, 97*porcentajeBarra, 10);
            this.contenedor.draw(410, 688, 100, 20);
            porcentajeBarra=(float)VenganzaBelial.atributoGestion.getJugs().get(1).getMpActual()/(float)VenganzaBelial.atributoGestion.getJugs().get(1).getMp();
            porcentajeBarra=compruebaPorcentajeBarra(porcentajeBarra);
            this.mp.draw(411, 693, 97*porcentajeBarra, 10);
            mensajePantalla.drawString(410, 708, "MP "+VenganzaBelial.atributoGestion.getJugs().get(1).getMpActual()+ "/"+VenganzaBelial.atributoGestion.getJugs().get(1).getMp(),azul);
            Avatar2.draw(300, 618, 100, 150);
            /*Kibito Status update*/
            if(VenganzaBelial.atributoGestion.getJugs().get(2).estaVivo()){
                if(NewCombate.getTurno()>=NewCombate.getOrdenPersonajes().size())//If de seguridad para coordeinar render y update
                {
                   this.avatarVivo.draw(600, 618, 300, 150);
                }
                else if(NewCombate.getOrdenPersonajes().get(NewCombate.getTurno())==VenganzaBelial.atributoGestion.getJugs().get(2))
                {
                   this.avatarSeleccionado.draw(600, 618, 300, 150);
                }
                else{
                    this.avatarVivo.draw(600, 618, 300, 150);
                }                
                mensajePantalla.drawString(710, 658, "HP"+VenganzaBelial.atributoGestion.getJugs().get(2).getHpActual()+ "/"+VenganzaBelial.atributoGestion.getJugs().get(2).getHp(),rojo);
            }
            else{
                this.avatarMuerto.draw(600, 618, 300, 150);
                mensajePantalla.drawString(710, 658, "HP"+0+ "/"+VenganzaBelial.atributoGestion.getJugs().get(2).getHp(),rojo);
            }
            this.contenedor.draw(710, 638, 100, 20);
            porcentajeBarra=(float)VenganzaBelial.atributoGestion.getJugs().get(2).getHpActual()/(float)VenganzaBelial.atributoGestion.getJugs().get(2).getHp();
            porcentajeBarra=compruebaPorcentajeBarra(porcentajeBarra);
            this.hp.draw(711, 643, 97*porcentajeBarra, 10);
            this.contenedor.draw(710, 688, 100, 20);
            porcentajeBarra=(float)VenganzaBelial.atributoGestion.getJugs().get(2).getMpActual()/(float)VenganzaBelial.atributoGestion.getJugs().get(2).getMp();
            porcentajeBarra=compruebaPorcentajeBarra(porcentajeBarra);
            this.mp.draw(711, 693, 97*porcentajeBarra, 10);
            mensajePantalla.drawString(710, 708, "MP "+VenganzaBelial.atributoGestion.getJugs().get(2).getMpActual()+ "/"+VenganzaBelial.atributoGestion.getJugs().get(2).getMp(),azul);        
            Avatar3.draw(600, 618, 100, 150);
            //Marco Avatar Complaeto
            marco.draw(0,605,920,15);
            marcoL.draw(900, 613, 20, 160);
            /*EDIT:Debug prints*/
            //mensajePantalla.drawString(700, 10, "LVL "+VenganzaBelial.horacia.getNivel());
            //mensajePantalla.drawString(700, 30, "Atk: "+VenganzaBelial.horacia.getAtaque()+"Def: "+VenganzaBelial.horacia.getDefensa());
            //mensajePantalla.drawString(700, 50, "Exp: "+VenganzaBelial.horacia.getExp()+"/: "+VenganzaBelial.horacia.getExpProxNivel());
            
            //mensajePantalla.drawString(700, 70, "LVL "+VenganzaBelial.mordeim.getNivel());
             //mensajePantalla.drawString(700, 90, "Atk: "+VenganzaBelial.mordeim.getAtaque()+"Def: "+VenganzaBelial.mordeim.getDefensa());
             //mensajePantalla.drawString(700, 110, "Exp: "+VenganzaBelial.mordeim.getExp()+"/: "+VenganzaBelial.mordeim.getExpProxNivel());
             
            //mensajePantalla.drawString(700, 130, "LVL "+VenganzaBelial.kibito.getNivel());
            // mensajePantalla.drawString(700, 150, "Atk: "+VenganzaBelial.kibito.getAtaque()+"Def: "+VenganzaBelial.kibito.getDefensa());
            // mensajePantalla.drawString(700, 110, "Exp: "+VenganzaBelial.kibito.getExp()+"/: "+VenganzaBelial.kibito.getExpProxNivel());
            /**/
            if(NewCombate.getTurno()<NewCombate.getOrdenPersonajes().size())//If de seguridad para coordeinar render y update
            {
                mensajePantalla.drawString(10, 360, "Turno de:" + NewCombate.getOrdenPersonajes().get(NewCombate.getTurno()).getNombre(), new Color(0,0,0));
            }
    }/*private void renderAvatars(Graphics g)*/  
    private float compruebaPorcentajeBarra(float porcentajeBarra)
    {
        if(porcentajeBarra<0)
            porcentajeBarra=0;
        return porcentajeBarra;
    }
    private void renderMensajeSistema() throws SlickException
    {
        Image fondoDecision= new Image("Imagenes/Avatar/decisionFondo.png");
        fondoDecision.draw(0, 0, 1400, 70);
        opcionesJugadorTTF.drawString(10,20, this.mensajeSistema);
    }
    
    private void controlRenderSkill(int delta)
    {
        if(renderSkill==true)
        {
            posicionSkill.x+=delta;
            if(posicionSkill.x>1200)
            {
                posicionSkill.x=0;
                renderSkill=false;
            }
        }
    }
    private void renderSkill() throws SlickException
    {
        if(renderSkill==true){
            if(NewCombate.getOrdenPersonajes().get(NewCombate.getTurno())==VenganzaBelial.atributoGestion.getJugs().get(0))
            this.skill= new Image("Imagenes/Animaciones/Combate/SkillHoracia.png");
            if(NewCombate.getOrdenPersonajes().get(NewCombate.getTurno())==VenganzaBelial.atributoGestion.getJugs().get(1))
                this.skill= new Image("Imagenes/Animaciones/Combate/SkillMordeim.png");
            if(NewCombate.getOrdenPersonajes().get(NewCombate.getTurno())==VenganzaBelial.atributoGestion.getJugs().get(2))
                this.skill= new Image("Imagenes/Animaciones/Combate/SkillKibito.png");
            skill.draw(posicionSkill.x, posicionSkill.y);
        }
                   
    }
    
    private void controlRenderAtaqueEnemigo(int i)
    {
        if(renderAE==true)
        {
            time+=i;
            if(time/1000>0.4f)//
            {
                renderAE=false;
                time=0;
            }
        }
    }
    private void renderAtaqueEnemigo()
    {
        if(renderAE)
            this.ataqueEnemigo.draw(300, 578);
    }
    
    private void controlRenderAtaqueAliado(int i)
    {
        if(renderAA==true)
        {
            time+=i;
            if(time/1000>0.4f)//
            {
                renderAA=false;
                time=0;
            }
        }
    }
    private void renderAtaqueAliado()
    {
        if(renderAA)
            this.ataqueAliado.draw(500, 218);
    }
}
