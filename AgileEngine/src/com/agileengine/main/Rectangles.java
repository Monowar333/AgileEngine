package com.agileengine.main;

public class Rectangles {
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;
	private boolean flag;
	
	public Rectangles() {
		
	}
	
	public Rectangles(int xmin, int xmax, int ymin, int ymax){
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
		this.flag = true;
	}
	
	
	@Override
	public String toString() {
		return "Rectangles [xmin=" + xmin + ", ymin=" + ymin + ", xmax=" + xmax + ", ymax=" + ymax + ", flag=" + flag
				+ "]";
	}

	public int getXmin() {
		return xmin;
	}
	public void setXmin(int xmin) {
		this.xmin = xmin;
	}
	public int getYmin() {
		return ymin;
	}
	public void setYmin(int ymin) {
		this.ymin = ymin;
	}
	public int getXmax() {
		return xmax;
	}
	public void setXmax(int xmax) {
		this.xmax = xmax;
	}
	public int getYmax() {
		return ymax;
	}
	public void setYmax(int ymax) {
		this.ymax = ymax;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (flag ? 1231 : 1237);
		result = prime * result + xmax;
		result = prime * result + xmin;
		result = prime * result + ymax;
		result = prime * result + ymin;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rectangles other = (Rectangles) obj;
		if (flag != other.flag)
			return false;
		if (xmax != other.xmax)
			return false;
		if (xmin != other.xmin)
			return false;
		if (ymax != other.ymax)
			return false;
		if (ymin != other.ymin)
			return false;
		return true;
	}
	
	
	

}
