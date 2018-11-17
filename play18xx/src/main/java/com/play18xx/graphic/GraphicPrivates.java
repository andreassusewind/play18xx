package com.play18xx.graphic;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.play18xx.material.Basic;


public class GraphicPrivates extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GraphicPrivates(Basic basic) {
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		String[] combolist = new String[basic.getPlayers().size()];
		for (int i = 0; i < combolist.length; i++) {
			combolist[i] = basic.getPlayers().get(i).getName();
		}
		JTextField[] values = new JTextField[basic.getPrivates().size()];
		@SuppressWarnings("unchecked")
		JComboBox<Object>[] playerbox = new JComboBox[basic.getPrivates().size()];

		JLabel label = new JLabel("Set the end values of the privates and the bying player");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 6;
		this.add(label, c);

		for (int i = 0; i < basic.getPrivates().size(); i++) { // for every private
			label = new JLabel(basic.getPrivates().get(i).getName());
			c.gridx = 0;
			c.gridy = i + 1;
			c.gridwidth = 1;
			this.add(label, c);

			values[i] = new JTextField(Integer.toString(basic.getPrivates().get(i).getParValue()), 10);
			c.gridx = 1;
			c.gridy = i + 1;
			c.gridwidth = 1;
			this.add(values[i], c);
			playerbox[i] = new JComboBox<Object>(combolist);
			c.gridx = 2;
			c.gridy = i + 1;
			c.gridwidth = 1;
			this.add(playerbox[i], c);
		}

		JButton take = new JButton("Übernehmen");
		c.gridx = 1;
		c.gridy = 19;
		c.gridwidth = 2;
		c.insets = new Insets(10, 0, 0, 0); // top padding
		take.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < basic.getPrivates().size(); i++) { // for every private
					basic.getPlayers().get(playerbox[i].getSelectedIndex())
							.decreaseMoney(Integer.valueOf(values[i].getText())); // decrease Money of the player
//					basic.getPlayers().get(playerbox[i].getSelectedIndex()).getPrivates()
//							.add(basic.getPrivates().get(i)); // the player get the P-Certificate
					basic.getPrivates().get(i).setOwner(playerbox[i].getSelectedIndex()); // the P-Certificate get
																							// player as owner
					basic.getPrivates().get(i).BuyPrivate(basic, playerbox[i].getSelectedIndex()); // the P-Special
																									// Function is
																									// called
				}
				basic.getGameplay().setStockmarketRoundCounter(1);
				basic.getGameplay().setCurrentPlayer(
						(playerbox[basic.getPrivates().size() - 1].getSelectedIndex() + 1) % basic.getPlayers().size());

				basic.buildGraphics();
				basic.getTP().setSelectedIndex(0);
			}
		});
		this.add(take, c);
	}
}
