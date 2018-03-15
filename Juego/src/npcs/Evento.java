package npcs;

public class Evento extends NPC{
    //Atributos
    private String historia;
    
    //Constructor
    public Evento(String historia, String nombre, String saludo) {
        super(nombre, saludo);
        this.historia = historia;
    }
    //Getters and Setters
    public String getHistoria() {
        return historia;
    }
    public void setHistoria(String historia) {
        this.historia = historia;
    }
}