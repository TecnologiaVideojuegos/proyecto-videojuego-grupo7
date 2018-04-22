
package estados;

import items.Consumible;
import items.Item;
import java.awt.Font;
import java.util.ArrayList;
import npcs.Vendedor;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import otros.Inventario;

public class EstadoTienda extends BasicGameState {
    private Input input;
    private int estado;
    private static final int NUMESTADOS = 4;//EDIT:Deben ser 3
    private static final int MENUBASE = 0;
    private static final int COMPRANDO = 1;
    private static final int VENDIENDO = 2;
    private static final int SALIENDO = 3;
    //
    private static final int NUMOPCIONES = 3;//EDIT:Deben ser 3
    private static final int COMPRAR = 0;
    private static final int VENDER = 1;
    private static final int SALIR = 2;
    /**/
    private Image fondo;
    private Image vendedor;
    private String[] opciones = new String[NUMOPCIONES];
    private Font letraMenu, letraEquipo, letraTitulo;
    private TrueTypeFont opcionesJugadorTTF, texto;
    private int eleccionJugador, idEstado;
    private Color notChosen = new Color(153, 204, 255);
    private Color amarillo = new Color(255, 255, 0);
    /*Sonido*/
    private Sound sonidoSelect, sonidoError;
    //EDIT:Vndedores
    private Vendedor ven1;
    private Vendedor ven2;
    private Vendedor ven3;
    //EDIT:Inventarios
    private ArrayList<Item> mercanciaActual;
    private ArrayList<Item> mercancia1;
    private boolean flagEntrando=true;
    //
    public EstadoTienda(int id) {
        idEstado = id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        estado=MENUBASE;
        fondo= new Image("Imagenes/Fondos/tienda.jpg");
        vendedor= new Image("Imagenes/Personajes/anna.png");
        letraMenu = new Font("Verdana", Font.BOLD, 30);
        opcionesJugadorTTF = new TrueTypeFont(letraMenu, true);
        Font tipoLetra  =new Font("Verdana", Font.PLAIN, 15);
        texto = new TrueTypeFont(tipoLetra, true);
        opciones[0] = "Comprar";
        opciones[1] = "Vender";
        opciones[2] = "Salir";
        input = gc.getInput();
        /*cARGAR sONIDO*/
        sonidoSelect=new Sound("Musica/Efectos/select.wav");
        sonidoError=new Sound("Musica/Efectos/error.wav");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        fondo.draw(0, 0, VenganzaBelial.WIDTH, VenganzaBelial.HEIGHT);
        switch(estado)
        {
            case MENUBASE:
                renderOpcionesJugador();
                break;
            case COMPRANDO:
                renderComprando();
                break;
            case VENDIENDO:
                renderVendiendo();
                break;
        }
        vendedor.draw(800, 130);
        opcionesJugadorTTF.drawString(1000, 650, "Dinero: "+VenganzaBelial.atributoGestion.inv.getDinero(),amarillo);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        iniciaMercancia();
        if(this.flagEntrando)
        {
            eligeMercancia();
            this.flagEntrando=false;
        }
        switch(estado)
        {
            case MENUBASE:
                OpcionControl(NUMOPCIONES);
                menuBase(sbg);
                break;
            case COMPRANDO:
                OpcionControl(this.mercanciaActual.size());
                comprando();
                break;
            case VENDIENDO:
                OpcionControl(VenganzaBelial.atributoGestion.inv.getItems().size());
                vendiendo();
                break;
            case SALIENDO:
                break;
        }
        ReiniciarSeleccion();
    }
    
    private void OpcionControl(int NumeroOpciones)
    {
        /*Selector de Acciones de Usuario*/
            if (input.isKeyPressed(Input.KEY_DOWN)) 
            {
                if (eleccionJugador == (NumeroOpciones - 1)) 
                {
                    eleccionJugador = 0;
                } 
                else 
                {
                    eleccionJugador++;
                }
            }
            if (input.isKeyPressed(Input.KEY_UP)) 
            {
                if (eleccionJugador == 0) {
                    eleccionJugador = NumeroOpciones - 1;
                } else {
                    eleccionJugador--;
                }
            }                       
    }/*private void OpcionesBase()*/
    
    private void ReiniciarSeleccion()
    {
        if(input.isKeyPressed(Input.KEY_ESCAPE))
        {
            eleccionJugador=0;
            estado=MENUBASE;
            //sonidoSelect.play(1, 0.2f);
        }/**/
    }/*private void ReiniciarSeleccion()*/
    
