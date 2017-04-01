package com.agileengine.main;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.agileengine.imagecomparision.ImageComparison;

public class Main {

	public static void main(String[] args) {
	    BufferedImage image = null;
	    BufferedImage image2 = null;
	    BufferedImage imageout = null;
	 

	    try{
	        File sourceimage = new File("image1.png");
	        File sourceimage2 = new File("image4.png");
	        image = ImageIO.read(sourceimage);
	        image2 = ImageIO.read(sourceimage2);
	        imageout = new ImageComparison().comprasion(image, image2);
	        File picture = new File("result.png");
	        ImageIO.write(imageout, "png", picture);
	    } catch (IOException e) {

	    }
	    
	    
	    JFrame frame = new JFrame();

	    JLabel label = new JLabel(new ImageIcon(imageout));

	    frame.getContentPane().add(label, BorderLayout.CENTER);

	    frame.pack();

	    frame.setVisible(true);

	}

}
