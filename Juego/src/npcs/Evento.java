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
    public Evento(String linea1, String linea2, String linea3, String linea4, String linea5, String nombre) {
        super(nombre,linea1);
        this.linea1 = "Hola soy " + this.getNombre();
        this.linea2 = linea1;
        this.linea3 = linea2;
        this.linea4 = linea3;
        this.linea5 = linea4;
//        this.historia = historia;
        //ordenaLineas();
    }
    //Getters and Setters
    
    public String getHistoria() {
        return historia;
    }
    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getLinea1() {
        return linea1;
    }

    public void setLinea1(String linea1) {
        this.linea1 = linea1;
    }

    public String getLinea2() {
        return linea2;
    }

    public void setLinea2(String linea2) {
        this.linea2 = linea2;
    }

    public String getLinea3() {
        return linea3;
    }

    public void setLinea3(String linea3) {
        this.linea3 = linea3;
    }

    public String getLinea4() {
        return linea4;
    }

    public void setLinea4(String linea4) {
        this.linea4 = linea4;
    }

    public String getLinea5() {
        return linea5;
    }

    public void setLinea5(String linea5) {
        this.linea5 = linea5;
    }
}