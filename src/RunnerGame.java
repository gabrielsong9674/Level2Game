import java.awt.Dimension;
import javax.swing.JFrame;

public class RunnerGame {
	JFrame window;
	static final int WIDTH = 400;
	static final int HEIGHT = 500;
	GameState game;
	RunnerGame(){
		window = new JFrame();
		game = new GameState();
	}
	public static void main(String[] args) {
		RunnerGame gameRunner = new RunnerGame();
		gameRunner.setup();
	}
	void setup() {
		window.add(game);
		window.setVisible(true);
		window.setSize(WIDTH, HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		window.pack();
		game.startGame();
		window.addKeyListener(game);
	}
}
