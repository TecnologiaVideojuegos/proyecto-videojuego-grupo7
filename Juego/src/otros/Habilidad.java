package otros;

import java.util.ArrayList;
import personajes.Jugador;
import personajes.Personaje;

public class Habilidad {
    //Constantes
     public static final int TIPOCURAR=0;
     public static final int TIPORESUCITAR=1;
     public static final int TIPOATACAR=2;
     public static final int TIPODRENARVIDA=3;
     public static final int TIPOAOE=4;
    //Atributos
    private String nombre;
    private int nivel;
    private int danyo;
    private int costeMP; //Aumenta 5 en cada habilidad por nivel
    private String descripcion;
    private int tipoHabilidad;
    //private boolean cura;
    //private boolean resucita;
    //private boolean AOE;//Ataque en Area( Area Of Effect)
    /*EDIT: Atributos TEST*/
    //Constructor
    public Habilidad(String nombre, int nivel, int danyo, int costeMP, String descripcion, int tipo) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.danyo = danyo;
        this.costeMP = costeMP;
        this.descripcion = descripcion;
        this.tipoHabilidad= tipo;
    }
    /*EDIT: METODOS BAJO PRUEBAS*/
  
    public boolean habilidadUsable(Jugador usuario)
    {
        if(this.comprobarLVL(usuario)&& this.comprobarMp(usuario))
        {
            return true;
        }
        else
        {
            return false;
        }
    }/*public boolean habilidadUsable(Jugador usuario)*/
    
    
    private boolean comprobarLVL(Jugador usuario)
    {
        if(usuario.getNivel()<this.nivel)
        {
            return false;
        }
        else
        {
            return true;
        }
    }/*public boolean comprobarLVL(Jugador usuario)*/
    
    private boolean comprobarMp(Jugador usuario)
    {
        if(usuario.getMpActual()<this.costeMP)
        {
            return false;
        }
        else
        {
            return true;
        }
    }/*public boolean comprobarMp(Jugador usuario)*/
    
    
    public boolean usarHabilidad(Jugador usuario, Personaje Objetivo)
    {
        boolean usable=false;
        switch(this.tipoHabilidad)
        {
            case TIPOCURAR:
                usable=this.tipoCura(Objetivo);
                break;
            case TIPORESUCITAR:
                usable=this.tipoResucitar(Objetivo);
                break;
            case TIPOATACAR:
                this.tipoAtaque(usuario, Objetivo);
                usable=true;
                break;
            case TIPODRENARVIDA:
                this.tipoDrenar(usuario, Objetivo);
                usable=true;
                break;
            default:
                usable=false;    
        }
        if(usable==true)
        {
            this.descontarMP(usuario);
        }
        return usable;
    }/*usarHabilidad(Jugador usuario, Personaje Objetivo)*/
    
    public boolean usarHabilidad(Jugador usuario, ArrayList<Personaje> Objetivos)
    {
        this.tipoAOE(usuario, Objetivos);
        this.descontarMP(usuario);
        return true;
    }/*public boolean usarHabilidad(Jugador usuario, ArrayList<Personaje> Objetivos)*/
    
    private void tipoAtaque(Jugador usuario, Personaje Objetivo)
    {
        /*Calculo de daño*/
        int DañoCausado=usuario.getAtaque()*danyo-Objetivo.getDefensa();
        if (DañoCausado>0)
        {
          Objetivo.setHpActual(Objetivo.getHpActual()- DañoCausado);
        }/*if (DañoCausado>0)*/   
    }
    
    private void tipoDrenar(Jugador usuario,Personaje Objetivo)
    {
        /*Calculo de daño*/
        int DañoCausado=usuario.getAtaque()*danyo-Objetivo.getDefensa();
        int Recupera=0;
        if (DañoCausado>0)
        {
          Recupera=(int)(DañoCausado*0.3f);
          Objetivo.setHpActual(Objetivo.getHpActual()- DañoCausado);
          usuario.setHpActual(usuario.getHpActual()+Recupera);
        }/*if (DañoCausado>0)*/ 
        else
        {
            Recupera=1;
           Objetivo.setHpActual(Objetivo.getHpActual()- 1);
           usuario.setHpActual(usuario.getHpActual()+Recupera);
        }
    }/* public void tipoDrenar(Jugador usuario,Personaje Objetivo)*/
    
    private void tipoAOE(Jugador usuario, ArrayList<Personaje> Objetivos)
    {
        int ataqueHabilidad= usuario.getAtaque()*danyo;
        int i;
        for(i=0; i<Objetivos.size();i++)
        {
            int DañoCausado=ataqueHabilidad-Objetivos.get(i).getDefensa();
            if(DañoCausado>0)
            {
                Objetivos.get(i).setHpActual(Objetivos.get(i).getHpActual()-DañoCausado);
            }
            else
            {
                Objetivos.get(i).setHpActual(Objetivos.get(i).getHpActual()-1);
            }
        }
    }/* public void usarAOE(Personaje usuario, ArrayList<Personaje> Objetivos)*/
    
    private boolean tipoCura(Personaje Objetivo)
    {
        //Curar solo si el objetivo esta vivo
        if(Objetivo.estaVivo())
        {
            int PuntosCura= (int)Objetivo.getHp()*danyo/100;
            int VidaTrasCura=PuntosCura+Objetivo.getHpActual();
            if(VidaTrasCura>Objetivo.getHp())
            {
                Objetivo.setHpActual(Objetivo.getHp());
            }
            else
            {
                Objetivo.setHpActual(VidaTrasCura);
            }
            return true;
        }
        else
        {
            return false;
        }
    }/*public void usarCura(Personaje Objetivo)*/
    
    private boolean tipoResucitar(Personaje Objetivo)
    {
        if(Objetivo.estaVivo())
        {
            //No se puede resucitar porque el objetivo esta vivo
            return false;
        }/*if(Objetivo.estaVivo())*/
        else
        {
          Objetivo.setHpActual(1);
          return true;  
        }
    }/*public boolean tipoResucitar(Jugador Objetivo)*/
    
    /*EDIT: METODOS BAJO PRUEBAS END*/
    private void descontarMP(Jugador usuario)
    {
        usuario.setMpActual(usuario.getMpActual()-this.getCosteMP());
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
    public int getDanyo() {
        return danyo;
    }
    public void setDanyo(int danyo) {
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
    
}