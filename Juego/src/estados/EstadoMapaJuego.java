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
    static boolean fullscreen = false;
    static boolean showFPS = true;
    private boolean[][] blocked;
    private boolean[][] enemigos;
    private boolean[][] eventos;
    static String title = "Bosque";
    static int fpslimit = 60;
    private TiledMap map;
    Heroe player;
    Camara camera;
    int mapHeight, mapWidth;
    int tileHeight, tileWidth;
    int idEstado;
    
    private int mapaCargado;
    private final int MAPATUTORIAL = 0;
    private final int MAPABOSQUE = 1;
    private final int MAPAPUERTO = 2;
    private final int MAPACIUDADCATACUMBAS = 3;
    private final int MAPADUNGEONCATACUMBAS = 4;
    private final int MAPACIUDADMONTANA = 5;
    private final int MAPADUNGEONMONTANA = 6;
    public EstadoMapaJuego(int id) {
        idEstado = id;
    }

    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //EDIT: Hacer un Switch de carga de mapa en funcion del alguun indicador
        mapaCargado=0;
        //map = new TiledMap("tiledmaps/mapaBosque.tmx");
        map = new TiledMap("tiledmaps/MapaTutorial/MapaTutorial.tmx");
        //map = new TiledMap("tiledmaps/MapaTutorial/MapaTutorial.tmx");

            
        //VenganzaBelial.atributoGestion.enem.
        //map = new TiledMap("tiledmaps/prueba.tmx");
        //
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
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if(VenganzaBelial.atributoGestion.isRecargaEnemigos())
        {
            switch(VenganzaBelial.atributoGestion.getMapaActual()){
                case 1:
                    VenganzaBelial.atributoGestion.setEnem(VenganzaBelial.atributoGestion.cargarGrupoEnemigos("BaseDatos/enemigosBosque.dat"));
                    for (int i = 0; i < VenganzaBelial.atributoGestion.getEnem().size(); i++) {
                        for (int j = 0; j < VenganzaBelial.atributoGestion.getEnem().get(i).size(); j++) {
                            if(VenganzaBelial.atributoGestion.getEnem().get(i).get(j).getNombre().equals("Rata"))
                                VenganzaBelial.atributoGestion.getEnem().get(i).get(j).setImagen("Imagenes/Monstruos/Bosque/Rata.png");
                            else if(VenganzaBelial.atributoGestion.getEnem().get(i).get(j).getNombre().equals("Goblin"))
                                VenganzaBelial.atributoGestion.getEnem().get(i).get(j).setImagen("Imagenes/Monstruos/Bosque/Goblin.png");
                            else if(VenganzaBelial.atributoGestion.getEnem().get(i).get(j).getNombre().equals("Araña"))
                                VenganzaBelial.atributoGestion.getEnem().get(i).get(j).setImagen("Imagenes/Monstruos/Bosque/Spider.png");
                        }
                    }
                    break;
                case 4://Dungeon Catacumbas: ID=4
                    VenganzaBelial.atributoGestion.setEnem(VenganzaBelial.atributoGestion.cargarGrupoEnemigos("BaseDatos/enemigosCatacumba.dat"));
                   
                    for (int i = 0; i < VenganzaBelial.atributoGestion.getEnem().size(); i++) {
                        for (int j = 0; j < VenganzaBelial.atributoGestion.getEnem().get(i).size(); j++) {
                            if(VenganzaBelial.atributoGestion.getEnem().get(i).get(j).getNombre().equals("Esqueleto"))
                                VenganzaBelial.atributoGestion.getEnem().get(i).get(j).setImagen("Imagenes/Monstruos/Catacumbas/Skeleton.png");
                            else if(VenganzaBelial.atributoGestion.getEnem().get(i).get(j).getNombre().equals("Fanatico"))
                                VenganzaBelial.atributoGestion.getEnem().get(i).get(j).setImagen("Imagenes/Monstruos/Catacumbas/Fanatic.png");
                            else if(VenganzaBelial.atributoGestion.getEnem().get(i).get(j).getNombre().equals("Zombie"))
                                VenganzaBelial.atributoGestion.getEnem().get(i).get(j).setImagen("Imagenes/Monstruos/Catacumbas/Zombie.png");
                            else if(VenganzaBelial.atributoGestion.getEnem().get(i).get(j).getNombre().equals("Mimico"))
                                VenganzaBelial.atributoGestion.getEnem().get(i).get(j).setImagen("Imagenes/Monstruos/Catacumbas/Mimic.png");
                        }
                    }   
                    break;
                case 6://Dungeon Montañas: ID=6
                    VenganzaBelial.atributoGestion.setEnem(VenganzaBelial.atributoGestion.cargarGrupoEnemigos("BaseDatos/enemigosMontana.dat"));
                    for (int i = 0; i < VenganzaBelial.atributoGestion.getEnem().size(); i++) {
                        for (int j = 0; j < VenganzaBelial.atributoGestion.getEnem().get(i).size(); j++) {
                            if(VenganzaBelial.atributoGestion.getEnem().get(i).get(j).getNombre().equals("Slime"))
                                VenganzaBelial.atributoGestion.getEnem().get(i).get(j).setImagen("Imagenes/Monstruos/Montaña/Slime1.png");
                            else if(VenganzaBelial.atributoGestion.getEnem().get(i).get(j).getNombre().equals("MiniGrifo"))
                                VenganzaBelial.atributoGestion.getEnem().get(i).get(j).setImagen("Imagenes/Monstruos/Montaña/Griffin.png");
                            else if(VenganzaBelial.atributoGestion.getEnem().get(i).get(j).getNombre().equals("Minotauro"))
                                VenganzaBelial.atributoGestion.getEnem().get(i).get(j).setImagen("Imagenes/Monstruos/Montaña/Minotaur.png");
                            else if(VenganzaBelial.atributoGestion.getEnem().get(i).get(j).getNombre().equals("Murciegalo"))
                                VenganzaBelial.atributoGestion.getEnem().get(i).get(j).setImagen("Imagenes/Monstruos/Montaña/Imp.png");
                        }
                    } 
            }
            VenganzaBelial.atributoGestion.setRecargaEnemigos(false);
        }
        if((VenganzaBelial.atributoGestion.getMapaActual()>this.mapaCargado && VenganzaBelial.atributoGestion.getMapaActual()<10)){
//            this.player.setpos(VenganzaBelial.atributoGestion.getPosicionHeroe());
            this.mapaCargado=VenganzaBelial.atributoGestion.getMapaActual();
            
            switch(VenganzaBelial.atributoGestion.getMapaActual()){
                case 0://Mapa Tutorial: ID=0
                    break;
                case 1://Dungeon Bosque: ID=1
                    map = new TiledMap("tiledmaps/mapaBosque.tmx");
                    VenganzaBelial.atributoGestion.setEnem(VenganzaBelial.atributoGestion.cargarGrupoEnemigos("BaseDatos/enemigosBosque.dat"));
                    for (int i = 0; i < VenganzaBelial.atributoGestion.getEnem().size(); i++) {
                        for (int j = 0; j < VenganzaBelial.atributoGestion.getEnem().get(i).size(); j++) {
                            if(VenganzaBelial.atributoGestion.getEnem().get(i).get(j).getNombre().equals("Rata"))
                                VenganzaBelial.atributoGestion.getEnem().get(i).get(j).setImagen("Imagenes/Monstruos/Bosque/Rata.png");
                            else if(VenganzaBelial.atributoGestion.getEnem().get(i).get(j).getNombre().equals("Goblin"))
                                VenganzaBelial.atributoGestion.getEnem().get(i).get(j).setImagen("Imagenes/Monstruos/Bosque/Goblin.png");
                            else if(VenganzaBelial.atributoGestion.getEnem().get(i).get(j).getNombre().equals("Araña"))
                                VenganzaBelial.atributoGestion.getEnem().get(i).get(j).setImagen("Imagenes/Monstruos/Bosque/Spider.png");
                        }
                    }
                    this.player.setpos(new Vector2f(2*this.tileWidth,2*this.tileHeight));
                    /**/
                    break;
                case 2://Ciudad Puerto: ID=2
                    map = new TiledMap("tiledmaps/Puerto.tmx");
                    this.player.setpos(new Vector2f(2*this.tileWidth,12*this.tileHeight));
                    break;
                case 3://Ciudad Catacumbas: ID=3
                    map = new TiledMap("tiledmaps/MapaCiudadCatacumbas/CiudadCatacumbas.tmx");
                    this.player.setpos(new Vector2f(24*this.tileWidth,12*this.tileHeight));
                    break;
                case 4://Dungeon Catacumbas: ID=4
                    VenganzaBelial.atributoGestion.setEnem(VenganzaBelial.atributoGestion.cargarGrupoEnemigos("BaseDatos/enemigosCatacumba.dat"));
                    map = new TiledMap("tiledmaps/DungeonCatacumbas/DungeonCatacumbas.tmx");
                    for (int i = 0; i < VenganzaBelial.atributoGestion.getEnem().size(); i++) {
                        for (int j = 0; j < VenganzaBelial.atributoGestion.getEnem().get(i).size(); j++) {
                            if(VenganzaBelial.atributoGestion.getEnem().get(i).get(j).getNombre().equals("Esqueleto"))
                                VenganzaBelial.atributoGestion.getEnem().get(i).get(j).setImagen("Imagenes/Monstruos/Catacumbas/Skeleton.png");
                            else if(VenganzaBelial.atributoGestion.getEnem().get(i).get(j).getNombre().equals("Fanatico"))
                                VenganzaBelial.atributoGestion.getEnem().get(i).get(j).setImagen("Imagenes/Monstruos/Catacumbas/Fanatic.png");
                            else if(VenganzaBelial.atributoGestion.getEnem().get(i).get(j).getNombre().equals("Zombie"))
                                VenganzaBelial.atributoGestion.getEnem().get(i).get(j).setImagen("Imagenes/Monstruos/Catacumbas/Zombie.png");
                            else if(VenganzaBelial.atributoGestion.getEnem().get(i).get(j).getNombre().equals("Mimico"))
                                VenganzaBelial.atributoGestion.getEnem().get(i).get(j).setImagen("Imagenes/Monstruos/Catacumbas/Mimic.png");
                        }
                    }   

                    break;
                case 5: //Ciudad Montañas: ID=5
                    map = new TiledMap("tiledmaps/PuebloMontana.tmx");
                    this.player.setpos(new Vector2f(2*this.tileWidth,11*this.tileHeight));
                    break;
                case 6://Dungeon Montañas: ID=6
                    VenganzaBelial.atributoGestion.setEnem(VenganzaBelial.atributoGestion.cargarGrupoEnemigos("BaseDatos/enemigosMontana.dat"));
                    map = new TiledMap("tiledmaps/Montana.tmx");
                    this.player.setpos(new Vector2f(2*this.tileWidth,3*this.tileHeight));
                    for (int i = 0; i < VenganzaBelial.atributoGestion.getEnem().size(); i++) {
                        for (int j = 0; j < VenganzaBelial.atributoGestion.getEnem().get(i).size(); j++) {
                            if(VenganzaBelial.atributoGestion.getEnem().get(i).get(j).getNombre().equals("Slime"))
                                VenganzaBelial.atributoGestion.getEnem().get(i).get(j).setImagen("Imagenes/Monstruos/Montaña/Slime1.png");
                            else if(VenganzaBelial.atributoGestion.getEnem().get(i).get(j).getNombre().equals("MiniGrifo"))
                                VenganzaBelial.atributoGestion.getEnem().get(i).get(j).setImagen("Imagenes/Monstruos/Montaña/Griffin.png");
                            else if(VenganzaBelial.atributoGestion.getEnem().get(i).get(j).getNombre().equals("Minotauro"))
                                VenganzaBelial.atributoGestion.getEnem().get(i).get(j).setImagen("Imagenes/Monstruos/Montaña/Minotaur.png");
                            else if(VenganzaBelial.atributoGestion.getEnem().get(i).get(j).getNombre().equals("Murciegalo"))
                                VenganzaBelial.atributoGestion.getEnem().get(i).get(j).setImagen("Imagenes/Monstruos/Montaña/Imp.png");
                        }
                    }   
                    break;
                case 7: //
                    VenganzaBelial.atributoGestion.setEnem(VenganzaBelial.atributoGestion.cargarGrupoEnemigos("BaseDatos/enemigosMontana.dat"));
                    map = new TiledMap("tiledmaps/Cardinal.tmx");
                    this.player.setpos(new Vector2f(2*this.tileWidth,3*this.tileHeight));
                    break;
            }
            mapWidth = map.getWidth() * map.getTileWidth();
            mapHeight = map.getHeight() * map.getTileHeight();
            tileHeight = map.getTileHeight();
            tileWidth = map.getTileWidth();
            camera = new Camara(map, mapWidth, mapHeight);
            blocked = new boolean[map.getWidth()][map.getHeight()];
            enemigos= new boolean[map.getWidth()][map.getHeight()];
            eventos= new boolean[map.getWidth()][map.getHeight()];
            initializeBlocked();
            initializeEnemigos();
            initializeEventos();
        }
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
    }

    public boolean isEnemigos(float x, float y) {
        int xEnemigos = (int) x / map.getTileWidth();
        int yEnemigos = (int) y / map.getTileHeight();
        return enemigos[xEnemigos][yEnemigos];
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
    }
    public boolean isEventos(float x, float y) {
        int xEventos = (int) x / map.getTileWidth();
        int yEventos = (int) y / map.getTileHeight();
        return eventos[xEventos][yEventos];
    }
    public int[] devuelvePosicion(float x, float y) {
        int pos[] = {0,0};
        int xPos = (int) x / map.getTileWidth();
        int yPos = (int) y / map.getTileHeight();
        pos[0] = xPos;
        pos[1] = yPos;
        return pos;
    }        
}