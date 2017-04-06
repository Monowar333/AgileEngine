package com.agileengine.main;

import java.awt.Graphics;

public class Rectangles extends Figure{
	
	public Rectangles() {
		
	}
	
	public Rectangles(int xmin, int xmax, int ymin, int ymax){
		setXmin(xmin); 
		setXmax(xmax);
		setYmax(ymax);
		setYmin(ymin);
		setFlag(true);
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawRect(getXmin(), getYmin(), getXmax() - getXmin(),
				getYmax() - getYmin());
		
	}	
	
	
}
