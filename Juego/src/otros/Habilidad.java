package otros;

import personajes.Jugador;

public class Habilidad {
    //Atributos
    private String nombre;
    private int nivel;
    private int danyo;
    private int costeMP; //Aumenta 5 en cada habilidad por nivel
    private String descripcion;
    private boolean cura;
    private boolean resucita;
    
    //Constructor
    public Habilidad(String nombre, int nivel, int danyo, int costeMP, String descripcion, boolean cura, boolean resucita) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.danyo = danyo;
        this.costeMP = costeMP;
        this.descripcion = descripcion;
        this.cura = cura;
        this.resucita = resucita;
    }
    
    //Getters and Setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    public int getDanyo() {
        return danyo;
    }
    public void setDanyo(int danyo) {
        this.danyo = danyo;
    }
    public int getCosteMP() {
        return costeMP;
    }
    public void setCosteMP(int costeMP) {
        this.costeMP = costeMP;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public boolean isCura() {
        return cura;
    }
    public void setCura(boolean cura) {
        this.cura = cura;
    }
    public boolean isResucita() {
        return resucita;
    }
    public void setResucita(boolean resucita) {
        this.resucita = resucita;
    }
}