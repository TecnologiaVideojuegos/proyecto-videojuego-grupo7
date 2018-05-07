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
     public void cambiarMusica(String ruta) throws SlickException
     {
         //this.music.stop();
         music= new Music(ruta);
         this.music.loop();
     }
    public void cambiaMusicaMapa(int mapa) throws SlickException
    {
        this.pararMusica();
        switch(mapa){
            case 0:
                music= new Music("Musica/BSO/Music_City.wav");
                break;
            case 1:
                music= new Music("Musica/BSO/Music_Forest.wav");
                break;
            case 2:
                break;
            case 3:
                music= new Music("Musica/BSO/Catacumbas.wav");
                break;
            case 4:
                music= new Music("Musica/BSO/Catacumbas.wav");
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                music= new Music("Musica/BSO/Cardinal.wav");
                break;
        }
        this.loopMusica();
    }/*public void cambiaMusica(int nuevaMusica)*/
}