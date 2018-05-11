package otros;

import java.io.Serializable;
import java.util.ArrayList;
import personajes.Jugador;
import personajes.Personaje;

public class Habilidad implements Serializable{
    //Constantes
    public static final int TIPOCURAR = 0;
    public static final int TIPORESUCITAR = 1;
    public static final int TIPOATACAR = 2;
    public static final int TIPODRENARVIDA = 3;
    public static final int TIPOAOE = 4;
    //Atributos
    private String nombre;
    private int nivel;
    private float danyo;
    private int costeMP; //Aumenta 5 en cada habilidad por nivel
    private String descripcion;
    private int tipoHabilidad;
    
    private static final long serialVersionUID = 3L;
    //private boolean cura;
    //private boolean resucita;
    //private boolean AOE;//Ataque en Area( Area Of Effect)
    /*EDIT: Atributos TEST*/
    //Constructor
    public Habilidad(String nombre, int nivel, float danyo, int costeMP, String descripcion, int tipo) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.danyo = danyo;
        this.costeMP = costeMP;
        this.descripcion = descripcion;
        this.tipoHabilidad= tipo;
    }
    //Getters and Setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    public float getDanyo() {
        return danyo;
    }
    public void setDanyo(float danyo) {
        this.danyo = danyo;
    }
    public int getCosteMP() {
        return costeMP;
    }
    public void setCosteMP(int costeMP) {
        this.costeMP = costeMP;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipoHabilidad() {
        return tipoHabilidad;
    }

    public void setTipoHabilidad(int tipoHabilidad) {
        this.tipoHabilidad = tipoHabilidad;
    }
    
    /*EDIT: METODOS BAJO PRUEBAS*/
            
    public boolean usarHabilidad(Jugador usuario, Personaje objetivo)
    {
        boolean usable=false;
        switch(this.tipoHabilidad)
        {
            case TIPOCURAR:
                usable=this.tipoCura(objetivo);
                break;
            case TIPORESUCITAR:
                usable=this.tipoResucitar(objetivo);
                break;
            case TIPOATACAR:
                this.tipoAtaque(usuario, objetivo);
                usable=true;
                break;
            case TIPODRENARVIDA:
                this.tipoDrenar(usuario, objetivo);
                usable=true;
                break;
            default:
                usable=false;    
        }
        if(usable)
        {
            this.descontarMP(usuario);
        }
        return usable;
    }/*usarHabilidad(Jugador usuario, Personaje Objetivo)*/
    
    public boolean usarHabilidad(Jugador usuario, ArrayList<Personaje> objetivos)
    {
        this.tipoAOE(usuario, objetivos);
        this.descontarMP(usuario);
        return true;
    }/*public boolean usarHabilidad(Jugador usuario, ArrayList<Personaje> Objetivos)*/
    
    private void tipoAtaque(Jugador usuario, Personaje objetivo)
    {
        /*Calculo de daño*/
        int DanyoCausado=(int)(usuario.getAtaque()*danyo)-objetivo.getDefensa();
        if (DanyoCausado>0)
        {
          objetivo.setHpActual(objetivo.getHpActual()- DanyoCausado);
        }/*if (DañoCausado>0)*/   
        else{
            objetivo.setHpActual(objetivo.getHpActual()- 1);
        }
    }/*private void tipoAtaque(Jugador usuario, Personaje objetivo)*/
    
    private void tipoDrenar(Jugador usuario,Personaje objetivo)
    {
        int DanyoCausado=(int)(usuario.getAtaque()*danyo)-objetivo.getDefensa();
        int Recupera=0;
        if (DanyoCausado>0)
        {
          Recupera=(int)(DanyoCausado*0.2f);
          objetivo.setHpActual(objetivo.getHpActual()- DanyoCausado);
          usuario.setHpActual(usuario.getHpActual()+Recupera);
        }
        else
        {
           Recupera=1;
           objetivo.setHpActual(objetivo.getHpActual()- 1);
           Recupera=1;
           objetivo.setHpActual(objetivo.getHpActual()- 1);
           usuario.setHpActual(usuario.getHpActual()+Recupera);
        }
    }/* public void tipoDrenar(Jugador usuario,Personaje Objetivo)*/
    
    private void tipoAOE(Jugador usuario, ArrayList<Personaje> objetivos)
    {
        int ataqueHabilidad= (int)(usuario.getAtaque()*danyo);
        int i;
        for(i=0; i<objetivos.size();i++)
        {
            int DanyoCausado=ataqueHabilidad-objetivos.get(i).getDefensa();
            if(DanyoCausado>0)
            {
                objetivos.get(i).setHpActual(objetivos.get(i).getHpActual()-DanyoCausado);
            }
            else
            {
                objetivos.get(i).setHpActual(objetivos.get(i).getHpActual()-1);
            }
        }
    }/* public void usarAOE(Personaje usuario, ArrayList<Personaje> Objetivos)*/
    
    private boolean tipoCura(Personaje objetivo)
    {
        //Curar solo si el objetivo esta vivo
        boolean condicion=false;
        if(objetivo.estaVivo())
        {
            int PuntosCura= (int)(objetivo.getHp()*0.5f);
            int VidaTrasCura=PuntosCura+objetivo.getHpActual();
            if(VidaTrasCura>objetivo.getHp())
            {
                objetivo.setHpActual(objetivo.getHp());
            }
            else
            {
                objetivo.setHpActual(VidaTrasCura);
            }
            condicion=true;
        }
        return condicion;
    }/*public void usarCura(Personaje Objetivo)*/
    
    private boolean tipoResucitar(Personaje objetivo)
    {
        boolean condicion=false;
        if(!objetivo.estaVivo())
        {
            //No se puede resucitar porque el objetivo esta vivo
            condicion=true;
            objetivo.setHpActual(objetivo.getHp()/2);
        }
        return condicion;
    }/*public boolean tipoResucitar(Jugador Objetivo)*/
    
    private void descontarMP(Jugador usuario)
    {
        /*EDIT:bajo pruebas*/
        usuario.setMpActual(usuario.getMpActual()-this.getCosteMP()*usuario.getNivel());
    }
    
    
    
    
    
}