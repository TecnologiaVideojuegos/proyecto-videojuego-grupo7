package enemigos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import otros.Habilidad;
import personajes.Jugador;

public final class Fanatico extends Enemigo implements Serializable{
    private ArrayList<Habilidad> habilidades;
    private static final long serialVersionUID = 4L;
    
    public Fanatico(int id, int nivel, int hp, int ataque, int defensa) {
        super(id, nivel, hp, ataque, defensa); 

        inicializarEnemigo();
    }
    
    @Override
    public void inicializarEnemigo(){
        this.setNombre("Fanatico");
        habilidades = new ArrayList<>();  
        Habilidad hab = new Habilidad("Puñetazo demoledor", 1, 45, 0, "Fuerte puñetazo", 2);
        habilidades.add(hab);
        this.setHabilidad(habilidades);
        this.setOro(this.getNivel() * 3);
        this.setExpAportada(this.getNivel() * 5);
        this.setVelocidad(12);
        this.setHpActual(this.getHp());    
        //this.setImagen("Imagenes/Monstruos/Bosque/Rata.png");
    }

    @Override
    public String estrategiaAtacar(ArrayList<Jugador> jugadores) {
        //Ataca al que mas defensa tiene
        String msg;
        Random rand = new Random();
        float probHab = rand.nextFloat();
        int at = this.getAtaque();
        int danyo, total, danyoInflingido;
        boolean habilidad = false;
        int mayorDefensa = 0, indiceMayorDefensa = 0;
        
        if (probHab > 0.7){
            at += this.getHabilidad().get(0).getDanyo();
            habilidad = true;
        }
        
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).estaVivo()){
                if (jugadores.get(i).getDefensa() > mayorDefensa){
                    mayorDefensa = jugadores.get(i).getDefensa();
                    indiceMayorDefensa = i;
                }
            }
        }
        
        danyo = at - jugadores.get(indiceMayorDefensa).getDefensa();

        if (danyo > 0)
            total = danyo;
        else
            total = 1;

        danyoInflingido = jugadores.get(indiceMayorDefensa).getHpActual() - total;
        if(danyoInflingido < 0)
            jugadores.get(indiceMayorDefensa).setHpActual(0);
        else
            jugadores.get(indiceMayorDefensa).setHpActual(danyoInflingido);  
        
        msg = this.escribirMensaje(habilidad, this.getHabilidad().get(0), jugadores.get(indiceMayorDefensa), total);
        return msg;  
    }
}
