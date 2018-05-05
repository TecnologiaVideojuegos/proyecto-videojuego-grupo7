package estados;

import java.util.Random;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.TrueTypeFont;
import java.awt.Font;

public class Heroe {
    //Para renderizar eventos simples
    private static final int POSICIONAVATARX = 30;
    private static final int POSICIONAVATARY = 620;
    private static final int TAMANYOAVATARX = 115;
    private static final int TAMANYOAVATARY = 115;
    private boolean renderizarEvento=false;
    private Font tipoLetra  =new Font("Verdana", Font.PLAIN, 10);
    private TrueTypeFont mensajePantalla= new TrueTypeFont(tipoLetra, true);
    private Image ventanaDialogo;
    private String linea1="";
    private String linea2="";
    private String linea3="";
    private String linea4="";
    ///////////////////////////////////////////
    private Animation hero, movementUp, movementDown, movementLeft, movementRight, stillUp, stillDown, stillLeft, stillRight;
    private char ultimaDireccion;
    private Vector2f pos;
    private Rectangle rectangle;
    private static final int ANIMATIONSPEED = 500;
    private static final float SPEED = 0.1f;//0.1f
    private int w, h;
    private float tasaAparicion=0.02f;//0-1(0-100%)
    private int offset=6;//Original=-4
    //EDIT
    private float aparicion=1;

    
    //EDIT
    public Heroe(float x, float y) throws SlickException {
        Image[] animationUp = {new Image("Imagenes/HeroeMundo/her20.png"), new Image("Imagenes/HeroeMundo/her22.png")};
        Image[] animationDown = {new Image("Imagenes/HeroeMundo/her00.png"), new Image("Imagenes/HeroeMundo/her02.png")};
        Image[] animationLeft = {new Image("Imagenes/HeroeMundo/her30.png"), new Image("Imagenes/HeroeMundo/her32.png")};
        Image[] animationRight = {new Image("Imagenes/HeroeMundo/her10.png"), new Image("Imagenes/HeroeMundo/her12.png")};
        Image[] up = {new Image("Imagenes/HeroeMundo/her21.png")};
        Image[] down = {new Image("Imagenes/HeroeMundo/her01.png")};
        Image[] left = {new Image("Imagenes/HeroeMundo/her31.png")};
        Image[] right = {new Image("Imagenes/HeroeMundo/her11.png")};

        w = down[0].getWidth();
        h = down[0].getHeight();
        
        pos = new Vector2f(x, y);
        rectangle = new Rectangle(x, y, w, h);
        
        stillUp = new Animation(up, ANIMATIONSPEED);
        stillDown = new Animation(down, ANIMATIONSPEED);
        stillLeft = new Animation(left, ANIMATIONSPEED);
        stillRight = new Animation(right, ANIMATIONSPEED);
        movementUp = new Animation(animationUp, ANIMATIONSPEED);
        movementDown = new Animation(animationDown, ANIMATIONSPEED);
        movementLeft = new Animation(animationLeft, ANIMATIONSPEED);
        movementRight = new Animation(animationRight, ANIMATIONSPEED);

        hero = stillDown;
        //EDIT
        ventanaDialogo= new Image("Imagenes/Avatar/bocadillo.png");
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta, EstadoMapaJuego gps) {
        Input input = gc.getInput();
        controlaEvento(input);
        if (input.isKeyDown(Input.KEY_UP)) {
            if (!gps.isBlocked(pos.x + w -offset, pos.y - delta * SPEED) && !gps.isBlocked(pos.x + offset, pos.y - delta * SPEED)) {
                pos.y -= delta * SPEED;
            }
            hero = movementUp;
            hero.update(delta);
            ultimaDireccion = 'u';
        } else if (input.isKeyDown(Input.KEY_DOWN)) {
            if (!gps.isBlocked(pos.x + w - offset, pos.y + h + delta * SPEED) && !gps.isBlocked(pos.x + offset, pos.y + h + delta * SPEED)) {
                pos.y += delta * SPEED;
            }
            hero = movementDown;
            hero.update(delta);
            ultimaDireccion = 'd';
        } else if (input.isKeyDown(Input.KEY_LEFT)) {
            if (!gps.isBlocked(pos.x - delta * SPEED, pos.y + offset) && !gps.isBlocked(pos.x - delta * SPEED, pos.y + h - offset)) {
                pos.x -= delta * SPEED;
            }
            hero = movementLeft;
            hero.update(delta);
            ultimaDireccion = 'l';

        } else if (input.isKeyDown(Input.KEY_RIGHT)) {
            if (!gps.isBlocked(pos.x + w + delta * SPEED, pos.y + h - offset) && !gps.isBlocked(pos.x + w + delta * SPEED, pos.y + offset)) {
                pos.x += delta * SPEED;
            }
            hero = movementRight;
            hero.update(delta);
            ultimaDireccion = 'r';
        } 
        
        
        else {
            switch (ultimaDireccion) {
                case 'd':
                    hero = stillDown;
                    break;
                case 'u':
                    hero = stillUp;
                    break;
                case 'l':
                    hero = stillLeft;
                    break;
                case 'r':
                    hero = stillRight;
                    break;
            }
        }/*else*/
        //EDIT:Pruebas
        ApareceEnemigo( gc,  sbg,  delta,  gps,  input);
        ApareceEvento( gc,  sbg,  delta,  gps,  input);
    }

