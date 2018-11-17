package com.play18xx.graphic;

import java.awt.GraphicsEnvironment;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;

import com.play18xx.app.App;

public class FrameMain extends JTabbedPane {

	PaneOperatingRound POR = new PaneOperatingRound();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FrameMain() {
		this.setTabPlacement(JTabbedPane.LEFT);
		this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		JDialog frame = new JDialog();

		frame.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
		frame.setTitle(App.gamename);
		frame.add(this);
		frame.setVisible(true);
	}

	public PaneOperatingRound getPOR() {
		return POR;
	}

	public void setPOR(PaneOperatingRound pOR) {
		POR = pOR;
	}
	
	public void refreshPOR() {
		this.POR = new PaneOperatingRound();
	}
}
