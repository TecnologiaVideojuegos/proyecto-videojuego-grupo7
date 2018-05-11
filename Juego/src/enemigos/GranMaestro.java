package enemigos;
import java.util.ArrayList;
import java.util.Random;
import otros.Habilidad;
import personajes.Jugador;

public final class GranMaestro extends Enemigo{
    private ArrayList<Habilidad> habilidades;
    private static final long serialVersionUID = 4L;
    
    
    public GranMaestro(int id, int nivel, int hp, int ataque, int defensa) {
        super(id, nivel, hp, ataque, defensa); 
        inicializarEnemigo();
    }
    //poner habilidad (?) modificar oro y exp.
    
    @Override
    public void inicializarEnemigo(){
        int danoEspadazo = (int) (this.getAtaque()*1.3);
        Random rand = new Random();
        this.setNombre("LiderFanatico");
        habilidades = new ArrayList<>();
        Habilidad hab1 = new Habilidad("Rabia fanática", 1, danoEspadazo, 0, "Golpea con rabia", 2);
        habilidades.add(hab1);
        Habilidad hab2 = new Habilidad("Imposición fanática", 1, 200, 0, "Corrompe a todos los enemigos", 4);
        habilidades.add(hab2);
        this.setHabilidad(habilidades);
        this.setOro(200);
        this.setExpAportada(100+(int)(rand.nextFloat()*100));
        this.setVelocidad(12);
        this.setHpActual(this.getHp());    
    }
    
    //Añadir segunda habilidad

    @Override
    public String estrategiaAtacar(ArrayList<Jugador> jugadores) {
        String msg="";
        Random rand = new Random();
        float probHab = rand.nextFloat();
        int at = this.getAtaque();
        int danyo, total, danyoInflingido;
        boolean habilidad = false;
        int indice;
        ArrayList<Jugador> jugadoresAux = new ArrayList<>();

        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).estaVivo()) {
                jugadoresAux.add(jugadores.get(i));
            }
        }

        if (rand.nextInt(2) == 0) {
            at += this.getHabilidad().get(0).getDanyo();
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
            msg = this.escribirMensaje(habilidad, this.getHabilidad().get(0), jugadoresAux.get(indice), total);
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