    private void menuBase(StateBasedGame sbg)
    {
        if(input.isKeyPressed(Input.KEY_ENTER)) 
        {
           switch(eleccionJugador)
           {
               case COMPRAR:
                   estado=COMPRANDO;
                   break;
               case VENDER:
                   estado=VENDIENDO;
                   break;
               case SALIR:
                   this.flagEntrando=true;
                   //sbg.enterState(VenganzaBelial.ESTADOMENUINICIO);//EDIT
                   sbg.enterState(VenganzaBelial.ESTADOMAPAJUEGO);//EDIT
                   break;
           }
           eleccionJugador=0;
        }/*if(input.isKeyPressed(Input.KEY_ENTER)) */
    }/*private void menuBase()*/
    
    private void comprando()
    {
       if(input.isKeyPressed(Input.KEY_ENTER)) 
       {
           compraObjeto(this.mercanciaActual.get(eleccionJugador));
           if(eleccionJugador>=mercanciaActual.size())
                eleccionJugador--;//EDIT
       } 
    }
    
    private void compraObjeto(Item objeto)
    {
        int dineroRestante;
        if(VenganzaBelial.atributoGestion.inv.getDinero()>objeto.getPrecioCompra())
        {
            if(objeto.getTipoItem()!=0)//Si no es un consumible
            {
                if(VenganzaBelial.atributoGestion.inv.addItem(objeto))
                {
                    //Restamos el dinero
                    dineroRestante=VenganzaBelial.atributoGestion.inv.getDinero()-objeto.getPrecioCompra();
                    VenganzaBelial.atributoGestion.inv.setDinero(dineroRestante);
                    //Eliminamos el obejeto de mercancias
                    this.mercanciaActual.remove(objeto);
                    //EDIT: hacer algo para eliminar de la base de datos de mercancias.
                }
                else
                {
                    //sonido error
                }
            }
            else//El objeto a comprar es un Item
            {
                
                Consumible consumInv;
                Consumible consumTienda=(Consumible)objeto;
                if(consumTienda.getNumero()>0)
                {
                    //if(objeto.getNombre()=="PocionVida")
                    if(objeto.getNombre().matches("PocionVida"))
                    {
                        consumInv = (Consumible)VenganzaBelial.atributoGestion.inv.getItems().get(0); 
                        if(consumInv.getNumero()<consumInv.getCapacidad())
                        {
                            consumInv.setNumero(consumInv.getNumero()+1);
                            consumTienda.setNumero(consumTienda.getNumero()-1);
                            //Restamos el dinero
                            dineroRestante=VenganzaBelial.atributoGestion.inv.getDinero()-objeto.getPrecioCompra();
                            VenganzaBelial.atributoGestion.inv.setDinero(dineroRestante);       
                        }
                    }/*if(objeto.getNombre()=="PocionVida")*/
                    //else if(objeto.getNombre()=="PocionMana")
                    else if(objeto.getNombre().matches("PocionMana"))
                    {
                        consumInv = (Consumible)VenganzaBelial.atributoGestion.inv.getItems().get(1); 
                        if(consumInv.getNumero()<consumInv.getCapacidad())
                        {
                            consumInv.setNumero(consumInv.getNumero()+1);
                            consumTienda.setNumero(consumTienda.getNumero()-1);
                            //Restamos el dinero
                            dineroRestante=VenganzaBelial.atributoGestion.inv.getDinero()-objeto.getPrecioCompra();
                            VenganzaBelial.atributoGestion.inv.setDinero(dineroRestante);  
                        }
                    }/*if(objeto.getNombre()=="PocionMana")*/
                    //else if(objeto.getNombre()=="PocionResucitar")
                    else if(objeto.getNombre().matches("PocionResucitar"))
                    {
                        
                        consumInv = (Consumible)VenganzaBelial.atributoGestion.inv.getItems().get(2); 
                        if(consumInv.getNumero()<consumInv.getCapacidad())
                        {
                            consumInv.setNumero(consumInv.getNumero()+1);
                            consumTienda.setNumero(consumTienda.getNumero()-1);
                            //Restamos el dinero
                            dineroRestante=VenganzaBelial.atributoGestion.inv.getDinero()-objeto.getPrecioCompra();
                            VenganzaBelial.atributoGestion.inv.setDinero(dineroRestante);  
                        }
                    }/*if(objeto.getNombre()=="PocionResucitar")*/
                }/*if(consumTienda.getNumero()>0)*/
                else
                {
                    //sonido error
                }/**/
            }     
        }/*if(VenganzaBelial.atributoGestion.inv.getDinero()>objeto.getPrecioCompra())*/          
    }/*private void compraObjeto(Item objeto)*/
    
