import java.awt.Color;
import java.awt.Graphics;

public class Hole extends RunnerObject {

	Hole(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	void update() {
		super.update();
		y+=2;
		
		
	}
	
	void draw(Graphics g) {
	}
}
