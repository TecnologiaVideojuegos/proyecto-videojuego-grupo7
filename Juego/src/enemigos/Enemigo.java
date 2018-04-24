package enemigos;

import items.Item;
import java.io.Serializable;
import otros.Habilidad;
import personajes.Personaje;
import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import personajes.Jugador;

public abstract class Enemigo extends Personaje implements Serializable{
    //Atributos
    private int expAportada;
    private int oro;
    private int id;
    private ArrayList<Habilidad> habilidad;
    private static final long serialVersionUID = 3L;
    
    //Constructor
    public Enemigo(int expAportada, int oro, Item drop, ArrayList<Habilidad> habilidad, String nombre, int nivel, int hp, int hpActual, int ataque, int defensa, int velocidad) {
        super(nombre, nivel, hp, hpActual, ataque, defensa, velocidad);
        this.expAportada = expAportada;
        this.oro = oro;
        this.habilidad = habilidad;
        this.setPJ(false);//Establece que no es PJ
    }    
    public Enemigo(int id, int nivel, int hp, int ataque, int defensa){
        super(nivel, hp, ataque, defensa);
        this.id = id;
        this.setPJ(false);
    }
    
    public Enemigo(){
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
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public ArrayList<Habilidad> getHabilidad() {
        return habilidad;
    }
    public void setHabilidad(ArrayList<Habilidad> habilidad) {
        this.habilidad = habilidad;
    }
    
    public String escribirMensaje(boolean habilidad, Habilidad hab, Jugador jugador, int danyo){
        String msg;
        if (habilidad)
            msg = this.getNombre() + this.getId() + " ha usado la habilidad " + hab.getNombre() + 
                    " contra " + jugador.getNombre() + " y le ha quitado " + danyo + " puntos de vida";
        else
            msg = this.getNombre() + this.getId() + " ha atacado a  " + jugador.getNombre() + 
                    " y le ha quitado " + danyo + " puntos de vida";
        return msg;
    }
    
    public abstract String estrategiaAtacar(ArrayList<Jugador> jugadores);
    public abstract void inicializarEnemigo() throws SlickException;
    
}
