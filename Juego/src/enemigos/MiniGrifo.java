package enemigos;

import java.util.ArrayList;
import java.util.Random;
import otros.Habilidad;
import personajes.Jugador;

public final class MiniGrifo extends Enemigo{
    private ArrayList<Habilidad> habilidades;
    private static final long serialVersionUID = 4L;
    
    public MiniGrifo(int id, int nivel, int hp, int ataque, int defensa) {
        super(id, nivel, hp, ataque, defensa); 
        inicializarEnemigo();
    }
    
    @Override
    public void inicializarEnemigo(){
        this.setNombre("MiniGrifo");
        habilidades = new ArrayList<>();  
        Habilidad hab = new Habilidad("Aleteo", 1, 60, 0, "Aleteo que quita vida a todo el grupo", 4);
        habilidades.add(hab);
        this.setHabilidad(habilidades);
        this.setOro(this.getNivel() * 3);
        this.setExpAportada(this.getNivel() * 5);
        this.setVelocidad(11);
        this.setHpActual(this.getHp());    
    }

    @Override
    public String estrategiaAtacar(ArrayList<Jugador> jugadores) {
        //Ataca a toda la party
        String msg = this.getNombre() + this.getId();
        Random rand = new Random();
        float probHab = rand.nextFloat();
        int at = this.getAtaque();
        int danyo, total, danyoInflingido;
        boolean habilidad = false;
        //Se supone que hay vivos porque se comprueba donde se llame
        //Comprobamos si va a hacer habilidad o no
        if (probHab > 0.80){
            at += this.getHabilidad().get(0).getDanyo();
            habilidad = true;
            msg = msg + " ha usado habilidad " + this.getHabilidad().get(0).getNombre() + 
                            " contra todos los aliados y ha quitado a ";
        }
        else
            msg = msg + " ha atacado a todos los aliados y ha quitado a ";
        
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).estaVivo()){
                danyo = at - jugadores.get(i).getDefensa();
                if (danyo > 0)
                    total = danyo;
                else
                    total = 1;
                danyoInflingido = jugadores.get(i).getHpActual() - total;
                if(danyoInflingido < 0)
                    jugadores.get(i).setHpActual(0);
                else
                    jugadores.get(i).setHpActual(danyoInflingido); 
                msg = msg + jugadores.get(i).getNombre() + 
                            " " + danyoInflingido + " puntos de vida ";
            }
                
        }
        return msg;
    }
}
