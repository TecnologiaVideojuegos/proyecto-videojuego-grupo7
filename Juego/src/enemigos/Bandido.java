package enemigos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import otros.Habilidad;
import personajes.Jugador;

public final class Bandido extends Enemigo implements Serializable{
    private ArrayList<Habilidad> habilidades;
    private static final long serialVersionUID = 6L;
    
    public Bandido(int id, int nivel, int hp, int ataque, int defensa) {
        super(id, nivel, hp, ataque, defensa);
        inicializarEnemigo();
    }
    
    @Override
    public void inicializarEnemigo(){
        this.setNombre("Bandido");
        habilidades = new ArrayList<>(); 
        Habilidad hab = new Habilidad("Hachazo", 1, 40, 0, "Ataque fuerte con su espada", 2);
        habilidades.add(hab);
        this.setHabilidad(habilidades);
        this.setOro(this.getNivel() * 5);
        this.setExpAportada(this.getNivel() * 6);
        this.setVelocidad(9);
        this.setHpActual(this.getHp());    
    }

    @Override
    public String estrategiaAtacar(ArrayList<Jugador> jugadores) {
        //Ataca al que mas defensa tiene, ya meteremos en un mapa mas dificl
        //que ataque al que menor defensa tiene (en este caso los goblin son tontos)
        String msg;
        Random rand = new Random();
        float probHab = rand.nextFloat();
        int at = this.getAtaque();
        int danyo, total, danyoInflingido;
        boolean habilidad = false;
        int indice;
        ArrayList<Jugador> jugadoresAux= new ArrayList<>();
        //Se supone que hay vivos porque se comprueba donde se llame
        //Comprobamos si va a hacer habilidad o no
        if (probHab > 0.7){
            at += this.getHabilidad().get(0).getDanyo();
            habilidad = true;
        }
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).estaVivo())
                jugadoresAux.add(jugadores.get(i));
        }

        indice = rand.nextInt(jugadoresAux.size());
        
        
        danyo = at - jugadoresAux.get(indice).getDefensa();

        if (danyo > 0)
            total = danyo;
        else
            total = 1;

        danyoInflingido = jugadoresAux.get(indice).getHpActual() - total;
        if(danyoInflingido < 0)
            jugadoresAux.get(indice).setHpActual(0);
        else
            jugadoresAux.get(indice).setHpActual(danyoInflingido);   
        
        msg = this.escribirMensaje(habilidad, this.getHabilidad().get(0), jugadoresAux.get(indice), total);
        return msg;  
    }
}
