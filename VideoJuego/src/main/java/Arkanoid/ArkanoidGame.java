package Arkanoid;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameScene;

import componentes.Marcador;
import componentes.Pelota;
import componentes.Raqueta;
import componentes.ladrillos.Ladrillo;
import componentes.ladrillos.LadrilloDisparo;
import componentes.ladrillos.LadrilloPaletaChica;
import componentes.ladrillos.LadrilloPaletaGrande;
import componentes.ladrillos.LadrilloPaletaPegajosa;
import componentes.ladrillos.LadrilloPelotaLenta;
import componentes.ladrillos.LadrilloPelotaRapida;
import escenas.ArkanoidScene;
import escenas.GanasteOPerdisteScene;

public class ArkanoidGame extends Game {
	
	@Override
	protected void initializeResources() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void setUpScenes() {
		
		GameScene arkanoidScene = this.buildArkanoidScene();
		this.setCurrentScene(arkanoidScene);
		
	}
	
	public GameScene buildArkanoidScene() {
		
		Raqueta raqueta = new Raqueta(200, 450, 50, 4, Color.BLACK, 500, 0, this.getDisplayWidth());
		Marcador marcador = new Marcador(50, this.getDisplayHeight() - 35,Color.GRAY);
		Pelota pelota = new Pelota(20, 200, 430, new Vector(5, 5), 400, raqueta, marcador);
		ArkanoidScene arkanoidScene = new ArkanoidScene();
		arkanoidScene.setPelota(pelota);
		arkanoidScene.setRaqueta(raqueta);
		arkanoidScene.setMarcador(marcador);
		ArrayList<Ladrillo> ladrillos = renderElements(arkanoidScene, pelota, marcador, raqueta);
		arkanoidScene.setLadrillos(ladrillos);
		return arkanoidScene;
	}

	public ArrayList<Ladrillo> renderElements(final GameScene scene, final Pelota pelota, Marcador marcador, Raqueta raqueta) {

		ArrayList<Ladrillo> ladrillos = new ArrayList<Ladrillo>();
		int length = 10;
		int width = 10;
		Color[] colors = { Color.red, Color.black, Color.blue, Color.DARK_GRAY,
				Color.orange };
		
        int cantLadrillosEsp = 20;

        for (int y = 0; y < width; y++) {
            for (int x = 0; x < length; x++) {
                Color color = colors[(int) (Math.random() * colors.length)];
                Ladrillo ladrillo = new Ladrillo(color, 48, 18, x, y, marcador);
               
                	if( (cantLadrillosEsp > 0 && ((int) (Math.random() * 100)) > 60)){
                		ladrillo = this.crearLadrilloEspecial(48, 18, x, y, marcador, raqueta, pelota);
                		cantLadrillosEsp--;   
                	}
               
                scene.addComponent(ladrillo);
                ladrillos.add(ladrillo);
            }
        }  
        return ladrillos;
    }
	
	
	   public Ladrillo crearLadrilloEspecial(int ancho, int alto, double x,
	            double y, Marcador marcador, Raqueta raqueta, Pelota pelota){
	       
	        int[] cant = {1,2,3,4,5,6};
	        int nRandom = cant[ ((int) (Math.random() * cant.length)) ];
	       
	        if(nRandom == 1){
	            return new LadrilloPaletaChica(Color.PINK, 48, 18, x, y, marcador, raqueta, pelota);   
	        }
	        if(nRandom == 2){
	            return new LadrilloPelotaRapida(Color.PINK, 48, 18, x, y, marcador, raqueta, pelota);
	        }
	        if(nRandom == 3){
	            return new LadrilloPaletaGrande(Color.PINK, 48, 18, x, y, marcador, raqueta, pelota);
	        }
	        if(nRandom == 4){
	            return new LadrilloPelotaLenta(Color.PINK, 48, 18, x, y, marcador, raqueta, pelota);
	        }
	        if(nRandom == 5){
	            return new LadrilloPelotaRapida(Color.PINK, 48, 18, x, y, marcador, raqueta, pelota);
	        }
	        if(nRandom == 6){
	            return new LadrilloDisparo(Color.PINK, 48, 18, x, y, marcador, raqueta, pelota);
	        }
	        return new LadrilloPaletaPegajosa(Color.PINK, 48, 18, x, y, marcador, raqueta, pelota);
	    }

	@Override
	public Dimension getDisplaySize() {
		return new Dimension(500, 500);
	}

	@Override
	public String getTitle() {
		return "Arkanoid";
	}
	
	public GanasteOPerdisteScene buildScene(String msj){
		return new GanasteOPerdisteScene(30,100,msj);
	}
	

	public static void main(String[] args) {

		new DesktopGameLauncher(new ArkanoidGame()).launch();

	}
	
	

}
