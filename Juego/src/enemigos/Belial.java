package enemigos;

import java.util.ArrayList;
import java.util.Random;
import otros.Habilidad;
import personajes.Jugador;

public final class Belial extends Enemigo {

    private ArrayList<Habilidad> habilidades;
    private static final long serialVersionUID = 4L;
    private int estadoCombate = 0;

    public Belial(int id, int nivel, int hp, int ataque, int defensa) {
        super(id, nivel, hp, ataque, defensa);
        inicializarEnemigo();
    }
    //poner habilidad (?) modificar oro y exp.

    @Override
    public void inicializarEnemigo() {
        int danoFuego = (int) (this.getAtaque() * 1.3);
        int danoMordisco = (int) (this.getAtaque()*1.1);
        Random rand = new Random();
        this.setNombre("Belial");
        habilidades = new ArrayList<>();
        Habilidad hab1 = new Habilidad("Ataque del Emperador", 1, danoFuego, 0, "Golpe demoniaco", 2);
        habilidades.add(hab1);
        Habilidad hab2 = new Habilidad("Desolación", 1, 150, 0, "Golpea a todos los enemigos", 4);
        habilidades.add(hab2);
        Habilidad hab3 = new Habilidad("Mordisco Demoníaco", 1, danoMordisco, 0, "Mordisco que cura", 3);
        habilidades.add(hab3);
        this.setHabilidad(habilidades);
        this.setOro(200);
        this.setExpAportada(100 + (int) (rand.nextFloat() * 100));
        this.setVelocidad(8);
        this.setHpActual(this.getHp());
    }

    @Override
    public String estrategiaAtacar(ArrayList<Jugador> jugadores) {
        String msg = "";
        Random rand = new Random();
        int at = this.getAtaque();
        int danyo, total, danyoInflingido;
        boolean habilidad;
        int indice, tipoAtaq = 0, curar = 0;
        ArrayList<Jugador> jugadoresAux = new ArrayList<>();

        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).estaVivo()) {
                jugadoresAux.add(jugadores.get(i));
            }
        }
        if (estadoCombate < 2) {
            at += this.getHabilidad().get(1).getDanyo();
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

            }
            msg = this.getNombre() + " ha usado habilidad " + this.getHabilidad().get(1).getNombre()
                    + " contra todos los aliados ";
            estadoCombate++;
        } else if (estadoCombate < 3) {
            curar = (int) (this.getHabilidad().get(2).getDanyo());
            indice = rand.nextInt(jugadoresAux.size());
            danyo = curar - jugadoresAux.get(indice).getDefensa();
            habilidad = true;

            if (danyo > 0) {
                total = danyo;
            } else {
                total = 1;
            }

            danyoInflingido = jugadoresAux.get(indice).getHpActual() - total;
            if (danyoInflingido < 0) {
                jugadoresAux.get(indice).setHpActual(0);
            } else {
                jugadoresAux.get(indice).setHpActual(danyoInflingido);
            }
            msg = "Ataca a "+ jugadoresAux.get(indice).getNombre() + " y se cura.";
            if (total > this.getHp()) {
                this.setHpActual(this.getHp());
            } else {
                this.setHpActual((int) (this.getHpActual() + total));
            }
            estadoCombate++;
        } else {
            at += this.getHabilidad().get(tipoAtaq).getDanyo();
            indice = rand.nextInt(jugadoresAux.size());
            danyo = at - jugadoresAux.get(indice).getDefensa();
            habilidad = true;

            if (danyo > 0) {
                total = danyo;
            } else {
                total = 1;
            }

            danyoInflingido = jugadoresAux.get(indice).getHpActual() - total;
            if (danyoInflingido < 0) {
                jugadoresAux.get(indice).setHpActual(0);
            } else {
                jugadoresAux.get(indice).setHpActual(danyoInflingido);
            }
            msg = this.escribirMensaje(habilidad, this.getHabilidad().get(tipoAtaq), jugadoresAux.get(indice), total);
            estadoCombate = 0;
        }

        return msg;
    }
}
