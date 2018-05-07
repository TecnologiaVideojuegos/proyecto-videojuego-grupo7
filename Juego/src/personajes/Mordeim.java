//PICARO
package personajes;

import items.Arma;
import items.Armadura;
import otros.Habilidad;
import otros.Inventario;
import java.util.ArrayList;

public final class Mordeim extends Jugador{
    //Constructor
    public Mordeim(Arma arma, Armadura armadura, ArrayList<Habilidad> habilidades, Inventario inventario) {
        super(arma, armadura, habilidades, inventario);
        inicializarPersonaje();
    }
    public Mordeim(Inventario inventario) {
        super(inventario);
        inicializarPersonaje();
        setearHabilidades();
    }
    //Metodo que iniciliaza las estadisticas del Personaje Horacia 
    @Override
    public void inicializarPersonaje(){
        ArrayList<String> requisitos = new ArrayList<>();
        requisitos.add("Mordeim");
        Arma armaInicio = new Arma(10, 1, "Lapiz", "Podrás pintar a los enemigos", requisitos, 1, 0, 10);
        Armadura armaduraInicio = new Armadura(10, "Túnica arapienta", "Túnica rota", requisitos, 1, 0, 10);
        this.setNombre("Mordeim");
        this.setArma(armaInicio);
        this.setArmadura(armaduraInicio);
        this.setHp(120);
        this.setHpActual(this.getHp());
        this.setMp(50);
        this.setMpActual(50);
        this.setAtaqueBase(70);
        this.setAtaque(this.getAtaqueBase() + this.getArma().getDanyo());
        this.setDefensaBase(25);
        this.setDefensa(this.getDefensaBase()+ this.getArmadura().getDefensa());
        this.setVelocidad(15);
        this.setHabCritico(5 + this.getArma().getCritico());
        this.setNivel(1);
        this.setExp(0);
        this.setExpProxNivel(100);
        this.setPJ(true);    
        this.setArma(armaInicio);
    }
    
    @Override
    public void setearHabilidades(){
        Habilidad[] habs = new Habilidad[5];
        habs[0] = new Habilidad("Puñalada trapera", 1, 40, 3, "Apuñala al objetivo por la espalda", 2);
        habs[1] = new Habilidad("Saqueo", 5, 30, 7, "(Pasiva) Al finalizar el combate genera más ganancias", 5);//Posible Pasiva
        habs[2] = new Habilidad("Lo tuyo es mio", 10, 1.1f, 6, "Roba vida al enemigo", 3);
        habs[3] = new Habilidad("Pirotecnia", 15, 2, 10, "Lanza fuegos artifiales contra los enemigos", 4);
        habs[4] = new Habilidad("Asesinato", 20, 5, 10, "Golpe crítico a tu enemigo", 2);
        for (int i = 0; i < habs.length; i++) {
            this.anadirHabilidad(habs[i]);
        }
    }
    //Metodo que cambia las estadicticas basicas del Personaje al subir nivel
    @Override
    public void subirNivelEstadisticas(){
        this.setHp((this.getHp()+60));
        this.setDefensaBase((this.getDefensaBase()+10));
        this.setDefensa(this.getDefensaBase() + this.getArmadura().getDefensa());
        this.setAtaqueBase((this.getAtaque()+25));
        this.setAtaque(this.getAtaqueBase() + this.getArma().getDanyo());
        this.setMp((this.getMp()+40));
    }
    //toString
    @Override
    public String toString() {
        return "Mordeim{" + "nombre=" + this.getNombre() + ", nivel=" + this.getNivel() + ", hp=" + this.getHp() + 
                ", hpActual=" + this.getHpActual() + ", ataque base=" + this.getAtaqueBase() + ", ataque=" + this.getAtaque() +
                ", defensa base=" + this.getDefensaBase() + ", defensa=" + this.getDefensa() + ", velocidad=" + 
                this.getVelocidad() + ", mp=" + this.getMp() + ", habCritico=" + this.getHabCritico() + ", arma=" + 
                this.getArma() + ", armadura=" + this.getArmadura() + ", habilidades=" + this.getHabilidades() +
                ", exp=" + this.getExp() + ", expProxNivel=" + this.getExpProxNivel() + ", inventario=" + this.getInventario() +'}';
    }    
}