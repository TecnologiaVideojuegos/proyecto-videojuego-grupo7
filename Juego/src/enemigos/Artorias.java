package enemigos;
import java.util.ArrayList;
import java.util.Random;
import otros.Habilidad;

public abstract class Artorias extends Enemigo{
    private ArrayList<Habilidad> habilidades;
    private static final long serialVersionUID = 4L;
    private int danoEspadazo = (int) (Artorias.super.getAtaque()*1.3);
    
    public Artorias(int id, int nivel, int hp, int ataque, int defensa) {
        super(id, nivel, hp, ataque, defensa); 
        inicializarEnemigo();
    }
    //poner habilidad (?) modificar oro y exp.
    
    @Override
    public void inicializarEnemigo(){
        Random rand = new Random();
        this.setNombre("Artorias");
        habilidades = new ArrayList<>();
        Habilidad hab1 = new Habilidad("Espadazo maldito", 1, danoEspadazo, 0, "Blande su espada con fuerza", 2);
        habilidades.add(hab1);
        Habilidad hab2 = new Habilidad("Corrupcion", 1, 15, 0, "Maldice a todos los enemigos", 4);
        habilidades.add(hab2);
        this.setHabilidad(habilidades);
        this.setOro(200);
        this.setExpAportada(100+(int)(rand.nextFloat()*100));
        this.setVelocidad(12);
        this.setHpActual(this.getHp());    
    }
}