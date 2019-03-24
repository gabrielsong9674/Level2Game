import java.awt.Color;
import java.awt.Graphics;

public class RunnerSquare extends RunnerObject {
	double gravity = 0.6;
	double velocity = 0;
	int power = 20;
	int limit = 390;
	int xvelocity = 0;
	int rightLimit = 325;
	int leftLimit = 25;
	boolean canJump = false;
	RunnerSquare(int x, int y, int width, int height){
		super(x, y, width, height);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}
	void update() {
		velocity += gravity;
		y += velocity;
		if(y >= limit) {
			canJump = true;
			velocity = 0;
			y = limit;
		}
		x+= xvelocity;
		if(canJump == true) {
			super.update();
		}
		
	}
	void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, (int)y, width, height);
	}
	void jump() {
		if(canJump) {
		velocity -= power;
		canJump = false;
		collisionBox.setBounds(-100, -100, width, height);
		}	
	}
	
	
}
