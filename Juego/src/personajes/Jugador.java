package personajes;
import enemigos.Enemigo;
import items.Arma;
import items.Armadura;
import java.io.Serializable;
import otros.Habilidad;
import otros.Inventario;
import java.util.ArrayList;
import static otros.Habilidad.TIPOATACAR;
import static otros.Habilidad.TIPOCURAR;
import static otros.Habilidad.TIPODRENARVIDA;
import static otros.Habilidad.TIPORESUCITAR;

public abstract class Jugador extends Personaje implements Serializable{
    //Atributos
    private int mp;
    private int mpActual;
    private int habCritico;
    private Arma arma;
    private Armadura armadura;
    private ArrayList<Habilidad> habilidades;
    private int exp;
    private int expProxNivel;
    private Inventario inventario;
    private int defensaBase;
    private int ataqueBase;
    
    private static final long serialVersionUID = 3L;
    
    //Constructor
    public Jugador(int mp, int mpActual, int habCritico, Arma arma, Armadura armadura, ArrayList<Habilidad> habilidades, int exp, int expProxNivel, Inventario inventario, int defensaBase, int ataqueBase, String nombre, int nivel, int hp, int hpActual, int ataque, int defensa, int velocidad) {
        super(nombre, nivel, hp, hpActual, ataque, defensa, velocidad);
        this.mp = mp;
        this.mp = mpActual;
        this.habCritico = habCritico;
        this.arma = arma;
        this.armadura = armadura;
        this.habilidades = habilidades;
        this.exp = exp;
        this.expProxNivel = expProxNivel;
        this.inventario = inventario;
        this.defensaBase = defensaBase;
        this.ataqueBase = ataqueBase;
    }
    //Constructor para la clase Horacia, Kibito y Mordeim
    public Jugador(Arma arma, Armadura armadura, ArrayList<Habilidad> habilidades, Inventario inventario) {
        this.arma = arma;
        this.armadura = armadura;
        this.habilidades = habilidades;
        this.inventario = inventario;
    }
    public Jugador(){        
    }
    //Getters and setters
    public int getMpActual() {
        return mpActual;
    }
    public void setMpActual(int mpActual) {
        this.mpActual = mpActual;
    }
    public int getMp() {
        return mp;
    }
    public void setMp(int mp) {
        this.mp = mp;
    }
    public int getHabCritico() {
        return habCritico;
    }
    public void setHabCritico(int habCritico) {
        this.habCritico = habCritico;
    }
    public Arma getArma() {
        return arma;
    }
    public void setArma(Arma arma) {
        this.arma = arma;
    }
    public Armadura getArmadura() {
        return armadura;
    }
    public void setArmadura(Armadura armadura) {
        this.armadura = armadura;
    }
    public ArrayList<Habilidad> getHabilidades() {
        return habilidades;
    }
    public int getExp() {
        return exp;
    }
    public void setExp(int exp) {
        this.exp = exp;
    }
    public int getExpProxNivel() {
        return expProxNivel;
    }
    public void setExpProxNivel(int expProxNivel) {
        this.expProxNivel = expProxNivel;
    }
    public Inventario getInventario() {
        return inventario;
    }
    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
    public int getDefensaBase() {
        return defensaBase;
    }
    public void setDefensaBase(int defensaBase) {
        this.defensaBase = defensaBase;
    }
    public int getAtaqueBase() {
        return ataqueBase;
    }
    public void setAtaqueBase(int ataqueBase) {
        this.ataqueBase = ataqueBase;
    }
    
    //Metodos
    public void cambiarArma(Arma arma){ 
        Arma armaAntigua = this.getArma();
        if (arma.getRequisitoCategoria().contains(this.getNombre()) &&
                arma.getRequisitoNivel() <= this.getNivel()){
            this.arma=arma;
            this.setAtaque(this.getAtaqueBase() + arma.getDanyo());
            this.setHabCritico(arma.getCritico());
            this.inventario.getItems().remove(arma);
            this.inventario.getItems().add(armaAntigua);
            System.out.println("Arma cambiada correctamente");
        }
        else
            System.out.println("No puedes equiparte este arma");
    }
    public void cambiarArmadura(Armadura armadura){
        Armadura armaduraAntigua = this.getArmadura();
        if (armadura.getRequisitoCategoria().contains(this.getNombre()) &&
                armadura.getRequisitoNivel() <= this.getNivel()){
            this.armadura=armadura;
            this.setDefensa(this.getDefensaBase() + armadura.getDefensa());
            this.inventario.getItems().remove(armadura);
            this.inventario.getItems().add(armaduraAntigua);
            System.out.println("Armadura cambiada correctamente");
        }
        else
            System.out.println("No puedes equiparte esta armadura");
    }
    public void anadirHabilidad(Habilidad hab){
        this.habilidades.add(hab);
    }
    public boolean puedeSubir(){
        return this.exp >= this.expProxNivel;
    }
    public void subirNivel(){
        this.subirNivelEstadisticas();
        this.setExp(this.exp - this.expProxNivel);
        this.setExpProxNivel((int)(this.expProxNivel*1.20));
        
    }
    
    /*Metodos de Comprobacion de habilidades*/
    public boolean habilidadUsable(int nHabilidad)
    {
        return this.comprobarLVL(nHabilidad)&& this.comprobarMp(nHabilidad);
    }/*public boolean habilidadUsable()*/
    private boolean comprobarLVL(int nHabilidad)
    {
        return this.getNivel() >= this.getHabilidades().get(nHabilidad).getNivel();
    }/*public boolean comprobarLVL(Jugador usuario)*/
    private boolean comprobarMp(int nHabilidad)
    {
        return this.getMpActual() >= this.getHabilidades().get(nHabilidad).getCosteMP();
    }/*public boolean comprobarMp(Jugador usuario)*/
    /*Metodos de uso de habilidades*/
    public boolean usarHabilidad(int nHabilidad, Personaje objetivo)
    {
       boolean usable=false;
        usable=this.habilidades.get(nHabilidad).usarHabilidad(this, objetivo);
        return usable; 
    }/*public boolean usarHabilidad(int nHabilidad, Personaje objetivo)*/
    
    public boolean usarHabilidad(int nHabilidad, ArrayList<Personaje> objetivos)
    {
        this.habilidades.get(nHabilidad).usarHabilidad(this, objetivos);
        return true;
    }/*public boolean usarHabilidad(int nHabilidad, ArrayList<Personaje> objetivos)*/
    
    //Metodo abstracto
    public abstract void inicializarPersonaje();
    public abstract void subirNivelEstadisticas();
    //toString
    @Override
    public String toString() {
        return "Jugador{" + "mp=" + mp + ", mp actual=" + mpActual + ", habCritico=" + habCritico + ", arma=" + arma + ", armadura=" + armadura + ", habilidades=" + habilidades + ", exp=" + exp + ", expProxNivel=" + expProxNivel + ", inventario=" + inventario + ", defensaBase=" + defensaBase + ", ataqueBase=" + ataqueBase + '}';
    }
}