import java.awt.Color;
import java.awt.Graphics;

public class Obstacle extends RunnerObject {
	Obstacle(int x, int y, int width, int height){
		super(x,y, width, height);
	}
	float speed = 0;
	void update() {
		speed += .01;
		y+= speed;
		
	}
	void draw(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, (int)y, width, height);
		
	}
}
