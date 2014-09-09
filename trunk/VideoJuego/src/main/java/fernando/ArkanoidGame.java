package fernando;

import java.awt.Color;
import java.awt.Dimension;


//import yoni.Raqueta;
import yoni.RaquetaStrategy;
import yoni.Tuning;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.colissions.CollisionDetector;
import com.uqbar.vainilla.events.constants.Key;

public class ArkanoidGame extends Game {

	@Override
	protected void initializeResources() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setUpScenes() {
		Paleta raqueta = new Paleta(200, 450, 50, 5, Color.BLACK, 500, 0, this.getDisplayWidth());
		
		ArkanoidScene arkanoidScene = new ArkanoidScene();
		Pelota pelota = new Pelota(20, 300, 2, new Vector(5, -2), 500, raqueta);
		arkanoidScene.setPelota(pelota);
		
		//RAqueta
//		Dimension dimension = new Dimension(Tuning.getInteger("dimension.width", 800),
//				Tuning.getInteger("dimension.height", 600));
//		Color colorPlayer = Tuning.getColor("player.color", Color.BLUE);
//		double velocidadRaquetaPlayer = Tuning.getDouble("player.speed", 1110.8);
//		int raquetaAncho = (int) dimension.getWidth() / 8;
//		int raquetaAlto = 3;
//		double raquetaX = dimension.getWidth() / 2 - raquetaAncho / 2;
//		Raqueta raqueta = new Raqueta(raquetaX, 9 * dimension
//				.getHeight() / 10, raquetaAncho, raquetaAlto, colorPlayer,
//				velocidadRaquetaPlayer, 0, dimension.getWidth(),
//				Tuning.newInstance("player.strategy", RaquetaStrategy.class));

//		pelotitaScene.addComponent(raqueta);
		
		arkanoidScene.setRaqueta(raqueta);
		Marcador marcador = new Marcador(50, this.getDisplayHeight() - 35,Color.GREEN);
		arkanoidScene.addComponent(marcador);
		renderElements(arkanoidScene, pelota, marcador);
		this.setCurrentScene(arkanoidScene);
		
	}

	public void renderElements(final GameScene scene, final Pelota pelota, Marcador marcador) {

		int length = 10;
		int width = 10;

		Color[] colors = { Color.red, Color.black, Color.blue, Color.DARK_GRAY,
				Color.orange, Color.white };

		for (int y = 0; y < width; y++) {
			for (int x = 0; x < length; x++) {
				Color color = colors[(int) (Math.random() * colors.length)];
				scene.addComponent(new Ladrillo(color, 48, 18, x, y, pelota, marcador));
//				scene.addComponent(new GameComponent<GameScene>(new Rectangle(
//						color, 48, 18), x * 50, y * 20) {
//
//					private  double anguloMayor = Math.PI / 3;
//					private  double anguloMenor = -Math.PI / 3;
//
//					@Override
//					public void update(DeltaState deltaState) {
//						Vector nuevaPosicion = pelota
//								.getDireccion()
//								.producto(
//										pelota.getVelocidad()
//												* deltaState.getDelta())
//								.suma(new Vector(pelota.getX(), pelota.getY()));
//						if (colisiona(this, pelota, nuevaPosicion)) {
//							scene.removeComponent(this);
//							apply(pelota,nuevaPosicion,scene);
//						}
//					}
//
//					public void apply(Pelota pelota, Vector nuevaPosicion,
//							GameScene scene) {
//						double puntoDeColision = getPuntoColision(this, pelota,
//								nuevaPosicion);
//
//						double signoY = Math.signum(pelota.getDireccion()
//								.getY());
//
//						double anguloNuevo = ((anguloMayor - anguloMenor) / this
//								.getAppearance().getWidth())
//								* puntoDeColision
//								+ anguloMenor;
//						// aprovecho e invierto el signo que traia Y con el
//						// truquito de
//						// multiplicarlo por -1
//						pelota.setDireccion(new Vector(Math.sin(anguloNuevo),
//								(-1) * signoY * Math.cos(anguloNuevo)));
//
//						// pelota.setX(nuevaPosicion.getX());
//						pelota.setY(signoY > 0 ? this.getY()
//								- pelota.getAppearance().getHeight() - 1 : this
//								.getY() + this.getAppearance().getHeight() + 1);
//
//					}
//
//					private double getPuntoColision(
//							GameComponent<GameScene> raqueta, Pelota pelota,
//							Vector nuevaPosicion) {
//						if (pelota.getX() > raqueta.getX()
//								&& pelota.getX()
//										+ pelota.getAppearance().getWidth() < raqueta
//										.getX()
//										+ raqueta.getAppearance().getWidth()) {
//							double xCentroPelota = nuevaPosicion.getX()
//									+ pelota.getAppearance().getWidth() / 2;
//
//							return xCentroPelota - raqueta.getX();
//						} else if (pelota.getX() < raqueta.getX()) {
//							return 0;
//						} else {
//							return raqueta.getAppearance().getWidth();
//						}
//					}
//
//					public boolean colisiona(
//							GameComponent<GameScene> rectangle, Pelota pelota,
//							Vector nuevaPosicion) {
//						return CollisionDetector.INSTANCE
//								.collidesCircleAgainstRect(
//										nuevaPosicion.getX(), nuevaPosicion
//												.getY(), pelota.getAppearance()
//												.getWidth() / 2, rectangle
//												.getX(), rectangle.getY(),
//										rectangle.getAppearance().getWidth(),
//										rectangle.getAppearance().getHeight());
//					}
//				});
			}
		}

	}

	@Override
	public Dimension getDisplaySize() {
		return new Dimension(500, 500);
	}

	@Override
	public String getTitle() {
		return "Pelotita";
	}

	public static void main(String[] args) {

		new DesktopGameLauncher(new ArkanoidGame()).launch();

	}

}
