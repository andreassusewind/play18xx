package com.play18xx.graphic;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.play18xx.material.Basic;
import com.play18xx.material.Certificate;
import com.play18xx.material.Corporation;
import com.play18xx.material.Player;
import com.play18xx.material.Private;
import com.play18xx.material.Train;

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
	
	public static void buysellPrivate(Basic basic, Player player, int index, Point pos) {
		JFrame frame = new JFrame("Buying / Selling Private Companies between Players");
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		frame.setLocation(pos);

		//Variable typing
		JLabel label;
		JComboBox<Object> privbox;
		String[] privs = new String[basic.getPrivates().size()];
		JComboBox<Object> playerbox;
		String[] players = new String[basic.getPlayers().size()-1];
		JTextField price;
		JButton buy;
		JButton sell;
		

		int COUNTER = 0;
		for(Private priv : basic.getPrivates()) {
			privs[COUNTER] = priv.getName();
			COUNTER++;
		}
		COUNTER = 0;
		for(Player play : basic.getPlayers()) {
			if(play.getIndex() != player.getIndex()) {
				players[COUNTER] = play.getName();
				COUNTER++;
			}
		}
		
		// Begin of frame components
		label = new JLabel ("Select Private: ");
		c.gridx = 0;
		c.gridy = 0;
		frame.add(label,c);
		
		privbox = new JComboBox<Object>(privs);
		c.gridx = 1;
		c.gridy = 0;
		privbox.setSelectedIndex(index);
		ActionListener privboxActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			buysellPrivate(basic,player,privbox.getSelectedIndex(), frame.getLocation());
			frame.dispose();	
			
			}
        };		
		privbox.addActionListener(privboxActionListener);
		frame.add(privbox,c);
		
		Private selectedPrivate = basic.getPrivates().get(privbox.getSelectedIndex());
		
		label = new JLabel("Price: ");
		c.gridx = 0;
		c.gridy = 2;
		frame.add(label,c);
		price = new JTextField("5");
		c.gridx = 1;
		c.gridy = 2;
		frame.add(price,c);

		
		if(selectedPrivate.getOwner() == player.getIndex()) {
			label = new JLabel("Sell Private to:       ");
			c.gridx = 0;
			c.gridy = 1;
			frame.add(label,c);
			playerbox = new JComboBox<Object>(players);
			c.gridx = 1;
			c.gridy = 1;
			frame.add(playerbox,c);
			sell = new JButton("Sell");
			sell.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					player.increaseMoney(Integer.parseInt(price.getText()));
					Player otherplayer = com.play18xx.material.Player.getPlayer(basic, (String)playerbox.getSelectedItem());
					otherplayer.decreaseMoney(Integer.parseInt(price.getText()));
					selectedPrivate.setOwner(otherplayer.getIndex());
					
					basic.buildGraphics();
					basic.getTP().setSelectedIndex(tabpos);
					frame.dispose();
				}
			});
			c.gridx = 1;
			c.gridy = 3;
			if(selectedPrivate.getOwner() > 10) {sell.setEnabled(false);}
			frame.add(sell,c);
		}
		else {
			label = new JLabel("Buy Private from:       ");
			c.gridx = 0;
			c.gridy = 1;
			frame.add(label,c);
			label = new JLabel(selectedPrivate.getOwner(basic));
			c.gridx = 1;
			c.gridy = 1;
			frame.add(label,c);
			buy = new JButton("Buy");
			buy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					player.decreaseMoney(Integer.parseInt(price.getText()));
					basic.getPlayers().get(selectedPrivate.getOwner()).increaseMoney(Integer.parseInt(price.getText()));
					selectedPrivate.setOwner(player.getIndex());
					
					basic.buildGraphics();
					basic.getTP().setSelectedIndex(tabpos);
					frame.dispose();
				}
			});
			c.gridx = 1;
			c.gridy = 3;
			if(selectedPrivate.getOwner() > 10) {buy.setEnabled(false);}
			frame.add(buy,c);
			
		}
		
		
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	
	public static void sellfortrain(Basic basic, Player player, Corporation corp, Train train, String sellposition, List<Certificate> certs, int restvalue, int[] checkboxestrue, Point pos) {
		JFrame frame = new JFrame("Selling Certs for Train");
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 2;
		frame.setLocation(pos);
		c.gridwidth = 1;

		int selectedvalue = 0;
		int certvalue = 0;
		
		if(player.getMoney() + corp.getMoney() >= train.getCost()) {
			///implementation for direct buying of train without frame
		}
		else {
			JLabel label = new JLabel(player.getName() + " must sell Certs to get "+ (train.getCost()-corp.getMoney()) + " for buying a " + train.getDistancePrimary()
					+ " train from " + sellposition);
			c.gridx=0;
			c.gridy=0;
			frame.add(label,c);

			JCheckBox[] checkboxes = new JCheckBox[certs.size()];
			ItemListener checkboxListener = new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					for(int i=0; i<certs.size(); i++) {
						if(checkboxes[i].isSelected()) checkboxestrue[i] = 1; 
						else checkboxestrue[i] = 0;
					}
					sellfortrain(basic, player, corp, train, sellposition, certs, restvalue, checkboxestrue, frame.getLocation());
					frame.dispose();	
				}
	        };
			
			for(int i=0; i<certs.size(); i++) {
				for(Corporation var : basic.getCorporations()) {
					if(certs.get(i).getName().split("-")[0].equals(var.getName())) 
						certvalue = var.getMarker().getValue() * certs.get(i).getPercentValue()/10;
				}
				
				checkboxes[i] = new JCheckBox(certs.get(i).getName() + " - " + certvalue);
				if(checkboxestrue[i] == 1) {
					checkboxes[i].setSelected(true);
					selectedvalue=selectedvalue+certvalue;
				}
				c.gridy=i+1;
				checkboxes[i].addItemListener(checkboxListener);
				frame.add(checkboxes[i],c);
			}
			
			label = new JLabel("----------------------------------");
			c.gridy=GridBagConstraints.RELATIVE;
			frame.add(label,c);
			label = new JLabel("Total selected: " + selectedvalue);
			frame.add(label,c);
			
			final int moneyrest = selectedvalue + corp.getMoney() + player.getMoney() - train.getCost();
			
			JButton sell = new JButton("Sell Certs / Buy Train");
			sell.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for(int i=0; i<certs.size(); i++) {
						if(checkboxestrue[i] == 1) {
							certs.get(i).setOwner(92);
							Corporation soldcorp = certs.get(i).getCorp(basic);
							soldcorp.checkPresident();
						}
					}
					player.setMoney(moneyrest);
					corp.getTrains().add(train);
					corp.setMoney(0);
										
					basic.buildGraphics();
					basic.getTP().setSelectedIndex(tabpos);
					frame.dispose();
				}
			});
			if(player.getMoney() + corp.getMoney() + selectedvalue < train.getCost())sell.setEnabled(false);
			frame.add(sell,c);

			
		}
		
		//must later be put in the else case
		frame.setSize(500, 700);
		frame.setVisible(true);
		
	}
	
/*	public static void deletebuysellPrivateOLD(Basic basic, Player player) {
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
	} */
}
