package enemigos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import otros.Habilidad;
import personajes.Jugador;

public final class Murciegalo extends Enemigo implements Serializable{
    private ArrayList<Habilidad> habilidades;
    private static final long serialVersionUID = 4L;
    
    public Murciegalo(int id, int nivel, int hp, int ataque, int defensa) {
        super(id, nivel, hp, ataque, defensa); 

        inicializarEnemigo();
    }
    
    @Override
    public void inicializarEnemigo(){
        this.setNombre("Murciegalo");
        habilidades = new ArrayList<>();  
        Habilidad hab = new Habilidad("Mordisquito curativo", 1, 50, 0, "Mordisquito que cura", 3);
        habilidades.add(hab);
        this.setHabilidad(habilidades);
        this.setOro(this.getNivel() * 3);
        this.setExpAportada(this.getNivel() * 5);
        this.setVelocidad(12);
        this.setHpActual(this.getHp());    
    }

    @Override
    public String estrategiaAtacar(ArrayList<Jugador> jugadores) {
        //Ataca aleatorio y si es habilidad se cura
        String msg;
        Random rand = new Random();
        float probHab = rand.nextFloat();
        int at = this.getAtaque();
        int danyo, total, indice, danyoInflingido;
        boolean habilidad = false;
        ArrayList<Jugador> jugadoresAux = new ArrayList<>();
        //Se supone que hay vivos porque se comprueba donde se llame
        //Comprobamos si va a hacer habilidad o no
        if (probHab > 0.8){
            at += this.getHabilidad().get(0).getDanyo();
            habilidad = true;
        }

        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).estaVivo())
                jugadoresAux.add(jugadores.get(i));
        }

        //Indice aleatorio de los que estan vivos
        indice = rand.nextInt(jugadoresAux.size());
        
        danyo = at - jugadoresAux.get(indice).getDefensa();

        if (danyo > 0)
            total = danyo;
        else
            total = 1;
        
        danyoInflingido = jugadoresAux.get(indice).getHpActual() - total;
        if(habilidad){
            if((this.getHpActual() + danyoInflingido) >= this.getHp())
                this.setHpActual(this.getHp());
            else
                this.setHpActual(danyoInflingido);
        }
            
        if(danyoInflingido < 0)
            jugadoresAux.get(indice).setHpActual(0);
        else
            jugadoresAux.get(indice).setHpActual(danyoInflingido);   
        
        msg = this.escribirMensaje(habilidad, this.getHabilidad().get(0), jugadoresAux.get(indice), total);
        return msg;
    }
}

