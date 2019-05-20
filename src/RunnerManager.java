import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class RunnerManager {
	RunnerSquare square;
	ArrayList<Obstacle> obstacle = new ArrayList<Obstacle>();
	ArrayList<Hole> hole = new ArrayList<Hole>();
	ArrayList<Coin> coin = new ArrayList<Coin>();

	long obstacleTimer = 0;
	int obstacleSpawnTime = 6000;
	RunnerManager(RunnerSquare square){
		this.square = square;
	}
	void update() {
		square.update();
		for (Obstacle obstacle : obstacle) {
			obstacle.update();
		}
		for(Hole hole : hole) {
			hole.update(square);
		}
		for(Coin coin : coin) {
			coin.update();
		}
	}
	void draw(Graphics g) {
		
		for (Obstacle obstacle : obstacle) {
			obstacle.draw(g);
		}
		for(Hole hole: hole) {
			hole.draw(g);
		}
		for(Coin coin: coin) {
			coin.draw(g);
		}
		square.draw(g);
	}
	void addObstacle(Obstacle obstacle) {
		this.obstacle.add(obstacle);
	}
	void addHole(Hole hole) {
		this.hole.add(hole);
	}
	void addCoin(Coin coin) {
		this.coin.add(coin);
	}
	void checkCollision() {
		for (Obstacle obstacle : obstacle) {
			if(square.collisionBox.intersects(obstacle.collisionBox)&& square.y == 390) {
				square.Alive = false;
				
			}
			

		}	
	}
	
	void manageObstacles() {

		if(System.currentTimeMillis()-obstacleTimer>=obstacleSpawnTime) {
			addObstacle(new Obstacle(new Random().nextInt((int)(0.25*RunnerGame.WIDTH))+(int)(0.33*RunnerGame.WIDTH), 0, 30, 50));
			obstacleTimer = System.currentTimeMillis();
			addCoin(new Coin(new Random().nextInt((int)(0.25*RunnerGame.WIDTH))+(int)(0.33*RunnerGame.WIDTH), 0, 30, 50));
		}

		
	}
}
