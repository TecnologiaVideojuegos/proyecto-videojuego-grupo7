package npcs;

public class NPC {
    //Atributos
    private String nombre;
    private String saludo;
    
    //Constructor
    public NPC(String nombre, String saludo) {
        this.nombre = nombre;
        this.saludo = saludo;
    }
    
    //Getters and Setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getSaludo() {
        return saludo;
    }
    public void setSaludo(String saludo) {
        this.saludo = saludo;
    }
}