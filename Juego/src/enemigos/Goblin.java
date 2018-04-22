package enemigos;

import java.util.ArrayList;
import java.util.Random;
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
    public String estrategiaAtacar(ArrayList<Jugador> jugadores) {
        //Ataca al que mas defensa tiene, ya meteremos en un mapa mas dificl
        //que ataque al que menor defensa tiene (en este caso los goblin son tontos)
        String msg;
        Random rand = new Random();
        float probHab = rand.nextFloat();
        int at = this.getAtaque();
        int danyo, total, danyoInflingido;
        boolean habilidad = false;
        int mayorDefensa = 0, indiceMayorDefensa = 0;
        //Se supone que hay vivos porque se comprueba donde se llame
        //Comprobamos si va a hacer habilidad o no
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
        //Se ha hecho funciÃ³n para que todos los tipos de enemigos la tengan
        //y no tengamos que estar metiendo el mismo codigo varias veces
        msg = this.escribirMensaje(habilidad, this.getHabilidad().get(0), jugadores.get(indiceMayorDefensa), danyoInflingido);
        return msg;  
    }
}