    private void vendiendo()
    {
        int nuevoDinero;
        
        if(input.isKeyPressed(Input.KEY_ENTER)) 
        {
            if(VenganzaBelial.atributoGestion.inv.getItems().get(eleccionJugador).getTipoItem()!=0)
            {
                //EDIT: Comprobar
                nuevoDinero=VenganzaBelial.atributoGestion.inv.getDinero()+VenganzaBelial.atributoGestion.inv.getItems().get(eleccionJugador).getPrecioVenta();
                VenganzaBelial.atributoGestion.inv.setDinero(nuevoDinero);
                VenganzaBelial.atributoGestion.inv.borrarItem(VenganzaBelial.atributoGestion.inv.getItems().get(eleccionJugador));
                if(eleccionJugador>=VenganzaBelial.atributoGestion.inv.getItems().size())
                     eleccionJugador--;//EDIT
            }
            else
            {
                Consumible consum=(Consumible)VenganzaBelial.atributoGestion.inv.getItems().get(eleccionJugador);
                if(consum.getNumero()>0)
                {
                    //if(objeto.getNombre()=="PocionVida")
                    if(consum.getNombre().matches("PocionVida"))
                    {
                        consum.setNumero(consum.getNumero()-1);
                        //Restamos el dinero
                        nuevoDinero=VenganzaBelial.atributoGestion.inv.getDinero()+consum.getPrecioVenta();
                        VenganzaBelial.atributoGestion.inv.setDinero(nuevoDinero);       
                    }/*if(objeto.getNombre()=="PocionVida")*/
                    //else if(objeto.getNombre()=="PocionMana")
                    else if(consum.getNombre().matches("PocionMana"))
                    {
                        consum.setNumero(consum.getNumero()-1);
                        //Restamos el dinero
                        nuevoDinero=VenganzaBelial.atributoGestion.inv.getDinero()+consum.getPrecioVenta();
                        VenganzaBelial.atributoGestion.inv.setDinero(nuevoDinero); 
                    }/*if(objeto.getNombre()=="PocionMana")*/
                    //else if(objeto.getNombre()=="PocionResucitar")
                    else if(consum.getNombre().matches("PocionResucitar"))
                    {
                       consum.setNumero(consum.getNumero()-1);
                        //Restamos el dinero
                        nuevoDinero=VenganzaBelial.atributoGestion.inv.getDinero()+consum.getPrecioVenta();
                        VenganzaBelial.atributoGestion.inv.setDinero(nuevoDinero); 
                    }/*if(objeto.getNombre()=="PocionResucitar")*/
                }/*if(numero>0)*/
            }
        }/*if(input.isKeyPressed(Input.KEY_ENTER)) */
    }/*private void vendiendo()*/
    
    private void iniciaMercancia()
    {
        //EDIT:Posible base de datos
        //(int danyo,  critico,  nombre,  descripcion, requisitoCategoria,  requisitoNivel,  precioCompra,  precioVenta)
//        ArrayList<String> requisitos = new ArrayList<>();
//        requisitos.add("Horacia");
//        Arma arma2 = new Arma(20, 1, "Espada de bronce", "No muy afilada, pero es mejor que un garrote", requisitos, 5, 51, 25);
//        Armadura armadura2 = new Armadura(20, "Armadura de bronce", "Por fin algo de protección", requisitos, 5, 52, 25);
//        //
//        requisitos.remove(0);
//        requisitos.add("Mordeim");
//        Arma arma3 = new Arma(20, 1, "Navaja", "Para atracar a ancianitas y ser el más malo del barrio", requisitos, 5, 53, 25);
//        Armadura armadura3 = new Armadura(20, "Capa de cuero", "No protege mucho pero abriga contra el frio", requisitos, 5, 54, 25);
//        requisitos.remove(0);
//        requisitos.add("Kibito");
//        Arma arma4 = new Arma(20, 1, "Vara de olivo", "Serás el terror de los alérgicos al olivo", requisitos, 5, 55, 25);
//        Armadura armadura4 = new Armadura(20, "Capa de tela", "La normas impiden llevar al mago algo que proteja demasiado", requisitos, 5, 56, 25);
//        //
//        Consumible pocionVida = new Consumible(20, 0, 5, "PocionVida", "Pocion que sirve para curar tu vida",
//            requisitos, 1, 60, 20);
//        Consumible pocionMana = new Consumible(0, 20, 5, "PocionMana", "Pocion que sirve para curar tu mana",
//            requisitos, 1, 55, 20);
//        Consumible pocionRes = new Consumible(50, 0, 2, "PocionResucitar", "Pocion que sirve para resucitar un jugador",
//            requisitos, 1, 200, 80);
        //
        mercancia1= new ArrayList<>();
        for (int i = 0; i < VenganzaBelial.eventos.getVendedor().getItems().size(); i++) {
            mercancia1.add(VenganzaBelial.eventos.getVendedor().getItems().get(i));
        }

//        mercancia1.add(pocionVida);
//        mercancia1.add(pocionMana);
//        mercancia1.add(pocionRes);
//        mercancia1.add(arma2);
//        mercancia1.add(arma3);
//        mercancia1.add(arma4);
//        mercancia1.add(armadura2);
//        mercancia1.add(armadura3);
//        mercancia1.add(armadura4);
        
    }
    
