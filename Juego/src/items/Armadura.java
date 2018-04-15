package items;

import java.io.Serializable;
import java.util.ArrayList;

public class Armadura extends Item implements Serializable{
    //Atributos
    private int defensa;
    private static final long serialVersionUID = 3L;    

    //Constructor
    public Armadura(int defensa, String nombre, String descripcion, ArrayList<String> requisitoCategoria, int requisitoNivel, int precioCompra, int precioVenta) {
        super(nombre, descripcion, requisitoCategoria, requisitoNivel, precioCompra, precioVenta);
        this.defensa = defensa;
        this.setTipoItem(2);//Identificador de Armadura
    }

    //Getters and Setters
    public int getDefensa() {
        return defensa;
    }
    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }
}