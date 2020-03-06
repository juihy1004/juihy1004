package com.Flipbook.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.Flipbook.model.Brush;

public class ColorBoardFunction {

	public Color changeBlackPen() {

		Color col2 = new Color(88, 87, 85);
		return col2;
	}

	public Color changeRedPen() {

		Color col2 = new Color(220, 129, 103);
		return col2;
	}

	public Color changeGreedPen() {

		Color col2 = new Color(188, 196, 155);
		return col2;
	}

	public Color changeBluePen() {

		Color col2 = new Color(164, 182, 186);
		return col2;
	}

	public Color changeEraser() {

		Color col2 = new Color(255, 255, 255);
		return col2;

	}

	public BufferedImage allEraser(BufferedImage bi) {

		for (int i = 0; i < bi.getWidth(); i++)
			for (int j = 0; j < bi.getHeight(); j++) {
				bi.setRGB(i, j, Color.white.getRGB());
			}

		return bi;

	}
	
	public BufferedImage allEraser_b(BufferedImage bi) {
		
        for(int i = 0; i < bi.getWidth();i++)
            for(int j = 0; j < bi.getHeight(); j ++)
            {
            	Color color = new Color(88, 87, 85);
                bi.setRGB(i,j ,color.getRGB());
            }    
       
       
		return bi;
		
		
	}

}
