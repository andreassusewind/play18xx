package com.play18xx.graphic;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.play18xx.material.Basic;

public class PanePlayer {
	private final static int tabpos = 2;

	public static void setPanePlayer(Basic basic) {
		if (basic.getTP().getTabCount() > tabpos) {
			basic.getTP().removeTabAt(tabpos);
		}

		JPanel panel = new JPanel();

		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0.01;

		for (int i = 0; i < basic.getPlayers().size(); i++) {
			c.gridx = i % 4;
			if (i < 4) {
				c.gridy = 0;
			} else {
				c.gridy = 1;
			}
			panel.add(new GraphicPlayer(basic, basic.getPlayers().get(i)), c);
		}

		ImageIcon newicon = new ImageIcon(); // dummy ImageIcon maybe set in later versions
		basic.getTP().insertTab("Player Overview", newicon, panel, "Stock Market", tabpos);
	}

}
