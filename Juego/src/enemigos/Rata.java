package enemigos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import otros.Habilidad;
import personajes.Jugador;

public final class Rata extends Enemigo implements Serializable{
    private ArrayList<Habilidad> habilidades;
    private static final long serialVersionUID = 4L;
    
    public Rata(int id, int nivel, int hp, int ataque, int defensa) {
        super(id, nivel, hp, ataque, defensa); 

        inicializarEnemigo();
    }
    
    @Override
    public void inicializarEnemigo(){
        this.setNombre("Rata");
        habilidades = new ArrayList<>();  
        Habilidad hab = new Habilidad("Mordisco", 1, 25, 0, "Mordisco feroz de la rata", 2);
        habilidades.add(hab);
        this.setHabilidad(habilidades);
        this.setOro(this.getNivel() * 3);
        this.setExpAportada(this.getNivel() * 5);
        this.setVelocidad(12);
        this.setHpActual(this.getHp());
    }

    @Override
    public String estrategiaAtacar(ArrayList<Jugador> jugadores) {
        //Ataca al primer aliado que este vivo
        String msg;
        Random rand = new Random();
        float probHab = rand.nextFloat();
        int at = this.getAtaque();
        int danyo, total, indice, danyoInflingido;
        boolean habilidad = false;
        //Se supone que hay vivos porque se comprueba donde se llame
        //Comprobamos si va a hacer habilidad o no
        if (probHab > 0.8){
            at += this.getHabilidad().get(0).getDanyo();
            habilidad = true;
        }

        //Elegimos a quien atacar simplemente desde 0 a 2 el primero que este vivo
        if (jugadores.get(0).estaVivo())
            indice = 0;
        else if(jugadores.get(1).estaVivo())
            indice = 1;
        else
            indice = 2;

        danyo = at - jugadores.get(indice).getDefensa();

        if (danyo > 0)
            total = danyo;
        else
            total = 1;
        
        danyoInflingido = jugadores.get(indice).getHpActual() - total;
        if(danyoInflingido < 0)
            jugadores.get(indice).setHpActual(0);
        else
            jugadores.get(indice).setHpActual(danyoInflingido);   
        //Se ha hecho funciÃ³n para que todos los tipos de enemigos la tengan
        //y no tengamos que estar metiendo el mismo codigo varias veces
        msg = this.escribirMensaje(habilidad, this.getHabilidad().get(0), jugadores.get(indice), total);
        return msg;
    }
}

