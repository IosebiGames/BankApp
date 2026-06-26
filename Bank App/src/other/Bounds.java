package other;

import java.awt.Rectangle;

public class Bounds {
	final int x, y, w, h;
	private Rectangle rec;
	
	public Bounds(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		rec = new Rectangle(x, y, w, h);
	}
	public Rectangle getBounds(int yChangeDir, int xChangeDir) {
		if(!(yChangeDir == 0)) rec.y += yChangeDir; rec.x += xChangeDir;
		return rec.getBounds();
	}
}
