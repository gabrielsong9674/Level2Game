import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameState extends JPanel implements ActionListener, KeyListener, MouseListener {
	final int START = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = START;
	static int score = 0;
	int highScore = score;
	Timer timer;
	Font titleFont;
	Font howToFont;
	Font scoreFont;
	Font endFont;
	Font difficultyFont;
	Font instructionFont;
	int[] xpoints = { 0, 120, 280, 400 };
	int[] ypoints = { 500, 0, 0, 500 };
	int numberOfPoints = 4;
	int squareX = 175;
	int squareY = 390;
	int squareWidth = 70;
	int squareHeight = 70;
	static boolean canSpawnObstacle;
	long lastGapSpawnTime;
	long obstacleSpawnCoolDown = 2000;
	public static BufferedImage coinImg;
	public static int frameCount = 0;
	static int lineCount = 0;
	int holeCount = 0;
	ArrayList<Lines> lines = new ArrayList<Lines>();

	RunnerManager manager;
	RunnerSquare square;
	Obstacle obstacle;
	Hole hole;
	Coin coin;

	int mouseX;
	int mouseY;

	static boolean btn1Click = false;
	static boolean btn2Click = false;
	static boolean btn3Click = false;
	static boolean instructionBtn = false;
	boolean xBtn = false;
	boolean menuBtn = false;

	GameState() {
		titleFont = new Font("Oswald", Font.PLAIN, 48);
		howToFont = new Font("Oswald", Font.PLAIN, 36);
		scoreFont = new Font("Oswald", Font.PLAIN, 12);
		endFont = new Font("Oswald", Font.PLAIN, 36);
		difficultyFont = new Font("Oswald", Font.PLAIN, 18);
		instructionFont = new Font("Oswald", Font.PLAIN, 14);

		timer = new Timer(1000 / 60, this);
		square = new RunnerSquare(squareX, squareY, squareWidth, squareHeight);
		manager = new RunnerManager(square);
		try {
			coinImg = ImageIO.read(this.getClass().getResourceAsStream("coin.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void resetGame() {

		square = new RunnerSquare(squareX, squareY, squareWidth, squareHeight);
		manager = new RunnerManager(square);
		holes = new ArrayList<Hole>();
		lines = new ArrayList<Lines>();
		score = 0;

		canSpawnObstacle = false;
		lastGapSpawnTime = System.currentTimeMillis();
		frameCount = 0;
		lineCount = 0;
		holeCount = 0;
		RunnerManager.baseSpeed = 0;
		btn1Click = false;
		btn2Click = false;
		btn3Click = false;
		instructionBtn = false;
		xBtn = false;
		menuBtn = false;
	}

	void startGame() {
		timer.start();
	}

	void updateStart() {

	}

	void updateGame() {
		manager.update();
		manager.manageObstacles();
		manager.checkCollision();
		if (square.Alive == false) {
			currentState = END;
		}
		HoleTimer();
	}

	void updateEnd() {

	}

	void drawStart(Graphics g) {
		addMouseListener(this);
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, RunnerGame.WIDTH, RunnerGame.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.cyan);
		g.drawString("RUN!", 130, 150);
		g.setColor(Color.black);
		g.drawString("RUN!", 132, 152);

		g.setColor(Color.cyan);
		g.setFont(difficultyFont);
		g.drawString("Choose your difficulty:", 96, 220);
		g.setColor(Color.black);
		g.drawString("Choose your difficulty:", 98, 222);
		if (btn1Click && !btn2Click && !btn3Click) {
			g.setColor(Color.DARK_GRAY);
			currentState = GAME;
		} else {
			g.setColor(Color.BLACK);
		}
		g.fillRoundRect(85, 250, 60, 40, 5, 5);
		if (btn2Click && !btn1Click && !btn3Click) {
			g.setColor(Color.DARK_GRAY);
			currentState = GAME;
		} else {
			g.setColor(Color.BLACK);
		}
		g.fillRoundRect(165, 250, 60, 40, 5, 5);
		if (btn3Click && !btn1Click && !btn2Click) {
			g.setColor(Color.DARK_GRAY);
			currentState = GAME;
		} else {
			g.setColor(Color.BLACK);
		}
		g.fillRoundRect(245, 250, 60, 40, 5, 5);
		g.setColor(Color.white);
		g.drawString("1", 111, 276);
		g.drawString("2", 191, 276);
		g.drawString("3", 271, 276);
		drawInstructions(g);
		g.setColor(Color.cyan);
		g.drawRect(2, 2, 396, 496);
		g.setColor(Color.black);
		g.drawRect(0, 0, 400, 500);
	}

	void drawInstructions(Graphics g) {
		g.setColor(Color.black);
		g.fillRoundRect(88, 380, 215, 50, 5, 5);
		g.setColor(Color.white);
		g.setFont(difficultyFont);
		g.drawString("Click for Instructions", 107, 410);
		if (instructionBtn && !xBtn) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRoundRect(0, 0, RunnerGame.WIDTH, RunnerGame.HEIGHT, 10, 10);
			g.setColor(Color.BLACK);
			g.setFont(howToFont);
			g.drawString("How to Play:", 10, 40);
			g.setFont(instructionFont);
			g.drawString("Your player is the black square", 15, 70);
			g.drawString("Avoid the blue trapezoids and collect the gold coins!", 15, 100);
			g.drawString("Each coin is worth one point", 15, 130);
			g.drawString("Stay on the path or you'll die", 15, 160);
			g.drawString("Move the black square using the left & right arrow keys", 15, 190);
			g.drawString("Jump using the up arrow key to avoid blue trapezoids", 15, 220);
			g.drawString("If you touch the blue trapezoids, you'll die", 15, 250);
			g.drawString("Choose your difficulty, 1: easy, 2: medium, 3: hard", 15, 250);
			// draw x button right corner
			g.drawLine(380, 20, 390, 10);
			g.drawLine(380, 10, 390, 20);
		}
	}

	void drawLines(Graphics g) {
		frameCount++;
		if (lines.size() < 7 && frameCount % 50 == 0) {
			lines.add(new Lines());
			lineCount++;
		}
		for (int i = lines.size() - 1; i >= 0; i--) {
			lines.get(i).update();
			lines.get(i).draw(g);
			if (lines.get(i).lineY > RunnerGame.HEIGHT) {
				lines.remove(i);
			}
		}
		if (lineCount % 12 == 0) {
			holes.add(new Hole());
			holeCount++;
			canSpawnObstacle = false;
			lastGapSpawnTime = System.currentTimeMillis();
		}
	}

	ArrayList<Hole> holes = new ArrayList<Hole>();

	void drawHole(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		for (int i = holes.size() - 1; i >= 0; i--) {
			holes.get(i).update(square);
			holes.get(i).draw(g);
		}
	}

	void HoleTimer() {
		if (System.currentTimeMillis() - lastGapSpawnTime >= obstacleSpawnCoolDown) {
			canSpawnObstacle = true;
		}
	}

	void drawGame(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, RunnerGame.WIDTH, RunnerGame.HEIGHT);
		g.setColor(Color.black);
		g.drawPolygon(xpoints, ypoints, numberOfPoints);
		g.setColor(Color.gray);
		g.fillPolygon(xpoints, ypoints, numberOfPoints);
		g.setColor(Color.black);
		g.setFont(scoreFont);
		g.drawString("Score:" + score, 5, 11);
		g.drawString("Restart Game", 317, 14);
		drawLines(g);
		drawHole(g);
		manager.draw(g);
	}

	void drawEnd(Graphics g) {
		if (score > highScore) {
			highScore = score;
		}
		g.setColor(Color.black);
		g.fillRect(0, 0, RunnerGame.WIDTH, RunnerGame.HEIGHT);
		g.setColor(Color.cyan);
		g.setFont(endFont);
		g.drawString("You Lost", 120, 160);
		g.drawString("Score: " + score, 130, 200);
		g.drawString("Your record: " + highScore, 85, 240);

		g.drawString("Press ENTER", 90, 290);
		g.drawString("to Restart", 115, 330);

		g.drawLine(50, 100, 350, 100);
		g.drawLine(50, 350, 350, 350);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		if (currentState == START) {
			score = 0;
			updateStart();
		} else if (currentState == GAME) {
			updateGame();
		} else if (currentState == END) {
			updateEnd();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == START) {
			drawStart(g);
		} else if (currentState == GAME) {
			drawGame(g);
		} else if (currentState == END) {
			drawEnd(g);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER && currentState == END) {
			currentState = START;
			resetGame();
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			square.jump();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			square.xvelocity = -5;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			square.xvelocity = 5;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			square.xvelocity = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			square.xvelocity = 0;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseX = e.getX();
		mouseY = e.getY();

		// difficulty buttons
		if (mouseY <= 290 && mouseY >= 250) {
			if (mouseX <= 145 && mouseX >= 85) {
				btn1Click = true;
				btn2Click = false;
				btn3Click = false;

			}
			if (mouseX <= 225 && mouseX >= 165) {
				btn2Click = true;
				btn1Click = false;
				btn3Click = false;

			}
			if (mouseX <= 305 && mouseX >= 245) {
				btn3Click = true;
				btn1Click = false;
				btn2Click = false;
			}
		}
		// instruction button
		if (mouseX <= 303 && mouseX >= 88 && mouseY <= 430 && mouseY >= 380) {
			instructionBtn = true;
			xBtn = false;
		}
		// x button from instruction page
		if (mouseX <= 400 && mouseX >= 380 && mouseY <= 20 && mouseY >= 10) {
			xBtn = true;
			instructionBtn = false;
		}
		// menu button in game
		if (mouseX <= 410 && mouseX >= 310 && mouseY <= 20 && mouseY >= 0) {
			menuBtn = true;
			resetGame();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
