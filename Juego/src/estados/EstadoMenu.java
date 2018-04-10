package estados;

import items.Arma;
import items.Consumible;
import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import otros.Habilidad;
import otros.Inventario;
import personajes.Jugador;

public class EstadoMenu extends BasicGameState{
    /*Maquina estados*/
    private int estado=0;
    private static final int MENUBASE = 0;
    private static final int SELPERSONAJE=1;
    private static final int MENUPJ = 2;
    private static final int MENUINVENTARIO = 3;
    private static final int GUARDANDO = 4;
    private static final int CARGANDO = 5;
    private static final int CAMBIARARMA = 6;
    private static final int CAMBIARARMADURA = 7;
    private static final int SELHABILIDAD=8;
    private static final int SELOBJETIVO=9;
    private int estadoAnterior=0;
    /*Input*/
    private Input input;
    /*Opciones Principales*/
    private static final int NUMOPCIONES = 6;
    private static final int CONTINUAR = 0;
    private static final int PERSONAJES = 1;
    private static final int INVENTARIO = 2;
    private static final int GUARDAR = 3;
    private static final int CARGAR = 4;
    private static final int SALIR = 5;
    /*Personaje bajo tratamiento*/
    private int personajeElegido, habilidadSeleccionada, consumibleSeleccionado;
    /*Texto*/
    private String[] opciones = new String[NUMOPCIONES];
    private String[] opcionesPJ = new String[4];
    private Font letraMenu;
    private TrueTypeFont opcionesJugadorTTF,textoStatus;
    private int eleccionJugador, idEstado;
    private Color notChosen = new Color(153, 204, 255);
    /*Sonido*/
    private Sound sonidoSelect, sonidoError;

