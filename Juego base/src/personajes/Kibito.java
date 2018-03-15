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
    //Metodo que iniciliaza las estadisticas del Personaje Horacia 
    public void inicializarPersonaje(){
        this.setNombre("Kibito");
        this.setHp(80);
        this.setHpActual(this.getHp());
        this.setMp(100);
        this.setMpActual(100);
        this.setAtaqueBase(25);
        this.setAtaque(this.getAtaqueBase() + this.getArma().getDanyo());
        this.setDefensaBase(10);
        this.setDefensa(this.getDefensaBase()+ this.getArmadura().getDefensa());
        this.setVelocidad(10);
        this.setHabCritico(2 + this.getArma().getCritico());
        this.setNivel(1);
        this.setExp(0);
        this.setExpProxNivel(100);
    }
    //Metodo que cambia las estadicticas basicas del Personaje al subir nivel
    public void subirNivelEstadisticas(){
        this.setHp((int)(this.getHp()*1.10));
        this.setDefensaBase((int)(this.getDefensaBase()*1.05));
        this.setDefensa(this.getDefensaBase() + this.getArmadura().getDefensa());
        this.setAtaqueBase((int)(this.getAtaque()*1.05));
        this.setAtaque(this.getAtaqueBase() + this.getArma().getDanyo());
        this.setMp((int)(this.getMp()*1.20));
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