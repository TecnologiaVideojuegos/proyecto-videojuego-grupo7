package enemigos;

import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import otros.Habilidad;
import personajes.Jugador;

public final class Goblin extends Enemigo{
    private ArrayList<Habilidad> habilidades;
    
    public Goblin(int nivel, int hp, int ataque, int defensa) throws SlickException {
        super(nivel, hp, ataque, defensa);
        inicializarEnemigo();
    }
    
    @Override
    public void inicializarEnemigo() throws SlickException{
        this.setNombre("Goblin");
        habilidades = new ArrayList<>(); 
        Habilidad hab = new Habilidad("Espadazo", 1, 40, 0, "Ataque fuerte con su espada", 2);
        habilidades.add(hab);
        this.setHabilidad(habilidades);
        this.setOro(this.getNivel() * 5);
        this.setExpAportada(this.getNivel() * 6);
        this.setVelocidad(9);
        this.setHpActual(this.getHp());    
        //this.setImagen("Imagenes/Monstruos/Bosque/Goblin.png");
    }

    @Override
    public void estrategiaAtacar(ArrayList<Jugador> jugadores) {
            
        
    }
}