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
        Random rand = new Random();
        this.setNombre("Caronte");
        habilidades = new ArrayList<>();
        Habilidad hab = new Habilidad("Llamada de Caronte", 1, 25, 0, "Llamada de Caronte", 2);
        habilidades.add(hab);
        this.setHabilidad(habilidades);
        this.setOro(this.getNivel() * 3);
        this.setExpAportada(100+(int)(rand.nextFloat()*100));
        this.setVelocidad(12);
        this.setHpActual(this.getHp());    
        //this.setImagen("Imagenes/Monstruos/Bosque/Rata.png");
    }

    @Override
    public String estrategiaAtacar(ArrayList<Jugador> jugadores) {
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

        //Indice aleatorio de los que estan vivos
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