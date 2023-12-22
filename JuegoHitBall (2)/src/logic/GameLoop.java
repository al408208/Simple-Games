package logic;

public class GameLoop extends Thread {
	private static final int FPS = 30;
	private GameEngine game;

	public GameLoop(GameEngine game) {
		super();
		this.game = game;
	}


	@Override
	public void run() {
		try {
			while (game.isRunning) {
				long time = System.currentTimeMillis();
				if(game.bandera) {
					game.mover();
					game.draw();
					game.fin();
				}

				// delay for each frame - time it took for one frame
				time = (1000 / FPS) - (System.currentTimeMillis() - time);

				if (time > 0) {
					try {
						Thread.sleep(time);
					} catch (Exception e) {
					}
				}
			}

			game.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
