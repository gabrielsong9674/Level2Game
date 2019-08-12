import java.awt.Graphics;
import java.awt.Rectangle;

public class RunnerObject {
	 float x;
     float y;
     
     double width;
     double height;
     boolean Alive;
     Rectangle collisionBox;
	RunnerObject(int x, int y, double width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		Alive = true;
		collisionBox = new Rectangle(x, y, (int)width, height);
	}
	void update() {
        collisionBox.setBounds((int)x, (int)y, (int)width,(int) height);
        
	}
	void draw(Graphics g) {
		
	}
}
