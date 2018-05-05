package estados;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

public class Musica {

    private Music music;

    public Musica() throws SlickException {
        //Musica de inicio
        music= new Music("Musica/BSO/Archi.wav");
    }
    
    public void pararMusica()
    {
        music.stop();
    }
    public void loopMusica()
    {
        music.loop();
    }
    public boolean musicaSonando()
    {
        return music.playing();
    }
    
    public void cambiaMusica(int nuevaMusica) throws SlickException
    {
        this.pararMusica();
        switch(nuevaMusica){
            case 0:
                music= new Music("Musica/BSO/Archi.wav");
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
        this.loopMusica();
    }/*public void cambiaMusica(int nuevaMusica)*/
}