    private void eligeMercancia()
    {    
        //EDIT: mirar bien el switch
//        switch(VenganzaBelial.MapaActual)
//        {
//            case 0:
//                mercanciaActual= mercancia1;
//                break;
//            case 1:
//                mercanciaActual= mercancia1;
//                //for (int i = 0; i < mercancia1.size(); i++) {
//                //    mercanciaActual.add(mercancia1.get(i));
//                //}
//                break;      
//        }
        mercanciaActual= mercancia1;
    }/*private void eligeMercancia()*/
    
    
    private void renderOpcionesJugador() {
        for (int i = 0; i < NUMOPCIONES; i++) {
            if (eleccionJugador == i) {
                opcionesJugadorTTF.drawString(100, i * 50 + 200, opciones[i]);
            } else {
                opcionesJugadorTTF.drawString(100, i * 50 + 200, opciones[i], notChosen);
            }
        }
    }
    
    
    private void renderComprando()
    {
        String nombreItem="";
        for (int i = 0; i < mercanciaActual.size(); i++) {
            if(mercanciaActual.get(i).getTipoItem()==0)
            {
                Consumible consum=(Consumible)mercanciaActual.get(i);
                nombreItem= consum.getNombre()+" ("+consum.getNumero()+"/"+consum.getCapacidad()+")";
            }
            else
            {
                nombreItem=mercanciaActual.get(i).getNombre();
            }
            
            if (eleccionJugador == i) {
                opcionesJugadorTTF.drawString(100, i * 50 + 50, nombreItem); 
            } else {
                opcionesJugadorTTF.drawString(100, i * 50 + 50,  nombreItem, notChosen);
            }
            opcionesJugadorTTF.drawString(550, i * 50 + 50, ""+mercanciaActual.get(i).getPrecioCompra(),amarillo);
        }
        this.texto.drawString(800, 100, this.mercanciaActual.get(eleccionJugador).getDescripcion());
        texto.drawString(650, 0, "Tienda>>Comprar");
    }/*private void renderComprando()*/
    
    private void renderVendiendo()
    {
        Inventario inven = VenganzaBelial.atributoGestion.inv;
        int espaciosLlenos= inven.getItems().size();
        String nombreItem="";
        for (int i=0;i<inven.getCapacidadInv();i++)
        {
            if(i<espaciosLlenos)
            {
                if(i<3)
                {
                    Consumible consum = (Consumible)inven.getItems().get(i);
                    nombreItem= consum.getNombre()+" ("+consum.getNumero()+"/"+consum.getCapacidad()+")";
                }
                else
                    nombreItem=inven.getItems().get(i).getNombre();
                
                if(eleccionJugador==i)
                {
                   opcionesJugadorTTF.drawString(100,i*50+50,nombreItem);
                }
                else{
                    opcionesJugadorTTF.drawString(100, i*50 + 50, nombreItem, notChosen);
                }
                opcionesJugadorTTF.drawString(550, i * 50 + 50, ""+inven.getItems().get(i).getPrecioVenta(),amarillo);
            }
            else
                opcionesJugadorTTF.drawString(100,i*50+50,"---");
        }
        /*Render Descripcion de Habilidad Bajo Seleccion*/
        this.texto.drawString(800, 100, inven.getItems().get(eleccionJugador).getDescripcion());
        //
        texto.drawString(650, 0, "Tienda>>Vender");
    }/* private void renderInventario()*/

    
}
