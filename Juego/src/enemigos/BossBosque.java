package enemigos;
import java.util.ArrayList;
import java.util.Random;
import otros.Habilidad;
import personajes.Jugador;

public final class BossBosque extends Enemigo{
    private ArrayList<Habilidad> habilidades;
    private static final long serialVersionUID = 4L;
    private int estadoCombate=0;
    
    public BossBosque(int id, int nivel, int hp, int ataque, int defensa) {
        super(id, nivel, hp, ataque, defensa); 
        inicializarEnemigo();
    }
    
    @Override
    public void inicializarEnemigo(){
        Random rand = new Random();
        this.setNombre("Yggdrasil");
        habilidades = new ArrayList<>();  
        Habilidad hab = new Habilidad("Arraigo", 1, 15, 0, "Clava sus raices en el suelo y recupera vida", 4);
        habilidades.add(hab);
        this.setHabilidad(habilidades);
        this.setOro(200);
        this.setExpAportada(100+(int)(rand.nextFloat()*100));
        this.setVelocidad(12);
        this.setHpActual(this.getHp());    
    }

    @Override
    public String estrategiaAtacar(ArrayList<Jugador> jugadores) {
        //Ataca a toda la party
        String msg ="";
        int danyo, total, danyoInfligido;
        
        switch(estadoCombate)
        {
            case 0:
                int mayorHP=jugadores.get(0).getHpActual();
                int indiceMayorHp=0;
                for (int i = 0; i < jugadores.size(); i++) 
                {
                    if (jugadores.get(i).estaVivo()){
                        if (jugadores.get(i).getHpActual() > mayorHP){
                            mayorHP = jugadores.get(i).getDefensa();
                            indiceMayorHp = i;
                        }
                    }
                    danyo=this.getAtaque()-jugadores.get(indiceMayorHp).getDefensa();
                    if (danyo > 0)
                        total = danyo;
                    else
                        total = 1;
                    danyoInfligido = jugadores.get(indiceMayorHp).getHpActual() - total;
                    if(danyoInfligido < 0)
                        jugadores.get(indiceMayorHp).setHpActual(0);
                    else
                        jugadores.get(indiceMayorHp).setHpActual(danyoInfligido); 
                    msg = "Yggdrasil ha clavado sus raíces en "+jugadores.get(indiceMayorHp).getNombre()+" causando "+danyo+" de daño.";
                }
                break;
            case 1:
                msg="La energía del bosque alimenta a Yggdrasil recuperando 150 PV";
                        this.setHpActual(this.getHpActual()+150);
                break;
            case 2:
                for (int i = 0; i < jugadores.size(); i++) {
                    jugadores.get(i).setHpActual(jugadores.get(i).getHpActual()-15);
                }
                msg="Yggdrasil continua curandose. La polución del bosque daña a los aliados";
                break;
        }
        estadoCombate++;
        if(estadoCombate>2)
            estadoCombate=0;
        
        return msg;
    }
}
