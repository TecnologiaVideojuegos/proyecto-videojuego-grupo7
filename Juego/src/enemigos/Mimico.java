package enemigos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import otros.Habilidad;
import personajes.Jugador;

public final class Mimico extends Enemigo implements Serializable{
    private ArrayList<Habilidad> habilidades;
    private static final long serialVersionUID = 4L;
    
    public Mimico(int id, int nivel, int hp, int ataque, int defensa) {
        super(id, nivel, hp, ataque, defensa); 
        inicializarEnemigo();
    }
    
    @Override
    public void inicializarEnemigo(){
        this.setNombre("Mimico");
        habilidades = new ArrayList<>();  
        Habilidad hab = new Habilidad("Mordiscazo", 1, 25, 0, "Mordiscazo mortal", 2);
        habilidades.add(hab);
        this.setHabilidad(habilidades);
        this.setOro(this.getNivel() * 3);
        this.setExpAportada(this.getNivel() * 5);
        this.setVelocidad(12);
        this.setHpActual(this.getHp());
    }

    @Override
    public String estrategiaAtacar(ArrayList<Jugador> jugadores) {
        //Ataca aleatorio y quita 1 o lo mata directamente
        String msg="";
        Random rand = new Random();
        float probHab = rand.nextFloat();
        int at = this.getAtaque();
        int indice, danyoInfligido;
        boolean habilidad = false;
        ArrayList<Jugador> jugadoresAux = new ArrayList<>();
        
        if (probHab > 0.95){
            habilidad = true;
        }
        
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).estaVivo())
                jugadoresAux.add(jugadores.get(i));
        }

        indice = rand.nextInt(jugadoresAux.size());

        if(habilidad)
            danyoInfligido = jugadoresAux.get(indice).getHpActual();
        else
            danyoInfligido = 1;
        
        
        if((jugadoresAux.get(indice).getHpActual() - danyoInfligido)  <= 0)
            jugadoresAux.get(indice).setHpActual(0);
        else
            jugadoresAux.get(indice).setHpActual(jugadoresAux.get(indice).getHpActual()-danyoInfligido); 
                
        msg = this.escribirMensaje(habilidad, this.getHabilidad().get(0), jugadoresAux.get(indice), danyoInfligido);
        return msg;
    }
}
