package com.play18xx.graphic;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import com.play18xx.material.Basic;
import com.play18xx.material.Player;
import com.play18xx.material.Private;

public class PaneStatistic {
	private final static int tabpos = 3;

	public static void setPaneStatistic(Basic basic) {
		if (basic.getTP().getTabCount() > tabpos) {
			basic.getTP().removeTabAt(tabpos);
		}

		JPanel panel = new JPanel(new GridLayout(3, 1));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0.01;
		
		GPS gps = new GPS(basic);
		JScrollPane gpsscroll  = new JScrollPane(gps);
		gpsscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		gpsscroll.setRowHeaderView(new GPSH());
		panel.add(gpsscroll,c);
		
		PS ps = new PS(basic);
		JScrollPane psscroll  = new JScrollPane(ps);
		psscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		psscroll.setRowHeaderView(new PSH());
		panel.add(psscroll,c);

		ImageIcon newicon = new ImageIcon(); // dummy ImageIcon maybe set in later versions
		basic.getTP().insertTab("Game Statistics", newicon, panel, "Game Statistics", tabpos);
	}
}

class GPS  extends JPanel{
	private static final long serialVersionUID = 1L;

	public GPS(Basic basic) {
		this.setLayout(new GridLayout(5, basic.getGameplay().getGameplayStatistic().size()));
		Border border = BorderFactory.createEmptyBorder(0, 10, 0, 20);
		JLabel label;
		for(int i=basic.getGameplay().getGameplayStatistic().size()-1; i>=0; i--) {
			label = new JLabel(""+basic.getGameplay().getGameplayStatistic().get(i).getStockmarketRoundCounter());
			label.setBorder(border);
			this.add(label);
		}
		for(int i=basic.getGameplay().getGameplayStatistic().size()-1; i>=0; i--) {
			label = new JLabel(""+basic.getGameplay().getGameplayStatistic().get(i).getOperationroundCounter());
			label.setBorder(border);
			this.add(label);
		}
		for(int i=basic.getGameplay().getGameplayStatistic().size()-1; i>=0; i--) {
			label = new JLabel(""+basic.getGameplay().getGameplayStatistic().get(i).getPlayerMoney());
			label.setBorder(border);
			this.add(label);
		}
		for(int i=basic.getGameplay().getGameplayStatistic().size()-1; i>=0; i--) {
			label = new JLabel(""+basic.getGameplay().getGameplayStatistic().get(i).getCorporationMoney());
			label.setBorder(border);
			this.add(label);
		}
		for(int i=basic.getGameplay().getGameplayStatistic().size()-1; i>=0; i--) {
			label = new JLabel(""+basic.getGameplay().getGameplayStatistic().get(i).getFreeFlowMoney());
			label.setBorder(border);
			this.add(label);
		}
	}
}
class GPSH extends JPanel{
	private static final long serialVersionUID = 1L;

	public GPSH() {
		this.setLayout(new GridLayout(5,1));
		Border border = BorderFactory.createEmptyBorder(0, 10, 0, 20);
		JLabel label; 
		label = new JLabel("Round of Game");
		label.setBorder(border);
		this.add(label);
		label = new JLabel("Operation Round");
		label.setBorder(border);
		this.add(label);
		label = new JLabel("Sum of Playermoney");
		label.setBorder(border);
		this.add(label);
		label = new JLabel("Sum of Corpmoney");
		label.setBorder(border);
		this.add(label);
		label = new JLabel("Sum of all Money");
		label.setBorder(border);
		this.add(label);
	}
}

class PS extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public PS(Basic basic) {
		this.setLayout(new GridBagLayout());
		Border border = BorderFactory.createEmptyBorder(0, 10, 0, 20);
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;

		List<Player> Players = new ArrayList<Player>(basic.getPlayers());
		int[] array = new int[Players.size()];

		for(int i=0; i<array.length; i++) {
			array[i] = Players.get(i).getPlayersOverallValue(basic);
		}
		
		JLabel label;
		for(int i=0; i<array.length; i++) {
			int j = getIndexOfLargest(array);
			array[j] = 0;
			
			label = new JLabel(Players.get(j).getName());
			label.setBorder(border);
			c.gridy = 0;
			c.gridx = i;
			this.add(label,c);
			
			label = new JLabel(""+Players.get(j).getMoney());
			label.setBorder(border);
			c.gridy = GridBagConstraints.RELATIVE;
			this.add(label,c);
			
			if(Players.get(j).getPlayersCertsCount(basic) == Players.get(j).getPlayersCertsOverall(basic)) {
				label = new JLabel(Players.get(j).getPlayersCertsCount(basic) + "");
			}
			else {
				label = new JLabel(Players.get(j).getPlayersCertsCount(basic) + " (" + Players.get(j).getPlayersCertsOverall(basic) + ")");
			}
			label.setBorder(border);
			this.add(label,c);

			label = new JLabel(""+Players.get(j).getPlayersOverallValue(basic));
			label.setBorder(border);
			this.add(label,c);
		}
	}
	public int getIndexOfLargest( int[] array )
	{
	  if ( array == null || array.length == 0 ) return -1; // null or empty
	
	  int largest = 0;
	  for ( int i = 1; i < array.length; i++ )
	  {
	      if ( array[i] > array[largest] ) largest = i;
	  }
	  return largest; // position of the first largest found
	}
}
class PSH extends JPanel{
	private static final long serialVersionUID = 1L;
	public PSH() {
		this.setLayout(new GridBagLayout());
		Border border = BorderFactory.createEmptyBorder(0, 10, 0, 20);
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;

		JLabel label; 

		label = new JLabel("Name");
		c.gridy = 0;
		c.gridx = 0;
		label.setBorder(border);
		this.add(label,c);
		
		label = new JLabel("Money");
		c.gridy = GridBagConstraints.RELATIVE;
		label.setBorder(border);
		this.add(label,c);

		label = new JLabel("Total Shares");
		label.setBorder(border);
		this.add(label, c);
		
		label = new JLabel("Total Value");
		label.setBorder(border);
		this.add(label,c);
	}
}

	
class PanePlayerStatistic extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	class PlayerStat{
		public Player player;
		public int totalmoney;
		public PlayerStat(Player player, Basic basic) {
			this.player = player;
			totalmoney = player.getMoney() + player.getPlayersCertsValue(basic);
		}
	}
	List<PlayerStat> players = new ArrayList<PlayerStat>();
	
	public PanePlayerStatistic(Basic basic) {
		for(Player player : basic.getPlayers()) {
			players.add(new PlayerStat(player, basic));
		}
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel label = new JLabel("Player Statistics");
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(20, 0, 0, 0);
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.PAGE_START;
		this.add(label,c);
		label = new JLabel("Player");
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 20, 0, 0);
		this.add(label,c);
		label = new JLabel("Total Values");
		c.gridx = 0;
		c.gridy = 2;
		this.add(label,c);
		label = new JLabel("");
		c.gridx = 0;
		c.gridy = 3;
		this.add(label,c);
		label = new JLabel("");
		c.gridx = 0;
		c.gridy = 4;
		this.add(label,c);
		label = new JLabel("");
		c.gridx = 0;
		c.gridy = 5;
		this.add(label,c);
		
		int COUNTER = 1;
		for(PlayerStat player : players) {
			label = new JLabel(""+player.getClass().getName());
			c.gridx = COUNTER;
			c.gridy = 1;
			this.add(label, c);
			
			COUNTER = COUNTER + 1;
		}
	}
}
