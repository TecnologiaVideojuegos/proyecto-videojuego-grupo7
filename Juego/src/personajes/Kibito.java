//MAGO
package personajes;

import items.Arma;
import items.Armadura;
import otros.Habilidad;
import otros.Inventario;
import java.util.ArrayList;

public final class Kibito extends Jugador{
    //Constructor
    public Kibito(Arma arma, Armadura armadura, ArrayList<Habilidad> habilidades, Inventario inventario) {
        super(arma, armadura, habilidades, inventario);
        inicializarPersonaje();
    }
    public Kibito(Inventario inventario) {
        super(inventario);
        inicializarPersonaje();
        setearHabilidades();
    }
    //Metodo que iniciliaza las estadisticas del Personaje Horacia 
    @Override
    public void inicializarPersonaje(){
        ArrayList<String> requisitos = new ArrayList<>();
        requisitos.add("Kibito");
        //EDIT:armas
        Arma armaInicio = new Arma(10, 1, "Palo", "Palo de poca calidad", requisitos, 1, 0, 10);
        Armadura armaduraInicio = new Armadura(10, "Calzoncillos", "Calzoncillos un tanto usados", requisitos, 1, 0, 10);
        this.setNombre("Kibito");
        this.setArma(armaInicio);
        this.setArmadura(armaduraInicio);
        this.setHp(100);
        this.setHpActual(this.getHp());
        this.setMp(100);
        this.setMpActual(100);
        this.setAtaqueBase(25);
        this.setAtaque(this.getAtaqueBase() + this.getArma().getDanyo());
        this.setDefensaBase(25);
        this.setDefensa(this.getDefensaBase()+ this.getArmadura().getDefensa());
        this.setVelocidad(10);
        this.setHabCritico(2 + this.getArma().getCritico());
        this.setNivel(1);
        this.setExp(0);
        this.setExpProxNivel(100);
        this.setPJ(true);
    }
    
    @Override
    public void setearHabilidades(){
        Habilidad[] habs = new Habilidad[5];
        habs[0] = new Habilidad("Bola fuego", 1, 3, 8, "Bola de fuego contra todos los objetivos", 4);
        habs[1] = new Habilidad("Milagro sanitario", 6, 30, 7, "Cura a un aliado", 0);
        habs[2] = new Habilidad("Lanza de rayos", 10, 7, 8, "Rayo poderoso contra objetivo", 2);
        habs[3] = new Habilidad("Levantate gandul", 15, 300, 10, "Resucita un aliado", 1);
        habs[4] = new Habilidad("O. Arcano", 20, 5, 10, "Proporciona un buen golpe a un objetivo", 2);
        for (int i = 0; i < habs.length; i++) {
            this.anadirHabilidad(habs[i]);
        }
    }
    
    //Metodo que cambia las estadicticas basicas del Personaje al subir nivel
    @Override
    public void subirNivelEstadisticas(){
        this.setHp((this.getHp()+50));
        this.setDefensaBase((this.getDefensaBase()+10));
        this.setDefensa(this.getDefensaBase() + this.getArmadura().getDefensa());
        this.setAtaqueBase((this.getAtaqueBase()+10));
        this.setAtaque(this.getAtaqueBase() + this.getArma().getDanyo());
        this.setMp((this.getMp()+80));
    }
    //toString
    @Override
    public String toString() {
        return "Kibito{" + "nombre=" + this.getNombre() + ", nivel=" + this.getNivel() + ", hp=" + this.getHp() + 
                ", hpActual=" + this.getHpActual() + ", ataque base=" + this.getAtaqueBase() + ", ataque=" + this.getAtaque() +
                ", defensa base=" + this.getDefensaBase() + ", defensa=" + this.getDefensa() + ", velocidad=" + 
                this.getVelocidad() + ", mp=" + this.getMp() + ", habCritico=" + this.getHabCritico() + ", arma=" + 
                this.getArma() + ", armadura=" + this.getArmadura() + ", habilidades=" + this.getHabilidades() +
                ", exp=" + this.getExp() + ", expProxNivel=" + this.getExpProxNivel() + ", inventario=" + this.getInventario() +'}';
    }    
}