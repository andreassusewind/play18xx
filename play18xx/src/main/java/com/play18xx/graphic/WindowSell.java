package com.play18xx.graphic;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.play18xx.material.Basic;
import com.play18xx.material.Corporation;
import com.play18xx.material.Player;

public class WindowSell {
	private final static int tabpos = 0;

	public static void setWindowSell(Basic basic, Corporation corp, Player player, int selloption) {
		JFrame frame = new JFrame();
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		switch (selloption) {
		case 49: 
			sellNormalShare(basic, corp, player, frame, c);
			break;
		case 99:
			break;
		}
		frame.setVisible(true);
	}
	
	private static void sellNormalShare(Basic basic, Corporation corp, Player player, JFrame frame,
			GridBagConstraints c) {
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		JLabel label = new JLabel(player.getName());
		frame.add(label, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.insets = new Insets(20, 0, 0, 0);
		label = new JLabel("Get share from");
		frame.add(label, c);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.insets = new Insets(10, 0, 0, 0);
		label = new JLabel(corp.getName());
		frame.add(label, c);

		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.insets = new Insets(20, 0, 0, 0);
		label = new JLabel("for " + corp.getMarker().getValue());
		frame.add(label, c);
		
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.insets = new Insets(20, 0, 0, 0);
		label = new JLabel("Quantity: ");
		frame.add(label, c);
		
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		c.insets = new Insets(20, 0, 0, 0);
		int shares = corp.getSaleableShares(player.getIndex());
		String[] combolist = new String[shares+1];
		for(int i=0; i<=shares; i++) { combolist[i] = ""+i; }
		JComboBox<Object> quantity = new JComboBox<Object>(combolist);
		quantity.setSelectedIndex(1);
		frame.add(quantity, c);
		
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		JButton buy = new JButton("Sell");
		buy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				corp.sellShares(basic, player, quantity.getSelectedIndex());
				corp.checkPresident();
				
				basic.buildGraphics();
				basic.getTP().setSelectedIndex(tabpos);
				frame.dispose();
			}
		});
		frame.add(buy, c);
		

		frame.setSize(300, 300);
	}
	
	public static void buysellPrivate(Basic basic, Player player) {
		JFrame frame = new JFrame();
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		JLabel label = new JLabel("Buy / sell Private: ");
		frame.add(label, c);
		
		String[] privs = new String[basic.getPrivates().size()];
		for(int i=0; i<basic.getPrivates().size(); i++) {
			privs[i] = basic.getPrivates().get(i).getName();
		}
		JComboBox<Object> privbox = new JComboBox<Object>(privs);
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		frame.add(privbox, c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		label = new JLabel("from / to Player: ");
		frame.add(label, c);
		
		String[] players = new String[basic.getPlayers().size()];
		for(int i=0; i<basic.getPlayers().size(); i++) {
			players[i] = basic.getPlayers().get(i).getName();
		}
		JComboBox<Object> playerbox = new JComboBox<Object>(players);
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		frame.add(playerbox, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		label = new JLabel("for: ");
		frame.add(label, c);
		
		JTextField value = new JTextField("50", 4);
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		frame.add(value, c);
		
		JButton buy = new JButton("Buy");
		buy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				basic.buildGraphics();
				basic.getTP().setSelectedIndex(tabpos);
				frame.dispose();
			}
		});
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		frame.add(buy, c);
		
		JButton sell = new JButton("Sell");
		sell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				basic.buildGraphics();
				basic.getTP().setSelectedIndex(tabpos);
				frame.dispose();
			}
		});
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		frame.add(sell, c);
		
		
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
}
