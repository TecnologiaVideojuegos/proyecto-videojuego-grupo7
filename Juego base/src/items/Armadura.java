package items;

import java.util.ArrayList;

public class Armadura extends Item{
    //Atributos
    private int defensa;

    //Constructor
    public Armadura(int defensa, String nombre, String descripcion, ArrayList<String> requisitoCategoria, int requisitoNivel, int precioCompra, int precioVenta) {
        super(nombre, descripcion, requisitoCategoria, requisitoNivel, precioCompra, precioVenta);
        this.defensa = defensa;
    }

    //Getters and Setters
    public int getDefensa() {
        return defensa;
    }
    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }
}