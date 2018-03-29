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
    private Image Avatar1, Avatar2, Avatar3;
    private TrueTypeFont mensajePantalla;
    private Font TipoLetra  =new Font("Verdana", Font.PLAIN, 20);    
    private Color rojo = new Color (256,0,0);
    private Color verde = new Color (0,256,0);
    private Color azul = new Color (0,0,256);    
    private Music OST;
    private Sound sonidoAtaque;
    private Sound sonidoMuerto;
    private String mensajeSistema= "";
    //private SpriteSheet sprite;
    //private Animation animacion;
    /*EDIT*/
    private int flagPruebas=0;
    
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
        /*ost*/
        OST= new Music("Musica/BSO/Ablaze.wav");
        sonidoAtaque= new Sound("Musica/Efectos/Sword4.wav");
        sonidoMuerto= new Sound("Musica/Efectos/Cry2.wav");
        /*Input*/
        this.input = gc.getInput();
        /**/
        letraMenu = new Font("Verdana", Font.ROMAN_BASELINE, 25);
        opcionesJugadorTTF = new TrueTypeFont(letraMenu, true);
        opciones[0] = "Atacar";
        opciones[1] = "Habilidad";
        opciones[2] = "Consumible";
        opciones[3]= "Huir";
        /*EDIT:Test*/
        //this.sprite= new SpriteSheet("Imagenes/Animaciones/ataque.png",200,200);
        //this.animacion= new Animation(sprite, 200);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
    {
        renderAvatars(g);
        renderEnemigos();
        /*Switch Case para visualizar opciones en funcion del estado*/
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
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException 
    {
        if(NuevoCombate)//Ejecutar Con Cada nuevo combate
        {
            /*Genera Nuevo Combate*/
            NewCombate= new Combate(VenganzaBelial.Party, VenganzaBelial.MapaActual);//
            /*EDIT: Mirar donde añadir imagenes al los enemigos*/
            VenganzaBelial.hori.setImagen("Imagenes/Monstruos/Test1.png");
            VenganzaBelial.mordi.setImagen("Imagenes/Monstruos/Test2.png");
            VenganzaBelial.kibi.setImagen("Imagenes/Monstruos/Test3.png");
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
                    //EDIT: Eliminar
                    if(this.flagPruebas==0)
                    {
                        this.mensajeSistema=NewCombate.Atacar(NewCombate.getOrdenPersonajes().get(NewCombate.getTurno()), VenganzaBelial.mordeim);
                    }
                    else if(this.flagPruebas==1)
                    {
                        this.mensajeSistema=NewCombate.Atacar(NewCombate.getOrdenPersonajes().get(NewCombate.getTurno()), VenganzaBelial.horacia);
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
                    OpcionControl(VenganzaBelial.Party.size());
                    //Seleccion de aliado al que lanzar Habilidad
                    selAliado();
                    break;
                case FINTURNO:
                    /*Gestiona el final del turno, comprobando si el combate esta acabado o no*/
                    FinTurno();
                    break;
                case FINCOMBATE:
                    FinCombate(gc);
                    //sbg.enterState(VenganzaBelial.ESTADOMENUINICIO);//EDIT:Eliminar
                    OST.stop();
                    break;
            }
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
            }/**/
        }/*if(Estado!=TURNOENEMIGO && Estado!=HUYENDO && Estado!=FINTURNO)*/
    }/*private void ReiniciarSeleccion()*/
    
    private void OpcionBase()
    {
        if (input.isKeyPressed(Input.KEY_ENTER))
        {  
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
            }
        }/**/
    }/*private void Habilidades()*/
    
    private void selConsumible()
    {
        if(input.isKeyPressed(Input.KEY_ENTER))
        { 
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
            //pj.getHabilidades().get(this.habilidadSeleccionada).usarHabilidad(pj, NewCombate.getEnemigos().get(eleccionJugador));
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
                    if(pj.usarHabilidad(this.habilidadSeleccionada, VenganzaBelial.Party.get(eleccionJugador)))
                    {
                        /*EDIT: Cambiar Mensaje Sistema*/
                        this.mensajeSistema=(pj.getNombre()+" ha utilizado "+pj.getHabilidades().get(habilidadSeleccionada).getNombre()+" sobre "+VenganzaBelial.Party.get(eleccionJugador).getNombre());
                        if(pj.getHabilidades().get(habilidadSeleccionada).getTipoHabilidad()==Habilidad.TIPORESUCITAR){
                            NewCombate.GestionaResurrecion(VenganzaBelial.Party.get(eleccionJugador));
                        }
                        eleccionJugador=0;
                        estadoAnterior=SELALIADO;
                        Estado=FINTURNO;
                    }
                    else{
                        //EDIT:Posible mensaje de sistema informando de que no se puede usar la habilidad
                        this.mensajeSistema="Imposible utilizar esta habilidad en esta situación";
                    }
                    break;
                case SELCONSUMIBLE:
                    this.mensajeSistema=("Imposible utilizar objeto en esta situación");
                    switch (consumibleSeleccionado)
                    {
                        case 0:
                            if(pj.getInventario().usarPocionVida((Jugador)VenganzaBelial.Party.get(eleccionJugador))){
                                this.mensajeSistema=("Utilizada "+pj.getInventario().getItems().get(consumibleSeleccionado).getNombre());
                                estadoAnterior=SELALIADO;//EDIT:Buscar forma optima de cambiar de estado
                                Estado=FINTURNO;
                            }
                            break;
                        case 1:
                            if(pj.getInventario().usarPocionMana((Jugador)VenganzaBelial.Party.get(eleccionJugador))){
                                this.mensajeSistema=("Utilizada "+pj.getInventario().getItems().get(consumibleSeleccionado).getNombre());
                                estadoAnterior=SELALIADO;//EDIT:Buscar forma optima de cambiar de estado
                                Estado=FINTURNO;
                            }
                            break;
                        case 2:
                            if(pj.getInventario().usarPocionRes((Jugador)VenganzaBelial.Party.get(eleccionJugador))){
                                this.mensajeSistema=("Utilizada "+pj.getInventario().getItems().get(consumibleSeleccionado).getNombre());
                                NewCombate.GestionaResurrecion(VenganzaBelial.Party.get(eleccionJugador));
                                estadoAnterior=SELALIADO;//EDIT:Buscar forma optima de cambiar de estado
                                Estado=FINTURNO;
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
    
    private void FinCombate(GameContainer gc)
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
                for(aux=0;aux<VenganzaBelial.Party.size();aux++)
                {
                    Jugador pj=(Jugador)VenganzaBelial.Party.get(aux);
                    //Recibe la experiencia solo si no esta muerto al final del combate
                    if(pj.estaVivo())
                    {
                        pj.setExp(pj.getExp()+exp);
                        if(pj.puedeSubir())
                        {
                            pj.subirNivel();
                        }/*if(pj.puedeSubir())*/
                    }/* if(pj.estaVivo())*/
                }/*for(aux=0;aux<VenganzaBelial.Party.size();aux++)*/
                //Reactiva Flag para la proxima vez que se genera un combate
                NuevoCombate=true;
                //EDIT:ELIMINAR OBJETO COMBATE O VACIAR
                gc.exit();  
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
        Jugador PJ= (Jugador)NewCombate.getOrdenPersonajes().get(NewCombate.getTurno());
        for (int i=0;i<NCONSUMIBLES;i++)
        {
            if(eleccionJugador==i)
            {
               opcionesJugadorTTF.drawString(10,i*20+400,PJ.getInventario().getItems().get(i).getNombre());
            }
            else{
                opcionesJugadorTTF.drawString(10, i * 20 + 400, PJ.getInventario().getItems().get(i).getNombre(), notChosen);
            }
        }
    }
    
    private void renderSelAliados()
    {
        opcionesJugadorTTF.drawString(10,20+400, VenganzaBelial.Party.get(this.eleccionJugador).getNombre());
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
    
    private void renderAvatars(Graphics g) throws SlickException
    {
         /*Update Grafico*/
        /*Imagenes del fondo*/
            fondo = new Image("Imagenes/BackBattle/Bosque.jpg");
            /*EDIT:Imagenes de los personajes*/
            Avatar1 =  new Image("Imagenes/Personajes/Horacia.jpg");
            Avatar2 =  new Image("Imagenes/Personajes/Mordeim.jpg");
            Avatar3 =  new Image("Imagenes/Personajes/Kibito.jpg");
            fondo.draw(0, 0, 1366, 768);
            Rectangle rect1 = new Rectangle(0,0, 230,100);
            Rectangle rect2 = new Rectangle(0,100, 230, 100);
            Rectangle rect3 = new Rectangle(0,200, 230, 100);
            g.draw(rect1);
            g.draw(rect2);
            g.draw(rect3);
            g.fill(rect1);
            g.fill(rect2);
            g.fill(rect3);
            Avatar1.draw(0, 0, 100, 100);
            Avatar2.draw(0, 100, 100, 100);
            Avatar3.draw(0, 200, 100, 100);
            /*EDIT END*/
            /*Horacia status update*/
            mensajePantalla = new TrueTypeFont(TipoLetra, true);
            if(VenganzaBelial.horacia.estaVivo()){
                mensajePantalla.drawString(110, 20, "HP"+VenganzaBelial.horacia.getHpActual()+ "/"+VenganzaBelial.horacia.getHp(),rojo);
            }
            else{
                mensajePantalla.drawString(110, 20, "HP"+0+ "/"+VenganzaBelial.horacia.getHp(),rojo);
            }
            mensajePantalla.drawString(110, 50, "MP "+VenganzaBelial.horacia.getMpActual()+ "/"+VenganzaBelial.horacia.getMp(),azul);
            /*Mordeim Status Update*/
            if(VenganzaBelial.mordeim.estaVivo()){
               mensajePantalla.drawString(110, 120, "HP "+VenganzaBelial.mordeim.getHpActual()+ "/"+VenganzaBelial.mordeim.getHp(),rojo); 
            }
            else{
               mensajePantalla.drawString(110, 120, "HP "+0+ "/"+VenganzaBelial.mordeim.getHp(),rojo); 
            }
            mensajePantalla.drawString(110, 150, "MP "+VenganzaBelial.mordeim.getMpActual()+ "/"+VenganzaBelial.mordeim.getMp(),azul);
            /*Kibito Status update*/
            if(VenganzaBelial.kibito.estaVivo()){
                mensajePantalla.drawString(110, 220, "HP "+VenganzaBelial.kibito.getHpActual()+ "/"+VenganzaBelial.kibito.getHp(),rojo);
            }
            else{
                mensajePantalla.drawString(110, 220, "HP "+0+ "/"+VenganzaBelial.kibito.getHp(),rojo);
            }
            mensajePantalla.drawString(110, 250, "MP "+VenganzaBelial.kibito.getMpActual()+ "/"+VenganzaBelial.kibito.getMp(),azul);
                        
            /*EDIT:Debug prints*/
            mensajePantalla.drawString(1100, 400, "Estado: "+Estado);
            mensajePantalla.drawString(1100, 350, "EleccionJugador: "+eleccionJugador);
            mensajePantalla.drawString(1100, 300, "Nparticipantes"+ NewCombate.getOrdenPersonajes().size());
            mensajePantalla.drawString(1100, 250, "Party"+ NewCombate.getAliadosRestantes());   
            mensajePantalla.drawString(1100, 200, "Enemigos"+ NewCombate.getEnemigosRestantes());   
            mensajePantalla.drawString(1100, 450, "Turno: " + NewCombate.getTurno());
            mensajePantalla.drawString(1100, 500, "Party"+ VenganzaBelial.Party.size());
            Consumible consumible=(Consumible)VenganzaBelial.inventario.getItems().get(0);
            mensajePantalla.drawString(1100, 550, "PotisVida"+consumible.getNumero());
            consumible=(Consumible)VenganzaBelial.inventario.getItems().get(1);
            mensajePantalla.drawString(1100, 600, "PotisMana"+consumible.getNumero());
            consumible=(Consumible)VenganzaBelial.inventario.getItems().get(2);
            mensajePantalla.drawString(1100, 650, "PotisRes"+consumible.getNumero());
            /**/
            mensajePantalla.drawString(500, 20, "Turno de:" + NewCombate.getOrdenPersonajes().get(NewCombate.getTurno()).getNombre());
    }/*private void renderAvatars(Graphics g)*/  
    
    private void renderMensajeSistema()
    {
        opcionesJugadorTTF.drawString(10,600, this.mensajeSistema);
    }
}
