package enemigos;

import java.util.ArrayList;
import java.util.Random;
import otros.Habilidad;
import personajes.Jugador;

public final class Fafnir extends Enemigo {

    private ArrayList<Habilidad> habilidades;
    private static final long serialVersionUID = 4L;

    public Fafnir(int id, int nivel, int hp, int ataque, int defensa) {
        super(id, nivel, hp, ataque, defensa);
        inicializarEnemigo();
    }

    @Override
    public void inicializarEnemigo() {
        this.setNombre("Fafnir");
        habilidades = new ArrayList<>();
        Habilidad hab1 = new Habilidad("Llamarada", 1, 500, 0, "Fuego de dragón que quita vida a todo el grupo", 4);
        habilidades.add(hab1);
        Habilidad hab2 = new Habilidad("Cuchillada", 1, 400, 0, "Corte de dragón que quita vida a todo el grupo", 4);
        habilidades.add(hab2);
        this.setHabilidad(habilidades);
        this.setOro(this.getNivel() * 3);
        this.setExpAportada(this.getNivel() * 5);
        this.setVelocidad(11);
        this.setHpActual(this.getHp());
    }

    @Override
    public String estrategiaAtacar(ArrayList<Jugador> jugadores) {
        String msg = "";
        Random rand = new Random();
        int at = this.getAtaque();
        int danyo, total, danyoInflingido;
        int numeroHab;
        ArrayList<Jugador> jugadoresAux = new ArrayList<>();

        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).estaVivo()) {
                jugadoresAux.add(jugadores.get(i));
            }
        }

        if (rand.nextInt(2) == 0) {
            at += this.getHabilidad().get(0).getDanyo();
            numeroHab=0;
        } else {
            at += this.getHabilidad().get(1).getDanyo();
            numeroHab=1;

        }

        for (int i = 0; i < jugadoresAux.size(); i++) {
            danyo = at - jugadoresAux.get(i).getDefensa();
            if (danyo > 0) {
                total = danyo;
            } else {
                total = 1;
            }
            danyoInflingido = jugadoresAux.get(i).getHpActual() - total;
            if (danyoInflingido < 0) {
                jugadoresAux.get(i).setHpActual(0);
            } else {
                jugadoresAux.get(i).setHpActual(danyoInflingido);
            }
            msg = this.getNombre() + " ha usado habilidad " + this.getHabilidad().get(numeroHab).getNombre()
                    + " contra todos los aliados ";
        }

        return msg;
    }
}
