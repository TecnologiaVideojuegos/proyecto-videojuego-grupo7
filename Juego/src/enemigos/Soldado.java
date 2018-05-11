package enemigos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import otros.Habilidad;
import personajes.Jugador;

public final class Soldado extends Enemigo implements Serializable{
    private ArrayList<Habilidad> habilidades;
    private static final long serialVersionUID = 4L;
    
    public Soldado(int id, int nivel, int hp, int ataque, int defensa) {
        super(id, nivel, hp, ataque, defensa); 
        inicializarEnemigo();
    }
    
    @Override
    public void inicializarEnemigo(){
        this.setNombre("Soldado");
        habilidades = new ArrayList<>();  
        Habilidad hab = new Habilidad("Ataque infernal", 1, 70, 0, "Ataque infernal contra aliado", 2);
        habilidades.add(hab);
        this.setHabilidad(habilidades);
        this.setOro(this.getNivel() * 3);
        this.setExpAportada(this.getNivel() * 5);
        this.setVelocidad(10);
        this.setHpActual(this.getHp());    
    }

    @Override
    public String estrategiaAtacar(ArrayList<Jugador> jugadores) {
        //Ataca aleatorio
        String msg;
        Random rand = new Random();
        float probHab = rand.nextFloat();
        int at = this.getAtaque();
        int danyo, total, indice, danyoInflingido;
        boolean habilidad = false;
        ArrayList<Jugador> jugadoresAux = new ArrayList<>();
        
        if (probHab > 0.9){
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
        if(danyoInflingido < 0)
            jugadoresAux.get(indice).setHpActual(0);
        else
            jugadoresAux.get(indice).setHpActual(danyoInflingido);   
        
        msg = this.escribirMensaje(habilidad, this.getHabilidad().get(0), jugadoresAux.get(indice), total);
        return msg;
    }
}
