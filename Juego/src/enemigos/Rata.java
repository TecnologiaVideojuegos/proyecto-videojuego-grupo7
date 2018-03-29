package enemigos;

import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.SlickException;
import otros.Habilidad;
import personajes.Jugador;

public final class Rata extends Enemigo{
    private ArrayList<Habilidad> habilidades;
    
    public Rata(int nivel, int hp, int ataque, int defensa) throws SlickException {
        super(nivel, hp, ataque, defensa); 
        inicializarEnemigo();
    }
    
    @Override
    public void inicializarEnemigo() throws SlickException{
        this.setNombre("Rata");
        habilidades = new ArrayList<>();  
        Habilidad hab = new Habilidad("Mordisco", 1, 25, 0, "Mordisco feroz de la rata", 2);
        habilidades.add(hab);
        this.setHabilidad(habilidades);
        this.setOro(this.getNivel() * 3);
        this.setExpAportada(this.getNivel() * 5);
        this.setVelocidad(12);
        this.setHpActual(this.getHp());    
        //this.setImagen("Imagenes/Monstruos/Bosque/Rata.png");
    }

    @Override
    public void estrategiaAtacar(ArrayList<Jugador> jugadores) {
        Random rand = new Random();
        float probHab = rand.nextFloat();
        int at = this.getAtaque();
        int danyo, total, indice, danyoInflingido;
        //Se supone que hay vivos porque se comprueba donde se llame
        //Comprobamos si va a hacer habilidad o no
        if (probHab > 0.8)
            at += this.getHabilidad().get(0).getDanyo();
        danyo = at - jugadores.get(0).getDefensa();

        if (danyo > 0)
            total = danyo;
        else
            total = 1;

        //Elegimos a quien atacar simplemente desde 0 a 2 el primero que este vivo
        if (jugadores.get(0).estaVivo())
            indice = 0;
        else if(jugadores.get(1).estaVivo())
            indice = 1;
        else
            indice = 2;

        danyoInflingido = jugadores.get(indice).getHpActual() - total;
        if(danyoInflingido < 0)
            jugadores.get(indice).setHpActual(0);
        else
            jugadores.get(indice).setHpActual(jugadores.get(indice).getHpActual() - total);     
        
    }
}