import java.awt.Color;
import java.awt.Graphics;

public class Hole{
	double oneX1 = 120;
	double oneX2 = 280;
	double twoX1 = 114;
	double twoX2 = 286;
	double oneY = 0;
	double twoY = 25;
	double speedsOne = 0;
	double speedsTwo = 0.1;
	void update(RunnerSquare square) {
		speedsOne += .01;
		oneY+=speedsOne;
		twoY+=speedsTwo+=.01;
		oneX1-=(6.0/25.0)*speedsOne;
		twoX1-=(6.0/25.0)*speedsTwo;
		oneX2+=(6.0/25.0)*speedsOne;
		twoX2+=(6.0/25.0)*speedsTwo;
		if(square.y == 390) {
			square.fall(twoY);
		}
		if(square.height==0) {
			square.Alive=false;
		}
	}
	
	void draw(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillPolygon(new int [] {(int)oneX1, (int)oneX2, (int)twoX2,(int)twoX1},
				new int[] {(int)oneY, (int)oneY, (int)twoY,(int)twoY}, 4);
	}
}
