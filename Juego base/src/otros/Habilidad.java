package otros;

import personajes.Jugador;

public class Habilidad {
    //Atributos
    private String nombre;
    private int nivel;
    private int danyo;
    private int costeMP; //Aumenta 5 en cada habilidad por nivel
    private String descripcion;
    
    //Constructor
    public Habilidad(String nombre, int nivel, int danyo, int costeMP, String descripcion) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.danyo = danyo;
        this.costeMP = costeMP;
        this.descripcion = descripcion;
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
    
    //Metodos
    private void curar(Jugador jugador){
        //Danyo es multiplicador de ataque base
        jugador.setHp(jugador.getHp()+this.danyo);
        //Gastar mp
        jugador.setMpActual(jugador.getMpActual() - (costeMP + (jugador.getNivel() * 2)));
    }
    
    private void resucitar(Jugador jugador){
        if (!jugador.estaVivo()) {
            jugador.setHp((int)(jugador.getHp()*0.30));
            jugador.setMpActual(jugador.getMpActual() - (costeMP + (jugador.getNivel() * 2)));
        }
        else    System.out.println("Ya esta vivo");
    }
}