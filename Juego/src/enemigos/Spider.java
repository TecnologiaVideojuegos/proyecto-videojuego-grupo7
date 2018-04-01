package enemigos;

import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import otros.Habilidad;
import personajes.Jugador;

public final class Spider extends Enemigo{
    private ArrayList<Habilidad> habilidades;
    
    public Spider(int nivel, int hp, int ataque, int defensa) throws SlickException {
        super(nivel, hp, ataque, defensa);   
        inicializarEnemigo();
    }
    
    @Override
    public void inicializarEnemigo() throws SlickException{
        this.setNombre("Araña");
        habilidades = new ArrayList<>();
        Habilidad hab = new Habilidad("Picotazo", 1, 15, 0, "Picotazo", 2);
        habilidades.add(hab);
        this.setHabilidad(habilidades);
        this.setOro(this.getNivel() * 4);
        this.setExpAportada(this.getNivel() * 4);
        this.setVelocidad(14);
        this.setHpActual(this.getHp());    
        //this.setImagen("Imagenes/Monstruos/Bosque/Spider.png");
    }

    @Override
    public void estrategiaAtacar(ArrayList<Jugador> jugadores) {
            
        
    }
}