package estados;


import basesdatos.BaseDatosCardinal;
import basesdatos.BaseDatosCatacumba;
import basesdatos.BaseDatosEnemigos;
import basesdatos.BaseDatosMontana;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import otros.EventosNpcs;
import otros.Gestion;
/*Pruebas*/

public class VenganzaBelial extends StateBasedGame {
    public static final int ESTADOMENUINICIO = 0;
    public static final int ESTADOCOMBATE = 1;
    public static final int ESTADOMENU = 2;
    public static final int ESTADOESCENAPROTOTIPO = 3;//EDIT
    public static final int ESTADOMAPAJUEGO = 4;//EDIT
    public static final int ESTADOCOMBATETUT=5;
    public static final int ESCENACARRETA=6;
    public static final int ESCENABOSQUE1=7;
    public static final int ESCENABOSQUE2=8;//EDIT
    public static final int ESCENABOSQUEPREBOSS=9;//EDIT
    public static final int ESCENAPUERTO2=10;//EDIT
    public static final int ESCENABOSQUE3=11;//EDIT
    public static final int ESTADOTIENDA=12;
    public static final int ESCENAPUERTO1=13;//EDIT
    public static final int ESCENABOSQUEPOSTBOSS=14;//EDIT
    public static final int ESCENATROYIA1=15;//EDIT
    public static final int ESCENAFANATICO=16;//EDIT
    public static final int ESCENACATACUMBASPREBOSS=17;//EDIT
    public static final int ESCENATROYIA2=18;//EDIT
    public static final int ESCENACATACUMBAS1=19;
    public static final int ESCENAMONTANABOSS=20;
    public static final int ESCENADEYOLICA=21;
    public static final int ESCENATROYIAPOSTBOSS=22;
    public static final int ESCENAARCHI1=23;
    public static final int ESCENATROYIAPOSTBOSS2=24;
    public static final int ESCENAMONTANAMINIBOSS=25;
    public static final int ESCENAMONTANAMINIBOSS2=26;
    public static final int ESCENAARCHI2=27;
    public static final int ESCENAPUEBLOMONTANA=28;
    public static final int ESCENAMONTANAPOSTBOSS=29;
    public static final int ESCENADEYOLICAPOSTMONTANA=30;
    public static final int ESCENACARDINAL1=31;
    public static final int ESCENACARDINAL2=32;
    public static final int ESCENACARDINALMINIBOSS1=33;
    public static final int ESCENACARDINALMINIBOSS2=34;
    public static final int ESCENACARDINALOPCIONAL=35;
    public static final int ESCENAFINAL=36;
    public static final int ESCENAFINALBUENO=37;
    public static final int ESCENAFINALMALO=38;


    public static final int WIDTH = 1366;
    public static final int HEIGHT = 768;
    public static final boolean FULLSCREEN = false;

    public static Gestion atributoGestion = new Gestion();//
    
    public static EventosNpcs eventos = new EventosNpcs();
    public static Musica controlMusica;
    
