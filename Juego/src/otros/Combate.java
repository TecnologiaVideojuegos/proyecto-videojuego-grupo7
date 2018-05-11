package otros;

import enemigos.*;
import personajes.Personaje;
import java.util.ArrayList;
/*EDIT: Eliminar Import VenganzaBelial*/
import estados.VenganzaBelial;
import items.Item;
import java.util.Random;
import org.newdawn.slick.SlickException;
import personajes.Jugador;

public final class Combate {
    //Atributos
    private ArrayList<Personaje> ordenPersonajes = new ArrayList<Personaje>();
    private int nParticipantes; //N de participantes en el combate
    private ArrayList<Personaje> Enemigos;
    private int EnemigosRestantes;
    private int AliadosRestantes;
    private int Turno;
    private int expCombate;
    private int oroCombate;
    /*EDIT: posible conversion de dropItem a ArrayList*/
    private Item dropItem;
    private int ArrayEnemigosUsado;
    //Constructor
    public Combate(ArrayList<Personaje> Party, int Mapa) 
    {
        int aux=0;//Variable auxuliar para rellenar
        //ArrayList<Personaje> participantes = Party;//Añadimos "party" a lista de participantes
        ArrayList<Personaje> participantes =new ArrayList<Personaje>();
        for(aux=0;aux<Party.size();aux++)
        {
            participantes.add(Party.get(aux));
        }
        Enemigos = GeneraArrayEnemigos(Mapa);//Generamos Array de enemigos en funcion del mapa
        for(aux=0;aux<Enemigos.size();aux++)//Añadimos enemigos a lista de participantes
        {
            participantes.add(Enemigos.get(aux));
        }/*for*/
        EnemigosRestantes=Enemigos.size();
        AliadosRestantes=Party.size();//EDIT: Posible cambio en funcion de si estan vivos
        nParticipantes=participantes.size();//Indica los participantes iniciales
        OrdenaTurnos(participantes);//Reordenamos a los participantes en funcion de iniciativa
        Turno=0;//Indice para guiar los turnos y saber a quien le toca jugar
    }/*public Combate(Personaje[] participantes) END*/
    
    //Funcion que ordena los pjs por turno en un array
    private void OrdenaTurnos(ArrayList<Personaje> participantes)
    {
        int[] Iniciativas= new int[nParticipantes];
        Random rand=new Random();
        float Modificador;
        /*Variables Auxiliares*/
        int auxIndex;
        int auxCount;
        int maxId;
        int maximo;
        /*Bucle de calculo de iniciativas*/
        for(auxIndex=0;auxIndex<this.nParticipantes;auxIndex++)
        {
            /*Randomizar Modificador entre 0.5 y 1*/
            Modificador=(float)(rand.nextFloat()/2+0.5);
            Iniciativas[auxIndex]=(int)(participantes.get(auxIndex).getVelocidad()*Modificador);       
        }/*for(auxIndex=0;auxIndex<nParticipantes;auxIndex++) END*/
        /*Bucle de ordenamiento de personajes en el combate*/
        auxCount=0;
        while(auxCount<this.nParticipantes)
        {
            maximo=Iniciativas[0];
            maxId=0;
            for(auxIndex=1;auxIndex<this.nParticipantes;auxIndex++)
            {
                if( maximo<Iniciativas[auxIndex])
                {
                    maximo=Iniciativas[auxIndex];
                    maxId=auxIndex;
                }/*if( maximo<Iniciativas[auxIndex]) END*/
            }/*for(auxIndex=1;auxIndex<nParticipantes;auxIndex++) END*/
            
            Iniciativas[maxId]=0;//Eliminamos la posibilidad de que se repita el pj
            this.ordenPersonajes.add(participantes.get(maxId));//Metemos el pj en la lista turnos
            auxCount++;//Aumentamos el indice del orden de pjs
        }/* while(auxCount<nParticipantes) END*/
    }/* private void OrdenaTurnos(Personaje[] participantes)END*/
    
