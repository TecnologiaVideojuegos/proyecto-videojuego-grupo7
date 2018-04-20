package estados;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class EstadoMapaJuego extends BasicGameState {
    private final int LAYEREVENTOS=2;
    static boolean fullscreen = false;
    static boolean showFPS = true;
    private boolean[][] blocked;
    private boolean[][] enemigos;
    private boolean[][] eventos;
    static String title = "Bosque";
    static int fpslimit = 60;
    TiledMap map;
    Heroe player;
    Camara camera;
    int mapHeight, mapWidth;
    int tileHeight, tileWidth;
    int idEstado;

    public EstadoMapaJuego(int id) {
        idEstado = id;
    }

    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //map = new TiledMap("tiledmaps/mapaBosque.tmx");
        map = new TiledMap("tiledmaps/prueba.tmx");
        mapWidth = map.getWidth() * map.getTileWidth();
        mapHeight = map.getHeight() * map.getTileHeight();
        tileHeight = map.getTileHeight();
        tileWidth = map.getTileWidth();
        player = new Heroe(tileWidth, tileHeight);
        camera = new Camara(map, mapWidth, mapHeight);
        blocked = new boolean[map.getWidth()][map.getHeight()];
        enemigos= new boolean[map.getWidth()][map.getHeight()];
        eventos= new boolean[map.getWidth()][map.getHeight()];
        initializeBlocked();
        initializeEnemigos();
        initializeEventos();
        int a=0;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        
        //EDIT: idea para eventos
        /*He pensado que habra eventos donde queramos que se renderice el mapa y se den opciones al jugador
        *pero el jugador no tendra que moverse, por ello tal vez debamos añadir un flag que controle si estamos
        *moviendonos por el mapa o moviendonos en las opciones de un evento.
        EJ:
        if(evento==false)
            player.update(gc, sbg, delta, this);
        else
            eventos.update();//COntrol del evento dentrol Estado Eventos
        */
        player.update(gc, sbg, delta, this);
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            sbg.enterState(VenganzaBelial.ESTADOMENU);
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        camera.translate(g, player);
        map.render(0, 0);
        player.render();
    }

    public boolean isBlocked(float x, float y) {
        int xBlock = (int) x / map.getTileWidth();
        int yBlock = (int) y / map.getTileHeight();
        return blocked[xBlock][yBlock];
    }
 
    public Vector2f getHeroPosition() {
        return player.getpos();
    }
    
    public void setHeroPosition(Vector2f pos) {
        player.setpos(pos);
    }

    private void initializeBlocked() {    
        for (int l = 0; l < map.getLayerCount(); l++) {
            String layerValue = map.getLayerProperty(l, "blocked", "false");
            if (layerValue.equals("true")) {
                for (int c = 0; c < map.getWidth(); c++) {
                    for (int r = 0; r < map.getHeight(); r++) {
                        if (map.getTileId(c, r, l) != 0) {
                            blocked[c][r] = true;
                        }
                    }
                }
            }
        }
    }
    
    //
    private void initializeEnemigos() {
        for (int l = 0; l < map.getLayerCount(); l++) {
            String layerValue = map.getLayerProperty(l, "enemigos", "false");
            if (layerValue.equals("true")) {
                for (int c = 0; c < map.getWidth(); c++) {
                    for (int r = 0; r < map.getHeight(); r++) {
                        if (map.getTileId(c, r, l) != 0) {
                            enemigos[c][r] = true;
                        }
                    }
                }
            }
        }
    }/**/

    public boolean isEnemigos(float x, float y) {
        int xBlock = (int) x / map.getTileWidth();
        int yBlock = (int) y / map.getTileHeight();
        return enemigos[xBlock][yBlock];
    }/**/
    //
    private void initializeEventos() {
        for (int l = 0; l < map.getLayerCount(); l++) {
            String layerValue = map.getLayerProperty(l, "evento", "false");
            if (layerValue.equals("true")) {
                for (int c = 0; c < map.getWidth(); c++) {
                    for (int r = 0; r < map.getHeight(); r++) {
                        if (map.getTileId(c, r, l) != 0) {
                            eventos[c][r] = true;
                        }
                    }
                }
            }
        }
    }/**/
        public boolean isEventos(float x, float y) {
        int xBlock = (int) x / map.getTileWidth();
        int yBlock = (int) y / map.getTileHeight();
        
        return eventos[xBlock][yBlock];
    }/**/
        public int devuelveIDEvento(float x, float y) {
        int id; 
        int xBlock = (int) x / map.getTileWidth();
        int yBlock = (int) y / map.getTileHeight();
        id= map.getTileId(xBlock, yBlock, LAYEREVENTOS);
        return id;
    }/**/
        
        
}