package otros;

import estados.VenganzaBelial;
import items.Consumible;
import items.Item;
import java.util.ArrayList;
import npcs.Evento;
import npcs.Vendedor;
import org.newdawn.slick.geom.Vector2f;

public class EventosNpcs {
    private Vector2f posicionEvento;
    private Evento evento;
    private Vendedor vendedor;
    
    private final int MAPATUTORIAL = 0;
    private final int MAPABOSQUE = 1;
    private final int MAPAPUERTO = 2;
    private final int MAPACIUDADCATACUMBAS = 3;
    private final int MAPADUNGEONCATACUMBAS = 4;
    private final int MAPACIUDADMONTANA = 5;
    private final int MAPADUNGEONMONTANA = 6;
    private final int BOSSBOSQUE = 10;
    private final int BANDIDOSPUERTO = 11;
    private final int BOSSPUERTO = 12;
    private final int FANATICOS = 13;
    private final int BOSSCATACUMBAS = 14;
    private final int GRIFO = 15;
    private final int BOSSMONTANA = 16;
    private final int BOSSBELIAL = 17;
    private final int BOSSARCHIE = 18;
    //EDIT
    private int controlEventos=0;//Controla la aparición de escenas y "puntos de control" para que no se repitan
                                //las escenas al pasar por el mismo lugar
    /*
    *0= llegando a EscenaBosque2
    *1=Llegando a EscenaBosquePreBoss
    */
    private int nextEstado;
    
    public EventosNpcs(){
        posicionEvento= new Vector2f(0,0);
        evento = null;
        vendedor = null;
    }    

    public Evento getEvento() {
        return evento;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }
    
