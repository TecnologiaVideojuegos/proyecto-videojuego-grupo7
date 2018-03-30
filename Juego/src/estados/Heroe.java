package estados;

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

    public Heroe(float x, float y) throws SlickException {
        Image[] animationUp = {new Image("Imagenes/HeroeMundo/hero20.png"), new Image("Imagenes/HeroeMundo/hero22.png")};
        Image[] animationDown = {new Image("Imagenes/HeroeMundo/hero00.png"), new Image("Imagenes/HeroeMundo/hero02.png")};
        Image[] animationLeft = {new Image("Imagenes/HeroeMundo/hero30.png"), new Image("Imagenes/HeroeMundo/hero32.png")};
        Image[] animationRight = {new Image("Imagenes/HeroeMundo/hero10.png"), new Image("Imagenes/HeroeMundo/hero12.png")};
        Image[] up = {new Image("Imagenes/HeroeMundo/hero21.png")};
        Image[] down = {new Image("Imagenes/HeroeMundo/hero01.png")};
        Image[] left = {new Image("Imagenes/HeroeMundo/hero31.png")};
        Image[] right = {new Image("Imagenes/HeroeMundo/hero11.png")};

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
            if (!gps.isBlocked(pos.x + w -4, pos.y - delta * SPEED) && !gps.isBlocked(pos.x + 4, pos.y - delta * SPEED)) {
            pos.y -= delta * SPEED;
            }
            hero = movementUp;
            hero.update(delta);
            ultimaDireccion = 'u';
        } else if (input.isKeyDown(Input.KEY_DOWN)) {
            if (!gps.isBlocked(pos.x + w - 4, pos.y + h + delta * SPEED) && !gps.isBlocked(pos.x + 4, pos.y + h + delta * SPEED)) {
            pos.y += delta * SPEED;
            }hero = movementDown;
            hero.update(delta);
            ultimaDireccion = 'd';
        } else if (input.isKeyDown(Input.KEY_LEFT)) {
            if (!gps.isBlocked(pos.x - delta * SPEED, pos.y + 4) && !gps.isBlocked(pos.x - delta * SPEED, pos.y + h - 4)) {
                pos.x -= delta * SPEED;
            }
            hero = movementLeft;
            hero.update(delta);
            ultimaDireccion = 'l';

        } else if (input.isKeyDown(Input.KEY_RIGHT)) {
            if (!gps.isBlocked(pos.x + w + delta * SPEED, pos.y + h - 4) && !gps.isBlocked(pos.x + w + delta * SPEED, pos.y + 4)) {
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
        }
    }

    public void render() {
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
}