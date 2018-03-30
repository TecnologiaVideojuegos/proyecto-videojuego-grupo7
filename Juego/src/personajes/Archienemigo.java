package personajes;

import items.Arma;
import items.Armadura;
import otros.Habilidad;
import otros.Inventario;
import java.util.ArrayList;

public class Archienemigo extends Jugador{
    //Constructor
    public Archienemigo(Arma arma, Armadura armadura, ArrayList<Habilidad> habilidades, Inventario inventario) {
        super(arma, armadura, habilidades, inventario);
        inicializarPersonaje();
    }
    public Archienemigo(Inventario inventario) {
        super(inventario);
        inicializarPersonaje();
    }
    //Metodo que iniciliaza las estadisticas del Personaje Archienemigo 
    @Override
    public void inicializarPersonaje(){
        this.setNombre("Archi");
        this.setHp(100);
        this.setHpActual(this.getHp());
        this.setMp(30);
        this.setAtaqueBase(50);
        this.setAtaque(50); //Esta linea hay que cambiarla cuando tengamos clase Arma para sumarle danyo del arma
        this.setDefensaBase(30);
        this.setDefensa(30); //Esta linea hay que cambiarla cuando tengamos Armadura para sumarle defensa de la armadura
        this.setVelocidad(5);
        this.setHabCritico(1);
        this.setNivel(25);
        this.setExp(0);
        this.setExpProxNivel(100);
    }
    @Override
    public void setearHabilidades() {
    }

    //Metodo que cambia las estadicticas basicas del Personaje al subir nivel
    @Override
    public void subirNivelEstadisticas(){
        this.setHp((int)(this.getHp()*1.20));
        this.setDefensaBase((int)(this.getDefensaBase()*1.20));
        //Actualizar la Defensa actual, this.setDefensa(this.getDefensaBase + this.getArmadura().getDefensa())
        this.setAtaqueBase((int)(this.getAtaque()*1.10));
        //Actualizar el Ataque actual, this.setAtaque(this.getAtaqueBase + this.getArma().getDanyo())
        this.setMp((int)(this.getMp()*1.05));
    }
    //toString
    @Override
    public String toString() {
        return "Archienemigo{" + "nombre=" + this.getNombre() + ", nivel=" + this.getNivel() + ", hp=" + this.getHp() + 
                ", hpActual=" + this.getHpActual() + ", ataque base=" + this.getAtaqueBase() + ", ataque=" + this.getAtaque() +
                ", defensa base=" + this.getDefensaBase() + ", defensa=" + this.getDefensa() + ", velocidad=" + 
                this.getVelocidad() + ", mp=" + this.getMp() + ", habCritico=" + this.getHabCritico() + ", arma=" + 
                this.getArma() + ", armadura=" + this.getArmadura() + ", habilidades=" + this.getHabilidades() +
                ", exp=" + this.getExp() + ", expProxNivel=" + this.getExpProxNivel() + ", inventario=" + this.getInventario() +'}';
    }
}  