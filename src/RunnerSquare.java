import java.awt.Color;
import java.awt.Graphics;

public class RunnerSquare extends RunnerObject {
	double gravity = 0.3;
	double velocity = 0;
	int power = 15;
	int limit = 390;
	int xvelocity = 0;
	int rightLimit = 325;
	int leftLimit = 25;
	boolean canJump = false;
	RunnerSquare(int x, int y, double width, int height){
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
		if(y< limit) {
			canJump = false;
		}
		x+= xvelocity;
		if(canJump) {
			super.update();
		}
		
	}
	int difference;
	void draw(Graphics g) {
		difference = 390 - (int)y;

		g.setColor(Color.DARK_GRAY);
		g.fillRect((int)x+difference/20, 430+difference/40, (int)width-(difference/10), (int)height/2 - difference/20);
		g.setColor(Color.black);
		g.fillRect((int)x, (int)y, (int)width, (int)height);

	}
	
	void jump() {
		if(canJump) {
			velocity -= power;
		}
	}
	void fall(double hole) {
		if(y+40 < hole-30 && y+60 > hole-60) {
			width-=5;
			height-=5;
			x+=3;
		}
	}
	
	
}
