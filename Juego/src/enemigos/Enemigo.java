package enemigos;

import items.Item;
import otros.Habilidad;
import personajes.Personaje;
import java.util.ArrayList;

public class Enemigo extends Personaje{
    //Atributos
    private int expAportada;
    private int oro;
    private ArrayList<Habilidad> habilidad;
    
    //Constructor
    public Enemigo(int expAportada, int oro, Item drop, ArrayList<Habilidad> habilidad, String nombre, int nivel, int hp, int hpActual, int ataque, int defensa, int velocidad) {
        super(nombre, nivel, hp, hpActual, ataque, defensa, velocidad);
        this.expAportada = expAportada;
        this.oro = oro;
        this.habilidad = habilidad;
    }    
    //Getters and Setters
    public int getExpAportada() {
        return expAportada;
    }
    public void setExpAportada(int expAportada) {
        this.expAportada = expAportada;
    }
    public int getOro() {
        return oro;
    }
    public void setOro(int oro) {
        this.oro = oro;
    }
    public ArrayList<Habilidad> getHabilidad() {
        return habilidad;
    }
    public void setHabilidad(ArrayList<Habilidad> habilidad) {
        this.habilidad = habilidad;
    }
    
}