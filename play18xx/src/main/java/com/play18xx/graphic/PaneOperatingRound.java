package com.play18xx.graphic;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.play18xx.graphic.paneoperatinground.Panelleft;
import com.play18xx.graphic.paneoperatinground.Panelright;
import com.play18xx.graphic.paneoperatinground.Panemiddel;
import com.play18xx.material.Basic;

public class PaneOperatingRound {
	private final static int tabpos = 1;

	JPanel panel           = new JPanel(new GridLayout(1, 3));
	Panelleft  panelleft   = new Panelleft();
	Panemiddel panelmiddel = new Panemiddel();
	Panelright panelright  = new Panelright();
	GridBagConstraints c   = new GridBagConstraints();

	public static void setPaneOperatingRound(Basic basic) {
		basic.getTP().getPOR().getPanelleft().removeAll();
		basic.getTP().getPOR().setPanel(basic);
	}
		
	public void setPanel(Basic basic) {
		if (basic.getTP().getTabCount() > tabpos) {
			basic.getTP().removeTabAt(tabpos);
		}
		
		this.panelleft.setPanel(basic);
		
		panel.add(panelleft);
		panel.add(panelmiddel);
		panel.add(panelright);
		
		ImageIcon newicon = new ImageIcon(); // dummy ImageIcon maybe set in later versions
		basic.getTP().insertTab("Operating Round", newicon, panel, "Operating Round", tabpos);
	}
	
	public Panemiddel getPanemiddel() {
		return panelmiddel;
	}
	public Panelleft getPanelleft() {
		return panelleft;
	}
	public Panelright getPanelright() {
		return panelright;
	}
	public void refreshPanelleft() {
		this.panelleft = new Panelleft();
	}
	
	
}
