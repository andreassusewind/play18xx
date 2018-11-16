package com.play18xx.graphic;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.play18xx.material.Basic;
import com.play18xx.material.Corporation;
import com.play18xx.material.Private;
import com.play18xx.material.Station;
import com.play18xx.material.Train;

public class PaneCorporation {
	private final static int tabpos = 3;

	public static void setPaneCorporation(Basic basic) {
		int COUNTER = 0;

		for (Corporation corp : basic.getCorporations()) {// int i=0; i<basic.getCorporations().size(); i++) {
			if (corp.isOpen()) {
				if (basic.getTP().getTabCount() > tabpos + COUNTER) {
					basic.getTP().removeTabAt(tabpos + COUNTER);
				}

				JPanel panel = new JPanel();
				panel.setLayout(new GridBagLayout());
				GridBagConstraints c = new GridBagConstraints();

				JLabel label = new JLabel("President for " + corp.getName() + " is " + corp.getPresident().getName());
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
				for (Private priv : corp.getPrivates()) {
					label = new JLabel(priv.getName());
					c.gridx = COUNTERpriv;
					c.gridy = 7;
					c.gridwidth = 1;
					c.anchor = GridBagConstraints.PAGE_START;
					panel.add(label, c);

					label = new JLabel(String.valueOf(priv.getRevenue()));
					c.gridx = COUNTERpriv;
					c.gridy = 8;
					c.gridwidth = 1;
					c.anchor = GridBagConstraints.PAGE_START;
					panel.add(label, c);

					COUNTERpriv = COUNTERpriv + 1;
				}

				ImageIcon newicon = new ImageIcon(); // dummy ImageIcon maybe set in later versions
				basic.getTP().insertTab(corp.getName(), newicon, panel, corp.getName(), tabpos + COUNTER);

				COUNTER = COUNTER + 1;
			}
		}
	}
}
