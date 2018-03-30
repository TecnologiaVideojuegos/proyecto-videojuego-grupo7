package estados;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

public class Camara {

    private int x, y;
    private int mapWidth, mapHeight;
    private Rectangle viewPort;

    public Camara(TiledMap map, int mapWidth, int mapHeight) {
        x = 0;
        y = 0;
        viewPort = new Rectangle(0, 0, VenganzaBelial.WIDTH, VenganzaBelial.HEIGHT);
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    public void translate(Graphics g, Heroe heroe) {
        if (heroe.getX() - VenganzaBelial.WIDTH / 2 + 16 < 0) {
            x = 0;
        } else if (heroe.getX() + VenganzaBelial.WIDTH / 2 + 16 > mapWidth) {
            x = -mapWidth + VenganzaBelial.WIDTH;
        } else {
            x = (int) -heroe.getX() + VenganzaBelial.WIDTH / 2 - 16;
        }

        if (heroe.getY() - VenganzaBelial.HEIGHT / 2 + 16 < 0) {
            y = 0;
        } else if (heroe.getY() + VenganzaBelial.HEIGHT / 2 + 16 > mapHeight) {
            y = -mapHeight + VenganzaBelial.HEIGHT;
        } else {
            y = (int) -heroe.getY() + VenganzaBelial.HEIGHT / 2 - 16;
        }
        g.translate(x, y);
        viewPort.setX(-x);
        viewPort.setY(-y);
    }
}