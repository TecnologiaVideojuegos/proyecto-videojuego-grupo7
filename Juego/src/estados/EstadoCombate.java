package estados;

import static estados.VenganzaBelial.ESTADOMENUINICIO;
import java.awt.Font;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.*;
import personajes.*;
import otros.Combate;

public class EstadoCombate extends BasicGameState{
    private static final int NUMOPCIONES = 4;
    private static final int ATACAR = 0;
    private static final int HABILIDAD = 1;
    private static final int CONSUMIBLE = 2;
    private static final int HUIR = 3;
    private String[] opciones = new String[NUMOPCIONES];
    private Font letraMenu;
    private TrueTypeFont opcionesJugadorTTF;
    private int eleccionJugador, idEstado;
    private Color notChosen = new Color(153, 204, 255);
    private boolean NuevoCombate = true;
    /*Control de Combate*/
    private ArrayList<Personaje> ordenPersonajes = new ArrayList<Personaje>();
    /*Avatar Control*/
    private Image Fondo;
    private Image Avatar1;
    private Image Avatar2;
    private Image Avatar3;
    private ArrayList<Image> avatarEnemigo;
    private TrueTypeFont HP1, HP2, HP3;
    private TrueTypeFont MP1, MP2, MP3;
    private Font TipoLetra  =new Font("Verdana", Font.PLAIN, 20);    
    private Color rojo = new Color (256,0,0);
    private Color verde = new Color (0,256,0);
    private Color azul = new Color (0,0,256);
 
    
    
    public EstadoCombate(int id) {
        idEstado = id;
    }
    @Override
    public int getID() {
        return idEstado;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
    {
        letraMenu = new Font("Verdana", Font.ROMAN_BASELINE, 25);
        opcionesJugadorTTF = new TrueTypeFont(letraMenu, true);
        opciones[0] = "Atacar";
        opciones[1] = "Habilidad";
        opciones[2] = "Consumible";
        opciones[3]= "Huir";
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
    {
        renderAvatars(g);
        renderOpcionesJugador();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException 
    {
        if(NuevoCombate==true)//Ejecutar Con Cada nuevo combate
        {
            /*Init de turnos*/
            /*Añadir Imagenes en funcion del enemigo*/
            avatarEnemigo = new ArrayList<Image>();
            avatarEnemigo.add(new Image("Imagenes/Monstruos/Rata.png"));
            avatarEnemigo.add(new Image("Imagenes/Monstruos/Rata.png"));
            avatarEnemigo.add(new Image("Imagenes/Monstruos/Rata.png"));
            NuevoCombate = false;
        }
        else
        {
            /*Print Gráfico del combate*/
            /*Selector de Acciones de Usuario*/
            Input input = gc.getInput();
            if (input.isKeyPressed(Input.KEY_DOWN)) 
            {
                if (eleccionJugador == (NUMOPCIONES - 1)) 
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
                    eleccionJugador = NUMOPCIONES - 1;
                } else {
                    eleccionJugador--;
                }
            }           
            if (input.isKeyPressed(Input.KEY_ENTER))
            {
                switch (eleccionJugador) 
                {
                    case ATACAR:
                        //sbg.enterState(IceAdventure.GAMEPLAYSTATE);
                        break;
                    case HABILIDAD:
                        //heropos = fileio.loadSave();
                        //((GamePlayState)sbg.getState(IceAdventure.GAMEPLAYSTATE)).setHeroPosition(heropos);
                        //sbg.enterState(IceAdventure.GAMEPLAYSTATE);
                        break;
                    case CONSUMIBLE:
                        sbg.enterState(VenganzaBelial.ESTADOMENUINICIO);
                        break;
                }
            }
        }
    }
    
    private void renderOpcionesJugador()
    {
        for (int i = 0; i < NUMOPCIONES; i++) {
            if (eleccionJugador == i) {
                opcionesJugadorTTF.drawString(10, i * 20 + 400, opciones[i]);
            } else {
                opcionesJugadorTTF.drawString(10, i * 20 + 400, opciones[i], notChosen);
            }
        }
    }
    
    private void renderAvatars(Graphics g) throws SlickException
    {
         /*Update Grafico*/
        /*Imagenes del fondo*/
            Fondo = new Image("Imagenes/BackBattle/Bosque.jpg");
            /*Imagenes de los personajes*/
            Avatar1 =  new Image("Imagenes/Personajes/Horacia.jpg");
            Avatar2 =  new Image("Imagenes/Personajes/Mordeim.jpg");
            Avatar3 =  new Image("Imagenes/Personajes/Kibito.jpg");
            Fondo.draw(0, 0, 1366, 768);
            Rectangle rect1 = new Rectangle(0,10, 230,100);
            Rectangle rect2 = new Rectangle(0,110, 230, 100);
            Rectangle rect3 = new Rectangle(0,210, 230, 100);
            g.draw(rect1);
            g.draw(rect2);
            g.draw(rect3);
            g.fill(rect1);
            g.fill(rect2);
            g.fill(rect3);
            Avatar1.draw(0, 10, 100, 100);
            Avatar2.draw(0, 110, 100, 100);
            Avatar3.draw(0, 210, 100, 100);
            /*Horacia status update*/
            HP1 = new TrueTypeFont(TipoLetra, true);
            HP1.drawString(110, 30, "HP"+VenganzaBelial.horacia.getHpActual()+ "/"+VenganzaBelial.horacia.getHp(),rojo);
            MP1 = new TrueTypeFont(TipoLetra, true);
            MP1.drawString(110, 60, "MP "+VenganzaBelial.horacia.getMpActual()+ "/"+VenganzaBelial.horacia.getMp(),azul);
            /*Mordeim Status Update*/
            HP2 = new TrueTypeFont(TipoLetra, true);
            HP2.drawString(110, 130, "HP "+VenganzaBelial.mordeim.getHpActual()+ "/"+VenganzaBelial.mordeim.getHp(),rojo);
            MP2 = new TrueTypeFont(TipoLetra, true);
            MP2.drawString(110, 160, "MP "+VenganzaBelial.mordeim.getMpActual()+ "/"+VenganzaBelial.mordeim.getMp(),azul);
            /*Kibito Status update*/
            HP3 = new TrueTypeFont(TipoLetra, true);
            HP3.drawString(110, 230, "HP "+VenganzaBelial.kibito.getHpActual()+ "/"+VenganzaBelial.kibito.getHp(),rojo);
            MP3 = new TrueTypeFont(TipoLetra, true);
            MP3.drawString(110, 260, "MP "+VenganzaBelial.kibito.getMpActual()+ "/"+VenganzaBelial.kibito.getMp(),azul);
            /*Render Enemigos*/
            
            avatarEnemigo.get(0).draw(550, 500, 100, 100);
            avatarEnemigo.get(1).draw(650, 500, 100, 100);
            avatarEnemigo.get(2).draw(750, 500, 100, 100);
    }
            
}
