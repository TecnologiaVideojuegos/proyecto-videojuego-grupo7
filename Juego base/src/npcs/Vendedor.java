package npcs;

import items.Item;
import java.util.ArrayList;

public class Vendedor extends NPC{
    //Atributos
    private ArrayList<Item> items;
    
    //Constructor
    public Vendedor(ArrayList<Item> items, String nombre, String saludo) {
        super(nombre, saludo);
        this.items = items;
    }
    //Getters and Setters
    public ArrayList<Item> getItems() {
        return items;
    }
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    
}