package com.play18xx.graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;

import com.play18xx.material.Basic;
import com.play18xx.material.Corporation;

public class GraphicStockmarket extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Basic basic;
	int stockmiddel;

	public GraphicStockmarket(Basic basic) {
		this.basic = basic;
		this.stockmiddel = setStockmiddel(basic.getStockmarket().getCode(), basic.getStockmarket().getRows(),
				basic.getStockmarket().getCols());

		setPreferredSize(new Dimension(basic.getStockmarket().getCols() * 50 + 1,
				basic.getStockmarket().getRows() * 50 + basic.getCorporations().size() * 20 + 35));
	}

	@Override
	public void paintComponent(Graphics g) {
		Color panel = new Color(238, 238, 238); // Color white

		g.setFont(new Font("TimesRoman", Font.PLAIN, 10));

// set the stockmarket graphic with the basic square values
		for (int i = basic.getStockmarket().getRows() - 1; i >= 0; i--) {
			// build fields from left to middel
			for (int j = 0; j < this.stockmiddel; j++) {
				g.setColor(getFieldcolor(basic.getStockmarket().getCode()[i][j]));
				g.fillRect(j * 50, i * 50, 50, 50); // draw field
				if (basic.getStockmarket().getCode()[i][j] == 0) {
					g.setColor(panel);
				} else {
					g.setColor(Color.BLACK);
				}
				g.drawRect(j * 50, i * 50, 50, 50); // draw frame
				g.setColor(getTextcolor(basic.getStockmarket().getCode()[i][j]));
				g.drawString(Integer.toString(basic.getStockmarket().getValue()[i][j]), (j * 50) + 30, (i * 50) + 47); // write
																														// field
																														// value
			}
			// build fields from right to middel
			for (int j = basic.getStockmarket().getCols() - 1; j >= this.stockmiddel; j--) {
				g.setColor(getFieldcolor(basic.getStockmarket().getCode()[i][j]));
				g.fillRect(j * 50, i * 50, 50, 50); // draw field
				if (basic.getStockmarket().getCode()[i][j] == 0) {
					g.setColor(panel);
				} else {
					g.setColor(Color.BLACK);
				}
				g.drawRect(j * 50, i * 50, 50, 50); // draw frame
				g.setColor(getTextcolor(basic.getStockmarket().getCode()[i][j]));
				g.drawString(Integer.toString(basic.getStockmarket().getValue()[i][j]), (j * 50) + 30, (i * 50) + 47); // write
																														// field
																														// value
			}
		}

// Set all corp markers on the stock market
		for (Corporation corp : basic.getCorporations()) {
			if (corp.getMarker().getValue() > 0) {
				int xpos = (corp.getMarker().getCol() * 50) + 5;
				int ypos = (corp.getMarker().getRow() * 50) + 10 + ((8 - corp.getMarker().getStack()) * 10);
				int xoff;
				int yoff;
				if (corp.getMarker().getStack() > 4) {
					xoff = 0;
					yoff = 0;
				} else {
					xoff = 12;
					yoff = -40;
				}
				g.setColor(Color.BLACK);
				g.fillOval(xpos + xoff - 4, ypos + yoff - 10, 12, 12);
				g.setColor(Color.WHITE);
				g.drawString(Integer.toString(corp.getIndex() + 1), xpos + xoff, ypos + yoff);
			}
		}
		/*
		 * for(int i=1; i<=basic.getStockmarket().getCorporationPositions().size(); i++)
		 * {
		 * System.out.println(basic.getStockmarket().getCorporationPositions().get(i-1).
		 * getRow()); int xpos =
		 * (basic.getStockmarket().getCorporationPositions().get(i-1).getCol() * 50) +
		 * 5; int ypos =
		 * (basic.getStockmarket().getCorporationPositions().get(i-1).getRow() * 50) +
		 * 10 + ((8 -
		 * basic.getStockmarket().getCorporationPositions().get(i-1).getStack()) * 10);
		 * int xoff; int yoff;
		 * if(basic.getStockmarket().getCorporationPositions().get(i-1).getStack() > 4)
		 * {xoff = 0; yoff = 0;} else {xoff = 12; yoff = -40;} g.setColor(Color.BLACK);
		 * g.fillOval( xpos+xoff-4, ypos+yoff-10, 12, 12); g.setColor(Color.WHITE);
		 * g.drawString(Integer.toString(basic.getStockmarket().getCorporationPositions(
		 * ).get(i-1).getCorp().getCorporationNumber(basic) +1 ), xpos+xoff, ypos+yoff);
		 * }
		 */
// Set the overview of corp details
		int xpos = 10;
		int yoff = basic.getStockmarket().getRows() * 50 + 10;
		int xoffintialstock = basic.getCorporations().get(0).getCertificates().size() * 20 + 20; // factor with
																									// initialstock
		g.setColor(Color.BLACK);
		g.drawString("Player", xpos + 60, yoff + 10);
		g.drawString("shares", xpos + 60, yoff + 20);
		g.drawString("Initial", xpos + 115, yoff + 10);
		g.drawString("price", xpos + 115, yoff + 20);
		g.drawString("Initial", xpos + 160, yoff + 10);
		g.drawString("stock", xpos + 160, yoff + 20);
		g.drawString("Bank", xpos + 160 + xoffintialstock, yoff + 10);
		g.drawString("stock", xpos + 160 + xoffintialstock, yoff + 20);

		for (int i = 0; i < basic.getCorporations().size(); i++) {
			int ypos = i * 20 + 40 + yoff;// this.corps[i].getCorporationnumber()*20 + 40 + yoff;
			g.setColor(Color.BLACK);
			g.fillOval(xpos - 4, ypos - 10, 12, 12);
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(i + 1), xpos, ypos); // this.corps[i].getCorporationnumber() + 1 ), xpos,
																// ypos);

			g.setColor(Color.BLACK);
			g.drawString(basic.getCorporations().get(i).getName(), xpos + 20, ypos);
			g.drawString(Integer.toString(basic.getCorporations().get(i).getSoldShares()) + " %", xpos + 60, ypos);
			g.drawString(Integer.toString(basic.getCorporations().get(i).getShareParValue()), xpos + 120, ypos);

// set values for the initial stock per corp
/*			for (int j = 1; j <= basic.getCorporations().get(i).getInitialStock().size(); j++) {
				g.drawString(
						Integer.toString(basic.getCorporations().get(i).getInitialStock().get(j - 1).getPercentValue()),
						xpos + 160 + ((j - 1) * 20), ypos);
			}*/

// set values for the bank stock per corp
/*			for (int j = 1; j <= basic.getCorporations().get(i).getBankPool().size(); j++) {
				g.drawString(
						Integer.toString(basic.getCorporations().get(i).getBankPool().get(j - 1).getPercentValue()),
						xpos + 160 + xoffintialstock + (j * 20), ypos);
			}*/
		}
	}

	public int getStockmiddel() {
		return stockmiddel;
	}

	private int setStockmiddel(int[][] array, int rows, int cols) {
		int middel = 0;
		for (int i = 0; i < cols; i++) {
			if (array[rows - 1][i] == 0) {
			} else {
				middel++;
			}
		}
		return (middel);
	}

	public Color getFieldcolor(int var) {
		Color panel = new Color(238, 238, 238); // Color white
		switch (var) {
		case 0:
			return (panel);
		case 1:
			return (Color.LIGHT_GRAY);
		case 2:
			return (Color.YELLOW);
		case 3:
			return (Color.ORANGE);
		case 4:
			return (Color.RED);
		case 5:
			return (Color.GREEN);
		default:
			return (panel);
		}
	}

	public Color getTextcolor(int var) {
		Color panel = new Color(238, 238, 238); // Color white
		switch (var) {
		case 0:
			return (panel);
		case 1:
			return (Color.BLACK);
		case 2:
			return (Color.BLACK);
		case 3:
			return (Color.BLACK);
		case 4:
			return (Color.WHITE);
		case 5:
			return (Color.BLACK);
		default:
			return (Color.BLACK);
		}
	}

}
