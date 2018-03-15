package items;

import java.util.ArrayList;

public class Consumible extends Item{
    //Atributos
    private int ph;  
    private int pm;
    private int numero;
    private int capacidad;

    //Constructor
    public Consumible(int ph, int pm, int numero, String nombre, String descripcion, ArrayList<String> requisitoCategoria, int requisitoNivel, int precioCompra, int precioVenta) {
        super(nombre, descripcion, requisitoCategoria, requisitoNivel, precioCompra, precioVenta);
        this.ph = ph;
        this.pm = pm;
        this.numero = numero;
        //El numero maximo del mismo consumible sera 10 por ejemplo
        this.capacidad = 10;
    }
    //Getters and Setters
    public int getPh() {
        return ph;
    }
    public void setPh(int ph) {
        this.ph = ph;
    }
    public int getPm() {
        return pm;
    }
    public void setPm(int pm) {
        this.pm = pm;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}