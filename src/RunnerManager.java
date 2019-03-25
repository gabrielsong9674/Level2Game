import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class RunnerManager {
	RunnerSquare square;
	ArrayList<Obstacle> obstacle = new ArrayList<Obstacle>();
	ArrayList<Hole> hole = new ArrayList<Hole>();

	long obstacleTimer = 0;
	int obstacleSpawnTime =7000;
	long holeTimer = 0;
	int holeSpawnTime = 5000;
	RunnerManager(RunnerSquare square){
		this.square = square;
	}
	void update() {
		square.update();
		for (Obstacle obstacle : obstacle) {
			obstacle.update();
		}
		for(Hole hole : hole) {
			hole.update();
		}
	}
	void draw(Graphics g) {
		
		for (Obstacle obstacle : obstacle) {
			obstacle.draw(g);
		}
		for(Hole hole: hole) {
			hole.draw(g);
		}
		square.draw(g);
	}
	void addObstacle(Obstacle obstacle) {
		this.obstacle.add(obstacle);
	}
	void addHole(Hole hole) {
		this.hole.add(hole);
	}
	void checkCollision() {
		for (Obstacle obstacle : obstacle) {
			if(square.collisionBox.intersects(obstacle.collisionBox)) {
				square.Alive = false;
				
			}
			

		}	
	}
	
	void manageObstacles() {

		if(System.currentTimeMillis()-obstacleTimer>=obstacleSpawnTime) {
			addObstacle(new Obstacle(new Random().nextInt((int)(0.3*RunnerGame.WIDTH))+(int)(0.28*RunnerGame.WIDTH), -100, 70, 90));
			obstacleTimer = System.currentTimeMillis();
			//addHole(new Hole(new Random().nextInt((int)(0.3*RunnerGame.WIDTH))+(int)(0.2*RunnerGame.WIDTH), 20, 100, 50));

		}

		
	}
}
