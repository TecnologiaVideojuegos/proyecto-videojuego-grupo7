package enemigos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import otros.Habilidad;
import personajes.Jugador;

public final class Caronte extends Enemigo implements Serializable{
    private ArrayList<Habilidad> habilidades;
    private static final long serialVersionUID = 4L;
    
    public Caronte(int id, int nivel, int hp, int ataque, int defensa) {
        super(id, nivel, hp, ataque, defensa); 

        inicializarEnemigo();
    }
    
    @Override
    public void inicializarEnemigo(){
        this.setNombre("Mimico");
        habilidades = new ArrayList<>();
        this.setHabilidad(habilidades);
        this.setOro(this.getNivel() * 3);
        this.setExpAportada(this.getNivel() * 5);
        this.setVelocidad(12);
        this.setHpActual(this.getHp());    
        //this.setImagen("Imagenes/Monstruos/Bosque/Rata.png");
    }

    @Override
    public String estrategiaAtacar(ArrayList<Jugador> jugadores) {
        //Ataca aleatorio y quita 1 o lo mata directamente
        String msg;
        Random rand = new Random();
        float probHab = rand.nextFloat();
        int at = this.getAtaque();
        int indice, danyoInfligido;
        boolean habilidad = false;
        ArrayList<Jugador> jugadoresAux = new ArrayList<>();
        
        if (probHab > 0.95){
            at += this.getHabilidad().get(0).getDanyo();
            habilidad = true;
        }
        
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).estaVivo())
                jugadoresAux.add(jugadores.get(i));
        }

        //Indice aleatorio de los que estan vivos
        indice = rand.nextInt(jugadoresAux.size());

        if(habilidad)
            danyoInfligido = jugadores.get(indice).getHpActual();
        else
            danyoInfligido = 1;
        
        
        if((jugadores.get(indice).getHpActual() - danyoInfligido)  <= 0)
            jugadores.get(indice).setHpActual(0);
        else
            jugadores.get(indice).setHpActual(danyoInfligido); 
        
        
        jugadores.get(indice).setHpActual(danyoInfligido);
        
        msg = this.escribirMensaje(habilidad, this.getHabilidad().get(0), jugadores.get(indice), danyoInfligido);
        return msg;
    }
}