    public EstadoMenu(int id) {
        idEstado = id;
    }

    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        input = gc.getInput();
        /**/
        letraMenu = new Font("Verdana", Font.BOLD, 30);
        opcionesJugadorTTF = new TrueTypeFont(letraMenu, true);
        Font letraStatus = new Font("Verdana", Font.BOLD, 20);
        textoStatus= new TrueTypeFont(letraStatus, true);
        /*MENU BASE*/
        opciones[0] = "Continuar";
        opciones[1] = "Personajes";
        opciones[2] = "Inventario";
        opciones[3] = "Guardar partida";
        opciones[4] = "Cargar partida";
        opciones[5] = "Salir";
        /*MENU PJ*/
        opcionesPJ[0]="Habilidades";
        opcionesPJ[1]="Cambiar arma";
        opcionesPJ[2]="Cambiar armadura";
        opcionesPJ[3]="Descripción";
        /*cARGAR sONIDO*/
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        sonidoError=new Sound("Musica/Efectos/error.wav");
        
        
    }/*init*/

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
    {
        switch(estado)
        {
            case MENUBASE:
                renderOpcionesJugador();
                break;
            case SELPERSONAJE:
                renderSelPersonaje();
                break;
            case MENUPJ:
                renderMenuPJ();
                renderStatusPJ();
                break;
            case MENUINVENTARIO:
                renderInventario();
                break;
            case GUARDANDO:
                break;
            case CARGANDO:
                break;
            case CAMBIARARMA:
                break;
            case CAMBIARARMADURA:
                break;
            case SELHABILIDAD:
                renderHabilidades();
                break;
            case SELOBJETIVO:
                renderSelPersonaje();
                break;
        }/*switch*/
    }/*render*/
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        
        switch(estado)
        {
            case MENUBASE:
                OpcionControl(NUMOPCIONES);
                menuBase(gc,sbg);
                break;
            case SELPERSONAJE:
                OpcionControl(3);//La party son 3 PJs
                eleccionPJ();
                break;
            case MENUPJ:
                OpcionControl(4);
                menuPJ();
                break;
            case MENUINVENTARIO:
                int numeroItems=VenganzaBelial.atributoGestion.inv.getItems().size();
                OpcionControl(numeroItems);//La party son 3 PJs
                menuInventario();
                break;
            case GUARDANDO:
                break;
            case CARGANDO:
                break;
            case CAMBIARARMA:
                break;
            case CAMBIARARMADURA:
                break;
            case SELHABILIDAD:
                OpcionControl(5);//Numero de Habilidades
                selHabilidades();
                break;
            case SELOBJETIVO:
                OpcionControl(3);//La party son 3 PJs
                selObjetivo();
                break;
        }/*switch*/
        ReiniciarSeleccion();
    }/*update*/

    
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
    
    private void ReiniciarSeleccion()
    {
        if(input.isKeyPressed(Input.KEY_ESCAPE))
        {
            eleccionJugador=0;
            estado=estadoAnterior;
            estadoAnterior=MENUBASE;
            sonidoSelect.play(1, 0.2f);
        }/**/
    }/*private void ReiniciarSeleccion()*/
    
    private void menuBase(GameContainer gc, StateBasedGame sbg)
    {
        if (input.isKeyPressed(Input.KEY_ENTER)) 
        {
            switch (eleccionJugador) 
            {
                case CONTINUAR:
                    eleccionJugador=0;
                    sbg.enterState(VenganzaBelial.ESTADOMAPAJUEGO);
                    break;
                case PERSONAJES:
                    estado=SELPERSONAJE;
                    break;
                case INVENTARIO:
                    estado=MENUINVENTARIO;
                    break;
                case GUARDAR:
                    estado=GUARDANDO;
                    break;
                case CARGAR:
                    estado=CARGANDO;
                    break;
                case SALIR:
                    gc.exit();
                    break;
            }/*switch*/
            eleccionJugador=0;
        }/*if*/
    }/*private void menuBase()*/
    
    private void eleccionPJ()
    {
        if(input.isKeyPressed(Input.KEY_ENTER))
        {
            sonidoSelect.play();
            personajeElegido=this.eleccionJugador;
            eleccionJugador=0;
            estadoAnterior=SELPERSONAJE;
            estado=MENUPJ;
        }
    }/*private void menuPersonaje()*/
    
    private void menuPJ()
    {
        if(input.isKeyPressed(Input.KEY_ENTER))
        {
            this.sonidoSelect.play();
            estadoAnterior=MENUPJ;
            switch(eleccionJugador)
            {
                case 0://Status y habilidades
                    estado=SELHABILIDAD;
                    break;
                case 1://Cambiar Arma
                    estado=CAMBIARARMA;
                    break;
                case 2://Cambiar Armadura
                    estado=CAMBIARARMADURA;
                    break;
                case 3://Leer Trasfondo
                    break;
            }/*switch(eleccionJugador)*/
            eleccionJugador=0;
        }
    }/*private void menuPJ()*/
    
     private void selHabilidades()/*EDIT: Bajo Pruebas*/
    {
        int tipo;
        Jugador pj= VenganzaBelial.atributoGestion.jugs.get(this.personajeElegido);
        if(input.isKeyPressed(Input.KEY_ENTER))
        {
            if(pj.habilidadUsable(this.eleccionJugador))
            {
               //Guarda la habilidad seleccionada a utilizar
                habilidadSeleccionada=eleccionJugador;
                tipo=pj.getHabilidades().get(eleccionJugador).getTipoHabilidad();
                //Comprueba el tipo de habilidad para decidir contra quien usarla
                if(tipo==Habilidad.TIPOCURAR || tipo==Habilidad.TIPORESUCITAR)
                {
                    eleccionJugador=0;
                    estadoAnterior=SELHABILIDAD;
                    estado=SELOBJETIVO;
                }
                sonidoSelect.play();
            }
            else{
                /*Habilidad no usable*/
                sonidoError.play(1,0.3f);
            }
        }/**/
    }/*private void Habilidades()*/

     private void selObjetivo()
    {
       if(input.isKeyPressed(Input.KEY_ENTER))
        { 
            Jugador pj= VenganzaBelial.atributoGestion.jugs.get(this.personajeElegido);
            switch (estadoAnterior)
            {
                case SELHABILIDAD:
                    //Ejecutar habilidad con el pj correcpondiente sobre el Aliado designado y comprobar que se puede
                    //Si se puede se ejecutará y devolvera true
                    if(pj.usarHabilidad(this.habilidadSeleccionada, VenganzaBelial.Party.get(eleccionJugador))&& pj.habilidadUsable(this.habilidadSeleccionada))
                    {
                        sonidoSelect.play();
                        estado=SELOBJETIVO;
                    }
                    else{
                        //EDIT:Posible mensaje de sistema informando de que no se puede usar la habilidad
                        sonidoError.play(1,0.3f);
                        //this.mensajeSistema="Imposible utilizar esta habilidad en esta situación";
                    }
                    break;
                case MENUINVENTARIO:
                    switch (consumibleSeleccionado)
                    {
                        case 0:
                            if(pj.getInventario().usarPocionVida((Jugador)VenganzaBelial.Party.get(eleccionJugador)))
                            {
                                sonidoSelect.play();
                                estado=SELOBJETIVO; 
                            }
                            else{
                                sonidoError.play(1,0.3f);
                            }
                            break;
                        case 1:
                            if(pj.getInventario().usarPocionMana((Jugador)VenganzaBelial.Party.get(eleccionJugador))){
                                //this.mensajeSistema=("Utilizada "+pj.getInventario().getItems().get(consumibleSeleccionado).getNombre());
                                sonidoSelect.play();
                                estado=SELOBJETIVO; 
                            }
                            else{
                                sonidoError.play(1,0.3f);
                            }
                            break;
                        case 2:
                            if(pj.getInventario().usarPocionRes((Jugador)VenganzaBelial.Party.get(eleccionJugador))){
                                //this.mensajeSistema=("Utilizada "+pj.getInventario().getItems().get(consumibleSeleccionado).getNombre());
                                sonidoSelect.play();
                                estado=SELOBJETIVO; 
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
     
    private void menuInventario()
    {
        if(input.isKeyPressed(Input.KEY_ENTER))
        { 
            if(eleccionJugador<3)//Si se selecciona un Consumible
            {
                sonidoSelect.play();
                consumibleSeleccionado=eleccionJugador;
                this.estadoAnterior=MENUINVENTARIO;
                estado=SELOBJETIVO;
                eleccionJugador=0;
            }
        }
    }/*private void menuInventario()*/
    
    private void cambiarArma()
    {
       if(input.isKeyPressed(Input.KEY_ENTER))
        { 
          //EDIT//Comprobar que el item seleccionado sea un arma y cumpla los requisitos
        } 
    }/*private void cambiarArma()*/
     
    private void renderOpcionesJugador() {
        for (int i = 0; i < NUMOPCIONES; i++) {
            if (eleccionJugador == i) {
                opcionesJugadorTTF.drawString(100, i * 50 + 200, opciones[i]);
            } else {
                opcionesJugadorTTF.drawString(100, i * 50 + 200, opciones[i], notChosen);
            }
        }
    }
    
    private void renderSelPersonaje()
    {
        int offsetX=1000;
        int offsetY=50;
        String nombre= "";
        for(int i=0; i<3;i++)
        {
           nombre=VenganzaBelial.atributoGestion.jugs.get(i).getNombre();
           if (eleccionJugador == i) {
                opcionesJugadorTTF.drawString(100, i * 50 + 200,nombre );
            } else {
                opcionesJugadorTTF.drawString(100, i * 50 + 200, nombre, notChosen);
            } 
        }
        //EDIT:VenganzaBelial.atributoGestion.jugs.get(eleccionJugador).getImagen().draw(600, 50, 200, 600);
        Jugador pj = VenganzaBelial.atributoGestion.jugs.get(eleccionJugador);
        textoStatus.drawString(offsetX, offsetY,  ""+ pj.getNombre());
        offsetY+=20;
        textoStatus.drawString(offsetX, offsetY,  "HP "+ pj.getHpActual()+"/"+pj.getHp());
        offsetY+=20;
        textoStatus.drawString(offsetX, offsetY,  "MP "+ pj.getMpActual()+"/"+pj.getMp());
    }/*private void renderSelPersonaje()*/
    
    private void renderMenuPJ()
    {
        for(int i=0; i<3;i++)
        {
           if (eleccionJugador == i) {
                opcionesJugadorTTF.drawString(100, i * 50 + 200,opcionesPJ[i] );
            } else {
                opcionesJugadorTTF.drawString(100, i * 50 + 200, opcionesPJ[i], notChosen);
            } 
        }
    }/*private void renderMenuPJ()*/
    
    private void renderStatusPJ()
    {
        int offsetX=1000;
        int offsetY=50;
        Color color = new Color(255, 0, 0);
        Jugador pj = VenganzaBelial.atributoGestion.jugs.get(this.personajeElegido);
        //VenganzaBelial.atributoGestion.jugs.get(eleccionJugador).getImagen().draw(600, 50, 200, 600);
        textoStatus.drawString(offsetX, offsetY,  ""+ pj.getNombre());
        offsetY+=20;
        textoStatus.drawString(offsetX, offsetY,  "LVL "+ pj.getNivel());
        offsetY+=20;
        textoStatus.drawString(offsetX, offsetY,  "Exp "+ pj.getExp()+"/"+pj.getExpProxNivel());
        offsetY+=20;
        textoStatus.drawString(offsetX, offsetY,  "HP "+ pj.getHpActual()+"/"+pj.getHp());
        offsetY+=20;
        textoStatus.drawString(offsetX, offsetY,  "MP "+ pj.getMpActual()+"/"+pj.getMp());
        offsetY+=20;
        textoStatus.drawString(offsetX, offsetY,  "Ataque "+ pj.getAtaque()+" ("+pj.getAtaqueBase()+")");
        offsetY+=20;
        textoStatus.drawString(offsetX, offsetY,  "Defensa "+ pj.getDefensa()+" ("+pj.getDefensaBase()+")");
        offsetY+=20;
        textoStatus.drawString(offsetX, offsetY,  "Velocidad "+ pj.getVelocidad());
        offsetY+=20;
        textoStatus.drawString(offsetX, offsetY,  "Critico "+ pj.getHabCritico());
        offsetY+=20;
        textoStatus.drawString(offsetX, offsetY,  "Arma "+ pj.getArma().getNombre());
        offsetY+=20;
        textoStatus.drawString(offsetX, offsetY,  "Armadura "+ pj.getArmadura().getNombre());
    }/*private void renderStatusPJ()*/
    
    private void renderHabilidades()
    {
        Jugador pj= VenganzaBelial.atributoGestion.jugs.get(this.personajeElegido);
        for (int i=0;i<5;i++)
        {
            if(eleccionJugador==i)
            {
               opcionesJugadorTTF.drawString(100,i*50+300,pj.getHabilidades().get(i).getNombre());
            }
            else{
                opcionesJugadorTTF.drawString(100, i * 50 + 300, pj.getHabilidades().get(i).getNombre(), notChosen);
            }
        }
        /*Render Descripcion de Habilidad Bajo Seleccion*/
        this.textoStatus.drawString(800, 100, pj.getHabilidades().get(eleccionJugador).getDescripcion());
    }/* private void renderHabilidades()*/
    
    private void renderInventario()
    {
        Inventario inven = VenganzaBelial.atributoGestion.inv;
        int espaciosLlenos= inven.getItems().size();
        String nombreItem="";
        for (int i=0;i<inven.getCapacidadInv();i++)
        {
            
            
            if(i<espaciosLlenos)
            {
                if(i<3)
                {
                    Consumible consum = (Consumible)inven.getItems().get(i);
                    nombreItem= consum.getNombre()+" ("+consum.getNumero()+"/"+consum.getCapacidad()+")";
                }
                else
                    nombreItem=inven.getItems().get(i).getNombre();
                
                if(eleccionJugador==i)
                {
                   opcionesJugadorTTF.drawString(100,i*50+100,nombreItem);
                }
                else{
                    opcionesJugadorTTF.drawString(100, i * 50 + 100, nombreItem, notChosen);
                }
            }
            else
                opcionesJugadorTTF.drawString(100,i*50+100,"---");
        }
        /*Render Descripcion de Habilidad Bajo Seleccion*/
        this.textoStatus.drawString(800, 100, inven.getItems().get(eleccionJugador).getDescripcion());
    }/* private void renderInventario()*/

}
