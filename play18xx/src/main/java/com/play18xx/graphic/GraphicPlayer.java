package com.play18xx.graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JComponent;

import com.play18xx.material.Basic;
import com.play18xx.material.Certificate;
import com.play18xx.material.Player;
import com.play18xx.material.Private;

public class GraphicPlayer extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Player player;
	Basic basic;

	public GraphicPlayer(Basic basic, Player player) {
		this.player = player;
		this.basic = basic;
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
		
		List<Private> privs = basic.getPlayerPrivates(player.getIndex());
		for(Private priv : privs) {
			ypos = ypos + 10;
			g.drawString(priv.getName(), xpos1, ypos);
		}

		List<Certificate> certs = basic.getPlayerCertificates(player.getIndex());
		for(Certificate cert : certs) {
			ypos = ypos + 10;
			g.drawString(cert.getName(), xpos1, ypos);
		}
		
		ypos = ypos + 10;
		g.drawString("----------------", xpos1, ypos);
		ypos = ypos + 10;
		g.drawString("Total: " + player.getPlayersCertsCount(basic), xpos1, ypos);
	}
}
