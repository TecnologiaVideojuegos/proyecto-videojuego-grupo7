//GUERRERA
package personajes;

import items.Arma;
import items.Armadura;
import otros.Habilidad;
import otros.Inventario;
import java.util.ArrayList;

public final class Horacia extends Jugador{
    //Constructor
    public Horacia(Arma arma, Armadura armadura, ArrayList<Habilidad> habilidades, Inventario inventario) {
        super(arma, armadura, habilidades, inventario);
        inicializarPersonaje();
    }       
    public Horacia()
    {
        this.setNombre("Horacia");
    }
    public Horacia(Inventario inventario) {
        super(inventario);
        inicializarPersonaje();
        setearHabilidades();
    }       
    //Metodo que iniciliaza las estadisticas del Personaje Horacia 
    @Override
    public void inicializarPersonaje(){
        ArrayList<String> requisitos = new ArrayList<>();
        requisitos.add("Horacia");
        Arma armaInicio = new Arma(10, 1, "Garrote", "Garrote para aporrear gente", requisitos, 1, 0, 10);
        Armadura armaduraInicio = new Armadura(10, "Ropa de pobre", "Ropa robada a un mendigo", requisitos, 1, 0, 10);
        this.setNombre("Horacia");
        this.setArma(armaInicio);
        this.setArmadura(armaduraInicio);
        this.setHp(100);
        this.setHpActual(this.getHp());
        this.setMp(30);
        this.setMpActual(30);
        this.setAtaqueBase(50);
        this.setAtaque(this.getAtaqueBase() + this.getArma().getDanyo());
        this.setDefensaBase(30);
        this.setDefensa(this.getDefensaBase()+ this.getArmadura().getDefensa());
        this.setVelocidad(5);
        this.setHabCritico(1 + this.getArma().getCritico());
        this.setNivel(1);
        this.setExp(0);
        this.setExpProxNivel(100);
        this.setPJ(true);
    }
    
    @Override
    public void setearHabilidades(){
        Habilidad[] habs = new Habilidad[4];
        habs[0] = new Habilidad("Embestida", 1, 20, 5, "Embestida potente contra un objetivo", 2);
        habs[1] = new Habilidad("Apalear", 5, 30, 7, "Apalea a un bjetivo", 2);
        habs[2] = new Habilidad("Ira salvaje", 10, 5, 5, "Habilidad multiobjetivo", 4);
        habs[3] = new Habilidad("Torbellino", 5, 15, 7, "Hace un torbellino en torno a todos los objetivos", 4);
        for (int i = 0; i < habs.length; i++) {
            this.anadirHabilidad(habs[i]);
        }
    }
    //Metodo que cambia las estadicticas basicas del Personaje al subir nivel
    @Override
    public void subirNivelEstadisticas(){
        this.setHp((int)(this.getHp()*1.20));
        this.setDefensaBase((int)(this.getDefensaBase()*1.20));
        this.setDefensa(this.getDefensaBase() + this.getArmadura().getDefensa());
        this.setAtaqueBase((int)(this.getAtaque()*1.10));
        this.setAtaque(this.getAtaqueBase() + this.getArma().getDanyo());
        this.setMp((int)(this.getMp()*1.05));
    }
    //toString
    @Override
    public String toString() {
        return "Horacia{" + "nombre=" + this.getNombre() + ", nivel=" + this.getNivel() + ", hp=" + this.getHp() + 
                ", hpActual=" + this.getHpActual() + ", ataque base=" + this.getAtaqueBase() + ", ataque=" + this.getAtaque() +
                ", defensa base=" + this.getDefensaBase() + ", defensa=" + this.getDefensa() + ", velocidad=" + 
                this.getVelocidad() + ", mp=" + this.getMp() + ", habCritico=" + this.getHabCritico() + ", arma=" + 
                this.getArma() + ", armadura=" + this.getArmadura() + ", habilidades=" + this.getHabilidades() +
                ", exp=" + this.getExp() + ", expProxNivel=" + this.getExpProxNivel() + ", inventario=" + this.getInventario() +'}';
    }  
}