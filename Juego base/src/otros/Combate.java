package otros;

import personajes.Personaje;
import java.util.ArrayList;

public final class Combate {
    //Atributos
    private ArrayList<Personaje> ordenPersonajes = new ArrayList<Personaje>();
    private int nParticipantes; //N de participantes en el combate
    private int nEnemigos;
    private int nPJ;

    //Constructor
    public Combate(ArrayList<Personaje> participantes, int numeroEnemigos, int numeroPJs) 
    {
        setnParticipantes(participantes.size());//numero de participante en combate
        setnEnemigos(numeroEnemigos);//numero de enemigos a abatir
        setnPJ(numeroPJs);//numero de PJ vivos restantes
        OrdenaTurnos(participantes);//Funcion que asigna turnos
        
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
    
    //Desarrollo del combate completo
    public void DesarrollaCombate()
    {
        int indiceTurno=0;
        //Bucle de turnos mientras queden enemigos O aliados
        while(this.nEnemigos!=0 || this.nPJ!=0)
        {
            //Comprueba que el el personaje esta vivo
            if(ordenPersonajes.get(indiceTurno).estaVivo())
            {
                    //Metodo "Combatir"

                    //Manual para jugador

                    //Automatico para enemigo
                indiceTurno++; //Siguiente personaje/enemigo en cola
            }/*if(ordenPersonajes[indiceTurno].estaVivo()) END*/
            else
            {
                ordenPersonajes.remove(ordenPersonajes.get(indiceTurno));
                nParticipantes--;
            }
            
            if (indiceTurno>this.nParticipantes)
            {
                indiceTurno=0;//Bucle turnos
            }/*if (indiceTurno>this.nParticipantes) END*/
        }/*while(nEnemigos!=0 || nPJ!=0) */
    }/*public void DesarrollaCombate() END*/
    
    //Getter and setter
        public int getnParticipantes() {
        return nParticipantes;
    }

    public void setnParticipantes(int nParticipantes) {
        this.nParticipantes = nParticipantes;
    }

    public int getnEnemigos() {
        return nEnemigos;
    }

    public void setnEnemigos(int nEnemigos) {
        this.nEnemigos = nEnemigos;
    }

    public int getnPJ() {
        return nPJ;
    }

    public void setnPJ(int nPJ) {
        this.nPJ = nPJ;
    }
}