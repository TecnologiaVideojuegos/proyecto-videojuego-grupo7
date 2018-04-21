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
            case 2:
                if(x == 3 && y == 14){
                    items = new ArrayList<>();
                    requisitoCategoria = new ArrayList<>();
                    requisitoCategoria.add("Mordeim");
                    Consumible pocionVida = new Consumible(20, 0, 5, "PocionVida", "Pocion que sirve para curar tu vida",
                            requisitoCategoria, 1, 50, 20);
                    items.add(pocionVida);
                    vendedor = new Vendedor(items, "Luis", "que tal");  
                    tipo = 1;
                }
                else if(x == 11 && y == 17){
                    evento = new Evento("MiHistoria", "Marisa", "que asasas");                   
                    tipo = 0;
                }
                break;
        }
        return tipo;
        
    }
    
    
}
