package otros;

import items.Consumible;
import items.Item;
import java.io.Serializable;
import personajes.Jugador;
import java.util.ArrayList;

public class Inventario implements Serializable {
    //Atributos
    private ArrayList<Item> items;
    private int dinero;
    private int capacidadInv;
    private Consumible pocionVida;
    private Consumible pocionMana;
    private Consumible pocionRes;
    private ArrayList<String> requisitoCategoria;
    private static final long serialVersionUID = 3L;
    //Meter consumibles iniciales

    //Constructor
    public Inventario() {
        this.items = new ArrayList<Item>();
        this.dinero = 750;//EDIT
        this.capacidadInv = 10;
        this.requisitoCategoria = new ArrayList<String>();
        requisitoCategoria.add("Mordeim");
        requisitoCategoria.add("Kibito");
        requisitoCategoria.add("Horacia");
        this.pocionVida = new Consumible(30, 0, 5, "PocionVida", "Pocion que sirve para curar tu vida",
            requisitoCategoria, 1, 50, 20);
        this.pocionMana = new Consumible(0, 20, 5, "PocionMana", "Pocion que sirve para curar tu mana",
            requisitoCategoria, 1, 50, 20);
        this.pocionRes = new Consumible(50, 0, 2, "PocionResucitar", "Pocion que sirve para resucitar un jugador",
            requisitoCategoria, 1, 200, 80);
        items.add(pocionVida);
        items.add(pocionMana);
        items.add(pocionRes);
        
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
    public boolean addItem(Item i){
        boolean condicion=false;
        if (items.size()<capacidadInv) {
            items.add(i);
            condicion=true;            
        }
        return condicion;
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
    
    public boolean usarPocionVida(Jugador jug){
        Consumible pocion = (Consumible) items.get(0);
        int curar;
        boolean condicion=false;
        if(pocion.getNumero()>0){
            curar = (int)((jug.getHp()*(pocion.getPh()))/100);
            if (jug.getHpActual() + curar > jug.getHp())
                jug.setHpActual(jug.getHp());
            else
                jug.setHpActual(jug.getHpActual() + curar);
            pocion.setNumero(pocion.getNumero()-1);
            condicion=true;
        }
        return condicion;//System.out.println("No puedes curar porque no teines suficientes pociones de vida"); 
    }/*public boolean usarPocionVida(Jugador jug)*/
    
    public boolean usarPocionMana(Jugador jug){
        Consumible pocion = (Consumible) items.get(1);
        int curar;
        boolean condicion=false;
        if(pocion.getNumero()>0){
            curar = (int)((jug.getMp()*(pocion.getPm()))/100);
            if (jug.getMpActual() + curar > jug.getMp())
                jug.setMpActual(jug.getMp());
            else
                jug.setMpActual(jug.getMpActual() + curar);
            pocion.setNumero(pocion.getNumero()-1);
            condicion=true;
        }
        return condicion;//System.out.println("No puedes curar porque no teines suficientes pociones de vida"); 
    }/*public boolean usarPocionMana(Jugador jug)*/
    
    public boolean usarPocionRes(Jugador jug){
        Consumible pocion = (Consumible) items.get(2);
        boolean condicion=false;
        if(pocion.getNumero()>0 && !jug.estaVivo()){
            jug.setHpActual((int)((jug.getHp()*(pocion.getPh()))/100));
            pocion.setNumero(pocion.getNumero()-1);
            condicion=true;
        }
        return condicion;
    }/*public boolean usarPocionRes(Jugador jug)*/
    
}