package com.play18xx.graphic;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.play18xx.material.Basic;
import com.play18xx.material.Corporation;
import com.play18xx.material.Dividend;
import com.play18xx.material.Private;
import com.play18xx.material.Station;
import com.play18xx.material.Train;

public class PaneCorporation {

	private final static int tabpos = 4;

	public static void setPaneCorporation(Basic basic) {
		int COUNTER = 0;

		for (Corporation corp : basic.getCorporations()) {
			if (corp.isOpen()) {
				if (basic.getTP().getTabCount() > tabpos + COUNTER) {
					basic.getTP().removeTabAt(tabpos + COUNTER);
				}
				JPanel panel = setPane(basic, corp);
				
				ImageIcon newicon = new ImageIcon(); // dummy ImageIcon maybe set in later versions
				basic.getTP().insertTab(corp.getName(), newicon, panel, corp.getName(), tabpos + COUNTER);

				COUNTER = COUNTER + 1;
			}
		}
	}
	
	public static JPanel setPane(Basic basic, Corporation corp) {
		JPanel panel           = new JPanel(new GridLayout(1, 2));
		

		
		
		panel.add(panelleft(basic, corp));
		panel.add(panelright(basic, corp));

		return panel;
	}
	
	public static JPanel panelleft(Basic basic, Corporation corp) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JLabel label = new JLabel("President for " + corp.getName() + " is " + basic.getPlayers().get((corp.getPresident())).getName());
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.PAGE_START;
		panel.add(label, c);

		label = new JLabel("Money: ");
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.PAGE_START;
		panel.add(label, c);
		label = new JLabel("" + corp.getMoney());
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.PAGE_START;
		panel.add(label, c);

		int COUNTERstation = 1;
		label = new JLabel("Stations: ");
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.PAGE_START;
		panel.add(label, c);
		for (Station station : corp.getStations()) {
			String build = "O";
			if (station.isBuild()) {
				build = "X";
			}
			;
			label = new JLabel(build + "      ");
			c.gridx = COUNTERstation;
			c.gridy = 3;
			c.gridwidth = 1;
			c.anchor = GridBagConstraints.PAGE_START;
			panel.add(label, c);

			COUNTERstation = COUNTERstation + 1;
		}
		COUNTERstation = 1;
		label = new JLabel("Costs: ");
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.PAGE_START;
		panel.add(label, c);
		for (Station station : corp.getStations()) {
			label = new JLabel(station.getCost() + "      ");
			c.gridx = COUNTERstation;
			c.gridy = 4;
			c.gridwidth = 1;
			c.anchor = GridBagConstraints.PAGE_START;
			panel.add(label, c);

			COUNTERstation = COUNTERstation + 1;
		}

		int COUNTERtrain = 1;
		label = new JLabel("Train: ");
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.PAGE_START;
		panel.add(label, c);
		label = new JLabel("Rusts: ");
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.PAGE_START;
		panel.add(label, c);
		for (Train train : corp.getTrains()) {
			label = new JLabel(train.getDistancePrimary() + "");
			c.gridx = COUNTERtrain;
			c.gridy = 5;
			c.gridwidth = 1;
			c.anchor = GridBagConstraints.PAGE_START;
			panel.add(label, c);

			label = new JLabel(train.getRust() + "");
			c.gridx = COUNTERtrain;
			c.gridy = 6;
			c.gridwidth = 1;
			c.anchor = GridBagConstraints.PAGE_START;
			panel.add(label, c);

			COUNTERtrain = COUNTERtrain + 1;
		}

		int COUNTERpriv = 1;
		label = new JLabel("Privates: ");
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.PAGE_START;
		panel.add(label, c);
		label = new JLabel("Revenue: ");
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.PAGE_START;
		panel.add(label, c);
		for(Private priv : basic.getPrivates()) {
			if(priv.getOwner()-30 == corp.getIndex()) {
				label = new JLabel(priv.getName());
				c.gridx = COUNTERpriv;
				c.gridy = 7;
				panel.add(label,c);
				label = new JLabel(priv.getRevenue()+"");
				c.gridy = 8;
				panel.add(label,c);
				COUNTERpriv++;
			}
		}
		
		
		return panel;
	}
	
	public static JScrollPane panelright(Basic basic, Corporation corp) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
        
		JLabel label;
		
		if(corp.getDividends().size() > 0) {
			int reversecounter = corp.getDividends().size() - 1; 
			for(int i=0; i<corp.getDividends().size(); i++) {
				
				Dividend div = corp.getDividends().get(reversecounter - i);
				
				label = new JLabel("Game Round:   ");
				c.gridx = 20;
				c.gridy = (i*5)+1;
				c.gridwidth = 1;
				c.anchor = GridBagConstraints.PAGE_START;
				panel.add(label, c);
				label = new JLabel(""+ div.getGamePhase());
				c.gridx = 21;
				c.gridy = (i*5)+1;
				c.gridwidth = 1;
				c.anchor = GridBagConstraints.PAGE_START;
				panel.add(label, c);
				
				label = new JLabel("Dividend:   ");
				c.gridx = 20;
				c.gridy = (i*5)+2;
				c.gridwidth = 1;
				c.anchor = GridBagConstraints.PAGE_START;
				panel.add(label, c);
				label = new JLabel(""+ div.getDividend());
				c.gridx = 21;
				c.gridy = (i*5)+2;
				c.gridwidth = 1;
				c.anchor = GridBagConstraints.PAGE_START;
				panel.add(label, c);

				label = new JLabel("Payout:   ");
				c.gridx = 20;
				c.gridy = (i*5)+3;
				c.gridwidth = 1;
				c.anchor = GridBagConstraints.PAGE_START;
				panel.add(label, c);
				label = new JLabel(""+ div.isPayout());
				c.gridx = 21;
				c.gridy = (i*5)+3;
				c.gridwidth = 1;
				c.anchor = GridBagConstraints.PAGE_START;
				panel.add(label, c);

				int COUNTERtrain = 1;
				label = new JLabel("Trains:   ");
				c.gridx = 20;
				c.gridy = (i*5)+4;
				c.gridwidth = 1;
				c.anchor = GridBagConstraints.PAGE_START;
				panel.add(label, c);
				for (Train train : div.getTrainSet()) {
					label = new JLabel(train.getDistancePrimary() + "");
					c.gridx = 20 + COUNTERtrain;
					c.gridy = (i*5)+4;
					c.gridwidth = 1;
					c.anchor = GridBagConstraints.PAGE_START;
					panel.add(label, c);

					COUNTERtrain = COUNTERtrain + 1;
				}

				label = new JLabel("--------------------------");
				c.gridx = 20;
				c.gridy = (i*5)+5;
				c.gridwidth = 1;
				c.insets = new Insets(20,20,20,20);
				c.anchor = GridBagConstraints.PAGE_START;
				panel.add(label, c);
				c.insets = new Insets(0,0,0,0);
				
			}
		}
		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		return scrollPane;
	}
}
