package enemigos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import otros.Habilidad;
import personajes.Jugador;

public final class Minotauro extends Enemigo implements Serializable{
    private ArrayList<Habilidad> habilidades;
    private static final long serialVersionUID = 4L;
    
    public Minotauro(int id, int nivel, int hp, int ataque, int defensa) {
        super(id, nivel, hp, ataque, defensa); 
        inicializarEnemigo();
    }
    
    @Override
    public void inicializarEnemigo(){
        this.setNombre("Minotauro");
        habilidades = new ArrayList<>();  
        Habilidad hab = new Habilidad("Pisoton", 1, 70, 0, "Fuerte pisoton", 2);
        habilidades.add(hab);
        this.setHabilidad(habilidades);
        this.setOro(this.getNivel() * 3);
        this.setExpAportada(this.getNivel() * 5);
        this.setVelocidad(11);
        this.setHpActual(this.getHp());    
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
        
        msg = this.escribirMensaje(habilidad, this.getHabilidad().get(0), jugadores.get(indiceMenorVida), total);
        return msg;       
    }
}
