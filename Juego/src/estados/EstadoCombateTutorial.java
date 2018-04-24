package estados;

import static estados.VenganzaBelial.ESTADOMENUINICIO;
import static estados.VenganzaBelial.hori;
import static estados.VenganzaBelial.kibi;
import static estados.VenganzaBelial.mordi;
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

public class EstadoCombateTutorial extends BasicGameState{
    /*Estados Inercombate*/
    private Input input;
    private int Estado=0;
    private int estadoDialogo=0;
    private static final int NESTADOS=11;
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
    private static final int DIALOGO=10;
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
    private Image contenedor,hp, mp,avatarVivo, avatarMuerto,avatarSeleccionado,marco,marcoL,ventanaDialogo, Avatar1, Avatar2, Avatar3;
    private TrueTypeFont mensajePantalla;
    private Font TipoLetra  =new Font("Verdana", Font.PLAIN, 15);    
    private Color rojo = new Color (256,0,0);
    private Color verde = new Color (0,256,0);
    private Color azul = new Color (0,0,256);    
    private Music OST;
    private Sound sonidoAtaque,sonidoMuerto,sonidoSelect, sonidoError;
    private String mensajeSistema= "";
    //private SpriteSheet sprite;
    //private Animation animacion;
    /*EDIT*/
    private int flagPruebas=0;
    
