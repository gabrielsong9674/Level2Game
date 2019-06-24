import java.awt.Color;
import java.awt.Graphics;

public class Obstacle extends RunnerObject{
	
	Obstacle(int x, int y, double width, int height){
		super(x,y,  width, height);
		line1.lineX1 = x;
		line1.lineX2 = x+width;
		line2.lineX1 = x-6;
		line2.lineX2 = x+6+width;
		line1.lineY = -25;
		line2.lineY = 0;
		distanceFromCenter = 200-(x+(.5*width));
		
	}
	double distanceFromCenter;
	
	Lines line1 = new Lines();
	Lines line2 = new Lines();
	float speed = 0;
	double size = 0;
	void update() {
		super.update();
		speed += .01;
		line1.lineX1-=(6.0/25.0)*speed/4;
		line1.lineX2+=(6.0/25.0)*speed/4;
		line2.lineX1-=(6.0/25.0)*speed/4;
		line2.lineX2+=(6.0/25.0)*speed/4;
		line1.lineY+=speed*.9;		
		line2.lineY+=speed;	
		width = line1.lineX2 - line1.lineX1;
		distanceFromCenter = 200-(line1.lineX1+(.5*width));
		line1.lineX1-=distanceFromCenter*.003;
		line2.lineX1-=distanceFromCenter*.003;
		line1.lineX2-=distanceFromCenter*.003;
		line2.lineX2-=distanceFromCenter*.003;
		super.collisionBox.setBounds((int)line1.lineX1, (int)line1.lineY, (int)width, (int)height);
	}
	void draw(Graphics g) {
			g.setColor(Color.black);
			g.drawLine((int)line1.lineX1, (int)line1.lineY, (int)line1.lineX2, (int)line1.lineY);
			g.drawLine((int)line2.lineX1, (int)line2.lineY, (int)line2.lineX2, (int)line2.lineY);
			g.drawLine((int)line1.lineX1, (int)line1.lineY, (int)line2.lineX1, (int)line2.lineY);
			g.drawLine((int)line2.lineX2-6, (int)line1.lineY, (int)line1.lineX2+6, (int)line2.lineY);	
		
		
	}
}
