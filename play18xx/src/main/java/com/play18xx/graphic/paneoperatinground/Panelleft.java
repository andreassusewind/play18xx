package com.play18xx.graphic.paneoperatinground;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.play18xx.graphic.PaneOperatingRound;
import com.play18xx.material.Basic;
import com.play18xx.material.Corporation;

import xml.XMLBasicSave;

public class Panelleft extends JPanel{
	private final static int tabpos = 1;

	JPanel panelleftmiddel = new JPanel(new GridBagLayout());
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void setPanel(Basic basic) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		if(basic.getGameplay().isOperationRound()) {
			JLabel label = new JLabel("Operation Round Number " + basic.getGameplay().getOperationroundCounter() + " of "
					+ basic.getGameplay().getMaxOperationrounds());
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 1;
			c.anchor = GridBagConstraints.PAGE_START;
			c.insets = new Insets(20, 0, 0, 0);
			this.add(label,c);
			
			setPanelleftmiddel(basic);
			c.weighty = 1.0;
			c.gridx = 0;
			c.gridy = 5;
			c.gridwidth = 1;
			c.anchor = GridBagConstraints.CENTER;
			this.add(panelleftmiddel, c);
			
		}
		else {
			JLabel label = new JLabel("Stockmarket Round is running....");
			c.weighty = 1.0;
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 1;
			c.anchor = GridBagConstraints.PAGE_START;
			c.insets = new Insets(20, 0, 0, 0);
			this.add(label,c);
		}

		JButton save = new JButton("Speichern");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0.1;
		c.anchor = GridBagConstraints.PAGE_END;
		c.insets = new Insets(0, 0, 0, 0);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 20;
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XMLBasicSave savexml = new XMLBasicSave(basic);
				savexml.savetoxml();
			}
		});
		this.add(save, c);

		JButton load = new JButton("Laden");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0.1;
		c.anchor = GridBagConstraints.PAGE_END;
		c.insets = new Insets(0, 0, 20, 0);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 21;
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xml.XMLBasicLoad.loadfromxml(basic);
				basic.buildGraphics();
				basic.getTP().setSelectedIndex(tabpos);
			}
		});
		this.add(load, c);

	}
	
	public void setPanelleftmiddel(Basic basic) {
		GridBagConstraints c = new GridBagConstraints();
		
		if (basic.getGameplay().getPhase() < 5) {
			JButton privates = new JButton("Privates");
			c.weighty = 0.1;
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 2;
			c.insets = new Insets(10, 0, 0, 0);
			privates.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					basic.getTP().getPOR().getPanemiddel().removeAll();
					basic.getTP().getPOR().getPanemiddel().setPanelPrivate(basic);
					PaneOperatingRound.setPaneOperatingRound(basic);
					basic.getTP().setSelectedIndex(tabpos);
				}
			});
			if(basic.getGameplay().isPrivatesDone()) {privates.setEnabled(false);}
			panelleftmiddel.add(privates, c);
		}
		
		//Corporation Buttons
		JButton corpbutton = new JButton();
		int COUNTER = 2;
		for (Corporation corp : basic.getGameplay().getOperationroundCorpOrder()) {
			c.weighty = 0.1;
			c.gridx = 0;
			c.gridy = COUNTER;
			c.gridwidth = 2;
			c.insets = new Insets(10, 0, 0, 0);
			corpbutton = new JButton(corp.getName());
			corpbutton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					basic.getTP().getPOR().getPanemiddel().removeAll();
					basic.getTP().getPOR().getPanemiddel().setPanelCorporation(basic, corp);
					basic.getTP().getPOR().getPanelright().removeAll();
					PaneOperatingRound.setPaneOperatingRound(basic);
					basic.getTP().setSelectedIndex(tabpos);
				}
			});
			if(corp.isOpRoundDone()) { corpbutton.setEnabled(false); }
			panelleftmiddel.add(corpbutton, c);
			COUNTER = COUNTER + 1;
		}
				
		JButton done = new JButton("Operation Round Ends");
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = COUNTER;
		c.gridwidth = 2;
		c.insets = new Insets(10, 0, 0, 0);
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (basic.getGameplay().getOperationroundCounter() < basic.getGameplay().getMaxOperationrounds()) {
					basic.newOperationRound();
				} else {
					basic.switchToStockMarketRound();
				}
			}
		});
		panelleftmiddel.add(done,c);
		
		List<Corporation> corps = basic.getGameplay().exceedTrains(basic);
		if(corps.size()>0) panelleftmiddel.removeAll();
	}
	
	public JPanel getPanelleftmiddel() {
		return panelleftmiddel;
	}
}