    public int comprobarEvento(int x, int y, int mapa){
        int tipo = 0;
        ArrayList<Item> items;
        ArrayList<String> requisitoCategoria;
        switch(mapa){
            //bosque
            case MAPATUTORIAL:
                break;
            case MAPABOSQUE:
                //Vendedor
                if(x == 83 && y == 11){
                    items = new ArrayList<>();
                    requisitoCategoria = new ArrayList<>();
                    requisitoCategoria.add("Mordeim");
                    Consumible pocionVida = new Consumible(20, 0, 5, "PocionVida", "Pocion que sirve para curar tu vida",
                            requisitoCategoria, 1, 50, 20);
                    items.add(pocionVida);
                    vendedor = new Vendedor(items, "Luis", "que tal");                   
                    tipo = 1;
                }
                //Evento con algo de historia opcional pa rellenar zona mapa opcional
                else if(x == 81 && y == 38){
                    tipo = 0;
                }
                //Vendedor
                else if(x == 51 && y == 53){
                    items = new ArrayList<>();
                    requisitoCategoria = new ArrayList<>();
                    requisitoCategoria.add("Mordeim");
                    Consumible pocionVida = new Consumible(20, 0, 5, "PocionVida", "Pocion que sirve para curar tu vida",
                    requisitoCategoria, 1, 50, 20);
                    items.add(pocionVida);
                    vendedor = new Vendedor(items, "Luis", "que tal");
                    tipo = 1;
                }
                //Healer
                else if(x == 56 && y == 53){
                    evento = new Evento("Voy a curarte para que puedas seguir luchando con"
                            + " los peligros de Reynos", "Healer", "Hola amigo"); 
                    tipo = 2;
                }
                else if(x==45 && y>9 && y<19){
                    if(controlEventos==0)
                    {
                        controlEventos++;
                        nextEstado=VenganzaBelial.ESCENABOSQUE2;
                        tipo=3;
                    }
                    else
                        tipo=10;//No ocurre nada, asi se salta el case de opciones de evento   
                }
                else if(x==66 && y>50 && y<58){
                    if(controlEventos==1)
                    {
                        controlEventos++;
                        nextEstado=VenganzaBelial.ESCENABOSQUEPREBOSS;
                        tipo=3;
                    }
                    else
                        tipo=10;//No ocurre nada, asi se salta el case de opciones de evento   
                }
                //EDIT:Pruebas
                else if(x==3 && y==6){
                    evento = new Evento("Aqui estoy para tu pruebas"
                            + "recuerda eliminarme despues", "Tripi", "No soy parte del la historia colega"); 
                    tipo = 0;
                }
                break;
                //************************************************************
            case MAPAPUERTO:
                if (x==8 && y==17) {
                    evento = new Evento("Voy a curarte para que puedas seguir luchando con"
                            + " los peligros de Reynos", "Healer", "Hola amigo"); 
                    tipo = 2;
                }
                else if (x==2 && y==19) {
                    items = new ArrayList<>();
                    requisitoCategoria = new ArrayList<>();
                    requisitoCategoria.add("Mordeim");
                    Consumible pocionVida = new Consumible(20, 0, 5, "PocionVida", "Pocion que sirve para curar tu vida",
                    requisitoCategoria, 1, 50, 20);
                    items.add(pocionVida);
                    vendedor = new Vendedor(items, "Luis", "que tal");
                    tipo = 1;
                }
                else if (x==11 && y==1) {
                    evento = new Evento("Me acaban de suspender programación avanzada, "
                            + "y no se si cortarme las venas o dejarmelas largas...", "Rosendo", "Vaya dia de deposicion");
                    tipo = 0;
                }
                else if (x==19 && y==12) {
                    evento = new Evento("Ademas de hacerse con el barco del alcalde no nos dejan salir a pescar"
                    , "Masto", "Lo que faltaba");
                    tipo = 0;
                }
                else if (x==34 && y==21) {
                    evento = new Evento("Puedes enfrentarte al lider de los bandidos directamente, pero agradecería que "
                            + "les dieras una paliza a los abusones que le acompañan", "Tolino", "Snif...");
                    tipo = 0;
                }
                else if (x==43 && y==18) {
                    VenganzaBelial.MapaActual = BANDIDOSPUERTO;
                    nextEstado=VenganzaBelial.ESTADOCOMBATE;
                    tipo = 3;
                }
                else if (x==44 && y==12) {
                    VenganzaBelial.MapaActual = BANDIDOSPUERTO;
                    nextEstado=VenganzaBelial.ESTADOCOMBATE;
                    tipo = 3;
                }
                else if (x==49 && y==11) {
                    VenganzaBelial.MapaActual = BOSSPUERTO;
                    nextEstado=VenganzaBelial.ESTADOCOMBATE;
                    tipo = 3;
                }
                break;
                //***********************************************************
            case MAPACIUDADMONTANA:
                if (x==8 && y==10) {
                    evento = new Evento("Voy a curarte para que puedas seguir luchando con"
                            + " los peligros de Reynos", "Healer", "Hola amigo"); 
                    tipo = 2;
                }
                else if (x==13 && y==16) {
                    items = new ArrayList<>();
                    requisitoCategoria = new ArrayList<>();
                    requisitoCategoria.add("Mordeim");
                    Consumible pocionVida = new Consumible(20, 0, 5, "PocionVida", "Pocion que sirve para curar tu vida",
                    requisitoCategoria, 1, 50, 20);
                    items.add(pocionVida);
                    vendedor = new Vendedor(items, "Luis", "que tal");
                    tipo = 1;
                }
                else if (x==13 && y==6) {
                    evento = new Evento("Creo que estoy borracha"
                    , "Ginebra", "Ups..");
                    tipo = 0;
                }
                else if (x==13 && y==0) {
                    evento = new Evento("¿A que jode venir para nada?"
                    , "J.M.", "Jiji");
                    tipo = 0;
                }
                else if (x==32 && y==4) {
                    evento = new Evento("Poz el otro dia me ice uno, KACEEES tu con la leña, resulta que le quemé la casa"
                    , "Ermi", "JIJIJEJO");
                    tipo = 0;
                }
                else if (x==23 && y==20) {
                    evento = new Evento("Creo que es obvio pero... a lo mejor si sigues el camino pasas al siguiente mapa"
                    , "Cartel", "Hola, soy un cartel");
                    tipo = 0;
                }
                else if (x==39 && (y==19 || y==20)) {
                    VenganzaBelial.MapaActual = MAPADUNGEONMONTANA;
                    tipo = 3;
                }
                break;
                //***********************************************************
            case MAPADUNGEONMONTANA:
                if (x==4 && y==6) {
                    evento = new Evento("Voy a curarte para que puedas seguir luchando con"
                            + " los peligros de Reynos", "Healer", "Hola amigo"); 
                    tipo = 2;
                }
                else if (x==34 && y==30) {
                    evento = new Evento("Voy a curarte para que puedas seguir luchando con"
                            + " los peligros de Reynos", "Healer", "Hola amigo"); 
                    tipo = 2;
                }
                else if (x==64 && y==6) {
                    evento = new Evento("Voy a curarte para que puedas seguir luchando con"
                            + " los peligros de Reynos", "Healer", "Hola amigo"); 
                    tipo = 2;
                }
                else if (x==40 && y==5) {
                    items = new ArrayList<>();
                    requisitoCategoria = new ArrayList<>();
                    requisitoCategoria.add("Mordeim");
                    Consumible pocionVida = new Consumible(20, 0, 5, "PocionVida", "Pocion que sirve para curar tu vida",
                    requisitoCategoria, 1, 50, 20);
                    items.add(pocionVida);
                    vendedor = new Vendedor(items, "Luis", "que tal");
                    tipo = 1;
                }
                else if (x==39 && y==42) {
                    evento = new Evento("Tenía que haberme puesto una rebequita como me dijo mi madre"
                    , "Congelio", "Ttttt....");
                    tipo = 0;
                }
                else if (x==23 && y==20) {
                    evento = new Evento("A mi no me mires, solo estoy aqui de relleno"
                    , "Rogelio", "xD");
                    tipo = 0;
                }
                break;
        }
        posicionEvento.x=x;
        posicionEvento.y=y;
        return tipo;
    }

    public int getNextEstado() {
        return nextEstado;
    }

    public void setNextEstado(int nextEstado) {
        this.nextEstado = nextEstado;
    }

    public Vector2f getPosicionEvento() {
        return posicionEvento;
    }

    
    
    
    
    
    
    
}
