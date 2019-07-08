import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class RunnerManager {
	RunnerSquare square;
	ArrayList<Obstacle> obstacle = new ArrayList<Obstacle>();
	ArrayList<Hole> hole = new ArrayList<Hole>();
	ArrayList<Coin> coin = new ArrayList<Coin>();
	long obstacleTimer = 0;
	int obstacleSpawnTime = 3000;
	long coinTimer = 0;
	int coinSpawnTime = 6000;
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
		removeObstacles();
	}
	void draw(Graphics g) {
		for(Hole hole: hole) {
			hole.draw(g);	
		}
		for(Obstacle obstacle : obstacle) {
			obstacle.draw(g);	
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
		for(Coin coin : coin) {
			if(square.collisionBox.intersects(coin.collisionBox) && square.y > 250) {
				coin.Alive = false;
				GameState.score ++;
			}
		}
	}
	void removeObstacles() {
		for (Iterator iterator = coin.iterator(); iterator.hasNext();) {
			
			Coin coin2 = (Coin) iterator.next();
			if(coin2.Alive == false) {
				iterator.remove();
			}
			
		}
	}
	void manageObstacles() {
		if(System.currentTimeMillis()-obstacleTimer>=obstacleSpawnTime && GameState.canSpawnObstacle) {
			addObstacle(new Obstacle(new Random().nextInt((int)(0.20*RunnerGame.WIDTH))+(int)(0.37*RunnerGame.WIDTH), 0, 30, 50));
			obstacleTimer = System.currentTimeMillis();
			}
		if(System.currentTimeMillis()-coinTimer>=coinSpawnTime && GameState.canSpawnObstacle) {
			addCoin(new Coin(new Random().nextInt((int)(0.20*RunnerGame.WIDTH))+(int)(0.37*RunnerGame.WIDTH), -40, 40, 40));
			coinTimer = System.currentTimeMillis();
		}
	}
}
