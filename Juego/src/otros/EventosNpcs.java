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
