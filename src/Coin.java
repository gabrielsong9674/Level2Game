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
	float speed = (float) RunnerManager.baseSpeed;
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
		g.drawImage(GameState.coinImg, (int)x, (int)y, (int)width, (int)height, null);
	}
	
}