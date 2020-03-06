package com.Flipbook.model;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

import com.Flipbook.controller.ColorBoardFunction;
import com.Flipbook.view.GUI.MainFrame;

public class Brush extends JLabel {

	public int xx, yy;
	public Color col;

	public void paint(Graphics g) {

		g.setColor(col);
		g.fillOval(xx - 5, yy - 5, 10, 10);

	}
}