package estados;

import static estados.VenganzaBelial.ESTADOMENUINICIO;
import java.awt.Font;
import java.util.ArrayList;
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
    private static final int SELALIADO=7;//Seleccion de pj al que tirar consumible
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
    /*opciones SELCONSUMIBLE*/
    private static final int NCONSUMIBLES=3;
    /*Tipos de letra y Mensajes*/
    private String[] opciones = new String[NUMOPCIONESBASE];
    private Font letraMenu;
    private TrueTypeFont opcionesJugadorTTF;
    private int eleccionJugador, idEstado, habilidadSeleccionada;
    private Color notChosen = new Color(153, 204, 255);
    private boolean NuevoCombate = true;
    /*Control de Combate*/
    //private ArrayList<Personaje> ordenPersonajes = new ArrayList<Personaje>();7
    private Combate NewCombate;
    /*Avatar Image Control*/
    private Image Fondo;
    private Image Avatar1, Avatar2, Avatar3;
    private ArrayList<Image> avatarEnemigo;
    private TrueTypeFont HMP;
    private Font TipoLetra  =new Font("Verdana", Font.PLAIN, 20);    
    private Color rojo = new Color (256,0,0);
    private Color verde = new Color (0,256,0);
    private Color azul = new Color (0,0,256);    
    private Music OST;
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
        /*Input*/
        this.input = gc.getInput();
        /**/
        letraMenu = new Font("Verdana", Font.ROMAN_BASELINE, 25);
        opcionesJugadorTTF = new TrueTypeFont(letraMenu, true);
        opciones[0] = "Atacar";
        opciones[1] = "Habilidad";
        opciones[2] = "Consumible";
        opciones[3]= "Huir";
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
    {
        renderAvatars(g);
        /*Switch Case para visualizar opciones en funcion del estado*/
        renderOpcionesJugador();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException 
    {
        if(NuevoCombate==true)//Ejecutar Con Cada nuevo combate
        {
            /*Genera Nuevo Combate*/
            NewCombate= new Combate(VenganzaBelial.Party, VenganzaBelial.MapaActual);//
            /*Añadir Imagenes en funcion del enemigo*/
            avatarEnemigo = new ArrayList<Image>();
            avatarEnemigo.add(new Image("Imagenes/Monstruos/Rata.png"));
            avatarEnemigo.add(new Image("Imagenes/Monstruos/Rata.png"));
            avatarEnemigo.add(new Image("Imagenes/Monstruos/Rata.png"));
            NuevoCombate = false;
            OST.loop();
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
                    break;
                case  HUYENDO://Opciones base del pj
                    Huir();
                    break;
                case  TURNOENEMIGO://Turno automatico Enemigo
                    //EDIT: Eliminar
                    NewCombate.Atacar(VenganzaBelial.horaciaenemiga, VenganzaBelial.horacia);
                    //VenganzaBelial.horacia.setHpActual(50);
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
                    //FinCombate();
                    sbg.enterState(VenganzaBelial.ESTADOMENUINICIO);//EDIT:Eliminar
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
                Estado=OPCIONESBASE;
            }/**/
        }/*if(Estado!=TURNOENEMIGO && Estado!=HUYENDO && Estado!=FINTURNO)*/
    }/*private void ReiniciarSeleccion()*/
    
    private void OpcionBase()
    {
        if (input.isKeyPressed(Input.KEY_ENTER))
        {            
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
           NewCombate.Atacar(NewCombate.getOrdenPersonajes().get(IndiceTurno), NewCombate.getEnemigos().get(eleccionJugador)); 
            /*Cambio de estado a turno final*/
            eleccionJugador=0;//Resetea el indice de seleccion de opciones para el nuevo estado*/
            Estado=FINTURNO; 
        }/**/
             
    }/*private void Atacando()*/
    
    private void selHabilidades()/*EDIT: Bajo Pruebas*/
    {
        int tipo;
        int IndiceTurno=NewCombate.getTurno();
        Jugador PJ= (Jugador)NewCombate.getOrdenPersonajes().get(IndiceTurno);
        if(input.isKeyPressed(Input.KEY_ENTER))
        {   
            //Comprobar que se puede usar la habilidad
            if(PJ.getHabilidades().get(eleccionJugador).habilidadUsable(PJ))
            {
                //Guarda la habilidad seleccionada a utilizar
                habilidadSeleccionada=eleccionJugador;
                //Comprueba el tipo de habilidad para decidir contra quien usarla
                tipo=PJ.getHabilidades().get(eleccionJugador).getTipoHabilidad();
                //Cambia de estado en función del tipo de habilidad
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
                        PJ.getHabilidades().get(habilidadSeleccionada).usarHabilidad(PJ, NewCombate.getEnemigos());
                        Estado=FINTURNO;
                        break;
                }
                eleccionJugador=0;
            }
            else{
                //La habilidad no se puede usar por falta de nivel o falta de Mp
            }
        }/**/
    }/*private void Habilidades()*/
    
    private void selObjetivo()
    {
        if(input.isKeyPressed(Input.KEY_ENTER))
        { 
            int IndiceTurno=NewCombate.getTurno();
            Jugador PJ= (Jugador)NewCombate.getOrdenPersonajes().get(IndiceTurno);
            //Ejecutar habilidad con el PJ correcpondiente sobre el Objetivo designado
            PJ.getHabilidades().get(this.habilidadSeleccionada).usarHabilidad(PJ, NewCombate.getEnemigos().get(this.eleccionJugador));
            eleccionJugador=0;
            Estado=FINTURNO;
        }
    }/*private void selObjetivo()*/
    
    private void selAliado()
    {
       if(input.isKeyPressed(Input.KEY_ENTER))
        { 
            int IndiceTurno=NewCombate.getTurno();
            Jugador PJ= (Jugador)NewCombate.getOrdenPersonajes().get(IndiceTurno);
            //Ejecutar habilidad con el PJ correcpondiente sobre el Aliado designado y comprobar que se puede
            //Si se puede se ejecutará y devolvera true
            if(PJ.getHabilidades().get(this.habilidadSeleccionada).usarHabilidad(PJ, NewCombate.getEnemigos().get(this.eleccionJugador)))
            {
                eleccionJugador=0;
                Estado=FINTURNO;
            }
            else{
                //Si o se puede no se ejecutara y devolvera false
            }
            
        } 
    }/*private void selAliado()*/
    
    
    private void Huir()
    {
        double TasaHuida = Math.random();
        if(TasaHuida<1)
        {
            //Objetivo no huye y pierde turno
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
        /*Gestiona muertes del ultimo turno*/
        NewCombate.GestionaMuertes();
        /*Comprueba que el combate no se haya acabado*/
        //if(NewCombate.CombateAcabado())
        //{
        //    Estado=FINCOMBATE;
        //}/*if(NewCombate.CombateAcabado())*/
        //else
        //{
            if(NewCombate.GestionaSiguienteTurno())//Si el siguiente turno es de jugador
            {
                Estado=OPCIONESBASE;
            }
            else{
                Estado=TURNOENEMIGO;
            }
        //}/*else*/
    }/*private void FinTurno()*/
    
    private void FinCombate()
    {
        NuevoCombate=true;
        /*Comprobar quien ha ganado el combate y actuar concorde*/
        if(NewCombate.CombateGanado())
        {
            //Eliminar objeto combate
           //Si el combate ha sido ganado-> EXP+Drop+Devolver al mapa 
        }
        else
        {
            //Eliminar objeto combate
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
        int IndiceTurno=NewCombate.getTurno();
        //String HabName=NewCombate.getOrdenPersonajes().get(IndiceTurno).getHabilidades().getNombre;
        for (int i=0;i<NHABILIDADES;i++)
        {
            if(eleccionJugador==i)
            {
                //opcionesJugadorTTF.drawString(10,i*20+400, )
            }
        }
    }
    
    private void renderAvatars(Graphics g) throws SlickException
    {
         /*Update Grafico*/
        /*Imagenes del fondo*/
            Fondo = new Image("Imagenes/BackBattle/Bosque.jpg");
            /*Imagenes de los personajes*/
            Avatar1 =  new Image("Imagenes/Personajes/Horacia.jpg");
            Avatar2 =  new Image("Imagenes/Personajes/Mordeim.jpg");
            Avatar3 =  new Image("Imagenes/Personajes/Kibito.jpg");
            Fondo.draw(0, 0, 1366, 768);
            Rectangle rect1 = new Rectangle(0,10, 230,100);
            Rectangle rect2 = new Rectangle(0,110, 230, 100);
            Rectangle rect3 = new Rectangle(0,210, 230, 100);
            g.draw(rect1);
            g.draw(rect2);
            g.draw(rect3);
            g.fill(rect1);
            g.fill(rect2);
            g.fill(rect3);
            Avatar1.draw(0, 10, 100, 100);
            Avatar2.draw(0, 110, 100, 100);
            Avatar3.draw(0, 210, 100, 100);
            /*Horacia status update*/
            HMP = new TrueTypeFont(TipoLetra, true);
            HMP.drawString(110, 30, "HP"+VenganzaBelial.horacia.getHpActual()+ "/"+VenganzaBelial.horacia.getHp(),rojo);
            HMP = new TrueTypeFont(TipoLetra, true);
            HMP.drawString(110, 60, "MP "+VenganzaBelial.horacia.getMpActual()+ "/"+VenganzaBelial.horacia.getMp(),azul);
            /*Mordeim Status Update*/
            HMP.drawString(110, 130, "HP "+VenganzaBelial.mordeim.getHpActual()+ "/"+VenganzaBelial.mordeim.getHp(),rojo);
            HMP.drawString(110, 160, "MP "+VenganzaBelial.mordeim.getMpActual()+ "/"+VenganzaBelial.mordeim.getMp(),azul);
            /*Kibito Status update*/
            HMP.drawString(110, 230, "HP "+VenganzaBelial.kibito.getHpActual()+ "/"+VenganzaBelial.kibito.getHp(),rojo);
            HMP.drawString(110, 260, "MP "+VenganzaBelial.kibito.getMpActual()+ "/"+VenganzaBelial.kibito.getMp(),azul);
            /*Render 
            Enemigos si no estan muertos*/        
            avatarEnemigo.get(0).draw(550, 500, 100, 100);
            avatarEnemigo.get(1).draw(650, 500, 100, 100);
            avatarEnemigo.get(2).draw(750, 500, 100, 100);  
            HMP.drawString(250, 250, "HPe "+VenganzaBelial.horaciaenemiga.getHpActual()+ "/"+VenganzaBelial.horaciaenemiga.getHp());
            /*Debug*/
            HMP.drawString(500, 400, "Estado: "+Estado);
            HMP.drawString(500, 350, "EleccionJugador: "+eleccionJugador);
            HMP.drawString(500, 300, "Nparticipantes"+ NewCombate.getOrdenPersonajes().size());
            HMP.drawString(500, 250, "Turno: " + NewCombate.getTurno());
    }/*private void renderAvatars(Graphics g)*/  
  
    private void Sound()
    {
        //private Sound sound;
        //private Music music;
        
    }
}