    public String Atacar(Personaje Atacante, Personaje Defensor)
    {
        String mensaje;
        Jugador jug= (Jugador)Atacante;
        /*Calculo de daño*/
        Random rand=new Random();
        int danyoCausado;
        float critico=rand.nextFloat()*100;//EDIT
        if(critico<jug.getHabCritico())
        {
            danyoCausado=Atacante.getAtaque()*2-Defensor.getDefensa();
        }
        else
        {
           danyoCausado=Atacante.getAtaque()-Defensor.getDefensa(); 
        }
        if (danyoCausado>0)
        {
          Defensor.setHpActual(Defensor.getHpActual()- danyoCausado);
        }/*if (DañoCausado>0)*/
        else{
            danyoCausado=1;
            Defensor.setHpActual(Defensor.getHpActual()- danyoCausado);
        }
        mensaje=Atacante.getNombre()+" a atacado a "+Defensor.getNombre()+" causando "+danyoCausado+" de daño";
        return mensaje;
    }/*private void Atacar*/
    
    public void GestionaMuertes()
    {
       int aux=0;
        while(aux<this.nParticipantes)
        {
            if(!this.ordenPersonajes.get(aux).estaVivo())
            {
                if(this.ordenPersonajes.get(aux).isPJ()){
                    this.AliadosRestantes--;
                }
                else{
                    this.EnemigosRestantes--;
                    this.Enemigos.remove(this.ordenPersonajes.get(aux));
                }
                this.ordenPersonajes.remove(aux);
                this.nParticipantes=this.ordenPersonajes.size();
            }/*if(!this.ordenPersonajes.get(aux).estaVivo())*/
            else{
                aux++;
            }/*else*/
        }/*while(aux<this.nParticipantes)*/
    }/*public void GestionaMuertes()*/
    
    public void GestionaResurrecion(Personaje jugadorResucitado)
    {  
        /*EDIT:Bajor pruebas de funcionamiento*/
        this.AliadosRestantes++;
        this.ordenPersonajes.add(jugadorResucitado);
        this.nParticipantes=ordenPersonajes.size();
    }/*public void GestionaResurrecion()*/
    
    public boolean CombateAcabado()
    {
        return (AliadosRestantes==0 || EnemigosRestantes==0);
    }/*FinCombate()*/
    
    public boolean CombateGanado()
    {
        return (AliadosRestantes!=0 && EnemigosRestantes ==0);
    }/*public boolean CombateGanado()*/
    
    public boolean GestionaSiguienteTurno()
    {
       Turno++;
       this.nParticipantes=this.ordenPersonajes.size();
       if(Turno>=this.nParticipantes)
       {
           Turno=0;
       }/*if(Turno>this.nParticipantes)*/
       /*EDIT:Comprobar si el siguiente turno pertenece a un jugador o es automatico*/
       if(ordenPersonajes.get(Turno).isPJ())
       {
           return true;
       }
       else{
           return false;  
       }
     //  return true;
    }/*public boolean GestionaSiguienteTurno()*/
    
    public boolean GestionaPrimerTurno()
    {
        return this.ordenPersonajes.get(0).isPJ();
    }
    
    public void expCombate()
    {
        
    }
    //Getter y Setter
    public ArrayList<Personaje> getOrdenPersonajes() {
        return ordenPersonajes;
    }

    public void setOrdenPersonajes(ArrayList<Personaje> ordenPersonajes) {
        this.ordenPersonajes = ordenPersonajes;
    }

    public int getnParticipantes() {
        return nParticipantes;
    }

    public void setnParticipantes(int nParticipantes) {
        this.nParticipantes = nParticipantes;
    }

    public ArrayList<Personaje> getEnemigos() {
        return Enemigos;
    }

    public void setEnemigos(ArrayList<Personaje> Enemigos) {
        this.Enemigos = Enemigos;
    }

    public int getEnemigosRestantes() {
        return EnemigosRestantes;
    }

    public void setEnemigosRestantes(int EnemigosRestantes) {
        this.EnemigosRestantes = EnemigosRestantes;
    }

    public int getAliadosRestantes() {
        return AliadosRestantes;
    }

    public void setAliadosRestantes(int AliadosRestantes) {
        this.AliadosRestantes = AliadosRestantes;
    }    
    
     public int getTurno() {
        return Turno;
    }

    public void setTurno(int Turno) {
        this.Turno = Turno;
    }

    public int getExpCombate() {
        return expCombate;
    }

    public void setExpCombate(int expCombate) {
        this.expCombate = expCombate;
    }

    public Item getDropItems() {
        return dropItem;
    }

