package com.play18xx.graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Collections;

import javax.swing.JComponent;

import com.play18xx.material.Certificate;
import com.play18xx.material.Player;

public class GraphicPlayer extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Player player;

	public GraphicPlayer(Player player) {
		this.player = player;
		setPreferredSize(new Dimension(200, 250));
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		int xpos1 = 10;
		int xpos2 = 100;
		int ypos = 20;
		g.setColor(Color.BLACK);
		g.drawString(player.getName(), xpos1, ypos);

		g.setFont(new Font("Arial", Font.PLAIN, 10));
		ypos = ypos + 20;
		g.drawString("Players share:", xpos1, ypos);
		g.drawString("Players money:", xpos2, ypos);

		ypos = ypos + 20;
		g.drawString(Integer.toString(player.getMoney()), xpos2, ypos);

		ypos = 50;
		for (int i = 1; i <= player.getPrivates().size(); i++) {
			ypos = ypos + 10;
			g.drawString(player.getPrivates().get(i - 1).getName(), xpos1, ypos);
		}
		Collections.sort(player.getCertificates(), Certificate.NameComparator);
		for (int i = 1; i <= player.getCertificates().size(); i++) {
			ypos = ypos + 10;
			g.drawString(player.getCertificates().get(i - 1).getName(), xpos1, ypos);
		}
		
	}
}
