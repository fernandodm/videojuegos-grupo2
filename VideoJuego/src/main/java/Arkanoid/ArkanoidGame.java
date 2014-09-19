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

		for (int y = 0; y < width; y++) {
			for (int x = 0; x < length; x++) {
				Color color = colors[(int) (Math.random() * colors.length)];
				Ladrillo ladrillo = new Ladrillo(color, 48, 18, x, y, marcador);
				scene.addComponent(ladrillo);
				ladrillos.add(ladrillo);
			}
		}
		
		
//		//MODO DE EJEMPLO HAY QUE ARREGLARLO FEOOOO
//		LadrilloPaletaChica ladrilloPalChica = new LadrilloPaletaChica(Color.BLACK, 48, 18, 2, 4, marcador, raqueta, pelota);
//		LadrilloPelotaRapida ladrillPelRapida = new LadrilloPelotaRapida(Color.RED, 48, 18, 5, 3, marcador, raqueta, pelota);
//		LadrilloPaletaGrande ladrilloPalGrande = new LadrilloPaletaGrande(Color.BLUE, 48, 18, 8, 1, marcador, raqueta, pelota);
//		LadrilloPelotaLenta ladrilloPelLenta = new LadrilloPelotaLenta(Color.DARK_GRAY, 48, 18, 9, 9, marcador, raqueta, pelota);
//		LadrilloPaletaChica ladrilloPalChica1 = new LadrilloPaletaChica(Color.BLACK, 48, 18, 1, 1, marcador, raqueta, pelota);
//		LadrilloPelotaRapida ladrillPelRapida2 = new LadrilloPelotaRapida(Color.RED, 48, 18, 7, 3, marcador, raqueta, pelota);
//		LadrilloPaletaGrande ladrilloPalGrande3 = new LadrilloPaletaGrande(Color.BLUE, 48, 18, 3, 2, marcador, raqueta, pelota);
//		LadrilloPelotaLenta ladrilloPelLenta4 = new LadrilloPelotaLenta(Color.DARK_GRAY, 48, 18, 2, 2, marcador, raqueta, pelota);
//		
//		ArrayList<LadrilloEspecial> ladrillosEpeciales = new ArrayList<LadrilloEspecial>();
//		ladrillosEpeciales.add(ladrilloPalChica);
//		ladrillosEpeciales.add(ladrillPelRapida);
//		ladrillosEpeciales.add(ladrilloPalGrande);
//		ladrillosEpeciales.add(ladrilloPelLenta);
//		ladrillosEpeciales.add(ladrilloPalChica1);
//		ladrillosEpeciales.add(ladrillPelRapida2);
//		ladrillosEpeciales.add(ladrilloPalGrande3);
//		ladrillosEpeciales.add(ladrilloPelLenta4);
		
		
		
		return ladrillos;

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
