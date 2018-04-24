package enemigos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.SlickException;
import otros.Habilidad;
import personajes.Jugador;

public final class Spider extends Enemigo implements Serializable{
    private ArrayList<Habilidad> habilidades;
    private static final long serialVersionUID = 5L;
    
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
        this.setVelocidad(10);
        this.setHpActual(this.getHp());    
        //this.setImagen("Imagenes/Monstruos/Bosque/Spider.png");
    }

    @Override
    public String estrategiaAtacar(ArrayList<Jugador> jugadores) {
        //Ataca al que menos vida tiene de los que estan vivos
        String msg;
        Random rand = new Random();
        float probHab = rand.nextFloat();
        int at = this.getAtaque();
        int danyo, total, danyoInflingido;
        boolean habilidad = false;
        int menorVida = jugadores.get(0).getHp(), indiceMenorVida = 0;
        //Se supone que hay vivos porque se comprueba donde se llame
        //Comprobamos si va a hacer habilidad o no
        if (probHab > 0.8){
            at += this.getHabilidad().get(0).getDanyo();
            habilidad = true;
        }
        
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).estaVivo()){
                if (jugadores.get(i).getHpActual() < menorVida){
                    menorVida = jugadores.get(i).getHpActual();
                    indiceMenorVida = i;
                }
            }
        }
        
        danyo = at - jugadores.get(indiceMenorVida).getDefensa();

        if (danyo > 0)
            total = danyo;
        else
            total = 1;

        danyoInflingido = jugadores.get(indiceMenorVida).getHpActual() - total;
        if(danyoInflingido < 0)
            jugadores.get(indiceMenorVida).setHpActual(0);
        else
            jugadores.get(indiceMenorVida).setHpActual(danyoInflingido);   
        //Se ha hecho funciÃ³n para que todos los tipos de enemigos la tengan
        //y no tengamos que estar metiendo el mismo codigo varias veces
        msg = this.escribirMensaje(habilidad, this.getHabilidad().get(0), jugadores.get(indiceMenorVida), total);
        return msg;     
        
    }
}
