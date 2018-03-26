package personajes;

import java.io.Serializable;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Personaje implements Serializable{ //Hacer abstracta por metodo combatir
    //Atributos
    private String nombre;
    private int nivel;
    private int hp;
    private int hpActual;
    private int ataque;
    private int defensa;
    private int velocidad;
    private Image imagen ;
    
    //EDIT: Flag de pruebas combate
    private boolean PJ;    
    public boolean isPJ() {
        return PJ;
    }
    public void setPJ(boolean PJ) {
        this.PJ = PJ;
    }
    //EDIT END
    private static final long serialVersionUID = 3L;
    
    //Constructor
    public Personaje(String nombre, int nivel, int hp, int hpActual, int ataque, int defensa, int velocidad) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.hp = hp;
        this.hpActual = hpActual;
        this.ataque = ataque;
        this.defensa = defensa;
        this.velocidad = velocidad;
    }
    public Personaje() {
    }
    //Getter and setter
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getHpActual() {
        return hpActual;
    }
    public void setHpActual(int hpActual) {
        this.hpActual = hpActual;
    }
    public int getAtaque() {
        return ataque;
    }
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }
    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }
    public int getVelocidad() {
        return velocidad;
    }
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(String ruta) throws SlickException{
        this.imagen = new Image(ruta);
        //this.imagen = imagen;
    }
    
    
    //Metodos
    public boolean estaVivo(){
        return this.hpActual > 0;
    }
    
    //toString
    @Override
    public String toString() {
        return "Personaje{" + "nombre=" + nombre + ", nivel=" + nivel + ", hp=" + hp + ", hpActual=" + hpActual + ", ataque=" + ataque + ", defensa=" + defensa + ", velocidad=" + velocidad + '}';
    }    
}