package estados;

import java.util.Random;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Heroe {

    private Animation hero, movementUp, movementDown, movementLeft, movementRight, stillUp, stillDown, stillLeft, stillRight;
    private char ultimaDireccion;
    private Vector2f pos;
    private Rectangle rectangle;
    private static final int ANIMATIONSPEED = 500;
    private static final float SPEED = 0.1f;
    private int w, h;
    
    private int offset=6;//Original=-4
    //EDIT
    private float aparicion=0;

    private final int MAPATUTORIAL = 0;
    private final int MAPABOSQUE = 1;
    private final int MAPAPUERTO = 2;
    private final int MAPACIUDADCATACUMBAS = 3;
    private final int MAPADUNGEONCATACUMBAS = 4;
    private final int MAPACIUDADMONTANA = 5;
    private final int MAPADUNGEONMONTANA = 6;
    
    private int numMapa = MAPABOSQUE;
    
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
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta, EstadoMapaJuego gps) {
        Input input = gc.getInput();

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
        if(aparicion>0.99f){
            sbg.enterState(VenganzaBelial.ESTADOCOMBATE);
            aparicion=0;
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
            tipo = VenganzaBelial.eventos.comprobarEvento(posicion[0], posicion[1], numMapa);
            if (tipo == 0)
                sbg.enterState(VenganzaBelial.ESTADOEVENTO);
            else if (tipo == 1)
                sbg.enterState(VenganzaBelial.ESTADOTIENDA);
            //else if (tipo == 2)
                //Accion de Healer
        }
    }

}