package items;

import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable{
    //Atributos
    private String nombre;
    private String descripcion;
    private ArrayList<String> requisitoCategoria;
    private int requisitoNivel;
    private int precioCompra;
    private int precioVenta;
    //EDIT
    private int tipoItem;//Consumible=0/Arma=1//Armadura=2
    //
    private static final long serialVersionUID = 3L;
    
    //Constructor
    public Item(String nombre, String descripcion, ArrayList<String> requisitoCategoria, int requisitoNivel, int precioCompra, int precioVenta) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.requisitoCategoria = requisitoCategoria;
        this.requisitoNivel = requisitoNivel;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
    }
    
    //Getters and Setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public ArrayList<String> getRequisitoCategoria() {
        return requisitoCategoria;
    }
    public void setRequisitoCategoria(ArrayList<String> requisitoCategoria) {
        this.requisitoCategoria = requisitoCategoria;
    }
    public int getRequisitoNivel() {
        return requisitoNivel;
    }
    public void setRequisitoNivel(int requisitoNivel) {
        this.requisitoNivel = requisitoNivel;
    }
    public int getPrecioCompra() {
        return precioCompra;
    }
    public void setPrecioCompra(int precioCompra) {
        this.precioCompra = precioCompra;
    }
    public int getPrecioVenta() {
        return precioVenta;
    }
    public void setPrecioVenta(int precioVenta) {
        this.precioVenta = precioVenta;
    }   

    public int getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(int tipoItem) {
        this.tipoItem = tipoItem;
    }
    
    
}