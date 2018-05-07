package npcs;

import items.Item;
import java.util.ArrayList;
import java.util.Arrays;

public class Evento extends NPC{
    //Atributos
    private String historia;
    private String linea1="";
    private String linea2="";
    private String linea3="";
    private String linea4="";
    private String linea5="";
    
    //Constructor
    public Evento(String historia, String nombre, String saludo) {
        super(nombre, saludo);
        this.historia = historia;
        //ordenaLineas();
    }
    //Getters and Setters
    public String getHistoria() {
        return historia;
    }
    public void setHistoria(String historia) {
        this.historia = historia;
    }
}