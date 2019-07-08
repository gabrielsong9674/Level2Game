import java.awt.Color;
import java.awt.Graphics;

public class Lines {
	double lineX1 = 120;
	double lineY = 0;
	double lineX2 = 280;
	double speeds = 0;

	void update() {
		speeds += .01;
		lineY += speeds;
		lineX1 -= (6.0/25.0)*speeds;
		lineX2 += (6.0/25.0)*speeds;
	}
	void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine((int)lineX1, (int)lineY, (int)lineX2, (int)lineY);
	}
}
