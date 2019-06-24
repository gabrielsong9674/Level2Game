import java.awt.Color;
import java.awt.Graphics;
public class Coin extends RunnerObject {
	
	Coin(int x, int y, double width, int height) {
		super(x, y, width, height);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		distanceFromCenter = 200-(x+(.5*width));
	}
	double distanceFromCenter;
	float speed = 0;
	void update() {
		super.update();
		speed +=.01;
		y+=speed;
		height=height*(1+(speed/500));
		width=width*(1+(speed/500));
		distanceFromCenter = 200-(x+(.5*width));
		x-=distanceFromCenter*.003;
		super.collisionBox.setBounds((int)x, (int)y, (int)width, (int)height);
	}
	void draw(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillOval((int)x, (int)y+60, (int)width, (int)height-20);
		g.setColor(Color.YELLOW);
		g.fillOval((int)x, (int)y, (int)width, (int)height);
	}
	
}