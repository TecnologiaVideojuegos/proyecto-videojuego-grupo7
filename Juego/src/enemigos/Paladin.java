package enemigos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import otros.Habilidad;
import personajes.Jugador;

public final class Paladin extends Enemigo implements Serializable{
    private ArrayList<Habilidad> habilidades;
    private static final long serialVersionUID = 6L;
    
    public Paladin(int id, int nivel, int hp, int ataque, int defensa) {
        super(id, nivel, hp, ataque, defensa);
        inicializarEnemigo();
    }
    
    @Override
    public void inicializarEnemigo(){
        this.setNombre("Paladin");
        habilidades = new ArrayList<>(); 
        Habilidad hab = new Habilidad("Ataque fuerte", 1, 80, 0, "Ataque fuerte contra aliado", 2);
        habilidades.add(hab);
        this.setHabilidad(habilidades);
        this.setOro(this.getNivel() * 5);
        this.setExpAportada(this.getNivel() * 6);
        this.setVelocidad(9);
        this.setHpActual(this.getHp());    
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
            total = 25;//Edit especial para hacer daño a horacia

        danyoInflingido = jugadores.get(indiceMayorDefensa).getHpActual() - total;
        if(danyoInflingido < 0)
            jugadores.get(indiceMayorDefensa).setHpActual(0);
        else
            jugadores.get(indiceMayorDefensa).setHpActual(danyoInflingido);   
        //Se ha hecho funciÃƒÂ³n para que todos los tipos de enemigos la tengan
        //y no tengamos que estar metiendo el mismo codigo varias veces
        msg = this.escribirMensaje(habilidad, this.getHabilidad().get(0), jugadores.get(indiceMayorDefensa), total);
        return msg;  
    }
}