    /*Atributos de pruebas END*/
    public VenganzaBelial() {
        super("La Venganza de Belial");
        addState(new EstadoMenuInicio(ESTADOMENUINICIO));
        addState(new EstadoCombate(ESTADOCOMBATE));
        addState(new EstadoMenu(ESTADOMENU));
        //EDIT
        addState(new EscenaInicio(ESTADOESCENAPROTOTIPO));
        addState(new EstadoMapaJuego(ESTADOMAPAJUEGO));
        addState(new EstadoCombateTutorial(ESTADOCOMBATETUT));
        addState(new EscenaCarreta(ESCENACARRETA));
        addState(new EscenaBosque1(ESCENABOSQUE1));
        addState(new EscenaBosque2(ESCENABOSQUE2));
        addState(new EscenaBosquePreBoss(ESCENABOSQUEPREBOSS));
        addState(new EscenaPuerto2(ESCENAPUERTO2));
        addState(new EstadoTienda(ESTADOTIENDA));
        addState(new EscenaPuerto1(ESCENAPUERTO1));

        addState(new EscenaBosquePostBoss(ESCENABOSQUEPOSTBOSS));

        addState(new EscenaTroyia1(ESCENATROYIA1));
        addState(new EscenaTroyia2(ESCENATROYIA2));
        addState(new EscenaFanatico(ESCENAFANATICO));
        addState(new EscenaCatacumbasPreBoss(ESCENACATACUMBASPREBOSS));
        addState(new EscenaCatacumbas1(ESCENACATACUMBAS1));
        addState(new EscenaMontanaBoss(ESCENAMONTANABOSS));
        addState(new EscenaDeyolica(ESCENADEYOLICA));
        addState(new EscenaTroyiaPostBoss(ESCENATROYIAPOSTBOSS));
        addState(new EscenaArchi1(ESCENAARCHI1));
        addState(new EscenaTroyiaPostBoss2(ESCENATROYIAPOSTBOSS2));
        addState(new EscenaMontanaMiniBoss(ESCENAMONTANAMINIBOSS));
        addState(new EscenaMontanaMiniBoss2(ESCENAMONTANAMINIBOSS2));
        addState(new EscenaArchi2(ESCENAARCHI2));
        addState(new EscenaPuebloMontana(ESCENAPUEBLOMONTANA));
        addState(new EscenaMontanaPostBoss(ESCENAMONTANAPOSTBOSS));
        addState(new EscenaDeyolicaPostMontana(ESCENADEYOLICAPOSTMONTANA));
        addState(new EscenaCardinal1(ESCENACARDINAL1));
        addState(new EscenaCardinal2(ESCENACARDINAL2));
        addState(new EscenaCardinalMiniBoss1(ESCENACARDINALMINIBOSS1));
        addState(new EscenaCardinalMiniBoss2(ESCENACARDINALMINIBOSS2));
        addState(new EscenaCardinalOpcional(ESCENACARDINALOPCIONAL));
        addState(new EscenaFinal(ESCENAFINAL));
        addState(new EscenaFinalBueno(ESCENAFINALBUENO));
        addState(new EscenaFinalMalo(ESCENAFINALMALO));
        //
        this.enterState(ESTADOMENUINICIO);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        getState(ESTADOMENUINICIO).init(gc, this);
        getState(ESTADOCOMBATE).init(gc, this);
        getState(ESTADOMENU).init(gc, this);
        //edit
        getState(ESCENABOSQUE1).init(gc, this);
        getState(ESCENABOSQUE2).init(gc, this);
        getState(ESCENABOSQUEPREBOSS).init(gc, this);
        getState(ESCENAPUERTO2).init(gc, this);
        getState(ESCENACARRETA).init(gc, this);
        getState(ESTADOESCENAPROTOTIPO).init(gc, this);
        getState(ESTADOMAPAJUEGO).init(gc, this);
        getState(ESTADOCOMBATETUT).init(gc, this);
        getState(ESTADOTIENDA).init(gc, this);
        getState(ESCENAPUERTO1).init(gc, this);

        getState(ESCENABOSQUEPOSTBOSS).init(gc, this);

        getState(ESCENATROYIA1).init(gc, this);
        getState(ESCENATROYIA2).init(gc, this);
        getState(ESCENAFANATICO).init(gc, this);
        getState(ESCENACATACUMBAS1).init(gc, this);
        getState(ESCENACATACUMBASPREBOSS).init(gc, this);
        getState(ESCENAMONTANABOSS).init(gc, this);
        getState(ESCENADEYOLICA).init(gc, this);
        getState(ESCENATROYIAPOSTBOSS).init(gc, this);
        getState(ESCENAARCHI1).init(gc, this);
        getState(ESCENATROYIAPOSTBOSS2).init(gc, this);
        getState(ESCENAMONTANAMINIBOSS).init(gc, this);
        getState(ESCENAMONTANAMINIBOSS2).init(gc, this);
        getState(ESCENAARCHI2).init(gc, this);
        getState(ESCENAPUEBLOMONTANA).init(gc, this);
        getState(ESCENAMONTANAPOSTBOSS).init(gc, this);
        getState(ESCENADEYOLICAPOSTMONTANA).init(gc, this);
        getState(ESCENACARDINAL1).init(gc, this);
        getState(ESCENACARDINAL2).init(gc, this);
        getState(ESCENACARDINALMINIBOSS1).init(gc, this);
        getState(ESCENACARDINALMINIBOSS2).init(gc, this);
        getState(ESCENACARDINALOPCIONAL).init(gc, this);
        getState(ESCENAFINAL).init(gc, this);
        getState(ESCENAFINALBUENO).init(gc, this);
        getState(ESCENAFINALMALO).init(gc, this);
    }

    public static void main(String[] args) throws SlickException {
        /*Se inicia atributo de control de musica*/
        controlMusica= new Musica();
        controlMusica.loopMusica();
        /*Se rehacen las bases de datos de enemigos por si hubiera problemas*/
        BaseDatosEnemigos base = new BaseDatosEnemigos();
        BaseDatosCatacumba base1 = new BaseDatosCatacumba();
        BaseDatosMontana base2 = new BaseDatosMontana();
        BaseDatosCardinal base3 = new BaseDatosCardinal();
        /*Inicia juego*/
        AppGameContainer app = new AppGameContainer(new VenganzaBelial());
        app.setDisplayMode(WIDTH, HEIGHT, FULLSCREEN);
        app.start();
    }
}