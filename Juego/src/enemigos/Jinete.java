package enemigos;

import java.util.ArrayList;
import java.util.Random;
import otros.Habilidad;
import personajes.Jugador;

public final class Jinete extends Enemigo {

    private ArrayList<Habilidad> habilidades;
    private static final long serialVersionUID = 4L;

    public Jinete(int id, int nivel, int hp, int ataque, int defensa) {
        super(id, nivel, hp, ataque, defensa);
        inicializarEnemigo();
    }
    //poner habilidad (?) modificar oro y exp.

    @Override
    public void inicializarEnemigo() {
        int danoEspadazo = (int) (this.getAtaque() * 1.25);
        int danoFuego = (int) (this.getAtaque()*1.5);
        Random rand = new Random();
        this.setNombre("Jinete Espectral");
        habilidades = new ArrayList<>();
        Habilidad hab1 = new Habilidad("Tajo de Cardinal", 1, danoEspadazo, 0, "Ataca con su lanza a un aliado", 2);
        habilidades.add(hab1);
        Habilidad hab2 = new Habilidad("Fuego de Wyvern", 1, 800, 0, "Calcina a todos los enemigos", 4);
        habilidades.add(hab2);
        Habilidad hab3 = new Habilidad("Furia de Wyvern", 1, danoFuego, 0, "Quema a un aliado", 2);
        habilidades.add(hab3);
        this.setHabilidad(habilidades);
        this.setOro(200);
        this.setExpAportada(100 + (int) (rand.nextFloat() * 100));
        this.setVelocidad(8);
        this.setHpActual(this.getHp());
    }

    @Override
    public String estrategiaAtacar(ArrayList<Jugador> jugadores) {
        String msg="";
        Random rand = new Random();
        int at = this.getAtaque();
        int danyo, total, danyoInflingido;
        boolean habilidad;
        int indice,tipoAtaq;
        ArrayList<Jugador> jugadoresAux = new ArrayList<>();

        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).estaVivo()) {
                jugadoresAux.add(jugadores.get(i));
            }
        }
        tipoAtaq = rand.nextInt(3);
        if (tipoAtaq == 0 || tipoAtaq==2) {
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
        } else {
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
                msg = this.getNombre()+ " ha usado habilidad " + this.getHabilidad().get(1).getNombre() + 
                            " contra todos los aliados ";
            }
        }
        
        return msg;
    }
}
