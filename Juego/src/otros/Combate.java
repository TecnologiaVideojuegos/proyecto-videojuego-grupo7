package otros;

import personajes.Personaje;
import java.util.ArrayList;
/*EDIT: Eliminar Import VenganzaBelial*/
import estados.VenganzaBelial;

public final class Combate {
    //Atributos
    private ArrayList<Personaje> ordenPersonajes = new ArrayList<Personaje>();
    private int nParticipantes; //N de participantes en el combate
    private ArrayList<Personaje> Enemigos;
    private int EnemigosRestantes;
    private int AliadosRestantes;
    private int Turno=0;
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
        float Modificador= 0.5f;
        /*Variables Auxiliares*/
        int auxIndex;
        int auxCount;
        int maxId;
        int maximo;
        /*Bucle de calculo de iniciativas*/
        for(auxIndex=0;auxIndex<this.nParticipantes;auxIndex++)
        {
            /*Randomizar Modificador entre 0.5 y 1*/
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
    
    private ArrayList<Personaje> GeneraArrayEnemigos (int Mapa)
    {
        ArrayList<Personaje> GeneraEnemigos;
        /*Swtch casa aleatorio en funcion del mapa para generar grupos de enemigos*/
        GeneraEnemigos= new ArrayList<Personaje>();
        switch (Mapa)
        {
            case 0:
                //EDIT: Eliminar
                GeneraEnemigos.add(VenganzaBelial.hori);
                GeneraEnemigos.add(VenganzaBelial.mordi);
                GeneraEnemigos.add(VenganzaBelial.kibi);
                break;
            case 1:
                break;
            case 2:
                break;
        }/*switch (Mapa)*/
        return GeneraEnemigos;
    }    
    public void Atacar(Personaje Atacante, Personaje Defensor)
    {
        /*Calculo de daño*/
        int DañoCausado=Atacante.getAtaque()-Defensor.getDefensa();
        if (DañoCausado>0)
        {
          Defensor.setHpActual(Defensor.getHpActual()- DañoCausado);
        }/*if (DañoCausado>0)*/        
    }/*private void Atacar*/
    
    public void GestionaMuertes()
    {
        int aux;
        for(aux=0;aux<this.nParticipantes;aux++)
        {
            if(!this.ordenPersonajes.get(aux).estaVivo())
            {
                /*EDIT:COMPROBAR SI ES PJ O ENEMIGO para disminuir el contador de unos u otro*/
                //this.AliadosRestantes--;
                //this.EnemigosRestantes--;
                /*Remover de la lista de turnos*/
                this.ordenPersonajes.remove(aux);
            }/*if(!this.ordenPersonajes.get(aux).estaVivo())*/
        }/*for(aux=0;aux<this.nParticipantes;aux++)*/
    }/*public void GestionaMuertes()*/
    
    public boolean CombateAcabado()
    {
        if(AliadosRestantes==0 || EnemigosRestantes==0)
        {
            return true;
        }
        else
        {   
            return false;
        }
    }/*FinCombate()*/
    
    public boolean CombateGanado()
    {
       if(AliadosRestantes!=0 && EnemigosRestantes ==0)
       {
           return true;
       }
       else
       {
           return false;
       }
    }/*public boolean CombateGanado()*/
    
    public boolean GestionaSiguienteTurno()
    {
       Turno++;
       this.nParticipantes=this.ordenPersonajes.size();
       if(Turno==this.nParticipantes)
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
}