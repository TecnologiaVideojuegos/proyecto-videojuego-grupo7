package enemigos;
import java.util.ArrayList;
import java.util.Random;
import otros.Habilidad;

public abstract class GranMaestro extends Enemigo{
    private ArrayList<Habilidad> habilidades;
    private static final long serialVersionUID = 4L;
    private int danoEspadazo = (int) (GranMaestro.super.getAtaque()*1.3);
    
    public GranMaestro(int id, int nivel, int hp, int ataque, int defensa) {
        super(id, nivel, hp, ataque, defensa); 
        inicializarEnemigo();
    }
    //poner habilidad (?) modificar oro y exp.
    
    @Override
    public void inicializarEnemigo(){
        Random rand = new Random();
        this.setNombre("Artorias");
        habilidades = new ArrayList<>();
        Habilidad hab = new Habilidad("Rabia fanática", 1, danoEspadazo, 0, "Golpea con rabia", 2);
        habilidades.add(hab);
        this.setHabilidad(habilidades);
        this.setOro(200);
        this.setExpAportada(100+(int)(rand.nextFloat()*100));
        this.setVelocidad(12);
        this.setHpActual(this.getHp());    
    }
    
    //Añadir segunda habilidad
}