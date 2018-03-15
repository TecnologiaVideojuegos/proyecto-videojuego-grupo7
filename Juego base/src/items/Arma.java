package items;

import java.util.ArrayList;

public class Arma extends Item{
    //Atributos
    private int danyo;
    private int critico;

    //Constructor
    public Arma(int danyo, int critico, String nombre, String descripcion, ArrayList<String> requisitoCategoria, int requisitoNivel, int precioCompra, int precioVenta) {
        super(nombre, descripcion, requisitoCategoria, requisitoNivel, precioCompra, precioVenta);
        this.danyo = danyo;
        this.critico = critico;
    }   

    //Getters and Setters
    public int getDanyo() {
        return danyo;
    }
    public void setDanyo(int danyo) {
        this.danyo = danyo;
    }
    public int getCritico() {
        return critico;
    }
    public void setCritico(int critico) {
        this.critico = critico;
    }
}