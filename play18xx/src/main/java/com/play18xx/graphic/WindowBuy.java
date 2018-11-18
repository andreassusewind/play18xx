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

import com.play18xx.material.Basic;
import com.play18xx.material.Corporation;
import com.play18xx.material.CorporationPosition;
import com.play18xx.material.Player;

public class WindowBuy {
	private final static int tabpos = 0;

	public static void setWindowBuy(Basic basic, Corporation corp, Player player, int buyoption) {
		JFrame frame = new JFrame();
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		//For Buyoptions search in material.Stockmarket.java
		switch (buyoption) {
		case 38:
			BuyNormalShareInitialStock(basic, corp, player, frame, c);
			break;
		case 39:
			BuyPresident(basic, corp, player, frame, c);
			break;
		case 48:
			BuyPresidentBaO(basic, corp, player, frame, c);
			break; // called by Private.BuyPrivate -> Switch for BO
		case 99:
			break;
		}

		frame.setVisible(true);
	}

	private static void BuyNormalShareInitialStock(Basic basic, Corporation corp, Player player, JFrame frame,
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
		c.gridwidth = 0;
		c.insets = new Insets(20, 0, 0, 0);
		label = new JLabel("for " + corp.getShareParValue());
		frame.add(label, c);

		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 0;
		JButton buy = new JButton("Buy");
		buy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.decreaseMoney(corp.getShareParValue()); 
				corp.getInitialStock().get(0).setOwner(player.getIndex());
/*				for (Certificate Certs : corp.getCertificates()) {
					if (Certs.getOwner() == 91) {
						Certs.setOwner(player.getIndex());
						break;
					}
				}*/
				basic.getGameplay().setCurrentPlayer(basic);
				basic.getGameplay().setPassNumber(0);

				basic.buildGraphics();
				basic.getTP().setSelectedIndex(tabpos);
				frame.dispose();
			}
		});
		frame.add(buy, c);
		frame.setSize(300, 300);

	}

	private static void BuyPresident(Basic basic, Corporation corp, Player player, JFrame frame, GridBagConstraints c) {
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		JLabel label = new JLabel(player.getName());
		frame.add(label, c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.insets = new Insets(20, 0, 0, 0);
		label = new JLabel("Set initial share value for");
		frame.add(label, c);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.insets = new Insets(10, 0, 0, 0);
		label = new JLabel(corp.getName());
		frame.add(label, c);

		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 0;
		c.insets = new Insets(20, 0, 0, 0);
		String[] combolist = new String[] { "100", "90", "82", "76", "71", "67" };
		JComboBox<Object> initvalue = new JComboBox<Object>(combolist);
		frame.add(initvalue, c);

		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 0;
		JButton buy = new JButton("Buy");
		buy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.decreaseMoney(Integer.parseInt((String) initvalue.getSelectedItem())   *   2);
				corp.getCertificates().get(0).setOwner(player.getIndex());
				corp.setShareParValue(Integer.parseInt((String) initvalue.getSelectedItem()));
				corp.setMoney(Integer.parseInt((String) initvalue.getSelectedItem()) * 10);
				corp.setPresident(player.getIndex());
				corp.setMarker(
						new CorporationPosition(basic, corp, Integer.parseInt((String) initvalue.getSelectedItem())));
//				basic.getStockmarket().getCorporationPositions().add(new CorporationPosition(basic, corp, Integer.parseInt((String)initvalue.getSelectedItem())));
				//basic.getGameplay().setCurrentPlayer((basic.getGameplay().getCurrentPlayer() + 1) % basic.getPlayers().size());
				basic.getGameplay().setCurrentPlayer(basic);
				basic.getGameplay().setPassNumber(0);

				basic.buildGraphics();
				basic.getTP().setSelectedIndex(tabpos);
				frame.dispose();
			}
		});
		frame.add(buy, c);
		frame.setSize(300, 300);

	}

	private static void BuyPresidentBaO(Basic basic, Corporation corp, Player player, JFrame frame,
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
		label = new JLabel("Set initial share value for");
		frame.add(label, c);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.insets = new Insets(10, 0, 0, 0);
		label = new JLabel(corp.getName());
		frame.add(label, c);

		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 0;
		c.insets = new Insets(20, 0, 0, 0);
		String[] combolist = new String[] { "100", "90", "82", "76", "71", "67" };
		JComboBox<Object> initvalue = new JComboBox<Object>(combolist);
		frame.add(initvalue, c);

		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 0;
		JButton buy = new JButton("Buy");
		buy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				corp.setShareParValue(Integer.parseInt((String) initvalue.getSelectedItem()));
				corp.setMoney(Integer.parseInt((String) initvalue.getSelectedItem()) * 10);
				corp.setMarker(
						new CorporationPosition(basic, corp, Integer.parseInt((String) initvalue.getSelectedItem())));
				corp.setPresident(player.getIndex());
				basic.buildGraphics();
				basic.getTP().setSelectedIndex(tabpos);
				frame.dispose();
			}
		});
		frame.add(buy, c);
		frame.setSize(300, 300);
	}
}
