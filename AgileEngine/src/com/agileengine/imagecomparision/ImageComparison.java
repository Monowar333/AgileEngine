package com.agileengine.imagecomparision;

import static java.awt.Color.red;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.agileengine.main.Figure;
import com.agileengine.main.Rectangles;

public class ImageComparison {
	private BufferedImage image = null;
	private BufferedImage image2 = null;
	private BufferedImage imageout = null;
	private List<Figure> rectList;

	public BufferedImage comprasion(BufferedImage image, BufferedImage image2) {
		this.image = image;
		this.image2 = image2;
		int width = (image.getWidth() - image2.getWidth() < 0) ? image.getWidth() : image2.getWidth();
		int hight = (image.getHeight() - image2.getHeight() < 0) ? image.getHeight() : image2.getHeight();
		imageout = new BufferedImage(width, hight, BufferedImage.TYPE_INT_RGB);
		Graphics g = imageout.getGraphics();
		g.setColor(red);		
		int countflag = 0;
		int rgb1 = 0;
		int rgb2 = 0;
		rectList = new ArrayList<>();
		for (int i = 0; i < width - 1; i++) {
			for (int j = 0; j < hight - 1; j++) {
				rgb1 = image.getRGB(i, j);
				rgb2 = image2.getRGB(i, j);
				if (!compareRGB(rgb1, rgb2)) {
					countflag = 0;
					if (rectList.size() != 0) {
						for (Figure rectIn : rectList) {
							if ((i <= rectIn.getXmax() + 5 && 
									i >= rectIn.getXmin() - 5 && 
									j <= rectIn.getYmax() + 5 && 
									j >= rectIn.getYmin() - 5)) {
								if (i > rectIn.getXmax()) {
									rectIn.setXmax(i);
								}
								if (i < rectIn.getXmin()) {
									rectIn.setXmin(i);
								}
								if (j > rectIn.getYmax()) {
									rectIn.setYmax(j);
								}
								if (j < rectIn.getYmin()) {
									rectIn.setYmin(j);
								}
								++countflag;
								break;
							}
						}
						if (countflag == 0) {
							rectList.add(new Rectangles(i, i, j, j));
						}

					} else {
						rectList.add(new Rectangles(i, i, j, j));
					}
					imageout.setRGB(i, j, rgb2);
				} else {
					imageout.setRGB(i, j, rgb1);
				}
			}
		}

		System.out.println("Count: " + rectList.size());
		for (Figure rectIn : rectList) {
			System.out.println(rectIn.toString());
		}
		rectanglesMarge();
		System.out.println("Count: " + rectList.size());
		for (Figure rectIn : rectList) {
			System.out.println(rectIn.toString());
		}
		for (Figure rectIn : rectList) {
			rectIn.draw(g);
		}
		return imageout;
	}

	private void rectanglesMarge() {
		int countflag = rectList.size();
		while (countflag != 0) {
			countflag = 0;
			for (Figure rectRes : rectList) {
				if (rectRes.isFlag()) {
					for (Figure rectIn : rectList) {
						if ((rectIn.isFlag()) && (!rectIn.equals(rectRes)) && ((rectIn.getXmax() <= rectRes.getXmax()
								&& rectIn.getXmax() >= rectRes.getXmin() && rectIn.getYmax() <= rectRes.getYmax()
								&& rectIn.getYmax() >= rectRes.getYmin())
								|| (rectIn.getXmin() <= rectRes.getXmax() && rectIn.getXmin() >= rectRes.getXmin()
										&& rectIn.getYmin() <= rectRes.getYmax()
										&& rectIn.getYmin() >= rectRes.getYmin())
								|| (rectIn.getXmin() <= rectRes.getXmax() && rectIn.getXmin() >= rectRes.getXmin()
										&& rectIn.getYmax() <= rectRes.getYmax()
										&& rectIn.getYmax() >= rectRes.getYmin())
								|| (rectIn.getXmax() <= rectRes.getXmax() && rectIn.getXmax() >= rectRes.getXmin()
										&& rectIn.getYmin() <= rectRes.getYmax()
										&& rectIn.getYmin() >= rectRes.getYmin()))) {
							System.out.println(rectRes + "/////////" + rectIn);
							if (rectRes.getXmax() < rectIn.getXmax()) {
								rectRes.setXmax(rectIn.getXmax());
							}
							if (rectRes.getXmin() > rectIn.getXmin()) {
								rectRes.setXmin(rectIn.getXmin());
							}
							if (rectRes.getYmax() < rectIn.getYmax()) {
								rectRes.setYmax(rectIn.getYmax());
								;
							}
							if (rectRes.getYmin() > rectIn.getYmin()) {
								rectRes.setYmin(rectIn.getYmin());
							}
							rectRes.setFlag(true);
							rectIn.setFlag(false);
							++countflag;
						}

					}
				}
			}
			Iterator<Figure> it = rectList.iterator();
			while (it.hasNext()) {
				Figure rec = it.next();
				if (!rec.isFlag()) {
					it.remove();
				}
			}
		}
	}

	private boolean compareRGB(int rgb1, int rgb2) {
		int r1 = rgb1 >> 16 & 0xff;
		int g1 = rgb1 >> 8 & 0xff;
		int b1 = rgb1 & 0xff;
		int r2 = rgb2 >> 16 & 0xff;
		int g2 = rgb2 >> 8 & 0xff;
		int b2 = rgb2 & 0xff;
		return (r2 >= r1 * 0.9) && (r2 <= r1 * 1.1) && 
				(g2 >= g1 * 0.9) && (g2 <= g1 * 1.1) && 
				(b2 >= b1 * 0.9) && (b2 <= b1 * 1.1);
	}

	public List<Figure> getRectList() {
		return rectList;
	}

	public void setRectList(List<Figure> rectList) {
		this.rectList = rectList;
	}

}