    public void render() {
        //EDIT:Debug Param
        TrueTypeFont textoDebug;
        java.awt.Font letra  =new java.awt.Font("Verdana", java.awt.Font.PLAIN, 12); 
        textoDebug= new TrueTypeFont(letra,true);
        textoDebug.drawString(500, 30, "Tasa: "+this.aparicion);
        //
        hero.draw(pos.x, pos.y);
        renderEvento();
        
    }

    public float getX() {
        return pos.x;
    }

    public float getY() {
        return pos.y;
    }

    public Vector2f getpos() {
        return pos;
    }

    public void setpos(Vector2f pos) {
        this.pos = pos;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
    
    //EDIT:Comprobando funcion de enemigos
    private void ApareceEnemigo(GameContainer gc, StateBasedGame sbg, int delta, EstadoMapaJuego gps, Input input)
    {
        Random rand=new Random();
        //aparicion=0;
        if (input.isKeyDown(Input.KEY_UP)) {
            if (gps.isEnemigos(pos.x + w -4, pos.y - delta * SPEED) && gps.isEnemigos(pos.x + 4, pos.y - delta * SPEED)) {
                aparicion=rand.nextFloat();
            }
        } else if (input.isKeyDown(Input.KEY_DOWN)) {
            if (gps.isEnemigos(pos.x + w - 4, pos.y + h + delta * SPEED) && gps.isEnemigos(pos.x + 4, pos.y + h + delta * SPEED)) {
                aparicion=rand.nextFloat();
                //
            }
        } else if (input.isKeyDown(Input.KEY_LEFT)) {
            if (gps.isEnemigos(pos.x - delta * SPEED, pos.y + 4) && gps.isEnemigos(pos.x - delta * SPEED, pos.y + h - 4)) {
                aparicion=rand.nextFloat();
            }

        } else if (input.isKeyDown(Input.KEY_RIGHT)) {
            if (gps.isEnemigos(pos.x + w + delta * SPEED, pos.y + h - 4) && gps.isEnemigos(pos.x + w + delta * SPEED, pos.y + 4)) {
                aparicion=rand.nextFloat();
            }
        }
        if(aparicion<tasaAparicion){
            sbg.enterState(VenganzaBelial.ESTADOCOMBATE);
            aparicion=1;
        }
    }/**/
    
    private void ApareceEvento(GameContainer gc, StateBasedGame sbg, int delta, EstadoMapaJuego gps, Input input)
    {
        //EDIT: Llamada a clase eventos, ajustar colision
        int tipo;
        int posicion[] = {0,0};
        boolean evento = false;
        if (input.isKeyDown(Input.KEY_UP)) {
            if (gps.isEventos(pos.x + w -offset, pos.y - delta * SPEED) && gps.isEventos(pos.x + offset, pos.y - delta * SPEED)) {
                posicion = gps.devuelvePosicion(pos.x + w -offset, pos.y- delta * SPEED);
                evento = true;
            } 
        } else if (input.isKeyDown(Input.KEY_DOWN)) {
            if (gps.isEventos(pos.x + w - offset, pos.y + h + delta * SPEED) && gps.isEventos(pos.x + offset, pos.y + h + delta * SPEED)) {
                posicion=gps.devuelvePosicion(pos.x + w - offset, pos.y + h + delta * SPEED);
                evento = true;
            }
        } else if (input.isKeyDown(Input.KEY_LEFT)) {
            if (gps.isEventos(pos.x - delta * SPEED, pos.y + offset) && gps.isEventos(pos.x - delta * SPEED, pos.y + h - offset)) {
                posicion=gps.devuelvePosicion(pos.x - delta * SPEED, pos.y + offset);
                evento = true;
            }

        } else if (input.isKeyDown(Input.KEY_RIGHT)) {
            if (gps.isEventos(pos.x + w + delta * SPEED, pos.y + h - offset) && gps.isEventos(pos.x + w + delta * SPEED, pos.y + 4)) {
                posicion=gps.devuelvePosicion(pos.x + w + delta * SPEED , pos.y + h - offset);
                evento = true;
            }
        }
        if(evento){
            tipo = VenganzaBelial.eventos.comprobarEvento(posicion[0], posicion[1], VenganzaBelial.atributoGestion.getMapaActual());
            switch (tipo) {
                case 0://Renderizar dialogo simple
                    renderizarEvento=true;
                    break;
                case 1://Tienda
                    sbg.enterState(VenganzaBelial.ESTADOTIENDA);
                    break;
                case 2://Healier
                    //Cura a los aliados al maximoVenganzaBelial.atributoGestion.jugs.get(0).setHpActual(0);
                    VenganzaBelial.atributoGestion.getJugs().get(0).setHpActual(VenganzaBelial.atributoGestion.getJugs().get(0).getHp());
                    VenganzaBelial.atributoGestion.getJugs().get(1).setHpActual(VenganzaBelial.atributoGestion.getJugs().get(1).getHp());
                    VenganzaBelial.atributoGestion.getJugs().get(2).setHpActual(VenganzaBelial.atributoGestion.getJugs().get(2).getHp());
                    VenganzaBelial.atributoGestion.getJugs().get(0).setMpActual(VenganzaBelial.atributoGestion.getJugs().get(0).getMp());
                    VenganzaBelial.atributoGestion.getJugs().get(1).setMpActual(VenganzaBelial.atributoGestion.getJugs().get(1).getMp());
                    VenganzaBelial.atributoGestion.getJugs().get(2).setMpActual(VenganzaBelial.atributoGestion.getJugs().get(2).getMp());
                    //Cuenta la tipica historia de te voy a curar
                    renderizarEvento=true;
                    break;
                case 3://Escena
                      sbg.enterState(VenganzaBelial.eventos.getNextEstado());  
                    break;
                case 4://Batalla definida(Boss o eventos especiales)
                    break;
                default:
                    break;
            }
        }
    }
    
    
    
    private void renderEvento()
    {
        if(renderizarEvento)
        {       
            Vector2f posicionEvento= VenganzaBelial.eventos.getPosicionEvento();
            this.ventanaDialogo.draw(posicionEvento.x*this.w+10, posicionEvento.y*this.h+10, 1);
    //      ///////////////////////////////////,"////////////////////////////////////////////////////////"/;
            //mensajePantalla.drawString(100, 200,""+posicionEvento,new Color(255,0,0));
            mensajePantalla.drawString(posicionEvento.x*this.w+30, posicionEvento.y*this.h+110,linea1,new Color(0,0,0));
            mensajePantalla.drawString(posicionEvento.x*this.w+30, posicionEvento.y*this.h+125,linea2,new Color(0,0,0));
            mensajePantalla.drawString(posicionEvento.x*this.w+30, posicionEvento.y*this.h+140,linea3,new Color(0,0,0));
            mensajePantalla.drawString(posicionEvento.x*this.w+30, posicionEvento.y*this.h+155,linea4,new Color(0,0,0));
        }
    }
    private void controlaEvento(Input input)
    {
        if(input.isKeyPressed(Input.KEY_ENTER))
            if(renderizarEvento)
                renderizarEvento=false;
        if(renderizarEvento){
            linea1 = "Hola mi nombre es " + VenganzaBelial.eventos.getEvento().getNombre();
            linea2 = VenganzaBelial.eventos.getEvento().getSaludo();
            linea3 = VenganzaBelial.eventos.getEvento().getHistoria();
        }
    }

}