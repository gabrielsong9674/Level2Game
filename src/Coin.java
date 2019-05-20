import java.awt.Color;
import java.awt.Graphics;
public class Coin extends RunnerObject {
	
	Coin(int x, int y, double width, int height) {
		super(x, y, width, height);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	float speed = 0;
	void update() {
		super.update();
		speed +=.01;
		y+=speed;
	}
	void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillOval((int)x, (int)y, (int)width, (int)height);
	}
}
