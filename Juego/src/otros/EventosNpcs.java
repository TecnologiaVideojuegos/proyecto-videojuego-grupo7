package otros;

import estados.VenganzaBelial;
import items.Arma;
import items.Armadura;
import items.Consumible;
import items.Item;
import java.util.ArrayList;
import npcs.Evento;
import npcs.Vendedor;
import org.newdawn.slick.SlickException;
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
    private final int MAPACARDINAL=7;
    private final int BOSSBOSQUE = 10;
    private final int BANDIDOSPUERTO = 11;
    private final int BOSSPUERTO = 12;
    private final int FANATICOS = 13;
    private final int BOSSCATACUMBAS = 14;
    private final int MINIBOSSMONTANA = 15;
    private final int BOSSMONTANA = 16;
    private final int MINIBOSSCardinal=17;
    private final int BOSSBELIAL = 18;
    private final int BOSSARCHIE = 19;
    //EDIT
    private boolean flagOpcional=true;
    private int controlEventos=0;//Controla la aparición de escenas y "puntos de control" para que no se repitan
                                //las escenas al pasar por el mismo lugar
    /*
    *0= llegando a EscenaDelyolica(En Mapa tutoria)
    *1=Llegando a EscenaBosquePreBoss
    */
    private int nextEstado;
    String medida="Para ello solo chocate ypara dejar de hablar con ellos";//Medida de la linea
    
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
    
    public int comprobarEvento(int x, int y, int mapa) throws SlickException{
        int tipo = 10;//0
        ArrayList<Item> items;
        ArrayList<String> requisitoCategoria;
        this.controlEventos=VenganzaBelial.atributoGestion.getControlEscenas();
        switch(mapa){
            //bosque
            case MAPATUTORIAL:
                if(x == 13 && y == 4){
                    //Charla ini
                    evento = new Evento( "Deberias hablar con la gente.","Para ello solo chocate y para dejar de hablar con ellos","pulsa intro.","",""
                    , "Manolo");
                    tipo = 0;
                }
                if(x == 18 && y == 8){
                    //Charla ini
                    evento = new Evento("¿Ves a aquella chica durmiendo?","Se dice que ella y su ayudante se aparecen en ","cualquier lugar para hacer negocios","",""
                            , "Alfina");
                    tipo = 0;
                }
                else if(x == 29 & y ==3){
                    //Healer
                    evento = new Evento("Hola amigo","Voy a curarte para que puedas seguir luchando con","los peligros de Reynos.","","", "Healer");
                    tipo = 2;
                }
                else if(x== 22 & y ==4){
                    //Vendedor
                    items = new ArrayList<>();
                    requisitoCategoria = new ArrayList<>();
                    requisitoCategoria.add("Mordeim");
                    Consumible pocionVida = new Consumible(20, 0, 5, "PocionVida", "Pocion que sirve para curar tu vida",
                    requisitoCategoria, 1, 50, 20);
                    items.add(pocionVida);
                    vendedor = new Vendedor(items, "Hola", "que tal");
                    tipo = 1;
                }
                else if(x== 5 & y>= 4 && y<=9){
                    //Escena Delyolica
                    if (controlEventos==0)
                    {
                        controlEventos++;
                        VenganzaBelial.atributoGestion.setControlEscenas(controlEventos);
                        nextEstado=VenganzaBelial.ESCENADEYOLICA;
                        tipo=3;
                    }
                    else
                        tipo=10;
                }
                else if(x==34 && y>=5 && y<=7){
                    //Salida de Mapa 
                    nextEstado=VenganzaBelial.ESCENACARRETA;
                    tipo=3;
                    
                }
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
                    evento = new Evento("Hola amigo","Voy a curarte para que puedas seguir luchando con","los peligros de Reynos.","","", "Healer");
                    tipo = 2;
                }
                else if(x==45 && y>9 && y<19){
                    if(controlEventos==1)
                    {
                        controlEventos++;
                        VenganzaBelial.atributoGestion.setControlEscenas(controlEventos);
                        nextEstado=VenganzaBelial.ESCENABOSQUE2;
                        tipo=3;
                    }
                    else
                        tipo=10;//No ocurre nada, asi se salta el case de opciones de evento   
                }
                else if(x==66 && y>50 && y<58){
                    if(controlEventos==2)
                    {
                        controlEventos++;
                        VenganzaBelial.controlMusica.cambiarMusica("Musica/BSO/Escena_Yggdrasil.wav");
                        VenganzaBelial.atributoGestion.setControlEscenas(controlEventos);
                        nextEstado=VenganzaBelial.ESCENABOSQUEPREBOSS;
                        tipo=3;
                    }
                    else
                        tipo=10;//No ocurre nada, asi se salta el case de opciones de evento   
                }
                //EDIT:Pruebas
                else if(x==3 && y==6){
                    evento = new Evento("No soy parte del la historia colega","Aqui estoy para tu pruebas","recuerda eliminarme despues","","", "Tripi"); 
                    tipo = 0;
                }
                break;
                //************************************************************
            case MAPAPUERTO:
                if (x==8 && y==17) {
                    evento = new Evento("Hola amigo","Voy a curarte para que puedas seguir luchando con","los peligros de Reynos.","","", "Healer");
                    tipo = 2;
                }
                else if (x==2 && y==19) {
                    items = new ArrayList<>();
                    ArrayList<String> requisitos = new ArrayList<>();
                    requisitos.add("Horacia");
                    Arma arma2 = new Arma(30, 1, "Espada de bronce", "No muy afilada, pero es mejor que un garrote", requisitos, 5, 51, 25);
                    Armadura armadura2 = new Armadura(30, "Armadura de bronce", "Por fin algo de protección", requisitos, 5, 52, 25);
                    //
                    requisitos.remove(0);
                    requisitos.add("Mordeim");
                    Arma arma3 = new Arma(30, 1, "Navaja", "Para atracar a ancianitas y ser el más malo del barrio", requisitos, 5, 53, 25);
                    Armadura armadura3 = new Armadura(30, "Capa de cuero", "No protege mucho pero abriga contra el frio", requisitos, 5, 54, 25);
                    requisitos.remove(0);
                    requisitos.add("Kibito");
                    Arma arma4 = new Arma(30, 1, "Vara de olivo", "Serás el terror de los alérgicos al olivo", requisitos, 5, 55, 25);
                    Armadura armadura4 = new Armadura(30, "Capa de tela", "La normas impiden llevar al mago algo que proteja demasiado", requisitos, 5, 56, 25);
                    Consumible pocionVida = new Consumible(20, 0, 5, "PocionVida", "Pocion que sirve para curar tu vida",
                        requisitos, 1, 60, 20);
                    Consumible pocionMana = new Consumible(0, 20, 5, "PocionMana", "Pocion que sirve para restaurar tu mana",
                        requisitos, 1, 55, 20);
                    Consumible pocionRes = new Consumible(50, 0, 2, "PocionResucitar", "Pocion que sirve para resucitar un jugador",
                        requisitos, 1, 200, 80);
                    items.add(pocionVida);
                    items.add(pocionMana);
                    items.add(pocionRes);
                    items.add(arma2);
                    items.add(arma3);
                    items.add(arma4);
                    items.add(armadura2);
                    items.add(armadura3);
                    items.add(armadura4);
                    vendedor = new Vendedor(items, "Luis", "que tal");
                    tipo = 1;
                }
                else if (x==11 && y==1) {
                    evento = new Evento("Vaya dia de deposicion","Me acaban de suspender programación avanzada, ","y no se si cortarme las venas o dejarmelas largas...","","", "Rosendo");
                    tipo = 0;
                }
                else if (x==19 && y==12) {
                    evento = new Evento("Lo que faltaba","Ademas de hacerse con el barco del alcalde no nos dejan salir a pescar","","",""
                    , "Masto");
                    tipo = 0;
                }
                else if (x==34 && y==21) {
                    evento = new Evento("Snif...","Puedes enfrentarte al lider de los bandidos directamente,",
                            "pero agradecería que les dieras una paliza a los abusones","que le acompañan.","", "Tolino");
                    tipo = 0;
                }
                else if (x==43 && y==18) {
//                    VenganzaBelial.MapaActual = BANDIDOSPUERTO;
                    VenganzaBelial.atributoGestion.setMapaActual(BANDIDOSPUERTO);
                    nextEstado=VenganzaBelial.ESTADOCOMBATE;
                    tipo = 3;
                }
                else if (x==44 && y==12) {
//                    VenganzaBelial.MapaActual = BANDIDOSPUERTO;
                    VenganzaBelial.atributoGestion.setMapaActual(BANDIDOSPUERTO);
                    nextEstado=VenganzaBelial.ESTADOCOMBATE;
                    tipo = 3;
                }
                else if (x==49 && y==11) {
//                    VenganzaBelial.MapaActual = BOSSPUERTO;
                    VenganzaBelial.atributoGestion.setMapaActual(BOSSPUERTO);
                    nextEstado=VenganzaBelial.ESTADOCOMBATE;
                    tipo = 3;
                }
                break;
                //***********************************************************
            case MAPACIUDADCATACUMBAS:
                if(x == 11 && y == 11){
                    //Vendedor
                    items = new ArrayList<>();
                    ArrayList<String> requisitos = new ArrayList<>();
                    requisitos.add("Horacia");
                    Arma arma2 = new Arma(60, 2, "Maza de Hierro", "Si puedes con ella, puedes con los enemigos", requisitos, 10, 101, 50);
                    Armadura armadura2 = new Armadura(60, "Armadura de silicio", "Supongamos que el silicio protege mas que el bronce", requisitos, 10, 102, 50);
                    //
                    requisitos.remove(0);
                    requisitos.add("Mordeim");
                    Arma arma3 = new Arma(60, 2, "Cuchillo jamonero", "La joya culinaria", requisitos, 10, 103, 50);
                    Armadura armadura3 = new Armadura(60, "Capa de cuero curtida", "Es como la de cuero pero mejor", requisitos, 10, 104, 50);
                    requisitos.remove(0);
                    requisitos.add("Kibito");
                    Arma arma4 = new Arma(60, 2, "Bastón del abuelo", "Para meter las toñas que mete el abuelo", requisitos, 10, 105, 50);
                    Armadura armadura4 = new Armadura(60, "Mantón de la abuela", "No es muy estético pero hace su función", requisitos, 10, 106, 50);
                    Consumible pocionVida = new Consumible(20, 0, 5, "PocionVida", "Pocion que sirve para curar tu vida",
                        requisitos, 1, 60, 20);
                    Consumible pocionMana = new Consumible(0, 20, 5, "PocionMana", "Pocion que sirve para restaurar tu mana",
                        requisitos, 1, 55, 20);
                    Consumible pocionRes = new Consumible(50, 0, 2, "PocionResucitar", "Pocion que sirve para resucitar un jugador",
                        requisitos, 1, 200, 80);
                    items.add(pocionVida);
                    items.add(pocionMana);
                    items.add(pocionRes);
                    items.add(arma2);
                    items.add(arma3);
                    items.add(arma4);
                    items.add(armadura2);
                    items.add(armadura3);
                    items.add(armadura4);
                    vendedor = new Vendedor(items, "Luis", "que tal");
                    tipo = 1;
                }
                else if(x == 18 && y == 22){
                    //Healer
                    evento = new Evento("Hola amigo","Voy a curarte para que puedas seguir luchando con","los peligros de Reynos.","","", "Healer");
                    tipo = 2;
                }
                else if(x==9 && y>=15 && y<=22){
                    if (controlEventos==3)
                    {
                        controlEventos++;
                        VenganzaBelial.atributoGestion.setControlEscenas(controlEventos);
                        nextEstado=VenganzaBelial.ESCENATROYIA2;
                        tipo=3;
                    } 
                    else
                        tipo=10;
                }
                else if(y==0 && x>0 && x<6){
                    nextEstado=VenganzaBelial.ESCENAFANATICO;
                    tipo=3;
                }
                break;
                /*******************************************************************/
            case MAPADUNGEONCATACUMBAS:
                if(x == 37 && y == 14){
                    evento = new Evento("Estas catacumbas están inhóspitas","o eso creo....","comprúebalo tu mismo.","","quien sabe",""); 
                    tipo = 0;    
                }
                else if(x == 4 && y == 27){
                //Vendedor
                    items = new ArrayList<>();
                    requisitoCategoria = new ArrayList<>();
                    requisitoCategoria.add("Mordeim");
                    Consumible pocionVida = new Consumible(20, 0, 5, "PocionVida", "Pocion que sirve para curar tu vida",
                            requisitoCategoria, 1, 50, 20);
                    items.add(pocionVida);
                    vendedor = new Vendedor(items, "Luis", "que tal");                   
                    tipo = 1;    
                }
                else if(x == 20 && y == 23){
                //Healer
                    evento = new Evento("Hola amigo","Voy a curarte para que puedas seguir luchando con","los peligros de Reynos.","","", "Healer");
                    tipo = 2;    
                }
                else if(x==11 && y<33 && y>16){
                    if (controlEventos==4)
                    {
                        controlEventos++;
                        VenganzaBelial.atributoGestion.setControlEscenas(controlEventos);
                        nextEstado=VenganzaBelial.ESCENACATACUMBAS1;
                        tipo=3;
                    } 
                    else
                        tipo=10;
                }
                else if(x==59 && y>20 && y<22){
                 nextEstado=VenganzaBelial.ESCENACATACUMBASPREBOSS;
                 tipo=3;
                }
                break;
            /**********************************************************************/
            case MAPACIUDADMONTANA:
                if (x==8 && y==10) {
                    evento = new Evento("Hola amigo","Voy a curarte para que puedas seguir luchando con","los peligros de Reynos.","","", "Healer");
                    tipo = 2;
                }
                else if (x==13 && y==16) {
                    items = new ArrayList<>();
                    ArrayList<String> requisitos = new ArrayList<>();
                    requisitos.add("Horacia");
                    Arma arma2 = new Arma(90, 3, "Hacha de luz", "Tu primer arma para mayores", requisitos, 15, 151, 75);
                    Armadura armadura2 = new Armadura(90, "Armadura de malla", "Una armadura de verdad para un juego de verdad", requisitos, 15, 152, 75);
                    //
                    requisitos.remove(0);
                    requisitos.add("Mordeim");
                    Arma arma3 = new Arma(90, 3, "Puñal metalSlime", "El nombre esta bien", requisitos, 15, 153, 75);
                    Armadura armadura3 = new Armadura(90, "Capucha de asesino", "No tiene buena fama", requisitos, 15, 154, 75);
                    requisitos.remove(0);
                    requisitos.add("Kibito");
                    Arma arma4 = new Arma(90, 3, "Bastón del arcangel", "Desde la grieta hasta tu casa", requisitos, 15, 155, 75);
                    Armadura armadura4 = new Armadura(90, "Manto mágico de luz", "Para brillar en combate", requisitos, 15, 156, 75);
                    Consumible pocionVida = new Consumible(20, 0, 5, "PocionVida", "Pocion que sirve para curar tu vida",
                        requisitos, 1, 60, 20);
                    Consumible pocionMana = new Consumible(0, 20, 5, "PocionMana", "Pocion que sirve para restaurar tu mana",
                        requisitos, 1, 55, 20);
                    Consumible pocionRes = new Consumible(50, 0, 2, "PocionResucitar", "Pocion que sirve para resucitar un jugador",
                        requisitos, 1, 200, 80);
                    items.add(pocionVida);
                    items.add(pocionMana);
                    items.add(pocionRes);
                    items.add(arma2);
                    items.add(arma3);
                    items.add(arma4);
                    items.add(armadura2);
                    items.add(armadura3);
                    items.add(armadura4);
                    vendedor = new Vendedor(items, "Luis", "que tal");
                    tipo = 1;
                }
                else if (x==13 && y==6) {
                    evento = new Evento("Ups..","Creo que estoy borracha","","","", "Ginebra");
                    tipo = 0;
                }
                else if (x==13 && y==0) {
                    evento = new Evento("Jiji","¿A que jode venir para nada?","","","", "J.M.");
                    tipo = 0;
                }
                else if (x==32 && y==4) {
                    evento = new Evento("JIJIJEJO","Poz el otro dia me ice uno, KACEEES tu con la leña,"," resulta que le quemé la casa","",""
                    , "Ermi");
                    tipo = 0;
                }
                else if (x==23 && y==20) {
                    evento = new Evento("Hola, soy un cartel","Creo que es obvio pero... a lo mejor si sigues el","camino pasas al siguiente mapa","",""
                    , "Cartel");
                    tipo = 0;
                }
                else if (x==39 && (y==19 || y==20)) {
//                    VenganzaBelial.MapaActual = MAPADUNGEONMONTANA;
                    VenganzaBelial.atributoGestion.setMapaActual(MAPADUNGEONMONTANA);
                    //nextEstado=VenganzaBelial.ESTADOMAPAJUEGO;
                    //tipo = 3;
                }
                break;
                //***********************************************************
            case MAPADUNGEONMONTANA:
                if (x==4 && y==6) {
                    evento = new Evento("Hola amigo","Voy a curarte para que puedas seguir luchando con","los peligros de Reynos.","","", "Healer");
                    tipo = 2;
                }
                else if (x==34 && y==30) {
                    evento = new Evento("Hola amigo","Voy a curarte para que puedas seguir luchando con","los peligros de Reynos.","","", "Healer");
                    tipo = 2;
                }
                else if (x==64 && y==6) {
                    evento = new Evento("Hola amigo","Voy a curarte para que puedas seguir luchando con","los peligros de Reynos.","","", "Healer");
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
                    evento = new Evento("Ttttt....","Tenía que haberme puesto una rebequita como me dijo mi madre","","",""
                    , "Congelio");
                    tipo = 0;
                }
                else if (x==23 && y==20) {
                    evento = new Evento("xD","A mi no me mires, solo estoy aqui de relleno","","",""
                    , "Rogelio");
                    tipo = 0;
                }
                else if(x==32){
                    if (controlEventos==5)
                    {
                        controlEventos++;
                        VenganzaBelial.atributoGestion.setControlEscenas(controlEventos);
                        nextEstado=VenganzaBelial.ESCENAMONTANAMINIBOSS;
                        tipo=3;
                    } 
                    else
                        tipo=10;
                }
                else if(x>61 && x<78 && y>45 && y<58){

                        nextEstado=VenganzaBelial.ESCENAMONTANABOSS;
                        tipo=3;
                }
                break;
            case MAPACARDINAL:
                if (x==7 && y==3) {
                    evento = new Evento("Hola amigo","Voy a curarte para que puedas seguir luchando con","los peligros de Reynos.","","", "Healer");
                    tipo = 2;
                }
                else if(x==18 && y>7 && y<14){
                    if (controlEventos==6)
                    {
                        controlEventos++;
                        VenganzaBelial.atributoGestion.setControlEscenas(controlEventos);
                        nextEstado=VenganzaBelial.ESCENACARDINAL1;
                        tipo=3;
                    } 
                    else
                        tipo=10;
                }
                else if(x==53 && y>21 && y<25){
                    if (controlEventos==7)
                    {
                        controlEventos++;
                        VenganzaBelial.atributoGestion.setControlEscenas(controlEventos);
                        nextEstado=VenganzaBelial.ESCENACARDINAL2;
                        tipo=3;
                    } 
                    else
                        tipo=10;
                }
                
                else if(y==42 && x>0 && x<4){
                    if (controlEventos==8)
                    {
                        controlEventos++;
                        VenganzaBelial.atributoGestion.setControlEscenas(controlEventos);
                        nextEstado=VenganzaBelial.ESCENACARDINALMINIBOSS1;
                        tipo=3;
                    } 
                    else
                        tipo=10;
                }
                else if(x==18 && y>55 && y<59){
                        VenganzaBelial.atributoGestion.setControlEscenas(controlEventos);
                        nextEstado=VenganzaBelial.ESCENAFINAL;
                        tipo=3;
                }
                
                else if(y==27 && x>0 && x<17){
                    if (flagOpcional==true)
                    {
                        flagOpcional=false;
                        nextEstado=VenganzaBelial.ESCENACARDINALOPCIONAL;
                        tipo=3;
                    } 
                    else
                        tipo=10;
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

    public int getControlEventos() {
        return controlEventos;
    }

    public void setControlEventos(int controlEventos) {
        this.controlEventos = controlEventos;
    }

    
    
    
    
    
    
    
}
