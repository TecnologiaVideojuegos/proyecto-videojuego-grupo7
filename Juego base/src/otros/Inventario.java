package otros;

import items.Consumible;
import items.Item;
import personajes.Jugador;
import java.util.ArrayList;

public class Inventario {
    //Atributos
    private ArrayList<Item> items;
    private int dinero;
    private int capacidadInv;
    //Meter consumibles iniciales

    //Constructor
    public Inventario(ArrayList<Item> items) {
        this.items = items;
        this.dinero = 50;
        this.capacidadInv = 10;
    }    

    //Getters and Setters
    public ArrayList<Item> getItems() {
        return items;
    }
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    public int getCapacidadInv() {
        return capacidadInv;
    }
    public void setCapacidadInv(int capacidadInv) {
        this.capacidadInv = capacidadInv;
    }
    public int getDinero() {
        return dinero;
    }
    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
    
    //Metodos
    public void addItem(Item i){
        if (items.size()<capacidadInv) {
            items.add(i);            
        }
        else    System.out.println("Inventario lleno");        
    }
    
    public void borrarItem(Item i){
        if (items.contains(i)){
            items.remove(i);
        }
        else{
            System.out.println("No tienes ese " + i);
        }
    }
    public void tirarObjeto(Item i){
        borrarItem(i);
    }
    /*public void usarConsumible(Consumible c, Jugador jug){
        if (items.contains(c) && c.getNumero() > 0){
            //Hacer la diferencia de si llenara mas vida de la que se tiene
            if (!c.isResucitar()) {
                jug.setHpActual(jug.getHpActual() + (int)((jug.getHp()*(c.getPh()))/100));
                if ((jug.getHpActual() + c.getPh()) > jug.getHp()) {
                    
                    
                }
                
                
            }
            jug.setHpActual(jug.getHpActual() + (int)((jug.getHp()*(c.getPh()))/100));
            jug.setMpActual(c.getPm());
            c.setNumero(c.getNumero()-1);
        }
    }*/
}