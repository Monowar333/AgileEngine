package com.agileengine.main;

import java.awt.BorderLayout;
import static java.awt.Color.red;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

	public static void main(String[] args) {
	    BufferedImage image = null;
	    BufferedImage image2 = null;
	    BufferedImage imageout = null;
	    try {


	        File sourceimage = new File("image1.png");
	        File sourceimage2 = new File("image2.png");

	        image = ImageIO.read(sourceimage);
	        image2 = ImageIO.read(sourceimage2);

	        imageout = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
	        Graphics g = imageout.getGraphics();
	        g.setColor(red);
	        
	        int width = imageout.getWidth();
	        int hight = imageout.getHeight();
	        
	        int countflag = 0;
	        int rgb1 = 0;
	        int rgb2 = 0;
	        int delta = 0;
	        List<Rectangles> rectList = new ArrayList<>();
	        for(int i = 0; i < width - 1; i++){
	        	for(int j = 0; j < hight - 1; j++){
		        		rgb1 = image.getRGB(i, j);
		        		rgb2 = image2.getRGB(i, j);
		        		delta = Math.abs((rgb1 / rgb2) * 100);
		        		if(delta < 90 || delta > 110){
		        		countflag = 0;
		        		if (rectList.size() != 0){
		        			for(Rectangles rectIn: rectList){
		        				if((i <= rectIn.getXmax() + 5 &&
			        			   i >= rectIn.getXmin() - 5  &&
			        			   j <= rectIn.getYmax() + 5 &&
			        			   j >= rectIn.getYmin() - 5)){
				        				if (i > rectIn.getXmax()){
				        					rectIn.setXmax(i);
				        					}
				        				if (i < rectIn.getXmin()){
					        				rectIn.setXmin(i);
					        				}
				        				if (j > rectIn.getYmax()){
					        				rectIn.setYmax(j);
					        				}
				        				if (j < rectIn.getYmin()){
						        				rectIn.setYmin(j);
						        			}	
				        				++countflag;
		        				}
		        			}
			        		if(countflag == 0){
		        				rectList.add(new Rectangles(i, i, j, j));
		        			}
		        			
	        			}else{
	        				rectList.add(new Rectangles(i, i, j, j));
	        			}
		        		imageout.setRGB(i, j, rgb2);
	        		}else {
	        			imageout.setRGB(i, j, rgb1);
	        		}
	        	}
	        }
	        
	        System.out.println("Count: " + rectList.size());
	        for(Rectangles rectIn: rectList){
	        	System.out.println(rectIn.toString());
	        }
	        for(Rectangles rectIn: rectList){
	        	  g.drawRect(rectIn.getXmin(), rectIn.getYmin(),
	        			  rectIn.getXmax() - rectIn.getXmin(), 
	        			  rectIn.getYmax() - rectIn.getYmin());
	        }
	        g.dispose();
	    } catch (IOException e) {

	    }

	    JFrame frame = new JFrame();

	    JLabel label = new JLabel(new ImageIcon(imageout));

	    frame.getContentPane().add(label, BorderLayout.CENTER);

	    frame.pack();

	    frame.setVisible(true);

	}

}
