import java.awt.Graphics;
import java.awt.Rectangle;

public class RunnerObject {
	 int x;
     float y;
     int width;
     int height;
     boolean Alive;
     Rectangle collisionBox;
	RunnerObject(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		Alive = true;
		collisionBox = new Rectangle(x, y, width, height);
	}
	void update() {
        collisionBox.setBounds(x, (int)y, width, height);
	}
	void draw(Graphics g) {
		
	}
}
