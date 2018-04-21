package otros;

import items.Consumible;
import items.Item;
import java.util.ArrayList;
import npcs.Evento;
import npcs.Vendedor;

public class EventosNpcs {
    private int x;
    private int y;
    private Evento evento;
    private Vendedor vendedor;
    
    private final int MAPATUTORIAL = 0;
    private final int MAPABOSQUE = 1;
    private final int MAPAPUERTO = 2;
    private final int MAPACIUDADCATACUMBAS = 3;
    private final int MAPADUNGEONCATACUMBAS = 4;
    private final int MAPACIUDADMONTANA = 5;
    private final int MAPADUNGEONMONTANA = 6;
    
    public EventosNpcs(){
        this.x = 0;
        this.y = 0;
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
                    tipo = 1;
                }
                //Healer
                else if(x == 56 && y == 53){
                    evento = new Evento("Voy a curarte para que puedas seguir luchando con"
                            + " los peligros de Reynos", "Healer", "Hola amigo"); 
                    tipo = 2;
                }
                break;
        }
        return tipo;
        
    }
    
    
}