    public void setDropItems(Item dropItems) {
        this.dropItem = dropItems;
    }

    public int getOroCombate() {
        return oroCombate;
    }

    public void setOroCombate(int oroCombate) {
        this.oroCombate = oroCombate;
    }

    public Item getDropItem() {
        return dropItem;
    }

    public void setDropItem(Item dropItem) {
        this.dropItem = dropItem;
    }
    
    //Genera enemigos
    private ArrayList<Personaje> GeneraArrayEnemigos (int Mapa)
    {
        ArrayList<Personaje> GeneraEnemigos;
        /*Swtch casa aleatorio en funcion del mapa para generar grupos de enemigos*/
        GeneraEnemigos= new ArrayList<Personaje>();
        Random rand = new Random();
        int ini = 0; //dependiendo del nivel emepzamos a buscar en una pos u otra
        int fin = rand.nextInt(6);//Como hay 6 tipos de party elegimos entre la 0-5
        int nivel=0;
        int exptotal=0;
        int dinerototal=0;
        //Nos quedamos el mayor nivel
//        for (int i = 0; i < VenganzaBelial.atributoGestion.jugs.size(); i++) {
//            if (VenganzaBelial.atributoGestion.jugs.get(i).getNivel() > nivel)
//                nivel = VenganzaBelial.atributoGestion.jugs.get(i).getNivel();
//        }
        for (int i = 0; i < VenganzaBelial.atributoGestion.getJugs().size(); i++) {
            if (VenganzaBelial.atributoGestion.getJugs().get(i).getNivel() > nivel)
                nivel = VenganzaBelial.atributoGestion.getJugs().get(i).getNivel();
        }
        switch (Mapa)
        {
            case 0://Caso tutorial, party especial
                Rata rat1 = new Rata(1,1, 100, 50, 30);
                rat1.setVelocidad(99999);
                rat1.setNombre("Rata");
                Rata rat2 = new Rata(2,1, 100, 50, 30);
                rat2.setNombre("Rata");
                Rata rat3 = new Rata(3,1, 100, 50, 30);
                rat3.setNombre("Rata");
                GeneraEnemigos.add((Personaje)rat1);
                GeneraEnemigos.add((Personaje)rat2);
                GeneraEnemigos.add((Personaje)rat3);
                exptotal=15;
                break;
            case 1://Bosque
                if (nivel < 3)
                    ini = 0;
                else if (nivel < 5)
                    ini = 6;
                else
                    ini = 12;
                 ArrayEnemigosUsado = ini + fin;
                 regeneraEnemigos();
                 
                for (int i = 0; i < VenganzaBelial.atributoGestion.getEnem().get(ArrayEnemigosUsado).size(); i++) {
                    GeneraEnemigos.add((Personaje)VenganzaBelial.atributoGestion.getEnem().get(ArrayEnemigosUsado).get(i));
                    Enemigo enemaux=(Enemigo)GeneraEnemigos.get(i);
                    exptotal+=enemaux.getExpAportada();
                    dinerototal+=enemaux.getOro();
                }          
                break;
//            case 2 y 3:
//                //No existe, enemigos por evento
//                break;
            case 4:
                if (nivel < 11)
                    ini = 0;
                else if (nivel < 14)
                    ini = 6;
                else
                    ini = 12;
                 ArrayEnemigosUsado = ini + fin;
                 regeneraEnemigos();
                 
                for (int i = 0; i < VenganzaBelial.atributoGestion.getEnem().get(ArrayEnemigosUsado).size(); i++) {
                    GeneraEnemigos.add((Personaje)VenganzaBelial.atributoGestion.getEnem().get(ArrayEnemigosUsado).get(i));
                    Enemigo enemaux=(Enemigo)GeneraEnemigos.get(i);
                    exptotal+=enemaux.getExpAportada();
                    dinerototal+=enemaux.getOro();
                }     
                break;
            case 6:
                if (nivel < 18)
                    ini = 0;
                else if (nivel < 20)
                    ini = 6;
                else
                    ini = 12;
                 ArrayEnemigosUsado = ini + fin;
                 regeneraEnemigos();
                 
                for (int i = 0; i < VenganzaBelial.atributoGestion.getEnem().get(ArrayEnemigosUsado).size(); i++) {
                    GeneraEnemigos.add((Personaje)VenganzaBelial.atributoGestion.getEnem().get(ArrayEnemigosUsado).get(i));
                    Enemigo enemaux=(Enemigo)GeneraEnemigos.get(i);
                    exptotal+=enemaux.getExpAportada();
                    dinerototal+=enemaux.getOro();
                }     
                break;
            case 10://Boss del Bosque
                BossBosque ygg= new BossBosque(0, 5, 3300, 180, 30);
                GeneraEnemigos.add(ygg);
                dinerototal=ygg.getOro();
                exptotal=ygg.getExpAportada();
                //Drop Arma Kibito 50%
                //Armadura random 50%
                //Exp random entre 100-200
                break;
            case 11://Bandidos Puerto
                Bandido ban1=new Bandido(1,6,650,250,100);
                Bandido ban2= new Bandido(2,6,650,250,100);
                Bandido ban3= new Bandido(3,6,650,250,100);
                GeneraEnemigos.add(ban1);
                GeneraEnemigos.add(ban2);
                GeneraEnemigos.add(ban3);
                dinerototal=100;
                exptotal=50;
                break;
            case 12:
                Bandido ban11=new Bandido(1,6,300,90,80);
                Bandido banBoss=new Bandido(0,6,4500,350,100);
                banBoss.setNombre("Gran Bandido Crow");
                Bandido ban12=new Bandido(2,6,300,90,40);
                dinerototal=1400;
                exptotal=(int) (80+100*(new Random().nextFloat()));
                GeneraEnemigos.add(ban11);
                GeneraEnemigos.add(banBoss);
                GeneraEnemigos.add(ban12);
                break;
            case 13:
                Fanatico fan=new Fanatico(1,6,300,90,40);
                //Corrupto corrup= new Corrupto(1,6,300,90,40);
                Fanatico fan1=new Fanatico(2,6,300,90,40);
                dinerototal=240;
                exptotal=(int) (150+150*(new Random().nextFloat()));
                GeneraEnemigos.add(fan);
                //GeneraEnemigos.add(corrup);
                GeneraEnemigos.add(fan1);
                break;
            case 14:
                Fanatico fan11=new Fanatico(1,6,300,90,40);
                //Muerte muerte= new Muerte(1,6,300,90,40);
                //LiderFanatico fan12=new LiderFanatico(2,6,300,90,40);
                dinerototal=240;
                exptotal=(int) (150+150*(new Random().nextFloat()));
                GeneraEnemigos.add(fan11);
                //GeneraEnemigos.add(corrup);
                //GeneraEnemigos.add(fan12);
                break;
            case 15:
                //EDIT
                Fanatico fan151=new Fanatico(1,6,300,90,40);
                fan151.setNombre("Bellafonte");
//                Pegaso peg=new Pegaso(1,6,300,90,40);
                dinerototal=240;
                exptotal=(int) (150+150*(new Random().nextFloat()));
                GeneraEnemigos.add(fan151);
                break;
            case 16:
                //EDIT
                Fanatico fan161=new Fanatico(1,6,300,90,40);
                fan161.setNombre("Dragón");
//                Pegaso peg=new Pegaso(1,6,300,90,40);
                dinerototal=240;
                exptotal=(int) (150+150*(new Random().nextFloat()));
                GeneraEnemigos.add(fan161);
                break;
            case 17:
                Fanatico fan171=new Fanatico(1,6,300,90,40);
                fan171.setNombre("Jinete Espectral");
//                Pegaso peg=new Pegaso(1,6,300,90,40);
                dinerototal=240;
                exptotal=(int) (150+150*(new Random().nextFloat()));
                GeneraEnemigos.add(fan171);
                break;
        }/*switch (Mapa)*/
        this.setOroCombate(dinerototal);
        this.setExpCombate(exptotal);
        return GeneraEnemigos;
    }/*private ArrayList<Personaje> GeneraArrayEnemigos (int Mapa)*/

    private void regeneraEnemigos()
    {
        Enemigo enem;
        for (int i = 0; i < VenganzaBelial.atributoGestion.getEnem().get(ArrayEnemigosUsado).size(); i++) {
            enem=VenganzaBelial.atributoGestion.getEnem().get(ArrayEnemigosUsado).get(i);
            enem.setHpActual(enem.getHp());
        }
    }/*private void regeneraEnemigos()*/
}