    public EstadoCombateTutorial(int id) {
        idEstado = id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
    {
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
        ventanaDialogo= new Image("Imagenes/Avatar/cajaMensaje.png");
        /*EDIT:Test*/
        //this.sprite= new SpriteSheet("Imagenes/Animaciones/ataque.png",200,200);
        //this.animacion= new Animation(sprite, 200);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
    {
        /*Imagenes del fondo*/
        fondo.draw(0, 0, 1366, 768);
        renderEnemigos();
        mensajePantalla.drawString(500, 500, "estadoDialogo"+this.estadoDialogo);
        /*Switch Case para visualizar opciones en funcion del estado*/
        if(Estado==DIALOGO){
            renderDialogos();
        }
        else{
            renderAvatars();
            switch (Estado)
            {
                case OPCIONESBASE:
                    renderOpcionesJugador();
                    break;
                case SELHABILIDAD:
                    renderHabilidades();
                    break;
                case SELOBJETIVO :
                    renderSelObjetivo();
                    break;
                case ATACANDO:
                    renderSelObjetivo();
                    //this.animacion.draw(750, 200, 200, 200);
                    break;
                case SELCONSUMIBLE:
                    renderObjetos();
                    break;
                case SELALIADO:
                    renderSelAliados();
                    break;
                case FINTURNO:
                    renderMensajeSistema();
                    break;
                case FINCOMBATE:
                    renderMensajeSistema();
                    break;
            }    
        }/*else*/
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException 
    {
        if(NuevoCombate)//Ejecutar Con Cada nuevo combate
        {
            /*Genera Nuevo Combate*/
            ArrayList<Personaje> party= new ArrayList<Personaje>();
            party.add(VenganzaBelial.atributoGestion.getJugs().get(0));
            party.add(VenganzaBelial.atributoGestion.getJugs().get(1));
            party.add(VenganzaBelial.atributoGestion.getJugs().get(2));
            NewCombate= new Combate(party, VenganzaBelial.MapaActual);//
            //NewCombate= new Combate(VenganzaBelial.Party, VenganzaBelial.MapaActual);//
            fondo = new Image("Imagenes/BackBattle/Bosque.jpg");
            /*EDIT:Imagenes de los personajes*/
            Avatar1 =  new Image("Imagenes/Personajes/HoraciaA.png");
            Avatar2 =  new Image("Imagenes/Personajes/MordeimA.png");
            Avatar3 =  new Image("Imagenes/Personajes/KibitoA.png");
            //
            Estado=DIALOGO;
            /*EDIT: Mirar donde añadir imagenes al los enemigos*/
            VenganzaBelial.hori.setImagen("Imagenes/Monstruos/Test1.png");
            VenganzaBelial.mordi.setImagen("Imagenes/Monstruos/Test2.png");
            VenganzaBelial.kibi.setImagen("Imagenes/Monstruos/Test3.png");
            NuevoCombate = false;
            //OST.loop(1, 0.1f);
        }
        else
        {
            if(Estado==DIALOGO)
            {
                dialogoTutorial();
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
                        //EDIT: Eliminar
                        if(this.flagPruebas==0)
                        {
                            this.mensajeSistema=NewCombate.Atacar(NewCombate.getOrdenPersonajes().get(NewCombate.getTurno()), VenganzaBelial.horacia);
                        }
                        else if(this.flagPruebas==1)
                        {
                            this.mensajeSistema=NewCombate.Atacar(NewCombate.getOrdenPersonajes().get(NewCombate.getTurno()), VenganzaBelial.mordeim);
                        }
                        else if(this.flagPruebas==2)
                        {
                            this.mensajeSistema=NewCombate.Atacar(NewCombate.getOrdenPersonajes().get(NewCombate.getTurno()), VenganzaBelial.kibito);
                        }
                        this.flagPruebas++;
                        if(this.flagPruebas>3)
                        {
                           this.flagPruebas=0; 
                        }
                        //VenganzaBelial.horacia.setHpActual(50);
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
            }/*else dialogo*/
            /*Escape vuelve al estado inicial*/
            ReiniciarSeleccion();
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
            eleccionJugador=0;
            estadoAnterior=SELOBJETIVO;
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
        if(tasaHuida<1)/*EDIT:0.9*/
        {
            //Objetivo no huye y pierde turno
            this.mensajeSistema="No se ha conseguido huir";
            estadoAnterior=HUYENDO;
            Estado=FINTURNO;
        }
        else
        {
            //Objetivo Huye y se finaliza el combate 
            Estado=FINCOMBATE;          
        }
    }/*private void Huir()*/
    
    private void FinTurno()
    {
        if(estadoDialogo==1){
            estadoDialogo=2;
            Estado=DIALOGO;
        }
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
    
    private void FinCombate(GameContainer gc, StateBasedGame sbg)
    {
        /*Comprobar quien ha ganado el combate y actuar concorde*/
        if(NewCombate.CombateGanado())
        {
            //Si el combate ha sido ganado-> EXP+Drop+Devolver al mapa 
            this.mensajeSistema="YOU WIN\n EXP Recibida: "+NewCombate.getExpCombate()+"\n Items:";
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
                //Reactiva Flag para la proxima vez que se genera un combate
                NuevoCombate=true;
                //EDIT:Volver al mapa
                sbg.enterState(VenganzaBelial.ESTADOMAPAJUEGO);
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
             NuevoCombate=true;
              gc.exit();  
            }
            //Si se ha perdido el combate recargar datos del ultimo punto de control
        }
    }/*private void FinComabte()*/
    
    private void renderOpcionesJugador()
    {
        for (int i = 0; i < NUMOPCIONESBASE; i++) 
        {
            if (eleccionJugador == i) {
                opcionesJugadorTTF.drawString(10, i * 20 + 400, opciones[i]);
            } else {
                opcionesJugadorTTF.drawString(10, i * 20 + 400, opciones[i], notChosen);
            }
        }
    }/*private void renderOpcionesJugador()*/
    
    private void renderHabilidades()
    {
        Jugador PJ= (Jugador)NewCombate.getOrdenPersonajes().get(NewCombate.getTurno());
        for (int i=0;i<NHABILIDADES;i++)
        {
            if(eleccionJugador==i)
            {
               opcionesJugadorTTF.drawString(10,i*20+400,PJ.getHabilidades().get(i).getNombre());
            }
            else{
                opcionesJugadorTTF.drawString(10, i * 20 + 400, PJ.getHabilidades().get(i).getNombre(), notChosen);
            }
        }
    }/* private void renderHabilidades()*/
    
    private void renderObjetos()
    {
        for (int i=0;i<NCONSUMIBLES;i++)
        {
            //EDIT:Ruta de Inventario
            Consumible consumible=(Consumible)VenganzaBelial.atributoGestion.getInv().getItems().get(i);
            if(eleccionJugador==i)
            {
               opcionesJugadorTTF.drawString(10,i*20+400,consumible.getNombre()+" "+consumible.getNumero()+"/10");
            }
            else{
                opcionesJugadorTTF.drawString(10, i * 20 + 400, consumible.getNombre()+" "+consumible.getNumero()+"/10", notChosen);
            }
        }
    }
    
    private void renderSelAliados()
    {
        opcionesJugadorTTF.drawString(10,20+400, VenganzaBelial.atributoGestion.getJugs().get(this.eleccionJugador).getNombre());
    }/*private void renderSelAliados()*/
    
    private void renderSelObjetivo()
    {
        opcionesJugadorTTF.drawString(10,20+400, NewCombate.getEnemigos().get(this.eleccionJugador).getNombre());
    }/*private void renderSelObjetivo()*/
    
    private void renderEnemigos() throws SlickException
    {
       /*Render Enemigos si no estan muertos*/
        int aux;
        int nEnemigos= NewCombate.getEnemigos().size();
        for(aux=0;aux<nEnemigos;aux++)
        {
            if(NewCombate.getEnemigos().get(aux).estaVivo())
            {
                NewCombate.getEnemigos().get(aux).getImagen().draw(aux*300+200, 200, 350, 400);
            }
        }
       /*Debug prints*/
        mensajePantalla.drawString(1000, 50, "HP D.Horacia "+VenganzaBelial.hori.getHpActual()+ "/"+VenganzaBelial.hori.getHp());
        mensajePantalla.drawString(1000, 100, "HP D. Mordeim "+VenganzaBelial.mordi.getHpActual()+ "/"+VenganzaBelial.mordi.getHp());
        mensajePantalla.drawString(1000, 150, "HP D. Kibito "+VenganzaBelial.kibi.getHpActual()+ "/"+VenganzaBelial.kibi.getHp()); 
    }/*private void renderEnemigos() throws SlickException*/
    
    private void renderAvatars() throws SlickException
    {
         /*Update Grafico*/
        float porcentajeBarra=1;//Auxuliar de calculo
            /*EDIT END*/
            /*Horacia status update*/
            if(VenganzaBelial.horacia.estaVivo()){
                if(NewCombate.getOrdenPersonajes().get(NewCombate.getTurno())==VenganzaBelial.horacia)
                {
                   this.avatarSeleccionado.draw(0, 618, 300, 150);
                }
                else{
                    this.avatarVivo.draw(0, 618, 300, 150);
                }                
                mensajePantalla.drawString(110, 658, "HP"+VenganzaBelial.horacia.getHpActual()+ "/"+VenganzaBelial.horacia.getHp(),rojo);
            }
            else{
                this.avatarMuerto.draw(0, 618, 300, 150);
                mensajePantalla.drawString(110, 658, "HP"+0+ "/"+VenganzaBelial.horacia.getHp(),rojo);
            }
            this.contenedor.draw(110, 638, 100, 20);
            porcentajeBarra=(float)VenganzaBelial.horacia.getHpActual()/(float)VenganzaBelial.horacia.getHp();
            porcentajeBarra=compruebaPorcentajeBarra(porcentajeBarra);
            this.hp.draw(111, 643, 97*porcentajeBarra, 10);
            this.contenedor.draw(110, 688, 100, 20);
            porcentajeBarra=(float)VenganzaBelial.horacia.getMpActual()/(float)VenganzaBelial.horacia.getMp();
            porcentajeBarra=compruebaPorcentajeBarra(porcentajeBarra);
            this.mp.draw(111, 693, 97*porcentajeBarra, 10);
            mensajePantalla.drawString(110, 708, "MP "+VenganzaBelial.horacia.getMpActual()+ "/"+VenganzaBelial.horacia.getMp(),azul);
            Avatar1.draw(0, 618, 100, 150);        
            /*MORDEIM STATUS UPDATE*/
            if(VenganzaBelial.mordeim.estaVivo()){
                if(NewCombate.getOrdenPersonajes().get(NewCombate.getTurno())==VenganzaBelial.mordeim)
                {
                   this.avatarSeleccionado.draw(300, 618, 300, 150);
                }
                else{
                    this.avatarVivo.draw(300, 618, 300, 150);
                }                
                mensajePantalla.drawString(410, 658, "HP"+VenganzaBelial.mordeim.getHpActual()+ "/"+VenganzaBelial.mordeim.getHp(),rojo);
            }
            else{
                this.avatarMuerto.draw(300, 618, 300, 150);
                mensajePantalla.drawString(410, 658, "HP"+0+ "/"+VenganzaBelial.mordeim.getHp(),rojo);
            }
            this.contenedor.draw(410, 638, 100, 20);
            porcentajeBarra=(float)VenganzaBelial.mordeim.getHpActual()/(float)VenganzaBelial.mordeim.getHp();
            porcentajeBarra=compruebaPorcentajeBarra(porcentajeBarra);
            this.hp.draw(411, 643, 97*porcentajeBarra, 10);
            this.contenedor.draw(410, 688, 100, 20);
            porcentajeBarra=(float)VenganzaBelial.mordeim.getMpActual()/(float)VenganzaBelial.mordeim.getMp();
            porcentajeBarra=compruebaPorcentajeBarra(porcentajeBarra);
            this.mp.draw(411, 693, 97*porcentajeBarra, 10);
            mensajePantalla.drawString(410, 708, "MP "+VenganzaBelial.mordeim.getMpActual()+ "/"+VenganzaBelial.mordeim.getMp(),azul);
            Avatar2.draw(300, 618, 100, 150);
            /*Kibito Status update*/
            if(VenganzaBelial.kibito.estaVivo()){
                if(NewCombate.getOrdenPersonajes().get(NewCombate.getTurno())==VenganzaBelial.kibito)
                {
                   this.avatarSeleccionado.draw(600, 618, 300, 150);
                }
                else{
                    this.avatarVivo.draw(600, 618, 300, 150);
                }                
                mensajePantalla.drawString(710, 658, "HP"+VenganzaBelial.kibito.getHpActual()+ "/"+VenganzaBelial.kibito.getHp(),rojo);
            }
            else{
                this.avatarMuerto.draw(600, 618, 300, 150);
                mensajePantalla.drawString(710, 658, "HP"+0+ "/"+VenganzaBelial.kibito.getHp(),rojo);
            }
            this.contenedor.draw(710, 638, 100, 20);
            porcentajeBarra=(float)VenganzaBelial.kibito.getHpActual()/(float)VenganzaBelial.kibito.getHp();
            porcentajeBarra=compruebaPorcentajeBarra(porcentajeBarra);
            this.hp.draw(711, 643, 97*porcentajeBarra, 10);
            this.contenedor.draw(710, 688, 100, 20);
            porcentajeBarra=(float)VenganzaBelial.kibito.getMpActual()/(float)VenganzaBelial.kibito.getMp();
            porcentajeBarra=compruebaPorcentajeBarra(porcentajeBarra);
            this.mp.draw(711, 693, 97*porcentajeBarra, 10);
            mensajePantalla.drawString(710, 708, "MP "+VenganzaBelial.kibito.getMpActual()+ "/"+VenganzaBelial.kibito.getMp(),azul);        
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
            mensajePantalla.drawString(10, 560, "Turno de:" + NewCombate.getOrdenPersonajes().get(NewCombate.getTurno()).getNombre());
            
    }/*private void renderAvatars(Graphics g)*/  
    private float compruebaPorcentajeBarra(float porcentajeBarra)
    {
        if(porcentajeBarra<0)
            porcentajeBarra=0;
        return porcentajeBarra;
    }
    private void renderMensajeSistema()
    {
        opcionesJugadorTTF.drawString(10,20, this.mensajeSistema);
    }
    
    private void dialogoTutorial()
    {
        if(input.isKeyPressed(Input.KEY_ENTER))
        {
            estadoDialogo++;
            sonidoSelect.play(1, 0.2f);
        }  
        switch (estadoDialogo)
        {
            case 1:
                if(NewCombate.GestionaPrimerTurno())
                {
                    Estado=OPCIONESBASE;
                }
                else{
                    Estado=TURNOENEMIGO;
                }
                break;
            case 29:
                Estado=FINTURNO;
                break;
        }
    }/*private void dialogoTutorial()*/
    
    private void renderDialogos() throws SlickException
    {
        switch (estadoDialogo)
        {
            case 0:
                Avatar1.draw(30, 610, 115, 125);
                this.ventanaDialogo.draw(0, 600, 1);
                mensajePantalla.drawString(160, 625, "¡MONSTRUOS, TODOS PREPARA- ~~~ AAHHG!");
                break;
            case 2:
                renderMensajeSistema();
                Image flecha= new Image("Imagenes/Avatar/flecha.png");
                flecha.draw(120, 450, 100, 150);
                flecha.rotate(180);
                flecha.draw(50, 60, 200, 150);
                renderAvatars();
                break;
            case 3:
                this.renderOpcionesJugador();
                Avatar1.draw(30, 610, 115, 125);
                this.ventanaDialogo.draw(0, 600, 1);
                mensajePantalla.drawString(160, 625, "Son muy rápidos, rápido utilicemos el comando `Defensa`");
                mensajePantalla.drawString(160, 640, " para reducir los daños y luego... ");
                break;
            case 4:
                this.renderOpcionesJugador();
                Avatar1.draw(30, 610, 115, 125);
                this.ventanaDialogo.draw(0, 600, 1);
                mensajePantalla.drawString(160, 625, "¿¡Donde esta el comando para defenderse!?");
                break;
            case 5:
                this.renderOpcionesJugador();
                Avatar2.draw(30, 610, 115, 125);
                this.ventanaDialogo.draw(0, 600, 1);
                mensajePantalla.drawString(160, 625, "Ah eso, hable con los diseñadores y les dije que no ");
                mensajePantalla.drawString(160, 645, "hacia falta programar esa acción. ");
                break;
            case 6:
                this.renderOpcionesJugador();
                Avatar1.draw(30, 610, 115, 125);
                this.ventanaDialogo.draw(0, 600, 1);
                mensajePantalla.drawString(160, 625, "¿¡Que hiciste que!?");
                break;
            case 7:
                this.renderOpcionesJugador();
                Avatar2.draw(30, 610, 115, 125);
                this.ventanaDialogo.draw(0, 600, 1);
                mensajePantalla.drawString(160, 625, "La mejor defensa es un buen ataque, y me tienes a mi, no");
                mensajePantalla.drawString(160, 640, "hay monstruo que no pueda derrotar con un par de golpes.");
                break;
            case 8:
                this.renderOpcionesJugador();
                Avatar2.draw(30, 610, 115, 125);
                this.ventanaDialogo.draw(0, 600, 1);
                mensajePantalla.drawString(160, 625, "Además, de acuerdo a una encuesta realizada a 20 ");
                mensajePantalla.drawString(160, 640, "personas solo 3 usan el comando defender por lo que no  ");
                mensajePantalla.drawString(160, 655, "importa mucho si nos tomamos la libertad de no meterlo.");
                break;
            case 9:
                this.renderOpcionesJugador();
                Avatar3.draw(30, 610, 115, 125);
                this.ventanaDialogo.draw(0, 600, 1);
                mensajePantalla.drawString(160, 625, "Veo que tampoco te importa mucho saltarte la cuarta pared,");
                mensajePantalla.drawString(160, 640, "pensar que habíamos llegado hasta aquí sin hacerlo...");
                break;
            case 10:
                this.renderOpcionesJugador();
                Avatar3.draw(30, 610, 115, 125);
                this.ventanaDialogo.draw(0, 600, 1);
                mensajePantalla.drawString(160, 625, "Como sea, supongo que podemos aprovecharnos de ello..");
                mensajePantalla.drawString(160, 640, "Mientras mis compañeros discuten paremos el tiempo y así");
                mensajePantalla.drawString(160, 655, "explico tranquilamente el sistema de combate.");
                break;
            case 11:
                this.renderOpcionesJugador();
                Avatar3.draw(30, 610, 115, 125);
                this.ventanaDialogo.draw(0, 600, 1);
                mensajePantalla.drawString(160, 625, "Como habeís podido apreciar mi compañera Horacia ha ");
                mensajePantalla.drawString(160, 640, "sufrido un ataque nada más empezar.");
                break;
            case 12:
                renderMensajeSistema();
                Image flecha2= new Image("Imagenes/Avatar/flecha.png");
                flecha2.draw(120, 450, 100, 150);
                flecha2.rotate(180);
                flecha2.draw(50, 60, 200, 150);
                renderAvatars();
                break;
            case 13:
                this.renderOpcionesJugador();
                Avatar3.draw(30, 610, 115, 125);
                this.ventanaDialogo.draw(0, 600, 1);
                mensajePantalla.drawString(160, 625, "La mecánica para enfrentar enemigos en ");
                mensajePantalla.drawString(160, 640, "``LA VENGANZA DE BELIAL´´ consiste en un combate");
                mensajePantalla.drawString(160, 655, "guiado por un sistema de turnos.");
                break;
            case 14:
                this.renderOpcionesJugador();
                Avatar3.draw(30, 610, 115, 125);
                this.ventanaDialogo.draw(0, 600, 1);
                mensajePantalla.drawString(160, 625, "Al comenzar el combate los participantes se dispondrán");
                mensajePantalla.drawString(160, 640, "en orden según su ``Iniciativa´´.");
                break;
            case 15:
                this.renderOpcionesJugador();
                Avatar3.draw(30, 610, 115, 125);
                this.ventanaDialogo.draw(0, 600, 1);
                mensajePantalla.drawString(160, 625, "La iniciativa depende en gran medida de la velocidad pero");
                mensajePantalla.drawString(160, 640, "puede variar en función de factores aleatorios, como el");
                mensajePantalla.drawString(160, 655, "clima, estar recien comido o no tener ganas de pelear ese");
                mensajePantalla.drawString(160, 670, "día, como yo y mis ganas de trabajar los lunes.");
                break;   
            case 16:
                this.renderOpcionesJugador();
                Avatar3.draw(30, 610, 115, 125);
                this.ventanaDialogo.draw(0, 600, 1);
                mensajePantalla.drawString(160, 625, "En este caso el enemigo estaba muy emocionado y ha sido ");
                mensajePantalla.drawString(160, 640, "más rápido, por lo que nada más empezar el combate  ");
                mensajePantalla.drawString(160, 655, "Horacia se ha llevado un buen golpe,por suerte para  ");
                mensajePantalla.drawString(160, 670, "nosotros, tiene bastante vitalidad.");
                break;
            case 17:
                this.renderOpcionesJugador();
                Avatar3.draw(30, 610, 115, 125);
                this.ventanaDialogo.draw(0, 600, 1);
                mensajePantalla.drawString(160, 625, "Cuando sea el turno de alguno de nosotros se dispondrá  ");
                mensajePantalla.drawString(160, 640, "de un menú de opciones para elegir la acción a desarrollar:");
                break;
            case 18:
                this.renderOpcionesJugador();
                Avatar3.draw(30, 610, 115, 125);
                Image flechah1= new Image("Imagenes/Avatar/flecha.png");
                flechah1.rotate(90);
                flechah1.draw(120, 360, 100, 150);
                this.ventanaDialogo.draw(0, 600, 1);
                mensajePantalla.drawString(160, 625, "``Atacar´´, como su nombre indica permitirá golpear ");
                mensajePantalla.drawString(160, 640, "a UNO de los enemigos presentes, por suerte para mi ");
                mensajePantalla.drawString(160, 655, "el fuego amigo se ha tenido en cuenta y no ha sido ");
                mensajePantalla.drawString(160, 670, "implementado.");
                break;
            case 19:
                this.renderOpcionesJugador();
                Avatar3.draw(30, 610, 115, 125);
                Image flechah2= new Image("Imagenes/Avatar/flecha.png");
                flechah2.rotate(90);
                flechah2.draw(150, 380, 100, 150);
                this.ventanaDialogo.draw(0, 600, 1);
                mensajePantalla.drawString(160, 625, "``Habilidad´´ nos permitirá acceder a las diferentes ");
                mensajePantalla.drawString(160, 640, "acciones especiales de cada personaje a cambio de un gasto");
                mensajePantalla.drawString(160, 655, " de MP. Hay varias habilidades pero se pueden agrupar en ");
                mensajePantalla.drawString(160, 670, "3 grandes grupos:Ataque unico, ataque en area y cura.");
                break;
            case 20:
                this.renderOpcionesJugador();
                Avatar3.draw(30, 610, 115, 125);
                this.ventanaDialogo.draw(0, 600, 1);
                mensajePantalla.drawString(160, 625, "Las habilidades se desbloquean a medida que se sube de ");
                mensajePantalla.drawString(160, 640, "nivel,al mismo tiempo el consumo y la potencia varian a ");
                mensajePantalla.drawString(160, 655, "medida que se avanza, ¿fácil verdad?");
                break;
            case 21:
                this.renderOpcionesJugador();
                Avatar3.draw(30, 610, 115, 125);
                Image flechah3= new Image("Imagenes/Avatar/flecha.png");
                flechah3.rotate(90);
                flechah3.draw(180, 400, 100, 150);
                this.ventanaDialogo.draw(0, 600, 1);
                mensajePantalla.drawString(160, 625, "``Consumibles´´ da acceso al uso de los 3 tipos de items");
                mensajePantalla.drawString(160, 640, " especiales: Pociones de Vida, de Mana y Resurrección. ");
                mensajePantalla.drawString(160, 655, "Al igual que con ``Ataque´´ se han preocupado de no poder ");
                mensajePantalla.drawString(160, 670, "curar a los enemigos asique no te preocupes mucho");
                break;
            case 22:
                this.renderOpcionesJugador();
                Avatar3.draw(30, 610, 115, 125);
                this.ventanaDialogo.draw(0, 600, 1);
                mensajePantalla.drawString(160, 625, "Ah, pero un detalle importante, no podrás curar aliado ");
                mensajePantalla.drawString(160, 640, "muertos y los aliado podrán consumir pociones incluso ");
                mensajePantalla.drawString(160, 655, "estando en perfecto estado, puesto que son limitadas vigila ");
                mensajePantalla.drawString(160, 670, "bien quien se las toma.");
                break;
            case 23:
                this.renderOpcionesJugador();
                Avatar3.draw(30, 610, 115, 125);
                Image flechah4= new Image("Imagenes/Avatar/flecha.png");
                flechah4.rotate(90);
                flechah4.draw(100, 420, 100, 150);
                this.ventanaDialogo.draw(0, 600, 1);
                mensajePantalla.drawString(160, 625, "Por último el comando ``Huir´´nos permitirá realizar una ");
                mensajePantalla.drawString(160, 640, "retirada estratégica, siempre que el enemigo este lo ");
                mensajePantalla.drawString(160, 655, "bastante despistado. Mordeim nunca usaría esta opción, por ");
                mensajePantalla.drawString(160, 670, "suerte él no da las ordenes.");
                break;
            case 24:
                this.renderOpcionesJugador();
                Avatar3.draw(30, 610, 115, 125);
                this.ventanaDialogo.draw(0, 600, 1);
                mensajePantalla.drawString(160, 625, "Cuando sea el turno de uno de tus personajes el recuadro ");
                mensajePantalla.drawString(160, 640, "se mostrará verde, mientras que si esta muerto sera rojo  ");
                mensajePantalla.drawString(160, 655, "y si no es su turno pero esta vivo su fondo será azul, ");
                mensajePantalla.drawString(160, 670, "bastante intuitivo la verdad.");
                break;
            case 25:
                this.renderOpcionesJugador();
                Avatar3.draw(30, 610, 115, 125);
                this.ventanaDialogo.draw(0, 600, 1);
                mensajePantalla.drawString(160, 625, "No creo que haga falta decirlo pero abajo se muetsra la ");
                mensajePantalla.drawString(160, 640, "vida(HP) y el maná(MP) de tus aliados.");
                break;
            case 26:
                this.renderOpcionesJugador();
                renderMensajeSistema();
                Image flecha3= new Image("Imagenes/Avatar/flecha.png");
                flecha3.draw(120, 450, 100, 150);
                renderAvatars();
                break;
            case 27:
                this.renderOpcionesJugador();
                Avatar3.draw(30, 610, 115, 125);
                this.ventanaDialogo.draw(0, 600, 1);
                mensajePantalla.drawString(160, 625, "Por ultimo, al terminar el combate solo aquellos esten ");
                mensajePantalla.drawString(160, 640, "con vida recibirán experiencia por el combate, recuerda,");
                mensajePantalla.drawString(160, 655, "los muertos no aprenden.");
                break;
                
            case 28:
                this.renderOpcionesJugador();
                Avatar3.draw(30, 610, 115, 125);
                this.ventanaDialogo.draw(0, 600, 1);
                mensajePantalla.drawString(160, 625, "Sin mucho más que decir...Intenta no matarme demasiado.");
                break;
                
        }
        //this.ventanaDialogo.draw(0, 600, 1700, 150);
        
    }
}
