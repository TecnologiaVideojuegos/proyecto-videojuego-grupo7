package items;

import java.io.Serializable;
import java.util.ArrayList;

public class Arma extends Item implements Serializable{
    //Atributos
    private int danyo;
    private int critico;
    private static final long serialVersionUID = 3L;

    //Constructor
    public Arma(int danyo, int critico, String nombre, String descripcion, ArrayList<String> requisitoCategoria, int requisitoNivel, int precioCompra, int precioVenta) {
        super(nombre, descripcion, requisitoCategoria, requisitoNivel, precioCompra, precioVenta);
        this.danyo = danyo;
        this.critico = critico;
        this.setTipoItem(1);//Identificador de arma
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