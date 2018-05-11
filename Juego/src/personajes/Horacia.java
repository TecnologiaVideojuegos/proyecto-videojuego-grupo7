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
        Armadura armaduraInicio = new Armadura(15, "Ropa de pobre gruesa", "Ropa robada a un mendigo", requisitos, 1, 0, 10);
        this.setNombre("Horacia");
        this.setArma(armaInicio);
        this.setArmadura(armaduraInicio);
        this.setHp(200);
        this.setHpActual(this.getHp());
        this.setMp(30);
        this.setMpActual(30);
        this.setAtaqueBase(55);
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
        Habilidad[] habs = new Habilidad[5];
        habs[0] = new Habilidad("Bendición Divina", 1, 5, 6, "La bendición de Cardinal cura las heridas de un aliado", 0);
        habs[1] = new Habilidad("Embestida", 5, 1.5f, 10, "Embestida potente contra un objetivo", 2);
        habs[2] = new Habilidad("Apalear", 10, 1.75f, 15, "Apalea a un objetivo", 2);
        habs[3] = new Habilidad("Ira salvaje", 15, 1.5f, 30, "Habilidad multiobjetivo", 4);
        habs[4] = new Habilidad("Torbellino", 20, 2, 35, "Hace un torbellino en torno a todos los objetivos", 4);
        
        for (int i = 0; i < habs.length; i++) {
            this.anadirHabilidad(habs[i]);
        }
    }
    //Metodo que cambia las estadicticas basicas del Personaje al subir nivel
    @Override
    public void subirNivelEstadisticas(){
        this.setHp((this.getHp()+100));
        this.setDefensaBase((this.getDefensaBase()+15));
        this.setDefensa(this.getDefensaBase() + this.getArmadura().getDefensa());
        this.setAtaqueBase((this.getAtaqueBase()+10));
        this.setAtaque(this.getAtaqueBase() + this.getArma().getDanyo());
        this.setMp((this.getMp()+20));
        for (int i = 0; i < this.getHabilidades().size(); i++) {
            this.getHabilidades().get(i).setCosteMP(this.getHabilidades().get(i).getCosteMP()+5); 
        }
        
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